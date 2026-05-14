package com.yizhaiyiju.app;

import android.app.Activity;
import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public final class InsetsHelper {
    private InsetsHelper() {}
    public static void applyTopInset(Activity activity, int viewId) {
        if (activity == null) return;
        applyTopInset(activity.findViewById(viewId));
    }
    public static void applyTopInset(View target) {
        if (target == null) return;
        final int left = target.getPaddingLeft();
        final int top = target.getPaddingTop();
        final int right = target.getPaddingRight();
        final int bottom = target.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(target, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(left, top + insets.top, right, bottom);
            return windowInsets;
        });
        ViewCompat.requestApplyInsets(target);
    }
}
