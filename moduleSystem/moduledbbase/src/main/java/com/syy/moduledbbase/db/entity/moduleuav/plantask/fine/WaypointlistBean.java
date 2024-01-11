package com.syy.moduledbbase.db.entity.moduleuav.plantask.fine;

import java.io.Serializable;

public class WaypointlistBean {
    /**
     * camtarpos : {"lng":113.41336349784005,"alt":80.11207516091605,"lat":23.172915624850027}
     * gimbalpitch : 0
     * flength : 24
     * takephoto : true
     * aircraftyaw : 31.778084624052255
     * tarobjname : 500kV增穗甲线 右地线挂点
     * uavpos : {"lng":113.41333710582299,"alt":80.11207516091605,"lat":23.172877657638402}
     */

    private CamtarposBean camtarpos;
    private double gimbalpitch;
    private int flength;
    private boolean takephoto;
    private double aircraftyaw;
    private String tarobjname;
    private UavposBean uavpos;

    public CamtarposBean getCamtarpos() {
        return camtarpos;
    }

    public void setCamtarpos(CamtarposBean camtarpos) {
        this.camtarpos = camtarpos;
    }

    public double getGimbalpitch() {
        return gimbalpitch;
    }

    public void setGimbalpitch(double gimbalpitch) {
        this.gimbalpitch = gimbalpitch;
    }

    public int getFlength() {
        return flength;
    }

    public void setFlength(int flength) {
        this.flength = flength;
    }

    public boolean isTakephoto() {
        return takephoto;
    }

    public void setTakephoto(boolean takephoto) {
        this.takephoto = takephoto;
    }

    public double getAircraftyaw() {
        return aircraftyaw;
    }

    public void setAircraftyaw(double aircraftyaw) {
        this.aircraftyaw = aircraftyaw;
    }

    public String getTarobjname() {
        return tarobjname;
    }

    public void setTarobjname(String tarobjname) {
        this.tarobjname = tarobjname;
    }

    public UavposBean getUavpos() {
        return uavpos;
    }

    public void setUavpos(UavposBean uavpos) {
        this.uavpos = uavpos;
    }

    public static class CamtarposBean implements Serializable {
        /**
         * lng : 113.41336349784005
         * alt : 80.11207516091605
         * lat : 23.172915624850027
         */

        private double lng;
        private double alt;
        private double lat;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getAlt() {
            return alt;
        }

        public void setAlt(double alt) {
            this.alt = alt;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }

    public static class UavposBean implements Serializable {
        /**
         * lng : 113.41333710582299
         * alt : 80.11207516091605
         * lat : 23.172877657638402
         */

        private double lng;
        private double alt;
        private double lat;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getAlt() {
            return alt;
        }

        public void setAlt(double alt) {
            this.alt = alt;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
