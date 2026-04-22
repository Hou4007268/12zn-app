package com.yizhaiyiju.app;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UpdateHelper {
    private static final String TAG = "UpdateHelper";
    private static long downloadId = -1;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private static final boolean IN_APP_UPDATE_ENABLED = true;

    public static void checkUpdate(Context context) {
        if (!IN_APP_UPDATE_ENABLED) {
            return;
        }
        doCheck(context, false);
    }

    public static void checkUpdateManual(Context context) {
        doCheck(context, true);
    }

    private static void doCheck(Context context, boolean isManual) {
        new Thread(() -> doCheckImpl(context, isManual)).start();
    }

    static void lambda$doCheck$1(Context context) {
        doCheckImpl(context, false);
    }

    static void lambda$doCheck$2(Context context) {
        doCheckImpl(context, true);
    }

    private static void doCheckImpl(Context context, boolean isManual) {
        try {
            Request request = new Request.Builder()
                    .url("https://12zn.com/api/app/version")
                    .build();
            Response response = httpClient.newCall(request).execute();

            if (!response.isSuccessful()) {
                Log.w(TAG, "Version check failed: HTTP " + response.code());
                if (isManual && context instanceof Activity) {
                    mainHandler.post(() -> Toast.makeText(context, "\u68c0\u67e5\u66f4\u65b0\u5931\u8d25", Toast.LENGTH_SHORT).show());
                }
                return;
            }

            ResponseBody body = response.body();
            if (body == null) {
                if (isManual && context instanceof Activity) {
                    mainHandler.post(() -> Toast.makeText(context, "\u68c0\u67e5\u66f4\u65b0\u5931\u8d25", Toast.LENGTH_SHORT).show());
                }
                return;
            }

            String jsonStr = body.string();
            JSONObject json = new JSONObject(jsonStr);

            if (!json.optBoolean("success", false)) {
                if (isManual && context instanceof Activity) {
                    mainHandler.post(() -> Toast.makeText(context, "\u68c0\u67e5\u66f4\u65b0\u5931\u8d25", Toast.LENGTH_SHORT).show());
                }
                return;
            }

            JSONObject data = json.getJSONObject("data");
            int remoteVersion = data.optInt("version_code", 0);
            int localVersion = getLocalVersionCode(context);

            Log.d(TAG, "Remote: " + remoteVersion + ", Local: " + localVersion);

            if (remoteVersion > localVersion) {
                String versionName = data.optString("version_name", "");
                String apkUrl = data.optString("apk_url", "");
                String updateNote = data.optString("update_note", "");
                boolean forceUpdate = data.optBoolean("force_update", false);

                if (context instanceof Activity) {
                    final Activity activity = (Activity) context;
                    if (IN_APP_UPDATE_ENABLED) {
                        mainHandler.post(() -> showUpdateDialog(activity, versionName, apkUrl, updateNote, forceUpdate));
                    } else if (isManual) {
                        mainHandler.post(() -> showExternalUpdateDialog(activity, versionName, apkUrl));
                    }
                }
            } else {
                if (isManual && context instanceof Activity) {
                    mainHandler.post(() -> Toast.makeText(context, "\u5df2\u662f\u6700\u65b0\u7248\u672c", Toast.LENGTH_SHORT).show());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Update check error", e);
            if (isManual && context instanceof Activity) {
                mainHandler.post(() -> Toast.makeText(context, "\u68c0\u67e5\u66f4\u65b0\u5931\u8d25: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        }
    }

    private static int getLocalVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    private static void showUpdateDialog(Context context, String versionName, String apkUrl, String updateNote, boolean forceUpdate) {
        if (updateNote.isEmpty()) {
            updateNote = "\u6709\u65b0\u7248\u672c\u53ef\u7528\uff0c\u5efa\u8bae\u66f4\u65b0";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("\u53d1\u73b0\u65b0\u7248\u672c v" + versionName);
        builder.setMessage(updateNote);
        builder.setPositiveButton("\u7acb\u5373\u66f4\u65b0", (dialog, which) -> downloadAndInstall(context, apkUrl));
        if (!forceUpdate) {
            builder.setNegativeButton("\u53d6\u6d88", null);
        }
        AlertDialog dialog = builder.create();
        dialog.setCancelable(!forceUpdate);
        dialog.show();
    }

    private static void showExternalUpdateDialog(Context context, String versionName, String apkUrl) {
        String targetUrl = (apkUrl == null || apkUrl.isEmpty()) ? "https://12zn.com/" : apkUrl;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("发现新版本 v" + versionName);
        builder.setMessage("应用内更新已关闭。点击下方按钮前往官网下载安装最新版。");
        builder.setPositiveButton("前往下载", (dialog, which) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(targetUrl));
            context.startActivity(intent);
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    private static void downloadAndInstall(final Context context, String url) {
        Toast.makeText(context, "\u6b63\u5728\u4e0b\u8f7d\u66f4\u65b0...", Toast.LENGTH_SHORT).show();
        try {
            final File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "yizhaiyiju-update.apk");
            if (file.exists()) file.delete();

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle("\u4e00\u5b85\u4e00\u53e5\u66f4\u65b0");
            request.setDescription("\u6b63\u5728\u4e0b\u8f7d...");
            request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "yizhaiyiju-update.apk");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            downloadId = ((DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);

            BroadcastReceiver receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context ctx, Intent intent) {
                    if (intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1) == downloadId) {
                        ctx.unregisterReceiver(this);
                        installApk(context, file);
                    }
                }
            };

            if (Build.VERSION.SDK_INT >= 33) {
                context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE), Context.RECEIVER_NOT_EXPORTED);
            } else {
                context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            }
        } catch (Exception e) {
            Toast.makeText(context, "\u4e0b\u8f7d\u5931\u8d25\uff0c\u8bf7\u624b\u52a8\u66f4\u65b0", Toast.LENGTH_SHORT).show();
        }
    }

    private static void installApk(Context context, File file) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "\u5b89\u88c5\u5931\u8d25\uff0c\u8bf7\u624b\u52a8\u5b89\u88c5", Toast.LENGTH_SHORT).show();
        }
    }
}
