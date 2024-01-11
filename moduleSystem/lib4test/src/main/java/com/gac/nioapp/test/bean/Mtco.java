package com.gac.nioapp.test.bean;

import java.util.HashMap;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-08-13 15:06
 ** @describe
 *******************************/
public class Mtco {
    HashMap<String, ColorPicBean> mtco ;

    public void setMtco(HashMap<String, ColorPicBean> mtco) {
        this.mtco = mtco;
    }

    public HashMap<String, ColorPicBean> getMtco() {
        return mtco;
    }
}
