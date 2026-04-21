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
public class KuaNumberActivity extends d.s {
    private LinearLayout resultContainer;
    private RadioGroup rgGender;
    private Spinner spYear;
    static final String[] DIR_NAMES = {"正北", "东北", "正东", "东南", "正南", "西南", "正西", "西北"};
    static final String[] AUSPICIOUS = {"生气", "天医", "延年", "伏位"};
    static final String[] INAUSPICIOUS = {"祸害", "六煞", "五鬼", "绝命"};
    static final int[][] GOOD_DIRS = {new int[0], new int[]{0, 2, 3, 1}, new int[]{4, 7, 5, 6}, new int[]{3, 1, 2, 0}, new int[]{2, 0, 3, 1}, new int[]{6, 4, 7, 5}, new int[]{5, 6, 4, 7}, new int[]{7, 5, 6, 4}, new int[]{1, 3, 0, 2}, new int[]{3, 2, 0, 1}};
    static final int[][] BAD_DIRS = {new int[0], new int[]{7, 5, 4, 6}, new int[]{0, 3, 2, 1}, new int[]{6, 7, 5, 4}, new int[]{7, 6, 5, 4}, new int[]{0, 3, 2, 1}, new int[]{1, 2, 0, 3}, new int[]{0, 1, 2, 3}, new int[]{4, 5, 7, 6}, new int[]{5, 4, 6, 7}};

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculate, reason: merged with bridge method [inline-methods] */
    public void lambda$onCreate$0() {
        int i4 = 0;
        int calcKua = calcKua(this.spYear.getSelectedItemPosition() + 1950, this.rgGender.getCheckedRadioButtonId() == R.id.rb_male);
        String str = (calcKua == 1 || calcKua == 3 || calcKua == 4 || calcKua == 9) ? "东四命" : "西四命";
        ((TextView) findViewById(R.id.tv_kua_number)).setText(calcKua + "号命卦");
        ((TextView) findViewById(R.id.tv_group)).setText(str);
        StringBuilder sb = new StringBuilder();
        for (int i5 = 0; i5 < 4; i5++) {
            int i6 = GOOD_DIRS[calcKua][i5];
            sb.append(AUSPICIOUS[i5]);
            sb.append("方：");
            sb.append(DIR_NAMES[i6]);
            if (i5 < 3) {
                sb.append("\n");
            }
        }
        ((TextView) findViewById(R.id.tv_good_dirs)).setText(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        for (int i7 = 0; i7 < 4; i7++) {
            int i8 = BAD_DIRS[calcKua][i7];
            sb2.append(INAUSPICIOUS[i7]);
            sb2.append("方：");
            sb2.append(DIR_NAMES[i8]);
            if (i7 < 3) {
                sb2.append("\n");
            }
        }
        ((TextView) findViewById(R.id.tv_bad_dirs)).setText(sb2.toString());
        StringBuilder sb3 = new StringBuilder("• 床头宜朝");
        String[] strArr = DIR_NAMES;
        int[][] iArr = GOOD_DIRS;
        sb3.append(strArr[iArr[calcKua][2]]);
        sb3.append("（延年）方\n• 办公桌宜朝");
        sb3.append(strArr[iArr[calcKua][0]]);
        sb3.append("（生气）方\n• 大门宜开");
        sb3.append(strArr[iArr[calcKua][1]]);
        sb3.append("（天医）方\n• 避免在");
        ((TextView) findViewById(R.id.tv_advice)).setText(o.h.a(sb3, strArr[BAD_DIRS[calcKua][3]], "（绝命）方设卧室或灶台"));
        this.resultContainer.setVisibility(0);
        findViewById(R.id.btn_share).setOnClickListener(new j0(this, i4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "八宅命卦");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$3(View view) {
        this.resultContainer.post(new h(7, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "八宅命卦", new k(this, 3));
    }

    public int calcKua(int i4, boolean z4) {
        int i5 = 0;
        while (i4 > 0) {
            i5 += i4 % 10;
            i4 /= 10;
        }
        while (i5 > 9) {
            int i6 = 0;
            while (i5 > 0) {
                i6 += i5 % 10;
                i5 /= 10;
            }
            i5 = i6;
        }
        if (z4) {
            int i7 = 10 - i5;
            if (i7 == 5) {
                return 2;
            }
            return i7;
        }
        int i8 = i5 + 5;
        if (i8 > 9) {
            i8 -= 9;
        }
        int i9 = i8;
        if (i9 == 5) {
            return 8;
        }
        return i9;
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_kua);
        int i4 = 1;
        if (getSupportActionBar() != null) {
            getSupportActionBar().m(true);
            getSupportActionBar().o("八宅命卦");
        }
        this.spYear = (Spinner) findViewById(R.id.sp_year);
        this.rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);
        int i5 = Calendar.getInstance().get(1) - 1949;
        String[] strArr = new String[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            strArr[i6] = (i6 + 1950) + "年";
        }
        this.spYear.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr));
        this.spYear.setSelection(Math.max(0, 40));
        findViewById(R.id.btn_calc).setOnClickListener(new j0(this, i4));
    }

    @Override // d.s
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
