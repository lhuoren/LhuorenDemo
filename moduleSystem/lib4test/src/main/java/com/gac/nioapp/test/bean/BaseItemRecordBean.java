package com.gac.nioapp.test.bean;

import java.util.List;

/**
 * Created by Sai on 2018/4/16.
 * 列表json基类模型，反射数据是records 为key的json数组
 */

public class BaseItemRecordBean<L> {


    public List<L> getRecords() {
        return records;
    }

    public void setRecords(List<L> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public String name;
    public String headImageUrl;
    public String brift;
    public String ugcHotShareUrl;
    public int totals;
    public int total;
    public int id;
    public List<L> records;
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRemark(String brift) {
        this.brift = brift;
    }

    public void setArticleName(String name) {
        this.name = name;
    }

    public void setArticleNum(int totals) {
        this.totals = totals;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getRemark() {
        return brift;
    }

    public int getArticleNum() {
        return totals;
    }

    public String getArticleName() {
        return name;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public String getUgcHotShareUrl() {
        return ugcHotShareUrl;
    }

    public void setUgcHotShareUrl(String ugcHotShareUrl) {
        this.ugcHotShareUrl = ugcHotShareUrl;
    }
}
