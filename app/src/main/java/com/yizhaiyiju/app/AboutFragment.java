package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* loaded from: classes.dex */
public class AboutFragment extends Fragment {
    @Override // androidx.lifecycle.i
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_about, viewGroup, false);
    }
}
