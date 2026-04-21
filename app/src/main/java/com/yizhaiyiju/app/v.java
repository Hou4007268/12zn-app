package com.yizhaiyiju.app;

import android.view.View;

/* loaded from: classes.dex */
public final /* synthetic */ class v implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2386e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ DirectChatActivity f2387f;

    public /* synthetic */ v(DirectChatActivity directChatActivity, int i4) {
        this.f2386e = i4;
        this.f2387f = directChatActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2386e;
        DirectChatActivity directChatActivity = this.f2387f;
        switch (i4) {
            case 0:
                directChatActivity.lambda$onCreate$0(view);
                break;
            case 1:
                directChatActivity.lambda$onCreate$1(view);
                break;
            case 2:
                directChatActivity.lambda$onCreate$3(view);
                break;
            case 3:
                directChatActivity.lambda$onCreate$4(view);
                break;
            case 4:
                directChatActivity.lambda$showContactDialog$6(view);
                break;
            default:
                directChatActivity.lambda$showContactDialog$7(view);
                break;
        }
    }
}
