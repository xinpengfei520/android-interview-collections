package com.xpf.p2p.utils;

import java.util.Scanner;

/**
 * Created by xpf on 2018/6/22 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class CalculateUtils {

    /*平年二月28天*/
    private static final int DAYS_28 = 28;
    /*闰年二月29天*/
    private static final int DAYS_29 = 29;
    /*除了31天的月份其他均为30天*/
    private static final int DAYS_30 = 30;
    /*1、3、5、7、8、10、12月份31天*/
    private static final int DAYS_31 = 31;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input year:");
        int year = input.nextInt();
        System.out.println("Please input month:");
        int month = input.nextInt();
        System.out.println("Please input day:");
        int day = input.nextInt();

        int daysInYear = getDaysInYear(year, month, day);
        System.out.println("daysInYear:" + daysInYear);
    }

    /**
     * get days in this year
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getDaysInYear(int year, int month, int day) {
        int totalDays = 0;

        switch (month) {
            // 12 月份加的是11月份的天数，依次类推
            case 12:
                totalDays += DAYS_30;
            case 11:
                totalDays += DAYS_31;
            case 10:
                totalDays += DAYS_30;
            case 9:
                totalDays += DAYS_31;
            case 8:
                totalDays += DAYS_31;
            case 7:
                totalDays += DAYS_30;
            case 6:
                totalDays += DAYS_31;
            case 5:
                totalDays += DAYS_30;
            case 4:
                totalDays += DAYS_31;
            case 3:
                // 判断是否是闰年
                if (((year / 4 == 0) && (year / 100 != 0)) || (year / 400 == 0)) {
                    totalDays += DAYS_29;
                } else {
                    totalDays += DAYS_28;
                }
            case 2:
                totalDays += DAYS_31;
            case 1: // 如果是1月份就加上输入的天数
                totalDays += day;
        }

        return totalDays;
    }
}
