package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * controlla che le ore specificate siano svolte nella stessa aula per la classe specificata
 */
public class LessonConstraint_OreConsecutiveStessaAula extends AbstractLessonConstraint {
    private final ClassData classe;
    private final EGiorno giorno;
    private final EOra ora1;
    private final EOra ora2;
    private final String note;

    public LessonConstraint_OreConsecutiveStessaAula(ClassData classe, EGiorno giorno, EOra ora1, EOra ora2, String note) {
        super(false);
        this.classe = classe;
        this.giorno = giorno;
        this.ora1 = ora1;
        this.ora2 = ora2;
        this.note = note;
    }

    /**
     * generaRoomCode tutti i vincoli per la stessa aula per le ore consecutive
     *
     * @param orario
     * @return
     */
    public static List<LessonConstraint_OreConsecutiveStessaAula> genera(BitOrarioGrigliaOrario orario) {
        List<LessonConstraint_OreConsecutiveStessaAula> ris = new ArrayList<>();
        final TreeSet<ClassData> classi = orario.getClassi();

        for (ClassData classe : classi) {
            final EGiorno[] giorni = EGiorno.values();
            final ClassData classeD = classe;
            for (EGiorno giorno : giorni) {


                final EOra[] ore = EOra.values();
                for (EOra ora : ore) {
                    if (!ora.flagOraDiLezione())
                        continue;
                    if (ora.next() == null || ora.next() == EOra.USCITA)
                        continue;
                    final BitOrarioOraLezione l = orario.getLezioneInClasse(ora, giorno, classe);
                    final BitOrarioOraLezione lnext = orario.getLezioneInClasse(ora.next(), giorno, classe);
                    if (l == null) continue;
                    if (l.getAula() == null) continue;
                    if (lnext == null) continue;
                    if (lnext.getAula() == null) continue;
                    if (lnext.getAula().isAulaLaboratorioPalestra()) continue;
                    if (l.getAula().isAulaLaboratorioPalestra()) continue;
                    if (l.getMateriaPrincipale().equals(lnext.getMateriaPrincipale())) {
                        ris.add(new LessonConstraint_OreConsecutiveStessaAula(classeD, giorno, ora, ora.next(), l.getMateriaPrincipale() + " " + l.getDocentePrincipale()));

                    }

                }
            }
        }

        return ris;
    }

    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale,
                              String docenteCompresenza, String materiaCompresenza,
                              String docenteSostegno, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        if (classe == null) return true;
        if (aula == null || aula.isAulaFittizia()) return true;

        if (this.classe == classe)
            if (this.giorno.equals(giorno)) {
                if (this.ora1.equals(ora)) {
                    final BitOrarioOraLezione altraLezione = orario.getLezioneInClasse(ora2, giorno, classe);
                    if (altraLezione != null) {
                        final RoomData altraAula = altraLezione.getAula();
                        if (altraAula != null && !altraAula.isAulaFittizia()) {
                            if (!altraAula.equals(aula))
                                return false;
                        }
                    }
                }
                /*
                if (this.ora2.equals(ora)) {
                    final BitOrarioOraLezione altraLezione = orario.getLezioneInClasse(ora1, giorno, classe);
                    if (altraLezione != null) {
                        final RoomData altraAula = altraLezione.getAula();
                        if (altraAula != null) {
                            if (!altraAula.equals(aula))
                                return false;
                        }
                    }
                }
                */


            }
        return true;
    }

    @Override
    public String toString() {
        return "LessonConstraint_OreConsecutiveStessaAula{" +
                "classe=" + classe +
                ", giorno=" + giorno +
                ", ora1=" + ora1 +
                ", ora2=" + ora2 +
                ", note='" + note + '\'' +
                '}';
    }
}
