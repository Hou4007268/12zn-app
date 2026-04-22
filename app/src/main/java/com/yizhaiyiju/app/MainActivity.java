package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends androidx.appcompat.app.AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiHelper.initFromPrefs(this);
        AnnouncementActivity.checkAndShow(this);
        UpdateHelper.checkUpdate(this);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // Keep 5 bottom tabs on all flavors: 首页/测试/聊天/文章/我的
        // 服务入口放在首页大卡片，避免超出 BottomNavigationView 5 项限制

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (id == R.id.nav_test) {
                fragment = new TestListFragment();
            } else if (id == R.id.nav_messages) {
                fragment = new MessagesFragment();
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

    public void openServicesPage() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ServicesFragment())
                .commit();
    }

    public void switchToTab(int tabId) {
        ((BottomNavigationView) findViewById(R.id.bottom_nav)).setSelectedItemId(tabId);
    }
}
