package com.syy.moduledbbase.db.entity.moduleuav.panorama;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

public class Big2littleBeanConverter implements PropertyConverter<List<Big2littleBean>, String> {

    @Override
    public List<Big2littleBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        String[] list_str = databaseValue.split("!!");
        List<Big2littleBean> list_transport = new ArrayList<>();
        for (String s : list_str) {
            list_transport.add(gson.fromJson(s, Big2littleBean.class));
        }
        return list_transport;
    }

    @Override
    public String convertToDatabaseValue(List<Big2littleBean> arrays) {
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
