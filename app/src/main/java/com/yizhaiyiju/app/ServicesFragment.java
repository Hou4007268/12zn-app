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
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ServicesFragment extends Fragment {

    public class ServiceAdapter extends androidx.recyclerview.widget.g0 {
        List<ServiceItem> data;

        public class VH extends i1 {
            TextView tvIcon;
            TextView tvName;
            TextView tvSummary;

            public VH(View view) {
                super(view);
                this.tvIcon = (TextView) view.findViewById(R.id.tv_icon);
                this.tvName = (TextView) view.findViewById(R.id.tv_name);
                this.tvSummary = (TextView) view.findViewById(R.id.tv_summary);
            }
        }

        public ServiceAdapter(List<ServiceItem> list) {
            this.data = list;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(ServiceItem serviceItem, View view) {
            Intent intent = new Intent(ServicesFragment.this.getActivity(), (Class<?>) ServiceDetailActivity.class);
            intent.putExtra("service_name", serviceItem.name);
            ServicesFragment.this.startActivity(intent);
        }

        @Override // androidx.recyclerview.widget.g0
        public int getItemCount() {
            return this.data.size();
        }

        @Override // androidx.recyclerview.widget.g0
        public void onBindViewHolder(VH vh, int i4) {
            ServiceItem serviceItem = this.data.get(i4);
            vh.tvIcon.setText(serviceItem.icon);
            vh.tvName.setText(serviceItem.name);
            vh.tvSummary.setText(serviceItem.summary);
            vh.itemView.setOnClickListener(new j(this, 2, serviceItem));
        }

        @Override // androidx.recyclerview.widget.g0
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service, viewGroup, false));
        }
    }

    public class ServiceItem {
        String icon;
        String name;
        String summary;

        public ServiceItem(String str, String str2, String str3) {
            this.name = str;
            this.summary = str2;
            this.icon = str3;
        }
    }

    private List<ServiceItem> getServices() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ServiceItem("真人1v1咨询", "资深风水师语音/视频一对一沟通，定制化专业方案", "🎙️"));
        arrayList.add(new ServiceItem("请符请物", "太岁符、招财符、桃花符、护身符，根据八字定制", "🪬"));
        arrayList.add(new ServiceItem("择日选时", "搬家吉日、开业良辰、婚嫁择日、动土奠基", "📅"));
        arrayList.add(new ServiceItem("起名改名", "宝宝起名、成人改名、公司取名、品牌命名", "📝"));
        arrayList.add(new ServiceItem("开光法物", "水晶摆件、貔貅开光、风水罗盘、转运手链", "✨"));
        arrayList.add(new ServiceItem("户型风水分析", "发送户型图，获得详细风水分析报告", "📐"));
        arrayList.add(new ServiceItem("财运事业咨询", "财运提升、事业方向分析、职业规划指导", "💰"));
        arrayList.add(new ServiceItem("姻缘感情咨询", "单身找对象、感情问题分析、婚姻调理", "💕"));
        arrayList.add(new ServiceItem("装修风水咨询", "装修布局规划、颜色搭配、门灶厕位置设计", "🏠"));
        arrayList.add(new ServiceItem("流年运势分析", "个人年度运势、每月运程、流年吉凶预测", "📊"));
        arrayList.add(new ServiceItem("风水法事", "还阴债、补财库、超度、祈福转运、化太岁", "🕯️"));
        arrayList.add(new ServiceItem("祖坟阴宅选址", "祖坟迁移、阴宅选址、墓地风水分析", "⛰️"));
        arrayList.add(new ServiceItem("商业风水", "办公室风水布局、店铺选址评估、公司风水规划", "🏢"));
        return arrayList;
    }

    @Override // androidx.lifecycle.i
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_services, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_services);
        getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        recyclerView.setAdapter(new ServiceAdapter(getServices()));
    }
}
