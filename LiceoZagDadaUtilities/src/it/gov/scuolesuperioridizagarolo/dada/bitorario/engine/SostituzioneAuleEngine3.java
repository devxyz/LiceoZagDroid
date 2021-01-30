package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.AbstractLessonConstraint;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OreConsecutiveStessaAula;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.*;

/**
 * Created by stefano on 14/02/2018.
 */
public class SostituzioneAuleEngine3 {
    private static final Random CASUALE = new Random(0);
    private static final boolean FLAG_SCELTA_CASUALE = true;
    private static int numProgressivoRegola = 0;
    //???
    //private static final Random generatoreCasualeFix = new Random(0);

    public static void spostamentiPerAuleNonDisponibili(
            BitOrarioGrigliaOrario o, LessonConstraintContainer vincoliStandard, FilterAule[] ff,
            TreeMap<AbstractLessonConstraint, Integer> numerositaVincoliNonSoddisfatti, boolean vincoliOreConsecutive) {

        final Set<CompatibilitaLaboratorio> estrai = CompatibilitaLaboratorio.estrai(o);


        //================================================================
        // PASSO 1: risolve i vincoli di base restituendo eventualmente le lezioni che non sono state soddisfatte
        //================================================================
        rimuoviAuleVincoliNonSoddisfatti(o, vincoliStandard);


        final ArrayList<BitOrarioOraLezione> lezioniCheNonRispettanoVincoli = risolveVincoliAndCambiaAula(o, vincoliStandard, estrai, ff, numerositaVincoliNonSoddisfatti);
        if (lezioniCheNonRispettanoVincoli.size() > 0) {

            System.out.println("ERRORE!!!!!  Alcune lezioni non rispettano i vincoli base: " + lezioniCheNonRispettanoVincoli.size());
            System.err.println("ERRORE!!!!!  Alcune lezioni non rispettano i vincoli base: " + lezioniCheNonRispettanoVincoli.size());
            for (BitOrarioOraLezione lezioni : lezioniCheNonRispettanoVincoli) {
                System.err.println(" --->> " + lezioni.toStringComplete());
            }

//            throw new IllegalArgumentException("Vedi messaggio");
            System.out.flush();
            System.err.flush();
            throw new IllegalArgumentException("Programma interrotto");
        }

        //================================================================
        // PASSO 2: risolve vincoli ore consecutive
        //================================================================
        if (!vincoliOreConsecutive) {
            System.out.println("IGNORA VINCOLI ORE CONSECUTIVE");
            return;
        }
        System.out.println("RISOLUZIONE VINCOLI ORE CONSECUTIVE");
        //lezioni in aula da controllare
        final ArrayList<BitOrarioOraLezione> lezioniInAulaOrdinato = SostituzioneAuleEngine3Util.estraiLezioniInAulaOrdinato(o);


        //vincoli complessivi verificati
        final LessonConstraintContainer vincoliFinali = new LessonConstraintContainer();
        vincoliFinali.addAll(vincoliStandard.get());
        final List<LessonConstraint_OreConsecutiveStessaAula> vincoliOreConsecutiveDaRisolvere = new ArrayList<>(LessonConstraint_OreConsecutiveStessaAula.genera(o));

        for (int i = 0; i < 4; i++) {
            System.out.println("============== >>>>>>>>>>>>>>> TENTATIVO RIMOZIONE ORE CONSECUTIVE AULE DIFFERENTI n." + (i + 1));
            vincoliOreConsecutiveDaRisolvere.clear();
            for (LessonConstraint_OreConsecutiveStessaAula x : LessonConstraint_OreConsecutiveStessaAula.genera(o)) {
                if (x.checkAll(lezioniInAulaOrdinato, o)) {
                    vincoliFinali.add(x);
                    //vincoliOreConsecutiveDaRisolvere.add(x);
                } else {
                    vincoliOreConsecutiveDaRisolvere.add(x);
                }
            }
            System.out.println("Vincoli ore consecutive non verificate: " + vincoliOreConsecutiveDaRisolvere.size());


            for (Iterator<LessonConstraint_OreConsecutiveStessaAula> iterator = vincoliOreConsecutiveDaRisolvere.iterator(); iterator.hasNext(); ) {
                LessonConstraint_OreConsecutiveStessaAula x = iterator.next();
                vincoliFinali.add(x);
                final ArrayList<BitOrarioOraLezione> ris = risolveVincoliAndCambiaAula(o, vincoliFinali, estrai, ff, numerositaVincoliNonSoddisfatti);
                if (ris.size() > 0) {
                    //non risolto
                    vincoliFinali.remove(x);
                } else {
                    iterator.remove();
                }
            }
            if (vincoliOreConsecutiveDaRisolvere.size() == 0) break;
        }

        System.out.println("Vincoli ore consecutive non risolti: " + vincoliOreConsecutiveDaRisolvere.size());


    }

