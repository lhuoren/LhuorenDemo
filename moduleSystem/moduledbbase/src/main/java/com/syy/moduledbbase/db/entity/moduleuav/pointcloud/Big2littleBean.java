package com.syy.moduledbbase.db.entity.moduleuav.pointcloud;

import com.syy.moduledbbase.db.entity.moduleuav.PhotoPositionBean;
import com.syy.moduledbbase.db.entity.moduleuav.PhotoPositionBeanConverter;

import org.greenrobot.greendao.annotation.Convert;

import java.util.List;


public class Big2littleBean {
    /**
     * name : 跨域障碍物点
     * uavpos : {"lng":113.22620005173704,"lat":23.13943617697338,"alt":25.8199}
     * camtarpos : {"lng":113.22620005173704,"lat":23.13943617697338,"alt":15.8199}
     * turningmode : FIXEDTURN
     * takephoto : true
     * aircraftyaw : 0
     * flength : 24
     * gimbalpitch : 0.010000000000005116
     * photoinfolist : null
     */

    private String name;
    private UavposBeanX uavpos;
    private CamtarposBeanX camtarpos;
    private String turningmode;
    private boolean takephoto;
    private int aircraftyaw;
    private int flength;
    private double gimbalpitch;
    @Convert(columnType = String.class, converter = PhotoPositionBeanConverter.class)
    private List<PhotoPositionBean> photoinfolist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UavposBeanX getUavpos() {
        return uavpos;
    }

    public void setUavpos(UavposBeanX uavpos) {
        this.uavpos = uavpos;
    }

    public CamtarposBeanX getCamtarpos() {
        return camtarpos;
    }

    public void setCamtarpos(CamtarposBeanX camtarpos) {
        this.camtarpos = camtarpos;
    }

    public String getTurningmode() {
        return turningmode;
    }

    public void setTurningmode(String turningmode) {
        this.turningmode = turningmode;
    }

    public boolean isTakephoto() {
        return takephoto;
    }

    public void setTakephoto(boolean takephoto) {
        this.takephoto = takephoto;
    }

    public int getAircraftyaw() {
        if (aircraftyaw > 180) {
            return aircraftyaw - 360;
        } else {
            return aircraftyaw;
        }
    }

    public void setAircraftyaw(int aircraftyaw) {
        this.aircraftyaw = aircraftyaw;
    }

    public int getFlength() {
        return flength;
    }

    public void setFlength(int flength) {
        this.flength = flength;
    }

    public double getGimbalpitch() {
//        if (gimbalpitch > 90) {
//            return 90D - gimbalpitch;
//        } else {
//            return gimbalpitch;
//        }
        return gimbalpitch;
    }

    public void setGimbalpitch(double gimbalpitch) {
        this.gimbalpitch = gimbalpitch;
    }

    public List<PhotoPositionBean> getPhotoinfolist() {
        return photoinfolist;
    }

    public void setPhotoinfolist(List<PhotoPositionBean> photoinfolist) {
        this.photoinfolist = photoinfolist;
    }

    public static class UavposBeanX {
        /**
         * lng : 113.22620005173704
         * lat : 23.13943617697338
         * alt : 25.8199
         */

        private double lng;
        private double lat;
        private double alt;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getAlt() {
            return alt;
        }

        public void setAlt(double alt) {
            this.alt = alt;
        }
    }

    public static class CamtarposBeanX {
        /**
         * lng : 113.22620005173704
         * lat : 23.13943617697338
         * alt : 15.8199
         */

        private double lng;
        private double lat;
        private double alt;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getAlt() {
            return alt;
        }

        public void setAlt(double alt) {
            this.alt = alt;
        }
    }
}
