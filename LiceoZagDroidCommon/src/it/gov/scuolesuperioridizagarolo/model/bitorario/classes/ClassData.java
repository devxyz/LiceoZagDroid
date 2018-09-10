package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

public enum ClassData {
    // CLASSES
    CLASS_1A("1A", 28, 1, "A"),
    CLASS_1B("1B", 28, 1, "B"),
    CLASS_1C("1C", 23, 1, "C"),
    CLASS_1D("1D", 28, 1, "D"),
    CLASS_1E("1E", 25, 1, "E"),
    CLASS_1F("1F", 28, 1, "F"),
    CLASS_1H("1H", 22, 1, "H"),

    CLASS_2A("2A", 24, 2, "A"),
    CLASS_2B("2B", 27, 2, "B"),
    CLASS_2C("2C", 28, 2, "C"),
    CLASS_2D("2D", 26, 2, "D"),
    CLASS_2E("2E", 21, 2, "E"),
    CLASS_2F("2F", 23, 2, "F"),
    CLASS_2G("2G", 24, 2, "G"),
    CLASS_2H("2H", 24, 2, "H"),

    CLASS_3A("3A", 27, 3, "A"),
    CLASS_3B("3B", 20, 3, "B"),
    CLASS_3C("3C", 26, 3, "C"),
    CLASS_3D("3D", 24, 3, "D"),
    CLASS_3E("3E", 27, 3, "E"),
    CLASS_3F("3F", 27, 3, "F"),
    CLASS_3G("3G", 27, 3, "G"),

    CLASS_4A("4A", 23, 4, "A"),
    CLASS_4B("4B", 21, 4, "B"),
    CLASS_4C("4C", 22, 4, "C"),
    CLASS_4D("4D", 19, 4, "D"),
    CLASS_4E("4E", 25, 4, "E"),

    CLASS_5A("5A", 14, 5, "A"),
    CLASS_5B("5B", 22, 5, "B"),
    CLASS_5C("5C", 17, 5, "C"),
    CLASS_5D("5D", 17, 5, "D"),
    CLASS_5E("5E", 17, 5, "E"),
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
