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
    private static final String[] DIRECTION_NAMES = {"北", "东北", "东", "东南", "南", "西南", "西", "西北"};
    private static final String[] AUSPICIOUS_NAMES = {"生气", "天医", "延年", "伏位"};
    private static final String[] INAUSPICIOUS_NAMES = {"祸害", "六煞", "五鬼", "绝命"};
    private static final int[][] GOOD_DIRECTIONS = {
            new int[0],
            new int[]{0, 2, 3, 1},
            new int[]{4, 7, 5, 6},
            new int[]{3, 1, 2, 0},
            new int[]{2, 0, 3, 1},
            new int[]{6, 4, 7, 5},
            new int[]{5, 6, 4, 7},
            new int[]{7, 5, 6, 4},
            new int[]{1, 3, 0, 2},
            new int[]{3, 2, 0, 1}
    };
    private static final int[][] BAD_DIRECTIONS = {
            new int[0],
            new int[]{7, 5, 4, 6},
            new int[]{0, 3, 2, 1},
            new int[]{6, 7, 5, 4},
            new int[]{7, 6, 5, 4},
            new int[]{0, 3, 2, 1},
            new int[]{1, 2, 0, 3},
            new int[]{0, 1, 2, 3},
            new int[]{4, 5, 7, 6},
            new int[]{5, 4, 6, 7}
    };

    private LinearLayout resultContainer;
    private RadioGroup rgGender;
    private Spinner spYear;

    public void lambda$onCreate$0() {
        int kuaNumber = calcKua(this.spYear.getSelectedItemPosition() + 1950, this.rgGender.getCheckedRadioButtonId() == R.id.rb_male);
        String groupName = isEastGroup(kuaNumber) ? "东四命" : "西四命";

        ((TextView) findViewById(R.id.tv_kua_number)).setText("命卦：" + kuaNumber);
        ((TextView) findViewById(R.id.tv_group)).setText(groupName);
        ((TextView) findViewById(R.id.tv_good_dirs)).setText(buildDirectionBlock(kuaNumber, true));
        ((TextView) findViewById(R.id.tv_bad_dirs)).setText(buildDirectionBlock(kuaNumber, false));
        ((TextView) findViewById(R.id.tv_advice)).setText(buildAdvice(kuaNumber));

        this.resultContainer.setVisibility(View.VISIBLE);
        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lambda$calculate$2();
            }
        });
    }

    private boolean isEastGroup(int kuaNumber) {
        return kuaNumber == 1 || kuaNumber == 3 || kuaNumber == 4 || kuaNumber == 9;
    }

    private String buildDirectionBlock(int kuaNumber, boolean good) {
        String[] labels = good ? AUSPICIOUS_NAMES : INAUSPICIOUS_NAMES;
        int[] directionIndexes = good ? GOOD_DIRECTIONS[kuaNumber] : BAD_DIRECTIONS[kuaNumber];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < labels.length; i++) {
            builder.append(labels[i]).append("：").append(DIRECTION_NAMES[directionIndexes[i]]);
            if (i < labels.length - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private String buildAdvice(int kuaNumber) {
        StringBuilder advice = new StringBuilder();
        advice.append("床位宜朝：")
                .append(DIRECTION_NAMES[GOOD_DIRECTIONS[kuaNumber][2]])
                .append("（延年）")
                .append("\n书桌宜朝：")
                .append(DIRECTION_NAMES[GOOD_DIRECTIONS[kuaNumber][0]])
                .append("（生气）")
                .append("\n大门宜朝：")
                .append(DIRECTION_NAMES[GOOD_DIRECTIONS[kuaNumber][1]])
                .append("（天医）")
                .append("\n尽量避开：")
                .append(DIRECTION_NAMES[BAD_DIRECTIONS[kuaNumber][3]])
                .append("（绝命）");
        return advice.toString();
    }

    public void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "八宅命卦");
    }

    public void lambda$calculate$3(View view) {
        this.resultContainer.post(new h(7, this));
    }

    public void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "八宅命卦", new k(this, 3));
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
        if (result > 9) {
            result -= 9;
        }
        return result == 5 ? 8 : result;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_kua);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("八宅命卦");
        }
        this.spYear = (Spinner) findViewById(R.id.sp_year);
        this.rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);

        int count = Calendar.getInstance().get(Calendar.YEAR) - 1949;
        String[] years = new String[count];
        for (int i = 0; i < count; i++) {
            years[i] = String.valueOf(i + 1950);
        }
        this.spYear.setAdapter((SpinnerAdapter) new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years));
        this.spYear.setSelection(Math.max(0, 40));
        findViewById(R.id.btn_calc).setOnClickListener(new j0(this, 1));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
