package com.yizhaiyiju.app;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FortuneActivity extends d.s {
    private RatingBar ratingCareer;
    private RatingBar ratingLove;
    private RatingBar ratingMoney;
    private LinearLayout resultContainer;
    private TextView tvFortuneTitle;
    private TextView tvLucky;
    private TextView tvOverall;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(int i4, View view) {
        showFortune(i4);
    }

    private int dp(int i4) {
        return (int) TypedValue.applyDimension(1, i4, getResources().getDisplayMetrics());
    }

    private void showFortune(int i4) {
        this.resultContainer.setVisibility(0);
        String[] strArr = TestData.ZODIAC_FORTUNES[i4];
        this.tvFortuneTitle.setText(TestData.ZODIAC_NAMES[i4] + " 今日运势");
        this.tvOverall.setText(strArr[1]);
        try {
            this.ratingMoney.setRating(Integer.parseInt(strArr[2]));
            this.ratingLove.setRating(Integer.parseInt(strArr[3]));
            this.ratingCareer.setRating(Integer.parseInt(strArr[4]));
            this.tvLucky.setText(strArr[5]);
        saveTestResult("生肖运势", TestData.ZODIAC_NAMES[i4] + " 今日运势", 
            "综合运势：" + strArr[1] + "\n财运评分：" + strArr[2] + "/5 爱情评分：" + strArr[3] + "/5 事业评分：" + strArr[4] + "/5\n幸运：" + strArr[5]);
        } catch (Exception unused) {
            this.ratingMoney.setRating(3.0f);
            this.ratingLove.setRating(3.0f);
            this.ratingCareer.setRating(3.0f);
            this.tvLucky.setText("");
        }
    }


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

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fortune);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { lambda$onCreate$0(v); } });
        this.resultContainer = (LinearLayout) findViewById(R.id.result_container);
        this.tvFortuneTitle = (TextView) findViewById(R.id.tv_fortune_title);
        this.tvOverall = (TextView) findViewById(R.id.tv_overall);
        this.tvLucky = (TextView) findViewById(R.id.tv_lucky);
        this.ratingMoney = (RatingBar) findViewById(R.id.rating_money);
        this.ratingLove = (RatingBar) findViewById(R.id.rating_love);
        this.ratingCareer = (RatingBar) findViewById(R.id.rating_career);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.zodiac_grid);
        int i4 = 0;
        for (int i5 = 0; i5 < 12; i5++) {
            TextView textView = new TextView(this);
            textView.setText(TestData.ZODIAC_NAMES[i5]);
            textView.setTextSize(16.0f);
            textView.setTextColor(getResources().getColor(R.color.text_primary));
            textView.setGravity(Gravity.CENTER);
            int dp = dp(14);
            textView.setPadding(dp, dp, dp, dp);
            textView.setBackgroundResource(R.drawable.bg_card);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = -2;
            layoutParams.columnSpec = GridLayout.spec(i5 % 4, 1.0f);
            layoutParams.rowSpec = GridLayout.spec(i5 / 4);
            int dp2 = dp(6);
            layoutParams.setMargins(dp2, dp2, dp2, dp2);
            textView.setLayoutParams(layoutParams);
            textView.setMinHeight(dp(64));
            final int zodiacIdx = i5;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) { lambda$onCreate$1(zodiacIdx, v); }
            });
            gridLayout.addView(textView);
        }
    }
}
