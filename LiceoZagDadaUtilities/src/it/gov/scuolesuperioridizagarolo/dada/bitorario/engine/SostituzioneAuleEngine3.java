package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OreConsecutiveStessaAula;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by stefano on 14/02/2018.
 */
public class SostituzioneAuleEngine3 {

    //???
    //private static final Random generatoreCasualeFix = new Random(0);

    public static void spostamentiPerAuleNonDisponibili(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoliStandard, String note) {

        final Set<CompatibilitaLaboratorio> estrai = CompatibilitaLaboratorio.estrai(o);


        //================================================================
        // PASSO 1: risolve i vincoli di base restituendo eventualmente le lezioni che non sono state soddisfatte
        //================================================================
        final ArrayList<BitOrarioOraLezione> lezioniCheNonRispettanoVincoli = risolveVincoliAndCambiaAula(o, vincoliStandard, estrai);
        if (lezioniCheNonRispettanoVincoli.size() > 0) {
            System.out.println("ERRORE!!!!!  Alcune lezioni non rispettano i vincoli base: " + lezioniCheNonRispettanoVincoli.size());
            throw new IllegalArgumentException("Programma interrotto");
        }

        //================================================================
        // PASSO 2: risolve vincoli ore consecutive
        //================================================================
        //lezioni in aula da controllare
        final ArrayList<BitOrarioOraLezione> lezioniInAulaOrdinato = SostituzioneAuleEngine3Util.estraiLezioniInAulaOrdinato(o);

        //vincoli complessivi verificati
        final LessonConstraintContainer vincoliFinali = new LessonConstraintContainer();
        vincoliFinali.addAll(vincoliStandard.get());

        final List<LessonConstraint_OreConsecutiveStessaAula> vincoliOreConsecutiveNonVerificati = new ArrayList<>();

        for (LessonConstraint_OreConsecutiveStessaAula x : LessonConstraint_OreConsecutiveStessaAula.genera(o)) {
            if (x.checkAll(lezioniInAulaOrdinato, o)) {
                vincoliFinali.add(x);
            } else {
                vincoliOreConsecutiveNonVerificati.add(x);
            }
        }
        System.out.println("Vincoli ore consecutive non verificate: " + vincoliOreConsecutiveNonVerificati.size());
        for (Iterator<LessonConstraint_OreConsecutiveStessaAula> iterator = vincoliOreConsecutiveNonVerificati.iterator(); iterator.hasNext(); ) {
            LessonConstraint_OreConsecutiveStessaAula x = iterator.next();
            vincoliFinali.add(x);
            final ArrayList<BitOrarioOraLezione> ris = risolveVincoliAndCambiaAula(o, vincoliFinali, estrai);
            if (ris.size() > 0) {
                //non risolto
                vincoliFinali.remove(x);
            } else {
                iterator.remove();
            }
        }

        System.out.println("Vincoli ore consecutive non risolti: " + vincoliOreConsecutiveNonVerificati.size());


    }

