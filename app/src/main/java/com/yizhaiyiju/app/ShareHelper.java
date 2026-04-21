package com.yizhaiyiju.app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class ShareHelper {
    private static final String QR_URL = "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=https%3A%2F%2F12zn.com%2Ftest%2F";

    private static Bitmap captureView(View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width <= 0 || height <= 0) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                view.measure(makeMeasureSpec, makeMeasureSpec);
                width = view.getMeasuredWidth();
                height = view.getMeasuredHeight();
            }
            if (width > 0 && height > 0) {
                Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(-66312);
                view.draw(canvas);
                return createBitmap;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doShare(Context context, View view, String str) {
        try {
            Bitmap captureView = captureView(view);
            if (captureView == null) {
                Toast.makeText(context, "生成图片失败", 0).show();
                return;
            }
            Uri saveBitmap = saveBitmap(context, captureView, str);
            if (saveBitmap == null) {
                Toast.makeText(context, "保存失败", 0).show();
                return;
            }
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/png");
            intent.putExtra("android.intent.extra.STREAM", saveBitmap);
            intent.putExtra("android.intent.extra.TEXT", "我在「一宅一句」测了" + str + "，快来试试！https://12zn.com");
            intent.addFlags(1);
            intent.addFlags(268435456);
            context.startActivity(Intent.createChooser(intent, "分享测试结果"));
        } catch (Exception e2) {
            Toast.makeText(context, "分享出错: " + e2.getMessage(), 0).show();
        }
    }

    private static int dpToPx(Context context, int i4) {
        return (int) TypedValue.applyDimension(1, i4, context.getResources().getDisplayMetrics());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadQRAndShare$1(Bitmap bitmap, ImageView imageView, View view, Context context, String str) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        view.post(new y0(context, view, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$loadQRAndShare$2(final ImageView imageView, final View view, final Context context, final String str) {
        Bitmap bitmap;
        q3.h0 g4;
        q3.j0 j0Var;
        try {
            q3.c0 c0Var = new q3.c0();
            c0Var.d(QR_URL);
            g4 = ApiHelper.getClient().a(c0Var.a()).g();
        } catch (Exception unused) {
        }
        if (g4.C() && (j0Var = g4.f4104g) != null) {
            c4.f y4 = ((q3.i0) j0Var).f4117c.y();
            bitmap = BitmapFactory.decodeStream(y4);
            try {
                y4.close();
            } catch (Exception unused2) {
            }
            final Bitmap bitmap2 = bitmap;
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.yizhaiyiju.app.v0
                @Override // java.lang.Runnable
                public final void run() {
                    ShareHelper.lambda$loadQRAndShare$1(bitmap2, imageView, view, context, str);
                }
            });
        }
        bitmap = null;
        final Bitmap bitmap22 = bitmap;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.yizhaiyiju.app.v0
            @Override // java.lang.Runnable
            public final void run() {
                ShareHelper.lambda$loadQRAndShare$1(bitmap22, imageView, view, context, str);
            }
        });
    }

    private static void loadQRAndShare(Context context, View view, ImageView imageView, String str) {
        new Thread(new e0(imageView, view, context, str)).start();
    }

    private static Uri saveBitmap(Context context, Bitmap bitmap, String str) {
        OutputStream openOutputStream;
        try {
            String str2 = "yizhaiyiju_" + str + "_" + System.currentTimeMillis() + ".png";
            if (Build.VERSION.SDK_INT < 29) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "一宅一句");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str2);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
                return Uri.fromFile(file2);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", str2);
            contentValues.put("mime_type", "image/png");
            contentValues.put("relative_path", Environment.DIRECTORY_PICTURES + "/一宅一句");
            Uri insert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (insert == null || (openOutputStream = context.getContentResolver().openOutputStream(insert)) == null) {
                return null;
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, openOutputStream);
            openOutputStream.close();
            return insert;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void shareResult(Context context, View view, String str) {
        if (view instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view;
            if (linearLayout.findViewWithTag("share_qr_section") == null) {
                View view2 = new View(context);
                view2.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(context, 40), dpToPx(context, 1)));
                view2.setBackgroundColor(-2239032);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpToPx(context, 40), dpToPx(context, 1));
                layoutParams.topMargin = dpToPx(context, 20);
                view2.setLayoutParams(layoutParams);
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setTag("share_qr_section");
                linearLayout2.setOrientation(1);
                linearLayout2.setGravity(17);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.topMargin = dpToPx(context, 16);
                linearLayout2.setLayoutParams(layoutParams2);
                ImageView imageView = new ImageView(context);
                int dpToPx = dpToPx(context, 100);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(dpToPx, dpToPx));
                imageView.setTag("share_qr_image");
                TextView textView = new TextView(context);
                textView.setText("扫码测更多 · 一宅一句");
                textView.setTextColor(-6252400);
                textView.setTextSize(2, 11.0f);
                textView.setGravity(17);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams3.topMargin = dpToPx(context, 8);
                textView.setLayoutParams(layoutParams3);
                TextView textView2 = new TextView(context);
                textView2.setText("12zn.com");
                textView2.setTextColor(-5862850);
                textView2.setTextSize(2, 12.0f);
                textView2.setTypeface(null, 1);
                textView2.setGravity(17);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams4.topMargin = dpToPx(context, 4);
                textView2.setLayoutParams(layoutParams4);
                linearLayout.addView(view2);
                linearLayout.addView(linearLayout2);
                linearLayout2.addView(imageView);
                linearLayout2.addView(textView);
                linearLayout2.addView(textView2);
                loadQRAndShare(context, view, imageView, str);
                return;
            }
        }
        doShare(context, view, str);
    }
}
