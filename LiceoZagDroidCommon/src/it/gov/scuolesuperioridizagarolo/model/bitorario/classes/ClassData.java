package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

public class ClassData {

    public final String name;
    public final int numberOfStudents;
    public final int _class;
    public final String _section;
    public final int progressive;

    public ClassData(int progressive, String name, int numberOfStudents, int aClass, String section) {
        this.progressive = progressive;
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
