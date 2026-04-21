package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private void goToLogin() {
        Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    private void updateUI(View view) {
        View layoutGuest = view.findViewById(R.id.layout_guest);
        View layoutUser = view.findViewById(R.id.layout_user);

        if (!ApiHelper.isLoggedIn()) {
            layoutGuest.setVisibility(View.VISIBLE);
            layoutUser.setVisibility(View.GONE);
            return;
        }

        layoutGuest.setVisibility(View.GONE);
        layoutUser.setVisibility(View.VISIBLE);

        String phone = ApiHelper.getUserPhone();
        TextView tvUsername = view.findViewById(R.id.tv_username);
        if (phone != null && phone.length() == 11) {
            phone = phone.substring(0, 3) + "****" + phone.substring(7);
        }
        tvUsername.setText(phone != null ? phone : "用户");

        TextView tvMemberType = view.findViewById(R.id.tv_member_type);
        String memberText = ApiHelper.getMemberType() != null ? ApiHelper.getMemberType() : "普通用户";
        String expires = ApiHelper.getMemberExpires();
        if (expires != null && !expires.isEmpty()) {
            String formatted = formatExpires(expires);
            if (formatted != null) {
                memberText = memberText + " · " + formatted + "到期";
            }
        }
        tvMemberType.setText(memberText);

        String email = requireContext().getSharedPreferences("user", 0).getString("email", "");
        TextView tvEmail = view.findViewById(R.id.tv_email);
        if (email != null && !email.isEmpty()) {
            tvEmail.setText(email);
            tvEmail.setVisibility(View.VISIBLE);
        } else {
            tvEmail.setVisibility(View.GONE);
        }
    }

    private String formatExpires(String iso) {
        String[] patterns = {"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd"};
        for (String p : patterns) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new SimpleDateFormat(p, Locale.CHINA).parse(iso));
            } catch (Exception e) { }
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ApiHelper.initFromPrefs(requireContext());

        view.findViewById(R.id.btn_login).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), LoginActivity.class)));

        view.findViewById(R.id.btn_my_consults).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MyConsultActivity.class)));

        view.findViewById(R.id.btn_my_tests).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MyTestsActivity.class)));

        view.findViewById(R.id.btn_settings).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), SettingsActivity.class)));

        view.findViewById(R.id.btn_feedback).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), FeedbackActivity.class)));

        // Trial pack
        view.findViewById(R.id.btn_buy_trial).setOnClickListener(v -> {
            if (!ApiHelper.isLoggedIn()) { goToLogin(); return; }
            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            intent.putExtra("product_id", "trial_pack");
            intent.putExtra("product_name", "AI体验包");
            intent.putExtra("product_price", "9.9");
            startActivity(intent);
        });

        // Monthly membership
        view.findViewById(R.id.btn_buy_monthly).setOnClickListener(v -> {
            if (!ApiHelper.isLoggedIn()) { goToLogin(); return; }
            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            intent.putExtra("product_id", "member_monthly");
            intent.putExtra("product_name", "月度会员");
            intent.putExtra("product_price", "29.9");
            startActivity(intent);
        });

        // Annual membership
        view.findViewById(R.id.btn_buy_yearly).setOnClickListener(v -> {
            if (!ApiHelper.isLoggedIn()) { goToLogin(); return; }
            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            intent.putExtra("product_id", "member_yearly");
            intent.putExtra("product_name", "年度会员");
            intent.putExtra("product_price", "599");
            startActivity(intent);
        });

        // Logout
        view.findViewById(R.id.btn_logout).setOnClickListener(v -> {
            ApiHelper.clearAuth(requireContext());
            updateUI(view);
            Toast.makeText(getContext(), "已退出登录", Toast.LENGTH_SHORT).show();
        });

        updateUI(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiHelper.initFromPrefs(requireContext());
        if (getView() != null) {
            updateUI(getView());
        }
    }
}
