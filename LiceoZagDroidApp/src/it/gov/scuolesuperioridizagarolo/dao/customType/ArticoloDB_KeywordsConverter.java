package it.gov.scuolesuperioridizagarolo.dao.customType;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by stefano on 27/02/2018.
 */
public class ArticoloDB_KeywordsConverter implements PropertyConverter<ArticoloDB_Keywords, String> {
    @Override
    public ArticoloDB_Keywords convertToEntityProperty(String s) {
        final ArticoloDB_Keywords ris = new ArticoloDB_Keywords();
        if (s == null) {
            return ris;
        }
        ris.add(s.split(","));

        return ris;
    }

    @Override
    public String convertToDatabaseValue(ArticoloDB_Keywords articoloDB_keywords) {
        if (articoloDB_keywords == null)
            articoloDB_keywords = new ArticoloDB_Keywords();

        StringBuilder sb = new StringBuilder();
        for (String w : articoloDB_keywords.getKeywords()) {
            if (sb.length() > 0)
                sb.append(",");
            sb.append(w);
        }

        return null;
    }
}
