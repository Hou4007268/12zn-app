package com.yizhaiyiju.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

/* loaded from: classes.dex */
public class ContactFragment extends Fragment {
    private void copyToClipboard(String str) {
        ((ClipboardManager) requireContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        copyToClipboard("hou4007268@gmail.com");
        Toast.makeText(getContext(), "邮箱已复制", Toast.LENGTH_SHORT).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(View view) {
        copyToClipboard("hou4007268@gmail.com");
        Toast.makeText(getContext(), "邮箱已复制", Toast.LENGTH_SHORT).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://12zn.com")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$3(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) ConsultActivity.class));
    }

    @Override
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_contact, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.btn_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ContactFragment.this.lambda$onViewCreated$0(view2);
            }
        });
        view.findViewById(R.id.btn_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ContactFragment.this.lambda$onViewCreated$1(view2);
            }
        });
        view.findViewById(R.id.btn_website).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ContactFragment.this.lambda$onViewCreated$2(view2);
            }
        });
        view.findViewById(R.id.btn_book_consult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ContactFragment.this.lambda$onViewCreated$3(view2);
            }
        });
    }
}
