package com.syy.moduledbbase.db.entity.moduleuav.channel;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

public class ChannelDataBeanConverter implements PropertyConverter<List<ChannelDataBean>, String> {

    @Override
    public List<ChannelDataBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        String[] list_str = databaseValue.split("!!");

        List<ChannelDataBean> list_transport = new ArrayList<>();
        for (String s : list_str) {
            list_transport.add(new Gson().fromJson(s, ChannelDataBean.class));
        }
        return list_transport;
    }

    @Override
    public String convertToDatabaseValue(List<ChannelDataBean> arrays) {
        if (arrays == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int array = 0; array < arrays.size(); array++) {
                String str = new Gson().toJson(arrays.get(array));
                sb.append(str);
                if (array != arrays.size() - 1) {
                    sb.append("!!");
                }
            }
            return sb.toString();
        }
    }
}
