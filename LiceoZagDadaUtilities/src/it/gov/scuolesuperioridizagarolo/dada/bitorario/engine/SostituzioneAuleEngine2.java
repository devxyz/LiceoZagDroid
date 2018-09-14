package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by stefano on 14/02/2018.
 */
@Deprecated
public class SostituzioneAuleEngine2 {
    public static String spostamentiPerAuleNonDisponibili(BitOrarioGrigliaOrario o, LessonConstraintContainer l, String note) {

        final Set<CompatibilitaLaboratorio> estrai = CompatibilitaLaboratorio.estrai(o);
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bout);
        out.println("<html>" +
                "<head>\n" +
                "<style>\n" +
                "td  " +
                "h1  " +
                "html {color:black;} " +
                ".pagebreak { page-break-before: always; } " +
                "</style>\n" +
                "</head>" +
                "<body>");
        out.println("<h1>" + note + "</h1>");

        out.print("<table border='1' width='100%'>");
        //ordina le lezioni
        ArrayList<BitOrarioOraLezione> lezioni = estraiOrarioLezioniOrdinato(o);
        Map<String, StringBuilder> m = new TreeMap<>();


        for (int i1 = 0; i1 < lezioni.size(); i1++) {
            BitOrarioOraLezione lezione = lezioni.get(i1);
            //skip ore di disposizione
            if (lezione.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE)
                continue;

            //salta uscite didattiche
            if (lezione.getAula() == RoomData.USCITA_DIDATTICA)
                continue;

            //controlla se lezione e' OK
            if (l.checkAll(lezione, o))
                continue;

            //skip aule speciali
/*            if (DataContainer.getRoom(lezione.nomeAula).flagAulaLaboratorioPalestra()){
                System.out.println(DataContainer.getRoom(lezione.nomeAula).usage);
                continue;
            }*/


            List<BitOrarioOraLezione> nuovaAssegnazione_DA = new ArrayList<>();
            List<BitOrarioOraLezione> nuovaAssegnazione_A = new ArrayList<>();

            for (FilterAule filterAule : new FilterAule[]{FilterAule.LABORATORI_MAI, FilterAule.LABORATORI_SOLO_COMPATIBILI, FilterAule.LABORATORI_SEMPRE}) {
                System.out.println(filterAule + " ===================================================");
                //cerco un'aula vuota compatibile e faccio il cambio se possibile... (la cerco la piu' piccola possibile evitando aule speciali)
                if (nuovaAssegnazione_DA.size() == 0) {
                    _cercaScambioAulaLibera(o, l, lezione, nuovaAssegnazione_DA, nuovaAssegnazione_A, filterAule, estrai);
                }

                //cerca un'aula disponibile in modo da minimizzare il numero totale di posti liberi in entrambe le aule
                //lo spostamento non puo' avvenire con una classe che si trova in aule attrezzate
                if (nuovaAssegnazione_DA.size() == 0) {
                    _cercaScambio2Aule(o, l, lezione, nuovaAssegnazione_DA, nuovaAssegnazione_A, filterAule, estrai);
                }


                //cerco scambio su tre classi evitando aule attrezzate
                if (nuovaAssegnazione_DA.size() == 0) {
                    _cercaAula3Scambi(o, l, lezione, nuovaAssegnazione_DA, nuovaAssegnazione_A, filterAule, estrai);
                }

            }


            //System.out.println("===============================");
            if (nuovaAssegnazione_A.size() == 0) {
                String s = "<tr><td>" + (lezione.getGiorno() + "</td><td>" + lezione.getOra().getProgressivOra() + "^ ORA</td><td>" + lezione.getDocentePrincipale() + " - " + lezione.getMateriaPrincipale() + "</td><td>classe " + lezione.getClasse()) +
                        ("</td><td>Vecchia aula: " + lezione.getNomeAula()) +
                        ("</td><td>Nessuna aula trovata in sostituzione</td><td>ATTENZIONE!!!</td></tr>");
                System.out.println(s);
                out.println(s);
            } else {

                //ricalcola elenco lezioni da analizzare e ricomincia dall'inizio
                lezioni = estraiOrarioLezioniOrdinato(o);
                i1 = 0;


                for (int i = 0; i < nuovaAssegnazione_DA.size(); i++) {
                    final BitOrarioOraLezione da = nuovaAssegnazione_DA.get(i);
                    final BitOrarioOraLezione a = nuovaAssegnazione_A.get(i);

                    StringBuilder sb = m.get(da.getClasse()) == null ? new StringBuilder() : m.get(da.getClasse());
                    m.put(da.getClasse(), sb);

                    String s;
                    s = "<tr><td>" + (da.getGiorno() + "</td><td>" + da.getOra().getProgressivOra() + "° ORA </td><td>" + da.getDocentePrincipale() + " - " + da.getMateriaPrincipale() + "</td><td>classe " + da.getClasse());
                    s += ("</td><td>Vecchia aula: " + da.getNomeAula());
                    // s += ("</td><td>Nuova aula: <b>" + a.getNomeAula()) + "</b> rule #" + regolaApplicata + "</td></tr>";
                    s += ("</td><td>Nuova aula: <b>" + a.getNomeAula()) + "</b></td><td>" + a.getNote() + "</td></tr>";
                    out.println(s);
                    sb.append(s).append("\n");

                }


            }

        }

