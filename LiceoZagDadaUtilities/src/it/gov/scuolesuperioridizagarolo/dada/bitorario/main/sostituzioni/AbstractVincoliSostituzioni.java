package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_ClasseBloccataInArea;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_ClasseNonInAula_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.util.List;

/**
 * Created by stefano on 27/04/2018.
 */
public abstract class AbstractVincoliSostituzioni {
    public static final RoomData _A1 = ClassesAndRoomContainer.parseRoom("A1");
    public static final RoomData _A2 = ClassesAndRoomContainer.parseRoom("A2");
    public static final RoomData _A3_FIS = ClassesAndRoomContainer.parseRoom("A3_FIS");
    public static final RoomData _A4_INF = ClassesAndRoomContainer.parseRoom("A4_INF");
    public static final RoomData _A5_DIS = ClassesAndRoomContainer.parseRoom("A5_DIS");
    public static final RoomData _A6 = ClassesAndRoomContainer.parseRoom("A6");
    public static final RoomData _A7 = ClassesAndRoomContainer.parseRoom("A7");
    public static final RoomData _B10 = ClassesAndRoomContainer.parseRoom("B10");
    public static final RoomData _B11 = ClassesAndRoomContainer.parseRoom("B11");
    public static final RoomData _B12 = ClassesAndRoomContainer.parseRoom("B12");
    public static final RoomData _B13 = ClassesAndRoomContainer.parseRoom("B13");
    public static final RoomData _B8 = ClassesAndRoomContainer.parseRoom("B8");
    public static final RoomData _B9 = ClassesAndRoomContainer.parseRoom("B9");
    public static final RoomData _C14 = ClassesAndRoomContainer.parseRoom("C14");
    public static final RoomData _C15 = ClassesAndRoomContainer.parseRoom("C15");
    public static final RoomData _C16 = ClassesAndRoomContainer.parseRoom("C16");
    public static final RoomData _C17 = ClassesAndRoomContainer.parseRoom("C17");
    public static final RoomData _C18 = ClassesAndRoomContainer.parseRoom("C18");
    public static final RoomData _C19 = ClassesAndRoomContainer.parseRoom("C19");
    public static final RoomData _C20 = ClassesAndRoomContainer.parseRoom("C20");
    public static final RoomData _C21 = ClassesAndRoomContainer.parseRoom("C21");
    public static final RoomData _D22 = ClassesAndRoomContainer.parseRoom("D22");
    public static final RoomData _D23 = ClassesAndRoomContainer.parseRoom("D23");
    public static final RoomData _D24 = ClassesAndRoomContainer.parseRoom("D24");
    public static final RoomData _D25 = ClassesAndRoomContainer.parseRoom("D25");
    public static final RoomData _D26 = ClassesAndRoomContainer.parseRoom("D26");
    public static final RoomData _E27 = ClassesAndRoomContainer.parseRoom("E27");
    public static final RoomData _E28 = ClassesAndRoomContainer.parseRoom("E28");
    public static final RoomData _E29 = ClassesAndRoomContainer.parseRoom("E29");
    public static final RoomData _E30 = ClassesAndRoomContainer.parseRoom("E30");
    public static final RoomData _F31_PALESTRA = ClassesAndRoomContainer.parseRoom("F31_PALESTRA");
    public static final RoomData _USCITA_DIDATTICA = RoomData.USCITA_DIDATTICA;
    public static final RoomData _F32_SCI = ClassesAndRoomContainer.parseRoom("F32_SCI");
    public static final ClassData _1A = ClassesAndRoomContainer.parseClass("1A");
    public static final ClassData _1B = ClassesAndRoomContainer.parseClass("1B");
    public static final ClassData _1C = ClassesAndRoomContainer.parseClass("1C");
    public static final ClassData _1D = ClassesAndRoomContainer.parseClass("1D");
    public static final ClassData _1E = ClassesAndRoomContainer.parseClass("1E");
    public static final ClassData _1F = ClassesAndRoomContainer.parseClass("1F");
    public static final ClassData _1H = ClassesAndRoomContainer.parseClass("1H");
    public static final ClassData _2A = ClassesAndRoomContainer.parseClass("2A");
    public static final ClassData _2B = ClassesAndRoomContainer.parseClass("2B");
    public static final ClassData _2C = ClassesAndRoomContainer.parseClass("2C");
    public static final ClassData _2D = ClassesAndRoomContainer.parseClass("2D");
    public static final ClassData _2E = ClassesAndRoomContainer.parseClass("2E");
    public static final ClassData _2F = ClassesAndRoomContainer.parseClass("2F");
    public static final ClassData _2G = ClassesAndRoomContainer.parseClass("2G");
    public static final ClassData _3A = ClassesAndRoomContainer.parseClass("3A");
    public static final ClassData _3B = ClassesAndRoomContainer.parseClass("3B");
    public static final ClassData _3C = ClassesAndRoomContainer.parseClass("3C");
    public static final ClassData _3D = ClassesAndRoomContainer.parseClass("3D");
    public static final ClassData _3E = ClassesAndRoomContainer.parseClass("3E");
    public static final ClassData _4A = ClassesAndRoomContainer.parseClass("4A");
    public static final ClassData _4B = ClassesAndRoomContainer.parseClass("4B");
    public static final ClassData _4C = ClassesAndRoomContainer.parseClass("4C");
    public static final ClassData _4D = ClassesAndRoomContainer.parseClass("4D");
    public static final ClassData _4E = ClassesAndRoomContainer.parseClass("4E");
    public static final ClassData _4F = null;//ClassesAndRoomContainer.parseClass("4F");
    public static final ClassData _5A = ClassesAndRoomContainer.parseClass("5A");
    public static final ClassData _5B = ClassesAndRoomContainer.parseClass("5B");
    public static final ClassData _5C = ClassesAndRoomContainer.parseClass("5C");
    public static final ClassData _5D = ClassesAndRoomContainer.parseClass("5D");
    protected String dal;
    protected String al;

