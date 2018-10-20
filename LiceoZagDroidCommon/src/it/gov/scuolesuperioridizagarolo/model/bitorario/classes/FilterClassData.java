package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by stefano on 19/10/2018.
 */
public abstract class FilterClassData {
    public final ClassData[] filterBy() {
        Set<ClassData> ris = new TreeSet<>();
        for (ClassData cl : ClassData.values()) {
            if (accept(cl)) {
                ris.add(cl);
            }
        }
        return ris.toArray(new ClassData[ris.size()]);
    }

    protected abstract boolean accept(ClassData r);
}
