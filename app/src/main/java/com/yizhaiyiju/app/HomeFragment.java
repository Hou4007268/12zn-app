package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* loaded from: classes.dex */
public class HomeFragment extends Fragment {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) ChatActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) CalendarActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) FortuneActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$3(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) ConsultActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$4(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).switchToTab(R.id.nav_test);
        }
    }

    @Override
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.btn_ai_chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                HomeFragment.this.lambda$onViewCreated$0(view2);
            }
        });
        view.findViewById(R.id.btn_wuxing_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                HomeFragment.this.lambda$onViewCreated$1(view2);
            }
        });
        view.findViewById(R.id.btn_fortune).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                HomeFragment.this.lambda$onViewCreated$2(view2);
            }
        });
        view.findViewById(R.id.btn_consult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                HomeFragment.this.lambda$onViewCreated$3(view2);
            }
        });
        view.findViewById(R.id.btn_more_tests).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                HomeFragment.this.lambda$onViewCreated$4(view2);
            }
        });
    }
}
