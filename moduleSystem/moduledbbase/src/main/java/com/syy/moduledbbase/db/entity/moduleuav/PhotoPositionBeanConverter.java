package com.syy.moduledbbase.db.entity.moduleuav;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhotoPositionBeanConverter implements PropertyConverter<List<PhotoPositionBean>, String> {
    @Override
    public List<PhotoPositionBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        List<String> list_str = Arrays.asList(databaseValue.split("#"));
        List<PhotoPositionBean> list_transport = new ArrayList<>();
        for (String s : list_str) {
            list_transport.add(new Gson().fromJson(s, PhotoPositionBean.class));
        }
        return list_transport;
    }

    @Override
    public String convertToDatabaseValue(List<PhotoPositionBean> arrays) {
        if (arrays == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int array = 0; array < arrays.size(); array++) {
                String str = new Gson().toJson(arrays.get(array));
                sb.append(str);
                if (array != arrays.size() - 1) {
                    sb.append("#");
                }
            }
            return sb.toString();
        }
    }
}
