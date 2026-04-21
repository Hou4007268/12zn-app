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
        Toast.makeText(getContext(), "邮箱已复制", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(View view) {
        copyToClipboard("hou4007268@gmail.com");
        Toast.makeText(getContext(), "邮箱已复制", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://12zn.com")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$3(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) ConsultActivity.class));
    }

    @Override // androidx.lifecycle.i
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_contact, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        final int i4 = 0;
        view.findViewById(R.id.btn_wechat).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.t

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ContactFragment f2379f;

            {
                this.f2379f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i5 = i4;
                ContactFragment contactFragment = this.f2379f;
                switch (i5) {
                    case 0:
                        contactFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        contactFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        contactFragment.lambda$onViewCreated$2(view2);
                        break;
                    default:
                        contactFragment.lambda$onViewCreated$3(view2);
                        break;
                }
            }
        });
        final int i5 = 1;
        view.findViewById(R.id.btn_email).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.t

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ContactFragment f2379f;

            {
                this.f2379f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i52 = i5;
                ContactFragment contactFragment = this.f2379f;
                switch (i52) {
                    case 0:
                        contactFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        contactFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        contactFragment.lambda$onViewCreated$2(view2);
                        break;
                    default:
                        contactFragment.lambda$onViewCreated$3(view2);
                        break;
                }
            }
        });
        final int i6 = 2;
        view.findViewById(R.id.btn_website).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.t

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ContactFragment f2379f;

            {
                this.f2379f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i52 = i6;
                ContactFragment contactFragment = this.f2379f;
                switch (i52) {
                    case 0:
                        contactFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        contactFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        contactFragment.lambda$onViewCreated$2(view2);
                        break;
                    default:
                        contactFragment.lambda$onViewCreated$3(view2);
                        break;
                }
            }
        });
        final int i7 = 3;
        view.findViewById(R.id.btn_book_consult).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.t

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ContactFragment f2379f;

            {
                this.f2379f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i52 = i7;
                ContactFragment contactFragment = this.f2379f;
                switch (i52) {
                    case 0:
                        contactFragment.lambda$onViewCreated$0(view2);
                        break;
                    case 1:
                        contactFragment.lambda$onViewCreated$1(view2);
                        break;
                    case 2:
                        contactFragment.lambda$onViewCreated$2(view2);
                        break;
                    default:
                        contactFragment.lambda$onViewCreated$3(view2);
                        break;
                }
            }
        });
    }
}
