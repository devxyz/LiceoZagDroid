package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum ClassData {
    // CLASSES
    CLASS_1A("1A", 27, 1, "A"),
    CLASS_1B("1B", 26, 1, "B"),
    CLASS_1C("1C", 25, 1, "C"),
    CLASS_1D("1D", 24+1, 1, "D"),//aggiunto studente
    CLASS_1E("1E", 25, 1, "E"),
    CLASS_1F("1F", 25+1, 1, "F"),//aggiunto studente

    CLASS_2A("2A", 19, 2, "A"),
    CLASS_2B("2B", 27, 2, "B"),
    CLASS_2C("2C", 19, 2, "C"),
    CLASS_2D("2D", 28, 2, "D"),
    CLASS_2E("2E", 22, 2, "E"),
    CLASS_2F("2F", 26, 2, "F"),
    CLASS_2G("2G", 19, 2, "G"),
    CLASS_2H("2H", 24, 2, "H"),


    CLASS_3A("3A", 21 + 1, 3, "A"),//con docente sostegno
    CLASS_3B("3B", 24, 3, "B"),
    CLASS_3C("3C", 21, 3, "C"),
    CLASS_3D("3D", 25, 3, "D"),
    CLASS_3E("3E", 19 + 1, 3, "E"),//con docente sostegno
    CLASS_3F("3F", 22+1, 3, "F"),//aggiunto studente
    CLASS_3H("3H", 18, 3, "H"),

    CLASS_4A("4A", 20, 4, "A"),
    CLASS_4B("4B", 20, 4, "B"),
    CLASS_4C("4C", 20, 4, "C"),
    CLASS_4D("4D", 26, 4, "D"),
    CLASS_4E("4E", 23, 4, "E"),
    CLASS_4F("4F", 21, 4, "F"),
    CLASS_4G("4G", 25, 4, "G"),
    CLASS_4H("4H", 20 + 1, 4, "H"),//con docente sostegno

    CLASS_5A("5A", 22, 5, "A"),
    CLASS_5B("5B", 21, 5, "B"),
    CLASS_5C("5C", 22, 5, "C"),
    CLASS_5D("5D", 16, 5, "D"),
    CLASS_5E("5E", 20, 5, "E"),
    CLASS_5F("5F", 16, 5, "F"),
    CLASS_5G("5G", 16, 5, "G"),
    ;


    public final String className;
    public final int numberOfStudents;
    public final int _class;
    public final String _section;

    public boolean isOrdinario() {
        return !isScienzeApp();
    }

    public boolean isScienzeApp() {
        return
                _section.equalsIgnoreCase("B")
                        || _section.equalsIgnoreCase("D")
                        || _section.equalsIgnoreCase("F")
                        || _section.equalsIgnoreCase("H");
    }

    ClassData(String classname, int numberOfStudents, int aClass, String section) {
        this.className = classname;
        this.numberOfStudents = numberOfStudents;
        _class = aClass;
        _section = section;
    }

    public static List<ClassData> searchPerAnnoCorso(final int anno) {
        return searchPerAnnoCorso(anno, null);
    }

    public static List<ClassData> searchPerAnnoCorso(final int anno, final Boolean ordinario) {
        if (ordinario == null) {
            return filter(new ClassDataFilter() {
                @Override
                public boolean accept(ClassData c) {
                    return c._class == anno;
                }
            });
        } else {
            return filter(new ClassDataFilter() {
                @Override
                public boolean accept(ClassData c) {
                    return c._class == anno && c.isOrdinario() == ordinario;
                }
            });

        }


    }

    public static ClassData search(String s) {
        //if (s == null) return CLASS_SCONOSCIUTA;
        for (ClassData xx : values()) {
            if (xx.className.equalsIgnoreCase(s)) return xx;
        }
        throw new IllegalArgumentException("Classe sconosciuta: " + s);
        //return CLASS_SCONOSCIUTA;
    }

    public static interface ClassDataFilter {
        boolean accept(ClassData c);
    }

    public static List<ClassData> filter(ClassDataFilter f) {
        List<ClassData> ris = new ArrayList<>();
        for (ClassData x : values()) {
            if (f.accept(x)) ris.add(x);
        }
        return ris;
    }

    public boolean flagClasseFittizia() {
        return false;/*this == CLASS_SCONOSCIUTA;*/
    }

    @Override
    public String toString() {
        return className;
    }

    public String toStringDimensioni() {
        return className + "[" + numberOfStudents + "]";
    }

    static int sommaStudenti(List<ClassData> cc) {
        int s = 0;
        for (ClassData x : cc) {
            s += x.numberOfStudents;
        }
        return s;
    }

    public static void main(String[] args) {

        for (ClassData xx : values()) {
            if(xx._class==5)
                System.out.println(xx.className+"\t"+xx.numberOfStudents);
        }



        for (ClassData value : ClassData.values()) {
            System.out.println(value.className+" (Liceo Scientifico)");
        }

        System.out.println("1A (IPIA)");
        System.out.println("1B (IPIA)");
        System.out.println("2A (IPIA)");
        System.out.println("2B (IPIA)");
        System.out.println("3A (IPIA)");
        System.out.println("4A (IPIA)");
        System.out.println("5A (IPIA)");

    }

    public static void mainx(String[] args) {
        int anno = 1;
        Boolean ordinario = null;

        for (int i = 1; i <= 4; i++) {
            anno = i;
            ordinario = null;
            System.out.println("ANNO " + anno + (ordinario == null ? " TUTTE" : (ordinario ? " ORDINARIO" : " SCIENZE APP.")) + "\t" + searchPerAnnoCorso(anno, ordinario) + ":\t" + sommaStudenti(searchPerAnnoCorso(anno, ordinario)));
        }

        for (int i = 1; i <= 4; i++) {
            anno = i;
            ordinario = true;
            System.out.println("ANNO " + anno + (ordinario == null ? " TUTTE" : (ordinario ? " ORDINARIO" : " SCIENZE APP.")) + "\t" + searchPerAnnoCorso(anno, ordinario) + ":\t" + sommaStudenti(searchPerAnnoCorso(anno, ordinario)));
        }


        System.out.println("=================");
        System.out.print("CONCATENA(");
        for (int i = 1; i <= 34; i++) {
            System.out.print("H" + i + ";");
        }
        System.out.print(")");

        System.out.println("Triennio:");
        System.out.println(filter(new ClassDataFilter() {
            @Override
            public boolean accept(ClassData c) {
                return c._class>=3;
            }
        }));
    }


}
