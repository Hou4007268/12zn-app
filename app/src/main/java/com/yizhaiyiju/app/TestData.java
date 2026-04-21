package com.yizhaiyiju.app;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TestData {
    public static String[] ELEMENTS = {"金", "木", "水", "火", "土"};
    public static String[] ELEMENT_EMOJIS = {"⚔️", "🌳", "🌊", "🔥", "⛰️"};
    public static String[] ELEMENT_DESC = {"金主义，你是一个果断坚毅的人，具有强烈的正义感和责任心。你做事有条理，追求完美，但有时过于固执。", "木主仁，你是一个富有同情心的人，善于倾听和理解他人。你有创造力，追求成长，但有时优柔寡断。", "水主智，你是一个聪明灵活的人，善于思考和分析。你适应力强，追求知识，但有时过于敏感。", "火主礼，你是一个热情开朗的人，充满活力和感染力。你善于表达，追求快乐，但有时冲动急躁。", "土主信，你是一个稳重踏实的人，值得信赖。你务实可靠，追求稳定，但有时过于保守。"};
    public static String[] ELEMENT_ADVICE = {"建议：多接触木属性事物（绿色植物、东方方位），可平衡五行。适合从事金融、法律、管理等行业。", "建议：多接触水属性事物（蓝色装饰、北方方位），可滋养木气。适合从事教育、医疗、艺术等行业。", "建议：多接触金属性事物（白色装饰、西方方位），可生水旺运。适合从事科研、IT、咨询等行业。", "建议：多接触土属性事物（黄色装饰、中央方位），可泄火生财。适合从事演艺、餐饮、能源等行业。", "建议：多接触火属性事物（红色装饰、南方方位），可生土旺运。适合从事农业、房地产、服务业等行业。"};
    public static String[][] LUCKY_COLORS = {new String[]{"🔴 红色", "象征热情和好运，今天穿红色能提升自信"}, new String[]{"🟢 绿色", "象征生机和成长，今天绿色能带来平和"}, new String[]{"🔵 蓝色", "象征智慧和冷静，今天蓝色能带来好运"}, new String[]{"🟣 紫色", "象征高贵和神秘，今天紫色能提升魅力"}};
    public static String[][] ZODIAC_FORTUNES = {new String[]{"🐀 鼠", "今天适合处理财务事务，可能会有意外收入。感情方面需要多沟通。", "3", "4", "4", "幸运色：金色，幸运数字：3"}, new String[]{"🐂 牛", "工作上会有贵人相助，适合推进重要项目。注意休息，别太劳累。", "4", "3", "5", "幸运色：红色，幸运数字：8"}, new String[]{"🐅 虎", "今天精力充沛，适合挑战新事物。但要注意控制脾气。", "4", "4", "3", "幸运色：绿色，幸运数字：1"}, new String[]{"🐇 兔", "今天桃花运不错，单身者可能有惊喜。工作平稳发展。", "3", "5", "3", "幸运色：粉色，幸运数字：6"}, new String[]{"🐉 龙", "今天运势很好，做事顺心如意。适合签约、面试等重要事项。", "5", "4", "5", "幸运色：金色，幸运数字：9"}, new String[]{"🐍 蛇", "今天需要谨慎行事，避免冲动决策。多听听他人意见。", "2", "3", "3", "幸运色：紫色，幸运数字：2"}, new String[]{"🐴 马", "今天社交运很好，适合拓展人脉。可能会认识新朋友。", "3", "4", "4", "幸运色：蓝色，幸运数字：7"}, new String[]{"🐑 羊", "今天创意灵感旺盛，适合从事创作工作。感情温馨。", "4", "4", "4", "幸运色：白色，幸运数字：5"}, new String[]{"🐵 猴", "今天运势起伏较大，需要保持冷静。投资需谨慎。", "3", "3", "3", "幸运色：黄色，幸运数字：4"}, new String[]{"🐔 鸡", "今天工作表现突出，可能获得认可。注意身体健康。", "4", "4", "4", "幸运色：红色，幸运数字：8"}, new String[]{"🐕 狗", "今天贵人运强，遇到困难会有人帮忙。适合求人办事。", "4", "3", "5", "幸运色：棕色，幸运数字：3"}, new String[]{"🐖 猪", "今天财运不错，可能会有意外收获。心情愉快。", "5", "4", "4", "幸运色：金色，幸运数字：9"}};
    public static String[] ZODIAC_EMOJIS = {"🐀", "🐂", "🐅", "🐇", "🐉", "🐍", "🐴", "🐑", "🐵", "🐔", "🐕", "🐖"};
    public static String[] ZODIAC_NAMES = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    public static class Question {
        public String[] options;
        public String question;
        public int[] scores;

        public Question(String str, String[] strArr, int[] iArr) {
            this.question = str;
            this.options = strArr;
            this.scores = iArr;
        }
    }

    public static class TestInfo {
        public String description;
        public String emoji;
        public String id;
        public String name;
        public List<Question> questions;

        public TestInfo(String str, String str2, String str3, String str4, List<Question> list) {
            this.id = str;
            this.name = str2;
            this.emoji = str3;
            this.description = str4;
            this.questions = list;
        }
    }

    public static List<TestInfo> getAllTests() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TestInfo("bazi", "八字排盘", "🔮", "四柱八字+大运流年排盘", null));
        arrayList.add(new TestInfo("marriage", "八字合婚", "💕", "男女八字配对+五行分析", null));
        arrayList.add(new TestInfo("name", "姓名分析", "✍️", "三才五格+笔画数理", null));
        arrayList.add(new TestInfo("flying_star", "玄空飞星", "🏠", "九宫飞星吉凶方位", null));
        arrayList.add(new TestInfo("kua_number", "八宅命卦", "🧭", "命卦+四吉方四凶方", null));
        arrayList.add(new TestInfo("fortune", "每日运势", "🌟", "选择生肖查看今日运势", null));
        arrayList.add(new TestInfo("wuxing", "五行测试", "☯️", "测测你的五行属性", getWuxingQuestions()));
        arrayList.add(new TestInfo("romance", "桃花运", "🌸", "测你今年的爱情运势", getRomanceQuestions()));
        arrayList.add(new TestInfo("mental_age", "心理年龄", "🧠", "你的心智年龄是多少？", getMentalAgeQuestions()));
        arrayList.add(new TestInfo("financial", "财富智商", "💰", "测测你的赚钱能力", getFinancialQuestions()));
        arrayList.add(new TestInfo("mbti", "MBTI性格", "🎭", "16种人格类型测试", getMBTIQuestions()));
        arrayList.add(new TestInfo("lucky_color", "幸运色", "🎨", "找到你的幸运颜色", getLuckyColorQuestions()));
        return arrayList;
    }

    public static List<Question> getFinancialQuestions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Question("你每月会存钱吗？", new String[]{"一定会存一部分", "看情况", "很少能存下", "月光族"}, new int[]{4, 3, 2, 1}));
        arrayList.add(new Question("你了解投资理财吗？", new String[]{"很了解，有投资", "了解一些", "听说过", "完全不懂"}, new int[]{4, 3, 2, 1}));
        arrayList.add(new Question("你如何看待消费？", new String[]{"理性消费，量入为出", "偶尔冲动消费", "喜欢就买", "借钱也要买"}, new int[]{4, 3, 2, 1}));
        arrayList.add(new Question("你有副业收入吗？", new String[]{"有多份收入", "有兼职", "想过但没行动", "没有"}, new int[]{4, 3, 2, 1}));
        arrayList.add(new Question("你如何应对财务危机？", new String[]{"有应急基金", "会找办法解决", "向家人求助", "不知道怎么办"}, new int[]{4, 3, 2, 1}));
        return arrayList;
    }

    public static List<Question> getLuckyColorQuestions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Question("你现在的心情是？", new String[]{"平静安宁", "兴奋激动", "有点焦虑", "无聊乏味"}, new int[]{0, 1, 2, 3}));
        arrayList.add(new Question("你希望生活增添什么？", new String[]{"更多温暖", "更多活力", "更多平静", "更多创意"}, new int[]{0, 1, 2, 3}));
        arrayList.add(new Question("你最喜欢的自然景观是？", new String[]{"日出日落", "茂密森林", "蔚蓝大海", "星空夜景"}, new int[]{0, 1, 2, 3}));
        return arrayList;
    }

    public static List<Question> getMBTIQuestions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Question("社交场合中你通常？", new String[]{"主动和很多人交谈", "只和熟悉的人交流"}, new int[]{0, 10}));
        arrayList.add(new Question("你更相信？", new String[]{"亲眼所见的事实", "直觉和第六感"}, new int[]{0, 100}));
        arrayList.add(new Question("做决定时你更看重？", new String[]{"逻辑和客观分析", "感受和他人感受"}, new int[]{0, 1000}));
        arrayList.add(new Question("你更喜欢？", new String[]{"有计划有安排", "灵活随意"}, new int[]{0, 10000}));
        arrayList.add(new Question("聚会结束后你感觉？", new String[]{"精神振奋", "需要独处充电"}, new int[]{0, 10}));
        arrayList.add(new Question("你更关注？", new String[]{"具体的细节", "整体的大局"}, new int[]{0, 100}));
        arrayList.add(new Question("批评别人时你？", new String[]{"直接指出问题", "先考虑对方感受"}, new int[]{0, 1000}));
        arrayList.add(new Question("旅行时你？", new String[]{"提前规划行程", "到了再说"}, new int[]{0, 10000}));
        return arrayList;
    }

    public static List<Question> getMentalAgeQuestions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Question("遇到困难时你通常会？", new String[]{"找人帮忙", "自己想办法", "先逃避再说", "不知道该怎么办"}, new int[]{3, 4, 1, 2}));
        arrayList.add(new Question("你如何看待规则？", new String[]{"必须遵守", "可以适当变通", "规则是用来打破的", "无所谓"}, new int[]{2, 4, 3, 1}));
        arrayList.add(new Question("你平时的娱乐方式是？", new String[]{"看书学习", "追剧玩游戏", "运动健身", "和朋友聚会"}, new int[]{4, 1, 3, 2}));
        arrayList.add(new Question("你如何处理和朋友的矛盾？", new String[]{"主动道歉和好", "等对方先低头", "冷战一段时间", "直接绝交"}, new int[]{4, 2, 1, 3}));
        arrayList.add(new Question("你对未来的规划是？", new String[]{"有详细计划", "大致方向有", "走一步看一步", "没想过"}, new int[]{4, 3, 2, 1}));
        return arrayList;
    }

    public static List<Question> getNameQuestions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Question("你的名字有几个字？", new String[]{"两个字", "三个字", "四个字（复姓）"}, new int[]{2, 3, 1}));
        arrayList.add(new Question("你喜欢自己的名字吗？", new String[]{"很喜欢", "一般般", "不太喜欢", "想改名"}, new int[]{4, 3, 2, 1}));
        arrayList.add(new Question("别人容易记住你的名字吗？", new String[]{"很容易", "一般", "不太容易", "经常被叫错"}, new int[]{4, 3, 2, 1}));
        return arrayList;
    }

    public static List<Question> getRomanceQuestions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Question("你目前的感情状态是？", new String[]{"单身，想找对象", "单身，享受自由", "有喜欢的人", "恋爱中", "已婚"}, new int[]{3, 1, 4, 2, 0}));
        arrayList.add(new Question("你平时社交频率如何？", new String[]{"经常参加聚会", "偶尔和朋友出去", "大部分时间宅在家", "工作太忙没时间"}, new int[]{4, 3, 1, 2}));
        arrayList.add(new Question("你对另一半的要求是？", new String[]{"外表好看最重要", "性格合得来就行", "要有共同兴趣", "经济条件要好"}, new int[]{2, 4, 3, 1}));
        arrayList.add(new Question("你相信缘分吗？", new String[]{"非常相信", "半信半疑", "不太相信", "完全不相信"}, new int[]{4, 3, 2, 1}));
        arrayList.add(new Question("你遇到喜欢的人会主动吗？", new String[]{"会主动出击", "会暗示但不直说", "等对方主动", "不敢表达"}, new int[]{4, 3, 2, 1}));
        return arrayList;
    }

    public static List<Question> getWuxingQuestions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Question("你最喜欢什么季节？", new String[]{"春天 - 万物复苏", "夏天 - 热情似火", "秋天 - 金色收获", "冬天 - 银装素裹"}, new int[]{2, 0, 0, 3, 1, 0, 0, 2, 0, 1, 0, 3, 0, 0, 1, 0, 3, 2, 0, 0}));
        arrayList.clear();
        arrayList.add(new Question("你最喜欢什么颜色？", new String[]{"白色/银色", "绿色/青色", "蓝色/黑色", "红色/紫色", "黄色/棕色"}, new int[]{0, 1, 2, 3, 4}));
        arrayList.add(new Question("你的性格特点是什么？", new String[]{"果断坚毅，有领导力", "仁慈善良，有同情心", "聪明灵活，善于思考", "热情开朗，充满活力", "稳重踏实，值得信赖"}, new int[]{0, 1, 2, 3, 4}));
        arrayList.add(new Question("你擅长什么？", new String[]{"组织管理，决断力强", "创意设计，艺术天赋", "分析研究，逻辑推理", "表达沟通，社交能力强", "执行落实，耐心细致"}, new int[]{0, 1, 2, 3, 4}));
        arrayList.add(new Question("你害怕什么？", new String[]{"被束缚，失去自由", "孤独，没有朋友", "被欺骗，失去信任", "失败，丢面子", "改变，不稳定"}, new int[]{0, 1, 2, 3, 4}));
        arrayList.add(new Question("你理想的居住环境是？", new String[]{"高楼大厦，都市繁华", "靠近自然，绿树成荫", "临水而居，湖光山色", "阳光充足，温暖明亮", "田园乡村，宁静安逸"}, new int[]{0, 1, 2, 3, 4}));
        arrayList.add(new Question("你最重视什么？", new String[]{"规则和秩序", "成长和进步", "智慧和知识", "快乐和热情", "稳定和安全"}, new int[]{0, 1, 2, 3, 4}));
        arrayList.add(new Question("你的身体状况如何？", new String[]{"呼吸系统较弱", "肝脏不太好", "肾脏需要注意", "心脏需要注意", "脾胃不太好"}, new int[]{0, 1, 2, 3, 4}));
        arrayList.add(new Question("你的决策风格是？", new String[]{"快刀斩乱麻", "深思熟虑", "权衡利弊", "凭直觉", "稳妥保守"}, new int[]{0, 1, 2, 3, 4}));
        return arrayList;
    }
}
