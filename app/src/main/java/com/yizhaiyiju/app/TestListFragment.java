package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i1;
import com.yizhaiyiju.app.TestData;
import java.util.List;

/* loaded from: classes.dex */
public class TestListFragment extends Fragment {

    public class TestAdapter extends androidx.recyclerview.widget.g0 {
        List<TestData.TestInfo> data;

        public class VH extends i1 {
            TextView tvDesc;
            TextView tvEmoji;
            TextView tvName;

            public VH(View view) {
                super(view);
                this.tvEmoji = (TextView) view.findViewById(R.id.tv_emoji);
                this.tvName = (TextView) view.findViewById(R.id.tv_name);
                this.tvDesc = (TextView) view.findViewById(R.id.tv_desc);
            }
        }

        public TestAdapter(List<TestData.TestInfo> list) {
            this.data = list;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(TestData.TestInfo testInfo, View view) {
            Intent intent;
            if (testInfo.id.equals("fortune")) {
                intent = new Intent(TestListFragment.this.getActivity(), (Class<?>) FortuneActivity.class);
            } else if (testInfo.id.equals("wuxing")) {
                intent = new Intent(TestListFragment.this.getActivity(), (Class<?>) WuxingTestActivity.class);
            } else if (testInfo.id.equals("bazi")) {
                intent = new Intent(TestListFragment.this.getActivity(), (Class<?>) BaZiActivity.class);
            } else if (testInfo.id.equals("flying_star")) {
                intent = new Intent(TestListFragment.this.getActivity(), (Class<?>) FlyingStarActivity.class);
            } else if (testInfo.id.equals("kua_number")) {
                intent = new Intent(TestListFragment.this.getActivity(), (Class<?>) KuaNumberActivity.class);
            } else if (testInfo.id.equals("marriage")) {
                intent = new Intent(TestListFragment.this.getActivity(), (Class<?>) MarriageMatchActivity.class);
            } else if (testInfo.id.equals("name")) {
                intent = new Intent(TestListFragment.this.getActivity(), (Class<?>) NameAnalysisActivity.class);
            } else {
                Intent intent2 = new Intent(TestListFragment.this.getActivity(), (Class<?>) GenericTestActivity.class);
                intent2.putExtra("test_id", testInfo.id);
                intent2.putExtra("test_name", testInfo.name);
                intent = intent2;
            }
            TestListFragment.this.startActivity(intent);
        }

        @Override // androidx.recyclerview.widget.g0
        public int getItemCount() {
            return this.data.size();
        }

        @Override // androidx.recyclerview.widget.g0
        public void onBindViewHolder(VH vh, int i4) {
            TestData.TestInfo testInfo = this.data.get(i4);
            vh.tvEmoji.setText(testInfo.emoji);
            vh.tvName.setText(testInfo.name);
            vh.tvDesc.setText(testInfo.description);
            vh.itemView.setOnClickListener(new j(this, 3, testInfo));
        }

        @Override // androidx.recyclerview.widget.g0
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).switchToTab(R.id.nav_home);
        }
    }

    @Override // androidx.lifecycle.i
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_test_list, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.btn_back).setOnClickListener(new i(6, this));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_tests);
        getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        recyclerView.setAdapter(new TestAdapter(TestData.getAllTests()));
    }
}
