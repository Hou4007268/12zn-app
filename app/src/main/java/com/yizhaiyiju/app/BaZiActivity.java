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

public class BaZiActivity extends d.s {
    static final String[] STEMS = {"Jia", "Yi", "Bing", "Ding", "Wu", "Ji", "Geng", "Xin", "Ren", "Gui"};
    static final String[] BRANCHES = {"Zi", "Chou", "Yin", "Mao", "Chen", "Si", "Wu", "Wei", "Shen", "You", "Xu", "Hai"};
    static final int[][] BRANCH_HIDDEN = {new int[]{8}, new int[]{5, 8, 6}, new int[]{0, 2, 4}, new int[]{1}, new int[]{1, 4, 8}, new int[]{2, 4, 6}, new int[]{3, 5}, new int[]{5, 3, 1}, new int[]{6, 8, 4}, new int[]{7}, new int[]{7, 3, 4}, new int[]{8, 0}};
    static final String[] ELEMENTS = {"Metal", "Wood", "Water", "Fire", "Earth"};
    static final int[] STEM_ELEMENT = {1, 1, 3, 3, 4, 4, 0, 0, 2, 2};
    static final String[] ELEMENT_NAMES = {"Metal", "Wood", "Water", "Fire", "Earth"};
    static final String[] TEN_GODS = {"BiJian", "JieCai", "ShiShen", "ShangGuan", "PianCai", "ZhengCai", "QiSha", "ZhengGuan", "PianYin", "ZhengYin"};
    private LinearLayout resultContainer;
    private RadioGroup rgGender;
    private Spinner spDay;
    private Spinner spHour;
    private Spinner spMonth;
    private Spinner spYear;

