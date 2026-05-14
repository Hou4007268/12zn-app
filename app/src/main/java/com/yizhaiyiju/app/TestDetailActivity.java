package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestDetailActivity extends d.s {

    private TestHistoryHelper.Record record;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test_detail);
        InsetsHelper.applyTopInset(this, R.id.top_bar);

        // Parse record from intent
        String testId = getIntent().getStringExtra("test_id");
        String testName = getIntent().getStringExtra("test_name");
        String resultTitle = getIntent().getStringExtra("result_title");
        String resultDesc = getIntent().getStringExtra("result_desc");
        String resultIcon = getIntent().getStringExtra("result_icon");
        String resultData = getIntent().getStringExtra("result_data");
        long timestamp = getIntent().getLongExtra("timestamp", 0L);

        record = new TestHistoryHelper.Record();
        record.testId = testId;
        record.testName = testName;
        record.resultTitle = resultTitle;
        record.resultDesc = resultDesc;
        record.resultIcon = resultIcon;
        record.resultData = resultData;
        record.timestamp = timestamp;

        // Title bar
        ((TextView) findViewById(R.id.tv_title)).setText(testName != null ? testName : "测试详情");
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        // Delete button
        findViewById(R.id.btn_delete_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestHistoryHelper.deleteRecord(TestDetailActivity.this, record);
                Toast.makeText(TestDetailActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });

        // Icon
        ImageView ivIcon = (ImageView) findViewById(R.id.iv_result_icon);
        if (resultIcon != null && !resultIcon.isEmpty()) {
            try {
                int resId = Integer.parseInt(resultIcon);
                ivIcon.setImageResource(resId);
            } catch (Exception e) {
                ivIcon.setImageResource(R.drawable.ic_test_result_default);
            }
        } else {
            ivIcon.setImageResource(R.drawable.ic_test_result_default);
        }

        // Title
        ((TextView) findViewById(R.id.tv_result_title)).setText(
            resultTitle != null ? resultTitle : "测试完成");

        // Meta (test name + time)
        String timeStr = "";
        if (timestamp > 0) {
            timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
                .format(new Date(timestamp));
        }
        ((TextView) findViewById(R.id.tv_meta)).setText(
            (testName != null ? testName : "") + "  ·  " + timeStr);

        // Description
        ((TextView) findViewById(R.id.tv_result_desc)).setText(
            resultDesc != null ? resultDesc : "");

        // Extra data
        View layoutExtra = findViewById(R.id.layout_extra);
        TextView tvExtraData = (TextView) findViewById(R.id.tv_extra_data);
        if (resultData != null && !resultData.isEmpty()) {
            layoutExtra.setVisibility(View.VISIBLE);
            tvExtraData.setText(resultData);
        } else {
            layoutExtra.setVisibility(View.GONE);
        }

        // Retake button
        findViewById(R.id.btn_retake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Share button
        findViewById(R.id.btn_share_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareText = (resultTitle != null ? resultTitle : "测试结果") + "\n"
                    + (resultDesc != null ? resultDesc : "");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(intent, "分享测试结果"));
            }
        });
    }
}
