package com.syy.moduledbbase.db;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.syy.moduledbbase.db.entity.moduleuav.CustomMarker;
import com.syy.moduledbbase.db.utils.GpsCoordinateUtils;
import com.syy.moduledbbase.db.utils.LogUtils;
import com.syy.moduledbbase.db.utils.Point;

import java.util.List;

public class MapUtil {
    public static double EARTH_RADIUS = 6378137.0D;
    public static double EARTH_RADIUS_SHORT;
    public static double metersPerDegree = 6378137.0D * 6.283185307179586D / 360.0D;
    public static double radiansPerDegree;
    public static double degreesPerRadian;
    public static double F;
    public static double PI;
    public static double AXIS;
    public static double OFFSET;
    public static double X_PI = 3.141592653589793D * 3000.0D / 180.0D;

    public MapUtil() {
    }

    public static double rad(double var0) {
        return var0 * 3.141592653589793D / 180.0D;
    }

    public static double deg(double var0) {
        return var0 * 180.0D / 3.141592653589793D;
    }

    public static double getDistance(CustomMarker var0, CustomMarker var1) {
        double var2;
        double var4;
        double var10000 = (var2 = rad(var0.getLocation().getLatitude())) - (var4 = rad(var1.getLocation().getLatitude()));
        double var6 = rad(var0.getLocation().getLongitude()) - rad(var1.getLocation().getLongitude());
        var10000 = (double) Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin(var10000 / 2.0D), 2.0D) + Math.cos(var2) * Math.cos(var4) * Math.pow(Math.sin(var6 / 2.0D), 2.0D))) * 2.0D * EARTH_RADIUS * 10000.0D) / 10000.0D;
        double var8 = Math.abs(var0.getLocation().getAltitude() - var1.getLocation().getAltitude());
        return Math.sqrt(var10000 * var10000 + var8 * var8);
    }

    public static double getDistance(LatLng latLng1, LatLng latLng2) {
        double radLat1 = rad(latLng1.getLatitude());
        double radLat2 = rad(latLng2.getLatitude());
        double var5 = radLat1 - radLat2;
        double var6 = rad(latLng1.getLongitude()) - rad(latLng2.getLongitude());
        double s = (double) Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin(var5 / 2.0D), 2.0D)
                + Math.cos(radLat1)
                * Math.cos(radLat2)
                * Math.pow(Math.sin(var6 / 2.0D), 2.0D)))
                * 2.0D
                * EARTH_RADIUS
                * 10000.0D)
                / 10000.0D;

        double var8 = Math.abs(latLng1.getAltitude() - latLng2.getAltitude());
        double sqrt = s * s + var8 * var8;
        return Math.sqrt(sqrt);

//        double radLat1 = rad(latLng1.getLatitude());
//        double radLat2 = rad(latLng2.getLatitude());
//        double a = radLat1 - radLat2;
//        double b = rad(latLng1.getLongitude()) - rad(latLng2.getLongitude());
//
//        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
//                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
//        s = s * EARTH_RADIUS;
//        s = Math.round(s * 10000D) / 10000D;

