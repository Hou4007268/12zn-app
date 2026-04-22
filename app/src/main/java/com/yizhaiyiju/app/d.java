package com.yizhaiyiju.app;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import com.yizhaiyiju.app.ApiHelper;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2309a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ApiHelper.Callback f2310b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Exception f2311c;

    public /* synthetic */ d(ApiHelper.Callback callback, Exception exc, int i4) {
        this.f2309a = i4;
        this.f2310b = callback;
        this.f2311c = exc;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2309a;
        Exception exc = this.f2311c;
        ApiHelper.Callback callback = this.f2310b;
        switch (i4) {
            case 0:
                ApiHelper.AnonymousClass1.lambda$onResponse$3(callback, exc);
                break;
            case 1:
                ApiHelper.AnonymousClass2.lambda$onResponse$3(callback, exc);
                break;
            case 2:
                ApiHelper.AnonymousClass3.lambda$onResponse$3(callback, exc);
                break;
            case 3:
                ApiHelper.AnonymousClass4.lambda$onResponse$3(callback, exc);
                break;
            case 4:
                ApiHelper.AnonymousClass5.lambda$onResponse$5(callback, exc);
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                ApiHelper.AnonymousClass6.lambda$onResponse$3(callback, exc);
                break;
            case 6:
                ApiHelper.AnonymousClass7.lambda$onResponse$2(callback, exc);
                break;
            default:
                ApiHelper.AnonymousClass8.lambda$onResponse$2(callback, exc);
                break;
        }
    }

    public static class s extends androidx.appcompat.app.AppCompatActivity {
    }

    public static class k {
        public View f2485n;
    }

    public static class p {
        private final AlertDialog dialog;

        public p(AlertDialog dialog) {
            this.dialog = dialog;
        }

        public void show() {
            dialog.show();
        }
    }

    public static class o {
        public final k f2561f = new k();
        private final AlertDialog.Builder builder;

        public o(Context context) {
            this.builder = new AlertDialog.Builder(context);
        }

        public p a() {
            if (f2561f.f2485n != null) {
                builder.setView(f2561f.f2485n);
            }
            return new p(builder.create());
        }
    }
}
