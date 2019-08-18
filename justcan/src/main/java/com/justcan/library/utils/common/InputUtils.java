package com.justcan.library.utils.common;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by James on 2016/7/28.
 * Note:字符串输入工具类
 */
public class InputUtils {
    private static final int MAX_PROGRESS = 100;

    /**
     * 格式化国际手机号码
     *
     * @param string
     * @param string2
     * @return
     */
    public static String formatCountryCodeAndMobile(String string, String string2) {
        return "+" + string + " " + string2;
    }

    /**
     * 百分比
     *
     * @param n
     * @param n2
     * @return
     */
    public static int getPercentage(int n, int n2) {
        return Math.min((int) (100.0 * (double) n / (double) n2), MAX_PROGRESS);
    }

    public static int getPercentage(long n, long n2) {
        return Math.min((int) (100.0 * (double) n / (double) n2), MAX_PROGRESS);
    }

    /**
     * 是否是中国的手机号
     *
     * @param string
     * @return
     */
    public static boolean isChinaCountryCode(String string) {
        if (TextUtils.isEmpty(string) || !string.equals("86"))
            return false;
        return true;
    }

    /**
     * 判断是否是邮件格式
     *
     * @param string
     * @return
     */
    public static boolean isEmail(String string) {
        return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher((CharSequence) string).matches();
    }

    /**
     * 判断是否是表情的格式
     *
     * @param string
     * @return
     */
    public static boolean isEmoji(String string) {
        return Pattern.compile("/[\u1f60-\u1f64]|[\u2702-\u27b0]|[\u1f68-\u1f6c]|[\u1f30-\u1f70]|[\u2600-\u26ff]/g").matcher(string).matches();
    }

    /**
     * 是否是中国标准手机号
     *
     * @param string
     * @param string2
     * @return
     */
    public static boolean isMobile(String string, String string2) {
        if (InputUtils.isChinaCountryCode(string)) {
            return InputUtils.isMobileNO(string2);
        }
        if (string2.length() < 4)
            return false;
        return true;
    }

    /**
     * 手机号码是否有效
     *
     * @param string
     * @return
     */
    public static boolean isMobileNO(String string) {
        return Pattern.compile("^((13[0-9])|(17[0-9])|(14[0-9])|(15[^4,\\D])|(18[0,0-9]))\\d{8}$").matcher(string).matches();
    }

    /**
     * 昵称是否有效
     *
     * @param string
     * @return
     */
    public static boolean isNickNameVailable(String string) {
        if (string.length() < 2 || string.length() > 20) return false;
        return Pattern.compile("[-a-zA-Z0-9_\u4e00-\u9fa5]+").matcher(string).matches();
    }

    /**
     * 是否是数字
     *
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        return Pattern.compile("^[0-9]*$").matcher(string).matches();
    }

    /**
     * 是否是文本
     *
     * @param string
     * @return
     */
    public static boolean isText(String string) {
        return Pattern.compile("[-a-zA-Z0-9_\u4e00-\u9fa5]+").matcher(string).matches();
    }

    /**
     * 隐藏中间手机号
     */
    public static String hidPhone(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1XXXX$2");
    }
}
