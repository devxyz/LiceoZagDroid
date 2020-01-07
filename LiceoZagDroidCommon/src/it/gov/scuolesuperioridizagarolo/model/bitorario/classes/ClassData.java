package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import java.util.ArrayList;
import java.util.List;

public enum ClassData {
    // CLASSES
    CLASS_1A("1A", 21, 1, "A"),
    CLASS_1B("1B", 30, 1, "B"),
    CLASS_1C("1C", 22, 1, "C"),
    CLASS_1D("1D", 30, 1, "D"),
    CLASS_1E("1E", 22, 1, "E"),
    CLASS_1F("1F", 26, 1, "F"),
    CLASS_1G("1G", 20, 1, "G"),
    CLASS_1H("1H", 26, 1, "H"),

    CLASS_2A("2A", 20, 2, "A"),
    CLASS_2B("2B", 24, 2, "B"),
    CLASS_2C("2C", 22, 2, "C"),
    CLASS_2D("2D", 25, 2, "D"),
    CLASS_2E("2E", 19, 2, "E"),
    CLASS_2F("2F", 23, 2, "F"),
    CLASS_2H("2H", 19, 2, "H"),//19

    CLASS_3A("3A", 22, 3, "A"),
    CLASS_3B("3B", 20, 3, "B"),
    CLASS_3C("3C", 20, 3, "C"),
    CLASS_3D("3D", 28, 3, "D"),
    CLASS_3E("3E", 23, 3, "E"),
    CLASS_3F("3F", 21, 3, "F"),
    CLASS_3G("3G", 25, 3, "G"),
    CLASS_3H("3H", 20, 3, "H"),

    CLASS_4A("4A", 22, 4, "A"),
    CLASS_4B("4B", 21, 4, "B"),
    CLASS_4C("4C", 22, 4, "C"),
    CLASS_4D("4D", 17, 4, "D"),
    CLASS_4E("4E", 20, 4, "E"),
    CLASS_4F("4F", 16, 4, "F"),
    CLASS_4G("4G", 16, 4, "G"),

    CLASS_5A("5A", 21, 5, "A"),
    CLASS_5B("5B", 15, 5, "B"),
    CLASS_5C("5C", 17, 5, "C"),
    CLASS_5D("5D", 18, 5, "D"),
    CLASS_5E("5E", 17, 5, "E"),
//    CLASS_SCONOSCIUTA("--", 0, 0, "-")
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


    public static void main(String[] args) {
        for (ClassData roomData : values()) {
            System.out.println(roomData._class + roomData._section);
        }
    }


}
