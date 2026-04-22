package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.Calendar;

/* loaded from: classes.dex */
public class BaZiActivity extends d.s {
    private LinearLayout resultContainer;
    private RadioGroup rgGender;
    private Spinner spDay;
    private Spinner spHour;
    private Spinner spMonth;
    private Spinner spYear;
    static final String[] STEMS = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    static final String[] BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    static final int[][] BRANCH_HIDDEN = {new int[]{9}, new int[]{5, 9, 7}, new int[]{0, 2, 4}, new int[]{1}, new int[]{1, 4, 9}, new int[]{2, 4, 6}, new int[]{3, 5}, new int[]{5, 3, 1}, new int[]{6, 8, 4}, new int[]{7}, new int[]{7, 3, 4}, new int[]{8, 0}};
    static final String[] ELEMENTS = {"金", "木", "水", "火", "土"};
    static final int[] STEM_ELEMENT = {1, 1, 3, 3, 4, 4, 0, 0, 2, 2};
    static final int[] BRANCH_ELEMENT = {2, 4, 1, 1, 4, 3, 3, 4, 0, 0, 4, 2};
    static final String[] ELEMENT_EMOJI = {"🔶", "🌿", "💧", "🔥", "🏔️"};
    static final String[] ELEMENT_NAMES = {"金", "木", "水", "火", "土"};
    static final String[] TEN_GODS = {"比肩", "劫财", "食神", "伤官", "偏财", "正财", "七杀", "正官", "偏印", "正印"};
    private int selectedYear = 1990;
    private int selectedMonth = 1;
    private int selectedDay = 1;
    private int selectedHour = 0;
    private boolean isMale = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculate, reason: merged with bridge method [inline-methods] */
    public void lambda$onCreate$0() {
        int i4;
        int i5;
        this.selectedYear = this.spYear.getSelectedItemPosition() + 1950;
        this.selectedMonth = this.spMonth.getSelectedItemPosition() + 1;
        this.selectedDay = this.spDay.getSelectedItemPosition() + 1;
        this.selectedHour = this.spHour.getSelectedItemPosition();
        int i6 = 0;
        this.isMale = this.rgGender.getCheckedRadioButtonId() == R.id.rb_male;
        int[] calcYearPillar = calcYearPillar(this.selectedYear);
        int[] calcMonthPillar = calcMonthPillar(this.selectedYear, this.selectedMonth);
        int[] calcDayPillar = calcDayPillar(this.selectedYear, this.selectedMonth, this.selectedDay);
        int[] calcHourPillar = calcHourPillar(calcDayPillar[0], this.selectedHour);
        TextView textView = (TextView) findViewById(R.id.tv_year_stem);
        String[] strArr = STEMS;
        textView.setText(strArr[calcYearPillar[0]]);
        TextView textView2 = (TextView) findViewById(R.id.tv_year_branch);
        String[] strArr2 = BRANCHES;
        textView2.setText(strArr2[calcYearPillar[1]]);
        ((TextView) findViewById(R.id.tv_month_stem)).setText(strArr[calcMonthPillar[0]]);
        ((TextView) findViewById(R.id.tv_month_branch)).setText(strArr2[calcMonthPillar[1]]);
        ((TextView) findViewById(R.id.tv_day_stem)).setText(strArr[calcDayPillar[0]]);
        ((TextView) findViewById(R.id.tv_day_branch)).setText(strArr2[calcDayPillar[1]]);
        ((TextView) findViewById(R.id.tv_hour_stem)).setText(strArr[calcHourPillar[0]]);
        ((TextView) findViewById(R.id.tv_hour_branch)).setText(strArr2[calcHourPillar[1]]);
        int i7 = calcDayPillar[0];
        ((TextView) findViewById(R.id.tv_year_tg)).setText(tenGod(i7, calcYearPillar[0]));
        ((TextView) findViewById(R.id.tv_month_tg)).setText(tenGod(i7, calcMonthPillar[0]));
        ((TextView) findViewById(R.id.tv_day_tg)).setText("日主");
        ((TextView) findViewById(R.id.tv_hour_tg)).setText(tenGod(i7, calcHourPillar[0]));
        ((TextView) findViewById(R.id.tv_day_master)).setText("日主" + strArr[i7] + "（" + ELEMENT_NAMES[STEM_ELEMENT[i7]] + "）");
        int[] iArr = new int[5];
        int[][] iArr2 = {calcYearPillar, calcMonthPillar, calcDayPillar, calcHourPillar};
        for (int i8 = 0; i8 < 4; i8++) {
            int[] iArr3 = iArr2[i8];
            int i9 = STEM_ELEMENT[iArr3[0]];
            iArr[i9] = iArr[i9] + 1;
            int i10 = BRANCH_ELEMENT[iArr3[1]];
            iArr[i10] = iArr[i10] + 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i11 = 0; i11 < 5; i11++) {
            sb.append(ELEMENT_EMOJI[i11]);
            sb.append(ELEMENT_NAMES[i11]);
            sb.append(": ");
            sb.append(iArr[i11]);
            sb.append("  ");
        }
        ((TextView) findViewById(R.id.tv_elements)).setText(sb.toString());
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 1; i14 < 5; i14++) {
            int i15 = iArr[i14];
            if (i15 < iArr[i12]) {
                i12 = i14;
            }
            if (i15 > iArr[i13]) {
                i13 = i14;
            }
        }
        StringBuilder sb2 = new StringBuilder("喜用神参考：五行");
        String[] strArr3 = ELEMENT_NAMES;
        sb2.append(strArr3[i12]);
        sb2.append("偏弱，可多接触");
        sb2.append(getElemAdvice(i12));
        sb2.append("来补益。忌");
        ((TextView) findViewById(R.id.tv_element_advice)).setText(new StringBuilder().append(sb2).append(strArr3[i13]).append("过旺。").toString());
        boolean z4 = this.isMale;
        boolean z5 = calcYearPillar[0] % 2 == 0;
        int max = Math.max(1, ((((!(z5 && z4) && (z5 || z4)) ? 13 - this.selectedMonth : this.selectedMonth - 1) * 10) / 3) + 1);
        StringBuilder j4 = new StringBuilder("起运年龄：约");
        j4.append(max);
        j4.append("岁\n\n");
        int i16 = calcMonthPillar[0];
        int i17 = calcMonthPillar[1];
        for (int i18 = 0; i18 < 8; i18++) {
            int i19 = (i18 * 10) + max;
            if (!(z5 && this.isMale) && (z5 || this.isMale)) {
                i4 = (((i16 - 1) - i18) + 20) % 10;
                i5 = ((i17 - 1) - i18) + 24;
            } else {
                i4 = ((i16 + 1) + i18) % 10;
                i5 = i17 + 1 + i18;
            }
            String tenGod = tenGod(i7, i4);
            int i20 = i19 + 9;
            j4.append(i19);
            j4.append("岁(");
            int i21 = this.selectedYear;
            j4.append(((i19 + 1900) + i21) - i21);
            j4.append("-");
            int i22 = this.selectedYear;
            j4.append(((i20 + 1900) + i22) - i22);
            j4.append(") ");
            j4.append(STEMS[i4]);
            j4.append(BRANCHES[i5 % 12]);
            j4.append("  ");
            j4.append(tenGod);
            if (i18 < 7) {
                j4.append("\n");
            }
        }
        ((TextView) findViewById(R.id.tv_dayun)).setText(j4.toString());
        this.resultContainer.setVisibility(0);
        findViewById(R.id.btn_share).setOnClickListener(new l(this, i6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "八字排盘");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$3(View view) {
        this.resultContainer.post(new h(4, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "八字排盘", new k(this, 0));
    }

    private void setupSpinners() {
        int i4 = Calendar.getInstance().get(1) - 1949;
        String[] strArr = new String[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            strArr[i5] = (i5 + 1950) + "年";
        }
        this.spYear.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr));
        this.spYear.setSelection(Math.max(0, 40));
        String[] strArr2 = new String[12];
        int i6 = 0;
        while (i6 < 12) {
            StringBuilder sb = new StringBuilder();
            int i7 = i6 + 1;
            sb.append(i7);
            sb.append("月");
            strArr2[i6] = sb.toString();
            i6 = i7;
        }
        this.spMonth.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr2));
        this.spMonth.setSelection(0);
        String[] strArr3 = new String[31];
        int i8 = 0;
        while (i8 < 31) {
            StringBuilder sb2 = new StringBuilder();
            int i9 = i8 + 1;
            sb2.append(i9);
            sb2.append("日");
            strArr3[i8] = sb2.toString();
            i8 = i9;
        }
        this.spDay.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr3));
        this.spDay.setSelection(0);
        String[] strArr4 = new String[12];
        for (int i10 = 0; i10 < 12; i10++) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(BRANCHES[i10]);
            sb3.append("时 (");
            int i11 = i10 * 2;
            sb3.append(i11);
            sb3.append("-");
            sb3.append(i11 + 2);
            sb3.append("点)");
            strArr4[i10] = sb3.toString();
        }
        this.spHour.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr4));
        this.spHour.setSelection(0);
    }

    public int[] calcDayPillar(int i4, int i5, int i6) {
        int i7 = (i4 + 4800) - ((14 - i5) / 12);
        int i8 = ((((((i7 * 12) + i5) - 3) * 153) + 2) / 5) + i6;
        int i9 = ((((((i7 / 400) + (((i7 / 4) + ((i7 * 365) + i8)) - (i7 / 100))) - 32045) - 11) % 60) + 60) % 60;
        return new int[]{i9 % 10, i9 % 12};
    }

    public int[] calcHourPillar(int i4, int i5) {
        return new int[]{(((i4 % 5) * 2) + i5) % 10, i5};
    }

    public int[] calcMonthPillar(int i4, int i5) {
        return new int[]{((new int[][]{new int[]{2, 4, 6, 8, 0, 2, 4, 6, 8, 0}, new int[]{4, 6, 8, 0, 2, 4, 6, 8, 0, 2}, new int[]{6, 8, 0, 2, 4, 6, 8, 0, 2, 4}, new int[]{8, 0, 2, 4, 6, 8, 0, 2, 4, 6}, new int[]{0, 2, 4, 6, 8, 0, 2, 4, 6, 8}}[calcYearPillar(i4)[0] % 5][0] + i5) - 1) % 10, (i5 + 1) % 12};
    }

    public int[] calcYearPillar(int i4) {
        int i5 = (i4 - 1) - 4;
        return new int[]{((i5 % 10) + 10) % 10, ((i5 % 12) + 12) % 12};
    }

    public String getElemAdvice(int i4) {
        return i4 != 0 ? i4 != 1 ? i4 != 2 ? i4 != 3 ? i4 != 4 ? "" : "黄色、陶瓷、中央方位" : "红色紫色、灯光、南方位" : "黑色蓝色、水景、北方位" : "绿色、植物、东方位" : "白色、金属饰品、西方位";
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bazi);
        int i4 = 1;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("八字排盘");
        }
        this.spYear = (Spinner) findViewById(R.id.sp_year);
        this.spMonth = (Spinner) findViewById(R.id.sp_month);
        this.spDay = (Spinner) findViewById(R.id.sp_day);
        this.spHour = (Spinner) findViewById(R.id.sp_hour);
        this.rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);
        setupSpinners();
        findViewById(R.id.btn_calc).setOnClickListener(new l(this, i4));
    }

    @Override // d.s
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public String tenGod(int i4, int i5) {
        return TEN_GODS[((i5 - i4) + 10) % 10];
    }
}
