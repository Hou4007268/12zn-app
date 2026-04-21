package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.yizhaiyiju.app.ServiceDetailActivity;

/* loaded from: classes.dex */
public class ServiceDetailActivity extends d.s {

    public static class ServiceInfo {
        String desc;
        String icon;
        String name;
        String notice;
        String price;
        String priceNum;
        String process;
        String productId;

        public ServiceInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.name = str;
            this.icon = str2;
            this.price = str3;
            this.desc = str4;
            this.process = str5;
            this.notice = str6;
            this.productId = str7;
            this.priceNum = str8;
        }
    }

    private ServiceInfo getServiceInfo(String str) {
        str.getClass();
        switch (str) {
            case "户型风水分析":
                return new ServiceInfo("户型风水分析", "🏠", "¥999/次", "发送户型图，师傅详细分析风水格局，找出冲煞点和聚气位，给出布局优化方案。\n\n适合：新房装修、二手房改造、想优化现有户型的客户。", "1. 下单后发送户型图\n2. 师傅48小时内出具分析报告\n3. 报告包含：问题标注+化解建议+布局图\n4. 可追加一次文字答疑", "• 请提供清晰的户型图（含朝向标注）\n• 毛坯/已装修均可\n• 超大户型或别墅另议", "consult_299", "999");
            case "姻缘感情咨询":
                return new ServiceInfo("姻缘感情咨询", "💕", "¥999/次", "拆关系信号，判断下一步怎么走。分析感情走势、桃花位、相处模式。\n\n适合：单身想招桃花、恋爱中有困惑、婚姻需要调理的客户。", "1. 下单填写感情状况和问题\n2. 师傅分析八字合婚/桃花运\n3. 一对一沟通详解\n4. 出具感情调理建议", "• 如需合婚请提供双方生辰\n• 卧室照片有助于分析桃花位\n• 所有信息严格保密", null, null);
            case "真人1v1咨询":
                return new ServiceInfo("真人1v1咨询", "🪑", "¥999/次", "资深风水师一对一语音/视频详细沟通，针对您的具体情况给出专业分析和调整方案。\n\n适合：需要深度分析、有具体问题需要当面沟通解答的客户。", "1. 下单预约，填写基本信息\n2. 师傅24小时内联系您确认时间\n3. 语音/视频一对一沟通（约60分钟）\n4. 沟通后出具书面建议", "• 预约后请保持手机畅通\n• 建议提前准备好户型图或问题清单\n• 沟通时间可协商调整", "consult_99", "999");
            case "流年运势分析":
                return new ServiceInfo("流年运势分析", "📈", "¥399起", "按年/月看起伏，提前避坑。分析流年大运、吉凶方位、注意事项。\n\n适合：想了解未来一年运势走向、提前规划的客户。", "1. 下单提供生辰信息\n2. 师傅分析流年大运\n3. 出具年度运势报告\n4. 含每月吉凶提示", "• 需要准确的出生年月日时\n• 年初或生日前咨询最佳\n• 可指定具体关注方向", null, null);
            case "财运事业咨询":
                return new ServiceInfo("财运事业咨询", "💰", "¥999/次", "先看财路卡点，再定行动方向。结合八字命理和风水环境，分析财运走势和事业瓶颈。\n\n适合：事业遇到瓶颈、想提升财运、做重大职业决策的客户。", "1. 下单填写生辰信息和现状描述\n2. 师傅分析八字+环境风水\n3. 语音/文字沟通解答\n4. 出具财运提升建议", "• 请提供准确的出生年月日时\n• 如有办公环境照片请一并提供\n• 建议提前列出最想问的问题", null, null);
            case "装修风水咨询":
                return new ServiceInfo("装修风水咨询", "🛋️", "¥999/次", "按预算先改动线、采光、门灶厕。在装修前介入，避免后期返工。\n\n适合：即将装修、想要风水加持的装修方案的客户。", "1. 下单提供户型图+装修需求\n2. 师傅分析风水要点\n3. 给出装修风水建议清单\n4. 装修过程中可追加2次咨询", "• 毛坯阶段效果最佳\n• 请提供装修预算和风格偏好\n• 建议在确定装修公司前咨询", null, null);
            case "祖坟阴宅选址":
                return new ServiceInfo("祖坟阴宅选址", "⛰️", "请询价", "选址先看地势和方位。阴宅风水关系后代运势，需要实地勘察。\n\n适合：需要选墓地、迁坟、调理祖坟风水的客户。", "1. 先联系客服沟通需求\n2. 提供备选地点信息\n3. 师傅现场勘察或远程分析\n4. 出具选址/调理建议", "• 需要提供墓地备选位置\n• 现场勘察需另付差旅费\n• 建议提前1-2周预约", null, null);
            case "商业风水":
                return new ServiceInfo("商业风水", "🏢", "¥999起", "办公室、店铺、公司布局直接优化。提升团队效率和财运。\n\n适合：企业主、店铺老板、想优化办公环境的管理者。", "1. 下单提供平面图和照片\n2. 师傅分析现有格局\n3. 给出调整方案\n4. 可预约现场勘察（另计）", "• 请提供办公场所平面图\n• 大面积场所可能需现场勘察\n• 连锁店可打包优惠", null, null);
            case "开光法物":
                return new ServiceInfo("开光法物", "💎", "¥499起", "水晶、貔貅、罗盘、手链等法物开光。按需选购，师傅加持开光。\n\n适合：有法物需要开光、想请购开光法物的客户。", "1. 下单说明需求\n2. 选择法物类型\n3. 师傅开光加持\n4. 顺丰寄出（含使用说明）", "• 自有法物可寄来开光\n• 新请法物师傅代为选购\n• 开光后请按说明养护", "talisman_199", "499");
            case "择日选时":
                return new ServiceInfo("择日选时", "📅", "¥399起", "搬家、开业、婚嫁、动土等重要事项择日。直接给出可用的黄道吉日。\n\n适合：有重要事项需要选好日子的客户。", "1. 下单说明事项和时间范围\n2. 师傅根据八字和黄历择日\n3. 给出2-3个备选吉日\n4. 说明当日注意事项", "• 请提供可选的时间范围\n• 结婚择日建议提前1个月\n• 开业/搬家提前1周即可", null, null);
            case "请符请物":
                return new ServiceInfo("请符请物", "🧿", "¥499起", "太岁符、招财符、桃花符、护身符等。按需求选配，师傅根据个人情况开光加持。\n\n适合：想要随身护佑、化解流年不利、增强特定运势的客户。", "1. 下单说明需求和用途\n2. 师傅推荐适合的符物\n3. 确认后师傅开光加持\n4. 顺丰寄出（含使用说明）", "• 符咒有效期一般为一年\n• 请按说明使用和保管\n• 定制符需提供生辰信息", "talisman_99", "499");
            case "起名改名":
                return new ServiceInfo("起名改名", "✍️", "¥399起", "宝宝起名、成人改名、公司起名、品牌命名。结合五行八字和音韵学。\n\n适合：新生儿取名、想改名转运、新公司/品牌命名的客户。", "1. 下单填写需求和基本信息\n2. 宝宝起名需提供生辰\n3. 师傅给出3-5个备选名\n4. 附每个名字的详细解析", "• 宝宝起名请提供准确出生时间\n• 公司起名请提供行业和期望风格\n• 改名后需自行办理证件变更", null, null);
            case "风水法事":
                return new ServiceInfo("风水法事", "🕯️", "请询价", "还阴债、补财库、超度、祈福转运等法事服务。需要根据具体情况定制方案。\n\n适合：运势持续低迷、有特殊需求需要法事化解的客户。", "1. 先联系客服沟通具体情况\n2. 师傅评估是否需要法事\n3. 确认方案和费用\n4. 择日进行", "• 法事需提前准备，请勿临时约\n• 需提供相关信息\n• 具体费用根据情况而定", null, null);
            default:
                return new ServiceInfo(str, "🎋", "请询价", "专业风水师傅一对一服务，根据您的具体需求定制方案。", "1. 下单填写需求\n2. 师傅联系您沟通\n3. 出具方案", "• 请详细描述您的需求\n• 保持联系方式畅通", null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(ServiceInfo serviceInfo, View view) {
        Intent intent = new Intent(this, (Class<?>) ConsultActivity.class);
        intent.putExtra("service_type", serviceInfo.name);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(ServiceInfo serviceInfo, View view) {
        Intent intent = new Intent(this, (Class<?>) PaymentActivity.class);
        intent.putExtra("product_id", serviceInfo.productId);
        intent.putExtra("product_name", serviceInfo.name);
        intent.putExtra("product_price", serviceInfo.priceNum);
        startActivity(intent);
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_service_detail);
        String stringExtra = getIntent().getStringExtra("service_name");
        if (stringExtra == null) {
            stringExtra = "服务";
        }
        findViewById(R.id.btn_back).setOnClickListener(new i(5, this));
        final ServiceInfo serviceInfo = getServiceInfo(stringExtra);
        ((TextView) findViewById(R.id.tv_title)).setText(serviceInfo.name);
        ((TextView) findViewById(R.id.tv_service_name)).setText(serviceInfo.name);
        ((TextView) findViewById(R.id.tv_icon)).setText(serviceInfo.icon);
        ((TextView) findViewById(R.id.tv_price)).setText(serviceInfo.price);
        ((TextView) findViewById(R.id.tv_desc)).setText(serviceInfo.desc);
        ((TextView) findViewById(R.id.tv_process)).setText(serviceInfo.process);
        ((TextView) findViewById(R.id.tv_notice)).setText(serviceInfo.notice);
        final int i4 = 0;
        findViewById(R.id.btn_book).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.t0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ ServiceDetailActivity f2381f;

            {
                this.f2381f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                ServiceDetailActivity.ServiceInfo serviceInfo2 = serviceInfo;
                ServiceDetailActivity serviceDetailActivity = this.f2381f;
                switch (i5) {
                    case 0:
                        serviceDetailActivity.lambda$onCreate$1(serviceInfo2, view);
                        break;
                    default:
                        serviceDetailActivity.lambda$onCreate$2(serviceInfo2, view);
                        break;
                }
            }
        });
        if (serviceInfo.productId != null) {
            View findViewById = findViewById(R.id.btn_pay);
            findViewById.setVisibility(0);
            ((TextView) findViewById).setText("立即支付 " + serviceInfo.price);
            final int i5 = 1;
            findViewById.setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.t0

                /* renamed from: f, reason: collision with root package name */
                public final /* synthetic */ ServiceDetailActivity f2381f;

                {
                    this.f2381f = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i52 = i5;
                    ServiceDetailActivity.ServiceInfo serviceInfo2 = serviceInfo;
                    ServiceDetailActivity serviceDetailActivity = this.f2381f;
                    switch (i52) {
                        case 0:
                            serviceDetailActivity.lambda$onCreate$1(serviceInfo2, view);
                            break;
                        default:
                            serviceDetailActivity.lambda$onCreate$2(serviceInfo2, view);
                            break;
                    }
                }
            });
        }
    }
}