        out.println("</table>");


        for (Map.Entry<String, StringBuilder> e : m.entrySet()) {
            out.print("<div class=\"pagebreak\"> </div>");
            out.println("<br><br><hr>");
            out.println("<h1> CLASSE " + e.getKey() + "  " + note + "</h1>");
            out.print("<table border='1' width='100%'>");
            out.print(e.getValue());
            out.println("</table>");
        }

        out.print("</body></html>");
        return bout.toString();

    }

    private static ArrayList<BitOrarioOraLezione> estraiOrarioLezioniOrdinato(BitOrarioGrigliaOrario o) {
        final ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(o.getLezioni());
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

                if (o1.getNomeAula() != null) {
                    i = o1.getNomeAula().compareTo(o2.getNomeAula());
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

    private static void _cercaAula3Scambi(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            List<BitOrarioOraLezione> nuovaAssegnazione_DA, List<BitOrarioOraLezione> nuovaAssegnazione_A,
            FilterAule s, Set<CompatibilitaLaboratorio> c) {
        String regolaApplicata;
        final EOra ora = lezione.getOra();
        final EGiorno giorno = lezione.getGiorno();


        //aule disponibili per la lezione corrente (quella attuale e quelle vuote)
        final ArrayList<RoomData> auleDisponibiliPerLezioneCorrente = toRoomData(o.getAuleVuote(ora, giorno));
        auleDisponibiliPerLezioneCorrente.add(lezione.getAula());
        sortByOccupacyASC(auleDisponibiliPerLezioneCorrente);

        //lezioni parallele con cui scambiare
        final ArrayList<BitOrarioOraLezione> lezioniParallele = estraiLezioniOrdinateCrescentePerOccupazioneAula(o, ora, giorno);

        final String aula0 = lezione.getNomeAula();

        for (RoomData x : auleDisponibiliPerLezioneCorrente) {
            final String aula1 = x.roomname;
            if (nuovaAssegnazione_DA.size() > 0) break;

            for (BitOrarioOraLezione altraLezione : lezioniParallele) {

                if (nuovaAssegnazione_DA.size() > 0) break;

                //salta lezioni in lab o non in aula
                final String aula2 = altraLezione.getNomeAula();

                if (aula2 == null || aula1.equals(aula2))
                    continue;

                for (BitOrarioOraLezione altraLezione2 : new ArrayList<>(lezioniParallele)) {
                    if (nuovaAssegnazione_DA.size() > 0) break;

                    //salta lezioni in lab o non in aula
                    final String aula3 = altraLezione2.getNomeAula();
                    //if (aula3 == null || skipAuleSpeciali && altraLezione2.getAula().flagAulaLaboratorioPalestra())

                    if (aula3 == null || aula1.equals(aula3) || aula2.equals(aula3) || aula0.equals(aula3))
                        continue;

                    String[] aule = new String[]{aula1, aula2, aula3};

                    for (int i1 = 0; i1 < aule.length; i1++) {
                        if (nuovaAssegnazione_DA.size() > 0) break;
                        for (int i2 = 0; i2 < aule.length; i2++) {
                            if (nuovaAssegnazione_DA.size() > 0) break;

                            for (int i3 = 0; i3 < aule.length; i3++) {
                                if (nuovaAssegnazione_DA.size() > 0) break;
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

                                    o.removeLezione(lezione);
                                    o.addLezione(nuovaLezione);

                                    //aggiungo cambiamenti DA --> A
                                    nuovaAssegnazione_DA.add(lezione);
                                    nuovaAssegnazione_A.add(nuovaLezione);


                                    o.removeLezione(altraLezione);
                                    o.addLezione(nuovaAltraLezione);

                                    //aggiungo cambiamenti DA --> A
                                    nuovaAssegnazione_DA.add(altraLezione);
                                    nuovaAssegnazione_A.add(nuovaAltraLezione);

                                    o.removeLezione(altraLezione2);
                                    o.addLezione(nuovaAltraLezione2);

                                    //aggiungo cambiamenti DA --> A
                                    nuovaAssegnazione_DA.add(altraLezione2);
                                    nuovaAssegnazione_A.add(nuovaAltraLezione2);

                                    regolaApplicata = String.format("regola >>> 3 >>> \n  > %-60s  ->  %s\n  > %-60s  ->  %s\n  > %-60s  ->  %s",
                                            lezione.toStringComplete(), nuovaLezione.toStringComplete(),
                                            altraLezione.toStringComplete(), nuovaAltraLezione.toStringComplete(),
                                            altraLezione2.toStringComplete(), nuovaAltraLezione2.toStringComplete()
                                    );

/*                                    regolaApplicata = "regola 3: " +
                                            lezione.getNomeAula() + "->" + nuovaLezione.getNomeAula() + " - " +
                                            altraLezione.getNomeAula() + "->" + nuovaAltraLezione.getNomeAula() + " - " +
                                            altraLezione2.getNomeAula() + "->" + nuovaAltraLezione2.getNomeAula() + "#"
                                            + " -- " +
                                            aule[i1] + " " + aule[i2] + " " + aule[i3] + " -- " +
                                            aula0 + "<>" + aula1 + "<>" + aula2 + "<>" + aula3
                                    ;*/
                                    System.out.println(regolaApplicata);
                                    return;

                                }

                            }
                        }

                    }
                }
            }
        }
    }

    private static void _cercaScambio2Aule(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            List<BitOrarioOraLezione> nuovaAssegnazione_DA, List<BitOrarioOraLezione> nuovaAssegnazione_A,
            FilterAule s, Set<CompatibilitaLaboratorio> c) {
        String regolaApplicata;
        final EOra ora = lezione.getOra();
        final EGiorno giorno = lezione.getGiorno();

        //aule disponibili per la lezione corrente (quella attuale e quelle vuote)
        final ArrayList<RoomData> auleDisponibiliPerLezioneCorrente = toRoomData(o.getAuleVuote(ora, giorno));
        auleDisponibiliPerLezioneCorrente.add(lezione.getAula());
        sortByOccupacyASC(auleDisponibiliPerLezioneCorrente);

        //lezioni parallele con cui scambiare
        final ArrayList<BitOrarioOraLezione> lezioniParallele = estraiLezioniOrdinateCrescentePerOccupazioneAula(o, ora, giorno);

        for (RoomData auleLezione : auleDisponibiliPerLezioneCorrente) {

            if (nuovaAssegnazione_DA.size() > 0) break;

            for (BitOrarioOraLezione altraLezione : lezioniParallele) {
                if (nuovaAssegnazione_DA.size() > 0) break;

                //salta lezioni in lab o non in aula
                if (altraLezione.getNomeAula() == null)
                    continue;

                final String aula1 = auleLezione.roomname;
                final String aula2 = altraLezione.getNomeAula();


                final BitOrarioOraLezione nuovaLezione = lezione.clonaLezioneInAltraAula(aula2);
                final BitOrarioOraLezione nuovaAltraLezione = altraLezione.clonaLezioneInAltraAula(aula1);

                //    System.out.println("PROVA " + aula2 + " " + aula1);
                if (
                        l.checkAll(nuovaLezione, o) && l.checkAll(nuovaAltraLezione, o) &&
                                s.accept(nuovaLezione, c) && s.accept(nuovaAltraLezione, c)
                        ) {

                    o.removeLezione(lezione);
                    o.addLezione(nuovaLezione);

                    //aggiungo cambiamenti DA --> A
                    nuovaAssegnazione_DA.add(lezione);
                    nuovaAssegnazione_A.add(nuovaLezione);


                    o.removeLezione(altraLezione);
                    o.addLezione(nuovaAltraLezione);

                    //aggiungo cambiamenti DA --> A
                    nuovaAssegnazione_DA.add(altraLezione);
                    nuovaAssegnazione_A.add(nuovaAltraLezione);
                    regolaApplicata = String.format("regola >>> 2 >>> \n  > %-60s  ->  %s\n  > %-60s  ->  %s",
                            lezione.toStringComplete(), nuovaLezione.toStringComplete(),
                            altraLezione.toStringComplete(), nuovaAltraLezione.toStringComplete());
                    System.out.println(regolaApplicata);
                    return;

                }
            }
        }
    }

    private static void _cercaScambioAulaLibera(
            BitOrarioGrigliaOrario o, LessonConstraintContainer l, BitOrarioOraLezione lezione,
            List<BitOrarioOraLezione> nuovaAssegnazione_DA, List<BitOrarioOraLezione> nuovaAssegnazione_A,
            FilterAule s, Set<CompatibilitaLaboratorio> c) {
        String regolaApplicata;
        final ArrayList<RoomData> auleVuoteX = toRoomData(o.getAuleVuote(lezione.getOra(), lezione.getGiorno()));
        sortByOccupacyASC(auleVuoteX);

        for (RoomData a : auleVuoteX) {
            if (a == null) continue;
            final BitOrarioOraLezione nuovaLezione = lezione.clonaLezioneInAltraAula(a.roomname);
            if (l.checkAll(nuovaLezione, o) &&
                    s.accept(nuovaLezione, c)) {
                //System.out.println("\tCapienza aula: " + occupazioneMigliore + " posti, num. studenti:" + numStudenti);
                o.removeLezione(lezione);
                o.addLezione(nuovaLezione);

                //aggiungo cambiamenti DA --> A
                nuovaAssegnazione_DA.add(lezione);
                nuovaAssegnazione_A.add(nuovaLezione);

                regolaApplicata = String.format("regola >>> 2 >>> \n  > %-60s  ->  %s", lezione.toStringComplete(), nuovaLezione.toStringComplete());
                System.out.println(regolaApplicata);

                return;
            }
        }
    }

    private static ArrayList<BitOrarioOraLezione> estraiLezioniOrdinateCrescentePerOccupazioneAula(BitOrarioGrigliaOrario o, EOra ora, EGiorno giorno) {
        final ArrayList<BitOrarioOraLezione> lezioni1 = o.getLezioni(ora, giorno);
        final ArrayList<BitOrarioOraLezione> lezioniParallele = new ArrayList<>();
        for (BitOrarioOraLezione xl : lezioni1) {
            if (xl.getNomeAula() != null)
                lezioniParallele.add(xl);
        }

        Collections.sort(lezioniParallele, new Comparator<BitOrarioOraLezione>() {
            @Override
            public int compare(BitOrarioOraLezione a, BitOrarioOraLezione b) {


                RoomData o1 = ClassesAndRoomContainer.getRoom(a.getNomeAula());
                RoomData o2 = ClassesAndRoomContainer.getRoom(b.getNomeAula());

                final int i = Integer.valueOf(o1.maxStudents).compareTo(o2.maxStudents);
                if (i != 0)
                    return i;
                return (o1.flagAulaLaboratorioPalestra() + "").compareToIgnoreCase(o2.flagAulaLaboratorioPalestra() + "");
            }
        });

        return lezioniParallele;
    }

    private static ArrayList<RoomData> toRoomData(Collection<String> aule) {
        ArrayList<RoomData> ris = new ArrayList<>();
        for (String s : aule) {
            ris.add(ClassesAndRoomContainer.getRoom(s));
        }
        return ris;
    }

    public static void sortByOccupacyASC(ArrayList<RoomData> rr) {
        Collections.sort(rr, new Comparator<RoomData>() {
            @Override
            public int compare(RoomData o1, RoomData o2) {
                final int i = Integer.valueOf(o1.maxStudents).compareTo(o2.maxStudents);
                if (i != 0)
                    return i;
                return (o1.flagAulaLaboratorioPalestra() + "").compareToIgnoreCase(o2.flagAulaLaboratorioPalestra() + "");
            }
        });

    }
}
