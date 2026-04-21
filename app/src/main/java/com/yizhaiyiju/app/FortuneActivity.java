package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

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

    private void showFortune(int i4) {
        this.resultContainer.setVisibility(0);
        String[] strArr = TestData.ZODIAC_FORTUNES[i4];
        this.tvFortuneTitle.setText(strArr[0] + " 今日运势");
        this.tvOverall.setText(strArr[1]);
        try {
            this.ratingMoney.setRating(Integer.parseInt(strArr[2]));
            this.ratingLove.setRating(Integer.parseInt(strArr[3]));
            this.ratingCareer.setRating(Integer.parseInt(strArr[4]));
            this.tvLucky.setText(strArr[5]);
        } catch (Exception unused) {
            this.ratingMoney.setRating(3.0f);
            this.ratingLove.setRating(3.0f);
            this.ratingCareer.setRating(3.0f);
            this.tvLucky.setText("");
        }
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fortune);
        findViewById(R.id.btn_back).setOnClickListener(new i(3, this));
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
            textView.setText(TestData.ZODIAC_EMOJIS[i5] + "\n" + TestData.ZODIAC_NAMES[i5]);
            textView.setTextSize(14.0f);
            textView.setTextColor(getResources().getColor(R.color.text_white));
            textView.setGravity(17);
            textView.setPadding(16, 16, 16, 16);
            textView.setBackgroundResource(R.drawable.bg_card);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = -2;
            layoutParams.columnSpec = GridLayout.spec(i5 % 4, 1.0f);
            layoutParams.rowSpec = GridLayout.spec(i5 / 4);
            layoutParams.setMargins(6, 6, 6, 6);
            textView.setLayoutParams(layoutParams);
            textView.setOnClickListener(new g0(this, i5, i4));
            gridLayout.addView(textView);
        }
    }
}
