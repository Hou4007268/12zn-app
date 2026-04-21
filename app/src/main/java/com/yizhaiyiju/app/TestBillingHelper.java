package com.yizhaiyiju.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;
import com.yizhaiyiju.app.ApiHelper;
import com.yizhaiyiju.app.TestBillingHelper;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes.dex */
public class TestBillingHelper {
    private static final int FREE_TESTS_PER_MONTH = 3;
    private static final String PREFS = "test_billing";

    /* renamed from: com.yizhaiyiju.app.TestBillingHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements ApiHelper.Callback<ApiHelper.PayResult> {
        final /* synthetic */ BillCallback val$callback;
        final /* synthetic */ Context val$ctx;
        final /* synthetic */ int val$currentCount;
        final /* synthetic */ String val$monthKey;
        final /* synthetic */ SharedPreferences val$prefs;

        public AnonymousClass1(Context context, SharedPreferences sharedPreferences, String str, int i4, BillCallback billCallback) {
            this.val$ctx = context;
            this.val$prefs = sharedPreferences;
            this.val$monthKey = str;
            this.val$currentCount = i4;
            this.val$callback = billCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onSuccess$1(ApiHelper.PayResult payResult, Context context, ImageView imageView) {
            try {
                ((Activity) context).runOnUiThread(new f(imageView, 7, BitmapFactory.decodeStream(new URL("https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + URLEncoder.encode(payResult.qrUrl, "UTF-8")).openStream())));
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onSuccess$2(SharedPreferences sharedPreferences, String str, int i4, BillCallback billCallback, DialogInterface dialogInterface, int i5) {
            sharedPreferences.edit().putInt("count_" + str, i4 + 1).apply();
            billCallback.onAllowed();
        }

        @Override // com.yizhaiyiju.app.ApiHelper.Callback
        public void onError(String str) {
            Toast.makeText(this.val$ctx, "支付接口暂时不可用，本次免费开放", 0).show();
            this.val$prefs.edit().putInt("count_" + this.val$monthKey, this.val$currentCount + 1).apply();
            this.val$callback.onAllowed();
        }

        @Override // com.yizhaiyiju.app.ApiHelper.Callback
        public void onSuccess(ApiHelper.PayResult payResult) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.val$ctx);
            ImageView imageView = new ImageView(this.val$ctx);
            new Thread(new y0(payResult, this.val$ctx, imageView)).start();
            AlertDialog.Builder view = builder.setTitle("扫码支付 9.9 元").setView(imageView);
            final SharedPreferences sharedPreferences = this.val$prefs;
            final String str = this.val$monthKey;
            final int i4 = this.val$currentCount;
            final BillCallback billCallback = this.val$callback;
            view.setPositiveButton("支付完成", new DialogInterface.OnClickListener() { // from class: com.yizhaiyiju.app.z0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i5) {
                    TestBillingHelper.AnonymousClass1.lambda$onSuccess$2(sharedPreferences, str, i4, billCallback, dialogInterface, i5);
                }
            }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
        }
    }

    public interface BillCallback {
        void onAllowed();
    }

    public static void checkAndProceed(Context context, String str, BillCallback billCallback) {
        String memberType = ApiHelper.getMemberType();
        if ("月度会员".equals(memberType) || "年度会员".equals(memberType)) {
            billCallback.onAllowed();
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
        String currentMonthKey = getCurrentMonthKey();
        if (!currentMonthKey.equals(sharedPreferences.getString("last_month", ""))) {
            sharedPreferences.edit().putString("last_month", currentMonthKey).putInt("count_".concat(currentMonthKey), 0).apply();
        }
        int i4 = sharedPreferences.getInt("count_".concat(currentMonthKey), 0);
        if (i4 >= 3) {
            showPaywall(context, str, i4, billCallback);
        } else {
            sharedPreferences.edit().putInt("count_".concat(currentMonthKey), i4 + 1).apply();
            billCallback.onAllowed();
        }
    }

    private static String getCurrentMonthKey() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1) + "-" + String.format(Locale.ROOT, "%02d", Integer.valueOf(calendar.get(2) + 1));
    }

    public static int getRemainingTests(Context context) {
        String memberType = ApiHelper.getMemberType();
        if ("月度会员".equals(memberType) || "年度会员".equals(memberType)) {
            return 999;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
        String currentMonthKey = getCurrentMonthKey();
        if (currentMonthKey.equals(sharedPreferences.getString("last_month", ""))) {
            return Math.max(0, 3 - sharedPreferences.getInt("count_".concat(currentMonthKey), 0));
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showPaywall$1(Context context, DialogInterface dialogInterface, int i4) {
        Intent intent = new Intent(context, (Class<?>) PaymentActivity.class);
        intent.putExtra("product_id", "member_monthly");
        intent.putExtra("product_name", "月度会员");
        intent.putExtra("price", "29.90");
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void paySingleTest(Context context, String str, int i4, String str2, SharedPreferences sharedPreferences, BillCallback billCallback) {
        ApiHelper.createOrder("test_charge", new AnonymousClass1(context, sharedPreferences, str2, i4, billCallback));
    }

    private static void showPaywall(final Context context, final String str, final int i4, final BillCallback billCallback) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
        final String currentMonthKey = getCurrentMonthKey();
        new AlertDialog.Builder(context).setTitle("测试次数已用完").setMessage("本月免费测试3次已用完\n\n选项：\n1. 支付 9.9 元继续本次测试\n2. 购买月度会员 29.9 元，无限次免费测试\n3. 等待下月免费额度刷新").setPositiveButton("支付9.9元", new DialogInterface.OnClickListener() { // from class: com.yizhaiyiju.app.w0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i5) {
                TestBillingHelper.paySingleTest(context, str, i4, currentMonthKey, sharedPreferences, billCallback);
            }
        }).setNeutralButton("买月度会员29.9", new DialogInterface.OnClickListener() { // from class: com.yizhaiyiju.app.x0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i5) {
                TestBillingHelper.lambda$showPaywall$1(context, dialogInterface, i5);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }
}
