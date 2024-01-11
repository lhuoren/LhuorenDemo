package com.gac.nioapp.test.bean;

import java.util.List;

/**
 * @package： com.hycan.community.bean
 * @describe： 反射数据是list 为key的json数组
 * @author： liming
 * @time： 2019/4/15 2:25 PM
 * @e-mail： liming@gac-nio.com
 */
public class BaseItemListBean<L> {
    public List<L> list;

    public List<L> getList() {
        return list;
    }

    public void setList(List<L> list) {
        this.list = list;
    }
}
