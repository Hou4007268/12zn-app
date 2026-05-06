package com.yizhaiyiju.app;

import android.view.View;
import android.widget.TextView;
import com.yizhaiyiju.app.TestData;
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

    @Override
    public final void onClick(View view) {
        Object obj = this.f2343g;
        Object obj2 = this.f2342f;
        switch (this.f2341e) {
            case 4:
                ((AnnouncementActivity) obj2).lambda$onCreate$0((String) obj, view);
                return;
            case 6:
                ((GenericTestActivity) obj2).lambda$onCreate$2((String) obj, view);
                return;
            case 7:
                ((View) obj).performClick();
                return;
            default:
                ((SettingsActivity) obj2).lambda$onCreate$2((TextView) obj, view);
                return;
        }
    }
}
