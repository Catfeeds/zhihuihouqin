package com.moe.wl.framework.utils;


import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * 创建人：wnj
 * 创建时间：2016/8/11 10:41
 * 描述：数字格式化工具类
 */
public class NumberUtils {

    /**
     * 格式化为指定位小数的数字,返回未使用科学计数法表示的具有指定位数的字符串。
     * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
     * <pre>
     *  "3.1415926", 1          --> 3.1
     *  "3.1415926", 3          --> 3.142
     *  "3.1415926", 4          --> 3.1416
     *  "3.1415926", 6          --> 3.141593
     *  "1234567891234567.1415926", 3   --> 1234567891234567.142
     * </pre>
     * @param number String类型的数字对象
     * @param precision  小数精确度总位数,如2表示两位小数
     * @return 返回数字格式化后的字符串表示形式(注意返回的字符串未使用科学计数法)
     */
    public static String keepPrecision(String number, int precision) {
        if(!number.equals("-") && !TextUtils.isEmpty(number)){
            if(number.contains("-")){
                String newNumber=number.replace("-","");
                BigDecimal bg = new BigDecimal(newNumber);
                return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString();
            }else{
                BigDecimal bg = new BigDecimal(number);
                return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString();
            }
        }

        return number;
    }

    /**
     * 保留几位小数
     * @param number  所要处理的字符串
     * @param precision  保留的几位小数
     * @return
     */
    public static String keepPre(String number, int precision) {
        if ("null".equals(number)){
            return "--";
        }else if ("--".equals(number)){
            return "--";
        }else if (TextUtils.isEmpty(number)){
            return "--";
        }else if ("-".equals(number)){
            return "--";
        }else{
            String num1;
            if (number.contains("%")){
                num1=number.substring(0,number.length()-1);
            }else{
                num1=number;
            }
            String num2;
            if (number.contains("-")){
                num2=num1.replace("-","");
            }else{
                num2=num1;
            }
            BigDecimal bg = new BigDecimal(num2);
            return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
    }

    /**
     * 保留几位小数
     * @param number  所要处理的字符串
     * @param precision  保留的几位小数
     *                   将数值乘以100
     * @return
     */
    public static String keepPre100(String number, int precision) {
        if ("null".equals(number)){
            return "--";
        }else if ("--".equals(number)){
            return "--";
        }else if (TextUtils.isEmpty(number)){
            return "--";
        }else if ("-".equals(number)){
            return "--";
        }else{
            String num1;
            if (number.contains("%")){
                num1=number.substring(0,number.length()-1);
            }else{
                num1=number;
            }
            String num2;
            if (number.contains("-")){
                num2=num1.replace("-","");
            }else{
                num2=num1;
            }

            num2= Float.valueOf(num2)*100+"";

            BigDecimal bg = new BigDecimal(num2);
            return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
    }

    /**
     * 判断大小正负
     * @param number  所要处理的字符串
     * @return  -1 负数 1正负  0-零  ，默认为0
     */
    public static int judgeSize(String number) {
        if (TextUtils.isEmpty(number)){
            return 0;
        }else if ("-".equals(number)){
            return 0;
        }else if ("--".equals(number)){
            return 0;
        }else{
            String num1;
            if (number.contains("%")){
                num1=number.substring(0,number.length()-1);
            }else{
                num1=number;
            }

            if (num1.contains("-")){
                return -1;
            }else if (Float.valueOf(num1)>0){
                return 1;
            }else if (Float.valueOf(num1)<0){
                return -1;
            }else{
                return 0;
            }
        }
    }

    /**
     * 格式化为指定位小数的数字,返回未使用科学计数法表示的具有指定位数的字符串。<br>
     * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
     * 如果给定的数字没有小数，则转换之后将以0填充；例如：int 123  1 --> 123.0<br>
     * <b>注意：</b>如果精度要求比较精确请使用 keepPrecision(String number, int precision)方法
     * @param number String类型的数字对象
     * @param precision  小数精确度总位数,如2表示两位小数
     * @return 返回数字格式化后的字符串表示形式(注意返回的字符串未使用科学计数法)
     */
    public static String keepPrecision(Number number, int precision) {
        return keepPrecision(String.valueOf(number), precision);
    }

    /**
     * 对double类型的数值保留指定位数的小数。<br>
     * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
     * <b>注意：</b>如果精度要求比较精确请使用 keepPrecision(String number, int precision)方法
     * @param number  要保留小数的数字
     * @param precision 小数位数
     * @return double 如果数值较大，则使用科学计数法表示
     */
    public static double keepPrecision(double number, int precision) {
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 对float类型的数值保留指定位数的小数。<br>
     * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
     * <b>注意：</b>如果精度要求比较精确请使用 keepPrecision(String number, int precision)方法
     * @param number  要保留小数的数字
     * @param precision 小数位数
     * @return float 如果数值较大，则使用科学计数法表示
     */
    public static String keepPrecision(float number, int precision) {
        if(number<0){
            return "~";
        }
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

}
