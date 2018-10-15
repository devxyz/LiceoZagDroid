package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.AbstractLessonConstraint;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OreConsecutiveStessaAula;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.*;

/**
 * Created by stefano on 14/02/2018.
 */
public class SostituzioneAuleEngine3 {
    private static final Random CASUALE=new Random(0);
    private static final boolean FLAG_SCELTA_REGOLA_CASUALE=true;

    //???
    //private static final Random generatoreCasualeFix = new Random(0);

    public static void spostamentiPerAuleNonDisponibili(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoliStandard, FilterAule[] ff, TreeMap<AbstractLessonConstraint, Integer> numerositaVincoliNonSoddisfatti) {

        final Set<CompatibilitaLaboratorio> estrai = CompatibilitaLaboratorio.estrai(o);


        //================================================================
        // PASSO 1: risolve i vincoli di base restituendo eventualmente le lezioni che non sono state soddisfatte
        //================================================================
        final ArrayList<BitOrarioOraLezione> lezioniCheNonRispettanoVincoli = risolveVincoliAndCambiaAula(o, vincoliStandard, estrai, ff, numerositaVincoliNonSoddisfatti);
        if (lezioniCheNonRispettanoVincoli.size() > 0) {
            System.out.println("ERRORE!!!!!  Alcune lezioni non rispettano i vincoli base: " + lezioniCheNonRispettanoVincoli.size());
            //throw new IllegalArgumentException("Programma interrotto");
        }

        //================================================================
        // PASSO 2: risolve vincoli ore consecutive
        //================================================================
        System.out.println("RISOLUZIONE VINCOLI ORE CONSECUTIVE");
        //lezioni in aula da controllare
        final ArrayList<BitOrarioOraLezione> lezioniInAulaOrdinato = SostituzioneAuleEngine3Util.estraiLezioniInAulaOrdinato(o);

        //vincoli complessivi verificati
        final LessonConstraintContainer vincoliFinali = new LessonConstraintContainer();
        vincoliFinali.addAll(vincoliStandard.get());

        final List<LessonConstraint_OreConsecutiveStessaAula> vincoliOreConsecutiveNonVerificati = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            vincoliOreConsecutiveNonVerificati.clear();
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
                final ArrayList<BitOrarioOraLezione> ris = risolveVincoliAndCambiaAula(o, vincoliFinali, estrai, ff, numerositaVincoliNonSoddisfatti);
                if (ris.size() > 0) {
                    //non risolto
                    vincoliFinali.remove(x);
                } else {
                    iterator.remove();
                }
            }
            if (vincoliOreConsecutiveNonVerificati.size() == 0) break;
        }

        System.out.println("Vincoli ore consecutive non risolti: " + vincoliOreConsecutiveNonVerificati.size());


    }

    private static RegolaCambioAula scegliRegola(List<RegolaCambioAula> regole){
        if (!FLAG_SCELTA_REGOLA_CASUALE)return regole.get(0);
        return regole.get(CASUALE.nextInt(regole.size()));
    }

    /**
     * risolve i vincoli di base, restituendo le lezioni per le quali c'e' almeno un vincolo non risolto
     *
     * @param o
     * @param vincoliStandard
     * @param estrai
     * @param numerositaVincoliNonSoddisfatti
     * @return
     */
    private static ArrayList<BitOrarioOraLezione> risolveVincoliAndCambiaAula(BitOrarioGrigliaOrario o, LessonConstraintContainer vincoliStandard, Set<CompatibilitaLaboratorio> estrai, FilterAule[] ff, TreeMap<AbstractLessonConstraint, Integer> numerositaVincoliNonSoddisfatti) {
        //System.out.println("VINCOLI: "+vincoliStandard);
        ArrayList<BitOrarioOraLezione> prevLezioniDaModificare = null;
        ArrayList<BitOrarioOraLezione> lezioniDaModificare = SostituzioneAuleEngine3Util.estraiLezioniViolanoVincoli(o, vincoliStandard);


        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        int iterazioni = 0;
        do {
            iterazioni++;

            //aggiorna
            prevLezioniDaModificare = lezioniDaModificare;

            System.out.println(iterazioni + "> ANALIZZA LE LEZIONI CHE VIOLANO VINCOLI. TROVATE " + lezioniDaModificare.size() + ", PRECEDENTI: " + prevLezioniDaModificare.size());

            if (lezioniDaModificare.size() == 0) break;

            for (BitOrarioOraLezione lezione : lezioniDaModificare) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("  >>> MODIFICA LEZIONE PER VIOLAZIONE VINCOLI " + lezione.toStringShort());

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

                    //cerca un'aula disponibile in modo da minimizzare il numero totale di posti liberi in entrambe le aule
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

                }


                //System.out.println("===============================");

                if (assegnato == null) {
                    String s = "ATTENZIONE!!!" + (lezione.getGiorno() + "\t" + lezione.getOra().getProgressivOra() + "^ ORA\t" + lezione.getDocentePrincipale() + " - " + lezione.getMateriaPrincipale() + "\tclasse " + lezione.getClasse()) +
                            ("\tVecchia aula: " + lezione.getAula()) +
                            ("\tNessuna aula trovata in sostituzione\tLA SOSTITUZIONE SARA' DI NUOVO TENTATA!!!");
                    System.out.println(s);
                } else {
                    System.out.println("Trovato cambio: " + assegnato);
                }
            }

            //riepilogo vincoli violati
            System.out.println();
            System.out.println("============= RIEPILOGO VINCOLI NON SODDISFATTI ====================");
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
            System.out.println("====================================================================");

            //RICALCOLA LEZIONI
            //ordina le lezioniDaModificare
            lezioniDaModificare = SostituzioneAuleEngine3Util.estraiLezioniViolanoVincoli(o, vincoliStandard);

        }
        while (lezioniDaModificare.size() > 0 && prevLezioniDaModificare.size() > lezioniDaModificare.size() && iterazioni < 10);
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

        final RoomData aula0 = lezione.getAula();

        for (RoomData x : auleDisponibiliPerLezioneCorrente) {
            final RoomData aula1 = x;

            for (BitOrarioOraLezione altraLezione : lezioniParallele) {

                //salta lezioni in lab o non in aula
                final RoomData aula2 = altraLezione.getAula();

                if (aula2 == null || aula1.equals(aula2))
                    continue;

                for (BitOrarioOraLezione altraLezione2 : new ArrayList<>(lezioniParallele)) {

                    //salta lezioni in lab o non in aula
                    final RoomData aula3 = altraLezione2.getAula();
                    //if (aula3 == null || skipAuleSpeciali && altraLezione2.getAula().flagAulaLaboratorioPalestra())

                    if (aula3 == null || aula1.equals(aula3) || aula2.equals(aula3) || aula0.equals(aula3))
                        continue;

                    RoomData[] aule = new RoomData[]{aula1, aula2, aula3};

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

                                    regolaApplicata = String.format("regola >>> cambio a 3 aule >>> \n  > %-60s  ->  %s\n  > %-60s  ->  %s\n  > %-60s  ->  %s -- FILTRO %s",
                                            lezione.toStringComplete(), nuovaLezione.toStringComplete(),
                                            altraLezione.toStringComplete(), nuovaAltraLezione.toStringComplete(),
                                            altraLezione2.toStringComplete(), nuovaAltraLezione2.toStringComplete(),
                                            s
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
                if (altraLezione.getAula() == null)
                    continue;

                final RoomData aula1 = auleLezione;
                final RoomData aula2 = altraLezione.getAula();


                final BitOrarioOraLezione nuovaLezione = lezione.clonaLezioneInAltraAula(aula2);
                final BitOrarioOraLezione nuovaAltraLezione = altraLezione.clonaLezioneInAltraAula(aula1);

                //    System.out.println("PROVA " + aula2 + " " + aula1);
                if (
                        l.checkAll(nuovaLezione, o) && l.checkAll(nuovaAltraLezione, o) &&
                                s.accept(nuovaLezione, c) && s.accept(nuovaAltraLezione, c)
                        ) {
                    //aggiungo cambiamenti DA --> A
                    regolaApplicata = String.format("regola >>> cambio a 2 aule >>> \n  > %-60s  ->  %s\n  > %-60s  ->  %s -- FILTRO %s",
                            lezione.toStringComplete(), nuovaLezione.toStringComplete(),
                            altraLezione.toStringComplete(), nuovaAltraLezione.toStringComplete(), s);


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
            final BitOrarioOraLezione nuovaLezione = lezione.clonaLezioneInAltraAula(a);
            if (l.checkAll(nuovaLezione, o) && s.accept(nuovaLezione, c)) {
                regolaApplicata = String.format("regola >>> cambio con aula libera >>> \n  > %-60s  ->  %s -- FILTRO %s", lezione.toStringComplete(), nuovaLezione.toStringComplete(), s);
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
