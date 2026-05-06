package com.yizhaiyiju.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FeedbackActivity extends d.s {
    private EditText etContact;
    private EditText etContent;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(Spinner spinner, View view) {
        String trim = this.etContact.getText().toString().trim();
        String trim2 = this.etContent.getText().toString().trim();
        String obj = spinner.getSelectedItem().toString();
        if (TextUtils.isEmpty(trim2)) {
            Toast.makeText(this, "请描述您的反馈内容", Toast.LENGTH_SHORT).show();
        } else {
            submitFeedback(obj, trim, trim2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$submitFeedback$2() {
        Toast.makeText(this, "反馈已提交，感谢您的建议！", Toast.LENGTH_LONG).show();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$submitFeedback$3() {
        Toast.makeText(this, "提交失败，请稍后再试", Toast.LENGTH_SHORT).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$submitFeedback$4(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("feedback_type", str);
            if (str2.isEmpty()) {
                str2 = "未留联系方式";
            }
            jSONObject.put("contact", str2);
            jSONObject.put("content", str3);
            String body = jSONObject.toString();
            Pattern pattern = q3.v.f4179d;
            q3.f0 requestBody = q3.o.f(body, q3.o.r("application/json"));
            q3.c0 c0Var = new q3.c0();
            c0Var.d("https://12zn.com/api/app/feedback");
            c0Var.c("POST", requestBody);
            ApiHelper.getClient().a(c0Var.a()).g();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    FeedbackActivity.this.lambda$submitFeedback$2();
                }
            });
        } catch (Exception unused) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    FeedbackActivity.this.lambda$submitFeedback$3();
                }
            });
        }
    }

    private void submitFeedback(String str, String str2, String str3) {
        new Thread(new e0(this, str, str2, str3)).start();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_feedback);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackActivity.this.lambda$onCreate$0(view);
            }
        });
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_type);
        this.etContact = (EditText) findViewById(R.id.et_contact);
        this.etContent = (EditText) findViewById(R.id.et_content);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, new String[]{"Bug反馈", "服务问题", "功能建议", "其他"});
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackActivity.this.lambda$onCreate$1(spinner, view);
            }
        });
    }
}
