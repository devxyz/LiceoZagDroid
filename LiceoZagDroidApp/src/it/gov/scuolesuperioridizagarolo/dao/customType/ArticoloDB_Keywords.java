package it.gov.scuolesuperioridizagarolo.dao.customType;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by stefano on 27/02/2018.
 */
public class ArticoloDB_Keywords {
    private final LinkedHashSet<String> keywords;

    public ArticoloDB_Keywords() {
        keywords = new LinkedHashSet<>();
    }

    public ArticoloDB_Keywords(String... k) {
        keywords = new LinkedHashSet<>();
        add(k);
    }

    public ArticoloDB_Keywords(Collection<String> k) {
        keywords = new LinkedHashSet<>();

        add(k);
    }


    void add(String... k) {
        for (String s : k) {
            if (s != null) {
                s = s.trim();
                if (s.length() > 0)
                    keywords.add(s.toLowerCase());
            }
        }

    }

    void add(Collection<String> k) {
        for (String s : k) {
            if (s != null) {
                s = s.trim();
                if (s.length() > 0)
                    keywords.add(s.toLowerCase());
            }
        }

    }

    public Set<String> getKeywords() {
        return keywords;
    }
}
