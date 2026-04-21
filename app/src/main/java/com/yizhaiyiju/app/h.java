package com.yizhaiyiju.app;

import com.yizhaiyiju.app.ApiHelper;
import com.yizhaiyiju.app.DirectChatActivity;
import com.yizhaiyiju.app.MessagesFragment;
import com.yizhaiyiju.app.RedeemActivity;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2333a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2334b;

    public /* synthetic */ h(int i4, Object obj) {
        this.f2333a = i4;
        this.f2334b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2333a;
        Object obj = this.f2334b;
        switch (i4) {
            case 0:
                ((ApiHelper.Callback) obj).onError("订单创建失败：返回数据不完整");
                break;
            case 1:
                ((DirectChatActivity.AnonymousClass2) obj).lambda$onResponse$0();
                break;
            case 2:
                ((MessagesFragment.AnonymousClass1) obj).lambda$onError$3();
                break;
            case 3:
                ((RedeemActivity.AnonymousClass1) obj).lambda$onResponse$2();
                break;
            case 4:
                ((BaZiActivity) obj).lambda$calculate$2();
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                ((DirectChatActivity) obj).updateRecordingTime();
                break;
            case 6:
                ((FlyingStarActivity) obj).lambda$calculate$2();
                break;
            case 7:
                ((KuaNumberActivity) obj).lambda$calculate$2();
                break;
            case 8:
                ((MarriageMatchActivity) obj).lambda$calculate$2();
                break;
            default:
                ((NameAnalysisActivity) obj).lambda$calculate$2();
                break;
        }
    }
}
