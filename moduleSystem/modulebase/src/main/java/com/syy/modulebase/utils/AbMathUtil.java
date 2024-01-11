package com.syy.modulebase.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AbMathUtil {
    public AbMathUtil() {
    }

    public static BigDecimal round(double number, int decimal) {
        return (new BigDecimal(number)).setScale(decimal, 4);
    }

    public static String byte2HexStr(byte[] b, int length) {
        String hs = "";
        String stmp = "";

        for(int n = 0; n < length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }

            hs = hs + ",";
        }

        return hs.toUpperCase();
    }

    public static char binaryToHex(int binary) {
        char ch;
        switch(binary) {
            case 0:
                ch = '0';
                break;
            case 1:
                ch = '1';
                break;
            case 2:
                ch = '2';
                break;
            case 3:
                ch = '3';
                break;
            case 4:
                ch = '4';
                break;
            case 5:
                ch = '5';
                break;
            case 6:
                ch = '6';
                break;
            case 7:
                ch = '7';
                break;
            case 8:
                ch = '8';
                break;
            case 9:
                ch = '9';
                break;
            case 10:
                ch = 'a';
                break;
            case 11:
                ch = 'b';
                break;
            case 12:
                ch = 'c';
                break;
            case 13:
                ch = 'd';
                break;
            case 14:
                ch = 'e';
                break;
            case 15:
                ch = 'f';
                break;
            default:
                ch = ' ';
        }

        return ch;
    }

    public static int[][] arrayToMatrix(int[] m, int width, int height) {
        int[][] result = new int[height][width];

        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                int p = j * height + i;
                result[i][j] = m[p];
            }
        }

        return result;
    }

    public static double[] matrixToArray(double[][] m) {
        int p = m.length * m[0].length;
        double[] result = new double[p];

        for(int i = 0; i < m.length; ++i) {
            for(int j = 0; j < m[i].length; ++j) {
                int q = j * m.length + i;
                result[q] = m[i][j];
            }
        }

        return result;
    }

    public static double[] intToDoubleArray(int[] input) {
        int length = input.length;
        double[] output = new double[length];

        for(int i = 0; i < length; ++i) {
            output[i] = Double.valueOf(String.valueOf(input[i]));
        }

        return output;
    }

    public static double[][] intToDoubleMatrix(int[][] input) {
        int height = input.length;
        int width = input[0].length;
        double[][] output = new double[height][width];

        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                output[i][j] = Double.valueOf(String.valueOf(input[i][j]));
            }
        }

        return output;
    }

    public static int average(int[] pixels) {
        float m = 0.0F;

        for(int i = 0; i < pixels.length; ++i) {
            m += (float)pixels[i];
        }

        m /= (float)pixels.length;
        return (int)m;
    }

    public static int average(double[] pixels) {
        float m = 0.0F;

        for(int i = 0; i < pixels.length; ++i) {
            m = (float)((double)m + pixels[i]);
        }

        m /= (float)pixels.length;
        return (int)m;
    }

    public static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    public boolean pointOnLine(double x, double y, double x1, double y1, double x2, double y2) {
        double result = (x - x1) * (y2 - y1) - (y - y1) * (x2 - x1);
        return result == 0.0D;
    }

    public static boolean pointAtELine(double x, double y, double x1, double y1, double x2, double y2) {
        double result = (x - x1) * (y2 - y1) - (y - y1) * (x2 - x1);
        if (result != 0.0D) {
            return false;
        } else {
            return x >= Math.min(x1, x2) && x <= Math.max(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
        }
    }

    public static boolean LineOnLine(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double k1 = (y2 - y1) / (x2 - x1);
        double k2 = (y4 - y3) / (x4 - x3);
        if (k1 == k2) {
            return false;
        } else {
            double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x3 * y4 - y3 * x4) * (x1 - x2)) / ((y2 - y1) * (x3 - x4) - (y4 - y3) * (x1 - x2));
            double y = (x1 * y2 - y1 * x2 - x * (y2 - y1)) / (x1 - x2);
            return true;
        }
    }

    public static boolean eLineOnELine(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double k1 = (y2 - y1) / (x2 - x1);
        double k2 = (y4 - y3) / (x4 - x3);
        if (k1 == k2) {
            return false;
        } else {
            double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x3 * y4 - y3 * x4) * (x1 - x2)) / ((y2 - y1) * (x3 - x4) - (y4 - y3) * (x1 - x2));
            double y = (x1 * y2 - y1 * x2 - x * (y2 - y1)) / (x1 - x2);
            return x >= Math.min(x1, x2) && x <= Math.max(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2) && x >= Math.min(x3, x4) && x <= Math.max(x3, x4) && y >= Math.min(y3, y4) && y <= Math.max(y3, y4);
        }
    }

    public static boolean eLineOnLine(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double k1 = (y2 - y1) / (x2 - x1);
        double k2 = (y4 - y3) / (x4 - x3);
        if (k1 == k2) {
            return false;
        } else {
            double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x3 * y4 - y3 * x4) * (x1 - x2)) / ((y2 - y1) * (x3 - x4) - (y4 - y3) * (x1 - x2));
            double y = (x1 * y2 - y1 * x2 - x * (y2 - y1)) / (x1 - x2);
            return x >= Math.min(x1, x2) && x <= Math.max(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
        }
    }

    public static boolean pointOnRect(double x, double y, double x1, double y1, double x2, double y2) {
        return x >= Math.min(x1, x2) && x <= Math.max(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
    }

    public static boolean rectOnRect(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return x1 >= Math.min(x3, x4) && x1 <= Math.max(x3, x4) && y1 >= Math.min(y3, y4) && y1 <= Math.max(y3, y4) && x2 >= Math.min(x3, x4) && x2 <= Math.max(x3, x4) && y2 >= Math.min(y3, y4) && y2 <= Math.max(y3, y4);
    }

    public static boolean circleOnRect(double x, double y, double r, double x1, double y1, double x2, double y2) {
        if (x >= Math.min(x1, x2) && x <= Math.max(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2)) {
            double l1 = Math.abs(x - x1);
            double l2 = Math.abs(y - y2);
            double l3 = Math.abs(x - x2);
            double l4 = Math.abs(y - y2);
            return r <= l1 && r <= l2 && r <= l3 && r <= l4;
        } else {
            return false;
        }
    }

    public static boolean pointOnCircle(AbPoint point, AbCircle c1, AbCircle c2) {
        return Math.pow(point.x - c2.point.x, 2.0D) + Math.pow(point.y - c2.point.y, 2.0D) == Math.pow(c2.r, 2.0D) && Math.pow(point.x - c1.point.x, 2.0D) + Math.pow(point.y - c1.point.y, 2.0D) == Math.pow(c1.r, 2.0D);
    }

    public static boolean pointOnCircle(AbPoint point, AbCircle c1, AbCircle c2, float offset) {
        return Math.pow(point.x - c2.point.x, 2.0D) + Math.pow(point.y - c2.point.y, 2.0D) <= Math.pow(c2.r, 2.0D) + (double)offset && Math.pow(point.x - c2.point.x, 2.0D) + Math.pow(point.y - c2.point.y, 2.0D) >= Math.pow(c2.r, 2.0D) - (double)offset && Math.pow(point.x - c1.point.x, 2.0D) + Math.pow(point.y - c1.point.y, 2.0D) <= Math.pow(c1.r, 2.0D) + (double)offset && Math.pow(point.x - c1.point.x, 2.0D) + Math.pow(point.y - c1.point.y, 2.0D) >= Math.pow(c1.r, 2.0D) - (double)offset;
    }

    public static boolean pointInCircle(AbPoint point, AbCircle circle) {
        return Math.pow(point.x - circle.point.x, 2.0D) + Math.pow(point.y - circle.point.y, 2.0D) <= Math.pow(circle.r, 2.0D);
    }

    public static double getDistance(AbPoint p1, AbPoint p2) {
        return getDistance(p1.x, p1.y, p2.x, p2.y);
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
        double x = x1 - x2;
        double y = y1 - y2;
        return Math.sqrt(x * x + y * y);
    }

    public static boolean isRectCollision(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
        if (x2 > x1 && x2 > x1 + w1) {
            return false;
        } else if (x2 < x1 && x2 < x1 - w2) {
            return false;
        } else if (y2 > y1 && y2 > y1 + h1) {
            return false;
        } else {
            return y2 >= y1 || y2 >= y1 - h2;
        }
    }

    public static List<AbPoint> circleCrossoverPoint(AbCircle circle1, AbCircle circle2) {
        List<AbPoint> pointList = new ArrayList();
        double a = 2.0D * circle1.r * (circle1.point.x - circle2.point.x);
        double b = 2.0D * circle1.r * (circle1.point.y - circle2.point.y);
        double c = Math.pow(circle2.r, 2.0D) - Math.pow(circle1.r, 2.0D) - Math.pow(circle1.point.x - circle2.point.x, 2.0D) - Math.pow(circle1.point.y - circle2.point.y, 2.0D);
        double p = Math.pow(a, 2.0D) + Math.pow(b, 2.0D);
        double q = -2.0D * a * c;
        double r = Math.pow(c, 2.0D) - Math.pow(b, 2.0D);
        double t = Math.pow(q, 2.0D) - 4.0D * p * r;
        double cosCita = (Math.sqrt(t) - q) / (2.0D * p);
        double cosCita2 = (-Math.sqrt(t) - q) / (2.0D * p);
        double x_1 = cosCita * circle1.r + circle1.point.x;
        double y_1_1 = Math.sqrt(Math.pow(circle1.r, 2.0D) - Math.pow(x_1 - circle1.point.x, 2.0D)) + circle1.point.y;
        double y_1_2 = -Math.sqrt(Math.pow(circle1.r, 2.0D) - Math.pow(x_1 - circle1.point.x, 2.0D)) + circle1.point.y;
        Set<AbPoint> pointSet = new HashSet();
        AbPoint p1_1 = new AbPoint(x_1, y_1_1);
        if (pointOnCircle(p1_1, circle1, circle2)) {
            pointSet.add(p1_1);
        }

        AbPoint p1_2 = new AbPoint(x_1, y_1_2);
        if (pointOnCircle(p1_2, circle1, circle2)) {
            pointSet.add(p1_2);
        }

        double x_2 = cosCita2 * circle1.r + circle1.point.x;
        double y_2_1 = Math.sqrt(Math.pow(circle1.r, 2.0D) - Math.pow(x_2 - circle1.point.x, 2.0D)) + circle1.point.y;
        double y_2_2 = -Math.sqrt(Math.pow(circle1.r, 2.0D) - Math.pow(x_2 - circle1.point.x, 2.0D)) + circle1.point.y;
        AbPoint p2_1 = new AbPoint(x_2, y_2_1);
        if (pointOnCircle(p2_1, circle1, circle2)) {
            pointSet.add(p2_1);
        }

        AbPoint p2_2 = new AbPoint(x_2, y_2_2);
        if (pointOnCircle(p2_2, circle1, circle2)) {
            pointSet.add(p2_2);
        }

        Iterator iter = pointSet.iterator();

        while(iter.hasNext()) {
            AbPoint point = (AbPoint)iter.next();
            pointList.add(point);
        }

        return pointList;
    }

    public static double rad(double d) {
        return d * 3.141592653589793D / 180.0D;
    }

    public static double getGeoDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double EARTH_RADIUS = 6378137.0D;
        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double a = radLat1 - radLat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));
        s *= EARTH_RADIUS;
        s = (double)(Math.round(s * 10000.0D) / 10000L);
        return s;
    }
}