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

public class KuaNumberActivity extends d.s {
    private LinearLayout resultContainer;
    private RadioGroup rgGender;
    private Spinner spYear;
    static final String[] DIR_NAMES = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
    static final String[] AUSPICIOUS = {"ShengQi", "TianYi", "YanNian", "FuWei"};
    static final String[] INAUSPICIOUS = {"HuoHai", "LiuSha", "WuGui", "JueMing"};
    static final int[][] GOOD_DIRS = {new int[0], new int[]{0, 2, 3, 1}, new int[]{4, 7, 5, 6}, new int[]{3, 1, 2, 0}, new int[]{2, 0, 3, 1}, new int[]{6, 4, 7, 5}, new int[]{5, 6, 4, 7}, new int[]{7, 5, 6, 4}, new int[]{1, 3, 0, 2}, new int[]{3, 2, 0, 1}};
    static final int[][] BAD_DIRS = {new int[0], new int[]{7, 5, 4, 6}, new int[]{0, 3, 2, 1}, new int[]{6, 7, 5, 4}, new int[]{7, 6, 5, 4}, new int[]{0, 3, 2, 1}, new int[]{1, 2, 0, 3}, new int[]{0, 1, 2, 3}, new int[]{4, 5, 7, 6}, new int[]{5, 4, 6, 7}};

    public void lambda$onCreate$0() {
        int calcKua = calcKua(this.spYear.getSelectedItemPosition() + 1950, this.rgGender.getCheckedRadioButtonId() == R.id.rb_male);
        String group = (calcKua == 1 || calcKua == 3 || calcKua == 4 || calcKua == 9) ? "East Group" : "West Group";
        ((TextView) findViewById(R.id.tv_kua_number)).setText(calcKua + " Kua");
        ((TextView) findViewById(R.id.tv_group)).setText(group);
        StringBuilder good = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int dir = GOOD_DIRS[calcKua][i];
            good.append(AUSPICIOUS[i]).append(": ").append(DIR_NAMES[dir]);
            if (i < 3) good.append("\n");
        }
        ((TextView) findViewById(R.id.tv_good_dirs)).setText(good.toString());
        StringBuilder bad = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int dir = BAD_DIRS[calcKua][i];
            bad.append(INAUSPICIOUS[i]).append(": ").append(DIR_NAMES[dir]);
            if (i < 3) bad.append("\n");
        }
        ((TextView) findViewById(R.id.tv_bad_dirs)).setText(bad.toString());
        StringBuilder advice = new StringBuilder("Bed: ");
        advice.append(DIR_NAMES[GOOD_DIRS[calcKua][2]]);
        advice.append("\nDesk: ");
        advice.append(DIR_NAMES[GOOD_DIRS[calcKua][0]]);
        advice.append("\nDoor: ");
        advice.append(DIR_NAMES[GOOD_DIRS[calcKua][1]]);
        advice.append("\nAvoid: ");
        advice.append(DIR_NAMES[BAD_DIRS[calcKua][3]]);
        ((TextView) findViewById(R.id.tv_advice)).setText(advice.toString());
        this.resultContainer.setVisibility(0);
        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { lambda$calculate$2(); }
        });
    }

    public void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "Kua Result");
    }

    public void lambda$calculate$3(View view) {
        this.resultContainer.post(new h(7, this));
    }

    public void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "Kua Number", new k(this, 3));
    }

    public int calcKua(int year, boolean male) {
        int sum = 0;
        while (year > 0) {
            sum += year % 10;
            year /= 10;
        }
        while (sum > 9) {
            int inner = 0;
            while (sum > 0) {
                inner += sum % 10;
                sum /= 10;
            }
            sum = inner;
        }
        if (male) {
            int result = 10 - sum;
            return result == 5 ? 2 : result;
        }
        int result = sum + 5;
        if (result > 9) result -= 9;
        return result == 5 ? 8 : result;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_kua);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Kua Number");
        }
        this.spYear = (Spinner) findViewById(R.id.sp_year);
        this.rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);
        int count = Calendar.getInstance().get(Calendar.YEAR) - 1949;
        String[] years = new String[count];
        for (int i = 0; i < count; i++) years[i] = String.valueOf(i + 1950);
        this.spYear.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, years));
        this.spYear.setSelection(Math.max(0, 40));
        findViewById(R.id.btn_calc).setOnClickListener(new j0(this, 1));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
