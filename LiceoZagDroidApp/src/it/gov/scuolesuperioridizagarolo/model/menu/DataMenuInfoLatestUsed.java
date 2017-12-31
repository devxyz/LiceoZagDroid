package it.gov.scuolesuperioridizagarolo.model.menu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by stefano on 07/09/15.
 */
public class DataMenuInfoLatestUsed {

    private final LinkedList<String> menuIdSortByUse;

    public DataMenuInfoLatestUsed(List<String> value) {
        menuIdSortByUse = new LinkedList<>(value);
    }

    public void add(String item) {
        //aggiunge in testa (rimuovendolo se gia' presente)
        menuIdSortByUse.remove((Object) item);
        menuIdSortByUse.addFirst(item);

    }

    public void add(DataMenuInfo item) {
        //aggiunge in testa (rimuovendolo se gia' presente)
        if (!item.getFlags().contains(DataMenuInfoFlag.DONT_SHOW_IN_HOME))
            add(item.getMenuID());
    }

    private DataMenuInfo _find(List<DataMenuInfo> ll, String id) {
        for (DataMenuInfo l : ll) {
            if (l.getMenuID() == id) return l;
        }
        return null;
    }

    public List<DataMenuInfo> filter(List<DataMenuInfo> ll) {
        List<DataMenuInfo> ris = new ArrayList<>(ll.size());
        for (String id : menuIdSortByUse) {
            final DataMenuInfo m = _find(ll, id);

            if (m != null)
                if (!m.getFlags().contains(DataMenuInfoFlag.DONT_SHOW_IN_HOME))
                    ris.add(m);

        }
        return ris;
    }


    public LinkedList<String> getMenuIdSortByUse() {
        return menuIdSortByUse;
    }
}
