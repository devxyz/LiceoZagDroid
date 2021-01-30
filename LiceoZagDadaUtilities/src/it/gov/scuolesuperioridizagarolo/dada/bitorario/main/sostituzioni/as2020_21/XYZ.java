package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

public class XYZ {
    public static void main(String[] args) {
        ClassData[] aa = new ClassData[]{
                ClassData.CLASS_1D,
                ClassData.CLASS_1F,
                ClassData.CLASS_2D,
                ClassData.CLASS_3D,
                ClassData.CLASS_4A,
                ClassData.CLASS_4D,
                ClassData.CLASS_4F,
                ClassData.CLASS_5D,
                ClassData.CLASS_5F,
        };
        int s = 0;
        for (ClassData classData : aa) {
            s += classData.numberOfStudents;
        }
        System.out.println(s);
        System.out.println(s - ClassData.CLASS_1D.numberOfStudents);

    }
}
