package com.syy.moduledbbase.db.entity.moduleuav;

import android.graphics.PointF;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.Projection;
import com.syy.moduledbbase.db.MapUtil;
import com.syy.moduledbbase.db.utils.LogUtils;
import com.syy.moduledbbase.db.utils.Point;
import com.syy.moduledbbase.db.utils.SharedPrefaceUtils;
import com.syy.moduledbbase.db.utils.SpConstants;

public class CustomMarker implements Cloneable {
    public static final int NAN_MARKER = -1;
    public static final int MAIN_MARKER = 0;
    public static final int SIDE_MARKER = 1;
    public static final int AIR_LINE_MARKER = 2;
    public String remark;
    public Point point;
    public LatLng location;
    public boolean isSkip;
    public int markerType;
    public Projection projection;

    public CustomMarker() {
    }

    public void setCustomMarkerData(LatLng var1, int var2, Projection var3) {
        this.location = var1;
        this.markerType = var2;
        this.projection = var3;
        this.getScreenPix();
    }

    public void setCustomMarkerData(LatLng var1, int var2, Projection var3, String var4) {
        this.location = var1;
        this.markerType = var2;
        this.projection = var3;
        this.remark = var4;
        this.getScreenPix();
    }

    public CustomMarker(Point var1, int var2, Projection var3) {
        this.point = var1;
        this.markerType = var2;
        this.projection = var3;
        this.getMapLocation();
    }

    public CustomMarker(LatLng var1, int var2, Projection var3) {
        this.location = var1;
        this.markerType = var2;
        this.projection = var3;
        this.getScreenPix();
    }

    public CustomMarker(LatLng var1, int var2, Projection var3, String var4) {
        this.location = var1;
        this.markerType = var2;
        this.projection = var3;
        this.remark = var4;
        this.getScreenPix();
    }

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(Point var1) {
        if (var1 != null) {
            double var10000 = var1.x;
            double var2 = var1.y;
            if (!Double.isNaN(var10000) && !Double.isNaN(var2)) {
                this.point = var1;
                this.getMapLocation();
            }
        }

    }

    public LatLng getLocation() {
        return this.location;
    }

    public void setLocation(LatLng var1) {
        this.location = var1;
        this.getScreenPix();
    }

    public int getMarkerType() {
        return this.markerType;
    }

    public void setMarkerType(int var1) {
        this.markerType = var1;
    }

    public Projection getProjection() {
        return this.projection;
    }

    public void setProjection(Projection var1) {
        this.projection = var1;
    }

    public void getMapLocation() {
        if (this.projection != null) {
            double var1 = 0.0D;
            double var3;
            if (this.point.z <= 0.0D) {
                var3 = var1;
            } else {
                var3 = this.point.z;
            }

            LogUtils.e("this.point.z", " this.point.z:" + this.point.z);

            LatLng var5;
            if ((var5 = this.location) != null && var5.getAltitude() > 0.0D) {
                var3 = this.location.getAltitude();
            }

            PointF pointF = null;
            if (!Double.isNaN(this.point.x) && !Double.isNaN(this.point.y)) {
                pointF = new PointF((float) this.point.x, (float) this.point.y);
            }

            if (pointF != null) {
                LatLng var10002 = var5 = this.projection.fromScreenLocation(pointF);
                this.location = var5;
                var10002.setAltitude(var3);
                if (var3 > 0.0D) {
                    this.location = MapUtil.getRealLocation(var10002, SharedPrefaceUtils.getString(SpConstants.SharedName.MAPTYPE));
                }
            }
        }
    }

    private Point getScreenPixPoint = null;

    private LatLng getScreenPixLatLng = null;

    public void getScreenPix() {
        if (this.projection != null) {
            CustomMarker var10000 = this;
            CustomMarker var10001 = this;
            double var10004 = this.location.getLatitude();
            double var1 = this.location.getLongitude();
            double var3 = this.location.getAltitude();
            if (getScreenPixLatLng == null) {
                getScreenPixLatLng = new LatLng(var10004, var1, var3);
            }
            getScreenPixLatLng.setLatitude(var10004);
            getScreenPixLatLng.setLongitude(var1);
            getScreenPixLatLng.setAltitude(var3);
            LatLng var7 = MapUtil.getDeflectionLocation(getScreenPixLatLng, SharedPrefaceUtils.getString(SpConstants.SharedName.MAPTYPE));
            PointF var8 = var10001.projection.toScreenLocation(var7);
            var1 = var8.x;
            var3 = var8.y;
            double var5 = var7.getAltitude();

            if (getScreenPixPoint == null) {
                var10000.point = new Point(var1, var3, var5);
            }
            var10000.point.setPoint(var1, var3, var5);
        }
    }

    public boolean isSkip() {
        return this.isSkip;
    }

    public void setSkip(boolean var1) {
        this.isSkip = var1;
    }

    public String toString() {
        return "CustomMarker{point=" + this.point + ", location=" + this.location + ", markerType=" + this.markerType + ", projection=" + this.projection + '}';
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String var1) {
        this.remark = var1;
    }

    public CustomMarker clone() {
        CustomMarker var10000 = this;
        CustomMarker var3 = null;

        try {
            var10000 = (CustomMarker) var10000.clone();
        } catch (Exception var2) {
            var2.printStackTrace();
            return var3;
        }

        var3 = var10000;
        return var3;
    }
}
