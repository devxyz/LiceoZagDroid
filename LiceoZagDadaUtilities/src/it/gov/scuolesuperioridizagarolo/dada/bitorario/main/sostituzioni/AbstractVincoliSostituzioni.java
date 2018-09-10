package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni;

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

    public static final RoomData _A1 = ClassesAndRoomContainer.getRoom("A1");
    public static final RoomData _A2 = ClassesAndRoomContainer.getRoom("A2");
    public static final RoomData _A3_FIS = ClassesAndRoomContainer.getRoom("A3_FIS");
    public static final RoomData _A4_INF = ClassesAndRoomContainer.getRoom("A4_INF");
    public static final RoomData _A5_DIS = ClassesAndRoomContainer.getRoom("A5_DIS");
    public static final RoomData _A6 = ClassesAndRoomContainer.getRoom("A6");
    public static final RoomData _A7 = ClassesAndRoomContainer.getRoom("A7");
    public static final RoomData _B10 = ClassesAndRoomContainer.getRoom("B10");
    public static final RoomData _B11 = ClassesAndRoomContainer.getRoom("B11");
    public static final RoomData _B12 = ClassesAndRoomContainer.getRoom("B12");
    public static final RoomData _B13 = ClassesAndRoomContainer.getRoom("B13");
    public static final RoomData _B8 = ClassesAndRoomContainer.getRoom("B8");
    public static final RoomData _B9 = ClassesAndRoomContainer.getRoom("B9");
    public static final RoomData _C14 = ClassesAndRoomContainer.getRoom("C14");
    public static final RoomData _C15 = ClassesAndRoomContainer.getRoom("C15");
    public static final RoomData _C16 = ClassesAndRoomContainer.getRoom("C16");
    public static final RoomData _C17 = ClassesAndRoomContainer.getRoom("C17");
    public static final RoomData _C18 = ClassesAndRoomContainer.getRoom("C18");
    public static final RoomData _C19 = ClassesAndRoomContainer.getRoom("C19");
    public static final RoomData _C20 = ClassesAndRoomContainer.getRoom("C20");
    public static final RoomData _C21 = ClassesAndRoomContainer.getRoom("C21");
    public static final RoomData _D22 = ClassesAndRoomContainer.getRoom("D22");
    public static final RoomData _D23 = ClassesAndRoomContainer.getRoom("D23");
    public static final RoomData _D24 = ClassesAndRoomContainer.getRoom("D24");
    public static final RoomData _D25 = ClassesAndRoomContainer.getRoom("D25");
    public static final RoomData _D26 = ClassesAndRoomContainer.getRoom("D26");
    public static final RoomData _E27 = ClassesAndRoomContainer.getRoom("E27");
    public static final RoomData _E28 = ClassesAndRoomContainer.getRoom("E28");
    public static final RoomData _E29 = ClassesAndRoomContainer.getRoom("E29");
    public static final RoomData _E30 = ClassesAndRoomContainer.getRoom("E30");
    public static final RoomData _F31_PALESTRA = ClassesAndRoomContainer.getRoom("F31_PALESTRA");
    public static final RoomData _USCITA_DIDATTICA = ClassesAndRoomContainer.USCITA_DIDATTICA();
    public static final RoomData _F32_SCI = ClassesAndRoomContainer.getRoom("F32_SCI");
    public static final ClassData _1A = ClassesAndRoomContainer.getClass("1A");
    public static final ClassData _1B = ClassesAndRoomContainer.getClass("1B");
    public static final ClassData _1C = ClassesAndRoomContainer.getClass("1C");
    public static final ClassData _1D = ClassesAndRoomContainer.getClass("1D");
    public static final ClassData _1E = ClassesAndRoomContainer.getClass("1E");
    public static final ClassData _1F = ClassesAndRoomContainer.getClass("1F");

    public static final ClassData _1H = ClassesAndRoomContainer.getClass("1H");
    public static final ClassData _2A = ClassesAndRoomContainer.getClass("2A");
    public static final ClassData _2B = ClassesAndRoomContainer.getClass("2B");
    public static final ClassData _2C = ClassesAndRoomContainer.getClass("2C");
    public static final ClassData _2D = ClassesAndRoomContainer.getClass("2D");
    public static final ClassData _2E = ClassesAndRoomContainer.getClass("2E");
    public static final ClassData _2F = ClassesAndRoomContainer.getClass("2F");
    public static final ClassData _2G = ClassesAndRoomContainer.getClass("2G");
    public static final ClassData _3A = ClassesAndRoomContainer.getClass("3A");
    public static final ClassData _3B = ClassesAndRoomContainer.getClass("3B");
    public static final ClassData _3C = ClassesAndRoomContainer.getClass("3C");
    public static final ClassData _3D = ClassesAndRoomContainer.getClass("3D");
    public static final ClassData _3E = ClassesAndRoomContainer.getClass("3E");
    public static final ClassData _4A = ClassesAndRoomContainer.getClass("4A");
    public static final ClassData _4B = ClassesAndRoomContainer.getClass("4B");
    public static final ClassData _4C = ClassesAndRoomContainer.getClass("4C");
    public static final ClassData _4D = ClassesAndRoomContainer.getClass("4D");
    public static final ClassData _4E = ClassesAndRoomContainer.getClass("4E");
    public static final ClassData _4F = null;//ClassesAndRoomContainer.getClass("4F");
    public static final ClassData _5A = ClassesAndRoomContainer.getClass("5A");
    public static final ClassData _5B = ClassesAndRoomContainer.getClass("5B");
    public static final ClassData _5C = ClassesAndRoomContainer.getClass("5C");
    public static final ClassData _5D = ClassesAndRoomContainer.getClass("5D");
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
                for (String aula : orarioTotale.getAule()) {
                    final RoomData room = ClassesAndRoomContainer.getRoom(aula);
                    if (room.equals(_F31_PALESTRA))
                        continue;
                    if (room.isUscitaDidattica())
                        continue;
                    final List<BitOrarioOraLezione> lezioneInAula = orarioTotale.getLezioneInAula(o, g, aula);
                    if (lezioneInAula.size() > 1) {
                        sb.append(" AULA " + aula + " " + lezioneInAula.size() + " lezioni!!!\n");
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

    public String getDal() {
        return dal;
    }

    public String getAl() {
        return al;
    }

    public abstract AbstractVincoliSostituzioni invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l);

}
