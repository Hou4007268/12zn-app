package com.yizhaiyiju.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i1;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MyTestsActivity extends d.s {
    private static final String PREFS_TEST_HISTORY = "test_history";
    private static final String KEY_TEST_HISTORY = "records";
    private HistoryAdapter adapter;
    private View layoutEmpty;
    private View layoutList;
    private final List<TestRecord> records = new ArrayList();

    public static class TestRecord {
        String resultDesc;
        String resultTitle;
        String testId;
        String testName;
        long timestamp;
    }

    public class HistoryAdapter extends androidx.recyclerview.widget.g0<HistoryAdapter.VH> {
        public class VH extends i1 {
            TextView btnDelete;
            TextView tvDesc;
            TextView tvName;
            TextView tvTime;
            TextView tvTitle;

            public VH(View view) {
                super(view);
                this.tvName = (TextView) view.findViewById(R.id.tv_test_name);
                this.tvTitle = (TextView) view.findViewById(R.id.tv_test_title);
                this.tvDesc = (TextView) view.findViewById(R.id.tv_test_desc);
                this.tvTime = (TextView) view.findViewById(R.id.tv_test_time);
                this.btnDelete = (TextView) view.findViewById(R.id.btn_delete);
            }
        }

        public HistoryAdapter() {
        }

        @Override
        public int getItemCount() {
            return MyTestsActivity.this.records.size();
        }

        @Override
        public void onBindViewHolder(VH vh, int i4) {
            final TestRecord testRecord = (TestRecord) MyTestsActivity.this.records.get(i4);
            vh.tvName.setText(testRecord.testName);
            vh.tvTitle.setText(testRecord.resultTitle);
            vh.tvDesc.setText(testRecord.resultDesc);
            vh.tvTime.setText(MyTestsActivity.this.formatTime(testRecord.timestamp));
            vh.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyTestsActivity.this.deleteRecord(testRecord);
                }
            });
        }

        @Override
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test_history, viewGroup, false));
        }
    }

    private void deleteRecord(TestRecord testRecord) {
        int indexOf = this.records.indexOf(testRecord);
        if (indexOf < 0) {
            return;
        }
        this.records.remove(indexOf);
        persistRecords();
        this.adapter.notifyItemRemoved(indexOf);
        updateState();
        Toast.makeText(this, "测试记录已删除", 0).show();
    }

    private String formatTime(long j4) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date(j4));
    }

    private void loadRecords() {
        this.records.clear();
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_TEST_HISTORY, 0);
            JSONArray jSONArray = new JSONArray(sharedPreferences.getString(KEY_TEST_HISTORY, "[]"));
            for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i4);
                if (optJSONObject != null) {
                    TestRecord testRecord = new TestRecord();
                    testRecord.testId = optJSONObject.optString("test_id", "");
                    testRecord.testName = optJSONObject.optString("test_name", "测试");
                    testRecord.resultTitle = optJSONObject.optString("result_title", "测试完成");
                    testRecord.resultDesc = optJSONObject.optString("result_desc", "");
                    testRecord.timestamp = optJSONObject.optLong("timestamp", 0L);
                    this.records.add(testRecord);
                }
            }
        } catch (Exception unused) {
        }
    }

    private void persistRecords() {
        JSONArray jSONArray = new JSONArray();
        try {
            for (int i4 = 0; i4 < this.records.size(); i4++) {
                TestRecord testRecord = (TestRecord) this.records.get(i4);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("test_id", testRecord.testId);
                jSONObject.put("test_name", testRecord.testName);
                jSONObject.put("result_title", testRecord.resultTitle);
                jSONObject.put("result_desc", testRecord.resultDesc);
                jSONObject.put("timestamp", testRecord.timestamp);
                jSONArray.put(jSONObject);
            }
        } catch (Exception unused) {
        }
        getSharedPreferences(PREFS_TEST_HISTORY, 0).edit().putString(KEY_TEST_HISTORY, jSONArray.toString()).apply();
    }

    private void updateState() {
        if (this.records.isEmpty()) {
            this.layoutEmpty.setVisibility(0);
            this.layoutList.setVisibility(8);
            return;
        }
        this.layoutEmpty.setVisibility(8);
        this.layoutList.setVisibility(0);
    }

    private void clearAll() {
        this.records.clear();
        persistRecords();
        this.adapter.notifyDataSetChanged();
        updateState();
        Toast.makeText(this, "测试记录已清空", 0).show();
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_tests);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTestsActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText("我的测试记录");
        this.layoutEmpty = findViewById(R.id.layout_empty);
        this.layoutList = findViewById(R.id.layout_list);
        ((TextView) findViewById(R.id.tv_empty_btn)).setText("去测试");
        findViewById(R.id.btn_empty_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTestsActivity.this.finish();
            }
        });
        findViewById(R.id.btn_clear_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTestsActivity.this.clearAll();
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_test_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HistoryAdapter historyAdapter = new HistoryAdapter();
        this.adapter = historyAdapter;
        recyclerView.setAdapter(historyAdapter);
        loadRecords();
        updateState();
    }
}
