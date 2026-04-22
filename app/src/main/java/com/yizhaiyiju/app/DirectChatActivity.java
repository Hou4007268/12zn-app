package com.yizhaiyiju.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i1;
import com.yizhaiyiju.app.DirectChatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DirectChatActivity extends d.s {
    private static final String BASE_URL = "https://12zn.com/api/";
    private static final int REQ_IMAGE = 100;
    private static final int REQ_PERMISSION = 200;
    private ChatAdapter adapter;
    private ImageButton btnContact;
    private ImageButton btnImage;
    private ImageButton btnSend;
    private ImageButton btnVoice;
    private EditText etMessage;
    private FrameLayout layoutRecording;
    private Runnable pollRunnable;
    private MediaRecorder recorder;
    private String recordingPath;
    private long recordingStart;
    private RecyclerView rvMessages;
    private String sessionId;
    private TextView tvRecordingStatus;
    private TextView tvRecordingTime;
    private List<JSONObject> messages = new ArrayList();
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean isRecording = false;

    /* renamed from: com.yizhaiyiju.app.DirectChatActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements q3.e {
        public AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onResponse$0() {
            DirectChatActivity.this.loadMessages(false);
            DirectChatActivity.this.handler.postDelayed(DirectChatActivity.this.pollRunnable, 3000L);
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            try {
                JSONObject jSONObject = new JSONObject(h0Var.f4104g.C());
                if (jSONObject.optBoolean("success")) {
                    DirectChatActivity.this.sessionId = jSONObject.getJSONObject("data").getString("id");
                    DirectChatActivity.this.runOnUiThread(new h(1, this));
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: com.yizhaiyiju.app.DirectChatActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements q3.e {
        final /* synthetic */ boolean val$scrollToEnd;

        public AnonymousClass3(boolean z4) {
            this.val$scrollToEnd = z4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onResponse$0(JSONArray jSONArray, boolean z4) {
            DirectChatActivity.this.messages.clear();
            for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                try {
                    DirectChatActivity.this.messages.add(jSONArray.getJSONObject(i4));
                } catch (Exception unused) {
                }
            }
            DirectChatActivity.this.adapter.notifyDataSetChanged();
            if (!z4 || DirectChatActivity.this.messages.size() <= 0) {
                return;
            }
            DirectChatActivity.this.rvMessages.e0(DirectChatActivity.this.messages.size() - 1);
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            try {
                JSONObject jSONObject = new JSONObject(h0Var.f4104g.C());
                if (jSONObject.optBoolean("success")) {
                    final JSONArray jSONArray = jSONObject.getJSONArray("data");
                    DirectChatActivity directChatActivity = DirectChatActivity.this;
                    final boolean z4 = this.val$scrollToEnd;
                    directChatActivity.runOnUiThread(new Runnable() { // from class: com.yizhaiyiju.app.y
                        @Override // java.lang.Runnable
                        public final void run() {
                            DirectChatActivity.AnonymousClass3.this.lambda$onResponse$0(jSONArray, z4);
                        }
                    });
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: com.yizhaiyiju.app.DirectChatActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements q3.e {
        public AnonymousClass5() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$0() {
            Toast.makeText(DirectChatActivity.this, "发送失败", 0).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onResponse$1() {
            DirectChatActivity.this.loadMessages(true);
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            DirectChatActivity.this.runOnUiThread(new z(this, 0));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            h0Var.close();
            DirectChatActivity.this.runOnUiThread(new z(this, 1));
        }
    }

    /* renamed from: com.yizhaiyiju.app.DirectChatActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements q3.e {
        public AnonymousClass6() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$0() {
            Toast.makeText(DirectChatActivity.this, "图片发送失败", 0).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onResponse$1() {
            DirectChatActivity.this.loadMessages(true);
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            DirectChatActivity.this.runOnUiThread(new a0(this, 0));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            h0Var.close();
            DirectChatActivity.this.runOnUiThread(new a0(this, 1));
        }
    }

    /* renamed from: com.yizhaiyiju.app.DirectChatActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements q3.e {
        public AnonymousClass7() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$0() {
            Toast.makeText(DirectChatActivity.this, "语音发送失败", 0).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onResponse$1() {
            DirectChatActivity.this.loadMessages(true);
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            DirectChatActivity.this.runOnUiThread(new b0(this, 0));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            h0Var.close();
            DirectChatActivity.this.runOnUiThread(new b0(this, 1));
        }
    }

    public class ChatAdapter extends androidx.recyclerview.widget.g0<ChatAdapter.VH> {

        public class VH extends i1 {
            ImageView ivMsgImage;
            ImageView ivMsgImageUser;
            LinearLayout layoutMaster;
            LinearLayout layoutUser;
            LinearLayout layoutVoice;
            LinearLayout layoutVoiceUser;
            TextView tvMsgText;
            TextView tvMsgTextUser;
            TextView tvVoiceDuration;
            TextView tvVoiceDurationUser;

            public VH(View view) {
                super(view);
                this.layoutMaster = (LinearLayout) view.findViewById(R.id.layout_master);
                this.layoutUser = (LinearLayout) view.findViewById(R.id.layout_user);
                this.tvMsgText = (TextView) view.findViewById(R.id.tv_msg_text);
                this.tvMsgTextUser = (TextView) view.findViewById(R.id.tv_msg_text_user);
                this.ivMsgImage = (ImageView) view.findViewById(R.id.iv_msg_image);
                this.ivMsgImageUser = (ImageView) view.findViewById(R.id.iv_msg_image_user);
                this.layoutVoice = (LinearLayout) view.findViewById(R.id.layout_voice);
                this.layoutVoiceUser = (LinearLayout) view.findViewById(R.id.layout_voice_user);
                this.tvVoiceDuration = (TextView) view.findViewById(R.id.tv_voice_duration);
                this.tvVoiceDurationUser = (TextView) view.findViewById(R.id.tv_voice_duration_user);
            }
        }

        public ChatAdapter() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(String str, View view) {
            try {
                DirectChatActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$1(String str, View view) {
            DirectChatActivity.this.playVoice(str);
        }

        @Override // androidx.recyclerview.widget.g0
        public int getItemCount() {
            return DirectChatActivity.this.messages.size();
        }

        @Override // androidx.recyclerview.widget.g0
        public void onBindViewHolder(VH vh, int i4) {
            try {
                JSONObject jSONObject = (JSONObject) DirectChatActivity.this.messages.get(i4);
                String optString = jSONObject.optString("sender", "user");
                String optString2 = jSONObject.optString("msg_type", "text");
                final String optString3 = jSONObject.optString("content", "");
                boolean equals = "master".equals(optString);
                final int i5 = 0;
                vh.layoutMaster.setVisibility(equals ? 0 : 8);
                vh.layoutUser.setVisibility(equals ? 8 : 0);
                TextView textView = equals ? vh.tvMsgText : vh.tvMsgTextUser;
                ImageView imageView = equals ? vh.ivMsgImage : vh.ivMsgImageUser;
                LinearLayout linearLayout = equals ? vh.layoutVoice : vh.layoutVoiceUser;
                TextView textView2 = equals ? vh.tvVoiceDuration : vh.tvVoiceDurationUser;
                textView.setVisibility(8);
                imageView.setVisibility(8);
                linearLayout.setVisibility(8);
                if ("text".equals(optString2)) {
                    textView.setVisibility(0);
                    textView.setText(optString3);
                    return;
                }
                if ("image".equals(optString2)) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(android.R.drawable.ic_menu_gallery);
                    imageView.setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.c0

                        /* renamed from: f, reason: collision with root package name */
                        public final /* synthetic */ DirectChatActivity.ChatAdapter f2305f;

                        {
                            this.f2305f = DirectChatActivity.this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i6 = i5;
                            String str = optString3;
                            DirectChatActivity.ChatAdapter chatAdapter = this.f2305f;
                            switch (i6) {
                                case 0:
                                    chatAdapter.lambda$onBindViewHolder$0(str, view);
                                    break;
                                default:
                                    chatAdapter.lambda$onBindViewHolder$1(str, view);
                                    break;
                            }
                        }
                    });
                } else if ("voice".equals(optString2)) {
                    linearLayout.setVisibility(0);
                    textView2.setText(jSONObject.optInt("duration", 0) + "\"");
                    final int i6 = 1;
                    linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.c0

                        /* renamed from: f, reason: collision with root package name */
                        public final /* synthetic */ DirectChatActivity.ChatAdapter f2305f;

                        {
                            this.f2305f = DirectChatActivity.this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i62 = i6;
                            String str = optString3;
                            DirectChatActivity.ChatAdapter chatAdapter = this.f2305f;
                            switch (i62) {
                                case 0:
                                    chatAdapter.lambda$onBindViewHolder$0(str, view);
                                    break;
                                default:
                                    chatAdapter.lambda$onBindViewHolder$1(str, view);
                                    break;
                            }
                        }
                    });
                }
            } catch (Exception unused) {
            }
        }

        @Override // androidx.recyclerview.widget.g0
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_direct_msg, viewGroup, false));
        }
    }

    private void cancelRecording() {
        if (this.isRecording) {
            this.isRecording = false;
            this.layoutRecording.setVisibility(8);
            try {
                this.recorder.stop();
                this.recorder.release();
                this.recorder = null;
                new File(this.recordingPath).delete();
            } catch (Exception unused) {
            }
        }
    }

    private void createSession() {
        String userId = ApiHelper.getUserId();
        String userPhone = ApiHelper.getUserPhone();
        if (userId == null) {
            Toast.makeText(this, "请先登录", 0).show();
            finish();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("user_id", Integer.parseInt(userId));
            if (userPhone == null) {
                userPhone = "";
            }
            jSONObject.put("user_name", userPhone);
            String jSONObject2 = jSONObject.toString();
            Pattern pattern = q3.v.f4179d;
            q3.f0 f4 = q3.o.f(jSONObject2, q3.o.r("application/json"));
            q3.c0 authRequest = ApiHelper.authRequest();
            authRequest.d("https://12zn.com/api/app/chat/sessions");
            authRequest.c("POST", f4);
            ApiHelper.getClient().a(authRequest.a()).f(new AnonymousClass2());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        sendTextMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onCreate$2(TextView textView, int i4, KeyEvent keyEvent) {
        if (i4 != 4) {
            return false;
        }
        sendTextMessage();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(View view) {
        startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), REQ_IMAGE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$4(View view) {
        showContactDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupVoiceRecording$5(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            startRecording();
            return true;
        }
        if (action == 1) {
            stopAndSendRecording();
            return true;
        }
        if (action != 3) {
            return false;
        }
        cancelRecording();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showContactDialog$6(View view) {
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("WeChat", "ayizhaiyiju"));
        Toast.makeText(this, "微信号已复制", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showContactDialog$7(View view) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/yizhaiyiju")));
        } catch (Exception unused) {
            Toast.makeText(this, "请安装Telegram", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadMessages(boolean z4) {
        if (this.sessionId == null) {
            return;
        }
        q3.c0 authRequest = ApiHelper.authRequest();
        authRequest.d("https://12zn.com/api/app/chat/messages?session_id=" + this.sessionId + "&limit=100");
        ApiHelper.getClient().a(authRequest.a()).f(new AnonymousClass3(z4));
    }

    private void markRead() {
        if (this.sessionId == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("session_id", this.sessionId);
            String jSONObject2 = jSONObject.toString();
            Pattern pattern = q3.v.f4179d;
            q3.f0 f4 = q3.o.f(jSONObject2, q3.o.r("application/json"));
            q3.c0 authRequest = ApiHelper.authRequest();
            authRequest.d("https://12zn.com/api/app/chat/read");
            authRequest.c("POST", f4);
            ApiHelper.getClient().a(authRequest.a()).f(new q3.e() { // from class: com.yizhaiyiju.app.DirectChatActivity.4
                @Override // q3.e
                public void onFailure(q3.d dVar, IOException iOException) {
                }

                @Override // q3.e
                public void onResponse(q3.d dVar, q3.h0 h0Var) {
                    h0Var.close();
                }
            });
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playVoice(String str) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(str);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new w());
            mediaPlayer.setOnCompletionListener(new x());
        } catch (Exception unused) {
            Toast.makeText(this, "播放失败", 0).show();
        }
    }

    private void sendImageMessage(Uri uri) {
        if (this.sessionId == null) {
            return;
        }
        try {
            InputStream openInputStream = getContentResolver().openInputStream(uri);
            File file = new File(getCacheDir(), "chat_img_" + System.currentTimeMillis() + ".jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    fileOutputStream.close();
                    openInputStream.close();
                    Pattern pattern = q3.v.f4179d;
                    q3.e0 e0Var = new q3.e0(file, q3.o.r("image/jpeg"));
                    q3.w wVar = new q3.w();
                    wVar.c(q3.y.N);
                    wVar.a("session_id", this.sessionId);
                    wVar.f4186c.add(q3.o.g("file", file.getName(), e0Var));
                    q3.y b5 = wVar.b();
                    q3.c0 authRequest = ApiHelper.authRequest();
                    authRequest.d("https://12zn.com/api/app/chat/messages/image");
                    authRequest.c("POST", b5);
                    ApiHelper.getClient().a(authRequest.a()).f(new AnonymousClass6());
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception unused) {
            Toast.makeText(this, "图片读取失败", 0).show();
        }
    }

    private void sendTextMessage() {
        String trim = this.etMessage.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || this.sessionId == null) {
            return;
        }
        this.etMessage.setText("");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("session_id", this.sessionId);
            jSONObject.put("content", trim);
            String jSONObject2 = jSONObject.toString();
            Pattern pattern = q3.v.f4179d;
            q3.f0 f4 = q3.o.f(jSONObject2, q3.o.r("application/json"));
            q3.c0 authRequest = ApiHelper.authRequest();
            authRequest.d("https://12zn.com/api/app/chat/messages");
            authRequest.c("POST", f4);
            ApiHelper.getClient().a(authRequest.a()).f(new AnonymousClass5());
        } catch (Exception unused) {
        }
    }

    private void sendVoiceMessage(String str, int i4) {
        if (this.sessionId == null) {
            return;
        }
        try {
            File file = new File(str);
            Pattern pattern = q3.v.f4179d;
            q3.e0 e0Var = new q3.e0(file, q3.o.r("audio/m4a"));
            q3.w wVar = new q3.w();
            wVar.c(q3.y.N);
            wVar.a("session_id", this.sessionId);
            wVar.a("duration", String.valueOf(i4));
            wVar.f4186c.add(q3.o.g("file", file.getName(), e0Var));
            q3.y b5 = wVar.b();
            q3.c0 authRequest = ApiHelper.authRequest();
            authRequest.d("https://12zn.com/api/app/chat/messages/voice");
            authRequest.c("POST", b5);
            ApiHelper.getClient().a(authRequest.a()).f(new AnonymousClass7());
        } catch (Exception unused) {
        }
    }

    private void setupVoiceRecording() {
        this.btnVoice.setOnTouchListener(new View.OnTouchListener() { // from class: com.yizhaiyiju.app.u
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$setupVoiceRecording$5;
                lambda$setupVoiceRecording$5 = DirectChatActivity.this.lambda$setupVoiceRecording$5(view, motionEvent);
                return lambda$setupVoiceRecording$5;
            }
        });
    }

    private void showContactDialog() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_contact, (ViewGroup) null);
        d.o oVar = new d.o(this);
        ((d.k) oVar.f2561f).f2485n = inflate;
        d.p a5 = oVar.a();
        inflate.findViewById(R.id.layout_wechat).setOnClickListener(new v(this, 4));
        inflate.findViewById(R.id.layout_telegram).setOnClickListener(new v(this, 5));
        a5.show();
    }

    private void startRecording() {
        if (x.g.a(this, "android.permission.RECORD_AUDIO") != 0) {
            x.g.c(this, new String[]{"android.permission.RECORD_AUDIO"}, REQ_PERMISSION);
            return;
        }
        try {
            this.recordingPath = getCacheDir() + "/voice_" + System.currentTimeMillis() + ".m4a";
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.recorder = mediaRecorder;
            mediaRecorder.setAudioSource(1);
            this.recorder.setOutputFormat(2);
            this.recorder.setAudioEncoder(3);
            this.recorder.setOutputFile(this.recordingPath);
            this.recorder.prepare();
            this.recorder.start();
            this.isRecording = true;
            this.recordingStart = System.currentTimeMillis();
            this.layoutRecording.setVisibility(0);
            updateRecordingTime();
        } catch (Exception unused) {
            Toast.makeText(this, "录音失败", 0).show();
        }
    }

    private void stopAndSendRecording() {
        if (this.isRecording) {
            this.isRecording = false;
            this.layoutRecording.setVisibility(8);
            try {
                this.recorder.stop();
                this.recorder.release();
                this.recorder = null;
                int currentTimeMillis = (int) ((System.currentTimeMillis() - this.recordingStart) / 1000);
                if (currentTimeMillis < 1) {
                    Toast.makeText(this, "录音太短", 0).show();
                } else {
                    sendVoiceMessage(this.recordingPath, currentTimeMillis);
                }
            } catch (Exception unused) {
                Toast.makeText(this, "录音失败", 0).show();
            }
        }
    }

    private void stopRecording() {
        if (this.isRecording) {
            cancelRecording();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRecordingTime() {
        if (this.isRecording) {
            long currentTimeMillis = (System.currentTimeMillis() - this.recordingStart) / 1000;
            this.tvRecordingTime.setText(String.format(Locale.getDefault(), "%d:%02d", Long.valueOf(currentTimeMillis / 60), Long.valueOf(currentTimeMillis % 60)));
            this.handler.postDelayed(new h(5, this), 200L);
        }
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i4, int i5, Intent intent) {
        super.onActivityResult(i4, i5, intent);
        if (i4 != REQ_IMAGE || i5 != -1 || intent == null || intent.getData() == null) {
            return;
        }
        sendImageMessage(intent.getData());
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_direct_chat);
        String stringExtra = getIntent().getStringExtra("session_id");
        this.sessionId = stringExtra;
        if (stringExtra == null || stringExtra.isEmpty()) {
            createSession();
        }
        this.rvMessages = (RecyclerView) findViewById(R.id.rv_messages);
        this.etMessage = (EditText) findViewById(R.id.et_message);
        this.btnSend = (ImageButton) findViewById(R.id.btn_send);
        this.btnVoice = (ImageButton) findViewById(R.id.btn_voice);
        this.btnImage = (ImageButton) findViewById(R.id.btn_image);
        this.btnContact = (ImageButton) findViewById(R.id.btn_contact);
        this.layoutRecording = (FrameLayout) findViewById(R.id.layout_recording);
        this.tvRecordingTime = (TextView) findViewById(R.id.tv_recording_time);
        this.tvRecordingStatus = (TextView) findViewById(R.id.tv_recording_status);
        ((ImageButton) findViewById(R.id.btn_back)).setOnClickListener(new v(this, 0));
        this.adapter = new ChatAdapter();
        int i4 = 1;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(1);
        linearLayoutManager.b1(true);
        this.rvMessages.setLayoutManager(linearLayoutManager);
        this.rvMessages.setAdapter(this.adapter);
        this.btnSend.setOnClickListener(new v(this, i4));
        this.etMessage.setOnEditorActionListener(new p(this, i4));
        this.btnImage.setOnClickListener(new v(this, 2));
        this.btnContact.setOnClickListener(new v(this, 3));
        setupVoiceRecording();
        this.pollRunnable = new Runnable() { // from class: com.yizhaiyiju.app.DirectChatActivity.1
            @Override // java.lang.Runnable
            public void run() {
                DirectChatActivity.this.loadMessages(true);
                DirectChatActivity.this.handler.postDelayed(this, 3000L);
            }
        };
    }

    @Override // d.s, androidx.fragment.app.a0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.pollRunnable);
        stopRecording();
    }

    @Override // androidx.fragment.app.a0, android.app.Activity
    public void onPause() {
        super.onPause();
        this.handler.removeCallbacks(this.pollRunnable);
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i4, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i4, strArr, iArr);
        if (i4 != REQ_PERMISSION || iArr.length <= 0 || iArr[0] == 0) {
            return;
        }
        Toast.makeText(this, "需要录音权限才能发送语音", 0).show();
    }

    @Override // androidx.fragment.app.a0, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.sessionId != null) {
            loadMessages(false);
            markRead();
            this.handler.postDelayed(this.pollRunnable, 3000L);
        }
    }
}
