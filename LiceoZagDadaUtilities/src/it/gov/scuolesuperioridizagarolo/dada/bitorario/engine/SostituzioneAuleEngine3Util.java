package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.AbstractLessonConstraint;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.*;

/**
 * Created by stefano on 01/08/2018.
 */
public class SostituzioneAuleEngine3Util {
    //elenco lezioni che violano i vincoli, ordinate
    static ArrayList<BitOrarioOraLezione> estraiLezioniViolanoVincoli(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoli) {
        final ArrayList<BitOrarioOraLezione> lezioni = estraiLezioniInAulaOrdinato(o);

        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        for (int i1 = 0; i1 < lezioni.size(); i1++) {
            BitOrarioOraLezione lezione = lezioni.get(i1);

            boolean violaVincoli = lezioneViolaVincoli(o, vincoli, lezione);

            if (violaVincoli)
                ris.add(lezione);
        }
        return ris;
    }


    static boolean lezioneViolaVincoli(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoli, BitOrarioOraLezione lezione) {
        //skip ore di disposizione
        if (lezione.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE)
            return false;

        //salta uscite didattiche
        if (lezione.getAula() == RoomData.USCITA_DIDATTICA)
            return false;

        //controlla se lezione e' OK
        return !vincoli.checkAll(lezione, o);
    }

    static AbstractLessonConstraint lezioneViolaVincoli__returnFirstConstraintNotSatisfied(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoli, BitOrarioOraLezione lezione) {
        //skip ore di disposizione
        if (lezione.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE)
            return null;

        //salta uscite didattiche
        if (lezione.getAula() == RoomData.USCITA_DIDATTICA)
            return null;

        //controlla se lezione e' OK
        return vincoli.checkAll_returnFirstConstraintNotSatisfied(lezione, o);
    }

    static ArrayList<BitOrarioOraLezione> estraiLezioniInAulaOrdinato(BitOrarioGrigliaOrario o) {
        final ArrayList<BitOrarioOraLezione> ll = o.getLezioni();
        final ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(ll.size());
        for (BitOrarioOraLezione x : ll) {
            //salta lezioni con disposizione
            if (x.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE)
                continue;

            //salta lezioni in uscita didattica
            if (x.getAula() == RoomData.USCITA_DIDATTICA)
                continue;

            //salta lezioni laboratori
            if (x.getAula().isAulaLaboratorioPalestra())
                continue;

            lezioni.add(x);
        }


        Collections.sort(lezioni, new Comparator<BitOrarioOraLezione>() {
            @Override
            public int compare(BitOrarioOraLezione o1, BitOrarioOraLezione o2) {
                if (o1.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE &&
                        o2.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE)
                    return o1.getDocentePrincipale().compareToIgnoreCase(o2.getDocentePrincipale());
                if (o1.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) return 1;
                if (o2.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) return -1;

                int i = o1.getGiorno().compareTo(o2.getGiorno());
                if (i != 0) return i;

                i = o1.getOra().compareTo(o2.getOra());
                if (i != 0) return i;

                if (o1.getClasse() != null) {
                    i = o1.getClasse().compareTo(o2.getClasse());
                    if (i != 0) return i;
                }

                if (o1.getAula() != null) {
                    i = o1.getAula().compareTo(o2.getAula());
                    if (i != 0) return i;
                }

                if (o1.getTipoLezione() != null) {
                    i = o1.getTipoLezione().compareTo(o2.getTipoLezione());
                    if (i != 0) return i;
                }


                return 0;
            }
        });
        return lezioni;
    }

    static ArrayList<BitOrarioOraLezione> estraiLezioniOrdinateCrescentePerOccupazioneAula(BitOrarioGrigliaOrario o, EOra ora, EGiorno giorno, FilterAule f, Set<CompatibilitaLaboratorio> c) {
        final ArrayList<BitOrarioOraLezione> lezioni1 = o.getLezioni(ora, giorno);
        final ArrayList<BitOrarioOraLezione> lezioniParallele = new ArrayList<>();
        for (BitOrarioOraLezione xl : lezioni1) {
            if (xl.getAula() == null || xl.getAula().isAulaFittizia())
                continue;
            if (f.accept(xl, c))
                lezioniParallele.add(xl);
        }

        Collections.sort(lezioniParallele, new Comparator<BitOrarioOraLezione>() {
            @Override
            public int compare(BitOrarioOraLezione a, BitOrarioOraLezione b) {


                RoomData o1 = (a.getAula());
                RoomData o2 = (b.getAula());

                final int i = Integer.valueOf(o1.maxStudents).compareTo(o2.maxStudents);
                if (i != 0)
                    return i;
                return (o1.isAulaLaboratorioPalestra() + "").compareToIgnoreCase(o2.isAulaLaboratorioPalestra() + "");
            }
        });

        return lezioniParallele;
    }

    static ArrayList<RoomData> toRoomData(Collection<RoomData> aule) {
        ArrayList<RoomData> ris = new ArrayList<>();
        for (RoomData s : aule) {
            ris.add((s));
        }
        return ris;
    }

    static void sortByOccupacyASC(ArrayList<RoomData> rr) {
        Collections.sort(rr, new Comparator<RoomData>() {
            @Override
            public int compare(RoomData o1, RoomData o2) {
                final int i = Integer.valueOf(o1.maxStudents).compareTo(o2.maxStudents);
                if (i != 0)
                    return i;
                return (o1.isAulaLaboratorioPalestra() + "").compareToIgnoreCase(o2.isAulaLaboratorioPalestra() + "");
            }
        });

    }
}
