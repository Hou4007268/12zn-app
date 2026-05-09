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
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyTestsActivity.this, TestDetailActivity.class);
                    intent.putExtra("test_id", testRecord.testId);
                    intent.putExtra("test_name", testRecord.testName);
                    intent.putExtra("result_title", testRecord.resultTitle);
                    intent.putExtra("result_desc", testRecord.resultDesc);
                    intent.putExtra("result_icon", "");
                    intent.putExtra("result_data", "");
                    intent.putExtra("timestamp", testRecord.timestamp);
                    MyTestsActivity.this.startActivity(intent);
                }
            });
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
        TestHistoryHelper.Record r = new TestHistoryHelper.Record();
        r.testId = testRecord.testId;
        r.testName = testRecord.testName;
        r.resultTitle = testRecord.resultTitle;
        r.resultDesc = testRecord.resultDesc;
        r.timestamp = testRecord.timestamp;
        TestHistoryHelper.deleteRecord(this, r);
        this.adapter.notifyItemRemoved(indexOf);
        updateState();
        Toast.makeText(this, "测试记录已删除", Toast.LENGTH_SHORT).show();
    }

    private String formatTime(long j4) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date(j4));
    }

    private void loadRecords() {
        this.records.clear();
        java.util.List<TestHistoryHelper.Record> list = TestHistoryHelper.loadRecords(this);
        for (TestHistoryHelper.Record r : list) {
            TestRecord tr = new TestRecord();
            tr.testId = r.testId;
            tr.testName = r.testName;
            tr.resultTitle = r.resultTitle;
            tr.resultDesc = r.resultDesc;
            tr.timestamp = r.timestamp;
            this.records.add(tr);
        }
    }

    private void persistRecords() {
        // delegated to TestHistoryHelper via deleteRecord/clearAll
    }

    private void updateState() {
        if (this.records.isEmpty()) {
            this.layoutEmpty.setVisibility(View.VISIBLE);
            this.layoutList.setVisibility(View.GONE);
            return;
        }
        this.layoutEmpty.setVisibility(View.GONE);
        this.layoutList.setVisibility(View.VISIBLE);
    }

    private void clearAll() {
        this.records.clear();
        TestHistoryHelper.clearAll(this);
        this.adapter.notifyDataSetChanged();
        updateState();
        Toast.makeText(this, "测试记录已清空", Toast.LENGTH_SHORT).show();
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
                getSharedPreferences("app_prefs", 0).edit().putBoolean("goto_test_tab", true).apply();
                finish();
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

    @Override
    protected void onResume() {
        super.onResume();
        loadRecords();
        updateState();
        if (adapter != null) adapter.notifyDataSetChanged();
    }

}