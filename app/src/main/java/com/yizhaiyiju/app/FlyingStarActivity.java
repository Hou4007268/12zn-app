package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/* loaded from: classes.dex */
public class FlyingStarActivity extends d.s {
    private LinearLayout resultContainer;
    private Spinner spDir;
    private Spinner spYear;
    private TextView[] tvCells = new TextView[9];
    static final String[] PALACE_NAMES = {"西北", "北", "东北", "西", "中", "东", "西南", "南", "东南"};
    static final int[] HT_ORDER = {6, 1, 8, 7, 5, 3, 2, 9, 4};
    static final String[] STAR_NAMES = {"", "一白贪狼", "二黑巨门", "三碧禄存", "四绿文曲", "五黄廉贞", "六白武曲", "七赤破军", "八白左辅", "九紫右弼"};
    static final String[] STAR_QUALITY = {"", "吉", "凶", "凶", "吉", "大凶", "吉", "凶", "吉", "吉"};
    static final String[] STAR_MEANING = {"", "桃花/文昌", "病符/健康", "是非/口舌", "文昌/考试", "灾煞/意外", "偏财/权力", "破财/盗贼", "正财/喜庆", "喜庆/姻缘"};

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculate, reason: merged with bridge method [inline-methods] */
    public void lambda$onCreate$0() {
        TextView textView;
        int i4;
        int selectedItemPosition = this.spYear.getSelectedItemPosition() + 1864;
        int selectedItemPosition2 = this.spDir.getSelectedItemPosition();
        int calcPeriod = calcPeriod(selectedItemPosition);
        ((TextView) findViewById(R.id.tv_period_info)).setText(selectedItemPosition + "年建造 | " + selectedItemPosition2 + "向 | " + calcPeriod + "运");
        int[] iArr = new int[9];
        int[] iArr2 = new int[9];
        int[] iArr3 = new int[9];
        generateStars(calcPeriod, selectedItemPosition2, iArr, iArr2, iArr3);
        String[] strArr = {"西北", "正北", "东北", "正西", "中宫", "正东", "西南", "正南", "东南"};
        StringBuilder sb = new StringBuilder();
        for (int i5 = 0; i5 < 9; i5++) {
            int i6 = iArr[i5];
            int i7 = iArr2[i5];
            String quality = getQuality(iArr3[i5], i6, i7);
            this.tvCells[i5].setText(strArr[i5] + "\n" + iArr3[i5] + i6 + i7 + "\n" + quality);
            if (quality.contains("吉")) {
                textView = this.tvCells[i5];
                i4 = -1509911;
            } else if (quality.contains("凶")) {
                textView = this.tvCells[i5];
                i4 = -5138;
            } else {
                sb.append(strArr[i5]);
                sb.append(": ");
                sb.append(iArr3[i5]);
                sb.append(i6);
                sb.append(i7);
                sb.append(" ");
                sb.append(quality);
                sb.append("\n");
            }
            textView.setBackgroundColor(i4);
            sb.append(strArr[i5]);
            sb.append(": ");
            sb.append(iArr3[i5]);
            sb.append(i6);
            sb.append(i7);
            sb.append(" ");
            sb.append(quality);
            sb.append("\n");
        }
        ((TextView) findViewById(R.id.tv_analysis)).setText(sb.toString());
        this.resultContainer.setVisibility(0);
        findViewById(R.id.btn_share).setOnClickListener(new f0(this, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "玄空飞星");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$3(View view) {
        this.resultContainer.post(new h(6, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "玄空飞星", new k(this, 1));
    }

    public int calcPeriod(int i4) {
        int i5 = ((i4 - 1864) / 20) % 9;
        if (i5 == 0) {
            return 9;
        }
        return i5;
    }

    public void generateStars(int i4, int i5, int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = {5, 8, 1, 6, 7, 3, 4, 9, 2};
        int[] iArr5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int i6 = i4 - 1;
        for (int i7 = 0; i7 < 9; i7++) {
            int i8 = iArr4[i7] - 1;
            if (i8 < 0 || i8 > 8) {
                i8 = 0;
            }
            int i9 = (i7 + i6) % 9;
            iArr3[i8] = iArr5[i9];
            iArr[i8] = ((i9 + i6) % 9) + 1;
            iArr2[i8] = (((i9 + 9) - i6) % 9) + 1;
        }
    }

    public String getQuality(int i4, int i5, int i6) {
        int[] iArr = {1, 6, 8, 9};
        int[] iArr2 = {2, 5};
        boolean z4 = false;
        boolean z5 = false;
        for (int i7 = 0; i7 < 4; i7++) {
            int i8 = iArr[i7];
            if (i5 == i8) {
                z4 = true;
            }
            if (i6 == i8) {
                z5 = true;
            }
        }
        boolean z6 = false;
        boolean z7 = false;
        for (int i9 = 0; i9 < 2; i9++) {
            int i10 = iArr2[i9];
            if (i5 == i10) {
                z6 = true;
            }
            if (i6 == i10) {
                z7 = true;
            }
        }
        return (z4 && z5) ? "⭐大吉" : (z4 || z5) ? "✅吉" : (z6 && z7) ? "❌大凶" : (z6 || z7) ? "⚠️凶" : "⚪平";
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_flying_star);
        if (getSupportActionBar() != null) {
            getSupportActionBar().m(true);
            getSupportActionBar().o("玄空飞星");
        }
        this.spYear = (Spinner) findViewById(R.id.sp_build_year);
        this.spDir = (Spinner) findViewById(R.id.sp_direction);
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);
        String[] strArr = new String[181];
        int i4 = 0;
        for (int i5 = 0; i5 < 181; i5++) {
            strArr[i5] = (i5 + 1864) + "年";
        }
        this.spYear.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr));
        this.spYear.setSelection(160);
        this.spDir.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"坐北朝南(子山午向)", "坐南朝北(午山子向)", "坐东朝西(卯山酉向)", "坐西朝东(酉山卯向)", "坐东北朝西南(艮山坤向)", "坐西南朝东北(坤山艮向)", "坐东南朝西北(巽山乾向)", "坐西北朝东南(乾山巽向)"}));
        int[] iArr = {R.id.tv_8, R.id.tv_1, R.id.tv_6, R.id.tv_7, R.id.tv_center, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_9};
        for (int i6 = 0; i6 < 9; i6++) {
            this.tvCells[i6] = (TextView) findViewById(iArr[i6]);
        }
        findViewById(R.id.btn_calc).setOnClickListener(new f0(this, i4));
    }

    @Override // d.s
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
