package com.yizhaiyiju.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends androidx.appcompat.app.AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        ApiHelper.initFromPrefs(this);
        AnnouncementActivity.checkAndShow(this);
        UpdateHelper.checkUpdate(this);

        View fragmentContainer = findViewById(R.id.fragment_container);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        int navServicesId = getResources().getIdentifier("nav_services", "id", getPackageName());

        final int fragmentTop = fragmentContainer.getPaddingTop();
        final int fragmentLeft = fragmentContainer.getPaddingLeft();
        final int fragmentRight = fragmentContainer.getPaddingRight();
        final int fragmentBottom = fragmentContainer.getPaddingBottom();
        final int navLeft = bottomNav.getPaddingLeft();
        final int navTop = bottomNav.getPaddingTop();
        final int navRight = bottomNav.getPaddingRight();
        final int navBottom = bottomNav.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            fragmentContainer.setPadding(fragmentLeft, fragmentTop + insets.top, fragmentRight, fragmentBottom);
            bottomNav.setPadding(navLeft, navTop, navRight, navBottom + insets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Hide Services tab for lite version
        if (!BuildConfig.FULL_VERSION) {
            if (navServicesId != 0) {
                bottomNav.getMenu().removeItem(navServicesId);
            }
        }

        if (savedInstanceState == null) {
            String targetTab = getIntent().getStringExtra("tab");
            Fragment initFragment = new HomeFragment();
            if ("test".equals(targetTab)) {
                initFragment = new TestListFragment();
            } else if ("services".equals(targetTab) && navServicesId != 0) {
                initFragment = new ServicesFragment();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, initFragment)
                    .commit();
            if ("test".equals(targetTab)) {
                bottomNav.setSelectedItemId(R.id.nav_test);
            } else if ("services".equals(targetTab) && navServicesId != 0) {
                bottomNav.setSelectedItemId(navServicesId);
            }
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (id == R.id.nav_test) {
                fragment = new TestListFragment();
            } else if (id == navServicesId && navServicesId != 0) {
                fragment = new ServicesFragment();
            } else if (id == R.id.nav_articles) {
                fragment = new ArticlesFragment();
            } else if (id == R.id.nav_profile) {
                fragment = new ProfileFragment();
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            }
            return false;
        });
    }

    public void switchToTab(int tabId) {
        ((BottomNavigationView) findViewById(R.id.bottom_nav)).setSelectedItemId(tabId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("app_prefs", 0);
        if (prefs.getBoolean("goto_test_tab", false)) {
            prefs.edit().putBoolean("goto_test_tab", false).apply();
            BottomNavigationView bn = findViewById(R.id.bottom_nav);
            if (bn != null) {
                int testTabId = R.id.nav_test;
                int servicesTabId = getResources().getIdentifier("nav_services", "id", getPackageName());
                if (bn.getMenu().findItem(testTabId) != null) {
                    bn.setSelectedItemId(testTabId);
                } else if (servicesTabId != 0 && bn.getMenu().findItem(servicesTabId) != null) {
                    bn.setSelectedItemId(servicesTabId);
                }
            }
        }
    }
}
