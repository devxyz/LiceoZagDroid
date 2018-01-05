package it.gov.scuolesuperioridizagarolo.model.menu;

import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by stefano on 19/08/15.
 */
public class DataMenuInfoStack implements Serializable {

    private final ArrayList<DataMenuInfoCall> stack = new ArrayList<>();

    public ArrayList<DataMenuInfoCall> getStack() {
        return new ArrayList<>(stack);
    }

    public void add(DataMenuInfo m, Bundle parameter) {
        add(new DataMenuInfoCall(m, parameter));
    }

    public void add(DataMenuInfoCall m) {
        stack.remove(m);
        stack.add(m);
    }

    public DataMenuInfoCall back() {
        //rimuove l'ultimo e il penultimo
        if (stack.size() > 0)
            stack.remove(stack.size() - 1);

        if (stack.size() > 0)
            return stack.remove(stack.size() - 1);
        return null;
    }

    public static class DataMenuInfoCall {
        public final DataMenuInfo menu;
        public final Bundle parameter;

        private DataMenuInfoCall(DataMenuInfo menu, Bundle parameter) {
            this.menu = menu;
            this.parameter = parameter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DataMenuInfoCall that = (DataMenuInfoCall) o;

            if (menu != null ? !menu.equals(that.menu) : that.menu != null) return false;
            return parameter != null ? parameter.equals(that.parameter) : that.parameter == null;

        }

        @Override
        public int hashCode() {
            int result = menu != null ? menu.hashCode() : 0;
            result = 31 * result + (parameter != null ? parameter.hashCode() : 0);
            return result;
        }
    }
}
