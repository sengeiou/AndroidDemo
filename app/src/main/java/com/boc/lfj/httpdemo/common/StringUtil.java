package com.boc.lfj.httpdemo.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 字符串处理类
 */
@SuppressLint({"DefaultLocale", "SimpleDateFormat"})
public class StringUtil {
    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
    public static int BUFFER_SIZE = 512;


    /**
     * �Ƿ�Ϊ��
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !"".equals(s.trim());
    }

    /**
     * ͨ��{n},��ʽ��.
     *
     * @param src
     * @param objects
     * @return
     */
    public static String format(String src, Object... objects) {
        int k = 0;
        for (Object obj : objects) {
            src = src.replace("{" + k + "}", obj.toString());
            k++;
        }
        return src;
    }

    public static String null2Blank(String str) {
        return (str == null ? "" : str);
    }

    public static boolean isNullOrEmpty(String str) {
        return (str == null) || "".equals(str) || "null".equals(str) || "NULL".equals(str) || TextUtils.isEmpty(str);
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        Date time = toDate(sdate);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.format(cal.getTime());
        String paramDate = dateFormater2.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.format(time);
        }
        return ftime;
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.format(today);
            String timeDate = dateFormater2.format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置需要高亮的字
     *
     * @param wholeText    原始字符串
     * @param spanableText 需要高亮的字符串
     * @return 高亮后的字符串
     */
    public static SpannableString getSpanableText(String wholeText, String spanableText) {
        if (TextUtils.isEmpty(wholeText))
            wholeText = "";
        SpannableString spannableString = new SpannableString(wholeText);
        if ("".equals(spanableText))
            return spannableString;
        wholeText = wholeText.toLowerCase();
        spanableText = spanableText.toLowerCase();
        int startPos = wholeText.indexOf(spanableText);
        if (startPos == -1) {
            int tmpLength = spanableText.length();
            String tmpResult = "";
            for (int i = 1; i <= tmpLength; i++) {
                tmpResult = spanableText.substring(0, tmpLength - i);
                int tmpPos = wholeText.indexOf(tmpResult);
                if (tmpPos == -1) {
                    tmpResult = spanableText.substring(i, tmpLength);
                    tmpPos = wholeText.indexOf(tmpResult);
                }
                if (tmpPos != -1)
                    break;
                tmpResult = "";
            }
            if (tmpResult.length() != 0) {
                return getSpanableText(wholeText, tmpResult);
            } else {
                return spannableString;
            }
        }
        int endPos = startPos + spanableText.length();
        do {
            endPos = startPos + spanableText.length();
            spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            startPos = wholeText.indexOf(spanableText, endPos);
        } while (startPos != -1);
        return spannableString;
    }

    /**
     * 去掉字符串首尾，中间的空格，trim()，不仅仅是去掉空格，此处主要是增加去掉中间的空格
     *
     * @param str
     * @return
     */
    public static String removeSpace(String str) {

        if (!TextUtils.isEmpty(str)) {
            return str.trim().replaceAll(" ", "");
        } else {
            return str;
        }
    }

    /**
     * 一行一行的读取asset目录下的txt文本，得到一个数组
     *
     * @param context
     * @param filename
     * @return
     */
    public static List<String> readStringByLine(Context context, final String filename) {
        List<String> strLines = new ArrayList<String>();
        InputStream is;
        try {
            is = context.getAssets().open(filename + ".txt");
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(is));
            String str = null;
            while ((str = bfReader.readLine()) != null) {
                strLines.add(str);
            }
            bfReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strLines;
    }

    /**
     * 针对TextView显示中文中出现的排版错乱问题，通过调用此方法得以解决
     *
     * @param str
     * @return 返回全部为全角字符的字符串
     */
    public static String toDBC(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }

        }
        return new String(c);
    }

    /**
     * ps-20160506---->2016.05.06
     */
    public static String getSimpleDate(String dateStr) {
        String date = dateStr.trim();
        StringBuilder builder = new StringBuilder();
        if (StringUtil.isNullOrEmpty(date) || date.length() < 8) {
            return date;
        } else {
            builder.append(date.substring(0, 4)).append(".").append(date.substring(4, 6)).append(".").append(date.substring(6, 8));
        }
        return builder.toString();
    }
    public static String getSHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName()
                    , PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] signatures = md.digest(cert);
            StringBuffer sha1 = new StringBuffer();
            int i = 0;
            for (byte key : signatures) {
                String appendString = Integer.toHexString(0xFF & key).toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    sha1.append("0");
                sha1.append(appendString);
                if (signatures.length - 1 == i)
                    break;
                sha1.append(":");
                i++;
            }
            Log.e("------------", sha1.toString());
            return sha1.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
