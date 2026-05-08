package com.yizhaiyiju.app;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestHistoryHelper {
    private static final String PREFS_NAME = "test_history";
    private static final String KEY_RECORDS = "records";
    private static final int MAX_RECORDS = 50;

    public static class Record {
        public String testId;
        public String testName;
        public String resultTitle;
        public String resultDesc;
        public String resultIcon;
        public String resultData;
        public long timestamp;
    }

    public static List<Record> loadRecords(Context ctx) {
        List<Record> list = new ArrayList<>();
        try {
            SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, 0);
            String raw = prefs.getString(KEY_RECORDS, "[]");
            JSONArray arr = new JSONArray(raw);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                if (obj == null) continue;
                Record r = new Record();
                r.testId = obj.optString("test_id", "");
                r.testName = obj.optString("test_name", "测试");
                r.resultTitle = obj.optString("result_title", "测试完成");
                r.resultDesc = obj.optString("result_desc", "");
                r.resultIcon = obj.optString("result_icon", "");
                r.resultData = obj.optString("result_data", "");
                r.timestamp = obj.optLong("timestamp", 0L);
                list.add(r);
            }
        } catch (Exception ignored) {}
        return list;
    }

    public static void saveRecord(Context ctx, Record record) {
        if (record == null) return;
        try {
            List<Record> list = loadRecords(ctx);
            // prepend new record
            list.add(0, record);
            // trim
            while (list.size() > MAX_RECORDS) {
                list.remove(list.size() - 1);
            }
            // serialize
            JSONArray arr = new JSONArray();
            for (Record r : list) {
                JSONObject obj = new JSONObject();
                obj.put("test_id", r.testId != null ? r.testId : "");
                obj.put("test_name", r.testName != null ? r.testName : "测试");
                obj.put("result_title", r.resultTitle != null ? r.resultTitle : "");
                obj.put("result_desc", r.resultDesc != null ? r.resultDesc : "");
                obj.put("result_icon", r.resultIcon != null ? r.resultIcon : "");
                obj.put("result_data", r.resultData != null ? r.resultData : "");
                obj.put("timestamp", r.timestamp);
                arr.put(obj);
            }
            ctx.getSharedPreferences(PREFS_NAME, 0)
                .edit().putString(KEY_RECORDS, arr.toString()).commit();
        } catch (Exception ignored) {}
    }

    public static void deleteRecord(Context ctx, Record record) {
        if (record == null) return;
        try {
            List<Record> list = loadRecords(ctx);
            for (int i = 0; i < list.size(); i++) {
                Record r = list.get(i);
                if (r.timestamp == record.timestamp
                    && eq(r.testId, record.testId)
                    && eq(r.testName, record.testName)) {
                    list.remove(i);
                    break;
                }
            }
            JSONArray arr = new JSONArray();
            for (Record r : list) {
                JSONObject obj = new JSONObject();
                obj.put("test_id", r.testId != null ? r.testId : "");
                obj.put("test_name", r.testName != null ? r.testName : "测试");
                obj.put("result_title", r.resultTitle != null ? r.resultTitle : "");
                obj.put("result_desc", r.resultDesc != null ? r.resultDesc : "");
                obj.put("result_icon", r.resultIcon != null ? r.resultIcon : "");
                obj.put("result_data", r.resultData != null ? r.resultData : "");
                obj.put("timestamp", r.timestamp);
                arr.put(obj);
            }
            ctx.getSharedPreferences(PREFS_NAME, 0)
                .edit().putString(KEY_RECORDS, arr.toString()).commit();
        } catch (Exception ignored) {}
    }

    public static void clearAll(Context ctx) {
        ctx.getSharedPreferences(PREFS_NAME, 0)
            .edit().putString(KEY_RECORDS, "[]").commit();
    }

    private static boolean eq(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }
}
