package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

/* loaded from: classes.dex */
public class NameAnalysisActivity extends d.s {
    static final int[][] STROKE_TABLE = new int[0][];
    private EditText etName;
    private LinearLayout resultContainer;
    private Spinner spYear;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculate, reason: merged with bridge method [inline-methods] */
    public void lambda$onCreate$0() {
        String trim = this.etName.getText().toString().trim();
        if (trim.isEmpty()) {
            Toast.makeText(this, "请输入姓名", 0).show();
            return;
        }
        String substring = trim.substring(0, 1);
        String substring2 = trim.length() > 1 ? trim.substring(1) : "";
        int kangxiStroke = getKangxiStroke(substring.charAt(0));
        int i4 = 0;
        for (char c5 : substring2.toCharArray()) {
            i4 += getKangxiStroke(c5);
        }
        int i5 = kangxiStroke + 1;
        int kangxiStroke2 = (substring2.length() > 0 ? getKangxiStroke(substring2.charAt(0)) : 0) + kangxiStroke;
        int i6 = substring2.length() > 0 ? i4 + 1 : i5;
        int i7 = i5 + i4;
        int i8 = i5 % 10;
        if (i8 == 0) {
            i8 = 10;
        }
        int i9 = kangxiStroke2 % 10;
        if (i9 == 0) {
            i9 = 10;
        }
        int i10 = i6 % 10;
        if (i10 == 0) {
            i10 = 10;
        }
        int i11 = i7 % 10;
        int i12 = i11 != 0 ? i11 : 10;
        boolean[] isGoodNumber = isGoodNumber(new int[]{i8, i9, i10, 1, i12});
        int i13 = 70;
        for (boolean z4 : isGoodNumber) {
            if (z4) {
                i13 += 5;
            }
        }
        String elem = getElem(i8);
        String elem2 = getElem(i9);
        String elem3 = getElem(i10);
        if (isGenerate(elem, elem2) && isGenerate(elem2, elem3)) {
            i13 += 10;
        } else if (isGenerate(elem, elem2) || isGenerate(elem2, elem3)) {
            i13 += 5;
        }
        if (isClash(elem, elem2) || isClash(elem2, elem3)) {
            i13 -= 10;
        }
        if (i13 > 100) {
            i13 = 100;
        }
        String str = i13 >= 90 ? "⭐ 大吉之名" : i13 >= 80 ? "💚 吉名" : i13 >= 70 ? "💛 中吉" : i13 >= 60 ? "🧡 吉凶参半" : "❤️ 建议改名";
        ((TextView) findViewById(R.id.tv_score)).setText(i13 + "分");
        ((TextView) findViewById(R.id.tv_comment)).setText(str);
        ((TextView) findViewById(R.id.tv_strokes)).setText("姓 " + substring + "：" + kangxiStroke + "画\n名 " + substring2 + "：" + i4 + "画\n总笔画：" + (kangxiStroke + i4) + "画");
        ((TextView) findViewById(R.id.tv_grid)).setText("天格 " + i8 + "（" + elem + "）：祖先运，影响不大\n人格 " + i9 + "（" + elem2 + "）：主运，一生核心运势\n地格 " + i10 + "（" + elem3 + "）：前运，36岁前运势\n外格 1（" + getElem(1) + "）：社交运，人际关系\n总格 " + i12 + "（" + getElem(i12) + "）：后运，36岁后运势");
        StringBuilder sb = new StringBuilder();
        sb.append(isGoodNumber[1] ? "✅ 人格数理吉利，主运顺畅\n" : "⚠️ 人格数理需注意，主运有波动\n");
        sb.append(isGoodNumber[4] ? "✅ 总格数理吉利，晚年运势佳\n" : "⚠️ 总格数理需留意\n");
        if (isGenerate(elem, elem2)) {
            sb.append("✅ 天人相生，祖荫庇护\n");
        }
        if (isClash(elem2, elem3)) {
            sb.append("⚠️ 人地相克，青年时期需努力\n");
        }
        if (sb.length() == 0) {
            sb.append("名字中规中矩，可通过佩戴对应五行饰品补益运势。");
        }
        ((TextView) findViewById(R.id.tv_interpret)).setText(sb.toString());
        this.resultContainer.setVisibility(0);
        findViewById(R.id.btn_share).setOnClickListener(new p0(this, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$2() {
        ShareHelper.shareResult(this, this.resultContainer, "姓名分析");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculate$3(View view) {
        this.resultContainer.post(new h(9, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        TestBillingHelper.checkAndProceed(this, "姓名分析", new k(this, 6));
    }

    public String getElem(int i4) {
        int i5 = i4 % 5;
        return i5 != 0 ? (i5 == 1 || i5 == 2) ? "木" : (i5 == 3 || i5 == 4) ? "火" : (i5 == 7 || i5 == 8) ? "金" : i5 != 9 ? "土" : "水" : "水";
    }

    public int getKangxiStroke(char c5) {
        if (c5 == 29579 || c5 == 20911 || c5 == 30707 || c5 == 21490 || c5 == 29976 || c5 == 30000) {
            return 5;
        }
        if (c5 == 21016 || c5 == 23385 || c5 == 26417 || c5 == 35768 || c5 == 21326 || c5 == 20052) {
            return 6;
        }
        if (c5 == 26446 || c5 == 21556 || c5 == 20309 || c5 == 27784 || c5 == 23435 || c5 == 33487) {
            return 7;
        }
        if (c5 == 24352 || c5 == 26472 || c5 == 26519 || c5 == 21608 || c5 == 32599 || c5 == 37329) {
            return 8;
        }
        if (c5 == 36213 || c5 == 32993 || c5 == 26611 || c5 == 27573) {
            return 9;
        }
        if (c5 == 24464 || c5 == 39640 || c5 == 22799 || c5 == 21776) {
            return 10;
        }
        if (c5 == 40644 || c5 == 26361 || c5 == 26753 || c5 == 33831) {
            return 11;
        }
        if (c5 == 38472 || c5 == 26366 || c5 == 31243 || c5 == 24429) {
            return 12;
        }
        if (c5 == 26472 || c5 == 21494 || c5 == 33891 || c5 == 36158) {
            return 13;
        }
        if (c5 == 36213 || c5 == 35060 || c5 == 31649) {
            return 14;
        }
        if (c5 == 21016 || c5 == 28504 || c5 == 33931) {
            return 15;
        }
        if (c5 == 38472 || c5 == 38065 || c5 == 38414) {
            return 16;
        }
        if (c5 == 40857 || c5 == 35874 || c5 == 38047) {
            return 17;
        }
        if (c5 == 39759 || c5 == 39068) {
            return 18;
        }
        if (c5 == 32599 || c5 == 20851) {
            return 19;
        }
        if (c5 == 20005 || c5 == 33487) {
            return 20;
        }
        if (c5 < 19968 || c5 > 40959) {
            return 5;
        }
        return ((c5 - 19968) % 20) + 3;
    }

    public boolean isClash(String str, String str2) {
        return (str.equals("木") && str2.equals("土")) || (str.equals("土") && str2.equals("水")) || ((str.equals("水") && str2.equals("火")) || ((str.equals("火") && str2.equals("金")) || (str.equals("金") && str2.equals("木"))));
    }

    public boolean isGenerate(String str, String str2) {
        return (str.equals("木") && str2.equals("火")) || (str.equals("火") && str2.equals("土")) || ((str.equals("土") && str2.equals("金")) || ((str.equals("金") && str2.equals("水")) || (str.equals("水") && str2.equals("木"))));
    }

    public boolean[] isGoodNumber(int[] iArr) {
        boolean[] zArr = new boolean[5];
        int[] iArr2 = {1, 3, 5, 6, 7, 8, 11, 13, 15, 16, 17, 18, 21, 23, 24, 25, 29, 31, 32, 33, 35, 37, 39, 41, 45, 47, 48, 52, 57, 61, 63, 65, 67, 68, 81};
        for (int i4 = 0; i4 < 5; i4++) {
            int i5 = 0;
            while (true) {
                if (i5 < 35) {
                    if (iArr[i4] == iArr2[i5]) {
                        zArr[i4] = true;
                        break;
                    }
                    i5++;
                }
            }
        }
        return zArr;
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_name);
        int i4 = 1;
        if (getSupportActionBar() != null) {
            getSupportActionBar().m(true);
            getSupportActionBar().o("姓名分析");
        }
        this.etName = (EditText) findViewById(R.id.et_name);
        this.spYear = (Spinner) findViewById(R.id.sp_year);
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);
        int i5 = Calendar.getInstance().get(1) - 1949;
        String[] strArr = new String[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            strArr[i6] = (i6 + 1950) + "年";
        }
        this.spYear.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strArr));
        this.spYear.setSelection(Math.max(0, 40));
        findViewById(R.id.btn_calc).setOnClickListener(new p0(this, i4));
    }

    @Override // d.s
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
