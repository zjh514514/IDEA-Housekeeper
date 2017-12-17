package housekeeper.tools;

import org.junit.Test;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 对日期操作
 */
public class Day {

    /**
     * 获取星期几
     *
     * @param time
     * @return
     */
    public int getWeek(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 增加天数
     *
     * @param time
     * @param n
     * @return
     */
    public Date addDay(Date time, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.DATE, n);// 增加n天，负数为减少
        return c.getTime();
    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat  f = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        System.out.println(getWeek(f.parse("2017-10-23 15:18")));
    }
}
