package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RoomData {

    NON_ASSEGNATO("(NON ASSEGNATO)", "(NON ASSEGNATO)", 0, 0, ERoomArea.AREA_A, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    AULA_SCONOSCIUTA("AULA SCONOSCIUTA", "AULA SCONOSCIUTA", 0, 0, ERoomArea.AREA_A, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    USCITA_DIDATTICA("USCITA DIDATTICA", "Uscita Didattica", 0, 0, ERoomArea.AREA_F, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),

    A1("A1", "Aula Didattica", 30, 11, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A2("A2", "Aula Didattica", 22, 13, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A3_FIS("A3", "Laboratorio di Fisica", 30, 15, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO,"A3 FISICA"),
    A4_INF("A4", "Laboratorio di Informatica", 30, 17, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO,"A4 INFORMATICA"),
    A5sharp("A5#", "Aula Didattica", 24, 22, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A5_DIS("A5 DISEGNO", "Aula di Disegno", 30, 22, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO, "A5 DISEGNO"),
    A6("A6", "Aula Didattica", 28, 20, ERoomArea.AREA_A, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    A7("A7", "Aula Didattica", 18, 21, ERoomArea.AREA_A, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),

    B8("B8", "Aula Didattica", 30, 10, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    B9("B9", "Aula Didattica", 30, 9, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    B10("B10", "Aula Didattica", 30, 8, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO AL MOMENTO non utilizzabile aveva 28 posti
    B11("B11", "Aula Didattica", 30, 31, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    B12("B12", "Aula Didattica", 30, 32, ERoomArea.AREA_B, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    B13("B13", "Aula Didattica", 30, 33, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    B13sharp("B13#", "Aula Didattica", 28, 33, ERoomArea.AREA_B, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//todo AL MOMENTO NON UTILIZZABILE

    C14("C14", "Aula Didattica", 20, 47, ERoomArea.AREA_C, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//ANNO SCORSO 25
    C15("C15", "Aula Didattica", 0, 46, ERoomArea.AREA_C, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//AULA CORRIDOIO

    C16("C16", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 45, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C17("C17", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 51, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C18("C18", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 52, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C19("C19", "Aula Didattica", 28, 53, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C20("C20", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 54, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C21("C21", "Aula Didattica", 18, 55, ERoomArea.AREA_C, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//ANNO SCORSO 22

    D22("D22", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 61, ERoomArea.AREA_D, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    D23("D23", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 62, ERoomArea.AREA_D, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    D24("D24", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 63, ERoomArea.AREA_D, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    D25("D25", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 64, ERoomArea.AREA_D, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    D26("D26", "Aula Didattica", 18, 65, ERoomArea.AREA_D, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//???ANNO SCORSO 22

    E27("E27", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 44, ERoomArea.AREA_E, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE - anno scorso 26 posti
    E28("E28", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 39, ERoomArea.AREA_E, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE
    E29("E29", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 40, ERoomArea.AREA_E, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE
    E29sharp("E29#", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 40, ERoomArea.AREA_E, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE
    E30("E30", "Aula Didattica", RoomDataUtil.NUMERO_MAX_AULE, 41, ERoomArea.AREA_E, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE
    E30sharp("E30#", "Aula Didattica", 0, 41, ERoomArea.AREA_E, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE

    F31_PALESTRA("F31", "Palestra", 30, 0, ERoomArea.AREA_F, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO,"F31 PALESTRA", "palestra", "palestra extra"),
    F32_CHIMICA("F32", "Laboratorio di Scienze", 30, 0, ERoomArea.AREA_F, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_SI, "F32 CHIMICA E SCIENZE","F32 CHIMICA"),

    //aule fittizie
    AULE_INGLESE("aule inglese", "aule inglese", 0, 0, ERoomArea.AREA_F, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_SI, "aule inglese"),
    AULE_RELIGIONE("N.A.", "Aula Magna scuole medie", 0, 0, ERoomArea.AREA_F, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_SI, "aula religione","blocco_religione","blocco_filosofia","blocco_inglese","blocco_italiano"),

    ;

    public final String roomName;
    public final String usage;
    public final int maxStudents;
    public final int idRoom;
    public final ERoomArea location;
    public final boolean flagLIM;
    public final boolean flagPRESACORRENTE;
    public final String[] etichetteFileOrario;//etichette riconosciute dal file

    RoomData(String roomname, String usage, int maxStudents, int idRoom, ERoomArea location, boolean flagLIM, boolean flagPRESACORRENTE, String... etichetteFileOrario) {
        this.roomName = roomname;
        this.usage = usage;
        this.maxStudents = maxStudents;
        this.idRoom = idRoom;
        this.location = location;
        this.flagLIM = flagLIM;
        this.flagPRESACORRENTE = flagPRESACORRENTE;

        ArrayList<String> etichette = new ArrayList<>(Arrays.asList(etichetteFileOrario));
        etichette.add(roomname);


        this.etichetteFileOrario = etichette.toArray(new String[etichette.size()]);

    }

    public static RoomData search(String s) {
        if (s == null) return AULA_SCONOSCIUTA;
        for (RoomData xx : values()) {
            for (String e : xx.etichetteFileOrario) {
                if (e.equalsIgnoreCase(s))
                    return xx;
            }

        }
        throw new IllegalArgumentException("Aula sconosciuta: " + s);
        //return AULA_SCONOSCIUTA;
    }

    public static void main(String[] args) {
        for (RoomData roomData : values()) {
            System.out.println(roomData.roomName + "\t" + roomData.location.description + "\t-");
        }
    }

    public boolean flagAulaFittizia() {
        return
                this == USCITA_DIDATTICA
                        || this == NON_ASSEGNATO
                        || this == AULA_SCONOSCIUTA;
    }


    public String simpleName() {
        return roomName.split("[_ ]+")[0];
    }

    @Override
    public String toString() {
        return simpleName();
    }

    //true se aula speciale
    public boolean flagAulaLaboratorioPalestra() {
        return this == F31_PALESTRA ||
                this == F32_CHIMICA ||
                this == A3_FIS ||
                this == A4_INF ||
                this == A5_DIS;
    }


    public static interface RoomDataFilter {
        boolean accept(RoomData c);
    }

    public static List<RoomData> filter(RoomDataFilter f) {
        List<RoomData> ris = new ArrayList<>();
        for (RoomData x : values()) {
            if (f.accept(x)) ris.add(x);
        }
        return ris;
    }


}
