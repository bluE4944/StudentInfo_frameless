package com.xinan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Date转字符串工具类
 * @author 11940
 */
public class DateUtil {

    /**
     * 将date装换成String
     * 得到像 2020-4-5 这种格式的时间字符串
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return  dateStr;
    }

    /**
     * 将date装换成String
     * 得到像 2020-4-5 14：52：22 这种格式的时间字符串
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return  dateStr;
    }

    /**
     * 将date装换成String
     * 得到像 14：52 这种格式的时间字符串
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String dateStr = sdf.format(date);
        return  dateStr;
    }

    /**
     * 获取系统当前日期，返回Date类型
     * 返回格式是这样的：Mon Nov 26 00:00:00 CST 2018
     * @return
     * @throws ParseException
     */
    public static Date getSystemDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return simpleDateFormat.parse(simpleDateFormat.format(date));
    }


    /**
     * 获取系统当前时间，返回Date类型
     * 返回格式是这样的：Mon Nov 26 11:14:26 CST 2018
     * @return
     * @throws ParseException
     */
    public static Date getSystemTime() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.parse(simpleDateFormat.format(date));
    }

}
