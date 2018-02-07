package it.gov.scuolesuperioridizagarolo.model;

/**
 * Created by stefano on 01/08/15.
 */
public class C_Pair<T1, T2> {
    public final T1 a;
    public final T2 b;

    public C_Pair(T1 a, T2 b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        C_Pair<?, ?> c_pair = (C_Pair<?, ?>) o;

        if (a != null ? !a.equals(c_pair.a) : c_pair.a != null) return false;
        return b != null ? b.equals(c_pair.b) : c_pair.b == null;

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }
}
