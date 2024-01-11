package com.syy.modulebase.utils;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     author: Blankj
 *     time  : 2016/8/16
 *     desc  : 字符串相关工具类
 * </pre>
 */
public class StringUtils {
    private static final String TAG = "StringUtils";

    private StringUtils() {
        Log.w(TAG, "u can't instantiate me...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    private static int[] pyValue = new int[]{-20319, -20317, -20304, -20295, -20292, -20283, -20265, -20257, -20242,
            -20230, -20051, -20036, -20032,
            -20026, -20002, -19990, -19986, -19982, -19976, -19805, -19784, -19775, -19774, -19763, -19756, -19751,
            -19746, -19741, -19739, -19728,
            -19725, -19715, -19540, -19531, -19525, -19515, -19500, -19484, -19479, -19467, -19289, -19288, -19281,
            -19275, -19270, -19263, -19261,
            -19249, -19243, -19242, -19238, -19235, -19227, -19224, -19218, -19212, -19038, -19023, -19018, -19006,
            -19003, -18996, -18977, -18961,
            -18952, -18783, -18774, -18773, -18763, -18756, -18741, -18735, -18731, -18722, -18710, -18697, -18696,
            -18526, -18518, -18501, -18490,
            -18478, -18463, -18448, -18447, -18446, -18239, -18237, -18231, -18220, -18211, -18201, -18184, -18183,
            -18181, -18012, -17997, -17988,
            -17970, -17964, -17961, -17950, -17947, -17931, -17928, -17922, -17759, -17752, -17733, -17730, -17721,
            -17703, -17701, -17697, -17692,
            -17683, -17676, -17496, -17487, -17482, -17468, -17454, -17433, -17427, -17417, -17202, -17185, -16983,
            -16970, -16942, -16915, -16733,
            -16708, -16706, -16689, -16664, -16657, -16647, -16474, -16470, -16465, -16459, -16452, -16448, -16433,
            -16429, -16427, -16423, -16419,
            -16412, -16407, -16403, -16401, -16393, -16220, -16216, -16212, -16205, -16202, -16187, -16180, -16171,
            -16169, -16158, -16155, -15959,
            -15958, -15944, -15933, -15920, -15915, -15903, -15889, -15878, -15707, -15701, -15681, -15667, -15661,
            -15659, -15652, -15640, -15631,
            -15625, -15454, -15448, -15436, -15435, -15419, -15416, -15408, -15394, -15385, -15377, -15375, -15369,
            -15363, -15362, -15183, -15180,
            -15165, -15158, -15153, -15150, -15149, -15144, -15143, -15141, -15140, -15139, -15128, -15121, -15119,
            -15117, -15110, -15109, -14941,
            -14937, -14933, -14930, -14929, -14928, -14926, -14922, -14921, -14914, -14908, -14902, -14894, -14889,
            -14882, -14873, -14871, -14857,
            -14678, -14674, -14670, -14668, -14663, -14654, -14645, -14630, -14594, -14429, -14407, -14399, -14384,
            -14379, -14368, -14355, -14353,
            -14345, -14170, -14159, -14151, -14149, -14145, -14140, -14137, -14135, -14125, -14123, -14122, -14112,
            -14109, -14099, -14097, -14094,
            -14092, -14090, -14087, -14083, -13917, -13914, -13910, -13907, -13906, -13905, -13896, -13894, -13878,
            -13870, -13859, -13847, -13831,
            -13658, -13611, -13601, -13406, -13404, -13400, -13398, -13395, -13391, -13387, -13383, -13367, -13359,
            -13356, -13343, -13340, -13329,
            -13326, -13318, -13147, -13138, -13120, -13107, -13096, -13095, -13091, -13076, -13068, -13063, -13060,
            -12888, -12875, -12871, -12860,
            -12858, -12852, -12849, -12838, -12831, -12829, -12812, -12802, -12607, -12597, -12594, -12585, -12556,
            -12359, -12346, -12320, -12300,
            -12120, -12099, -12089, -12074, -12067, -12058, -12039, -11867, -11861, -11847, -11831, -11798, -11781,
            -11604, -11589, -11536, -11358,
            -11340, -11339, -11324, -11303, -11097, -11077, -11067, -11055, -11052, -11045, -11041, -11038, -11024,
            -11020, -11019, -11018, -11014,
            -10838, -10832, -10815, -10800, -10790, -10780, -10764, -10587, -10544, -10533, -10519, -10331, -10329,
            -10328, -10322, -10315, -10309,
            -10307, -10296, -10281, -10274, -10270, -10262, -10260, -10256, -10254};

    private static String[] pyStr = new String[]{"a", "ai", "an", "ang", "ao", "ba", "bai", "ban", "bang", "bao",
            "bei", "ben", "beng", "bi", "bian",
            "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai", "can", "cang", "cao", "ce", "ceng", "cha", "chai",
            "chan", "chang", "chao", "che",
            "chen", "cheng", "chi", "chong", "chou", "chu", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci",
            "cong", "cou", "cu", "cuan",
            "cui", "cun", "cuo", "da", "dai", "dan", "dang", "dao", "de", "deng", "di", "dian", "diao", "die",
            "ding", "diu", "dong", "dou", "du",
            "duan", "dui", "dun", "duo", "e", "en", "er", "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou",
            "fu", "ga", "gai", "gan", "gang",
            "gao", "ge", "gei", "gen", "geng", "gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun",
            "guo", "ha", "hai", "han", "hang",
            "hao", "he", "hei", "hen", "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui", "hun",
            "huo", "ji", "jia", "jian",
            "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan", "jue", "jun", "ka", "kai", "kan",
            "kang", "kao", "ke", "ken",
            "keng", "kong", "kou", "ku", "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la", "lai", "lan",
            "lang", "lao", "le", "lei", "leng",
            "li", "lia", "lian", "liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu", "lv", "luan",
            "lue", "lun", "luo", "ma", "mai",
            "man", "mang", "mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie", "min", "ming", "miu",
            "mo", "mou", "mu", "na", "nai",
            "nan", "nang", "nao", "ne", "nei", "nen", "neng", "ni", "nian", "niang", "niao", "nie", "nin", "ning",
            "niu", "nong", "nu", "nv", "nuan",
            "nue", "nuo", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei", "pen", "peng", "pi", "pian", "piao",
            "pie", "pin", "ping", "po", "pu",
            "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing", "qiong", "qiu", "qu", "quan", "que", "qun",
            "ran", "rang", "rao", "re",
            "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui", "run", "ruo", "sa", "sai", "san", "sang", "sao",
            "se", "sen", "seng", "sha",
            "shai", "shan", "shang", "shao", "she", "shen", "sheng", "shi", "shou", "shu", "shua", "shuai", "shuan",
            "shuang", "shui", "shun",
            "shuo", "si", "song", "sou", "su", "suan", "sui", "sun", "suo", "ta", "tai", "tan", "tang", "tao", "te",
            "teng", "ti", "tian", "tiao",
            "tie", "ting", "tong", "tou", "tu", "tuan", "tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei",
            "wen", "weng", "wo", "wu", "xi",
            "xia", "xian", "xiang", "xiao", "xie", "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya",
            "yan", "yang", "yao", "ye", "yi",
            "yin", "ying", "yo", "yong", "you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang", "zao", "ze",
            "zei", "zen", "zeng", "zha",
            "zhai", "zhan", "zhang", "zhao", "zhe", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu", "zhua", "zhuai",
            "zhuan", "zhuang", "zhui",
            "zhun", "zhuo", "zi", "zong", "zou", "zu", "zuan", "zui", "zun", "zuo"};

    /**
     * 单个汉字转成ASCII码
     *
     * @param s 单个汉字字符串
     * @return 如果字符串长度是1返回的是对应的ascii码，否则返回-1
     */
    private static int oneCn2ASCII(String s) {
        if (s.length() != 1) return -1;
        int ascii = 0;
        try {
            byte[] bytes = s.getBytes("GB2312");
            if (bytes.length == 1) {
                ascii = bytes[0];
            } else if (bytes.length == 2) {
                int highByte = 256 + bytes[0];
                int lowByte = 256 + bytes[1];
                ascii = (256 * highByte + lowByte) - 256 * 256;
            } else {
                Log.e(TAG, "Illegal resource string");
            }
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "getAppCertificate exception=" + e.toString());
        }
        return ascii;
    }

