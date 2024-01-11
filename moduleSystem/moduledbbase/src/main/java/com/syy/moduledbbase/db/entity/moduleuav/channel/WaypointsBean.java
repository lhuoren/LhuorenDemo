package com.syy.moduledbbase.db.entity.moduleuav.channel;

import org.greenrobot.greendao.annotation.Convert;

import java.util.List;

public class WaypointsBean {

    @Convert(columnType = String.class, converter = Big2littleBeanConverter.class)
    private List<Big2littleBean> big2little;
    @Convert(columnType = String.class, converter = Little2bigBeanConverter.class)
    private List<Little2bigBean> little2big;

    public List<Big2littleBean> getBig2little() {
        return big2little;
    }

    public void setBig2little(List<Big2littleBean> big2little) {
        this.big2little = big2little;
    }

    public List<Little2bigBean> getLittle2big() {
        return little2big;
    }

    public void setLittle2big(List<Little2bigBean> little2big) {
        this.little2big = little2big;
    }

}
