package com.yizhaiyiju.app;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.yizhaiyiju.app.ApiHelper;

/* loaded from: classes.dex */
public class LoginActivity extends d.s {
    private Button btnLogin;
    private Button btnSendCode;
    private CountDownTimer countDownTimer;
    private EditText etCode;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPhone;
    private boolean isRegisterMode = false;
    private LinearLayout layoutCode;
    private LinearLayout layoutEmail;
    private TextView tvForgot;
    private TextView tvPasswordHint;
    private TextView tvSwitchMode;
    private TextView tvTitle;

    private void doLogin() {
        String trim = this.etPhone.getText().toString().trim();
        String trim2 = this.etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || trim.length() != 11) {
            Toast.makeText(this, "请输入正确的手机号", 0).show();
            return;
        }
        if (TextUtils.isEmpty(trim2)) {
            Toast.makeText(this, "请输入密码", 0).show();
            return;
        }
        this.btnLogin.setEnabled(false);
        if (!this.isRegisterMode) {
            ApiHelper.loginWithPassword(trim, trim2, new ApiHelper.Callback<ApiHelper.LoginResult>() { // from class: com.yizhaiyiju.app.LoginActivity.4
                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onError(String str) {
                    Toast.makeText(LoginActivity.this, str, 0).show();
                    LoginActivity.this.btnLogin.setEnabled(true);
                }

                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onSuccess(ApiHelper.LoginResult loginResult) {
                    ApiHelper.saveAuth(LoginActivity.this, loginResult.token, loginResult.userId, loginResult.phone, loginResult.memberType);
                    Toast.makeText(LoginActivity.this, "登录成功！", 0).show();
                    LoginActivity.this.finish();
                }
            });
            return;
        }
        if (!validateStrongPassword(trim2)) {
            this.btnLogin.setEnabled(true);
            return;
        }
        String trim3 = this.etCode.getText().toString().trim();
        if (!TextUtils.isEmpty(trim3)) {
            ApiHelper.register(trim, trim3, trim2, Settings.Secure.getString(getContentResolver(), "android_id"), new ApiHelper.Callback<ApiHelper.LoginResult>() { // from class: com.yizhaiyiju.app.LoginActivity.3
                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onError(String str) {
                    Toast.makeText(LoginActivity.this, str, 0).show();
                    LoginActivity.this.btnLogin.setEnabled(true);
                }

                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onSuccess(ApiHelper.LoginResult loginResult) {
                    ApiHelper.saveAuth(LoginActivity.this, loginResult.token, loginResult.userId, loginResult.phone, loginResult.memberType);
                    String trim4 = LoginActivity.this.etEmail.getText().toString().trim();
                    if (!trim4.isEmpty()) {
                        LoginActivity.this.getSharedPreferences("user", 0).edit().putString("email", trim4).apply();
                    }
                    Toast.makeText(LoginActivity.this, "注册成功！", 0).show();
                    LoginActivity.this.finish();
                }
            });
        } else {
            Toast.makeText(this, "请输入验证码", 0).show();
            this.btnLogin.setEnabled(true);
        }
    }

    private void doReset() {
        String trim = this.etPhone.getText().toString().trim();
        String trim2 = this.etCode.getText().toString().trim();
        String trim3 = this.etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || trim.length() != 11) {
            Toast.makeText(this, "请输入正确的手机号", 0).show();
            return;
        }
        if (TextUtils.isEmpty(trim2)) {
            Toast.makeText(this, "请输入验证码", 0).show();
            return;
        }
        if (TextUtils.isEmpty(trim3)) {
            Toast.makeText(this, "请输入新密码", 0).show();
        } else if (validateStrongPassword(trim3)) {
            this.btnLogin.setEnabled(false);
            ApiHelper.resetPassword(trim, trim2, trim3, new ApiHelper.Callback<ApiHelper.LoginResult>() { // from class: com.yizhaiyiju.app.LoginActivity.5
                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onError(String str) {
                    Toast.makeText(LoginActivity.this, str, 0).show();
                    LoginActivity.this.btnLogin.setEnabled(true);
                }

                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onSuccess(ApiHelper.LoginResult loginResult) {
                    ApiHelper.saveAuth(LoginActivity.this, loginResult.token, loginResult.userId, loginResult.phone, loginResult.memberType);
                    String trim4 = LoginActivity.this.etEmail.getText().toString().trim();
                    if (!trim4.isEmpty()) {
                        LoginActivity.this.getSharedPreferences("user", 0).edit().putString("email", trim4).apply();
                    }
                    Toast.makeText(LoginActivity.this, "密码重置成功！", 0).show();
                    LoginActivity.this.finish();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        sendCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(View view) {
        doLogin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(View view) {
        switchMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$4(View view) {
        resetPassword();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resetPassword$6(View view) {
        doReset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$switchMode$5(View view) {
        doLogin();
    }

    private void resetPassword() {
        this.isRegisterMode = true;
        this.tvTitle.setText("找回密码");
        this.layoutCode.setVisibility(0);
        this.layoutEmail.setVisibility(0);
        this.tvPasswordHint.setVisibility(0);
        this.etPassword.setHint("设置新密码");
        this.btnLogin.setText("重置密码");
        this.tvSwitchMode.setText("返回登录");
        this.tvForgot.setVisibility(8);
        this.btnLogin.setOnClickListener(new k0(this, 0));
    }

    private void sendCode() {
        String trim = this.etPhone.getText().toString().trim();
        String trim2 = this.etEmail.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || trim.length() != 11) {
            Toast.makeText(this, "请输入正确的手机号", 0).show();
        } else if (TextUtils.isEmpty(trim2) || !trim2.contains("@")) {
            Toast.makeText(this, "请输入邮箱地址", 0).show();
        } else {
            this.btnSendCode.setEnabled(false);
            ApiHelper.sendCode(trim, trim2, this.isRegisterMode ? "register" : "reset", new ApiHelper.Callback<String>() { // from class: com.yizhaiyiju.app.LoginActivity.1
                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onError(String str) {
                    Toast.makeText(LoginActivity.this, str, 0).show();
                    LoginActivity.this.btnSendCode.setEnabled(true);
                }

                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onSuccess(String str) {
                    Toast.makeText(LoginActivity.this, str, 0).show();
                    LoginActivity.this.startCountDown();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCountDown() {
        this.countDownTimer = new CountDownTimer(60000L, 1000L) { // from class: com.yizhaiyiju.app.LoginActivity.2
            @Override // android.os.CountDownTimer
            public void onFinish() {
                LoginActivity.this.btnSendCode.setEnabled(true);
                LoginActivity.this.btnSendCode.setText("发送验证码");
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j4) {
                LoginActivity.this.btnSendCode.setText((j4 / 1000) + "秒");
            }
        }.start();
    }

    private void switchMode() {
        this.isRegisterMode = !this.isRegisterMode;
        this.btnLogin.setOnClickListener(new k0(this, 1));
        updateUI();
    }

    private void updateUI() {
        if (this.isRegisterMode) {
            this.tvTitle.setText("注册账号");
            this.layoutCode.setVisibility(0);
            this.layoutEmail.setVisibility(0);
            this.tvPasswordHint.setVisibility(0);
            this.etPassword.setHint("设置密码");
            this.btnLogin.setText("注册");
            this.tvSwitchMode.setText("已有账号？去登录");
            this.tvForgot.setVisibility(8);
            return;
        }
        this.tvTitle.setText("登录");
        this.layoutCode.setVisibility(8);
        this.layoutEmail.setVisibility(8);
        this.tvPasswordHint.setVisibility(8);
        this.etPassword.setHint("输入密码");
        this.btnLogin.setText("登录");
        this.tvSwitchMode.setText("没有账号？去注册");
        this.tvForgot.setVisibility(0);
    }

    private boolean validateStrongPassword(String str) {
        String str2;
        if (str.length() < 8) {
            str2 = "密码至少8位";
        } else {
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            for (char c5 : str.toCharArray()) {
                if (Character.isLetter(c5)) {
                    z4 = true;
                } else if (Character.isDigit(c5)) {
                    z5 = true;
                } else {
                    z6 = true;
                }
            }
            if (z4 && z5 && z6) {
                return true;
            }
            str2 = "密码需包含字母+数字+特殊符号";
        }
        Toast.makeText(this, str2, 0).show();
        return false;
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_back).setOnClickListener(new k0(this, 2));
        this.etPhone = (EditText) findViewById(R.id.et_phone);
        this.etEmail = (EditText) findViewById(R.id.et_email);
        this.etCode = (EditText) findViewById(R.id.et_code);
        this.etPassword = (EditText) findViewById(R.id.et_password);
        this.btnSendCode = (Button) findViewById(R.id.btn_send_code);
        this.btnLogin = (Button) findViewById(R.id.btn_login);
        this.tvSwitchMode = (TextView) findViewById(R.id.tv_switch_mode);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvForgot = (TextView) findViewById(R.id.tv_forgot);
        this.tvPasswordHint = (TextView) findViewById(R.id.tv_password_hint);
        this.layoutCode = (LinearLayout) findViewById(R.id.layout_code);
        this.layoutEmail = (LinearLayout) findViewById(R.id.layout_email);
        this.btnSendCode.setOnClickListener(new k0(this, 3));
        this.btnLogin.setOnClickListener(new k0(this, 4));
        this.tvSwitchMode.setOnClickListener(new k0(this, 5));
        this.tvForgot.setOnClickListener(new k0(this, 6));
        if ("reset".equals(getIntent().getStringExtra("mode"))) {
            resetPassword();
        }
        updateUI();
    }

    @Override // d.s, androidx.fragment.app.a0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
