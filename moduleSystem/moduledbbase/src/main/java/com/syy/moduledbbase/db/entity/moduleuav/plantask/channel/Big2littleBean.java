package com.syy.moduledbbase.db.entity.moduleuav.plantask.channel;

import java.io.Serializable;

public class Big2littleBean {
    /**
     * camtarpos : {"lng":112.17492023259874,"alt":312.4999,"lat":24.700211299153647}
     * gimbalpitch : 0.010000000000005116
     * flength : 24
     * turningmode : FIXEDTURN
     * name : 塔顶
     * takephoto : true
     * aircraftyaw : 0
     * photoinfolist : null
     * uavpos : {"lng":112.17492023259874,"alt":337.4999,"lat":24.700211299153647}
     */

    private CamtarposBean camtarpos;
    private double gimbalpitch;
    private int flength;
    private String turningmode;
    private String name;
    private boolean takephoto;
    private int aircraftyaw;
    private Object photoinfolist;
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

    public String getTurningmode() {
        return turningmode;
    }

    public void setTurningmode(String turningmode) {
        this.turningmode = turningmode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTakephoto() {
        return takephoto;
    }

    public void setTakephoto(boolean takephoto) {
        this.takephoto = takephoto;
    }

    public int getAircraftyaw() {
        return aircraftyaw;
    }

    public void setAircraftyaw(int aircraftyaw) {
        this.aircraftyaw = aircraftyaw;
    }

    public Object getPhotoinfolist() {
        return photoinfolist;
    }

    public void setPhotoinfolist(Object photoinfolist) {
        this.photoinfolist = photoinfolist;
    }

    public UavposBean getUavpos() {
        return uavpos;
    }

    public void setUavpos(UavposBean uavpos) {
        this.uavpos = uavpos;
    }

    public static class CamtarposBean implements Serializable {
        /**
         * lng : 112.17492023259874
         * alt : 312.4999
         * lat : 24.700211299153647
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
         * lng : 112.17492023259874
         * alt : 337.4999
         * lat : 24.700211299153647
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
