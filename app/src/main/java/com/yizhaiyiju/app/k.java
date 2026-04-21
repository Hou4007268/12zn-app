package com.yizhaiyiju.app;

import com.yizhaiyiju.app.TestBillingHelper;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class k implements TestBillingHelper.BillCallback, u1.i {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2346a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d.s f2347b;

    public /* synthetic */ k(d.s sVar, int i4) {
        this.f2346a = i4;
        this.f2347b = sVar;
    }

    @Override // com.yizhaiyiju.app.TestBillingHelper.BillCallback
    public final void onAllowed() {
        int i4 = this.f2346a;
        d.s sVar = this.f2347b;
        switch (i4) {
            case 0:
                ((BaZiActivity) sVar).lambda$onCreate$0();
                break;
            case 1:
                ((FlyingStarActivity) sVar).lambda$onCreate$0();
                break;
            case 2:
                ((GenericTestActivity) sVar).lambda$onCreate$3();
                break;
            case 3:
                ((KuaNumberActivity) sVar).lambda$onCreate$0();
                break;
            case 4:
            default:
                ((WuxingTestActivity) sVar).lambda$onCreate$3();
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                ((MarriageMatchActivity) sVar).lambda$onCreate$0();
                break;
            case 6:
                ((NameAnalysisActivity) sVar).lambda$onCreate$0();
                break;
        }
    }
}
