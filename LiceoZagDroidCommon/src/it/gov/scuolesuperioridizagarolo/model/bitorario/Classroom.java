package it.gov.scuolesuperioridizagarolo.model.bitorario;

import java.util.*;

public enum Classroom {
    CLASSE_1A("IA", 30, false),
    //CLASSE_2A("IIA", 20, false),//todo: originale, modificato per simulazione
    CLASSE_2A("IIA", 25, false),
    CLASSE_3A("IIIA", 23, false),
    CLASSE_4A("IVA", 21, false),
    CLASSE_5A("VA", 19, false),
    //===========================================
    CLASSE_1B("IB", 28, true),
    CLASSE_2B("IIB", 24, true),
    CLASSE_3B("IIIB", 23, true),
    CLASSE_4B("IVB", 21, false),
    CLASSE_5B("VB", 19, false),
    //===========================================
    CLASSE_1C("IC", 31, false),
    //CLASSE_2C("IIC", 18, false),//todo: originale, modificato per simulazione
    CLASSE_2C("IIC", 24, false),
    CLASSE_3C("IIIC", 24, false),
    CLASSE_4C("IVC", 20, false),
    CLASSE_5C("VC", 21, false),
    //===========================================
    CLASSE_1D("ID", 25, true),
    CLASSE_2D("IID", 24, true),
    CLASSE_3D("IIID", 20, true),
    CLASSE_4D("IVD", 18, false),
    CLASSE_5D("VD", 23, false),
    //===========================================
    CLASSE_1E("IE", 29, false),
    //CLASSE_2E("IIE", 23, false),//todo: originale, modificato per simulazione
    CLASSE_2E("IIE", 26, false),
    CLASSE_3E("IIIE", 28, false),
    CLASSE_4E("IVE", 23, false),
    CLASSE_5E("VE", 23, false),
    //===========================================
    CLASSE_1F("IF", 28, true),
    //CLASSE_2F("IIF", 20, false),//todo: originale, modificato per simulazione
    CLASSE_2F("IIF", 26, false),
    CLASSE_3F("IIIF", 21, true),
    //===========================================
    CLASSE_1G("IG", 25, false);

    private static Map<String, Classroom> m = null;
    public final int numStudents;
    public final boolean flagScienzeApplicate;
    public final String label;

    Classroom(String n, int numStudents, boolean flagScienzeApplicate) {
        this.label = n;
        this.numStudents = numStudents;
        this.flagScienzeApplicate = flagScienzeApplicate;
    }

    public static Classroom getByLabel(String label) {
        final Classroom classroom = getMapByLabel().get(label);
        if (classroom == null)
            throw new NullPointerException("Etichetta " + label + " non trovata per la classe");
        return classroom;
    }

    public static Map<String, Classroom> getMapByLabel() {
        if (m == null) {
            m = new TreeMap<>();
            for (Classroom room : values()) {
                m.put(room.label, room);
            }
            m = Collections.unmodifiableMap(m);
        }
        return m;
    }

    public static void main(String[] args) {
        /*for (Classroom x : values()) {
            String n = x.name().replace("CLASSE_", "")
                    .replace("1", "I")
                    .replace("2", "II")
                    .replace("3", "III")
                    .replace("4", "IV")
                    .replace("5", "V");
            if (x.flagScienzeApplicate)
                System.out.println(x.name() + "(\"" + n + "\"," + x.numStudents + ",true),");
            else
                System.out.println(x.name() + "(\"" + n + "\"," + x.numStudents + "),");
        }*/
        System.out.println(getSortedByStudents());
    }

    public static List<Classroom> getSortedByStudents() {
        ArrayList<Classroom> c = new ArrayList<>(Arrays.asList(values()));
        Collections.sort(c, new Comparator<Classroom>() {
            @Override
            public int compare(Classroom o1, Classroom o2) {
                return new Integer(o1.numStudents).compareTo(o2.numStudents);
            }
        });
        return c;
    }

    public int classNumber() {
        if (label.startsWith("III")) return 3;
        if (label.startsWith("II")) return 2;
        if (label.startsWith("IV")) return 4;
        if (label.startsWith("I")) return 1;
        if (label.startsWith("V")) return 5;
        throw new IllegalArgumentException("Label non gestita " + label);
    }

    public String indirizzo() {
        if (flagScienzeApplicate)
            return "S";
        else
            return "O";
    }

    public String toString() {
        return label + "(" + numStudents + ")";
    }


}
