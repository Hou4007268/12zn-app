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
public class MyConsultActivity extends d.s {
    private static final String PREFS_KEY = "consult_records";
    private ConsultAdapter adapter;
    private View layoutEmpty;
    private View layoutList;
    private final List<ConsultRecord> records = new ArrayList<>();

    public static class ConsultRecord {
        String type;
        String name;
        String phone;
        String desc;
        long timestamp;
    }

    public class ConsultAdapter extends androidx.recyclerview.widget.g0<ConsultAdapter.VH> {
        public class VH extends i1 {
            TextView tvType;
            TextView tvContact;
            TextView tvDesc;
            TextView tvTime;
            TextView btnDelete;

            public VH(View view) {
                super(view);
                this.tvType = (TextView) view.findViewById(R.id.tv_consult_type);
                this.tvContact = (TextView) view.findViewById(R.id.tv_consult_contact);
                this.tvDesc = (TextView) view.findViewById(R.id.tv_consult_desc);
                this.tvTime = (TextView) view.findViewById(R.id.tv_consult_time);
                this.btnDelete = (TextView) view.findViewById(R.id.btn_delete);
            }
        }

        public ConsultAdapter() {
        }

        @Override
        public int getItemCount() {
            return MyConsultActivity.this.records.size();
        }

        @Override
        public void onBindViewHolder(VH vh, int i4) {
            final ConsultRecord rec = (ConsultRecord) MyConsultActivity.this.records.get(i4);
            vh.tvType.setText(rec.type != null && !rec.type.isEmpty() ? rec.type : "咨询");
            vh.tvContact.setText(rec.name + "  " + rec.phone);
            if (rec.desc != null && !rec.desc.isEmpty()) {
                vh.tvDesc.setText(rec.desc);
                vh.tvDesc.setVisibility(View.VISIBLE);
            } else {
                vh.tvDesc.setVisibility(View.GONE);
            }
            vh.tvTime.setText(MyConsultActivity.this.formatTime(rec.timestamp));
            vh.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyConsultActivity.this.deleteRecord(rec);
                }
            });
        }

        @Override
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_consult_history, viewGroup, false));
        }
    }

    private void deleteRecord(ConsultRecord rec) {
        int index = this.records.indexOf(rec);
        if (index < 0) return;
        this.records.remove(index);
        persistAll();
        this.adapter.notifyItemRemoved(index);
        updateState();
        Toast.makeText(this, "咨询记录已删除", Toast.LENGTH_SHORT).show();
    }

    private String formatTime(long ts) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date(ts));
    }

    private void loadRecords() {
        this.records.clear();
        try {
            SharedPreferences prefs = getSharedPreferences("app_prefs", 0);
            String json = prefs.getString(PREFS_KEY, "[]");
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                ConsultRecord cr = new ConsultRecord();
                cr.type = obj.optString("type", "");
                cr.name = obj.optString("name", "");
                cr.phone = obj.optString("phone", "");
                cr.desc = obj.optString("desc", "");
                cr.timestamp = obj.optLong("timestamp", 0);
                this.records.add(cr);
            }
        } catch (Exception e) {
            // ignore
        }
    }

    private void persistAll() {
        try {
            JSONArray arr = new JSONArray();
            for (ConsultRecord cr : this.records) {
                JSONObject obj = new JSONObject();
                obj.put("type", cr.type);
                obj.put("name", cr.name);
                obj.put("phone", cr.phone);
                obj.put("desc", cr.desc);
                obj.put("timestamp", cr.timestamp);
                arr.put(obj);
            }
            getSharedPreferences("app_prefs", 0).edit().putString(PREFS_KEY, arr.toString()).apply();
        } catch (Exception e) {
            // ignore
        }
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
        persistAll();
        this.adapter.notifyDataSetChanged();
        updateState();
        Toast.makeText(this, "咨询记录已清空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_consults);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyConsultActivity.this.finish();
            }
        });

        ((TextView) findViewById(R.id.tv_title)).setText("我的咨询记录");
        this.layoutEmpty = findViewById(R.id.layout_empty);
        this.layoutList = findViewById(R.id.layout_list);

        ((TextView) findViewById(R.id.tv_empty_icon)).setText("📋");
        ((TextView) findViewById(R.id.tv_empty_text)).setText("暂无咨询记录");
        ((TextView) findViewById(R.id.tv_empty_sub)).setText("预约咨询服务后，记录将显示在这里");
        ((TextView) findViewById(R.id.tv_empty_btn)).setText("去预约咨询");

        findViewById(R.id.btn_empty_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyConsultActivity.this, ConsultActivity.class));
            }
        });

        findViewById(R.id.btn_clear_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyConsultActivity.this.clearAll();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_consult_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ConsultAdapter consultAdapter = new ConsultAdapter();
        this.adapter = consultAdapter;
        recyclerView.setAdapter(consultAdapter);

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
