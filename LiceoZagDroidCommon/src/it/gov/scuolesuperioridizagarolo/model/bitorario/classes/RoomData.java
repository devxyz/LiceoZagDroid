package it.gov.scuolesuperioridizagarolo.model.bitorario.classes;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EEntry;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.util.*;

public enum RoomData {

    NON_ASSEGNATO("(NON ASSEGNATO)", "(NON ASSEGNATO)", 0, 0, ERoomArea.AREA_A, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    AULA_SCONOSCIUTA("AULA SCONOSCIUTA", "AULA SCONOSCIUTA", 0, 0, ERoomArea.AREA_A, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    USCITA_DIDATTICA("-", "-", 0, 0, ERoomArea.AREA_F, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),

    A1("A1", "Aula Didattica", 24, 0, ERoomArea.AREA_A, EEntry.ENTRY_1, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    A2("A2", "#(Temporanea Segreteria)", 18, 0, ERoomArea.AREA_A, EEntry.ENTRY_1, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    A3("A3", "Aula Fisica", 24, 0, ERoomArea.AREA_A, EEntry.ENTRY_1, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),

    A4("A4", "Aula Informatica", 0, 0, ERoomArea.AREA_A, EEntry.ENTRY_1, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    A5("A5", "Aula Didattica", 15, 0, ERoomArea.AREA_A, EEntry.ENTRY_1, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    A6("A6", "Aula Disegno", 28, 0, ERoomArea.AREA_A, EEntry.ENTRY_1, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    A7("A7", "#(Aula COVID)", 12, 0, ERoomArea.AREA_A, EEntry.ENTRY_1, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//aula COVID

    A8("A8", "Aula Didattica", 17, 0, ERoomArea.AREA_A, EEntry.ENTRY_1, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),

    B3("B3", "Aula Didattica", 24, 0, ERoomArea.AREA_B, EEntry.ENTRY_3, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    B4("B4", "Aula Didattica", 25, 0, ERoomArea.AREA_B, EEntry.ENTRY_3, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    B5("B5", "Aula Didattica", 24, 0, ERoomArea.AREA_B, EEntry.ENTRY_3, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    B6("B6", "Aula Didattica", 25, 0, ERoomArea.AREA_B, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    B7("B7", "Aula Didattica", 28, 0, ERoomArea.AREA_B, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    B1("B1", "Aula Didattica", 24, 0, ERoomArea.AREA_B, EEntry.ENTRY_3, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    B2("B2", "Aula Didattica", 24, 0, ERoomArea.AREA_B, EEntry.ENTRY_3, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    C6("C6", "Aula Didattica", 18, 0, ERoomArea.AREA_C, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    C7("C7", "#(Aula Attività Alternativa IRC)", 24, 0, ERoomArea.AREA_C, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//aggiunta extra
    C1("C1", "Aula Didattica", 21, 0, ERoomArea.AREA_C, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//ERA 27
    C2("C2", "Aula Didattica", 26 + 1, 0, ERoomArea.AREA_C, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//DA AGGIUNGERE
    C3("C3", "Aula Didattica", 26, 0, ERoomArea.AREA_C, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    C4("C4", "Aula Didattica", 26, 0, ERoomArea.AREA_C, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    C5("C5", "Aula Didattica", 22, 0, ERoomArea.AREA_C, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    D1("D1", "Aula Didattica", 21, 0, ERoomArea.AREA_D, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),//ERA 26
    D2("D2", "Aula Didattica", 26, 0, ERoomArea.AREA_D, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    D3("D3", "Aula Didattica", 26, 0, ERoomArea.AREA_D, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    D4("D4", "Aula Didattica", 26, 0, ERoomArea.AREA_D, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    D5("D5", "Aula Didattica", 22, 0, ERoomArea.AREA_D, EEntry.ENTRY_2, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),

    E1("E1", "Aula Didattica", 23, 0, ERoomArea.AREA_E, EEntry.ENTRY_4, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    E2("E2", "Aula Didattica", 25, 0, ERoomArea.AREA_E, EEntry.ENTRY_4, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    E3("E3", "Aula Didattica", 25, 0, ERoomArea.AREA_E, EEntry.ENTRY_4, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),
    E4("E4", "Aula Didattica", 25, 0, ERoomArea.AREA_E, EEntry.ENTRY_4, RoomDataConstant.LIM_SI, RoomDataConstant.PRESA_CORRENTE_NO),

    E5("E5", "Aula Didattica", 25, 0, ERoomArea.AREA_E, EEntry.ENTRY_4, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    //F1B("F1x", "Palestra", 30, 0, ERoomArea.AREA_F, EEntry.ENTRY_5, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    F2("F2", "Aula Scienze", 23, 0, ERoomArea.AREA_F, EEntry.ENTRY_5, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    F1("F1", "Palestra", 0, 0, ERoomArea.AREA_F, EEntry.ENTRY_5, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    PAL1("F1#1", "Aula Palestra (Aula P1)", 28, 0, ERoomArea.AREA_F, EEntry.ENTRY_5, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    PAL2("F1#2", "Aula Palestra (Aula P2)", 28, 0, ERoomArea.AREA_F, EEntry.ENTRY_5, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),


    DDI_aula_A1("DDI (A1)", "Didattica a distanza (postazione docente in aula A1)", RoomData.A1),
    DDI_aula_A2("DDI (A2)", "Didattica a distanza (postazione docente in aula A2)", RoomData.A2),
    DDI_aula_A3("DDI (A3)", "Didattica a distanza (postazione docente in aula A3)", RoomData.A3),
    DDI_aula_A4("DDI (A4)", "Didattica a distanza (postazione docente in aula A4)", RoomData.A4),
    DDI_aula_A5("DDI (A5)", "Didattica a distanza (postazione docente in aula A5)", RoomData.A5),
    DDI_aula_A6("DDI (A6)", "Didattica a distanza (postazione docente in aula A6)", RoomData.A6),
    DDI_aula_A7("DDI (A7)", "Didattica a distanza (postazione docente in aula A7)", RoomData.A7),
    DDI_aula_A8("DDI (A8)", "Didattica a distanza (postazione docente in aula A8)", RoomData.A8),
    DDI_aula_B3("DDI (B3)", "Didattica a distanza (postazione docente in aula B3)", RoomData.B3),
    DDI_aula_B4("DDI (B4)", "Didattica a distanza (postazione docente in aula B4)", RoomData.B4),
    DDI_aula_B5("DDI (B5)", "Didattica a distanza (postazione docente in aula B5)", RoomData.B5),
    DDI_aula_B6("DDI (B6)", "Didattica a distanza (postazione docente in aula B6)", RoomData.B6),
    DDI_aula_B7("DDI (B7)", "Didattica a distanza (postazione docente in aula B7)", RoomData.B7),
    DDI_aula_B1("DDI (B1)", "Didattica a distanza (postazione docente in aula B1)", RoomData.B1),
    DDI_aula_B2("DDI (B2)", "Didattica a distanza (postazione docente in aula B2)", RoomData.B2),
    DDI_aula_C6("DDI (C6)", "Didattica a distanza (postazione docente in aula C6)", RoomData.C6),
    DDI_aula_C7("DDI (C7)", "Didattica a distanza (postazione docente in aula C7)", RoomData.C7),
    DDI_aula_C1("DDI (C1)", "Didattica a distanza (postazione docente in aula C1)", RoomData.C1),
    DDI_aula_C2("DDI (C2)", "Didattica a distanza (postazione docente in aula C2)", RoomData.C2),
    DDI_aula_C3("DDI (C3)", "Didattica a distanza (postazione docente in aula C3)", RoomData.C3),
    DDI_aula_C4("DDI (C4)", "Didattica a distanza (postazione docente in aula C4)", RoomData.C4),
    DDI_aula_C5("DDI (C5)", "Didattica a distanza (postazione docente in aula C5)", RoomData.C5),
    DDI_aula_D1("DDI (D1)", "Didattica a distanza (postazione docente in aula D1)", RoomData.D1),
    DDI_aula_D2("DDI (D2)", "Didattica a distanza (postazione docente in aula D2)", RoomData.D2),
    DDI_aula_D3("DDI (D3)", "Didattica a distanza (postazione docente in aula D3)", RoomData.D3),
    DDI_aula_D4("DDI (D4)", "Didattica a distanza (postazione docente in aula D4)", RoomData.D4),
    DDI_aula_D5("DDI (D5)", "Didattica a distanza (postazione docente in aula D5)", RoomData.D5),
    DDI_aula_E1("DDI (E1)", "Didattica a distanza (postazione docente in aula E1)", RoomData.E1),
    DDI_aula_E2("DDI (E2)", "Didattica a distanza (postazione docente in aula E2)", RoomData.E2),
    DDI_aula_E3("DDI (E3)", "Didattica a distanza (postazione docente in aula E3)", RoomData.E3),
    DDI_aula_E4("DDI (E4)", "Didattica a distanza (postazione docente in aula E4)", RoomData.E4),
    DDI_aula_E5("DDI (E5)", "Didattica a distanza (postazione docente in aula E5)", RoomData.E5),
    DDI_aula_F2("DDI (F2)", "Didattica a distanza (postazione docente in aula F2)", RoomData.F2),
    DDI_aula_F1("DDI (F1)", "Didattica a distanza (postazione docente in aula F1)", RoomData.F1),
    DDI_aula_PAL1("DDI (PAL1)", "Didattica a distanza (postazione docente in aula PAL1)", RoomData.PAL1),
    DDI_aula_PAL2("DDI (PAL2)", "Didattica a distanza (postazione docente in aula PAL2)", RoomData.PAL2),

    DDI_da_casa("DDI (da casa)", "Didattica a distanza", 30, 0, ERoomArea.AREA_DDI, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    DDI_corridoio_AREA_B("DDI (area B)", "Didattica a distanza (corridoio area B)", 30, 0, ERoomArea.AREA_DDI, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    DDI_corridoio_AREA_C("DDI (area C)", "Didattica a distanza (corridoio area C)", 30, 0, ERoomArea.AREA_DDI, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    DDI_corridoio_AREA_D("DDI (area D)", "Didattica a distanza (corridoio area D)", 30, 0, ERoomArea.AREA_DDI, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    DDI_corridoio_AREA_AC("DDI (area C-E)", "Didattica a distanza (corridoio area C-E)", 30, 0, ERoomArea.AREA_DDI, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO),
    ;

    public boolean isDDI() {
        return location == ERoomArea.AREA_DDI;
    }

    /**
     * room collegata nel caso di aule fittizie DDI
     */
    public final RoomData ddi_linkedRoom;

    public final String roomName;
    public final String usage;
    public final int maxStudents;
    public final int idRoom;
    public final ERoomArea location;

    public String getRoomName() {
        return roomName;
    }

    public String getUsage() {
        return usage;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public ERoomArea getLocation() {
        return location;
    }

    public boolean isFlagLIM() {
        return flagLIM;
    }

    public boolean isFlagPRESACORRENTE() {
        return flagPRESACORRENTE;
    }

    /**
     * ritorna la stanza DDI associata all'aula specificata (se esiste)
     *
     * @param r
     * @return
     */
    public static RoomData searchDDILinkedRoom(RoomData r) {
        if (r.isDDI())
            throw new IllegalArgumentException("Aula già DDI");
        for (RoomData v : values()) {
            if (v.ddi_linkedRoom == r)
                return v;
        }
        return null;
    }

    public String[] getEtichetteFileOrario() {
        return etichetteFileOrario;
    }

    private EEntry entry;
    public final boolean flagLIM;
    public final boolean flagPRESACORRENTE;

    public EEntry getEntry() {
        return entry;
    }

    public final String[] etichetteFileOrario;//etichette riconosciute dal file


    public String toStringDimensioni() {
        return roomName + "[" + maxStudents + "]";
    }

    //aula normale
    RoomData(String roomname, String usage, int maxStudents, int idRoom,
             ERoomArea location,
             EEntry entry, boolean flagLIM,
             boolean flagPRESACORRENTE,
             String... etichetteFileOrario) {
        this.roomName = roomname;
        this.usage = usage;
        this.maxStudents = maxStudents;
        this.idRoom = idRoom;
        this.location = location;
        this.entry = entry;
        this.flagLIM = flagLIM;
        this.flagPRESACORRENTE = flagPRESACORRENTE;
        ArrayList<String> etichette = new ArrayList<>(Arrays.asList(etichetteFileOrario));
        etichette.add(roomname);
        ddi_linkedRoom = null;
        this.etichetteFileOrario = etichette.toArray(new String[etichette.size()]);
    }

    //ddi
    RoomData(String roomname, String usage,
             RoomData ddi_linkedRoom,
             String... etichetteFileOrario) {
        this.roomName = roomname;
        this.usage = usage;
        this.maxStudents = ddi_linkedRoom.maxStudents;
        this.idRoom = ddi_linkedRoom.idRoom;
        this.location = ERoomArea.AREA_DDI;
        this.entry = ddi_linkedRoom.entry;
        this.flagLIM = ddi_linkedRoom.flagLIM;
        this.flagPRESACORRENTE = ddi_linkedRoom.flagPRESACORRENTE;
        this.ddi_linkedRoom = ddi_linkedRoom;
        ArrayList<String> etichette = new ArrayList<>(Arrays.asList(etichetteFileOrario));
        etichette.add(roomname);
        this.etichetteFileOrario = etichette.toArray(new String[etichette.size()]);
    }

    public RoomData getDDI_linkedRoom() {
        if (!isDDI())
            throw new IllegalArgumentException("Aula " + this + " non e' DDI");
        return ddi_linkedRoom;
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

        System.out.println(RoomData.C2.isDDI());


        for (RoomData r : values()) {
            if (r.isAulaFittizia()) continue;
            if (r.isDDI()) continue;
            System.out.printf("DDI_aula_%s(\"DDI (%s)\", \"Didattica a distanza (aula %s)\", 30, 0, ERoomArea.AREA_DDI, EEntry.ENTRY_NONE, RoomDataConstant.LIM_NO, RoomDataConstant.PRESA_CORRENTE_NO,RoomData.%s),\n",
                    r.name(), r.name(), r.name(), r.name());
        }


    }

    public boolean isAulaFittizia() {
        return
                this == USCITA_DIDATTICA
                        || this == NON_ASSEGNATO
                        || this.isDDI()
                        || this == AULA_SCONOSCIUTA;
    }


    public String simpleName() {
        return roomName;//roomName.split("[_ ]+")[0];
    }

    @Override
    public String toString() {
        return simpleName();
    }

    //true se aula speciale
    public boolean isAulaLaboratorioPalestra() {
        return this == F1 ||
                this == F2 ||
                this == A3 ||
                this == A4 ||
                this == A6;
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
