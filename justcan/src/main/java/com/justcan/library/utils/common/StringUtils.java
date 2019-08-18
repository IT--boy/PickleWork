package com.justcan.library.utils.common;

import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by James on 2016/8/2.
 * Note:字符串工具类
 */
public class StringUtils {
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

    public static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
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
     * 描述：某个值转换为String，如果为null，这变成""
     *
     * @param obj 指定的对象
     * @return 字符串
     */
    public static String valueOf(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    /**
     * 获取字符串中文字符的长度（每个中文算2个字符）.
     *
     * @param str 指定的字符串
     * @return 中文字符的长度
     */
    public static int chineseLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        if (!isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                /* 获取一个字符 */
                String temp = str.substring(i, i + 1);
                /* 判断是否为中文字符 */
                if (temp.matches(chinese)) {
                    valueLength += 2;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取字符串的长度.
     *
     * @param str 指定的字符串
     * @return 字符串的长度（中文字符计2个）
     */
    public static int strLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                    //中文字符长度为2
                    valueLength += 2;
                } else {
                    //其他字符长度为1
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取指定长度的字符所在位置.
     *
     * @param str  指定的字符串
     * @param maxL 要取到的长度（字符长度，中文字符计2个）
     * @return 字符的所在位置
     */
    public static int subStringLength(String str, int maxL) {
        int currentIndex = 0;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < str.length(); i++) {
            //获取一个字符
            String temp = str.substring(i, i + 1);
            //判断是否为中文字符
            if (temp.matches(chinese)) {
                //中文字符长度为2
                valueLength += 2;
            } else {
                //其他字符长度为1
                valueLength += 1;
            }
            if (valueLength >= maxL) {
                currentIndex = i;
                break;
            }
        }
        return currentIndex;
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
     * 描述：从输入流中获得String.
     *
     * @param is 输入流
     * @return 获得的String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            //最后一个\n删除
            if (sb.indexOf("\n") != -1 && sb.lastIndexOf("\n") == sb.length() - 1) {
                sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 描述：标准化日期时间类型的数据，不足两位的补0.
     *
     * @param dateTime 预格式的时间字符串，如:2012-3-2 12:2:20
     * @return String 格式化好的时间字符串，如:2012-03-20 12:02:20
     */
    public static String dateTimeFormat(String dateTime) {
        StringBuilder sb = new StringBuilder();
        try {
            if (isEmpty(dateTime)) {
                return null;
            }
            String[] dateAndTime = dateTime.split(" ");
            if (dateAndTime.length > 0) {
                for (String str : dateAndTime) {
                    if (str.indexOf("-") != -1) {
                        String[] date = str.split("-");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append("-");
                            }
                        }
                    } else if (str.indexOf(":") != -1) {
                        sb.append(" ");
                        String[] date = str.split(":");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append(":");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    /**
     * 描述：不足2个字符的在前面补“0”.
     *
     * @param str 指定的字符串
     * @return 至少2个字符的字符串
     */
    public static String strFormat2(String str) {
        try {
            if (str.length() <= 1) {
                str = "0" + str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    the str
     * @param length 指定字节长度
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length) {
        return cutString(str, length, "");
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    文本
     * @param length 字节长度
     * @param dot    省略符号
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length, String dot) {
        int strBLen = strlen(str, "GBK");
        if (strBLen <= length) {
            return str;
        }
        int temp = 0;
        StringBuffer sb = new StringBuffer(length);
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(c);
            if (c > 256) {
                temp += 2;
            } else {
                temp += 1;
            }
            if (temp >= length) {
                if (dot != null) {
                    sb.append(dot);
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串从第一个指定字符.
     *
     * @param str1   原文本
     * @param str2   指定字符
     * @param offset 偏移的索引
     * @return 截取后的字符串
     */
    public static String cutStringFromChar(String str1, String str2, int offset) {
        if (isEmpty(str1)) {
            return "";
        }
        int start = str1.indexOf(str2);
        if (start != -1) {
            if (str1.length() > start + offset) {
                return str1.substring(start + offset);
            }
        }
        return "";
    }

    /**
     * 描述：获取字节长度.
     *
     * @param str     文本
     * @param charset 字符集（GBK）
     * @return the int
     */
    public static int strlen(String str, String charset) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = str.getBytes(charset).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
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

    /**
     * 描述：ip地址转换为10进制数.
     *
     * @param ip the ip
     * @return the long
     */
    public static long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16 | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    /**
     * 根据传入的List可以拼接字符串
     *
     * @return
     */
    public static String join(List<String> strs, String spite) {
        String temp = "";
        if (strs != null && strs.size() > 0) {
            for (String str : strs) {
                temp = temp + str + spite;
            }
            temp = temp.substring(0, temp.length() - 1);
        }
        return temp;
    }

    /**
     * 根据传入的List可以拼接字符串
     *
     * @return
     */
    public static String join(int[] strs, String spite) {
        String temp = "";
        if (strs != null && strs.length > 0) {
            for (int str : strs) {
                temp = temp + str + spite;
            }
            temp = temp.substring(0, temp.length() - 1);
        }
        return temp;
    }

    /**
     * 根据传入的List可以拼接字符串
     *
     * @return
     */
    public static String joinIds(List<Integer> strs, String spite) {
        String temp = "";
        if (strs != null && strs.size() > 0) {
            for (Integer str : strs) {
                temp = temp + String.valueOf(str.intValue()) + spite;
            }
            temp = temp.substring(0, temp.length() - 1);
        }
        return temp;
    }

    public static String md5(String param) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(param.getBytes());

            return convertBytesToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String convertBytesToString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = bytes.length;
        int count = 0;

        while (count < length) {
            stringBuilder.append(bytes[count]);
            count++;
        }
        return stringBuilder.toString();
    }

    public static String getStringForDouble(double p1) {
        if (p1 == (double) (long) p1) {
            return String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf((long) p1)});
        }
        return String.format("%s", Double.valueOf(p1));
    }

    /**
     * 转mac地址格式
     */
    public static String convertMac(String mac) {
        StringBuilder sb = new StringBuilder(mac.replace(":", ""));
        sb.insert(2, ":");
        sb.insert(5, ":");
        sb.insert(8, ":");
        sb.insert(11, ":");
        sb.insert(14, ":");
        return sb.toString();
    }

    public static SpannableStringBuilder getSizeSpan(String paramString, int paramInt) {
        SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString);
        localSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(paramInt, true), 0, localSpannableStringBuilder.length(), 0);
        return localSpannableStringBuilder;
    }

    /**
     * 格式化文件大小
     */
    public static String formatSize(long param) {
        String result;
        DecimalFormat v0_1 = new DecimalFormat("#.00");
        if (param != 0) {
            if (param >= 1024) {
                if (param >= 1048576) {
                    if (param >= 1073741824) {
                        result = new StringBuilder().append(v0_1.format((((double) param) / 1073741824.0))).append("G").toString();
                    } else {
                        result = new StringBuilder().append(v0_1.format((((double) param) / 1048576.0))).append("M").toString();
                    }
                } else {
                    result = new StringBuilder().append(v0_1.format((((double) param) / 1024.0))).append("K").toString();
                }
            } else {
                result = new StringBuilder().append(v0_1.format(((double) param))).append("B").toString();
            }
        } else {
            result = "0 M";
        }
        return result;
    }

    //根据数字得到设置对应的文字
    public static void SetIntText(TextView textView, Integer customName, int[] numbers, String[] texts, String defaultValue) {
        if (customName == null) {
            textView.setText(defaultValue);
        } else {
            for (int i = 0; i < numbers.length; i++) {
                if (customName == numbers[i]) {
                    textView.setText(texts[i]);
                    return;
                }
            }
            textView.setText(defaultValue);
        }
    }

    //根据数字返回相应的文字,有默认值
    public static String SetIntText(Integer customName, int[] numbers, String[] texts, String defaultValue) {
        if (customName == null) {
            return defaultValue;
        } else {
            for (int i = 0; i < numbers.length; i++) {
                if (customName == numbers[i]) {
                    return texts[i];
                }
            }
        }
        return defaultValue;
    }

    //根据数字返回相应的文字,有默认值
    public static int SetIntToDrawable(Integer customName, int[] numbers, int[] drawId, int drawDefaultId) {
        if (customName == null) {
            return drawDefaultId;
        } else {
            for (int i = 0; i < numbers.length; i++) {
                if (customName == numbers[i]) {
                    return drawId[i];
                }
            }
        }
        return drawDefaultId;
    }

    //根据String得到设置对应的文字,无默认值
    public static String SetStringListText(String customName, String[] numbers, String[] texts) {
        if (customName == null) {
            return "";
        } else {

            for (int i = 0; i < numbers.length; i++) {
                customName = customName.replace(numbers[i], texts[i]);
            }

        }
        return customName;
    }

    //设置textView文字，有默认值
    public static void SetStringText(TextView textView, String text, String defaultText) {
        textView.setText(text == null ? defaultText : text);
    }

    public static void SetSingleIntText(TextView textView, Integer text, String defaultText) {
        textView.setText(text == null ? defaultText : String.valueOf(text));
    }

    public static String getFloatValue(Float text, String defaultText) {
        String finallyText = text == null ? defaultText : String.valueOf(text);
        return finallyText;
    }

    public static String getIntValue(Integer text, String defaultText) {
        String finallyText = text == null ? defaultText : String.valueOf(text);
        return finallyText;
    }

    //设置textView文字，加入判断是的为null或者"",有默认值
    public static void getStringValue(TextView textView, String text, String defaultText) {
        textView.setText(StringUtils.isEmpty(text) ? defaultText : text);
    }

    //设置textView文字，加入判断是的为null或者"",有默认值
    public static String getStringValue(String text, String defaultText) {
        return StringUtils.isEmpty(text) ? defaultText : text;
    }

    public static String getStringValueSybol(String text, String defaultText) {
        return !StringUtils.isEmpty(text) ? "(" + text + ")" : defaultText;
    }

    //切割文字,为空的时候返回默认文字
    public static String SetSubStringTextWithJudge(String text, String defaultText, int star, int end) {
        if (StringUtils.isEmpty(text)) {
            return defaultText;
        } else if (text.length() < end) {
            return defaultText;
        } else {
            return text.substring(star, end);
        }

    }

}