    public void lambda$onCreate$0() {
        int year = this.spYear.getSelectedItemPosition() + 1950;
        int month = this.spMonth.getSelectedItemPosition() + 1;
        int day = this.spDay.getSelectedItemPosition() + 1;
        int hourBranch = this.spHour.getSelectedItemPosition();
        int[] yearPillar = calcYearPillar(year);
        int[] monthPillar = calcMonthPillar(year, month);
        int[] dayPillar = calcDayPillar(year, month, day);
        int[] hourPillar = calcHourPillar(dayPillar[0], hourBranch);
        ((TextView) findViewById(R.id.tv_year_tg)).setText(STEMS[yearPillar[0]] + "-" + BRANCHES[yearPillar[1]]);
        ((TextView) findViewById(R.id.tv_month_tg)).setText(STEMS[monthPillar[0]] + "-" + BRANCHES[monthPillar[1]]);
        ((TextView) findViewById(R.id.tv_day_tg)).setText(STEMS[dayPillar[0]] + "-" + BRANCHES[dayPillar[1]]);
        ((TextView) findViewById(R.id.tv_hour_tg)).setText(STEMS[hourPillar[0]] + "-" + BRANCHES[hourPillar[1]]);
        ((TextView) findViewById(R.id.tv_day_master)).setText("Day Master: " + STEMS[dayPillar[0]] + " (" + ELEMENTS[STEM_ELEMENT[dayPillar[0]]] + ")");
        int[] counts = new int[5];
        counts[STEM_ELEMENT[yearPillar[0]]]++;
        counts[STEM_ELEMENT[monthPillar[0]]]++;
        counts[STEM_ELEMENT[dayPillar[0]]]++;
        counts[STEM_ELEMENT[hourPillar[0]]]++;
        addBranchElements(counts, yearPillar[1]);
        addBranchElements(counts, monthPillar[1]);
        addBranchElements(counts, dayPillar[1]);
        addBranchElements(counts, hourPillar[1]);
        StringBuilder elements = new StringBuilder();
        for (int i = 0; i < 5; i++) elements.append(ELEMENT_NAMES[i]).append(": ").append(counts[i]).append("  ");
        ((TextView) findViewById(R.id.tv_elements)).setText(elements.toString());
        int weakest = 0;
        int strongest = 0;
        for (int i = 1; i < 5; i++) {
            if (counts[i] < counts[weakest]) weakest = i;
            if (counts[i] > counts[strongest]) strongest = i;
        }
        StringBuilder advice = new StringBuilder("Weakest: ");
        advice.append(ELEMENT_NAMES[weakest]).append(". Strongest: ").append(ELEMENT_NAMES[strongest]).append(". ");
        advice.append("Year ").append(tenGod(dayPillar[0], yearPillar[0]));
        advice.append(", Month ").append(tenGod(dayPillar[0], monthPillar[0]));
        advice.append(", Hour ").append(tenGod(dayPillar[0], hourPillar[0]));
        ((TextView) findViewById(R.id.tv_element_advice)).setText(advice.toString());
        ((TextView) findViewById(R.id.tv_dayun)).setText("Flow summary ready");
        this.resultContainer.setVisibility(0);
        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { lambda$calculate$2(); }
        });
    }

    public void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "BaZi", new TestBillingHelper.BillCallback() {
            @Override public void onAllowed() { lambda$onCreate$0(); }
        });
    }

    public void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "BaZi Result");
    }

    public void lambda$calculate$3(View view) {
        this.resultContainer.post(new h(4, this));
    }

    private void addBranchElements(int[] counts, int branch) {
        for (int stem : BRANCH_HIDDEN[branch]) counts[STEM_ELEMENT[stem]]++;
    }

    public int[] calcDayPillar(int year, int month, int day) {
        int y = (year + 4800) - ((14 - month) / 12);
        int m = month + (12 * ((14 - month) / 12)) - 3;
        int jdn = day + (((m * 153) + 2) / 5) + (365 * y) + (y / 4) - (y / 100) + (y / 400) - 32045;
        int idx = ((jdn - 11) % 60 + 60) % 60;
        return new int[]{idx % 10, idx % 12};
    }

    public int[] calcHourPillar(int dayStem, int hourBranch) {
        return new int[]{(((dayStem % 5) * 2) + hourBranch) % 10, hourBranch};
    }

    public int[] calcMonthPillar(int year, int month) {
        int[][] starts = {new int[]{2, 4, 6, 8, 0, 2, 4, 6, 8, 0}, new int[]{4, 6, 8, 0, 2, 4, 6, 8, 0, 2}, new int[]{6, 8, 0, 2, 4, 6, 8, 0, 2, 4}, new int[]{8, 0, 2, 4, 6, 8, 0, 2, 4, 6}, new int[]{0, 2, 4, 6, 8, 0, 2, 4, 6, 8}};
        int start = starts[calcYearPillar(year)[0] % 5][0];
        return new int[]{((start + month) - 1) % 10, (month + 1) % 12};
    }

    public int[] calcYearPillar(int year) {
        int idx = (year - 1) - 4;
        return new int[]{((idx % 10) + 10) % 10, ((idx % 12) + 12) % 12};
    }

    public String tenGod(int dayStem, int otherStem) {
        return TEN_GODS[((otherStem - dayStem) + 10) % 10];
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bazi);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("BaZi");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        this.spYear = (Spinner) findViewById(R.id.sp_year);
        this.spMonth = (Spinner) findViewById(R.id.sp_month);
        this.spDay = (Spinner) findViewById(R.id.sp_day);
        this.spHour = (Spinner) findViewById(R.id.sp_hour);
        this.rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);
        setupSpinners();
        findViewById(R.id.btn_calc).setOnClickListener(new l(this, 1));
    }

    public void setupSpinners() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String[] years = new String[(currentYear - 1950) + 1];
        for (int i = 0; i < years.length; i++) years[i] = String.valueOf(1950 + i);
        this.spYear.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, years));
        this.spYear.setSelection(years.length - 1);
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) months[i] = String.valueOf(i + 1);
        this.spMonth.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, months));
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);
        this.spDay.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, days));
        String[] hours = new String[12];
        for (int i = 0; i < 12; i++) hours[i] = BRANCHES[i] + " (" + (i * 2) + "-" + ((i * 2) + 2) + ")";
        this.spHour.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hours));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
