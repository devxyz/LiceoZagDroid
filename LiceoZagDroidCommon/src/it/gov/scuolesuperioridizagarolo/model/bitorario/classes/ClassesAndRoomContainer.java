package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Elenco dai per aule e classi
 */
public class ClassesAndRoomContainer {
    public static final String USCITA_DIDATTICA = "###";
    private static final Map<String, RoomData> aule = new TreeMap<>();
    //public static class Class
    private static final Map<String, ClassData> classi = new TreeMap<>();

    static {
        for (ClassData c : ClassData.values()) {
            classi.put(c.name.toUpperCase(), c);
        }
        for (RoomData c : RoomData.values()) {
            aule.put(c.name.toUpperCase(), c);
        }
    }

    public static RoomData USCITA_DIDATTICA() {
        return RoomData.USCITA_DIDATTICA;
    }

    public static RoomData getRoom(BitOrarioOraLezione l) {
        return getRoom(l.getNomeAula());
    }

    public static RoomData getRoom(String aula) {
        if (aula == null) return null;

        //final RoomData roomData = aule.get(aula.split("_")[0].toUpperCase());
        final RoomData roomData = aule.get(aula.toUpperCase());
        if (roomData == null) {
            throw new IllegalArgumentException("Aula " + aula + " inesistente");
        }

        return roomData;
    }


    public static ClassData getClass(String classe) {
        final ClassData classData = classi.get(classe.toUpperCase());
        if (classData == null) {
            throw new IllegalArgumentException("Classe " + classe + " inesistente");
        }
        return classData;
    }

    public static ArrayList<ClassData> getAllClasses() {
        return new ArrayList<>(classi.values());
    }

    public static ArrayList<RoomData> getAllRooms() {
        return new ArrayList<>(aule.values());
    }

    public static ClassData getClass(BitOrarioOraLezione l) {
        return getClass(l.getClasse());
    }

    public static void main(String[] args) {
        for (String s : aule.keySet()) {

            System.out.println(s);
        }
    }

}
