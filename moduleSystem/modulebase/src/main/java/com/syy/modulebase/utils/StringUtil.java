package com.syy.modulebase.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StringUtil {
    public static NumberFormat amountNumberFormat = new DecimalFormat(",###,###,###,##0.##");
    public static DecimalFormat decimalFormatOne = new DecimalFormat("0.0");
    public static DecimalFormat decimalFormatTwo = new DecimalFormat("0.00");
    public static DecimalFormat decimalFormatThree = new DecimalFormat("0.000");
    public static DecimalFormat decimalFormatEight = new DecimalFormat("0.00000000");

    public StringUtil() {
    }

    public static String getNumberOne(double var0) {
        return var0 == 0.0D ? decimalFormatOne.format(var0).replaceAll("-", "") : decimalFormatOne.format(var0);
    }

    public static String getNumberTwo(double var0) {
        return var0 == 0.0D ? decimalFormatTwo.format(var0).replaceAll("-", "") : decimalFormatTwo.format(var0);
    }

    public static String getNumberEight(double var0) {
        return var0 == 0.0D ? decimalFormatEight.format(var0).replaceAll("-", "") : decimalFormatEight.format(var0);
    }

    public static String getNumberThree(double var0) {
        return var0 == 0.0D ? decimalFormatThree.format(var0).replaceAll("-", "") : decimalFormatThree.format(var0);
    }

    public static String getFormattedFigure(double var0) {
        return amountNumberFormat.format((long)((int)var0));
    }

    public static String getDurationString(long var0) {
        if (var0 < 0L) {
            return "";
        } else {
            long var2;
            long var10000 = var2 = var0 / 3600L;
            long var4 = var0 - (var0 = var0 / 60L - var2 * 60L) * 60L - var2 * 3600L;
            StringBuilder var6 = new StringBuilder();
            int var7;
            long var8;
            if ((var7 = (var8 = var10000 - 0L) == 0L ? 0 : (var8 < 0L ? -1 : 1)) > 0) {
                var6.append(var2).append(":");
                if (var4 > 0L || var0 > 0L) {
                    var6.append(var0).append(":");
                }
            } else {
                var6.append(var0).append(":");
            }

            if (var7 == 0 && var0 == 0L) {
                var6.append(var4).append("");
            } else if (var4 > 0L) {
                var6.append(var4).append("");
            }

            return var6.toString();
        }
    }

    public static String getHmsTimeString(long var0) {
        long var10000 = var0;
        long var10001 = var0;
        var0 /= 3600L;
        long var2;
        long var4 = var10000 - (var2 = var10001 / 60L - var0 * 60L) * 60L - var0 * 3600L;
        Object[] var6;
        Object[] var7 = var6 = new Object[3];
        var6[0] = var0;
        var6[1] = var2;
        var7[2] = var4;
        return String.format("%02d:%02d:%02d", var6);
    }

    public static boolean isEmpty(String var0) {
        return var0 == null || var0.trim().length() == 0;
    }
}