//        double var8 = Math.abs(latLng1.getAltitude() - latLng2.getAltitude());
//        double sqrt = s * s + var8 * var8;
//        return Math.sqrt(sqrt);
//        return s;
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000D) / 10000D;
        return s;
    }

    public static double getDistance(double var0, double var2, double var4, double var6, double var8, double var10) {
        double var10000 = (var0 = rad(var0)) - (var6 = rad(var6));
        var2 = rad(var2) - rad(var8);
        var10000 = (double) Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin(var10000 / 2.0D), 2.0D) + Math.cos(var0) * Math.cos(var6) * Math.pow(Math.sin(var2 / 2.0D), 2.0D))) * 2.0D * EARTH_RADIUS * 10000.0D) / 10000.0D;
        var0 = Math.abs(var4 - var10);
        return Math.sqrt(var10000 * var10000 + var0 * var0);
    }

    public static double getDistance(List<CustomMarker> var0) {
        double var1 = 0.0D;
        if (var0.size() == 2) {
            return getDistance((CustomMarker) var0.get(0), (CustomMarker) var0.get(1));
        } else {
            for (int var3 = 0; var3 < var0.size() - 1; var1 += getDistance((CustomMarker) var0.get(var3), (CustomMarker) var0.get(++var3))) {
            }

            return var1;
        }
    }

    private static Point pointCenter = new Point();
    public static Point getCenterPoiont(LatLng firstLatLng, LatLng lastLatLng) {

        double lat1 = firstLatLng.getLatitude();
        double lng1 = firstLatLng.getLongitude();

        double lat2 = lastLatLng.getLatitude();
        double lng2 = lastLatLng.getLongitude();


        double latca = (Math.max(lat1, lat2) - Math.min(lat1, lat2)) / 2;
        double lngca = (Math.max(lng1, lng2) - Math.min(lng1, lng2)) / 2;

        double lngcenter = Math.min(lng1, lng2) + lngca;
        double latcenter = Math.min(lat1, lat2) + latca;

        pointCenter.setPoint(latcenter, lngcenter);

        return pointCenter;

    }

    public static double getArea(List<CustomMarker> var0) {
        return var0.size() > 2 ? PlanarPolygonAreaMeters2(var0) : 0.0D;
    }

    public static double SphericalPolygonAreaMeters2(List<CustomMarker> var0) {
        double var1 = 0.0D;

        CustomMarker customMarker;
        int var2;
        CustomMarker var8;
        for (int var3 = 0; var3 < var0.size(); var1 += Angle(customMarker, var8, (CustomMarker) var0.get(var2))) {
            int var10002 = var3;
            int var10003 = var3;
            int var7 = ++var3 % var0.size();
            var2 = (var10003 + 2) % var0.size();
            customMarker = (CustomMarker) var0.get(var10002);
            var8 = (CustomMarker) var0.get(var7);
        }

        double var5;
        double var9;
        if ((var5 = var1 - (var9 = (double) (var0.size() - 2) * 180.0D)) > 420.0D) {
            var5 = (double) var0.size() * 360.0D - var1 - var9;
        } else if (var5 > 300.0D && var5 < 420.0D) {
            var5 = Math.abs(360.0D - var5);
        }

        return var5 * radiansPerDegree * (var1 = EARTH_RADIUS) * var1;
    }

    public static double Angle(CustomMarker var0, CustomMarker var1, CustomMarker var2) {
        double var3;
        if ((var3 = Bearing(var1, var0) - Bearing(var1, var2)) < 0.0D) {
            var3 += 360.0D;
        }

        return var3;
    }

    public static double Bearing(CustomMarker var0, CustomMarker var1) {
        double var2 = var0.getLocation().getLatitude() * radiansPerDegree;
        double var10000 = var0.getLocation().getLongitude() * radiansPerDegree;
        double var6 = var1.getLocation().getLatitude() * radiansPerDegree;
        double var4;
        if ((var6 = -Math.atan2(Math.sin(var4 = var10000 - var1.getLocation().getLongitude() * radiansPerDegree) * Math.cos(var6), Math.cos(var2) * Math.sin(var6) - Math.sin(var2) * Math.cos(var6) * Math.cos(var4))) < 0.0D) {
            var6 += 6.283185307179586D;
        }

        return var6 * degreesPerRadian;
    }

    public static double PlanarPolygonAreaMeters2(List<CustomMarker> var0) {
        double var1 = 0.0D;

        int var10;
        for (int var3 = 0; var3 < var0.size(); var3 = var10) {
            double var10000 = var1;
            int var7;
            int var2 = (var7 = var3 + 1) % var0.size();
            double var10001 = ((CustomMarker) var0.get(var3)).getLocation().getLongitude() * metersPerDegree * Math.cos(((CustomMarker) var0.get(var3)).getLocation().getLatitude() * radiansPerDegree);
            double var9 = ((CustomMarker) var0.get(var3)).getLocation().getLatitude() * metersPerDegree;
            double var5 = ((CustomMarker) var0.get(var2)).getLocation().getLongitude() * metersPerDegree * Math.cos(((CustomMarker) var0.get(var2)).getLocation().getLatitude() * radiansPerDegree);
            double var8 = var10000 + (var10001 * ((CustomMarker) var0.get(var2)).getLocation().getLatitude() * metersPerDegree - var5 * var9);
            var10 = var7;
            var1 = var8;
        }

        return Math.abs(var1 / 2.0D);
    }

    public static double[] gcj2BD09(double var0, double var2) {
        double[] var4;
        double[] var10000 = var4 = new double[2];
        double var5;
        double var10002 = var5 = Math.sqrt(var2 * var2 + var0 * var0) + Math.sin(var0 * X_PI) * 2.0E-5D;
        var2 = var5 * Math.sin(var0 = Math.atan2(var0, var2) + Math.cos(var2 * X_PI) * 3.0E-6D) + 0.006D;
        var4[0] = var2;
        var0 = var10002 * Math.cos(var0) + 0.0065D;
        var10000[1] = var0;
        return var10000;
    }

    public static double[] bd092GCJ(double var0, double var2) {
        double var10000 = var0;
        var0 = var2 - 0.0065D;
        var2 = var10000 - 0.006D;
        double[] var4;
        double[] var7 = var4 = new double[2];
        double var5;
        double var10002 = var5 = Math.sqrt(var0 * var0 + var2 * var2) - Math.sin(var2 * X_PI) * 2.0E-5D;
        var2 = var5 * Math.sin(var0 = Math.atan2(var2, var0) - Math.cos(var0 * X_PI) * 3.0E-6D);
        var4[0] = var2;
        var0 = var10002 * Math.cos(var0);
        var7[1] = var0;
        return var7;
    }

    public static double[] bd092WGS(double var0, double var2) {
        double[] var4;
        return gcj2WGS((var4 = bd092GCJ(var0, var2))[0], var4[1]);
    }

    public static double[] wgs2BD09(double var0, double var2) {
        double[] var4;
        return gcj2BD09((var4 = wgs2GCJ(var0, var2))[0], var4[1]);
    }

    public static double[] wgs2GCJ(double var0, double var2) {
        double[] var4 = {var0, var2};
        if (!outOfChina(var0, var2)) {
            double[] var5 = delta(var0, var2);
            var0 += var5[0];
            var4[0] = var0;
            var0 = var2 + var5[1];
            var4[1] = var0;
        }
        return var4;
    }

    public static double[] gcj2WGS(double var0, double var2) {
        double[] var4 = new double[2];
        if (outOfChina(var0, var2)) {
            var4[0] = var0;
            var4[1] = var2;
            return var4;
        } else {
            double var10002 = var2;
            double[] var5;
            double[] var10003 = var5 = delta(var0, var2);
            var0 -= var5[0];
            var4[0] = var0;
            var0 = var10002 - var10003[1];
            var4[1] = var0;
            return var4;
        }
    }

    public static double[] gcj2WGSExactly(double var0, double var2) {
        double var4 = 0.01D;
        double var6 = 1.0E-9D;
        double var8 = var0 - var4;
        double var10 = var2 - var4;
        double var12 = var0 + var4;
        var4 += var2;
        double var14 = 0.0D;

        double var16;
        double var18;
        do {
            double[] var10000 = wgs2GCJ(var16 = (var8 + var12) / 2.0D, var18 = (var10 + var4) / 2.0D);
            double var20 = var10000[0] - var0;
            double var22 = var10000[1] - var2;
            if (Math.abs(var20) < var6 && Math.abs(var22) < var6) {
                break;
            }

            if (var20 > 0.0D) {
                var12 = var16;
            } else {
                var8 = var16;
            }

            if (var22 > 0.0D) {
                var4 = var18;
            } else {
                var10 = var18;
            }
        } while (++var14 <= 10000.0D);

        return new double[]{var16, var18};
    }

    public static double distance(double var0, double var2, double var4, double var6) {
        double var10000 = var0;
        int var10 = 6371000;
        double var1;
        double var8;
        if ((var1 = Math.cos(var8 = var10000 * 3.141592653589793D / 180.0D) * Math.cos(var4 = var4 * 3.141592653589793D / 180.0D) * Math.cos((var2 - var6) * 3.141592653589793D / 180.0D) + Math.sin(var8) * Math.sin(var4)) > 1.0D) {
            var1 = 1.0D;
        }

        if (var1 < -1.0D) {
            var1 = -1.0D;
        }

        return Math.acos(var1) * (double) var10;
    }

    public static double[] delta(double var0, double var2) {
        double[] var10000 = {var0, var2};
        double var10003 = var2 - 105.0D;
        double var4 = transformLat(var2 - 105.0D, var2 = var0 - 35.0D);
        var10003 = transformLon(var10003, var2);
        double var10004 = var4;
        var2 = Math.sin(var0 = var0 / 180.0D * PI);
        var2 = OFFSET * var2 * var2;
        var4 = Math.sqrt(var2 = 1.0D - var2);
        var10004 *= 180.0D;
        double var6;
        double var10005 = var6 = AXIS;
        double var8 = OFFSET;
        var2 = var10004 / (var10005 * (1.0D - var8) / (var2 * var4) * PI);
        var0 = var10003 * 180.0D / (var6 / var4 * Math.cos(var0) * PI);
        var10000[0] = var2;
        var10000[1] = var0;
        return var10000;
    }

    public static boolean outOfChina(double var0, double var2) {
        if (var2 >= 72.004D && var2 <= 137.8347D) {
            return var0 < 0.8293D || var0 > 55.8271D;
        } else {
            return true;
        }
    }

    public static double transformLat(double var0, double var2) {
        double var4;
        return (var4 = var0 * 2.0D) + -100.0D + var2 * 3.0D + var2 * 0.2D * var2 + var0 * 0.1D * var2 + Math.sqrt(Math.abs(var0)) * 0.2D + (Math.sin(var0 * 6.0D * PI) * 20.0D + Math.sin(var4 * PI) * 20.0D) * 2.0D / 3.0D + (Math.sin(var2 * PI) * 20.0D + Math.sin(var2 / 3.0D * PI) * 40.0D) * 2.0D / 3.0D + (Math.sin(var2 / 12.0D * PI) * 160.0D + Math.sin(var2 * PI / 30.0D) * 320.0D) * 2.0D / 3.0D;
    }

    public static double transformLon(double var0, double var2) {
        double var4;
        return var0 + 300.0D + var2 * 2.0D + (var4 = var0 * 0.1D) * var0 + var4 * var2 + Math.sqrt(Math.abs(var0)) * 0.1D + (Math.sin(var0 * 6.0D * PI) * 20.0D + Math.sin(var0 * 2.0D * PI) * 20.0D) * 2.0D / 3.0D + (Math.sin(var0 * PI) * 20.0D + Math.sin(var0 / 3.0D * PI) * 40.0D) * 2.0D / 3.0D + (Math.sin(var0 / 12.0D * PI) * 150.0D + Math.sin(var0 / 30.0D * PI) * 300.0D) * 2.0D / 3.0D;
    }

    public static LatLng getRealLocation(LatLng latLng, String mapType) {
        if (latLng == null) {
            return new LatLng();
        } else {
            LatLng var2;
            if (!mapType.equals("MapBoxMap") && !mapType.equals("OpenStreetMap")) {
                double[] var8 = gcj2WGS(latLng.getLatitude(), latLng.getLongitude());
                double var7 = var8[0];
                double var3 = var8[1];
                if (isLocationValid(var7, var3)) {
                    double var5 = latLng.getAltitude();
                    var2 = new LatLng(var7, var3, var5);
                } else {
                    var2 = latLng;
                }
            } else {
                var2 = latLng;
            }
            LogUtils.e("getRealLocation", "getAltitude77:" + var2.getAltitude() + ",getLatitude:" + var2.getLatitude());
            if (var2.getAltitude() == 0.0D) {
                var2.setAltitude(115.0D);
            }

            return var2;
        }
    }

    public static LatLng getDeflectionLocation(LatLng latLng, String mapType) {
        if (latLng == null) {
            return new LatLng();
        } else {
            LatLng var2;
            if (!"MapBoxMap".equals(mapType) && !"OpenStreetMap".equals(mapType)) {
//                double[] wgs2GCJ = wgs2GCJ(latLng.getLatitude(), latLng.getLongitude());
                double[] wgs2GCJ = GpsCoordinateUtils.calWGS84toGCJ02(latLng.getLatitude(), latLng.getLongitude());
                double var7 = wgs2GCJ[0];
                double var3 = wgs2GCJ[1];
                double var5 = latLng.getAltitude();
                var2 = new LatLng(var7, var3, var5);
            } else {

//                if ("MapBoxMap".equals(mapType)) {
//                    double[] wgs2GCJ = GpsCoordinateUtils.calBD09toGCJ02(latLng.getLatitude(), latLng.getLongitude());
//                    double var7 = wgs2GCJ[0];
//                    double var3 = wgs2GCJ[1];
//                    double var5 = latLng.getAltitude();
//                    var2 = new LatLng(var7, var3, var5);
//                } else {
//                    var2 = latLng;
//                }

                var2 = latLng;
            }

            if (var2.getAltitude() == 0.0D) {
                var2.setAltitude(115.0D);
            }
            return var2;
        }
    }

    public static String toLocationString(double var0) {
        int var2 = (int) var0;
        String var10000 = var2 + "°";
        var2 = (int) (var0 = (var0 - (double) var2) * 60.0D);
        var10000 = var10000.concat(var2 + "′");
        int var3 = (int) ((var0 - (double) var2) * 60.0D);
        return var10000.concat(var3 + "″");
    }

    public static void main(String[] var0) {
    }

