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
        String json = s.substring(i+1);
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

    public static void main(String[] args) {
        String s="it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetailsEvento#{\"dataEvento\":\"Mar 8, 2014 12:00:00 PM\",\"oggetto\":\"GARA A SQUADRE OLIMPIADI DI MATEMATICA 8 marzo 2014\",\"paroleLowerCase\":[\"8 marzo 2014\",\"a\",\"di\",\"gara\",\"matematica\",\"olimpiadi\",\"squadre\"]}";
        ArticoloDetails_DBConverter aa=new ArticoloDetails_DBConverter();
        final ArticoloDetails articoloDetails = aa.convertToEntityProperty(s);
        System.out.println(articoloDetails);
    }
}
