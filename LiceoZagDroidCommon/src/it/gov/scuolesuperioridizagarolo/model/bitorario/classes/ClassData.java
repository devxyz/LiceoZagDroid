package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

public enum ClassData {
    // CLASSES
    CLASS_1A("1A", 24, 1, "A"),
    CLASS_1B("1B", 30, 1, "B"),
    CLASS_1C("1C", 25, 1, "C"),
    CLASS_1D("1D", 29, 1, "D"),
    CLASS_1E("1E", 24, 1, "E"),
    CLASS_1F("1F", 29, 1, "F"),
    CLASS_1H("1H", 29, 1, "H"),

    CLASS_2A("2A", 24, 2, "A"),
    CLASS_2B("2B", 21, 2, "B"),
    CLASS_2C("2C", 22, 2, "C"),
    CLASS_2D("2D", 30, 2, "D"),
    CLASS_2E("2E", 23, 2, "E"),
    CLASS_2F("2F", 24, 2, "F"),
    CLASS_2G("2G", 24, 2, "G"),
    CLASS_2H("2H", 21, 2, "H"),

    CLASS_3A("3A", 23, 3, "A"),
    CLASS_3B("3B", 23, 3, "B"),
    CLASS_3C("3C", 26, 3, "C"),
    CLASS_3D("3D", 21, 3, "D"),
    CLASS_3E("3E", 20, 3, "E"),
    CLASS_3F("3F", 21, 3, "F"),
    CLASS_3G("3G", 22, 3, "G"),

    CLASS_4A("4A", 21, 4, "A"),
    CLASS_4B("4B", 16, 4, "B"),
    CLASS_4C("4C", 17, 4, "C"),
    CLASS_4D("4D", 21, 4, "D"),
    CLASS_4E("4E", 18, 4, "E"),

    CLASS_5A("5A", 23, 5, "A"),
    CLASS_5B("5B", 20, 5, "B"),
    CLASS_5C("5C", 19, 5, "C"),
    CLASS_5D("5D", 18, 5, "D"),
    CLASS_5E("5E", 24, 5, "E"),
    CLASS_5F("5F", 17, 5, "F");

    public final String name;
    public final int numberOfStudents;
    public final int _class;
    public final String _section;

    ClassData(String name, int numberOfStudents, int aClass, String section) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        _class = aClass;
        _section = section;
    }


    @Override
    public String toString() {
        return name;
    }
}