package com.gac.nioapp.test.bean;

/**
 * @package： com.hycan.community.bean
 * @describe：
 * @author： liming
 * @time： 2020/10/22 6:38 PM
 * @e-mail： liming@gac-nio.com
 */
public class FieldSelectItem {
    private String optionValue; //选项名称
    private String optionStatus;//选项状态 0 未选中 1选中

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(String optionStatus) {
        this.optionStatus = optionStatus;
    }
}
