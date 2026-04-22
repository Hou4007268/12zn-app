package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.yizhaiyiju.app.TestData;
import java.util.List;

/* loaded from: classes.dex */
public class WuxingTestActivity extends d.s {
    private View scrollQuestions;
    private List<TestData.Question> questions = TestData.getWuxingQuestions();
    private int currentIndex = 0;
    private int[] scores = new int[5];

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        restart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(View view) {
        ShareHelper.shareResult(this, findViewById(R.id.share_card), "五行测试");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showQuestion$4(int i4, View view) {
        int[] iArr = this.scores;
        iArr[i4] = iArr[i4] + 1;
        this.currentIndex++;
        lambda$onCreate$3();
    }

    private void restart() {
        this.currentIndex = 0;
        this.scores = new int[5];
        this.scrollQuestions.setVisibility(0);
        findViewById(R.id.result_container).setVisibility(8);
        lambda$onCreate$3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showQuestion, reason: merged with bridge method [inline-methods] */
    public void lambda$onCreate$3() {
        if (this.currentIndex >= this.questions.size()) {
            showResult();
            return;
        }
        TestData.Question question = this.questions.get(this.currentIndex);
        ((TextView) findViewById(R.id.tv_progress)).setText((this.currentIndex + 1) + "/" + this.questions.size());
        ((ProgressBar) findViewById(R.id.progress_bar)).setProgress(this.currentIndex + 1);
        ((TextView) findViewById(R.id.tv_question)).setText(question.question);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.options_container);
        linearLayout.removeAllViews();
        for (int i4 = 0; i4 < question.options.length; i4++) {
            TextView textView = new TextView(this);
            textView.setText(question.options[i4]);
            textView.setTextColor(-12765904);
            textView.setTextSize(15.0f);
            textView.setPadding(24, 20, 24, 20);
            textView.setBackgroundResource(R.drawable.bg_card);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, 12);
            textView.setLayoutParams(layoutParams);
            textView.setOnClickListener(new g0(this, question.scores[i4], 2));
            linearLayout.addView(textView);
        }
    }

    private void showResult() {
        int i4 = 0;
        for (int i5 = 1; i5 < 5; i5++) {
            int[] iArr = this.scores;
            if (iArr[i5] > iArr[i4]) {
                i4 = i5;
            }
        }
        this.scrollQuestions.setVisibility(8);
        findViewById(R.id.result_container).setVisibility(0);
        ((TextView) findViewById(R.id.tv_result_title)).setText("你的五行属性");
        ((TextView) findViewById(R.id.tv_result_element)).setText(TestData.ELEMENT_EMOJIS[i4] + " " + TestData.ELEMENTS[i4]);
        ((TextView) findViewById(R.id.tv_result_desc)).setText(TestData.ELEMENT_DESC[i4]);
        ((TextView) findViewById(R.id.tv_result_advice)).setText(TestData.ELEMENT_ADVICE[i4]);
        ((WuxingBarView) findViewById(R.id.wuxing_chart)).setScores(this.scores);
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wuxing_test);
        final int i4 = 0;
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.c1

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ WuxingTestActivity f2308f;

            {
                this.f2308f = WuxingTestActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                WuxingTestActivity wuxingTestActivity = this.f2308f;
                switch (i5) {
                    case 0:
                        wuxingTestActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        wuxingTestActivity.lambda$onCreate$1(view);
                        break;
                    default:
                        wuxingTestActivity.lambda$onCreate$2(view);
                        break;
                }
            }
        });
        this.scrollQuestions = findViewById(R.id.scroll_questions);
        final int i5 = 1;
        findViewById(R.id.btn_restart).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.c1

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ WuxingTestActivity f2308f;

            {
                this.f2308f = WuxingTestActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                WuxingTestActivity wuxingTestActivity = this.f2308f;
                switch (i52) {
                    case 0:
                        wuxingTestActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        wuxingTestActivity.lambda$onCreate$1(view);
                        break;
                    default:
                        wuxingTestActivity.lambda$onCreate$2(view);
                        break;
                }
            }
        });
        final int i6 = 2;
        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.c1

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ WuxingTestActivity f2308f;

            {
                this.f2308f = WuxingTestActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i6;
                WuxingTestActivity wuxingTestActivity = this.f2308f;
                switch (i52) {
                    case 0:
                        wuxingTestActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        wuxingTestActivity.lambda$onCreate$1(view);
                        break;
                    default:
                        wuxingTestActivity.lambda$onCreate$2(view);
                        break;
                }
            }
        });
        TestBillingHelper.checkAndProceed(this, "五行测试", new k(this, 7));
    }
}
