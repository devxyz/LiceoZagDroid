package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2018_19;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by stefano on 10/01/2019.
 */
public class SimulazioneProvaQuinti {
    public static void main(String[] args) {
        ArrayList<ClassData> classiQuinte = new ArrayList<>();
        for (ClassData classData : ClassData.values()) {
            if (classData._class == 5) {
                classiQuinte.add(classData);
            }
        }

        ArrayList<RoomData> aulePrimoPiano = new ArrayList<>();
        for (RoomData roomData : RoomData.values()) {
            if (roomData.location == ERoomArea.AREA_C) {
                aulePrimoPiano.add(roomData);
            }
        }


        Collections.sort(aulePrimoPiano, new Comparator<RoomData>() {
            @Override
            public int compare(RoomData o1, RoomData o2) {
                return -new Integer(o1.maxStudents).compareTo(o2.maxStudents);
            }
        });


        Collections.sort(classiQuinte, new Comparator<ClassData>() {
            @Override
            public int compare(ClassData o1, ClassData o2) {
                return -new Integer(o1.numberOfStudents).compareTo(o2.numberOfStudents);
            }
        });


        System.out.println("AULE");
        for (RoomData roomData : aulePrimoPiano) {
            System.out.println(" - " + roomData + ": max " + roomData.maxStudents);
        }

        System.out.println();
        System.out.println("CLASSI");
        for (ClassData classData : classiQuinte) {
            System.out.println(" - " + classData + ": num.stud " + classData.numberOfStudents);
        }

    }
}
