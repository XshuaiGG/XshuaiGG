package cn.hp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将 日期类型 date 转成 字符串显示
 */
public class DateUtils {

    public static String dateToString(Date date){
        if(date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            String string = sdf.format(date);
            return string;
        }else{
            return null;
        }

    }

}
