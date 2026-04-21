package com.yizhaiyiju.app;

import android.view.View;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class k0 implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2348e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ LoginActivity f2349f;

    public /* synthetic */ k0(LoginActivity loginActivity, int i4) {
        this.f2348e = i4;
        this.f2349f = loginActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2348e;
        LoginActivity loginActivity = this.f2349f;
        switch (i4) {
            case 0:
                loginActivity.lambda$resetPassword$6(view);
                break;
            case 1:
                loginActivity.lambda$switchMode$5(view);
                break;
            case 2:
                loginActivity.lambda$onCreate$0(view);
                break;
            case 3:
                loginActivity.lambda$onCreate$1(view);
                break;
            case 4:
                loginActivity.lambda$onCreate$2(view);
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                loginActivity.lambda$onCreate$3(view);
                break;
            default:
                loginActivity.lambda$onCreate$4(view);
                break;
        }
    }
}
