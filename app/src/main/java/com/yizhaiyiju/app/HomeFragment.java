package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private void openAiChat(View view) { startActivity(new Intent(getActivity(), (Class<?>) ChatActivity.class)); }
    private void openCalendar(View view) { startActivity(new Intent(getActivity(), (Class<?>) CalendarActivity.class)); }
    private void openBaziExperience(View view) {
        Intent intent = new Intent(getActivity(), (Class<?>) ArticleDetailActivity.class);
        intent.putExtra("article_title", "八字命理体验");
        intent.putExtra("article_url", "https://12zn.com/test/bazi/");
        startActivity(intent);
    }
    private void openConsult(View view) { startActivity(new Intent(getActivity(), (Class<?>) ConsultActivity.class)); }
    private void openTests(View view) { if (getActivity() instanceof MainActivity) ((MainActivity) getActivity()).switchToTab(R.id.nav_test); }
    private void openServices(View view) { if (getActivity() instanceof MainActivity) ((MainActivity) getActivity()).switchToTab(R.id.nav_services); }
    @Override public u0.b getDefaultViewModelCreationExtras() { return u0.a.f4680b; }
    @Override public View onCreateView(LayoutInflater i, ViewGroup g, Bundle b) { return i.inflate(R.layout.fragment_home, g, false); }
    @Override public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.btn_ai_chat).setOnClickListener(this::openAiChat);
        view.findViewById(R.id.btn_wuxing_test).setOnClickListener(this::openCalendar);
        view.findViewById(R.id.btn_fortune).setOnClickListener(this::openBaziExperience);
        view.findViewById(R.id.btn_consult).setOnClickListener(this::openConsult);
        view.findViewById(R.id.btn_more_tests).setOnClickListener(this::openTests);
        view.findViewById(R.id.btn_all_services).setOnClickListener(this::openServices);
    }
}