    /**
     * risolve i vincoli di base, restituendo le lezioni per le quali c'e' almeno un vincolo non risolto
     *
     * @param o
     * @param vincoliStandard
     * @param estrai
     * @return
     */
    private static ArrayList<BitOrarioOraLezione> risolveVincoliAndCambiaAula(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoliStandard, Set<CompatibilitaLaboratorio> estrai) {
        ArrayList<BitOrarioOraLezione> prevLezioniDaModificare = null;
        ArrayList<BitOrarioOraLezione> lezioniDaModificare = o.getLezioni();
        do {

            //aggiorna
            prevLezioniDaModificare = lezioniDaModificare;

            //ordina le lezioniDaModificare
            lezioniDaModificare = SostituzioneAuleEngine3Util.estraiLezioniViolanoVincoli(o, vincoliStandard);

            System.out.println(">>>>>>>>>>>>>>>>>>> ANALIZZA LEZIONI CHE VIOLANO VINCOLI. " + lezioniDaModificare.size() + " LEZIONI DA ANALIZZARE, PRECEDENTI: " + prevLezioniDaModificare.size());


            for (BitOrarioOraLezione lezione : lezioniDaModificare) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("  >> CAMBIO LEZIONE " + lezione.toStringShort());

                if (!lezione.isActive()) {
                    System.out.println("   SKIP");
                    continue;
                }

                boolean violaVincoli = SostituzioneAuleEngine3Util.lezioneViolaVincoli(o, vincoliStandard, lezione);
                if (!violaVincoli) {
                    throw new IllegalArgumentException("Errore programma, non dovrebbe esserci una lezione ");
                }

                RegolaCambioAula assegnato = null;
                for (FilterAule filterAule : new FilterAule[]{FilterAule.LABORATORI_MAI, FilterAule.LABORATORI_SOLO_COMPATIBILI, FilterAule.LABORATORI_SEMPRE}) {
                    // System.out.println("Utilizzato " + filterAule + " ===================================================");
                    //cerco un'aula vuota compatibile e faccio il cambio se possibile... (la cerco la piu' piccola possibile evitando aule speciali)
                    {
                        final ArrayList<RegolaCambioAula> sostituzioni = x_cercaScambioAulaLibera(o, vincoliStandard, lezione, filterAule, estrai, true);
                        if (sostituzioni.size() > 0) {

                            sostituzioni.get(0).apply(o);
                            assegnato = sostituzioni.get(0);
                            break;
                        }
                    }

                    //cerca un'aula disponibile in modo da minimizzare il numero totale di posti liberi in entrambe le aule
                    //lo spostamento non puo' avvenire con una classe che si trova in aule attrezzate
                    {
                        final ArrayList<RegolaCambioAula> sostituzioni = x_cercaScambio2Aule(o, vincoliStandard, lezione, filterAule, estrai, true);
                        if (sostituzioni.size() > 0) {
                            assegnato = sostituzioni.get(0);
                            sostituzioni.get(0).apply(o);
                            break;
                        }
                    }


                    //cerco scambio su tre classi evitando aule attrezzate
                    {
                        final ArrayList<RegolaCambioAula> sostituzioni = x_cercaAula3Scambi(o, vincoliStandard, lezione, filterAule, estrai, true);
                        if (sostituzioni.size() > 0) {
                            assegnato = sostituzioni.get(0);
                            sostituzioni.get(0).apply(o);
                            break;
                        }
                    }

                }


                //System.out.println("===============================");
                if (assegnato == null) {
                    String s = "ATTENZIONE!!!" + (lezione.getGiorno() + "\t" + lezione.getOra().getProgressivOra() + "^ ORA\t" + lezione.getDocentePrincipale() + " - " + lezione.getMateriaPrincipale() + "\tclasse " + lezione.getClasse()) +
                            ("\tVecchia aula: " + lezione.getNomeAula()) +
                            ("\tNessuna aula trovata in sostituzione\tATTENZIONE!!!");
                    System.out.println(s);
                } else {
                    System.out.println(assegnato);
                }
            }
        } while (lezioniDaModificare.size() > 0 && prevLezioniDaModificare.size() > lezioniDaModificare.size());
        return lezioniDaModificare;
    }


    private static ArrayList<RegolaCambioAula> x_cercaAula3Scambi(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            FilterAule s, Set<CompatibilitaLaboratorio> c, boolean stopOnFirstMatch) {

        ArrayList<RegolaCambioAula> ris = new ArrayList<>();

        String regolaApplicata;
        final EOra ora = lezione.getOra();
        final EGiorno giorno = lezione.getGiorno();


        //aule disponibili per la lezione corrente (quella attuale e quelle vuote)
        final ArrayList<RoomData> auleDisponibiliPerLezioneCorrente = SostituzioneAuleEngine3Util.toRoomData(o.getAuleVuote(ora, giorno));
        auleDisponibiliPerLezioneCorrente.add(lezione.getAula());
        SostituzioneAuleEngine3Util.sortByOccupacyASC(auleDisponibiliPerLezioneCorrente);

        //lezioni parallele con cui scambiare
        final ArrayList<BitOrarioOraLezione> lezioniParallele = SostituzioneAuleEngine3Util.estraiLezioniOrdinateCrescentePerOccupazioneAula(o, ora, giorno);

        final String aula0 = lezione.getNomeAula();

        for (RoomData x : auleDisponibiliPerLezioneCorrente) {
            final String aula1 = x.name;

            for (BitOrarioOraLezione altraLezione : lezioniParallele) {

                //salta lezioni in lab o non in aula
                final String aula2 = altraLezione.getNomeAula();

                if (aula2 == null || aula1.equals(aula2))
                    continue;

                for (BitOrarioOraLezione altraLezione2 : new ArrayList<>(lezioniParallele)) {

                    //salta lezioni in lab o non in aula
                    final String aula3 = altraLezione2.getNomeAula();
                    //if (aula3 == null || skipAuleSpeciali && altraLezione2.getAula().flagAulaLaboratorioPalestra())

                    if (aula3 == null || aula1.equals(aula3) || aula2.equals(aula3) || aula0.equals(aula3))
                        continue;

                    String[] aule = new String[]{aula1, aula2, aula3};

                    for (int i1 = 0; i1 < aule.length; i1++) {
                        for (int i2 = 0; i2 < aule.length; i2++) {

                            for (int i3 = 0; i3 < aule.length; i3++) {
                                if (i1 == i2 || i1 == i3 || i2 == i3)
                                    continue;

                                /*
                                if (aule[i1].equals(aule[i2]) || Objects.equals(aule[i1], aule[i3]) || Objects.equals(aule[i2], aule[i3]))
                                    continue;
                                    */

                                //  System.out.println("PROVA " + aule[i1] + " " + aule[i2] + " " + aule[i3]);
                                final BitOrarioOraLezione nuovaLezione = lezione.clonaLezioneInAltraAula(aule[i1]);
                                final BitOrarioOraLezione nuovaAltraLezione = altraLezione.clonaLezioneInAltraAula(aule[i2]);
                                final BitOrarioOraLezione nuovaAltraLezione2 = altraLezione2.clonaLezioneInAltraAula(aule[i3]);
                                if (
                                        l.checkAll(nuovaLezione, o) && l.checkAll(nuovaAltraLezione, o) && l.checkAll(nuovaAltraLezione2, o) &&
                                                s.accept(nuovaLezione, c) && s.accept(nuovaAltraLezione, c) && s.accept(nuovaAltraLezione2, c)

                                        ) {
                                    //System.out.println("\tCapienza aula: " + occupazioneMigliore + " posti, num. studenti:" + numStudenti);

                                    regolaApplicata = String.format("regola >>> 3 >>> \n  > %-60s  ->  %s\n  > %-60s  ->  %s\n  > %-60s  ->  %s",
                                            lezione.toStringComplete(), nuovaLezione.toStringComplete(),
                                            altraLezione.toStringComplete(), nuovaAltraLezione.toStringComplete(),
                                            altraLezione2.toStringComplete(), nuovaAltraLezione2.toStringComplete()
                                    );

                                    final RegolaCambioAula e = new RegolaCambioAula(regolaApplicata);
                                    ris.add(e);

                                    e.removeLezione(lezione);
                                    e.addLezione(nuovaLezione);
                                    //aggiungo cambiamenti DA --> A


                                    e.removeLezione(altraLezione);
                                    e.addLezione(nuovaAltraLezione);
                                    //aggiungo cambiamenti DA --> A

                                    e.removeLezione(altraLezione2);
                                    e.addLezione(nuovaAltraLezione2);

                                    //aggiungo cambiamenti DA --> A
                                    if (stopOnFirstMatch)
                                        return ris;

                                }

                            }
                        }

                    }
                }
            }
        }
        return ris;
    }

    private static ArrayList<RegolaCambioAula> x_cercaScambio2Aule(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            FilterAule s, Set<CompatibilitaLaboratorio> c, boolean stopOnFirstMatch) {

        ArrayList<RegolaCambioAula> ris = new ArrayList<>();
        String regolaApplicata;
        final EOra ora = lezione.getOra();
        final EGiorno giorno = lezione.getGiorno();

        //aule disponibili per la lezione corrente (quella attuale e quelle vuote)
        final ArrayList<RoomData> auleDisponibiliPerLezioneCorrente = SostituzioneAuleEngine3Util.toRoomData(o.getAuleVuote(ora, giorno));
        auleDisponibiliPerLezioneCorrente.add(lezione.getAula());
        SostituzioneAuleEngine3Util.sortByOccupacyASC(auleDisponibiliPerLezioneCorrente);

        //lezioni parallele con cui scambiare
        final ArrayList<BitOrarioOraLezione> lezioniParallele = SostituzioneAuleEngine3Util.estraiLezioniOrdinateCrescentePerOccupazioneAula(o, ora, giorno);

        for (RoomData auleLezione : auleDisponibiliPerLezioneCorrente) {


            for (BitOrarioOraLezione altraLezione : lezioniParallele) {

                //salta lezioni in lab o non in aula
                if (altraLezione.getNomeAula() == null)
                    continue;

                final String aula1 = auleLezione.name;
                final String aula2 = altraLezione.getNomeAula();


                final BitOrarioOraLezione nuovaLezione = lezione.clonaLezioneInAltraAula(aula2);
                final BitOrarioOraLezione nuovaAltraLezione = altraLezione.clonaLezioneInAltraAula(aula1);

                //    System.out.println("PROVA " + aula2 + " " + aula1);
                if (
                        l.checkAll(nuovaLezione, o) && l.checkAll(nuovaAltraLezione, o) &&
                                s.accept(nuovaLezione, c) && s.accept(nuovaAltraLezione, c)
                        ) {
                    //aggiungo cambiamenti DA --> A
                    regolaApplicata = String.format("regola >>> 2 >>> \n  > %-60s  ->  %s\n  > %-60s  ->  %s",
                            lezione.toStringComplete(), nuovaLezione.toStringComplete(),
                            altraLezione.toStringComplete(), nuovaAltraLezione.toStringComplete());


                    final RegolaCambioAula e = new RegolaCambioAula(regolaApplicata);
                    e.vecchieLezioniDaRimuovere.add(lezione);
                    e.vecchieLezioniDaRimuovere.add(altraLezione);
                    e.nuoveLezioniDaAggiungere.add(nuovaLezione);
                    e.nuoveLezioniDaAggiungere.add(nuovaAltraLezione);

                    //o.removeLezione(lezione);
                    //o.addLezione(nuovaLezione);


                    //o.removeLezione(altraLezione);
                    //o.addLezione(nuovaAltraLezione);


                    ris.add(e);
                    if (stopOnFirstMatch) return ris;

                }
            }
        }
        return ris;
    }

    private static ArrayList<RegolaCambioAula> x_cercaScambioAulaLibera(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            FilterAule s, Set<CompatibilitaLaboratorio> c, boolean stopOnFirstMatch) {
        String regolaApplicata;
        final ArrayList<RoomData> auleVuoteX = SostituzioneAuleEngine3Util.toRoomData(o.getAuleVuote(lezione.getOra(), lezione.getGiorno()));
        SostituzioneAuleEngine3Util.sortByOccupacyASC(auleVuoteX);

        ArrayList<RegolaCambioAula> ris = new ArrayList<>();

        for (RoomData a : auleVuoteX) {
            if (a == null) continue;
            final BitOrarioOraLezione nuovaLezione = lezione.clonaLezioneInAltraAula(a.name);
            if (l.checkAll(nuovaLezione, o) && s.accept(nuovaLezione, c)) {
                regolaApplicata = String.format("regola >>> 2 >>> \n  > %-60s  ->  %s", lezione.toStringComplete(), nuovaLezione.toStringComplete());
                final RegolaCambioAula e = new RegolaCambioAula(regolaApplicata);
                e.vecchieLezioniDaRimuovere.add(lezione);
                e.nuoveLezioniDaAggiungere.add(nuovaLezione);
                ris.add(e);
                if (stopOnFirstMatch) return ris;

            }
        }
        return ris;
    }

}