    public AbstractVincoliSostituzioni() {

    }

    public static String checkAuleMultiple(final BitOrarioGrigliaOrario orarioTotale) {
        int anomalie = 0;
        StringBuilder sb = new StringBuilder();
        for (EGiorno g : EGiorno.values()) {
            if (!g.flagGiornoDiLezione()) continue;
            for (EOra o : EOra.values()) {
                if (!o.flagOraDiLezione()) continue;
                for (RoomData aula : orarioTotale.getAule()) {
                    if (aula.equals(_F31_PALESTRA))
                        continue;
                    if ((aula) == RoomData.USCITA_DIDATTICA)
                        continue;
                    final List<BitOrarioOraLezione> lezioneInAula = orarioTotale.getLezioneInAula(o, g, aula);
                    if (lezioneInAula.size() > 1) {
                        sb.append("\n------------\n AULA " + aula + " " + lezioneInAula.size() + " lezioni il " + g + " " + o + " ora!!!\n");
                        for (BitOrarioOraLezione l : lezioneInAula) {
                            sb.append("   >> ").append(l);
                        }

                        anomalie++;
                    }
                }
            }
        }

        return (anomalie > 0) ? ("Anomalie: " + anomalie + "\n" + sb) : "Nessuna anomalia con uso multiplo aule";
    }

    protected static void classeConStampelle(LessonConstraintContainer l, ClassData classe, EGiorno... giorni) {
        l.add(new LessonConstraint_ClasseBloccataInArea(true, classe, new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D, ERoomArea.AREA_F}, giorni));
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F32_SCI, giorni));
    }

    protected static void classeConStampelle(LessonConstraintContainer l, ClassData classe) {
        l.add(new LessonConstraint_ClasseBloccataInArea(true, classe, new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D, ERoomArea.AREA_F}, EGiorno.values()));
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F32_SCI, EGiorno.values()));
    }

    protected static void classeConStampellePianoTerra(LessonConstraintContainer l, ClassData classe) {
        l.add(new LessonConstraint_ClasseBloccataInArea(true, classe, new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, EGiorno.values()));
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F32_SCI, EGiorno.values()));
    }


    /**
     * eseguito al termine dell'elaborazione, prima della verifica finale vincoli e stampa report
     *
     * @param orarioTotale
     * @param l
     */
    protected void postOrarioBeforeFinalCheck(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {

    }
    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {

    }

    /*
    modificare in casi specifici
     */
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{FilterAule.LABORATORI_MAI, FilterAule.LABORATORI_SOLO_COMPATIBILI, FilterAule.LABORATORI_SEMPRE};
    }

    public String getDal() {
        return dal;
    }

    public String getAl() {
        return al;
    }

    public abstract AbstractVincoliSostituzioni invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l);

}
