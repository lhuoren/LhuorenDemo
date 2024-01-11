package com.gac.nioapp.test.bean;

import java.util.List;

/**
 * description:
 *
 * @author xyz
 * @email 826363590@qq.com
 * created on: 2019/5/22 4:10 PM
 */
public class ActivityNeedWriteFieldsBean {
    /**
     * fieldName : 姓名
     * fieldValue : 刘德华
     * fieldType : 1
     */

    private String fieldName;
    private String fieldValue;
    private String remark;
    private int fieldType;
    private String fieldStatus;  //0 填空 1 单选 2多选【3.2.4新增】
    private List<FieldSelectItem> fieldSelectArray;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldStatus() {
        return fieldStatus;
    }

    public void setFieldStatus(String fieldStatus) {
        this.fieldStatus = fieldStatus;
    }

    public List<FieldSelectItem> getFieldSelectArray() {
        return fieldSelectArray;
    }

    public void setFieldSelectArray(List<FieldSelectItem> fieldSelectArray) {
        this.fieldSelectArray = fieldSelectArray;
    }
}