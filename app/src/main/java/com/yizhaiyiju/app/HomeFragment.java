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

    @Override // androidx.lifecycle.i
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        final int i4 = 0;
        view.findViewById(R.id.btn_ai_chat).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.i0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ HomeFragment f2340f;

            {
                this.f2340f = HomeFragment.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i5 = i4;
                HomeFragment homeFragment = this.f2340f;
                switch (i5) {
                    case 0:
                        homeFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        homeFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        homeFragment.lambda$onViewCreated$2(view2);
                        break;
                    case 3:
                        homeFragment.lambda$onViewCreated$3(view2);
                        break;
                    default:
                        homeFragment.lambda$onViewCreated$4(view2);
                        break;
                }
            }
        });
        final int i5 = 1;
        view.findViewById(R.id.btn_wuxing_test).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.i0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ HomeFragment f2340f;

            {
                this.f2340f = HomeFragment.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i52 = i5;
                HomeFragment homeFragment = this.f2340f;
                switch (i52) {
                    case 0:
                        homeFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        homeFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        homeFragment.lambda$onViewCreated$2(view2);
                        break;
                    case 3:
                        homeFragment.lambda$onViewCreated$3(view2);
                        break;
                    default:
                        homeFragment.lambda$onViewCreated$4(view2);
                        break;
                }
            }
        });
        final int i6 = 2;
        view.findViewById(R.id.btn_fortune).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.i0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ HomeFragment f2340f;

            {
                this.f2340f = HomeFragment.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i52 = i6;
                HomeFragment homeFragment = this.f2340f;
                switch (i52) {
                    case 0:
                        homeFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        homeFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        homeFragment.lambda$onViewCreated$2(view2);
                        break;
                    case 3:
                        homeFragment.lambda$onViewCreated$3(view2);
                        break;
                    default:
                        homeFragment.lambda$onViewCreated$4(view2);
                        break;
                }
            }
        });
        final int i7 = 3;
        view.findViewById(R.id.btn_consult).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.i0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ HomeFragment f2340f;

            {
                this.f2340f = HomeFragment.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i52 = i7;
                HomeFragment homeFragment = this.f2340f;
                switch (i52) {
                    case 0:
                        homeFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        homeFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        homeFragment.lambda$onViewCreated$2(view2);
                        break;
                    case 3:
                        homeFragment.lambda$onViewCreated$3(view2);
                        break;
                    default:
                        homeFragment.lambda$onViewCreated$4(view2);
                        break;
                }
            }
        });
        final int i8 = 4;
        view.findViewById(R.id.btn_more_tests).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.i0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ HomeFragment f2340f;

            {
                this.f2340f = HomeFragment.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i52 = i8;
                HomeFragment homeFragment = this.f2340f;
                switch (i52) {
                    case 0:
                        homeFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        homeFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        homeFragment.lambda$onViewCreated$2(view2);
                        break;
                    case 3:
                        homeFragment.lambda$onViewCreated$3(view2);
                        break;
                    default:
                        homeFragment.lambda$onViewCreated$4(view2);
                        break;
                }
            }
        });
    }
}
