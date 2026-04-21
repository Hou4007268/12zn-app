package com.yizhaiyiju.app;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.yizhaiyiju.app.ApiHelper;
import com.yizhaiyiju.app.MessagesFragment;
import com.yizhaiyiju.app.RedeemActivity;
import java.io.IOException;
import java.util.List;
import org.json.JSONObject;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2322a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2323b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2324c;

    public /* synthetic */ f(Object obj, int i4, Object obj2) {
        this.f2322a = i4;
        this.f2323b = obj;
        this.f2324c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2322a;
        Object obj = this.f2324c;
        Object obj2 = this.f2323b;
        switch (i4) {
            case 0:
                ((ApiHelper.Callback) obj2).onSuccess((ApiHelper.LoginResult) obj);
                break;
            case 1:
                ((ApiHelper.Callback) obj2).onSuccess((ApiHelper.PayResult) obj);
                break;
            case 2:
                ((ApiHelper.Callback) obj2).onSuccess((List) obj);
                break;
            case 3:
                ((ApiHelper.Callback) obj2).onSuccess((JSONObject) obj);
                break;
            case 4:
                ((MessagesFragment.AnonymousClass1) obj2).lambda$onSuccess$2((JSONObject) obj);
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                ((RedeemActivity.AnonymousClass1) obj2).lambda$onResponse$1((u2.e) obj);
                break;
            case 6:
                ((RedeemActivity.AnonymousClass1) obj2).lambda$onFailure$0((IOException) obj);
                break;
            default:
                ((ImageView) obj2).setImageBitmap((Bitmap) obj);
                break;
        }
    }
}