//    public static LatLng getMapCenterByPoints(List<CustomMarker> var0) {
//        return y.a(var0);
//    }

    public static LatLng destinationVincenty(LatLng latLng, double radius, double var3) {

        try {
            if (Double.isNaN(latLng.getLatitude()) && Double.isNaN(latLng.getLongitude())) {
                return null;
            }

            double var10000 = radius;
            double earthRadius = EARTH_RADIUS;

            double earthRadiusShort = EARTH_RADIUS_SHORT;
            double var5 = earthRadiusShort;
            double var10002 = var3;
            var3 = F;
            var10002 = rad(var10002);
            double var7 = Math.sin(var10002);
            double var9 = Math.cos(var10002);
            double var11;
            double var13;

            var10002 = var13 = (var11 = 1.0D - var3) * Math.tan(rad(Double.isNaN(latLng.getLongitude()) ? 0 : latLng.getLongitude()));
            double var15 = Math.sqrt(var10002 * var10002 + 1.0D);
            var10002 = var15 = 1.0D / var15;
            double var10004 = var13;
            var13 *= var15;
            double var17 = Math.atan2(var10004, var9);
            double var19;
            var10002 = var19 = var10002 * var7;
            double var21 = var10002 * var10002;
            double var23;
            var10002 = earthRadius = (var23 = 1.0D - var21) * (earthRadius * earthRadius - (earthRadius = var5 * var5)) / earthRadius;
            double var10003 = var10002 / 16384.0D;
            var5 = earthRadius * 175.0D;
            var5 = var10003 * (earthRadius * (earthRadius * (320.0D - var5) + -768.0D) + 4096.0D) + 1.0D;
            var10002 /= 1024.0D;
            var10003 = earthRadius;
            var10004 = earthRadius;
            earthRadius *= 47.0D;
            earthRadius = var10002 * (var10003 * (var10004 * (74.0D - earthRadius) + -128.0D) + 256.0D);
            var5 = radius / (earthRadiusShort * var5);
            double var25 = 6.283185307179586D;
            double var27 = 0.0D;
            double var29 = 0.0D;
            double var31 = 0.0D;
            double var33 = var31;
            var31 = var29;
            var29 = var27;
            var27 = var25;

            for (var25 = var5; Math.abs(var25 - var27) > 1.0E-12D; var25 = radius) {
                var31 = Math.cos(var17 * 2.0D + var25);
                var27 = Math.sin(var25);
                var29 = Math.cos(var25);
                var33 = var5 + earthRadius * var27 * (var31 + earthRadius / 4.0D * (var29 * (var31 * 2.0D * var31 + -1.0D) - earthRadius / 6.0D * var31 * (var27 * 4.0D * var27 + -3.0D) * (var31 * 4.0D * var31 + -3.0D)));
                radius = var33;
                var33 = var31;
                var31 = var29;
                var29 = var27;
                var27 = var25;
            }

            var10002 = var7;
            double var35;
            var7 = (var35 = var13 * var29) - (var5 = var15 * var31) * var9;
            var7 = Math.atan2(var13 * var31 + var15 * var29 * var9, var11 * Math.sqrt(var21 + var7 * var7));
            earthRadiusShort = Math.atan2(var29 * var10002, var5 - var35 * var9);
            var10002 = var3 / 16.0D * var23;
            var35 = var23 * 3.0D;
            var35 = var10002 * (var3 * (4.0D - var35) + 4.0D);
            var35 = earthRadiusShort - (1.0D - var35) * var3 * var19 * (var25 + var35 * var29 * (var33 + var35 * var31 * (var33 * 2.0D * var33 + -1.0D)));
            double lng = Double.isNaN(latLng.getLongitude()) ? 0 : latLng.getLongitude();
            var35 = lng * 1.0D + deg(var35);

            if (Double.isNaN(var7) && Double.isNaN(var35)) {
                return null;
            }

            return new LatLng(deg(var7), var35);
        } catch (Exception e) {
            LogUtils.e("Exception--------", e.getMessage());
        }

        return null;
    }

    public static boolean isLocationValid(double var0, double var2) {
        if (!Double.isNaN(var0) && !Double.isNaN(var2)) {
            if (var0 <= 90.0D && var0 >= -90.0D && var2 <= 180.0D && var2 >= -180.0D) {
                return (var0 >= 1.0E-6D || var0 <= -1.0E-6D) && (var2 >= 1.0E-6D || var2 <= -1.0E-6D);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
