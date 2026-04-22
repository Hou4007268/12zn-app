package com.yizhaiyiju.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import com.yizhaiyiju.app.ApiHelper;

/* loaded from: classes.dex */
public class ConsultActivity extends d.s {
    private EditText etDescription;
    private EditText etName;
    private EditText etPhone;
    private Spinner spinnerType;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        submit();
    }

    private void submit() {
        String trim = this.etName.getText().toString().trim();
        String trim2 = this.etPhone.getText().toString().trim();
        String obj = this.spinnerType.getSelectedItem().toString();
        String trim3 = this.etDescription.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(this, "请输入姓名", 0).show();
        } else if (TextUtils.isEmpty(trim2)) {
            Toast.makeText(this, "请输入手机号", 0).show();
        } else {
            ApiHelper.submitConsult(trim, trim2, obj, trim3, new ApiHelper.Callback<String>() { // from class: com.yizhaiyiju.app.ConsultActivity.1
                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onError(String str) {
                    Toast.makeText(ConsultActivity.this, "提交失败，请稍后再试", 0).show();
                }

                @Override // com.yizhaiyiju.app.ApiHelper.Callback
                public void onSuccess(String str) {
                    Toast.makeText(ConsultActivity.this, "预约成功！师傅会尽快联系您", 1).show();
                    ConsultActivity.this.finish();
                }
            });
        }
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_consult);
        final int i4 = 0;
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.s

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ConsultActivity f2375f;

            {
                this.f2375f = ConsultActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                ConsultActivity consultActivity = this.f2375f;
                switch (i5) {
                    case 0:
                        consultActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        consultActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        this.etName = (EditText) findViewById(R.id.et_name);
        this.etPhone = (EditText) findViewById(R.id.et_phone);
        this.etDescription = (EditText) findViewById(R.id.et_description);
        this.spinnerType = (Spinner) findViewById(R.id.spinner_type);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, new String[]{"风水咨询", "八字命理", "家居布局", "起名改名", "择日择吉", "其他"});
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerType.setAdapter((SpinnerAdapter) arrayAdapter);
        final int i5 = 1;
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.s

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ConsultActivity f2375f;

            {
                this.f2375f = ConsultActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                ConsultActivity consultActivity = this.f2375f;
                switch (i52) {
                    case 0:
                        consultActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        consultActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
    }
}