    //sceglie una regola a caso usando random deterministico o prmo elemento in base al flag
    private static RegolaCambioAula scegliRegola(List<RegolaCambioAula> regole) {
        if (!FLAG_SCELTA_CASUALE) return regole.get(0);
        return regole.get(CASUALE.nextInt(regole.size()));
    }

    /**
     * rimuove le aule dalle lezioni che non soddisfano tutti i vincoli
     */
    private static void rimuoviAuleVincoliNonSoddisfatti(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoli) {
        final ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(o.getLezioni());
        for (BitOrarioOraLezione l : lezioni) {
            if (l.getAula() == null) continue;
            if (l.getAula() == RoomData.USCITA_DIDATTICA) continue;
            if (!vincoli.checkAll(l, o)) {
                final BitOrarioOraLezione nuovaLez = l.clonaLezioneInAltraAula(RoomData.NON_ASSEGNATO);
                o.removeLezione(l);
                o.addLezione(nuovaLez);
            }
        }


    }

    private static ArrayList<BitOrarioOraLezione> risolveVincoliAndCambiaAula(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoliStandard, Set<CompatibilitaLaboratorio> estrai, FilterAule[] ff,
                                                                              TreeMap<AbstractLessonConstraint, Integer> numerositaVincoliNonSoddisfatti) {
        //System.out.println("VINCOLI: "+vincoliStandard);
        ArrayList<BitOrarioOraLezione> prevLezioniDaModificare = null;

        //--------------------------- prende le lezioni che violano vincoli ed eventualmente le riordina
        ArrayList<BitOrarioOraLezione> lezioniDaModificare = SostituzioneAuleEngine3Util.estraiLezioniViolanoVincoli(o, vincoliStandard);
        riordinaLezioniCheViolanoVincoli(lezioniDaModificare);
        if (lezioniDaModificare.size() == 0) return new ArrayList<>();


        //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        int iterazioni = 0;
        do {
            iterazioni++;

            System.out.println();
            System.out.println("===========================================================================================================");
            System.out.println("  ITERAZIONE: " + iterazioni);
            System.out.println("===========================================================================================================");

            //aggiorna
            prevLezioniDaModificare = lezioniDaModificare;
            riordinaLezioniCheViolanoVincoli(lezioniDaModificare);

            System.out.println(iterazioni + "> ANALIZZA LE LEZIONI CHE VIOLANO VINCOLI. TROVATE " + lezioniDaModificare.size() + ", PRECEDENTI: " + prevLezioniDaModificare.size());

            if (lezioniDaModificare.size() == 0) break;

            for (BitOrarioOraLezione lezione : lezioniDaModificare) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("  >>> ANALISI LEZIONE PER VIOLAZIONE VINCOLI " + lezione.toStringShort());

                if (!lezione.isActive()) {
                    System.out.println("   SKIP");
                    continue;
                }

                AbstractLessonConstraint violaVincoli = SostituzioneAuleEngine3Util.lezioneViolaVincoli__returnFirstConstraintNotSatisfied(o, vincoliStandard, lezione);
                if (violaVincoli == null) {
                    throw new IllegalArgumentException("Errore programma, non dovrebbe esserci una lezione ");
                } else {
                    Integer v = numerositaVincoliNonSoddisfatti.get(violaVincoli);
                    if (v == null) {
                        numerositaVincoliNonSoddisfatti.put(violaVincoli, 1);
                    } else {
                        numerositaVincoliNonSoddisfatti.put(violaVincoli, v + 1);
                    }

                }

                RegolaCambioAula assegnato = null;
                for (FilterAule filterAule : ff) {
                    // System.out.println("Utilizzato " + filterAule + " ===================================================");
                    //cerco un'aula vuota compatibile e faccio il cambio se possibile... (la cerco la piu' piccola possibile evitando aule speciali)
                    {
                        final ArrayList<RegolaCambioAula> sostituzioni = x_cercaScambioAulaLibera(o, vincoliStandard, lezione, filterAule, estrai, true);
                        if (sostituzioni.size() > 0) {

                            final RegolaCambioAula regolaCambioAula = scegliRegola(sostituzioni);


                            regolaCambioAula.apply(o);
                            //applica sostituzione
                            assegnato = regolaCambioAula;
                            break;
                        }
                    }

                    //cerca un'aula disponibile in modo da minimizzare il n_classe totale di posti liberi in entrambe le aule
                    //lo spostamento non puo' avvenire con una classe che si trova in aule attrezzate
                    if (filterAule != FilterAule.LABORATORI_SOLO_SE_LIBERI) {
                        final ArrayList<RegolaCambioAula> sostituzioni = x_cercaScambio2Aule(o, vincoliStandard, lezione, filterAule, estrai, true);
                        if (sostituzioni.size() > 0) {
                            final RegolaCambioAula regolaCambioAula = scegliRegola(sostituzioni);

                            assegnato = regolaCambioAula;
                            //applica sostituzione
                            regolaCambioAula.apply(o);
                            break;
                        }
                    }


                    //cerco scambio su tre classi evitando aule attrezzate
                    if (filterAule != FilterAule.LABORATORI_SOLO_SE_LIBERI) {
                        final ArrayList<RegolaCambioAula> sostituzioni = x_cercaAula3Scambi(o, vincoliStandard, lezione, filterAule, estrai, true);
                        if (sostituzioni.size() > 0) {
                            final RegolaCambioAula regolaCambioAula = scegliRegola(sostituzioni);

                            assegnato = regolaCambioAula;
                            //applica sostituzione
                            regolaCambioAula.apply(o);
                            break;
                        }
                    }

                    //cerco scambio su quattro classi evitando aule attrezzate
                    if (filterAule != FilterAule.LABORATORI_SOLO_SE_LIBERI) {
                        final ArrayList<RegolaCambioAula> sostituzioni = x_cercaAula4Scambi(o, vincoliStandard, lezione, filterAule, estrai, true);
                        if (sostituzioni.size() > 0) {
                            final RegolaCambioAula regolaCambioAula = scegliRegola(sostituzioni);

                            assegnato = regolaCambioAula;
                            //applica sostituzione
                            regolaCambioAula.apply(o);
                            break;
                        }
                    }

                }


                //System.out.println("===============================");

                if (assegnato == null) {
                    String s = "ATTENZIONE!!!" + (lezione.getGiorno() + "\t" + lezione.getOra().getProgressivOra() + "^ ORA\t" + lezione.getClasse() + " - "
                            + lezione.getDocentePrincipale() + " - " + lezione.getMateriaPrincipale() + "\tclasse " + lezione.getClasse()) +
                            ("\tVecchia aula: " + lezione.getAula()) +
                            ("\tNessuna aula trovata in sostituzione\tLA SOSTITUZIONE SARA' DI NUOVO TENTATA!!!");
                    System.out.println(s);
                } else {
                    System.out.println("Trovato ed applicato cambio: " + assegnato);
                }
            }

            //riepilogo vincoli violati
            System.out.println();
            System.out.println("============= STATISTICHE VINCOLI NON SODDISFATTI NEL TEMPO ====================");
            ArrayList<Map.Entry<AbstractLessonConstraint, Integer>> rr = new ArrayList<>(numerositaVincoliNonSoddisfatti.entrySet());
            Collections.sort(rr, new Comparator<Map.Entry<AbstractLessonConstraint, Integer>>() {
                @Override
                public int compare(Map.Entry<AbstractLessonConstraint, Integer> o1, Map.Entry<AbstractLessonConstraint, Integer> o2) {
                    return -o1.getValue().compareTo(o2.getValue());
                }
            });

            int count = 0;
            for (Map.Entry<AbstractLessonConstraint, Integer> e : rr) {
                System.out.println(" - " + e);
                if (count++ > 6) {
                    System.out.println("  ....... COntINUE");
                    break;
                }
            }

            //RICALCOLA LEZIONI

            //--------------------------- prende le lezioni che violano vincoli ed eventualmente le riordina
            lezioniDaModificare = SostituzioneAuleEngine3Util.estraiLezioniViolanoVincoli(o, vincoliStandard);
            riordinaLezioniCheViolanoVincoli(lezioniDaModificare);

            /*/TODO DEBUG
            System.out.println("DEBUGGG INTERRUZIONE");
            if (true) {
                new NullPointerException().printStackTrace();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
            }
            */

        }
        while (lezioniDaModificare.size() > 0 && prevLezioniDaModificare.size() > lezioniDaModificare.size() && iterazioni < 5);
        System.out.println("N. lezioni rimaste da modificare: " + lezioniDaModificare.size());
        return lezioniDaModificare;
    }

    private static ArrayList<RegolaCambioAula> x_cercaAula3Scambi(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            FilterAule s, Set<CompatibilitaLaboratorio> c, boolean stopOnFirstMatch) {

        ArrayList<RegolaCambioAula> ris = new ArrayList<>();

        final EOra ora = lezione.getOra();
        final EGiorno giorno = lezione.getGiorno();


        //aule disponibili per la lezione corrente (quella attuale e quelle vuote)
        final ArrayList<RoomData> auleDisponibiliPerLezioneCorrente = SostituzioneAuleEngine3Util.toRoomData(o.getAuleVuote(ora, giorno));
        //SostituzioneAuleEngine3Util.sortByOccupacyASC(auleDisponibiliPerLezioneCorrente);

        //lezioni parallele con cui scambiare
        final ArrayList<BitOrarioOraLezione> lezioniParallele = SostituzioneAuleEngine3Util.estraiLezioniOrdinateCrescentePerOccupazioneAula(o, ora, giorno, s, c);

        BitOrarioOraLezione l1 = lezione;
        for (int i1 = 0; i1 < lezioniParallele.size(); i1++) {
            BitOrarioOraLezione l2 = lezioniParallele.get(i1);
            if (l1 == l2) continue;
            for (int i2 = 0; i2 < lezioniParallele.size(); i2++) {
                BitOrarioOraLezione l3 = lezioniParallele.get(i2);
                if (l1 == l3) continue;
                if (l2 == l3) continue;
                RegolaCambioAula scambioPossibile = scambioPossibile_3lezioni(o, l, s, c, l1, l2, l3, auleDisponibiliPerLezioneCorrente);
                if (scambioPossibile != null) {
                    ris.add(scambioPossibile);
                    if (stopOnFirstMatch)
                        return ris;
                }

            }
        }

        return ris;
    }

    private static ArrayList<RegolaCambioAula> x_cercaAula4Scambi(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            FilterAule s, Set<CompatibilitaLaboratorio> c, boolean stopOnFirstMatch) {

        ArrayList<RegolaCambioAula> ris = new ArrayList<>();

        final EOra ora = lezione.getOra();
        final EGiorno giorno = lezione.getGiorno();


        //aule disponibili per la lezione corrente (quella attuale e quelle vuote)
        final ArrayList<RoomData> auleDisponibiliPerLezioneCorrente = SostituzioneAuleEngine3Util.toRoomData(o.getAuleVuote(ora, giorno));
        //SostituzioneAuleEngine3Util.sortByOccupacyASC(auleDisponibiliPerLezioneCorrente);

        //lezioni parallele con cui scambiare
        final ArrayList<BitOrarioOraLezione> lezioniParallele = SostituzioneAuleEngine3Util.estraiLezioniOrdinateCrescentePerOccupazioneAula(o, ora, giorno, s, c);

        BitOrarioOraLezione l1 = lezione;
        for (int i1 = 0; i1 < lezioniParallele.size(); i1++) {
            BitOrarioOraLezione l2 = lezioniParallele.get(i1);
            if (l2 == l1) continue;
            for (int i2 = i1 + 1; i2 < lezioniParallele.size(); i2++) {
                BitOrarioOraLezione l3 = lezioniParallele.get(i2);
                if (l3 == l1) continue;
                if (l3 == l2) continue;
                for (int i3 = i2 + 1; i3 < lezioniParallele.size(); i3++) {
                    BitOrarioOraLezione l4 = lezioniParallele.get(i3);
                    if (l4 == l1) continue;
                    if (l4 == l2) continue;
                    RegolaCambioAula scambioPossibile = scambioPossibile_4lezioni(o, l, s, c, l1, l2, l3, l4, auleDisponibiliPerLezioneCorrente);
                    if (scambioPossibile != null) {
                        ris.add(scambioPossibile);
                        if (stopOnFirstMatch)
                            return ris;
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
        final EOra ora = lezione.getOra();
        final EGiorno giorno = lezione.getGiorno();

        //aule disponibili per la lezione corrente (quella attuale e quelle vuote)
        final ArrayList<RoomData> auleDisponibiliPerLezioneCorrente = SostituzioneAuleEngine3Util.toRoomData(o.getAuleVuote(ora, giorno));

        //lezioni parallele con cui scambiare
        final ArrayList<BitOrarioOraLezione> lezioniParallele = SostituzioneAuleEngine3Util.estraiLezioniOrdinateCrescentePerOccupazioneAula(o, ora, giorno, s, c);


        for (BitOrarioOraLezione altraLezione : lezioniParallele) {
            RegolaCambioAula scambioPossibile = scambioPossibile_2lezioni(o, l, s, c, lezione, altraLezione, auleDisponibiliPerLezioneCorrente);
            if (scambioPossibile != null) {
                ris.add(scambioPossibile);
                if (stopOnFirstMatch)
                    return ris;
            }

        }
        return ris;
    }

    private static RegolaCambioAula scambioPossibile_2lezioni(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, FilterAule s, Set<CompatibilitaLaboratorio> c,
            BitOrarioOraLezione l1, BitOrarioOraLezione l2, Collection<RoomData> auleLibere) {

        debug_checkVincolo(o, l, l1, l2);

        //aule che si possono usare (libere e liberate dalle lezioni)
        List<RoomData> aule = new ArrayList<>(auleLibere.size() + 2);

        if (l1.getAula() != null)
            aule.add(l1.getAula());
        if (l2.getAula() != null)
            aule.add(l2.getAula());
        aule.addAll(auleLibere);

        for (RoomData a1 : aule) {
            //se non cambia aula skip
            if (l1.getAula() == a1) continue;

            for (RoomData a2 : aule) {
                if (a1 == a2) continue;

                //se non cambia aula skip
                if (l2.getAula() == a2) continue;

                final BitOrarioOraLezione nuovaL1 = l1.clonaLezioneInAltraAula(a1);
                final BitOrarioOraLezione nuovaL2 = l2.clonaLezioneInAltraAula(a2);

                if (
                        l.checkAll(nuovaL1, o) && l.checkAll(nuovaL2, o) &&
                                s.accept(nuovaL1, c) && s.accept(nuovaL2, c)
                ) {
                    //aggiungo cambiamenti DA --> A
                    String regolaApplicata = (numProgressivoRegola++) + ") " + String.format("regola scambio a 2 lezioni >\n  # %-60s->%s\n  # %-60s->%s -- FILTRO %s",
                            l1.toStringClasseAulaDocente(), nuovaL1.toStringAula(),
                            l2.toStringClasseAulaDocente(), nuovaL2.toStringAula(), s);


                    final RegolaCambioAula e = new RegolaCambioAula(regolaApplicata);
                    e.vecchieLezioniDaRimuovere.add(l1);
                    e.vecchieLezioniDaRimuovere.add(l2);
                    e.nuoveLezioniDaAggiungere.add(nuovaL1);
                    e.nuoveLezioniDaAggiungere.add(nuovaL2);
                    return e;
                }
            }
        }
        return null;
    }


    private static RegolaCambioAula scambioPossibile_3lezioni(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, FilterAule s, Set<CompatibilitaLaboratorio> c,
            BitOrarioOraLezione l1, BitOrarioOraLezione l2, BitOrarioOraLezione l3, Collection<RoomData> auleLibere) {

        debug_checkVincolo(o, l, l1, l2, l3);
        //aule che si possono usare (libere e liberate dalle lezioni)
        List<RoomData> aule = new ArrayList<>(auleLibere.size() + 3);

        if (l1.getAula() != null && !l1.getAula().isAulaFittizia())
            aule.add(l1.getAula());

        if (l2.getAula() != null && !l2.getAula().isAulaFittizia())
            aule.add(l2.getAula());

        if (l3.getAula() != null && !l3.getAula().isAulaFittizia())
            aule.add(l3.getAula());

        aule.addAll(auleLibere);

        for (RoomData a1 : aule) {
            //se non cambia aula skip
            if (l1.getAula() == a1) continue;

            for (RoomData a2 : aule) {
                if (a1 == a2) continue;
                //se non cambia aula skip
                if (l2.getAula() == a2) continue;

                for (RoomData a3 : aule) {
                    if (a1 == a3) continue;
                    if (a2 == a3) continue;
                    //se non cambia aula skip
                    if (l3.getAula() == a3) continue;

                    final BitOrarioOraLezione nuovaL1 = l1.clonaLezioneInAltraAula(a1);
                    final BitOrarioOraLezione nuovaL2 = l2.clonaLezioneInAltraAula(a2);
                    final BitOrarioOraLezione nuovaL3 = l3.clonaLezioneInAltraAula(a3);
                    if (
                            l.checkAll(nuovaL1, o) && l.checkAll(nuovaL2, o) && l.checkAll(nuovaL3, o) &&
                                    s.accept(nuovaL1, c) && s.accept(nuovaL2, c) && s.accept(nuovaL3, c)

                    ) {
                        //System.out.println("\tCapienza aula: " + occupazioneMigliore + " posti, num. studenti:" + numStudenti);

                        String regolaApplicata = (numProgressivoRegola++) + ") " + String.format("regola scambio a 3 lezioni > \n  # %-60s->%s\n  # %-60s->%s\n  # %-60s->%s -- FILTRO %s",
                                l1.toStringClasseAulaDocente(), nuovaL1.toStringAula(),
                                l2.toStringClasseAulaDocente(), nuovaL2.toStringAula(),
                                l3.toStringClasseAulaDocente(), nuovaL3.toStringAula(),
                                s
                        );

                        final RegolaCambioAula e = new RegolaCambioAula(regolaApplicata);


                        e.removeLezione(l1);
                        e.addLezione(nuovaL1);
                        //aggiungo cambiamenti DA --> A


                        e.removeLezione(l2);
                        e.addLezione(nuovaL2);
                        //aggiungo cambiamenti DA --> A

                        e.removeLezione(l3);
                        e.addLezione(nuovaL3);

                        return e;
                    }

                }
            }
        }
        return null;
    }

    private static RegolaCambioAula scambioPossibile_4lezioni(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, FilterAule s, Set<CompatibilitaLaboratorio> c,
            BitOrarioOraLezione l1, BitOrarioOraLezione l2, BitOrarioOraLezione l3, BitOrarioOraLezione l4, Collection<RoomData> auleLibere) {

        debug_checkVincolo(o, l, l1, l2, l3, l4);
        //aule che si possono usare (libere e liberate dalle lezioni)
        List<RoomData> aule = new ArrayList<>(auleLibere.size() + 4);

        if (l1.getAula() != null && !l1.getAula().isAulaFittizia())
            aule.add(l1.getAula());

        if (l2.getAula() != null && !l2.getAula().isAulaFittizia())
            aule.add(l2.getAula());

        if (l3.getAula() != null && !l3.getAula().isAulaFittizia())
            aule.add(l3.getAula());

        if (l4.getAula() != null && !l4.getAula().isAulaFittizia())
            aule.add(l4.getAula());

        aule.addAll(auleLibere);

        for (RoomData a1 : aule) {
            //se non cambia aula skip
            if (l1.getAula() == a1) continue;

            for (RoomData a2 : aule) {
                if (a2 == a1) continue;
                //se non cambia aula skip
                if (l2.getAula() == a2) continue;

                for (RoomData a3 : aule) {
                    if (a3 == a1) continue;
                    if (a3 == a2) continue;
                    //se non cambia aula skip
                    if (l3.getAula() == a3) continue;


                    for (RoomData a4 : aule) {
                        if (a4 == a1) continue;
                        if (a4 == a2) continue;
                        if (a4 == a3) continue;
                        //se non cambia aula skip
                        if (l4.getAula() == a4) continue;

                        final BitOrarioOraLezione nuovaL1 = l1.clonaLezioneInAltraAula(a1);
                        final BitOrarioOraLezione nuovaL2 = l2.clonaLezioneInAltraAula(a2);
                        final BitOrarioOraLezione nuovaL3 = l3.clonaLezioneInAltraAula(a3);
                        final BitOrarioOraLezione nuovaL4 = l4.clonaLezioneInAltraAula(a4);
                        if (
                                l.checkAll(nuovaL1, o) && l.checkAll(nuovaL2, o) && l.checkAll(nuovaL3, o) && l.checkAll(nuovaL4, o) &&
                                        s.accept(nuovaL1, c) && s.accept(nuovaL2, c) && s.accept(nuovaL3, c) && s.accept(nuovaL4, c)

                        ) {
                            //System.out.println("\tCapienza aula: " + occupazioneMigliore + " posti, num. studenti:" + numStudenti);

                            String regolaApplicata = (numProgressivoRegola++) + ") " + String.format("regola scambio a 4 lezioni > \n" +
                                            "  # %-60s->%s\n" +
                                            "  # %-60s->%s\n" +
                                            "  # %-60s->%s\n" +
                                            "  # %-60s->%s -- FILTRO %s",
                                    l1.toStringClasseAulaDocente(), nuovaL1.toStringAula(),
                                    l2.toStringClasseAulaDocente(), nuovaL2.toStringAula(),
                                    l3.toStringClasseAulaDocente(), nuovaL3.toStringAula(),
                                    l4.toStringClasseAulaDocente(), nuovaL4.toStringAula(),
                                    s
                            );

                            final RegolaCambioAula e = new RegolaCambioAula(regolaApplicata);


                            e.removeLezione(l1);
                            e.addLezione(nuovaL1);
                            //aggiungo cambiamenti DA --> A


                            e.removeLezione(l2);
                            e.addLezione(nuovaL2);
                            //aggiungo cambiamenti DA --> A

                            e.removeLezione(l3);
                            e.addLezione(nuovaL3);

                            e.removeLezione(l4);
                            e.addLezione(nuovaL4);

                            return e;
                        }

                    }
                }
            }
        }
        return null;
    }

    private static ArrayList<RegolaCambioAula> x_cercaScambioAulaLibera(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            FilterAule s, Set<CompatibilitaLaboratorio> c, boolean stopOnFirstMatch) {
        String regolaApplicata;
        final ArrayList<RoomData> auleVuoteX = SostituzioneAuleEngine3Util.toRoomData(o.getAuleVuote(lezione.getOra(), lezione.getGiorno()));
        // SostituzioneAuleEngine3Util.sortByOccupacyASC(auleVuoteX);

        debug_checkVincolo(o, l, lezione);

        ArrayList<RegolaCambioAula> ris = new ArrayList<>();

        for (RoomData a1 : auleVuoteX) {
            if (a1 == null) continue;

            //se non cambia aula skip
            if (lezione.getAula() == a1) continue;

            final BitOrarioOraLezione nuovaLezione = lezione.clonaLezioneInAltraAula(a1);
            if (l.checkAll(nuovaLezione, o) && s.accept(nuovaLezione, c)) {
                regolaApplicata = (numProgressivoRegola++) + ") " + String.format("regola scambio aula libera > \n  # %-60s->%s -- FILTRO %s", lezione.toStringClasseAulaDocente(), nuovaLezione.toStringClasseAulaDocente(), s);
                final RegolaCambioAula e = new RegolaCambioAula(regolaApplicata);
                e.vecchieLezioniDaRimuovere.add(lezione);
                e.nuoveLezioniDaAggiungere.add(nuovaLezione);
                ris.add(e);
                if (stopOnFirstMatch)
                    return ris;

            }
        }
        return ris;
    }

    private static final boolean debug_checkVincolo = true;

    private static void debug_checkVincolo(BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione... lezione) {
        if (debug_checkVincolo) return;
        List<String> strings = l.checkAllNotPassed(Arrays.asList(lezione), o);
        if (strings.size() == 0)
            throw new IllegalArgumentException("Invalid constraint: all lesson passed." + Arrays.asList(lezione));
    }

    //riordina casualmente le lezioni che violano i vincoli oppure no in base al flag
    private static void riordinaLezioniCheViolanoVincoli(ArrayList<BitOrarioOraLezione> lezioniDaModificare) {
        if (!FLAG_SCELTA_CASUALE) return;
        for (int i = 0; i < lezioniDaModificare.size() * 2; i++) {
            int i1 = CASUALE.nextInt(lezioniDaModificare.size());
            int i2 = CASUALE.nextInt(lezioniDaModificare.size());
            if (i1 != i2) {
                //scambia
                BitOrarioOraLezione o1 = lezioniDaModificare.get(i1);
                lezioniDaModificare.set(i1, lezioniDaModificare.get(i2));
                lezioniDaModificare.set(i2, o1);
            }
        }
    }

}
