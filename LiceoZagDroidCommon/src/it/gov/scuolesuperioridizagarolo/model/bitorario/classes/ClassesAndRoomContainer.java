package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Elenco dai per aule e classi
 */
public class ClassesAndRoomContainer {
    private static final Map<String, RoomData> aule = new TreeMap<>();
    //public static class Class
    private static final Map<String, ClassData> classi = new TreeMap<>();

    static {
        for (ClassData c : ClassData.values()) {
            classi.put(c.className.toUpperCase(), c);
        }
        for (RoomData c : RoomData.values()) {
            for (String e : c.etichetteFileOrario) {
                aule.put(e.toUpperCase(), c);
            }
        }
    }

    public static RoomData parseRoom(String aula) {
        if (aula == null) return null;

        //final RoomData roomData = aule.get(aula.split("_")[0].toUpperCase());
        final RoomData roomData = aule.get(aula.toUpperCase());
        if (roomData == null) {
            throw new IllegalArgumentException("Aula " + aula + " inesistente");
//            return RoomData.AULA_SCONOSCIUTA;
        }

        return roomData;
    }


    public static ClassData parseClass(String classe) {
        final ClassData classData = classi.get(classe.toUpperCase());
        if (classData == null) {
            //return ClassData.CLASS_SCONOSCIUTA;
            throw new IllegalArgumentException("Classe " + classe + " inesistente");
        }
        return classData;
    }

    public static ArrayList<ClassData> getAllClasses() {
        return new ArrayList<>(classi.values());
    }

    public static ArrayList<ClassData> getAllValidClasses() {
        final ArrayList<ClassData> ris = new ArrayList<>();
        for (ClassData x : getAllClasses()) {
            if (x.flagClasseFittizia()) continue;
            ris.add(x);
        }
        return ris;
    }

    public static ArrayList<RoomData> getAllValidRooms() {
        final ArrayList<RoomData> ris = new ArrayList<>();
        for (RoomData x : getAllRooms()) {
            if (x.flagAulaFittizia()) continue;
            ris.add(x);
        }
        return ris;
    }

    public static ArrayList<RoomData> getAllRooms() {
        return new ArrayList<>(aule.values());
    }

    public static void main(String[] args) {
        for (String s : aule.keySet()) {

            System.out.println(s);
        }
    }

}
