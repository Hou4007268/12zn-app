package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_ai_chat).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), ChatActivity.class)));

        view.findViewById(R.id.btn_wuxing_test).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), CalendarActivity.class)));

        view.findViewById(R.id.btn_fortune).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), FortuneActivity.class)));

        view.findViewById(R.id.btn_consult).setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), ConsultActivity.class)));

        view.findViewById(R.id.btn_more_tests).setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchToTab(R.id.nav_test);
            }
        });

        view.findViewById(R.id.btn_services_card).setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).openServicesPage();
            }
        });
    }
}
