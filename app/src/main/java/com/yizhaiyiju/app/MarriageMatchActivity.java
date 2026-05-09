package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.Calendar;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MarriageMatchActivity extends d.s {
    private LinearLayout resultContainer;
    private Spinner spFemaleDay;
    private Spinner spFemaleHour;
    private Spinner spFemaleMonth;
    private Spinner spFemaleYear;
    private Spinner spMaleDay;
    private Spinner spMaleHour;
    private Spinner spMaleMonth;
    private Spinner spMaleYear;
    static final String[] STEEMS = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    static final String[] BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    static final String[] ANIMALS = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    static final int[] STEM_ELEM = {1, 1, 3, 3, 4, 4, 0, 0, 2, 2};
    static final String[] ELEM_NAMES = {"金", "木", "水", "火", "土"};
    static final int[][] LIUHE = {new int[]{0, 1}, new int[]{2, 11}, new int[]{3, 10}, new int[]{4, 9}, new int[]{5, 8}, new int[]{6, 7}};
    static final int[][] LIUCHONG = {new int[]{0, 6}, new int[]{1, 7}, new int[]{2, 8}, new int[]{3, 9}, new int[]{4, 10}, new int[]{5, 11}};
    static final int[][] SANHE = {new int[]{8, 0, 4}, new int[]{2, 6, 10}, new int[]{11, 3, 7}, new int[]{5, 9, 1}};

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculate, reason: merged with bridge method [inline-methods] */
    

    private void saveTestResult(String testName, String resultTitle, String resultDesc) {
        try {
            TestHistoryHelper.Record r = new TestHistoryHelper.Record();
            r.testId = "";
            r.testName = testName != null ? testName : "测试";
            r.resultTitle = resultTitle != null ? resultTitle : "";
            r.resultDesc = resultDesc != null ? resultDesc : "";
            r.resultIcon = "";
            r.resultData = "";
            r.timestamp = System.currentTimeMillis();
            TestHistoryHelper.saveRecord(this, r);
        } catch (Exception ignored) {}
    }

    public void lambda$onCreate$0() {
        int maleYear = this.spMaleYear.getSelectedItemPosition() + 1950;
        int maleMonth = this.spMaleMonth.getSelectedItemPosition() + 1;
        int maleDay = this.spMaleDay.getSelectedItemPosition() + 1;
        int maleHour = this.spMaleHour.getSelectedItemPosition();
        int femaleYear = this.spFemaleYear.getSelectedItemPosition() + 1950;
        int femaleMonth = this.spFemaleMonth.getSelectedItemPosition() + 1;
        int femaleDay = this.spFemaleDay.getSelectedItemPosition() + 1;
        int femaleHour = this.spFemaleHour.getSelectedItemPosition();

        int[] malePillar = calcDayPillar(maleYear, maleMonth, maleDay);
        int[] femalePillar = calcDayPillar(femaleYear, femaleMonth, femaleDay);
        int maleStem = malePillar[0];
        int maleBranch = malePillar[1];
        int femaleStem = femalePillar[0];
        int femaleBranch = femalePillar[1];

        int score = 68;
        StringBuilder detail = new StringBuilder();
        detail.append("男方日柱：").append(STEEMS[maleStem]).append(BRANCHES[maleBranch]).append("，五行偏").append(ELEM_NAMES[STEM_ELEM[maleStem]]).append("。\n");
        detail.append("女方日柱：").append(STEEMS[femaleStem]).append(BRANCHES[femaleBranch]).append("，五行偏").append(ELEM_NAMES[STEM_ELEM[femaleStem]]).append("。\n");

        StringBuilder advice = new StringBuilder();

        if (isLiuHe(maleBranch, femaleBranch)) {
            score += 18;
            detail.append("两人地支六合，说明相处时容易互相理解、互补。\n");
            advice.append("属于六合组合，适合把共同目标定清楚后长期经营感情。\n");
        } else if (isLiuChong(maleBranch, femaleBranch)) {
            score -= 20;
            detail.append("两人地支相冲，说明节奏和脾气上容易顶撞。\n");
            advice.append("属于地支相冲，重要决定不要情绪化，当天有争执时先停一停。\n");
        } else {
            detail.append("地支关系中性，关键看沟通习惯和生活节奏是否匹配。\n");
            advice.append("ℹ️ 没有明显六合/相冲，日常相处比玄学分值更重要。\n");
        }

        if (STEM_ELEM[maleStem] == STEM_ELEM[femaleStem]) {
            score += 8;
            detail.append("日主五行一致，价值观与做事方式更容易同频。\n");
            advice.append("双方五行气质接近，适合一起做长期规划。\n");
        } else if (isGenerating(STEM_ELEM[maleStem], STEM_ELEM[femaleStem]) || isGenerating(STEM_ELEM[femaleStem], STEM_ELEM[maleStem])) {
            score += 12;
            detail.append("双方五行存在相生关系，彼此容易形成支持。\n");
            advice.append("五行相生，适合建立互相扶持的相处模式。\n");
        } else if (isControlling(STEM_ELEM[maleStem], STEM_ELEM[femaleStem]) || isControlling(STEM_ELEM[femaleStem], STEM_ELEM[maleStem])) {
            score -= 10;
            detail.append("双方五行有相克倾向，强势时容易互不相让。\n");
            advice.append("五行相克，建议明确边界，别把控制当关心。\n");
        } else {
            detail.append("五行关系平稳，没有明显相生相克。\n");
        }

        if (maleHour == femaleHour) {
            score += 6;
            detail.append("出生时支相同，作息与情绪节奏较容易同步。\n");
        } else if (Math.abs(maleHour - femaleHour) == 6) {
            score -= 4;
            detail.append("出生时支相对，作息和表达方式差异较大。\n");
        }

        if (isSanHeCandidate(maleBranch, femaleBranch)) {
            score += 6;
            detail.append("地支存在三合局潜力，长期磨合后默契会增强。\n");
        }

        if (score > 99) {
            score = 99;
        }
        if (score < 35) {
            score = 35;
        }

        String level;
        if (score >= 90) {
            level = "上等姻缘";
        } else if (score >= 80) {
            level = "良缘可成";
        } else if (score >= 70) {
            level = "需要经营";
        } else if (score >= 60) {
            level = "磨合偏多";
        } else {
            level = "建议谨慎";
        }

        if (advice.length() == 0) {
            advice.append("保持稳定沟通、减少情绪化表达，就是最有效的合婚增益。");
        } else {
            advice.append("共同原则比临时情绪更重要，感情要靠长期经营。");
        }

        this.resultContainer.setVisibility(0);
        saveTestResult("八字合婚", score + "分 " + level, detail.toString().trim() + "\n\n💡 " + advice.toString().trim());
        ((TextView) findViewById(R.id.tv_score)).setText(score + "分");
        ((TextView) findViewById(R.id.tv_level)).setText(level);
        ((TextView) findViewById(R.id.tv_detail)).setText(detail.toString().trim());
        ((TextView) findViewById(R.id.tv_advice)).setText(advice.toString().trim());
        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { lambda$calculate$3(v); } });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "八字合婚");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$3(View view) {
        this.resultContainer.post(new Runnable() { @Override public void run() { lambda$calculate$2(); } });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "八字合婚", new TestBillingHelper.BillCallback() { @Override public void onAllowed() { lambda$onCreate$0(); } });
    }

    private boolean isLiuHe(int a, int b) {
        for (int[] pair : LIUHE) {
            if ((pair[0] == a && pair[1] == b) || (pair[0] == b && pair[1] == a)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLiuChong(int a, int b) {
        for (int[] pair : LIUCHONG) {
            if ((pair[0] == a && pair[1] == b) || (pair[0] == b && pair[1] == a)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSanHeCandidate(int a, int b) {
        for (int[] group : SANHE) {
            boolean hasA = false;
            boolean hasB = false;
            for (int item : group) {
                if (item == a) {
                    hasA = true;
                }
                if (item == b) {
                    hasB = true;
                }
            }
            if (hasA && hasB) {
                return true;
            }
        }
        return false;
    }

    private boolean isGenerating(int from, int to) {
        return (from == 1 && to == 3) || (from == 3 && to == 4) || (from == 4 && to == 0) || (from == 0 && to == 2) || (from == 2 && to == 1);
    }

    private boolean isControlling(int from, int to) {
        return (from == 1 && to == 4) || (from == 4 && to == 2) || (from == 2 && to == 3) || (from == 3 && to == 0) || (from == 0 && to == 1);
    }

    public int[] calcDayPillar(int i4, int i5, int i6) {
        int i7 = (i4 + 4800) - ((14 - i5) / 12);
        int i8 = ((((((i7 * 12) + i5) - 3) * 153) + 2) / 5) + i6;
        int i9 = ((((((i7 / 400) + (((i7 / 4) + ((i7 * 365) + i8)) - (i7 / 100))) - 32045) - 11) % 60) + 60) % 60;
        return new int[]{i9 % 10, i9 % 12};
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_marriage);
        int i4 = 1;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("八字合婚");
        }
        this.spMaleYear = (Spinner) findViewById(R.id.sp_male_year);
        this.spMaleMonth = (Spinner) findViewById(R.id.sp_male_month);
        this.spMaleDay = (Spinner) findViewById(R.id.sp_male_day);
        this.spMaleHour = (Spinner) findViewById(R.id.sp_male_hour);
        this.spFemaleYear = (Spinner) findViewById(R.id.sp_female_year);
        this.spFemaleMonth = (Spinner) findViewById(R.id.sp_female_month);
        this.spFemaleDay = (Spinner) findViewById(R.id.sp_female_day);
        this.spFemaleHour = (Spinner) findViewById(R.id.sp_female_hour);
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);
        int i5 = Calendar.getInstance().get(1) - 1949;
        String[] strArr = new String[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            strArr[i6] = (i6 + 1950) + "年";
        }
        String[] strArr2 = new String[12];
        int i7 = 0;
        while (i7 < 12) {
            StringBuilder sb = new StringBuilder();
            int i8 = i7 + 1;
            sb.append(i8);
            sb.append("月");
            strArr2[i7] = sb.toString();
            i7 = i8;
        }
        String[] strArr3 = new String[31];
        int i9 = 0;
        while (i9 < 31) {
            StringBuilder sb2 = new StringBuilder();
            int i10 = i9 + 1;
            sb2.append(i10);
            sb2.append("日");
            strArr3[i9] = sb2.toString();
            i9 = i10;
        }
        String[] strArr4 = new String[12];
        for (int i11 = 0; i11 < 12; i11++) {
            strArr4[i11] = BRANCHES[i11] + "时";
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr2);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr3);
        ArrayAdapter arrayAdapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr4);
        this.spMaleYear.setAdapter((SpinnerAdapter) arrayAdapter);
        this.spMaleYear.setSelection(40);
        this.spMaleMonth.setAdapter((SpinnerAdapter) arrayAdapter2);
        this.spMaleDay.setAdapter((SpinnerAdapter) arrayAdapter3);
        this.spMaleHour.setAdapter((SpinnerAdapter) arrayAdapter4);
        this.spFemaleYear.setAdapter((SpinnerAdapter) arrayAdapter);
        this.spFemaleYear.setSelection(42);
        this.spFemaleMonth.setAdapter((SpinnerAdapter) arrayAdapter2);
        this.spFemaleDay.setAdapter((SpinnerAdapter) arrayAdapter3);
        this.spFemaleHour.setAdapter((SpinnerAdapter) arrayAdapter4);
        findViewById(R.id.btn_calc).setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { lambda$onCreate$1(v); } });
    }

    @Override // d.s
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