    /**
     * 单个汉字转成拼音
     *
     * @param s 单个汉字字符串
     * @return 如果字符串长度是1返回的是对应的拼音，否则返回{@code null}
     */
    private static String oneCn2PY(String s) {
        int ascii = oneCn2ASCII(s);
        if (ascii == -1) return null;
        String ret = null;
        if (0 <= ascii && ascii <= 127) {
            ret = String.valueOf((char) ascii);
        } else {
            for (int i = pyValue.length - 1; i >= 0; i--) {
                if (pyValue[i] <= ascii) {
                    ret = pyStr[i];
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 获得第一个汉字首字母
     *
     * @param s 单个汉字字符串
     * @return 拼音
     */
    public static String getPYFirstLetter(String s) {
        if (isSpace(s)) return "";
        String first, py;
        first = s.substring(0, 1);
        py = oneCn2PY(first);
        if (py == null) return null;
        return py.substring(0, 1);
    }

    /**
     * 中文转拼音
     *
     * @param s 汉字字符串
     * @return 拼音
     */
    public static String cn2PY(String s) {
        String hz, py;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            hz = s.substring(i, i + 1);
            py = oneCn2PY(hz);
            if (py == null) {
                py = "?";
            }
            sb.append(py);
        }
        return sb.toString();
    }

    /**
     * 是否是中文
     */
    public static boolean IsChinese(String str) {
        String regex = "^[\u4e00-\u9fa5\uFE30-\uFFA0]+$";
        return match(regex, str);
    }

    /**
     * 正则匹配
     *
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkByLowerCase(String src, String ori) {
        if (isEmpty(src) || isEmpty(ori)) {
            return false;
        }

        if (src.toLowerCase().equals(ori.toLowerCase())) {
            return true;
        }

        return false;
    }

    public static boolean isNull(Collection list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 关键字高亮变色
     *
     * @param color   变化的色值
     * @param text    文字
     * @param keyword 文字中的关键字
     * @return
     */
    public static SpannableString matcherSearchTitle(int color, String text,
                                                     String keyword) {
        SpannableString s = new SpannableString(text);
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    /**
     * 多个关键字高亮变色
     *
     * @param color   变化的色值
     * @param text    文字
     * @param keyword 文字中的关键字数组
     * @return
     */
    public static SpannableString matcherSearchTitle(int color, String text,
                                                     String[] keyword) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyword.length; i++) {
            Pattern p = Pattern.compile(keyword[i]);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color), start, end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }

    /**
     * 版本对比
     *
     * @return int
     */
    public static int compare(String sourceTime, String targetTime) {
        sourceTime = sourceTime.replace(":", "");
        targetTime = targetTime.replace(":", "");
        int needUpdate = 0; // 相等

        for (int i = 0; i < sourceTime.length(); i++) {
            Integer sI = Integer.valueOf(sourceTime.charAt(i));
            Integer tI = Integer.valueOf(targetTime.charAt(i));
            if (sI < tI) {
                needUpdate = -1;//小于
                break;
            } else if (sI > tI) {
                needUpdate = 1;//大于
                break;
            }
        }
        return needUpdate;
    }

    /**
     * 身份证证件显示
     *
     * @param string
     * @return
     */
    public static String getIDCardString(String string) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        if (string.length() < 10) {
            return string;
        }
        String temp = string.substring(0, 5) + "********" + string.substring(string.length() - 4, string.length());
        return temp;
    }

    /**
     * 判断两个枚举类型是否相同
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 生成随机的n位 数字字符
     *
     * @param n
     * @return
     */
    public static String genRandomString(int n) {
        return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, n - 1)));
    }

    /**
     * 根据string.xml资源格式化字符串
     *
     * @param context
     * @param resource
     * @param args
     * @return
     */
    public static String formatResourceString(Context context, int resource, Object... args) {
        String str = context.getResources().getString(resource);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return String.format(str, args);
    }

    public static String getRandomString(int length) {
        //随机字符串的随机字符库
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    public static String getRandomNumber(int length) {
        //随机字符串的随机字符库
        String KeyString = "0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    /**
     * 描述：将null转化为“”.
     *
     * @param str 指定的字符串
     * @return 字符串的String类型
     */
    public static String parseEmpty(String str) {
        if (str == null || "null".equals(str.trim())) {
            str = "";
        }
        return str.trim();
    }

    //将一个String转成list集合
    public static ArrayList<String> getList(String string) {
        ArrayList<String> list = new ArrayList<>();
        if (!isEmpty(string)) {
            String[] array = string.split(";");
            for (int j = 0; j < array.length; j++) {
                list.add(array[j]);
            }
        }
        return list;
    }

    /**
     * 描述：判断一个字符串是否为null或空值.
     *
     * @param str 指定的字符串
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 描述：判断是否由字母,数组或符号两种或以上组成
     *
     * @param str 指定的字符串
     * @return 是否由字母, 数组或符号两种或以上组成:是为true，否则false
     */
    public static Boolean isTruePassword(String str) {
        Boolean isTrue = false;
        String password = "^(?![a-z]+$)(?![A-Z]+$)(?![0-9]+$)[0-9a-zA-Z\\W]\\S{6,18}$";
        if (str.matches(password)) {
            isTrue = true;
        }
        return isTrue;
    }

    /**
     * 描述：手机号格式验证.
     *
     * @param str 指定的手机号码字符串
     * @return 是否为手机号码格式:是为true，否则false
     */
    public static Boolean isMobileNo(String str) {
        Boolean isMobileNo = false;
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(str);
            isMobileNo = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isMobileNo;
    }

    /**
     * 描述：是否只是字母和数字.
     *
     * @param str 指定的字符串
     * @return 是否只是字母和数字:是为true，否则false
     */
    public static Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String expr = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * 描述：是否只是数字.
     *
     * @param str 指定的字符串
     * @return 是否只是数字:是为true，否则false
     */
    public static Boolean isNumber(String str) {
        Boolean isNumber = false;
        String expr = "^[0-9]+$";
        if (str.matches(expr)) {
            isNumber = true;
        }
        return isNumber;
    }

    /**
     * 描述：是否是邮箱.
     *
     * @param str 指定的字符串
     * @return 是否是邮箱:是为true，否则false
     */
    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

    /**
     * 描述：是否是中文.
     *
     * @param str 指定的字符串
     * @return 是否是中文:是为true，否则false
     */
    public static Boolean isChinese(String str) {
        Boolean isChinese = true;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                } else {
                    isChinese = false;
                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * 获取大小的描述.
     *
     * @param size 字节个数
     * @return 大小的描述
     */
    public static String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                //size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    //size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    //将Unicode转为中文
    public static String decodeUnicode(String str) {
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher(str);
        int start = 0;
        int start2 = 0;
        StringBuffer sb = new StringBuffer();
        while (m.find(start)) {
            start2 = m.start();
            if (start2 > start) {
                String seg = str.substring(start, start2);
                sb.append(seg);
            }
            String code = m.group(1);
            int i = Integer.valueOf(code, 16);
            byte[] bb = new byte[4];
            bb[0] = (byte) ((i >> 8) & 0xFF);
            bb[1] = (byte) (i & 0xFF);
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append(String.valueOf(set.decode(b)).trim());
            start = m.end();
        }
        start2 = str.length();
        if (start2 > start) {
            String seg = str.substring(start, start2);
            sb.append(seg);
        }
        return sb.toString();
    }

    public static String StripHT(String strHtml) {
//        if(StringUtils.isBlank(strHtml)) {
//            return "";
//        }
        String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); // 剔出<html>的标签
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");// 去除字符串中的空格,回车,换行符,制表符
        return txtcontent;
    }

    public static String Html2Text(String inputString) {
        if (inputString == null)
            return "";
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        Pattern p_special;
        Matcher m_special;
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            // 定义HTML标签的正则表达式
            String regEx_html = "<[^>]+>";
            // 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            String regEx_special = "\\&[a-zA-Z]{1,10};";

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
            m_special = p_special.matcher(htmlStr);
            htmlStr = m_special.replaceAll(""); // 过滤特殊标签
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }

    public static String[] returnImageUrlsFromHtml(String desr) {
        List<String> imageSrcList = new ArrayList<String>();
        String htmlCode = desr;
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);


        }
        if (imageSrcList == null || imageSrcList.size() == 0) {
            Log.e("xyz", "资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }

    /**
     * 积分金钱组合统一形式
     *
     * @param score
     * @param money
     * @return
     */
    public static String getScoreAndMoney(int score, double money) {
        if (score == 0) {
            return getSymbolMoney(money);
        }

        if (Math.abs(money) < 0.001)// double是否等于0是不能用“d==0” , 需要加上精度, money 的精度是0.01
            return String.valueOf(score);
        return score + " + ¥" + money;
    }

    /**
     * 带括号带积分金钱组合
     *
     * @param score
     * @param money
     * @return
     */
    public static String getScoreSymbolMoney(int score, double money) {
        if (score == 0) {
            return getSymbolMoney(money);
        }
        return score + "（¥" + money + "）";
    }

    public static String getSymbolMoney(double money) {
        return "¥ " + numberFormatMoneyForShop(money);
    }

    public static String getSymbolMoney(String money) {
        double m = Double.valueOf(money);
        DecimalFormat df = new DecimalFormat("¥ ###,###,##0.00");
        return df.format(m);
    }

    public static String getSymbolMoneyWithBlank(int money) {
        return " ¥ " + money;
    }


    public static String getSymbolMoneyForShop(double money) {
        return "¥" + numberFormatMoneyForShop(money);
    }


    public static String numberFormatMoneyForShop(double money) {
        if (money > 0) {
            BigDecimal bd = new BigDecimal(money);
            DecimalFormat df = new DecimalFormat("###,###,###.##");
            df.setMaximumFractionDigits(2);
            return df.format(bd);
        }
        return "0";
    }

    /**
     * 货币格式
     * ￥12,345,678
     * 这个在华为手机显示单杠￥
     * 这个才能显示双杠¥
     */

    public static String numberFormatMoneyInt(double money) {
        if (money > 0) {
            if (money == (int) money) {
                //不包含小数值
                BigDecimal bd = new BigDecimal(money);
                DecimalFormat df = new DecimalFormat("¥ ###,###,###");
                df.setMaximumFractionDigits(0);
                return df.format(bd);
            } else {
                //包含小数
                DecimalFormat df = new DecimalFormat("¥ ###,###,##0.00");
                return df.format(money);
            }
        }
        return "¥ 0";
    }

    public static String numberFormatMoneyString(String moneyStr) {
        double money = 0;
        if (!TextUtils.isEmpty(moneyStr)) {
            money = Double.parseDouble(moneyStr);
        }
        return numberFormatMoneyInt(money);
    }

    public static String numberFormatMoneyIntWithoutSymbol(double money) {
        if (money > 0) {
            if (money == (int) money) {
                //不包含小数值
                BigDecimal bd = new BigDecimal(money);
                DecimalFormat df = new DecimalFormat("###,###,###");
                df.setMaximumFractionDigits(0);
                return df.format(bd);
            } else {
                //包含小数
                DecimalFormat df = new DecimalFormat("###,###,##0.00");
                return df.format(money);
            }
        }
        return "0";
    }

    public static String showMoneyFormat(double money) {
        if (money > 0) {
            DecimalFormat df = new DecimalFormat("¥,###,##0.00");
            return df.format(df);
        } else {
            return "¥ 0";
        }
    }


    public static String bigDecimalMoneyInt(double money) {
        if (money > 0) {
            BigDecimal bd = new BigDecimal(money);
            if (money >= 1) {//因为购车下订需要精确到整数，整数以下只为方便测试
                DecimalFormat df = new DecimalFormat(",###,###");
                df.setMaximumFractionDigits(0);
                return df.format(bd);
            } else {
                DecimalFormat df = new DecimalFormat(",###,##0.00");
                return df.format(bd);
            }
        }
        return "";
    }

    /**
     * 货币精确位数
     * https://my.oschina.net/buwei/blog/222044
     * out: 12,345,6789.00
     */
    public static String bigDecimalMoney(String money) {
        if (!TextUtils.isEmpty(money)) {
            BigDecimal bd = new BigDecimal(Integer.parseInt(money));
            DecimalFormat df = new DecimalFormat(",###,####.00");
            return df.format(bd);
        }
        return "";
    }

    public static String getQuestionResolution(String html) {
        String regex = "\"([\\s\\S]*?)\"";
        Matcher matcher = Pattern.compile(regex).matcher(html);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "";
    }


    public static String parseContentSlashNToBr(String content) {
        if (!TextUtils.isEmpty(content)) {
            content = content.replace("\n", "<br>");
        }
        return content;
    }

    public static String parseContentDeleteBr(String content) {
        if (!TextUtils.isEmpty(content)) {
            content = content.replace("<br>", "");
        }
        return content;
    }

    public static String getUrlParameterReg(String url, String name) {

        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = TruncateUrlPage(url);
        if (strUrlParam == null) {
            return "";
        }
        //每个键值为一组 www.2cto.com
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest.get(name);
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 手机号中间四位隐藏
     *
     * @param phone 手机号
     */
    public static String phoneHide(String phone) {
        String phoneHide = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phoneHide;
    }

    /**
     * 身份证中间8位隐藏
     * 隐藏出生年月
     *
     * @param idCard 身份证号
     */

    public static String idCardHide(String idCard) {
        String idCardHide = idCard.replaceAll("(\\d{6})\\d{8}(\\w{4})", "$1*****$2");
        return idCardHide;
    }

    /**
     *      * 判断一个字符串是否为url
     *      * @param str String 字符串
     *      * @return boolean 是否为url
     *      * @author peng1 chen
     *      *
     **/
    public static boolean isURL(String str) {
        //转换为小写
        str = str.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)" //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184  
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.  
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名  
                + "[a-z]{2,6})" // first level domain- .com or .museum  
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name  
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        return str.matches(regex);
    }


    public static String urlAddScheme(String url) {

        Uri mUri = Uri.parse(url);

        // 协议
        String scheme = mUri.getScheme();
        // 域名+端口号+路径+参数
        String scheme_specific_part = mUri.getSchemeSpecificPart();
        // 域名+端口号
        String authority = mUri.getAuthority();
        // fragment
        String fragment = mUri.getFragment();
        // 域名
        String host = mUri.getHost();
        // 端口号
        int port = mUri.getPort();
        // 路径
        String path = mUri.getPath();
        // 参数
        String query = mUri.getQuery();

        /*Log.e("xyz", "======协议===scheme ==" + scheme);
        Log.e("xyz", "======域名+端口号+路径+参数==scheme_specific_part ===" + scheme_specific_part);
        Log.e("xyz", "======域名+端口号===authority ==" + authority);
        Log.e("xyz", "======fragment===fragment ==" + fragment);
        Log.e("xyz", "======域名===host ==" + host);
        Log.e("xyz", "======端口号===port ==" + port);
        Log.e("xyz", "======路径===path ==" + path);
        Log.e("xyz", "======参数===query ==" + query);*/

        if (mUri != null && TextUtils.isEmpty(scheme)) {
            url = "http://" + url;
        }

        return url;
    }


    public static String getValueByKey(String key, Map<String, byte[]> customInfo) {
        if (customInfo != null && customInfo.containsKey(key)) {
            String value = new String(customInfo.get(key));
            //Log.e("xyz","key:"+key+",value:"+value);
            return value;
        }
        return "";
    }

    /**
     * @param targetStr 要处理的字符串
     * @description 切割字符串，将文本和img标签碎片化，如"ab<img>cd"转换为"ab"、"<img>"、"cd"
     */
    public static List<String> cutStringByImgTag(String targetStr) {
        List<String> splitTextList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("(<img.*?src=\\\"(.*?)\\\".*?>)|(<video.*?</video>)");
        Matcher matcher = pattern.matcher(targetStr);
        int lastIndex = 0;
        while (matcher.find()) {
            if (matcher.start() > lastIndex) {
                splitTextList.add(targetStr.substring(lastIndex, matcher.start()));
            }
            splitTextList.add(targetStr.substring(matcher.start(), matcher.end()));
            lastIndex = matcher.end();
        }
        if (lastIndex != targetStr.length()) {
            splitTextList.add(targetStr.substring(lastIndex, targetStr.length()));
        }
        return splitTextList;
    }

    /**
     * 获取img标签中的src值
     *
     * @param content
     * @return
     */
    public static String getImgSrc(String content) {
        String str_src = null;
        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/>   <img alt="" src="1.jpg"></img>     <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);

                //开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    str_src = m_src.group(3);
                }
                //结束匹配<img />标签中的src

                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return str_src;
    }

    /**
     * 获取HTML标签中图片或者视频地址
     *
     * @param content
     * @return
     */
    public static String getImgOrVideoSrc(String content) {
        Document doc = Jsoup.parse(content);
        String imgSrc = doc.select("img").attr("src");
        if (!imgSrc.isEmpty()) {
            return imgSrc;
        }
        String videoSrc = doc.select("video").attr("src");
        if (!videoSrc.isEmpty()) {
            return videoSrc;
        }
        //video格式可能是<video src="url"></video>或者<video><source src="url" /></video>
        return doc.select("source").attr("src");
    }

    /**
     * @param targetStr 要处理的字符串
     * @description 切割字符串，将文本和img标签碎片化，如"ab<img>cd"转换为"ab"、"<img>"、"cd"
     */
    public static List<String> cutStringByATag(String targetStr) {
        List<String> splitTextList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("<a.*?href=\\\"(.*?)\\\".*?>");
        Matcher matcher = pattern.matcher(targetStr);
        int lastIndex = 0;
        while (matcher.find()) {
            if (matcher.start() > lastIndex) {
                splitTextList.add(targetStr.substring(lastIndex, matcher.start()));
            }
            splitTextList.add(targetStr.substring(matcher.start(), matcher.end()));
            lastIndex = matcher.end();
        }
        return splitTextList;
    }

    public static List<String> cutStringByBrTag(String targetStr) {
        List<String> splitTextList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("(<br>\n)+");
        Matcher matcher = pattern.matcher(targetStr);
        int lastIndex = 0;
        while (matcher.find()) {
            if (matcher.start() > lastIndex) {
                splitTextList.add(targetStr.substring(lastIndex, matcher.start()));
            }
            splitTextList.add(targetStr.substring(matcher.start(), matcher.end()));
            lastIndex = matcher.end();
        }
        if (targetStr.length() > lastIndex) {
            splitTextList.add(targetStr.substring(lastIndex));
        }
        return splitTextList;
    }


    /**
     * 关键字高亮显示
     *
     * @param target 需要高亮的关键字
     * @param text   需要显示的文字
     * @return spannable 处理完后的结果，记得不要toString()，否则没有效果
     * SpannableStringBuilder textString = TextUtilTools.highlight(item.getItemName(), KnowledgeActivity.searchKey);
     * vHolder.tv_itemName_search.setText(textString);
     */
    public static SpannableStringBuilder highlight(String text, String target) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        CharacterStyle span = null;

        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher(text);
        while (m.find()) {
            span = new ForegroundColorSpan(Color.parseColor("#EE5C42"));// 需要重复！
            spannable.setSpan(span, m.start(), m.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    /**
     * 从html文本中提取图片地址，或者文本内容
     *
     * @param html       传入html文本
     * @param isGetImage true获取图片，false获取文本
     * @return
     */
    public static ArrayList<String> getTextFromHtml(String html, boolean isGetImage) {
        ArrayList<String> imageList = new ArrayList<>();
        ArrayList<String> textList = new ArrayList<>();
        //根据img标签分割出图片和字符串
        List<String> list = cutStringByImgTag(html);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i);
            if (text.contains("<img") && text.contains("src=")) {
                //从img标签中获取图片地址
                String imagePath = getImgSrc(text);
                imageList.add(imagePath);
            } else {
                textList.add(text);
            }
        }
        //判断是获取图片还是文本
        if (isGetImage) {
            return imageList;
        } else {
            return textList;
        }
    }


    /**
     * 手机号码加空格格式化，必须是11位
     *
     * @param phone
     * @return
     */
    public static String formatPhoneNum(String phone) {
        if (phone == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int length = phone.length();
        if (length != 11) {
            return phone;
        }
        for (int i = 0; i < length; i++) {
            builder.append(phone.charAt(i));
            //第三个，第八个添加空格
            if (i == 2 || i == 6) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}