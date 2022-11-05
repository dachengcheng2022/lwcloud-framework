package com.bt.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by amis on 17-8-26.
 */
public class CharUtil {
    public static String hideShowCharsForString(String str, int hidenLength,char hidnStr) {
        if (StringUtils.isBlank(str)) return str;

        int length = str.length();
        if (length < (hidenLength + 1)) return str;
        String newStr = str;
        int restLent = length - hidenLength;
        int divisor = restLent/2;
        int remainder = restLent%2;
        int prefix = (remainder==0)?divisor:(divisor+1);
        int suffix = divisor;
        StringBuffer sb = new StringBuffer("");
        for(int i=0;i<hidenLength;i++){
            sb.append(hidnStr);
        }
        newStr = newStr.substring(0,prefix)+sb.toString()+newStr.substring(prefix+hidenLength,length);
        return newStr;
    }


    // 根据Unicode编码完美的判断中文汉字和符号
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByREG(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).find();
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByName(String str) {
        if (str == null) {
            return false;
        }
        // 大小写不同：\\p 表示包含，\\P 表示不包含
        // \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
        String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str.trim()).find();
    }
    
    public static String truncateStr(String str, int maxLength) {
    	if (StringUtils.isEmpty(str)) {
    		return str;
    	}
    	int strLen = str.length();
    	if (strLen > maxLength) {
    		return str.substring(0, maxLength);
    	}
    	return str;
    }

    public static String getRandomNum(Integer num) {
        String base = "0123456789";
//        return "123456";
        return getRandomCode(base, num);
    }

    public static String getRandomCode(String base, int length) {

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getOrderId(String prefix){
        String body = String.valueOf(new Date().getTime());
        return prefix + body + getRandomNumber(10,99);
    }

    private static int getRandomNumber(int from, int to) {
        float a = from + (to - from) * (new Random().nextFloat());
        int b = (int) a;
        return ((a - b) > 0.5 ? 1 : 0) + b;
    }
}
