package com.gac.nioapp.test.bean;

import java.util.List;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2020-03-06 18:25
 ** @describe
 *******************************/
public class CarOrderDataBean<SelectOrderCarsBean> {

    private List<SelectOrderCarsBean> carOrders;

    public List<SelectOrderCarsBean> getCarOrders() {
        return carOrders;
    }

    public void setCarOrders(List<SelectOrderCarsBean> carOrders) {
        this.carOrders = carOrders;
    }

}