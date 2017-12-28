package it.gov.scuolesuperioridizagarolo.model.menu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by stefano on 19/08/15.
 */
public class DataMenuInfoStack implements Serializable{

    private final ArrayList<DataMenuInfo> stack = new ArrayList<>();

    public ArrayList<DataMenuInfo> getStack() {
        return new ArrayList<>(stack);
    }

    public void add(DataMenuInfo m) {
        stack.remove(m);
        stack.add(m);
    }

    public DataMenuInfo back() {
        //rimuove l'ultimo e il penultimo
        if (stack.size() > 0)
            stack.remove(stack.size() - 1);

        if (stack.size() > 0)
            return stack.remove(stack.size() - 1);
        return null;
    }
}
