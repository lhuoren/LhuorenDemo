package com.gac.nioapp.test.bean;

/******************************
 ** @author xyz
 ** @email xyz@gac-nio.com
 ** @date 2019-08-13 15:06
 ** @describe
 *******************************/
public class AppConfigBean {

    private String configKey;
    private String configValue;

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigKey() {
        return configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
