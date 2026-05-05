package com.yizhaiyiju.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i1;
import com.yizhaiyiju.app.ApiHelper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatActivity extends d.s {
    private static final long AUTO_CLEAR_DAYS = 604800000;
    private static final String KEY_LAST_CLEAR = "last_clear_time";
    private static final String KEY_MESSAGES = "messages";
    private static final String PREFS_NAME = "chat_history";
    private MessageAdapter adapter;
    private EditText etMessage;
    private List<ChatMessage> messages = new ArrayList();
    private SharedPreferences prefs;
    private RecyclerView rvMessages;
    private boolean isSending = false;
    private ImageButton btnSend;

    public static class ChatMessage {
        boolean isUser;
        String text;

        public ChatMessage(String str, boolean z4) {
            this.text = str;
            this.isUser = z4;
        }
    }

    public class MessageAdapter extends androidx.recyclerview.widget.g0<MessageAdapter.VH> {
        List<ChatMessage> data;

        public class VH extends i1 {
            View layoutAi;
            TextView tvAi;
            TextView tvUser;

            public VH(View view) {
                super(view);
                this.layoutAi = view.findViewById(R.id.layout_ai);
                this.tvAi = (TextView) view.findViewById(R.id.tv_message_ai);
                this.tvUser = (TextView) view.findViewById(R.id.tv_message_user);
            }
        }

        public MessageAdapter(List<ChatMessage> list) {
            this.data = list;
        }

        @Override // androidx.recyclerview.widget.g0
        public int getItemCount() {
            return this.data.size();
        }

        @Override // androidx.recyclerview.widget.g0
        public void onBindViewHolder(VH vh, int i4) {
            TextView textView;
            ChatMessage chatMessage = this.data.get(i4);
            if (chatMessage.isUser) {
                vh.layoutAi.setVisibility(8);
                vh.tvUser.setVisibility(0);
                textView = vh.tvUser;
            } else {
                vh.layoutAi.setVisibility(0);
                vh.tvUser.setVisibility(8);
                textView = vh.tvAi;
            }
            textView.setText(chatMessage.text);
        }

        @Override // androidx.recyclerview.widget.g0
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMessage(String str, boolean z4) {
        this.messages.add(new ChatMessage(str, z4));
        this.adapter.notifyItemInserted(this.messages.size() - 1);
        this.rvMessages.scrollToPosition(this.messages.size() - 1);
        saveMessages();
    }

    private void checkAutoClear() {
        long j4 = this.prefs.getLong(KEY_LAST_CLEAR, 0L);
        if (System.currentTimeMillis() - j4 <= this.prefs.getLong("auto_clear_days", 7L) * 24 * 60 * 60 * 1000 || j4 <= 0) {
            return;
        }
        this.prefs.edit().remove(KEY_MESSAGES).putLong(KEY_LAST_CLEAR, System.currentTimeMillis()).apply();
    }

    private void clearMessages() {
        this.messages.clear();
        this.adapter.notifyDataSetChanged();
        this.prefs.edit().remove(KEY_MESSAGES).putLong(KEY_LAST_CLEAR, System.currentTimeMillis()).apply();
        addMessage("聊天记录已清空。有什么风水问题可以继续问我。", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(View view) {
        sendMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onCreate$3(TextView textView, int i4, KeyEvent keyEvent) {
        if (i4 != 4) {
            return false;
        }
        sendMessage();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$showChatMenu$4(MenuItem menuItem) {
        if (menuItem.getItemId() == 1) {
            clearMessages();
            return true;
        }
        if (menuItem.getItemId() != 2) {
            return false;
        }
        long j4 = 7;
        long j5 = this.prefs.getLong("auto_clear_days", 7L);
        if (j5 == 7) {
            j4 = 3;
        } else if (j5 == 3) {
            j4 = 1;
        }
        this.prefs.edit().putLong("auto_clear_days", j4).apply();
        menuItem.setTitle("自动清理: " + j4 + "天");
        return true;
    }

    private void loadMessages() {
        String string = this.prefs.getString(KEY_MESSAGES, null);
        if (string == null) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i4);
                this.messages.add(new ChatMessage(jSONObject.getString("text"), jSONObject.getBoolean("isUser")));
            }
        } catch (JSONException unused) {
        }
    }

    private void saveMessages() {
        JSONArray jSONArray = new JSONArray();
        for (ChatMessage chatMessage : this.messages) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("text", chatMessage.text);
                jSONObject.put("isUser", chatMessage.isUser);
                jSONArray.put(jSONObject);
            } catch (JSONException unused) {
            }
        }
        this.prefs.edit().putString(KEY_MESSAGES, jSONArray.toString()).apply();
    }

    private void sendMessage() {
        String trim = this.etMessage.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || this.isSending) {
            return;
        }
        this.isSending = true;
        this.btnSend.setEnabled(false);
        addMessage(trim, true);
        this.etMessage.setText("");
        doChatRequest(trim, 0);
    }

    private void doChatRequest(String question, final int retryCount) {
        ApiHelper.chat(question, null, new ApiHelper.Callback<String>() {
            @Override // com.yizhaiyiju.app.ApiHelper.Callback
            public void onError(String str) {
                if (retryCount < 1) {
                    doChatRequest(question, retryCount + 1);
                    return;
                }
                ChatActivity.this.isSending = false;
                ChatActivity.this.btnSend.setEnabled(true);
                ChatActivity.this.addMessage("抱歉，暂时无法回答，请稍后再试。", false);
            }

            @Override // com.yizhaiyiju.app.ApiHelper.Callback
            public void onSuccess(String str) {
                ChatActivity.this.isSending = false;
                ChatActivity.this.btnSend.setEnabled(true);
                ChatActivity.this.addMessage(str, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showChatMenu, reason: merged with bridge method [inline-methods] */
    public void lambda$onCreate$1(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenu().add(0, 1, 0, "清空聊天记录");
        popupMenu.getMenu().add(0, 2, 1, "自动清理: 7天");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.yizhaiyiju.app.q
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                boolean lambda$showChatMenu$4;
                lambda$showChatMenu$4 = ChatActivity.this.lambda$showChatMenu$4(menuItem);
                return lambda$showChatMenu$4;
            }
        });
        popupMenu.show();
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_chat);
        final int i4 = 0;
        this.prefs = getSharedPreferences(PREFS_NAME, 0);
        this.rvMessages = (RecyclerView) findViewById(R.id.rv_messages);
        this.etMessage = (EditText) findViewById(R.id.et_message);
        this.btnSend = (ImageButton) findViewById(R.id.btn_send);
        ((ImageButton) findViewById(R.id.btn_back)).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.o

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ChatActivity f2362f;

            {
                this.f2362f = ChatActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                ChatActivity chatActivity = this.f2362f;
                switch (i5) {
                    case 0:
                        chatActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        chatActivity.lambda$onCreate$1(view);
                        break;
                    default:
                        chatActivity.lambda$onCreate$2(view);
                        break;
                }
            }
        });
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.btn_chat_menu);
        final int i5 = 1;
        if (imageButton2 != null) {
            imageButton2.setVisibility(0);
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.o

                /* renamed from: f, reason: collision with root package name */
                public final /* synthetic */ ChatActivity f2362f;

                {
                    this.f2362f = ChatActivity.this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i52 = i5;
                    ChatActivity chatActivity = this.f2362f;
                    switch (i52) {
                        case 0:
                            chatActivity.lambda$onCreate$0(view);
                            break;
                        case 1:
                            chatActivity.lambda$onCreate$1(view);
                            break;
                        default:
                            chatActivity.lambda$onCreate$2(view);
                            break;
                    }
                }
            });
        }
        this.adapter = new MessageAdapter(this.messages);
        this.rvMessages.setLayoutManager(new LinearLayoutManager(this));
        this.rvMessages.setAdapter(this.adapter);
        checkAutoClear();
        loadMessages();
        if (this.messages.isEmpty()) {
            addMessage("你好！我是AI风水助手，有什么风水问题可以问我。比如：\n\n• 我家大门朝南，风水好吗？\n• 卧室床应该怎么摆放？\n• 办公桌放哪里最好？", false);
        }
        final int i6 = 2;
        this.btnSend.setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.o

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ChatActivity f2362f;

            {
                this.f2362f = ChatActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i6;
                ChatActivity chatActivity = this.f2362f;
                switch (i52) {
                    case 0:
                        chatActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        chatActivity.lambda$onCreate$1(view);
                        break;
                    default:
                        chatActivity.lambda$onCreate$2(view);
                        break;
                }
            }
        });
        this.etMessage.setOnEditorActionListener(new p(this, i4));
    }
}
