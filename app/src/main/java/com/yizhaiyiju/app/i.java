package com.yizhaiyiju.app;

import android.view.View;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class i implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2337e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2338f;

    public /* synthetic */ i(int i4, Object obj) {
        this.f2337e = i4;
        this.f2338f = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2337e;
        Object obj = this.f2338f;
        switch (i4) {
            case 0:
                ((ArticleDetailActivity) obj).lambda$onCreate$0(view);
                break;
            case 1:
                ((ArticlesFragment) obj).lambda$onViewCreated$0(view);
                break;
            case 2:
                ((FeedbackActivity) obj).lambda$onCreate$0(view);
                break;
            case 3:
                ((FortuneActivity) obj).lambda$onCreate$0(view);
                break;
            case 4:
                ((MessagesFragment) obj).lambda$loadSessions$0(view);
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                ((ServiceDetailActivity) obj).lambda$onCreate$0(view);
                break;
            default:
                ((TestListFragment) obj).lambda$onViewCreated$0(view);
                break;
        }
    }
}
