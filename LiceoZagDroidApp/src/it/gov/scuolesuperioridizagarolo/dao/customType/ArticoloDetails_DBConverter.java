package it.gov.scuolesuperioridizagarolo.dao.customType;

import com.google.gson.Gson;
import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by stefano on 27/02/2018.
 */
public class ArticoloDetails_DBConverter implements PropertyConverter<ArticoloDetails, String> {
    @Override
    public ArticoloDetails convertToEntityProperty(String s) {
        if (s == null) return null;
        final int i = s.indexOf('#');
        String type = s.substring(0, i);
        String json = s.substring(i);
        Gson g = new Gson();
        try {
            final Class<?> aClass = Class.forName(type);
            final ArticoloDetails o = (ArticoloDetails) g.fromJson(json, aClass);
            return o;

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }


    }

    @Override
    public String convertToDatabaseValue(ArticoloDetails articoloEnum) {
        if (articoloEnum == null) return null;
        Gson g = new Gson();
        final String json = g.toJson(articoloEnum);
        return articoloEnum.getClass().getName() + "#" + json;
    }
}
