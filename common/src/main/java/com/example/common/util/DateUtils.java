package com.example.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhuhui bao
 * @date: 16:37 2020/7/18
 **/
@Slf4j
public class DateUtils {
    public static void main(String[] args) {
        LocalDate ld = str2localDate("20180601", "yyyyMMdd");
        ld = parseStartDate("0506");

        System.out.println(ld);

    }

    /**
     * 判断两个日期是否在同一天
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一天 false:不是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(date1).equals(sdf.format(date2));
    }

    /**
     * 判断两个日期是否在同一周里
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一周 false:不是同一周
     */
    public static boolean isSameWeek(Date date1, Date date2) {
        boolean isSameWeek = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        if (sdf.format(date1).equals(sdf.format(date2))) {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal1.setFirstDayOfWeek(Calendar.MONDAY);
            cal2.setFirstDayOfWeek(Calendar.MONDAY);
            cal1.setTime(date1);
            cal2.setTime(date2);

            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                isSameWeek = true;
            }
        }
        return isSameWeek;
    }

    /**
     * 判断两个日期是否在同一月
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一月 false:不是同一月
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        return sdf.format(date1).equals(sdf.format(date2));
    }

    /**
     * 判断两个日期是否在同一月(指定月的开始日)
     * @param day
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一月 false:不是同一月
     */
    public static boolean isSameMonth(int day, Date date1, Date date2) {

        boolean isSameMonth = false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(date1);
        cal2.setTime(date2);

        int date1Month = cal1.get(Calendar.MONTH);
        int date1Day = cal1.get(Calendar.DAY_OF_MONTH);
        // 小于指定日，认为是上个月
        if (date1Day < day) {
            date1Month -= 1;
        }

        int date2Month = cal2.get(Calendar.MONTH);
        int date2Day = cal2.get(Calendar.DAY_OF_MONTH);
        // 小于指定日，认为是上个月
        if (date2Day < day) {
            date2Month -= 1;
        }
        if (date1Month == date2Month) {
            isSameMonth = true;
        }
        return isSameMonth;
    }

    /**
     * 判断两个日期是否在同一季度里(按自然日期)
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一季度 false:不是同一季度
     */
    public static boolean isSameQuarter(Date date1, Date date2) {

        // 存放每月所在季度
        Map<Integer, String> quarterMap = new HashMap<>(12);

        for (int i=0; i<12; i++) {
            quarterMap.put(i, String.valueOf(i / 3 + 1));
        }

        boolean isSameQuarter = false;

        if (isSameYear(date1, date2)) {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal1.setTime(date1);
            cal2.setTime(date2);

            int date1Month = cal1.get(Calendar.MONTH);
            int date2Month = cal2.get(Calendar.MONTH);

            if (quarterMap.get(date1Month).equals(quarterMap.get(date2Month))) {
                isSameQuarter = true;
            }
        }
        return isSameQuarter;
    }

    /**
     * 判断两个日期是否在同一季度里（按指定日期）
     * @param startDate (MM/dd)
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一季度 false:不是同一季度
     */
    /*public static boolean isSameQuarter(String startDate, Date date1, Date date2) {
        ChronoUnit.MONTHS.
        boolean isSameQuarter = false;

        if (isSameYear(date1, date2)) {
            int month;
            int startMonth = Integer.parseInt(startDate.substring(0,2));
            String day = startDate.substring(2);
            int dayInt = Integer.parseInt(day);
            Map<String, Integer> map = new HashMap<>();
            for (int i=0; i <=11; i++) {

                month = (startMonth+i) % 12 == 0 ? 12 : (startMonth+i) % 12;
                map.put(month + day, i/3);
            }

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal1.setTime(date1);
            cal2.setTime(date2);

            cal1.add(Calendar.MONTH, 7);
            Date d = cal1.getTime();

            int date1DayInt = cal1.get(Calendar.DAY_OF_MONTH);
            int date2DayInt = cal2.get(Calendar.DAY_OF_MONTH);

            int date1MonthInt = cal1.get(Calendar.MONTH) + 1;
            int date2MonthInt = cal2.get(Calendar.MONTH) + 1;

            date1MonthInt = date1DayInt < dayInt ? (date1MonthInt - 1) : date1MonthInt;
            date2MonthInt = date2DayInt < dayInt ? (date2MonthInt - 1) : date2MonthInt;

            if (map.get(date1MonthInt + day).equals(map.get(date2MonthInt + day))) {
                isSameQuarter = true;
            }
        }

        return isSameQuarter;
    }*/


    /**
     * 判断两个日期是否在同一年里
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一年 false:不是同一年
     */
    public static boolean isSameYear(Date date1, Date date2) {

        boolean isSameYear = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        if (sdf.format(date1).equals(sdf.format(date2))) {
            isSameYear = true;
        }
        return isSameYear;
    }

    /**
     * 判断两个日期是否在同一季度里（按指定日期）
     * @param startDate (MMdd)
     * @param date1 日期1
     * @param date2 日期2
     * @return true:同一季度 false:不是同一季度
     */
    public static boolean isSameQuarter(String startDate, Date date1, Date date2) {
        boolean isSameQuarter = false;

        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (localDate1.getYear() == localDate2.getYear()) {

            // 累计周期开始日转成localDate
            LocalDate ldStartDate = parseStartDate(startDate);

            Period date1Period = Period.between(ldStartDate, localDate1);
            Period date2Period = Period.between(ldStartDate, localDate2);

            int date1Month = date1Period.getMonths();
            int date2Month = date2Period.getMonths();

            // 两个日期在开始时间的同一侧
            if (date1Month * date2Month >= 0 ) {

                date1Month = Math.abs(date1Month);
                // 业务中结束日是开始日-1
                int date1Days = Math.abs(date1Period.getDays());
                date1Month = date1Days >= 0 ? date1Month + 1 : date1Month;

                date2Month = Math.abs(date2Month);
                int date2Days = Math.abs(date2Period.getDays());
                date2Month = date2Days >= 0 ? date2Month + 1 : date2Month;

                int p1 = date1Month % 3 > 0 ? date1Month/3+1 : date1Month/3;
                int p2 = date2Month % 3 > 0 ? date2Month/3+1 : date2Month/3;
                if (p1 == p2) {
                    isSameQuarter = true;
                }
            }
        }
        return isSameQuarter;
    }

    /**
     * 把累计周期开始日期转成LocalDate
     * @param startDate 累计周期开始日期(MMdd)
     * @return LocalDate
     */
    public static LocalDate parseStartDate(String startDate) {
        int currentYear = LocalDate.now().getYear();
        LocalDate ldStartDate;
        try {
            ldStartDate = LocalDate.parse(currentYear + startDate, DateTimeFormatter.BASIC_ISO_DATE);
        } catch (DateTimeParseException e) {
            // 如果设置的累计周期开始日是2月29日，下年2月是28天的情况会报异常，设置成当月最大的一天
            String tmpDate = currentYear + startDate.substring(0, 2) + "01";
            ldStartDate = LocalDate.parse(tmpDate, DateTimeFormatter.BASIC_ISO_DATE);
            ldStartDate = ldStartDate.with(TemporalAdjusters.lastDayOfMonth());
        }
        return ldStartDate;
    }

    /**
     * 判断两个日期是否在同一年里（按指定日期）
     * @param startDate (MM/dd)
     * @param txDate 日期2（交易日期）
     * @return true:同一年 false:不是同一年
     */
    public static boolean isSameYear(String startDate, Date txDate) {

        boolean isSameYear = false;

        LocalDate txDateLd = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // 累计周期开始日转成localDate
        LocalDate startDateLd = parseStartDate(startDate);
        Period period = Period.between(startDateLd, txDateLd);
        int years = period.getYears();
        if (Math.abs(years) == 0) {
            isSameYear = true;
        }
        return isSameYear;
    }

    /**
     * 字符串转localDate
     * @param strDate
     * @param format
     * @return
     */
    public static LocalDate str2localDate(String strDate, String format) {

        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            log.error(e.getMessage(), e);
        }
        return localDate;
    }

    /**
     * 字符串转localDateTime
     * @param strDate
     * @param format
     * @return
     */
    public static LocalDateTime str2localDateTime(String strDate, String format) {

        LocalDateTime localDateTime = null;
        try {
            localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            log.error(e.getMessage(), e);
        }
        return localDateTime;
    }
}
