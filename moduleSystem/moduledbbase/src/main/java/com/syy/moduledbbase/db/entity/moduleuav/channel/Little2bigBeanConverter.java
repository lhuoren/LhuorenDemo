package com.syy.moduledbbase.db.entity.moduleuav.channel;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

public class Little2bigBeanConverter implements PropertyConverter<List<Little2bigBean>, String> {

    @Override
    public List<Little2bigBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        String[] list_str = databaseValue.split("!!");
        List<Little2bigBean> list_transport = new ArrayList<>();
        for (String s : list_str) {
            list_transport.add(new Gson().fromJson(s, Little2bigBean.class));
        }
        return list_transport;
    }

    @Override
    public String convertToDatabaseValue(List<Little2bigBean> arrays) {
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
