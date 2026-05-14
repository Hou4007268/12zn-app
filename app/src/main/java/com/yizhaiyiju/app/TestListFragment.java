package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i1;
import com.yizhaiyiju.app.TestData;
import java.util.List;

/* loaded from: classes.dex */
public class TestListFragment extends Fragment {

    public class TestAdapter extends androidx.recyclerview.widget.g0<TestAdapter.VH> {
        List<TestData.TestInfo> data;

        public class VH extends i1 {
            ImageView ivIcon;
            TextView tvDesc;
            TextView tvName;

            public VH(View view) {
                super(view);
                this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
                this.tvName = (TextView) view.findViewById(R.id.tv_name);
                this.tvDesc = (TextView) view.findViewById(R.id.tv_desc);
            }
        }

        public TestAdapter(List<TestData.TestInfo> list) {
            this.data = list;
        }

        int resolveIconRes(String str) {
            if ("bazi".equals(str)) return R.drawable.ic_test_bazi;
            if ("marriage".equals(str)) return R.drawable.ic_test_match;
            if ("name".equals(str)) return R.drawable.ic_test_name;
            if ("flying_star".equals(str)) return R.drawable.ic_test_bazi;
            if ("kua_number".equals(str)) return R.drawable.ic_test_compass;
            if ("fortune".equals(str)) return R.drawable.ic_test_fortune;
            if ("wuxing".equals(str)) return R.drawable.ic_test_element;
            if ("mental_age".equals(str)) return R.drawable.ic_test_brain;
            if ("financial".equals(str)) return R.drawable.ic_test_wealth;
            if ("mbti".equals(str)) return R.drawable.ic_test_personality;
            if ("lucky_color".equals(str)) return R.drawable.ic_test_palette;
            if ("romance".equals(str)) return R.drawable.ic_service_love;
            return R.drawable.ic_test_bazi;
        }

        /* synthetic */ void lambda$onBindViewHolder$0(TestData.TestInfo testInfo, View view) {
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

        @Override
        public int getItemCount() {
            return this.data.size();
        }

        @Override
        public void onBindViewHolder(VH vh, int i4) {
            final TestData.TestInfo testInfo = this.data.get(i4);
            vh.ivIcon.setImageResource(resolveIconRes(testInfo.id));
            vh.tvName.setText(testInfo.name);
            vh.tvDesc.setText(testInfo.description);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
            });
        }

        @Override
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test, viewGroup, false));
        }
    }

    /* synthetic */ void lambda$onViewCreated$0(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).switchToTab(R.id.nav_home);
        }
    }

    @Override
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_test_list, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        InsetsHelper.applyTopInset(view.findViewById(R.id.top_bar));
        view.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).switchToTab(R.id.nav_home);
                }
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_tests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TestAdapter(TestData.getAllTests()));
    }
}
