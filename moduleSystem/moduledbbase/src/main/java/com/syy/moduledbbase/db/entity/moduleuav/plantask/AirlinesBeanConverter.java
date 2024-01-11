package com.syy.moduledbbase.db.entity.moduleuav.plantask;

import com.google.gson.Gson;
import com.syy.moduledbbase.db.entity.moduleuav.fine.FineDataBean;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

public class AirlinesBeanConverter implements PropertyConverter<List<AirlinesBean>, String> {

    @Override
    public List<AirlinesBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        String[] list_str = databaseValue.split("!!");
        List<AirlinesBean> list_transport = new ArrayList<>();
        for (String s : list_str) {
            list_transport.add(new Gson().fromJson(s, AirlinesBean.class));
        }
        return list_transport;
    }

    @Override
    public String convertToDatabaseValue(List<AirlinesBean> arrays) {
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
