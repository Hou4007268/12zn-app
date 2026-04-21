package com.yizhaiyiju.app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes.dex */
public class CalendarActivity extends d.s {
    private static final String[] TIANGAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] DIZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] SHENGXIAO = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    private static final String[] WUXING = {"木", "木", "火", "火", "土", "土", "金", "金", "水", "水"};
    private static final String[][] YUE_GAN = {new String[]{"丙", "丁", "戊", "己", "庚", "辛", "壬", "癸", "甲", "乙", "丙", "丁"}, new String[]{"戊", "己", "庚", "辛", "壬", "癸", "甲", "乙", "丙", "丁", "戊", "己"}, new String[]{"庚", "辛", "壬", "癸", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛"}, new String[]{"壬", "癸", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"}, new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸", "甲", "乙"}};
    private static final String[] JIEQI = {"小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"};
    private static final int[][] JIEQI_DATES = {new int[]{1, 6}, new int[]{1, 20}, new int[]{2, 4}, new int[]{2, 19}, new int[]{3, 6}, new int[]{3, 21}, new int[]{4, 5}, new int[]{4, 20}, new int[]{5, 6}, new int[]{5, 21}, new int[]{6, 6}, new int[]{6, 21}, new int[]{7, 7}, new int[]{7, 23}, new int[]{8, 7}, new int[]{8, 23}, new int[]{9, 8}, new int[]{9, 23}, new int[]{10, 8}, new int[]{10, 24}, new int[]{11, 7}, new int[]{11, 22}, new int[]{12, 7}, new int[]{12, 22}};

    private void displayDate(int i4, int i5, int i6) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(i4, i5, i6);
        int i7 = i5 + 1;
        ((TextView) findViewById(R.id.tv_solar_date)).setText(String.format(Locale.CHINA, "%d年%d月%d日 %s", Integer.valueOf(i4), Integer.valueOf(i7), Integer.valueOf(i6), new String[]{"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"}[calendar.get(7)]));
        ((TextView) findViewById(R.id.tv_lunar_date)).setText(getLunarDate(i4, i7, i6));
        int i8 = i4 - 4;
        int i9 = i8 % 10;
        int i10 = i8 % 12;
        TextView textView = (TextView) findViewById(R.id.tv_year_ganzhi);
        StringBuilder sb = new StringBuilder();
        String[] strArr = TIANGAN;
        sb.append(strArr[i9]);
        String[] strArr2 = DIZHI;
        sb.append(strArr2[i10]);
        sb.append("年 · ");
        sb.append(SHENGXIAO[i10]);
        sb.append("年 · ");
        String[] strArr3 = WUXING;
        sb.append(strArr3[i9]);
        sb.append("年");
        textView.setText(sb.toString());
        ((TextView) findViewById(R.id.tv_month_ganzhi)).setText(YUE_GAN[i9 % 5][i5] + strArr2[(i5 + 2) % 12] + "月");
        int i11 = i4 + (-1900);
        int i12 = (i5 * 30) + (i11 / 4) + (i11 * 5) + i6;
        int i13 = (i12 + (-30)) % 10;
        int i14 = (i12 - 1) % 12;
        ((TextView) findViewById(R.id.tv_day_ganzhi)).setText(strArr[i13] + strArr2[i14] + "日 · " + strArr3[i13] + "日");
        TextView textView2 = (TextView) findViewById(R.id.tv_jieqi);
        StringBuilder sb2 = new StringBuilder("节气：");
        sb2.append(getCurrentJieQi(i5, i6));
        textView2.setText(sb2.toString());
        String[] yiJi = getYiJi(i13, i14);
        ((TextView) findViewById(R.id.tv_yi)).setText("宜：" + yiJi[0]);
        ((TextView) findViewById(R.id.tv_ji)).setText("忌：" + yiJi[1]);
        ((TextView) findViewById(R.id.tv_chong)).setText("煞方：" + getSha(i14));
    }

    private String getCurrentJieQi(int i4, int i5) {
        int i6 = i4 * 2;
        if (i5 >= JIEQI_DATES[i6][1]) {
            return JIEQI[i6];
        }
        int i7 = i6 - 1;
        if (i7 < 0) {
            i7 = 23;
        }
        return JIEQI[i7];
    }

    private int getDaysInMonth(int i4, int i5) {
        return i5 != 2 ? (i5 == 4 || i5 == 6 || i5 == 9 || i5 == 11) ? 30 : 31 : isLeapYear(i4) ? 29 : 28;
    }

    private String getLunarDate(int i4, int i5, int i6) {
        String[] strArr = {"正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};
        String[] strArr2 = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};
        Calendar calendar = Calendar.getInstance();
        calendar.set(i4, i5 - 1, i6);
        long timeInMillis = calendar.getTimeInMillis() / 86400000;
        int i7 = (int) (timeInMillis % 30);
        if (i7 <= 0) {
            i7 += 30;
        }
        int i8 = (int) ((timeInMillis / 30) % 12);
        if (i8 <= 0) {
            i8 += 12;
        }
        return "农历" + strArr[i8 - 1] + "月" + strArr2[i7 - 1];
    }

    private String getSha(int i4) {
        return o.h.a(new StringBuilder(), new String[]{"东", "北", "西", "南", "东", "北", "西", "南", "东", "北", "西", "南"}[i4], "方");
    }

    private String[] getYiJi(int i4, int i5) {
        return new String[][]{new String[]{"祭祀,祈福,出行", "开市,动土,嫁娶"}, new String[]{"嫁娶,纳采,出行", "开仓,出货,安葬"}, new String[]{"开市,交易,入宅", "修造,动土,安床"}, new String[]{"纳采,嫁娶,祈福", "开市,破土,出行"}, new String[]{"祭祀,入学,嫁娶", "开仓,掘井,安葬"}, new String[]{"祈福,求嗣,开市", "嫁娶,出行,安床"}, new String[]{"修造,动土,入宅", "开市,嫁娶,出行"}, new String[]{"祭祀,祈福,纳采", "开仓,出货,破土"}, new String[]{"出行,入学,开市", "嫁娶,动土,安葬"}, new String[]{"嫁娶,祭祀,入宅", "开市,出行,动土"}, new String[]{"祈福,求嗣,开市", "修造,嫁娶,安葬"}, new String[]{"纳采,嫁娶,入宅", "开市,动土,出行"}}[(i4 + i5) % 12];
    }

    private boolean isLeapYear(int i4) {
        return (i4 % 4 == 0 && i4 % 100 != 0) || i4 % 400 == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(DatePicker datePicker, int i4, int i5, int i6) {
        displayDate(i4, i5, i6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(View view) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() { // from class: com.yizhaiyiju.app.n
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i4, int i5, int i6) {
                CalendarActivity.this.lambda$onCreate$1(datePicker, i4, i5, i6);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)).show();
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_calendar);
        final int i4 = 0;
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.m

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ CalendarActivity f2355f;

            {
                this.f2355f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                CalendarActivity calendarActivity = this.f2355f;
                switch (i5) {
                    case 0:
                        calendarActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        calendarActivity.lambda$onCreate$2(view);
                        break;
                }
            }
        });
        final int i5 = 1;
        findViewById(R.id.btn_pick_date).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.m

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ CalendarActivity f2355f;

            {
                this.f2355f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                CalendarActivity calendarActivity = this.f2355f;
                switch (i52) {
                    case 0:
                        calendarActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        calendarActivity.lambda$onCreate$2(view);
                        break;
                }
            }
        });
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int i6 = calendar.get(1);
        int i7 = calendar.get(2);
        int i8 = calendar.get(5);
        int i9 = i7 + 1;
        ((TextView) findViewById(R.id.tv_solar_date)).setText(String.format(Locale.CHINA, "%d年%d月%d日 %s", Integer.valueOf(i6), Integer.valueOf(i9), Integer.valueOf(i8), new String[]{"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"}[calendar.get(7)]));
        ((TextView) findViewById(R.id.tv_lunar_date)).setText(getLunarDate(i6, i9, i8));
        int i10 = i6 - 4;
        int i11 = i10 % 10;
        int i12 = i10 % 12;
        StringBuilder sb = new StringBuilder();
        sb.append(TIANGAN[i11]);
        String[] strArr = DIZHI;
        sb.append(strArr[i12]);
        String sb2 = sb.toString();
        String str = WUXING[i11];
        String str2 = SHENGXIAO[i12];
        ((TextView) findViewById(R.id.tv_year_ganzhi)).setText(sb2 + "年 · " + str2 + "年 · " + str + "年");
        StringBuilder sb3 = new StringBuilder();
        sb3.append(YUE_GAN[i11 % 5][i7]);
        sb3.append(strArr[(i7 + 2) % 12]);
        String sb4 = sb3.toString();
        ((TextView) findViewById(R.id.tv_month_ganzhi)).setText(sb4 + "月");
        int i13 = i6 + (-1900);
        long j4 = (long) ((i13 / 4) + (i13 * 365));
        int i14 = 0;
        while (i14 < i7) {
            i14++;
            j4 += getDaysInMonth(i6, i14);
        }
        long j5 = j4 + (i8 - 1) + 10;
        int i15 = (int) (j5 % 10);
        int i16 = (int) (j5 % 12);
        String str3 = TIANGAN[i15] + DIZHI[i16];
        String str4 = WUXING[i15];
        ((TextView) findViewById(R.id.tv_day_ganzhi)).setText(str3 + "日 · " + str4 + "日");
        String currentJieQi = getCurrentJieQi(i7, i8);
        TextView textView = (TextView) findViewById(R.id.tv_jieqi);
        StringBuilder sb5 = new StringBuilder("当前节气：");
        sb5.append(currentJieQi);
        textView.setText(sb5.toString());
        TextView textView2 = (TextView) findViewById(R.id.tv_yi);
        TextView textView3 = (TextView) findViewById(R.id.tv_ji);
        String[] yiJi = getYiJi(i15, i16);
        textView2.setText("宜：" + yiJi[0]);
        textView3.setText("忌：" + yiJi[1]);
        ((TextView) findViewById(R.id.tv_chong)).setText("冲" + SHENGXIAO[(i16 + 6) % 12] + " · 煞" + getSha(i16));
    }
}
