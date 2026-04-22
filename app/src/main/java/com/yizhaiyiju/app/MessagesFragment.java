package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i1;
import com.yizhaiyiju.app.ApiHelper;
import com.yizhaiyiju.app.MessagesFragment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MessagesFragment extends Fragment {
    private SessionAdapter adapter;
    private LinearLayout layoutEmpty;
    private RecyclerView rvSessions;
    private List<JSONObject> sessions = new ArrayList();

    /* renamed from: com.yizhaiyiju.app.MessagesFragment$1, reason: invalid class name */
    public class AnonymousClass1 implements ApiHelper.Callback<JSONObject> {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onError$3() {
            MessagesFragment.this.showEmpty();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$0(View view) {
            MessagesFragment.this.startActivity(new Intent(MessagesFragment.this.getContext(), (Class<?>) DirectChatActivity.class));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$1(View view) {
            MessagesFragment.this.startActivity(new Intent(MessagesFragment.this.getContext(), (Class<?>) DirectChatActivity.class));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$2(JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                MessagesFragment.this.sessions.clear();
                final int i4 = 0;
                for (int i5 = 0; i5 < jSONArray.length(); i5++) {
                    MessagesFragment.this.sessions.add(jSONArray.getJSONObject(i5));
                }
                if (MessagesFragment.this.sessions.isEmpty()) {
                    MessagesFragment.this.showEmpty();
                    MessagesFragment.this.layoutEmpty.setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.m0

                        /* renamed from: f, reason: collision with root package name */
                        public final /* synthetic */ MessagesFragment.AnonymousClass1 f2357f;

                        {
                            this.f2357f = MessagesFragment.this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i6 = i4;
                            MessagesFragment.AnonymousClass1 anonymousClass1 = this.f2357f;
                            switch (i6) {
                                case 0:
                                    anonymousClass1.lambda$onSuccess$0(view);
                                    break;
                                default:
                                    anonymousClass1.lambda$onSuccess$1(view);
                                    break;
                            }
                        }
                    });
                } else {
                    MessagesFragment.this.rvSessions.setVisibility(0);
                    MessagesFragment.this.layoutEmpty.setVisibility(8);
                    MessagesFragment.this.adapter.notifyDataSetChanged();
                }
            } catch (Exception unused) {
                MessagesFragment.this.showEmpty();
                final int i6 = 1;
                MessagesFragment.this.layoutEmpty.setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.m0

                    /* renamed from: f, reason: collision with root package name */
                    public final /* synthetic */ MessagesFragment.AnonymousClass1 f2357f;

                    {
                        this.f2357f = MessagesFragment.this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i62 = i6;
                        MessagesFragment.AnonymousClass1 anonymousClass1 = this.f2357f;
                        switch (i62) {
                            case 0:
                                anonymousClass1.lambda$onSuccess$0(view);
                                break;
                            default:
                                anonymousClass1.lambda$onSuccess$1(view);
                                break;
                        }
                    }
                });
            }
        }

        @Override // com.yizhaiyiju.app.ApiHelper.Callback
        public void onError(String str) {
            if (MessagesFragment.this.getActivity() == null) {
                return;
            }
            MessagesFragment.this.getActivity().runOnUiThread(new h(2, this));
        }

        @Override // com.yizhaiyiju.app.ApiHelper.Callback
        public void onSuccess(JSONObject jSONObject) {
            if (MessagesFragment.this.getActivity() == null) {
                return;
            }
            MessagesFragment.this.getActivity().runOnUiThread(new f(this, 4, jSONObject));
        }
    }

    public class SessionAdapter extends androidx.recyclerview.widget.g0<SessionAdapter.VH> {

        public class VH extends i1 {
            TextView tvLastMsg;
            TextView tvName;
            TextView tvTime;
            TextView tvUnread;

            public VH(View view) {
                super(view);
                this.tvName = (TextView) view.findViewById(R.id.tv_name);
                this.tvTime = (TextView) view.findViewById(R.id.tv_time);
                this.tvLastMsg = (TextView) view.findViewById(R.id.tv_last_msg);
                this.tvUnread = (TextView) view.findViewById(R.id.tv_unread);
            }
        }

        public SessionAdapter() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(JSONObject jSONObject, View view) {
            Intent intent = new Intent(MessagesFragment.this.getContext(), (Class<?>) DirectChatActivity.class);
            intent.putExtra("session_id", jSONObject.optString("id"));
            MessagesFragment.this.startActivity(intent);
        }

        @Override // androidx.recyclerview.widget.g0
        public int getItemCount() {
            return MessagesFragment.this.sessions.size();
        }

        @Override // androidx.recyclerview.widget.g0
        public void onBindViewHolder(VH vh, int i4) {
            TextView textView;
            String str;
            try {
                JSONObject jSONObject = (JSONObject) MessagesFragment.this.sessions.get(i4);
                vh.tvName.setText("张师傅");
                vh.tvLastMsg.setText(jSONObject.optString("last_message", "点击开始对话"));
                String optString = jSONObject.optString("updated_at", "");
                if (!optString.isEmpty()) {
                    try {
                        Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(optString);
                        long currentTimeMillis = System.currentTimeMillis() - parse.getTime();
                        if (currentTimeMillis < 60000) {
                            textView = vh.tvTime;
                            str = "刚刚";
                        } else if (currentTimeMillis < 3600000) {
                            textView = vh.tvTime;
                            str = (currentTimeMillis / 60000) + "分钟前";
                        } else if (currentTimeMillis < 86400000) {
                            textView = vh.tvTime;
                            str = (currentTimeMillis / 3600000) + "小时前";
                        } else {
                            vh.tvTime.setText(new SimpleDateFormat("MM/dd", Locale.getDefault()).format(parse));
                        }
                        textView.setText(str);
                    } catch (Exception unused) {
                        vh.tvTime.setText("");
                    }
                }
                int optInt = jSONObject.optInt("unread_count", 0);
                if (optInt > 0) {
                    vh.tvUnread.setVisibility(0);
                    vh.tvUnread.setText(optInt > 99 ? "99+" : String.valueOf(optInt));
                } else {
                    vh.tvUnread.setVisibility(8);
                }
                vh.itemView.setOnClickListener(new j(this, 1, jSONObject));
            } catch (Exception unused2) {
            }
        }

        @Override // androidx.recyclerview.widget.g0
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_session, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadSessions$0(View view) {
        startActivity(new Intent(getContext(), (Class<?>) DirectChatActivity.class));
    }

    private void loadSessions() {
        String userId = ApiHelper.getUserId();
        if (userId != null && !userId.isEmpty()) {
            ApiHelper.apiGet("app/chat/sessions?user_id=".concat(userId), new AnonymousClass1());
        } else {
            showEmpty();
            this.layoutEmpty.setOnClickListener(new i(4, this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmpty() {
        this.rvSessions.setVisibility(8);
        this.layoutEmpty.setVisibility(0);
    }

    @Override // androidx.lifecycle.i
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_messages, viewGroup, false);
        this.rvSessions = (RecyclerView) inflate.findViewById(R.id.rv_sessions);
        this.layoutEmpty = (LinearLayout) inflate.findViewById(R.id.layout_empty);
        this.adapter = new SessionAdapter();
        RecyclerView recyclerView = this.rvSessions;
        getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        this.rvSessions.setAdapter(this.adapter);
        loadSessions();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        loadSessions();
    }
}
