package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

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
        String[] auletxt = new String[]{
                "NOME RIDOTTO\tspecifica\tspeciale\tcollocazione\tid locale\tn.posti\nLIM",
                "A1\tAula Didattica\t\tPiano Terra (lato Segreteria)\t11\t26\t*",
                "A2\tAula Didattica\t\tPiano Terra (lato Segreteria)\t13\t24\t*",
                "A3\tAula Didattica\t\tPiano Terra (lato Segreteria)\t15\t28\t*",
                "A4_INF\tLaboratorio di Informatica\t*\tPiano Terra (lato Segreteria)\t17\t30\t*",
                "A5_DIS\tAula di Disegno\t*\tPiano Terra (lato Segreteria)\t22\t30\t*",
                "A6_FIS\tLaboratorio di Fisica\t*\tPiano Terra (lato Segreteria)\t20\t30\t*",
                "A7\tAula Didattica\t\tPiano Terra (lato Segreteria)\t21\t18\t-",
                "B10\tAula Didattica\t\tPiano Terra (lato Presidenza)\t8\t28\t*",
                "B11\tAula Didattica\t\tPiano Terra (lato Presidenza)\t31\t28\t*",
                "B12\tAula Didattica\t\tPiano Terra (lato Presidenza)\t32\t28\t-",
                "B13\tAula Didattica\t\tPiano Terra (lato Presidenza)\t33\t28\t*",
                "B8\tAula Didattica\t\tPiano Terra (lato Presidenza)\t10\t28\t*",
                "B9\tAula Didattica\t\tPiano Terra (lato Presidenza)\t9\t28\t*",
                "C14\tAula Didattica\t\tPiano Primo\t47\t25\t-",
                "C15\tAula Didattica\t\tPiano Primo\t46\t18\t-",
                "C16\tAula Didattica\t\tPiano Primo\t45\t28\t*",
                "C17\tAula Didattica\t\tPiano Primo\t51\t28\t*",
                "C18\tAula Didattica\t\tPiano Primo\t52\t28\t*",
                "C19\tAula Didattica\t\tPiano Primo\t53\t26\t*",
                "C20\tAula Didattica\t\tPiano Primo\t54\t26\t*",
                "C21\tAula Didattica\t\tPiano Primo\t55\t22\t-",
                "D22\tAula Didattica\t\tPiano Secondo\t61\t28\t*",
                "D23\tAula Didattica\t\tPiano Secondo\t62\t28\t*",
                "D24\tAula Didattica\t\tPiano Secondo\t63\t28\t*",
                "D25\tAula Didattica\t\tPiano Secondo\t64\t28\t*",
                "D26\tAula Didattica\t\tPiano Secondo\t65\t22\t-",
                "E27\tAula Didattica\t\tPiano Primo Esterno\t44\t26\t*",
                "E28\tAula Didattica\t\tPiano Primo Esterno\t39\t26\t*",
                "E29\tAula Didattica\t\tPiano Primo Esterno\t40\t26\t*",
                "E30\tAula Didattica\t\tPiano Primo Esterno\t41\t26\t*",
                "F31_PALESTRA\tPalestra\t*\tPiano Seminterrato\t0\t30\t-",
                "F32_SCI\tLaboratorio di Scienze\t*\tPiano Seminterrato\t0\t30\t*"
        };

        String[] classiTxt = new String[]{
                "classe\tsez\tnome\tnum studenti",
                "1\tA\t1A\t28",
                "1\tB\t1B\t28",
                "1\tC\t1C\t23",
                "1\tD\t1D\t28",
                "1\tE\t1E\t25",
                "1\tF\t1F\t28",
                "1\tG\t1G\t28",
                "1\tH\t1H\t22",
                "2\tA\t2A\t24",
                "2\tB\t2B\t27",
                "2\tC\t2C\t28",
                "2\tD\t2D\t26",
                "2\tE\t2E\t21",
                "2\tF\t2F\t23",
                "2\tG\t2G\t24",
                "3\tA\t3A\t27",
                "3\tB\t3B\t20",
                "3\tC\t3C\t26",
                "3\tD\t3D\t24",
                "3\tE\t3E\t27",
                "4\tA\t4A\t23",
                "4\tB\t4B\t21",
                "4\tC\t4C\t22",
                "4\tD\t4D\t19",
                "4\tE\t4E\t25",
                "4\tF\t4F\t19",
                "5\tA\t5A\t14",
                "5\tB\t5B\t22",
                "5\tC\t5C\t17",
                "5\tD\t5D\t17"
        };

        int count = 0;
        int progressive = 0;
        for (String riga : auletxt) {
            if (count++ == 0) continue;
            final String[] split = riga.split("\t");
            String nome = split[0].trim();
            ERoomArea area = ERoomArea.searchByDescription(split[3]);
            String uso = split[1].trim();
            int id = Integer.parseInt(split[4]);
            int maxStu = Integer.parseInt(split[5]);

            boolean flagLIM = split[6].trim().equalsIgnoreCase("*");

            aule.put(nome, new RoomData(progressive, nome, uso, maxStu, id, area, flagLIM, false));
            progressive++;
        }

        //aula speciale uscita didattica
        aule.put(USCITA_DIDATTICA, new RoomData(progressive, USCITA_DIDATTICA, "Uscita Didattica", 30, 0, ERoomArea.AREA_F, false, false));
        progressive++;

        // aule.put("?", new RoomData(progressive, "?", "Visita didattica", 0, 0, ERoomArea.AREA_F, false, false));
        progressive++;

        progressive = 0;
        count = 0;
        for (String riga : classiTxt) {
            if (count++ == 0) continue;
            final String[] split = riga.split("\t");
            String nome = split[2];
            int numStu = Integer.parseInt(split[3]);
            int classe = Integer.parseInt(split[0]);
            String sezione = split[1];
            classi.put(nome, new ClassData(progressive, nome, numStu, classe, sezione));
            progressive++;
        }


    }

    public static RoomData USCITA_DIDATTICA() {
        return getRoom(USCITA_DIDATTICA);
    }

    public static RoomData getRoom(BitOrarioOraLezione l) {
        return getRoom(l.getNomeAula());
    }

    public static RoomData getRoom(String aula) {
        if (aula == null) return null;
        final RoomData roomData = aule.get(aula);
        if (roomData == null) {
            throw new IllegalArgumentException("Aula " + aula + " inesistente");
        }

        return roomData;
    }

    public static ClassData getClass(String classe) {
        final ClassData classData = classi.get(classe);
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
        /*for (RoomData r : ClassesAndRoomContainer.getAllRooms()) {
            System.out.println("public static final RoomData _" + r.name + " = ClassesAndRoomContainer.getRoom(\"" + r.name + "\");");
        }
        for (ClassData r : ClassesAndRoomContainer.getAllClasses()) {
            System.out.println("public static final ClassData _" + r.name + " = ClassesAndRoomContainer.getClass(\"" + r.name + "\");");
        }*/
        System.out.println(ClassesAndRoomContainer.getRoom("A4_INF").flagSpecial());
    }
}
