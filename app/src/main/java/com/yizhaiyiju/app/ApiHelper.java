package com.yizhaiyiju.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.json.JSONObject;
import s2.r;

/* loaded from: classes.dex */
public class ApiHelper {
    private static final String BASE_URL = "https://12zn.com/api/";
    private static String authToken;
    static final q3.a0 client;
    private static final Handler mainHandler;
    private static String memberType;
    private static String memberExpires;
    private static String userId;
    private static String userPhone;

    /* renamed from: com.yizhaiyiju.app.ApiHelper$1, reason: invalid class name */
    public static class AnonymousClass1 implements q3.e {
        final /* synthetic */ Callback val$callback;

        public AnonymousClass1(Callback callback) {
            this.val$callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Callback callback, IOException iOException) {
            callback.onError(iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$1(Callback callback, u2.e eVar) {
            callback.onSuccess(eVar.k("message") ? eVar.j("message").h() : "验证码已发送");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$3(Callback callback, Exception exc) {
            callback.onError(exc.getMessage());
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            ApiHelper.mainHandler.post(new e(this.val$callback, iOException, 0));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            int i4 = 0;
            try {
                u2.e g4 = r.d1(h0Var.f4104g.C()).g();
                if (g4.k("success") && g4.j("success").f()) {
                    ApiHelper.mainHandler.post(new b(this.val$callback, g4, i4));
                } else {
                    ApiHelper.mainHandler.post(new c(this.val$callback, g4.k("error") ? g4.j("error").h() : "发送失败", i4));
                }
            } catch (Exception e2) {
                ApiHelper.mainHandler.post(new d(this.val$callback, e2, i4));
            }
        }
    }

    /* renamed from: com.yizhaiyiju.app.ApiHelper$2, reason: invalid class name */
    public static class AnonymousClass2 implements q3.e {
        final /* synthetic */ Callback val$callback;

        public AnonymousClass2(Callback callback) {
            this.val$callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Callback callback, IOException iOException) {
            callback.onError(iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$3(Callback callback, Exception exc) {
            callback.onError(exc.getMessage());
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            ApiHelper.mainHandler.post(new e(this.val$callback, iOException, 1));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            int i4 = 1;
            try {
                u2.e g4 = r.d1(h0Var.f4104g.C()).g();
                if (!g4.k("success") || !g4.j("success").f()) {
                    ApiHelper.mainHandler.post(new c(this.val$callback, g4.k("error") ? g4.j("error").h() : "操作失败", i4));
                    return;
                }
                LoginResult loginResult = new LoginResult();
                loginResult.userId = g4.k("user_id") ? g4.j("user_id").h() : "";
                loginResult.token = g4.k("token") ? g4.j("token").h() : "";
                loginResult.phone = g4.k("phone") ? g4.j("phone").h() : "";
                loginResult.memberType = g4.k("member_type") ? g4.j("member_type").h() : "普通用户";
                int i5 = 0;
                loginResult.isNew = g4.k("is_new") && g4.j("is_new").f();
                    loginResult.memberExpires = g4.k("member_expires") ? g4.j("member_expires").h() : null;
                ApiHelper.mainHandler.post(new f(this.val$callback, i5, loginResult));
            } catch (Exception e2) {
                ApiHelper.mainHandler.post(new d(this.val$callback, e2, i4));
            }
        }
    }

    /* renamed from: com.yizhaiyiju.app.ApiHelper$3, reason: invalid class name */
    public static class AnonymousClass3 implements q3.e {
        final /* synthetic */ Callback val$callback;

        public AnonymousClass3(Callback callback) {
            this.val$callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Callback callback, IOException iOException) {
            callback.onError(iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onResponse$1(Callback callback, q3.h0 h0Var) {
            callback.onError("请求失败: " + h0Var.f4101d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$3(Callback callback, Exception exc) {
            callback.onError(exc.getMessage());
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            ApiHelper.mainHandler.post(new e(this.val$callback, iOException, 2));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            String str;
            u2.b j4;
            int i4 = 2;
            try {
                if (!h0Var.C()) {
                    ApiHelper.mainHandler.post(new g(this.val$callback, h0Var, 0));
                    return;
                }
                u2.e g4 = r.d1(h0Var.f4104g.C()).g();
                if (g4.k("answer")) {
                    j4 = g4.j("answer");
                } else {
                    if (!g4.k("reply")) {
                        str = "抱歉，暂时无法回答";
                        ApiHelper.mainHandler.post(new c(this.val$callback, str, i4));
                    }
                    j4 = g4.j("reply");
                }
                str = j4.h();
                ApiHelper.mainHandler.post(new c(this.val$callback, str, i4));
            } catch (Exception e2) {
                ApiHelper.mainHandler.post(new d(this.val$callback, e2, i4));
            }
        }
    }

    /* renamed from: com.yizhaiyiju.app.ApiHelper$4, reason: invalid class name */
    public static class AnonymousClass4 implements q3.e {
        final /* synthetic */ Callback val$callback;

        public AnonymousClass4(Callback callback) {
            this.val$callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Callback callback, IOException iOException) {
            callback.onError(iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onResponse$1(Callback callback, q3.h0 h0Var) {
            callback.onError("请求失败: " + h0Var.f4101d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$3(Callback callback, Exception exc) {
            callback.onError(exc.getMessage());
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            ApiHelper.mainHandler.post(new e(this.val$callback, iOException, 3));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            int i4 = 3;
            try {
                if (!h0Var.C()) {
                    ApiHelper.mainHandler.post(new g(this.val$callback, h0Var, 1));
                } else {
                    u2.e g4 = r.d1(h0Var.f4104g.C()).g();
                    ApiHelper.mainHandler.post(new c(this.val$callback, g4.k("message") ? g4.j("message").h() : "提交成功", i4));
                }
            } catch (Exception e2) {
                ApiHelper.mainHandler.post(new d(this.val$callback, e2, i4));
            }
        }
    }

    /* renamed from: com.yizhaiyiju.app.ApiHelper$5, reason: invalid class name */
    public static class AnonymousClass5 implements q3.e {
        final /* synthetic */ Callback val$callback;

        public AnonymousClass5(Callback callback) {
            this.val$callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Callback callback, IOException iOException) {
            callback.onError(iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onResponse$1(Callback callback, q3.h0 h0Var) {
            callback.onError("请求失败: " + h0Var.f4101d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$2(Callback callback, u2.e eVar) {
            callback.onError(eVar.j("error").h());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$5(Callback callback, Exception exc) {
            callback.onError("创建订单失败: " + exc.getMessage());
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            ApiHelper.mainHandler.post(new e(this.val$callback, iOException, 4));
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0081 A[Catch: Exception -> 0x00c8, TryCatch #0 {Exception -> 0x00c8, blocks: (B:3:0x000a, B:5:0x0010, B:8:0x0020, B:10:0x0037, B:12:0x0046, B:15:0x0053, B:16:0x005d, B:18:0x0065, B:19:0x0069, B:20:0x0079, B:22:0x0081, B:23:0x008c, B:25:0x0094, B:26:0x009f, B:28:0x00a9, B:30:0x00b9, B:34:0x006e, B:36:0x0074), top: B:2:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0094 A[Catch: Exception -> 0x00c8, TryCatch #0 {Exception -> 0x00c8, blocks: (B:3:0x000a, B:5:0x0010, B:8:0x0020, B:10:0x0037, B:12:0x0046, B:15:0x0053, B:16:0x005d, B:18:0x0065, B:19:0x0069, B:20:0x0079, B:22:0x0081, B:23:0x008c, B:25:0x0094, B:26:0x009f, B:28:0x00a9, B:30:0x00b9, B:34:0x006e, B:36:0x0074), top: B:2:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00a9 A[Catch: Exception -> 0x00c8, TryCatch #0 {Exception -> 0x00c8, blocks: (B:3:0x000a, B:5:0x0010, B:8:0x0020, B:10:0x0037, B:12:0x0046, B:15:0x0053, B:16:0x005d, B:18:0x0065, B:19:0x0069, B:20:0x0079, B:22:0x0081, B:23:0x008c, B:25:0x0094, B:26:0x009f, B:28:0x00a9, B:30:0x00b9, B:34:0x006e, B:36:0x0074), top: B:2:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00b9 A[Catch: Exception -> 0x00c8, TRY_LEAVE, TryCatch #0 {Exception -> 0x00c8, blocks: (B:3:0x000a, B:5:0x0010, B:8:0x0020, B:10:0x0037, B:12:0x0046, B:15:0x0053, B:16:0x005d, B:18:0x0065, B:19:0x0069, B:20:0x0079, B:22:0x0081, B:23:0x008c, B:25:0x0094, B:26:0x009f, B:28:0x00a9, B:30:0x00b9, B:34:0x006e, B:36:0x0074), top: B:2:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x008a  */
        @Override // q3.e
        public void onResponse(q3.d dVar, final q3.h0 h0Var) {
            try {
                if (!h0Var.C()) {
                    ApiHelper.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            AnonymousClass5.this.val$callback.onError("请求失败: " + h0Var.f4101d);
                        }
                    });
                    return;
                }

                final u2.e g4 = r.d1(h0Var.f4104g.C()).g();
                if (g4.k("error")) {
                    ApiHelper.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            AnonymousClass5.this.val$callback.onError(g4.j("error").h());
                        }
                    });
                    return;
                }

                final PayResult payResult = new PayResult();
                payResult.orderNo = g4.k("order_no") ? g4.j("order_no").h() : "";
                if (g4.k("qr_code") && !g4.j("qr_code").h().isEmpty()) {
                    payResult.qrUrl = g4.j("qr_code").h();
                } else if (g4.k("qr_url") && !g4.j("qr_url").h().isEmpty()) {
                    payResult.qrUrl = g4.j("qr_url").h();
                } else {
                    payResult.qrUrl = "";
                }
                payResult.amount = g4.k("amount") ? g4.j("amount").h() : "0";
                payResult.subject = g4.k("subject") ? g4.j("subject").h() : "咨询服务";

                if (payResult.orderNo.isEmpty()) {
                    ApiHelper.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            AnonymousClass5.this.val$callback.onError("订单号为空");
                        }
                    });
                    return;
                }

                ApiHelper.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        AnonymousClass5.this.val$callback.onSuccess(payResult);
                    }
                });
            } catch (final Exception e2) {
                ApiHelper.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        AnonymousClass5.this.val$callback.onError("创建订单失败: " + e2.getMessage());
                    }
                });
            }
        }

    }

    /* renamed from: com.yizhaiyiju.app.ApiHelper$6, reason: invalid class name */
    public static class AnonymousClass6 implements q3.e {
        final /* synthetic */ Callback val$callback;

        public AnonymousClass6(Callback callback) {
            this.val$callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Callback callback, IOException iOException) {
            callback.onError(iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onResponse$1(Callback callback, q3.h0 h0Var) {
            callback.onError("请求失败: " + h0Var.f4101d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$3(Callback callback, Exception exc) {
            callback.onError(exc.getMessage());
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            ApiHelper.mainHandler.post(new e(this.val$callback, iOException, 5));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            try {
                if (!h0Var.C()) {
                    ApiHelper.mainHandler.post(new g(this.val$callback, h0Var, 3));
                } else {
                    u2.e g4 = r.d1(h0Var.f4104g.C()).g();
                    ApiHelper.mainHandler.post(new c(this.val$callback, g4.k("status") ? g4.j("status").h() : "unknown", 4));
                }
            } catch (Exception e2) {
                ApiHelper.mainHandler.post(new d(this.val$callback, e2, 5));
            }
        }
    }

    /* renamed from: com.yizhaiyiju.app.ApiHelper$7, reason: invalid class name */
    public static class AnonymousClass7 implements q3.e {
        final /* synthetic */ Callback val$callback;

        public AnonymousClass7(Callback callback) {
            this.val$callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Callback callback, IOException iOException) {
            callback.onError(iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$2(Callback callback, Exception exc) {
            callback.onError(exc.getMessage());
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            ApiHelper.mainHandler.post(new e(this.val$callback, iOException, 6));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            try {
                u2.a aVar = (u2.a) r.d1(h0Var.f4104g.C()).g().f4744a.get("data");
                ArrayList arrayList = new ArrayList();
                for (int i4 = 0; i4 < aVar.f4742a.size(); i4++) {
                    u2.e g4 = ((u2.b) aVar.f4742a.get(i4)).g();
                    Article article = new Article();
                    String str = "";
                    article.title = g4.k("title") ? g4.j("title").h() : "";
                    article.summary = g4.k("summary") ? g4.j("summary").h() : "";
                    article.date = g4.k("date") ? g4.j("date").h() : "";
                    if (g4.k("url")) {
                        str = g4.j("url").h();
                    }
                    article.url = str;
                    arrayList.add(article);
                }
                ApiHelper.mainHandler.post(new f(this.val$callback, 2, arrayList));
            } catch (Exception e2) {
                ApiHelper.mainHandler.post(new d(this.val$callback, e2, 6));
            }
        }
    }

    /* renamed from: com.yizhaiyiju.app.ApiHelper$8, reason: invalid class name */
    public static class AnonymousClass8 implements q3.e {
        final /* synthetic */ Callback val$cb;

        public AnonymousClass8(Callback callback) {
            this.val$cb = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Callback callback, IOException iOException) {
            callback.onError(iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$2(Callback callback, Exception exc) {
            callback.onError(exc.getMessage());
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            ApiHelper.mainHandler.post(new e(this.val$cb, iOException, 7));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            try {
                ApiHelper.mainHandler.post(new f(this.val$cb, 3, new JSONObject(h0Var.f4104g.C())));
            } catch (Exception e2) {
                ApiHelper.mainHandler.post(new d(this.val$cb, e2, 7));
            }
        }
    }

    public static class Article {
        public String date;
        public String summary;
        public String title;
        public String url;
    }

    public interface Callback<T> {
        void onError(String str);

        void onSuccess(T t4);
    }

    public static class LoginResult {
        public boolean isNew;
        public String memberExpires;
        public String memberType;
        public String phone;
        public String token;
        public String userId;
    }

    public static class PayResult {
        public String amount;
        public String orderNo;
        public String qrUrl;
        public String subject;
    }

    static {
        q3.z zVar = new q3.z();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        r.r(timeUnit, "unit");
        zVar.f4205r = r3.b.b(15L, timeUnit);
        zVar.f4206s = r3.b.b(30L, timeUnit);
        client = new q3.a0(zVar);
        mainHandler = new Handler(Looper.getMainLooper());
        authToken = null;
        userId = null;
        userPhone = null;
        memberType = null;
        memberExpires = null;
    }

    public static void apiGet(String str, Callback callback) {
        q3.c0 authRequest = authRequest();
        authRequest.d(BASE_URL + str);
        client.a(authRequest.a()).f(new AnonymousClass8(callback));
    }

    public static q3.c0 authRequest() {
        q3.c0 c0Var = new q3.c0();
        if (authToken != null) {
            c0Var.b("Authorization", "Bearer " + authToken);
        }
        return c0Var;
    }

    public static void chat(String str, String str2, Callback<String> callback) {
        u2.e eVar = new u2.e();
        eVar.i("question", str);
        String bVar = eVar.toString();
        Pattern pattern = q3.v.f4179d;
        q3.f0 f4 = q3.o.f(bVar, q3.o.r("application/json"));
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/chat");
        authRequest.c("POST", f4);
        client.a(authRequest.a()).f(new AnonymousClass3(callback));
    }

    public static void checkPayStatus(String str, Callback<String> callback) {
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/pay/status?order_no=" + str);
        authRequest.c("GET", null);
        client.a(authRequest.a()).f(new AnonymousClass6(callback));
    }

    public static void clearAuth(Context context) {
        authToken = null;
        userId = null;
        userPhone = null;
        memberType = null;
        memberExpires = null;
        getSecurePrefs(context).edit().remove("token").remove("user_id").remove("phone").remove("member_type").remove("member_expires").apply();
    }

    public static void createOrder(String str, Callback<PayResult> callback) {
        u2.e eVar = new u2.e();
        eVar.i("product_id", str);
        eVar.i("source_page", "app");
        String str2 = userId;
        if (str2 != null) {
            eVar.i("user_id", str2);
        }
        String bVar = eVar.toString();
        Pattern pattern = q3.v.f4179d;
        q3.f0 f4 = q3.o.f(bVar, q3.o.r("application/json"));
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/pay");
        authRequest.c("POST", f4);
        client.a(authRequest.a()).f(new AnonymousClass5(callback));
    }

    private static void doLoginRequest(q3.d0 d0Var, Callback<LoginResult> callback) {
        client.a(d0Var).f(new AnonymousClass2(callback));
    }

    public static void getArticles(Callback<List<Article>> callback) {
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/app/articles");
        authRequest.c("GET", null);
        client.a(authRequest.a()).f(new AnonymousClass7(callback));
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static q3.a0 getClient() {
        return client;
    }

    public static String getMemberExpires() {
        return memberExpires;
    }

    public static void setMemberExpires(String expires) {
        memberExpires = expires;
    }

    public static String getMemberType() {
        return memberType;
    }

    private static SharedPreferences getSecurePrefs(Context context) {
        try {
            return z0.b.a(z0.c.a(z0.c.f5311a), context);
        } catch (Exception unused) {
            return context.getSharedPreferences("user", 0);
        }
    }

    public static String getUserId() {
        return userId;
    }

    public static String getUserPhone() {
        return userPhone;
    }

    public static void initFromPrefs(Context context) {
        SharedPreferences securePrefs = getSecurePrefs(context);
        authToken = securePrefs.getString("token", null);
        userId = securePrefs.getString("user_id", null);
        userPhone = securePrefs.getString("phone", null);
        memberType = securePrefs.getString("member_type", null);
        memberExpires = securePrefs.getString("member_expires", null);
    }

    public static boolean isLoggedIn() {
        String str = authToken;
        return (str == null || str.isEmpty()) ? false : true;
    }

    public static void loginWithPassword(String str, String str2, Callback<LoginResult> callback) {
        u2.e eVar = new u2.e();
        eVar.i("phone", str);
        eVar.i("password", str2);
        String bVar = eVar.toString();
        Pattern pattern = q3.v.f4179d;
        q3.f0 f4 = q3.o.f(bVar, q3.o.r("application/json"));
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/auth/login");
        authRequest.c("POST", f4);
        doLoginRequest(authRequest.a(), callback);
    }

    public static void register(String str, String str2, String str3, String str4, Callback<LoginResult> callback) {
        u2.e eVar = new u2.e();
        eVar.i("phone", str);
        eVar.i("code", str2);
        eVar.i("password", str3);
        eVar.i("device_id", str4);
        String bVar = eVar.toString();
        Pattern pattern = q3.v.f4179d;
        q3.f0 f4 = q3.o.f(bVar, q3.o.r("application/json"));
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/auth/register");
        authRequest.c("POST", f4);
        doLoginRequest(authRequest.a(), callback);
    }

    public static void resetPassword(String str, String str2, String str3, Callback<LoginResult> callback) {
        u2.e eVar = new u2.e();
        eVar.i("phone", str);
        eVar.i("code", str2);
        eVar.i("password", str3);
        String bVar = eVar.toString();
        Pattern pattern = q3.v.f4179d;
        q3.f0 f4 = q3.o.f(bVar, q3.o.r("application/json"));
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/auth/reset-password");
        authRequest.c("POST", f4);
        doLoginRequest(authRequest.a(), callback);
    }

    public static void saveAuth(Context context, String str, String str2, String str3, String str4) {
        saveAuth(context, str, str2, str3, str4, null);
    }

    public static void saveAuth(Context context, String str, String str2, String str3, String str4, String memberExp) {
        authToken = str;
        userId = str2;
        userPhone = str3;
        memberType = str4;
        memberExpires = memberExp;
        SharedPreferences.Editor editor = getSecurePrefs(context).edit().putString("token", str).putString("user_id", str2).putString("phone", str3).putString("member_type", str4);
        if (memberExp != null) editor.putString("member_expires", memberExp);
        editor.apply();
    }

    public static void sendCode(String str, String str2, String str3, Callback<String> callback) {
        u2.e eVar = new u2.e();
        eVar.i("phone", str);
        if (str2 != null && !str2.isEmpty()) {
            eVar.i("email", str2);
        }
        if (str3 != null) {
            eVar.i("purpose", str3);
        }
        String bVar = eVar.toString();
        Pattern pattern = q3.v.f4179d;
        q3.f0 f4 = q3.o.f(bVar, q3.o.r("application/json"));
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/auth/send-code");
        authRequest.c("POST", f4);
        client.a(authRequest.a()).f(new AnonymousClass1(callback));
    }

    public static void submitConsult(String str, String str2, String str3, String str4, Callback<String> callback) {
        u2.e eVar = new u2.e();
        eVar.i("name", str);
        eVar.i("phone", str2);
        eVar.i("type", str3);
        eVar.i("description", str4);
        String str5 = userId;
        if (str5 != null) {
            eVar.i("user_id", str5);
        }
        String bVar = eVar.toString();
        Pattern pattern = q3.v.f4179d;
        q3.f0 f4 = q3.o.f(bVar, q3.o.r("application/json"));
        q3.c0 authRequest = authRequest();
        authRequest.d("https://12zn.com/api/app/consult");
        authRequest.c("POST", f4);
        client.a(authRequest.a()).f(new AnonymousClass4(callback));
    }
}
