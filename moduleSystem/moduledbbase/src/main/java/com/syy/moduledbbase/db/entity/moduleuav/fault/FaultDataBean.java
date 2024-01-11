package com.syy.moduledbbase.db.entity.moduleuav.fault;

import com.syy.moduledbbase.db.entity.moduleuav.PhotoPositionBean;

import java.io.Serializable;
import java.util.List;

public class FaultDataBean implements Serializable {

    private double lat;
    private double lng;
    private double alt;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

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

    private List<LittlesideBean> littleside;
    private List<Little2bigBean> little2big;
    private List<BigsideBean> bigside;

    public List<LittlesideBean> getLittleside() {
        return littleside;
    }

    public void setLittleside(List<LittlesideBean> littleside) {
        this.littleside = littleside;
    }

    public List<Little2bigBean> getLittle2big() {
        return little2big;
    }

    public void setLittle2big(List<Little2bigBean> little2big) {
        this.little2big = little2big;
    }

    public List<BigsideBean> getBigside() {
        return bigside;
    }

    public void setBigside(List<BigsideBean> bigside) {
        this.bigside = bigside;
    }

    public static class LittlesideBean implements Serializable {
        /**
         * name : 小号侧
         * uavpos : {"lng":113.22633066436376,"lat":23.139380370845736,"alt":29.3459}
         * camtarpos : {"lng":113.22633066436376,"lat":23.139380370845736,"alt":6.957900108719029}
         * turningmode : FIXEDTURN
         * takephoto : true
         * aircraftyaw : 0
         * flength : 19
         * gimbalpitch : 0.010000000000005116
         * photoinfolist : null
         */

        private String name;
        private UavposBean uavpos;
        private CamtarposBean camtarpos;
        private String turningmode;
        private boolean takephoto;
        private double aircraftyaw;
        private double flength;
        private double gimbalpitch;
        private List<PhotoPositionBean> photoinfolist;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public UavposBean getUavpos() {
            return uavpos;
        }

        public void setUavpos(UavposBean uavpos) {
            this.uavpos = uavpos;
        }

        public CamtarposBean getCamtarpos() {
            return camtarpos;
        }

        public void setCamtarpos(CamtarposBean camtarpos) {
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

        public double getAircraftyaw() {
            if (aircraftyaw > 180) {
                return aircraftyaw - 360;
            } else {
                return aircraftyaw;
            }
        }

        public void setAircraftyaw(double aircraftyaw) {
            this.aircraftyaw = aircraftyaw;
        }

        public double getFlength() {
            return flength;
        }

        public void setFlength(double flength) {
            this.flength = flength;
        }

        public double getGimbalpitch() {
//            if (gimbalpitch > 90) {
//                return 90D - gimbalpitch;
//            } else {
//                return gimbalpitch;
//            }
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

        public static class UavposBean implements Serializable {
            /**
             * lng : 113.22633066436376
             * lat : 23.139380370845736
             * alt : 29.3459
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

        public static class CamtarposBean implements Serializable {
            /**
             * lng : 113.22633066436376
             * lat : 23.139380370845736
             * alt : 6.957900108719029
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

    public static class Little2bigBean implements Serializable {
        /**
         * name : 沿导线追踪: 0
         * uavpos : {"lng":113.22633066436376,"lat":23.139380370845736,"alt":11.957900108719029}
         * camtarpos : {"lng":113.22633066436376,"lat":23.139380370845736,"alt":6.957900108719029}
         * turningmode : FIXEDTURN
         * takephoto : true
         * aircraftyaw : 0
         * flength : 19
         * gimbalpitch : 1
         * photoinfolist : null
         */

        private String name;
        private UavposBeanX uavpos;
        private CamtarposBeanX camtarpos;
        private String turningmode;
        private boolean takephoto;
        private double aircraftyaw;
        private double flength;
        private double gimbalpitch;
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

        public double getAircraftyaw() {
            if (aircraftyaw > 180) {
                return aircraftyaw - 360;
            } else {
                return aircraftyaw;
            }
        }

        public void setAircraftyaw(double aircraftyaw) {
            this.aircraftyaw = aircraftyaw;
        }

        public double getFlength() {
            return flength;
        }

        public void setFlength(double flength) {
            this.flength = flength;
        }

        public double getGimbalpitch() {
//            if (gimbalpitch > 90) {
//                return 90D - gimbalpitch;
//            } else {
//                return gimbalpitch;
//            }
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

        public static class UavposBeanX implements Serializable {
            /**
             * lng : 113.22633066436376
             * lat : 23.139380370845736
             * alt : 11.957900108719029
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

        public static class CamtarposBeanX implements Serializable {
            /**
             * lng : 113.22633066436376
             * lat : 23.139380370845736
             * alt : 6.957900108719029
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

    public static class BigsideBean implements Serializable {
        /**
         * name : 大号侧
         * uavpos : {"lng":113.22633066436376,"lat":23.139380370845736,"alt":29.3459}
         * camtarpos : {"lng":113.22633066436376,"lat":23.139380370845736,"alt":6.957900108719029}
         * turningmode : FIXEDTURN
         * takephoto : true
         * aircraftyaw : 0
         * flength : 19
         * gimbalpitch : 0.010000000000005116
         * photoinfolist : null
         */

        private String name;
        private UavposBeanXX uavpos;
        private CamtarposBeanXX camtarpos;
        private String turningmode;
        private boolean takephoto;
        private double aircraftyaw;
        private double flength;
        private double gimbalpitch;
        private List<PhotoPositionBean> photoinfolist;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public UavposBeanXX getUavpos() {
            return uavpos;
        }

        public void setUavpos(UavposBeanXX uavpos) {
            this.uavpos = uavpos;
        }

        public CamtarposBeanXX getCamtarpos() {
            return camtarpos;
        }

        public void setCamtarpos(CamtarposBeanXX camtarpos) {
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

        public double getAircraftyaw() {
            if (aircraftyaw > 180) {
                return aircraftyaw - 360;
            } else {
                return aircraftyaw;
            }
        }

        public void setAircraftyaw(double aircraftyaw) {
            this.aircraftyaw = aircraftyaw;
        }

        public double getFlength() {
            return flength;
        }

        public void setFlength(double flength) {
            this.flength = flength;
        }

        public double getGimbalpitch() {
//            if (gimbalpitch > 90) {
//                return 90D - gimbalpitch;
//            } else {
//                return gimbalpitch;
//            }
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

        public static class UavposBeanXX implements Serializable {
            /**
             * lng : 113.22633066436376
             * lat : 23.139380370845736
             * alt : 29.3459
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

        public static class CamtarposBeanXX implements Serializable {
            /**
             * lng : 113.22633066436376
             * lat : 23.139380370845736
             * alt : 6.957900108719029
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
}
