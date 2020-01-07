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

import java.io.IOException;
import java.util.List;

/**
 * Created by stefano on 27/04/2018.
 */
public abstract class AbstractVincoliSostituzioni {
    public static final ClassData _1A = ClassData.CLASS_1A;
    public static final ClassData _1B = ClassData.CLASS_1B;
    public static final ClassData _1C = ClassData.CLASS_1C;
    public static final ClassData _1D = ClassData.CLASS_1D;
    public static final ClassData _1E = ClassData.CLASS_1E;
    public static final ClassData _1F = ClassData.CLASS_1F;
    public static final ClassData _1G = ClassData.CLASS_1G;
    public static final ClassData _1H = ClassData.CLASS_1H;
    public static final ClassData _2A = ClassData.CLASS_2A;
    public static final ClassData _2B = ClassData.CLASS_2B;
    public static final ClassData _2C = ClassData.CLASS_2C;
    public static final ClassData _2D = ClassData.CLASS_2D;
    public static final ClassData _2E = ClassData.CLASS_2E;
    public static final ClassData _2F = ClassData.CLASS_2F;
    public static final ClassData _2H = ClassData.CLASS_2H;
    public static final ClassData _3A = ClassData.CLASS_3A;
    public static final ClassData _3B = ClassData.CLASS_3B;
    public static final ClassData _3C = ClassData.CLASS_3C;
    public static final ClassData _3D = ClassData.CLASS_3D;
    public static final ClassData _3E = ClassData.CLASS_3E;
    public static final ClassData _3F = ClassData.CLASS_3F;
    public static final ClassData _3G = ClassData.CLASS_3G;
    public static final ClassData _3H = ClassData.CLASS_3H;
    public static final ClassData _4A = ClassData.CLASS_4A;
    public static final ClassData _4B = ClassData.CLASS_4B;
    public static final ClassData _4C = ClassData.CLASS_4C;
    public static final ClassData _4D = ClassData.CLASS_4D;
    public static final ClassData _4E = ClassData.CLASS_4E;
    public static final ClassData _4F = ClassData.CLASS_4F;
    public static final ClassData _4G = ClassData.CLASS_4G;
    public static final ClassData _5A = ClassData.CLASS_5A;
    public static final ClassData _5B = ClassData.CLASS_5B;
    public static final ClassData _5C = ClassData.CLASS_5C;
    public static final ClassData _5D = ClassData.CLASS_5D;
    public static final ClassData _5E = ClassData.CLASS_5E;
    public static final RoomData _NON_ASSEGNATO = RoomData.NON_ASSEGNATO;
    public static final RoomData _AULA_SCONOSCIUTA = RoomData.AULA_SCONOSCIUTA;
    public static final RoomData _USCITA_DIDATTICA = RoomData.USCITA_DIDATTICA;
    public static final RoomData _A1 = RoomData.A1;
    public static final RoomData _A2 = RoomData.A2;
    public static final RoomData _A3_FISICA = RoomData.A3_FIS;
    public static final RoomData _A4_INFORMATICA = RoomData.A4_INF;
    public static final RoomData _A5sharp = RoomData.A5sharp;
    public static final RoomData _A5_DISEGNO = RoomData.A5_DIS;
    public static final RoomData _A6 = RoomData.A6;
    public static final RoomData _A7 = RoomData.A7;
    public static final RoomData _B8 = RoomData.B8;
    public static final RoomData _B9 = RoomData.B9;
    public static final RoomData _B10 = RoomData.B10;
    public static final RoomData _B11 = RoomData.B11;
    public static final RoomData _B12 = RoomData.B12;
    public static final RoomData _B13 = RoomData.B13;
    public static final RoomData _B13sharp = RoomData.B13sharp;
    public static final RoomData _C14 = RoomData.C14;
    public static final RoomData _C15 = RoomData.C15;
    public static final RoomData _C16 = RoomData.C16;
    public static final RoomData _C17 = RoomData.C17;
    public static final RoomData _C18 = RoomData.C18;
    public static final RoomData _C19 = RoomData.C19;
    public static final RoomData _C20 = RoomData.C20;
    public static final RoomData _C21 = RoomData.C21;
    public static final RoomData _D22 = RoomData.D22;
    public static final RoomData _D23 = RoomData.D23;
    public static final RoomData _D24 = RoomData.D24;
    public static final RoomData _D25 = RoomData.D25;
    public static final RoomData _D26 = RoomData.D26;
    public static final RoomData _E27 = RoomData.E27;
    public static final RoomData _E28 = RoomData.E28;
    public static final RoomData _E29 = RoomData.E29;
    public static final RoomData _E29sharp = RoomData.E29sharp;
    public static final RoomData _E30 = RoomData.E30;
    public static final RoomData _E30sharp = RoomData.E30sharp;
    public static final RoomData _F31_PALESTRA = RoomData.F31_PALESTRA;
    public static final RoomData _F32_CHIMICA_E_SCIENZE = RoomData.F32_CHIMICA;

    protected String dal;
    protected String al;


    public static void main(String[] args) throws IOException {
        for (ClassData classe : ClassData.values()) {
            String className = classe.className.replace(" ", "_").replace("#", "sharp");
            System.out.println("public static final ClassData _" + className + " = ClassData.CLASS_" + classe.className.toUpperCase() + ";");
        }
        for (RoomData room : RoomData.values()) {
            String roomName = room.roomName.replace(" ", "_").replace("#", "sharp").replace("(", "").replace(")", "");
            System.out.println("public static final RoomData _" + roomName + " = RoomData." + room.name() + ";");
        }
    }

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
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F32_CHIMICA_E_SCIENZE, giorni));
    }

    protected static void classeConStampelle(LessonConstraintContainer l, ClassData classe) {
        l.add(new LessonConstraint_ClasseBloccataInArea(true, classe, new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D, ERoomArea.AREA_F}, EGiorno.values()));
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F32_CHIMICA_E_SCIENZE, EGiorno.values()));
    }

    protected static void classeConStampellePianoTerra(LessonConstraintContainer l, ClassData classe) {
        l.add(new LessonConstraint_ClasseBloccataInArea(true, classe, new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, EGiorno.values()));
        l.add(new LessonConstraint_ClasseNonInAula_labsToo(classe, _F32_CHIMICA_E_SCIENZE, EGiorno.values()));
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
