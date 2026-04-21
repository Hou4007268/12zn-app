package com.yizhaiyiju.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class WuxingBarView extends View {
    private final Paint barBgPaint;
    private final Paint barPaint;
    private final RectF barRect;
    private final int[] colors;
    private final String[] emojis;
    private final String[] labels;
    private int maxScore;
    private final Paint pctPaint;
    private int[] scores;
    private final Paint textPaint;

    public WuxingBarView(Context context) {
        super(context);
        this.labels = new String[]{"金", "木", "水", "火", "土"};
        this.colors = new int[]{-4153266, -10580895, -11896139, -4172998, -6587058};
        this.emojis = new String[]{"🥇", "🌲", "💧", "🔥", "🏔️"};
        this.scores = new int[]{0, 0, 0, 0, 0};
        this.maxScore = 1;
        this.barBgPaint = new Paint(1);
        this.barPaint = new Paint(1);
        this.textPaint = new Paint(1);
        this.pctPaint = new Paint(1);
        this.barRect = new RectF();
        init();
    }

    private void init() {
        this.barBgPaint.setColor(-1514794);
        this.barBgPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setColor(-12765904);
        this.textPaint.setTextSize(36.0f);
        this.pctPaint.setColor(-8753046);
        this.pctPaint.setTextSize(28.0f);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = ((getWidth() - 120.0f) - 80.0f) - 48.0f;
        int i4 = 0;
        for (int i5 : this.scores) {
            i4 += i5;
        }
        if (i4 == 0) {
            i4 = 1;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            float f4 = (i6 * 68.0f) + 16.0f;
            this.textPaint.setTextAlign(Paint.Align.LEFT);
            float f5 = 36.0f + f4;
            canvas.drawText(this.emojis[i6] + " " + this.labels[i6], 16.0f, f5, this.textPaint);
            float f6 = f4 + 10.0f;
            float f7 = 136.0f + width;
            float f8 = f4 + 46.0f;
            this.barRect.set(136.0f, f6, f7, f8);
            canvas.drawRoundRect(this.barRect, 10.0f, 10.0f, this.barBgPaint);
            float f9 = this.scores[i6] / i4;
            this.barPaint.setColor(this.colors[i6]);
            if (f9 > 0.0f) {
                this.barRect.set(136.0f, f6, (width * f9) + 136.0f, f8);
                canvas.drawRoundRect(this.barRect, 10.0f, 10.0f, this.barPaint);
            }
            this.pctPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(Math.round(f9 * 100.0f) + "%", f7 + 8.0f, f5, this.pctPaint);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i4, int i5) {
        setMeasuredDimension(View.MeasureSpec.getSize(i4), 420);
    }

    public void setScores(int[] iArr) {
        this.scores = iArr;
        this.maxScore = 1;
        for (int i4 : iArr) {
            if (i4 > this.maxScore) {
                this.maxScore = i4;
            }
        }
        invalidate();
    }

    public WuxingBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.labels = new String[]{"金", "木", "水", "火", "土"};
        this.colors = new int[]{-4153266, -10580895, -11896139, -4172998, -6587058};
        this.emojis = new String[]{"🥇", "🌲", "💧", "🔥", "🏔️"};
        this.scores = new int[]{0, 0, 0, 0, 0};
        this.maxScore = 1;
        this.barBgPaint = new Paint(1);
        this.barPaint = new Paint(1);
        this.textPaint = new Paint(1);
        this.pctPaint = new Paint(1);
        this.barRect = new RectF();
        init();
    }
}
