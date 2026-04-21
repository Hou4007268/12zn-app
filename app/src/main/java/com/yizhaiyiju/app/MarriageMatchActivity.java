package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import java.util.Calendar;

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
    /* JADX WARN: Removed duplicated region for block: B:100:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x032d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02dc  */
    /* renamed from: calculate, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void lambda$onCreate$0() {
        /*
            Method dump skipped, instructions count: 909
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yizhaiyiju.app.MarriageMatchActivity.lambda$onCreate$0():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "八字合婚");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$3(View view) {
        this.resultContainer.post(new h(8, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "八字合婚", new k(this, 5));
    }

    public int[] calcDayPillar(int i4, int i5, int i6) {
        int i7 = (i4 + 4800) - ((14 - i5) / 12);
        int i8 = ((((((r0 * 12) + i5) - 3) * 153) + 2) / 5) + i6;
        int i9 = ((((((i7 / 400) + (((i7 / 4) + ((i7 * 365) + i8)) - (i7 / 100))) - 32045) - 11) % 60) + 60) % 60;
        return new int[]{i9 % 10, i9 % 12};
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_marriage);
        int i4 = 1;
        if (getSupportActionBar() != null) {
            getSupportActionBar().m(true);
            getSupportActionBar().o("八字合婚");
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
            strArr4[i11] = o.h.a(new StringBuilder(), BRANCHES[i11], "时");
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
        findViewById(R.id.btn_calc).setOnClickListener(new l0(this, i4));
    }

    @Override // d.s
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
