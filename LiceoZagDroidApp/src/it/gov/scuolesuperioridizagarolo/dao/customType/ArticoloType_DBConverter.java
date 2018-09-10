package it.gov.scuolesuperioridizagarolo.dao.customType;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by stefano on 27/02/2018.
 */
public class ArticoloType_DBConverter implements PropertyConverter<ArticoloType, String> {
    @Override
    public ArticoloType convertToEntityProperty(String s) {
        if (s == null) return null;




        return ArticoloType.valueOf(s);
    }

    @Override
    public String convertToDatabaseValue(ArticoloType articoloDB_type) {



        return articoloDB_type == null ? null : articoloDB_type.name();
    }
}
