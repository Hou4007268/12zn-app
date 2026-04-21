package com.yizhaiyiju.app;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import com.yizhaiyiju.app.ArticlesFragment;
import com.yizhaiyiju.app.MessagesFragment;
import com.yizhaiyiju.app.ServicesFragment;
import com.yizhaiyiju.app.TestData;
import com.yizhaiyiju.app.TestListFragment;
import org.json.JSONObject;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class j implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2341e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2342f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f2343g;

    public /* synthetic */ j(Object obj, int i4, Object obj2) {
        this.f2341e = i4;
        this.f2342f = obj;
        this.f2343g = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2341e;
        Object obj = this.f2343g;
        Object obj2 = this.f2342f;
        switch (i4) {
            case 0:
                ((ArticlesFragment.ArticleAdapter) obj2).lambda$onBindViewHolder$0((ArticlesFragment.ArticleItem) obj, view);
                break;
            case 1:
                ((MessagesFragment.SessionAdapter) obj2).lambda$onBindViewHolder$0((JSONObject) obj, view);
                break;
            case 2:
                ((ServicesFragment.ServiceAdapter) obj2).lambda$onBindViewHolder$0((ServicesFragment.ServiceItem) obj, view);
                break;
            case 3:
                ((TestListFragment.TestAdapter) obj2).lambda$onBindViewHolder$0((TestData.TestInfo) obj, view);
                break;
            case 4:
                ((AnnouncementActivity) obj2).lambda$onCreate$0((String) obj, view);
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                ((FeedbackActivity) obj2).lambda$onCreate$1((Spinner) obj, view);
                break;
            case 6:
                ((GenericTestActivity) obj2).lambda$onCreate$2((String) obj, view);
                break;
            case 7:
                ((ProfileFragment) obj2).lambda$onViewCreated$7((View) obj, view);
                break;
            default:
                ((SettingsActivity) obj2).lambda$onCreate$2((TextView) obj, view);
                break;
        }
    }
}
