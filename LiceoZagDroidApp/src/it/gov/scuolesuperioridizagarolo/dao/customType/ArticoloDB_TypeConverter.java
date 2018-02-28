package it.gov.scuolesuperioridizagarolo.dao.customType;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by stefano on 27/02/2018.
 */
public class ArticoloDB_TypeConverter implements PropertyConverter<ArticoloDB_Type, String> {
    @Override
    public ArticoloDB_Type convertToEntityProperty(String s) {
        if (s == null) return null;
        return ArticoloDB_Type.valueOf(s);
    }

    @Override
    public String convertToDatabaseValue(ArticoloDB_Type articoloDB_type) {
        return articoloDB_type.name();
    }
}
