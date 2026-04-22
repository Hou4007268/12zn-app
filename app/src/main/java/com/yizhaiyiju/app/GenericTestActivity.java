package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.yizhaiyiju.app.TestData;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class GenericTestActivity extends d.s {
    private List<TestData.Question> questions;
    private View scrollQuestions;
    private String testId;
    private int currentIndex = 0;
    private int totalScore = 0;
    private int maxScore = 0;

    private int calcMaxPossible() {
        Iterator<TestData.Question> it = this.questions.iterator();
        int i4 = 0;
        while (it.hasNext()) {
            int i5 = 0;
            for (int i6 : it.next().scores) {
                if (i6 > i5) {
                    i5 = i6;
                }
            }
            i4 += i5;
        }
        return i4;
    }

    private String[] calculateResult() {
        double d5;
        int calcMaxPossible = calcMaxPossible();
        d5 = calcMaxPossible > 0 ? this.totalScore / calcMaxPossible : 0.0d;
        String str = this.testId;
        str.getClass();
        str.hashCode();
        switch (str) {
            case "mental_age":
                int i4 = ((int) (d5 * 30.0d)) + 18;
                String[] strArr = new String[3];
                strArr[0] = "心理年龄：" + i4 + "岁";
                strArr[1] = "🧠";
                StringBuilder j4 = new StringBuilder("你的心智成熟度相当于");
                j4.append(i4);
                j4.append("岁。");
                j4.append(i4 > 35 ? "你比同龄人更加成熟稳重。" : "你保持着年轻的心态和活力。");
                strArr[2] = j4.toString();
                return strArr;
            case "lucky_color":
                int i5 = this.totalScore;
                String[][] strArr2 = TestData.LUCKY_COLORS;
                String[] strArr3 = strArr2[i5 % strArr2.length];
                return new String[]{"你的幸运色", strArr3[0], strArr3[1]};
            case "mbti":
                int i6 = this.totalScore;
                int i7 = (i6 % 100) / 10;
                int i8 = (i6 % 1000) / 100;
                int i9 = (i6 % 10000) / 1000;
                int i10 = i6 / 10000;
                StringBuilder sb = new StringBuilder();
                sb.append(i7 >= 1 ? "I" : "E");
                sb.append(i8 >= 1 ? "N" : "S");
                sb.append(i9 >= 1 ? "F" : "T");
                sb.append(i10 >= 1 ? "P" : "J");
                String sb2 = sb.toString();
                String[][] strArr4 = {new String[]{"ISTJ", "务实可靠", "你做事认真负责，注重细节和规则，是最值得信赖的类型。"}, new String[]{"ISFJ", "温暖守护者", "你细心体贴，默默关心身边的人，是团队中最温暖的存在。"}, new String[]{"INFJ", "洞察者", "你有深刻的洞察力和强烈的理想主义，能看到别人看不到的东西。"}, new String[]{"INTJ", "战略家", "你独立自主，善于长远规划，是天生的战略思考者。"}, new String[]{"ISTP", "灵活工匠", "你喜欢动手实践，冷静分析问题，在危机中反而最沉着。"}, new String[]{"ISFP", "温柔艺术家", "你感性细腻，追求美和自由，用行动而非言语表达爱。"}, new String[]{"INFP", "理想主义者", "你内心丰富，追求意义和价值，有着不为人知的强大力量。"}, new String[]{"INTP", "逻辑学家", "你好奇心强，喜欢钻研原理，是天生的问题解决者。"}, new String[]{"ESTP", "行动派", "你精力充沛，喜欢冒险和挑战，活在当下享受生活。"}, new String[]{"ESFP", "活力达人", "你乐观开朗，是人群中的开心果，感染力极强。"}, new String[]{"ENFP", "追梦人", "你热情有创意，善于激励他人，永远对新事物充满好奇。"}, new String[]{"ENTP", "辩论家", "你聪明机智，喜欢挑战传统，总能找到创新的解决方案。"}, new String[]{"ESTJ", "管理者", "你有很强的组织能力，注重效率和结果，是天生的领导者。"}, new String[]{"ESFJ", "热心助人者", "你重视和谐，善于照顾他人感受，是团队凝聚力的核心。"}, new String[]{"ENFJ", "导师", "你有感染力，善于引导和启发他人，天生适合做领导和教育。"}, new String[]{"ENTJ", "指挥官", "你果断有力，善于制定战略和推动执行，是天生的决策者。"}};
                for (int i11 = 0; i11 < 16; i11++) {
                    String[] strArr5 = strArr4[i11];
                    if (strArr5[0].equals(sb2)) {
                        return new String[]{sb2 + " · " + strArr5[1], "🎭", strArr5[2]};
                    }
                }
                return new String[]{sb2, "🎭", new StringBuilder().append("你的性格类型是").append(sb2).append("，每种类型都有独特的优势。").toString()};
            case "name":
                int i12 = ((int) (d5 * 35.0d)) + 60;
                return new String[]{"名字评分：" + i12 + "分", "✨", i12 >= 90 ? "名字非常好！五行搭配得当，音韵和谐，寓意深远，对运势有积极加持。" : i12 >= 80 ? "名字不错，五行基本平衡，音韵流畅。稍加调整可以更完美。" : i12 >= 70 ? "名字尚可，但五行搭配有些偏颇，音韵上也有优化空间。建议咨询师傅做微调。" : "名字有待改善，五行存在明显偏差，可能对运势有一定影响。建议考虑调整。"};
            case "financial":
                return d5 > 0.7d ? new String[]{"财商很高！", "💰", "你有很好的理财意识和投资直觉，建议继续保持学习。"} : d5 > 0.5d ? new String[]{"财商中等", "📊", "你有一定的理财基础，但还有提升空间。建议多学习投资知识。"} : new String[]{"需要提升财商", "📚", "建议从基础理财开始学习，建立财务规划意识。"};
            case "romance":
                return d5 > 0.8d ? new String[]{"桃花运爆棚！", "🌸💕", "你的爱情运势非常好，最近可能会遇到心仪的对象，或者现有的感情会更进一步。"} : d5 > 0.6d ? new String[]{"桃花运不错", "🌸", "你的爱情运势较好，保持开放的心态，缘分可能就在不远处。"} : d5 > 0.4d ? new String[]{"桃花运一般", "🌿", "目前爱情运势平稳，建议多参加社交活动，拓展交际圈。"} : new String[]{"需要耐心等待", "🌱", "桃花暂时还没开，但请不要灰心。趁这段时间提升自己，缘分自然会来。"};
            default:
                return new String[]{"测试完成", "🎉", "感谢参与测试！"};
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        restart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(String str, View view) {
        View findViewById = findViewById(R.id.share_card);
        if (str == null) {
            str = "测试";
        }
        ShareHelper.shareResult(this, findViewById, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showQuestion$4(int i4, View view) {
        this.totalScore += i4;
        this.currentIndex++;
        lambda$onCreate$3();
    }

    private void loadQuestions() {
        List<TestData.Question> mentalAgeQuestions;
        String str = this.testId;
        str.getClass();
        switch (str) {
            case "mental_age":
                mentalAgeQuestions = TestData.getMentalAgeQuestions();
                break;
            case "lucky_color":
                mentalAgeQuestions = TestData.getLuckyColorQuestions();
                break;
            case "mbti":
                mentalAgeQuestions = TestData.getMBTIQuestions();
                break;
            case "name":
                mentalAgeQuestions = TestData.getNameQuestions();
                break;
            case "financial":
                mentalAgeQuestions = TestData.getFinancialQuestions();
                break;
            case "romance":
            default:
                mentalAgeQuestions = TestData.getRomanceQuestions();
                break;
        }
        this.questions = mentalAgeQuestions;
        ((ProgressBar) findViewById(R.id.progress_bar)).setMax(this.questions.size());
    }

    private void restart() {
        this.currentIndex = 0;
        this.totalScore = 0;
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
        TextView textView = (TextView) findViewById(R.id.tv_progress);
        StringBuilder sb = new StringBuilder();
        int i4 = 1;
        sb.append(this.currentIndex + 1);
        sb.append("/");
        sb.append(this.questions.size());
        textView.setText(sb.toString());
        ((ProgressBar) findViewById(R.id.progress_bar)).setProgress(this.currentIndex + 1);
        ((TextView) findViewById(R.id.tv_question)).setText(question.question);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.options_container);
        linearLayout.removeAllViews();
        for (int i5 = 0; i5 < question.options.length; i5++) {
            TextView textView2 = new TextView(this);
            textView2.setText(question.options[i5]);
            textView2.setTextColor(-12765904);
            textView2.setTextSize(15.0f);
            textView2.setPadding(24, 20, 24, 20);
            textView2.setBackgroundResource(R.drawable.bg_card);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, 12);
            textView2.setLayoutParams(layoutParams);
            textView2.setOnClickListener(new g0(this, question.scores[i5], i4));
            linearLayout.addView(textView2);
        }
    }

    private void showResult() {
        this.scrollQuestions.setVisibility(8);
        findViewById(R.id.result_container).setVisibility(0);
        String[] calculateResult = calculateResult();
        ((TextView) findViewById(R.id.tv_result_title)).setText(calculateResult[0]);
        ((TextView) findViewById(R.id.tv_result_emoji)).setText(calculateResult[1]);
        ((TextView) findViewById(R.id.tv_result_desc)).setText(calculateResult[2]);
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_generic_test);
        this.testId = getIntent().getStringExtra("test_id");
        String stringExtra = getIntent().getStringExtra("test_name");
        final int i4 = 0;
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.h0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ GenericTestActivity f2336f;

            {
                this.f2336f = GenericTestActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                GenericTestActivity genericTestActivity = this.f2336f;
                switch (i5) {
                    case 0:
                        genericTestActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        genericTestActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        this.scrollQuestions = findViewById(R.id.scroll_questions);
        final int i5 = 1;
        findViewById(R.id.btn_restart).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.h0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ GenericTestActivity f2336f;

            {
                this.f2336f = GenericTestActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                GenericTestActivity genericTestActivity = this.f2336f;
                switch (i52) {
                    case 0:
                        genericTestActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        genericTestActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText(stringExtra != null ? stringExtra : "测试");
        findViewById(R.id.btn_share).setOnClickListener(new j(this, 6, stringExtra));
        loadQuestions();
        TestBillingHelper.checkAndProceed(this, stringExtra, new k(this, 2));
    }
}
