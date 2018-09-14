package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

public enum RoomData {
    NON_ASSEGNATO("(NON ASSEGNATO)", "(NON ASSEGNATO)", 0, 0, ERoomArea.AREA_A, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    AULA_SCONOSCIUTA("AULA SCONOSCIUTA", "AULA SCONOSCIUTA", 0, 0, ERoomArea.AREA_A, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    USCITA_DIDATTICA("###", "Uscita Didattica", 0, 0, ERoomArea.AREA_F, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    A1("A1", "Aula Didattica", 30, 11, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A2("A2", "Aula Didattica", 28, 13, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A3_FIS("A3_FIS", "Laboratorio di Fisica", 30, 15, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A4_INF("A4_INF", "Laboratorio di Informatica", 30, 17, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A5sharp("A5#", "Aula Didattica", 24, 22, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A5_DIS("A5_DIS", "Aula di Disegno", 30, 22, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A6("A6", "Aula Didattica", 29, 20, ERoomArea.AREA_A, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    A7("A7", "Aula Didattica", 18, 21, ERoomArea.AREA_A, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),

    B8("B8", "Aula Didattica", 30, 10, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    B9("B9", "Aula Didattica", 30, 9, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    B10("B10", "Aula Didattica", 30, 8, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO AL MOMENTO non utilizzabile aveva 28 posti
    B11("B11", "Aula Didattica", 30, 31, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    B12("B12", "Aula Didattica", 30, 32, ERoomArea.AREA_B, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    B13("B13", "Aula Didattica", 30, 33, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    B13sharp("B13#", "Aula Didattica", 30, 33, ERoomArea.AREA_B, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//todo AL MOMENTO NON UTILIZZABILE

    C14("C14", "Aula Didattica", 22, 47, ERoomArea.AREA_C, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//ANNO SCORSO 25
    C15("C15", "Aula Didattica", 0, 46, ERoomArea.AREA_C, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//AULA CORRIDOIO

    C16("C16", "Aula Didattica", 30, 45, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C17("C17", "Aula Didattica", 30, 51, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C18("C18", "Aula Didattica", 30, 52, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C19("C19", "Aula Didattica", 30, 53, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C20("C20", "Aula Didattica", 30, 54, ERoomArea.AREA_C, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    C21("C21", "Aula Didattica", 21, 55, ERoomArea.AREA_C, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//ANNO SCORSO 22

    D22("D22", "Aula Didattica", 30, 61, ERoomArea.AREA_D, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    D23("D23", "Aula Didattica", 30, 62, ERoomArea.AREA_D, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    D24("D24", "Aula Didattica", 30, 63, ERoomArea.AREA_D, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    D25("D25", "Aula Didattica", 30, 64, ERoomArea.AREA_D, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    D26("D26", "Aula Didattica", 20, 65, ERoomArea.AREA_D, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//???ANNO SCORSO 22

    E27("E27", "Aula Didattica", 30, 44, ERoomArea.AREA_E, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE - anno scorso 26 posti
    E28("E28", "Aula Didattica", 30, 39, ERoomArea.AREA_E, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE
    E29("E29", "Aula Didattica", 30, 40, ERoomArea.AREA_E, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE
    E30("E30", "Aula Didattica", 30, 41, ERoomArea.AREA_E, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),//TODO DA INSERIRE

    F31_PALESTRA("F31_PALESTRA", "Palestra", 30, 0, ERoomArea.AREA_F, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    F32_SCI("F32_SCI", "Laboratorio di Scienze", 30, 0, ERoomArea.AREA_F, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_SI);

    public final String roomname;
    public final String usage;
    public final int maxStudents;
    public final int idRoom;
    public final ERoomArea location;
    public final boolean flagLIM;
    public final boolean flagPRESACORRENTE;

    RoomData(String roomname, String usage, int maxStudents, int idRoom, ERoomArea location, boolean flagLIM, boolean flagPRESACORRENTE) {
        this.roomname = roomname;
        this.usage = usage;
        this.maxStudents = maxStudents;
        this.idRoom = idRoom;
        this.location = location;
        this.flagLIM = flagLIM;
        this.flagPRESACORRENTE = flagPRESACORRENTE;
    }

    public boolean flagAulaFittizia() {
        return
                this == USCITA_DIDATTICA
                        || this == NON_ASSEGNATO
                        || this == AULA_SCONOSCIUTA;
    }

    public String simpleName() {
        return roomname.split("_")[0];
    }

    @Override
    public String toString() {
        return simpleName();
    }


    public static RoomData search(String s) {
        if (s == null) return AULA_SCONOSCIUTA;
        for (RoomData xx : values()) {
            if (xx.roomname.equalsIgnoreCase(s)) return xx;
        }
        return AULA_SCONOSCIUTA;
    }

    //true se aula speciale
    public boolean flagAulaLaboratorioPalestra() {
        return this == F31_PALESTRA ||
                this == F32_SCI ||
                this == A3_FIS ||
                this == A4_INF ||
                this == A5_DIS;
    }


}
