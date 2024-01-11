package com.syy.moduledbbase.db.entity.moduleuav.pointcloud;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

public class PointCloudDataBeanConverter implements PropertyConverter<List<PointCloudDataBean>, String> {

    @Override
    public List<PointCloudDataBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        String[] list_str = databaseValue.split("!!");

        List<PointCloudDataBean> list_transport = new ArrayList<>();
        for (String s : list_str) {
            list_transport.add(new Gson().fromJson(s, PointCloudDataBean.class));
        }
        return list_transport;
    }

    @Override
    public String convertToDatabaseValue(List<PointCloudDataBean> arrays) {
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
