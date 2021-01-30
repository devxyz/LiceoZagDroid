package utility.aule2020_21;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

public class UtilizzoClassi {
    public final RoomData room;
    public final ClassData clazz;
    public final EGiorno day;

    public UtilizzoClassi(RoomData room, ClassData clazz, EGiorno day) {
        this.room = room;
        this.clazz = clazz;
        this.day = day;
    }


    private static String toString(EGiorno g) {
        return "EGiorno." + g.name();
    }

    private static String toString(RoomData r) {
        return "RoomData." + r.name();
    }

    private static String toString(ClassData r) {
        return "ClassData." + r.name();
    }

    public String toStringGenerateCode() {
        return "new " + getClass().getSimpleName() + "(" +
                toString(room) + ","
                + toString(clazz) + ","
                + toString(day) + ")";
    }

    public UtilizzoClassi successivo() {
        return new UtilizzoClassi(room, clazz, day.nextGiornoLezione());
    }

    public UtilizzoClassi successivo(int n) {
        UtilizzoClassi u = this;
        for (int j = 1; j <= n; j++) {
            u = u.successivo();
        }
        return u;
    }

}
