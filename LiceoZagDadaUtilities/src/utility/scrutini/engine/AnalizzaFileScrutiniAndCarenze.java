package utility.scrutini.engine;

import utility.scrutini.engine.data.DatiStudente;
import utility.scrutini.engine.data.RecuperoCarenze_Intestazione;
import utility.scrutini.engine.data.Scrutini_Intestazione;
import utility.scrutini.engine.data.Scrutini_TipoValoreEnum;
import utility.scrutini.engine.parser_scrutini_carenze.ParserFileRiepilogoCarenze;
import utility.scrutini.engine.parser_scrutini_carenze.ParserFileEsitiScrutini;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnalizzaFileScrutiniAndCarenze {
    private static Integer convertInteger(String s) throws NumberFormatException {
        if (s == null || s.trim().length() == 0) return null;
        return Integer.parseInt(s);
    }


    public static DatiStudente estraiDatiStudente(String classe, LinkedHashMap<Scrutini_Intestazione, String> intestazione) {
        DatiStudente d = new DatiStudente();
        d.setClasse(classe);
        for (Map.Entry<Scrutini_Intestazione, String> entry : intestazione.entrySet()) {
            String etichetta = entry.getKey().materia;
            String valore = entry.getValue();
            switch (entry.getKey().tipologiaEnum) {
                case ASSENZA:
                    d.get(etichetta).assenze = convertInteger(valore);
                    break;
                case RECUPERO_DEBITI:
                    d.get(etichetta).modalitaRecupero = valore;
                    break;
                case ALUNNO:
                    d.setNome(valore);
                    break;
                case VOTO:
                    try {
                        Integer voto = convertInteger(valore);
                        d.get(etichetta).voto = voto;
                        d.get(etichetta).votoGiudizio = null;
                    } catch (NumberFormatException e) {
                        d.get(etichetta).voto = null;
                        d.get(etichetta).votoGiudizio = valore;
                    }
                    break;
                case SCONOSCIUTO:
                    throw new IllegalArgumentException("Tipo sconosciuto: " + entry.getKey());
                case COMPORTAMENTO:
                    d.setVoto_comportamento(convertInteger(valore));
                    break;
                case ESITO_FINALE:
                    d.setEsito(valore);
                    break;
                case CREDITO:
                    d.setCredito(valore);
                    break;
                case CREDITO_ANNI_PREC:
                    break;
                default:
                    throw new IllegalArgumentException("Tipo sconosciuto: " + entry.getKey().tipologia);
            }
        }
        return d;
    }

    public static ArrayList<DatiStudente> generaReport(File scrutini, File carenze) throws IOException {
        return generaReport(scrutini, carenze, null);
    }

    public static ArrayList<DatiStudente> generaReport(File scrutini, File carenze, File report) throws IOException {
        ArrayList<LinkedHashMap<Scrutini_Intestazione, String>> datiscrutini_totali = ParserFileEsitiScrutini.analizzaFileScrutini(scrutini);
        String classe = ParserFileEsitiScrutini.analizzaFileScrutiniClasse(scrutini);
        ArrayList<LinkedHashMap<RecuperoCarenze_Intestazione, String>> daticarenze = ParserFileRiepilogoCarenze.analizzaFileCarenze(carenze);


        //le intestazioni di scrutini e carenze sono nello stesso ordine
        ArrayList<Scrutini_Intestazione> intestazioniScrutini = new ArrayList<>(datiscrutini_totali.iterator().next().keySet());
        ArrayList<RecuperoCarenze_Intestazione> intestazioniCarenze = daticarenze.size() == 0 ? new ArrayList<>() : new ArrayList<>(daticarenze.iterator().next().keySet());

        if (daticarenze.size() > 0 && daticarenze.size() != datiscrutini_totali.size()) {
            throw new IllegalArgumentException("Numero studenti non corrispondente: carenze=" + daticarenze.size() + " scrutini=" + datiscrutini_totali.size());
        }

        //controlla il numero di materie
        int materie = 0;

        ArrayList<String> labelIntestazioniScrutini = new ArrayList<>();
        for (Scrutini_Intestazione scrutini_intestazione : intestazioniScrutini) {
            if (scrutini_intestazione.tipologiaEnum == Scrutini_TipoValoreEnum.ASSENZA) {
                materie++;
                if (!labelIntestazioniScrutini.contains(scrutini_intestazione.materia))
                    labelIntestazioniScrutini.add(scrutini_intestazione.materia);
            }
        }

        if (intestazioniCarenze.size() > 0 && materie != intestazioniCarenze.size() - 1) {
            throw new IllegalArgumentException("Numero materie per le intestazioni differenti.\n  scrutini:" + labelIntestazioniScrutini + "\n  carenze:" + intestazioniCarenze);
        }

        int numeroTotaleStudenti = daticarenze.size();
        for (int numStudente = 0; numStudente < numeroTotaleStudenti; numStudente++) {
            LinkedHashMap<RecuperoCarenze_Intestazione, String> c = daticarenze.get(numStudente);
            LinkedHashMap<Scrutini_Intestazione, String> s = datiscrutini_totali.get(numStudente);
            int indexCarenze = 0;

            //scorre il recupero debiti (saltando la prima colonna - alunni)
            ArrayList<Map.Entry<RecuperoCarenze_Intestazione, String>> entries = new ArrayList<>(c.entrySet());
            for (int i = 1; i < entries.size(); i++) {
                Map.Entry<RecuperoCarenze_Intestazione, String> e = entries.get(i);
                String cc = labelIntestazioniScrutini.get(indexCarenze);
                s.put(new Scrutini_Intestazione(cc, Scrutini_TipoValoreEnum.RECUPERO_DEBITI, 0, 0), e.getValue());
                indexCarenze++;
            }
        }
        ArrayList<DatiStudente> ris = new ArrayList<>();

        //STAMPA
        for (LinkedHashMap<Scrutini_Intestazione, String> a : datiscrutini_totali) {
            ris.add(estraiDatiStudente(classe, a));
            System.out.println("=================================");
            for (Map.Entry<Scrutini_Intestazione, String> e : a.entrySet()) {
                System.out.println("  " + e.getKey().materia + "(" + e.getKey().tipologiaEnum + ") = " + e.getValue());
            }
        }

        System.out.println();
        System.out.println();
        for (DatiStudente datiStudente : ris) {
            System.out.println(datiStudente);
        }
        if (report != null) {
            PrintStream rep = new PrintStream(report);
            rep.println("<html><body>");

            for (DatiStudente d : ris) {
                rep.println("<h1>" + d.getNome() + "</h1>");
                rep.println("<table style='width=100%' >");
                rep.println("<tr><td><b>ESITO FINALE</b></td><td>" + d.getEsito() + "</td><td></td><td></td><td></td></tr>");
                rep.println("<tr><td><b>COMPORTAMENTO</b></td><td>" + d.getVoto_comportamento() + "</td><td></td><td></td></td></td></td></tr>");
                rep.println("<tr>" +
                        "<td><b style='background-color:lightgray'>Disciplina</b></td>" +
                        "<td><b style='background-color:lightgray'>Voto</b></td>" +
                        "<td><b style='background-color:lightgray'>Assenze</b></td>" +
                        "<td><b style='background-color:lightgray'>Modalità di recupero</b></td>" +
                        "<td><b style='background-color:lightgray'>Annotazioni</b></td>" +

                        "</tr>");
                for (Map.Entry<String, DatiStudente.ScrutinioMateria> m : d.getMaterie().entrySet()) {
                    DatiStudente.ScrutinioMateria infoMateria = m.getValue();
                    String nomeMateria = m.getKey();
                    rep.println("<tr >" +
                            "<td style='border:2px solid black;font-weight:bold;color:blu;font-size:14px'>" + nomeMateria + "</td>" +
                            "<td style='border:1px solid gray; text-align:center'>" + voto(infoMateria) + "</td>" +
                            "<td style='border:1px solid gray; text-align:center'>" + assenze(infoMateria) + "</td>" +
                            "<td style='border:1px solid gray;color:red;'>" + infoMateria.modalitaRecupero + "</td>" +
                            "<td style='border:1px solid gray'> </td>" +
                            "</tr>");

                }
                rep.println("</table><hr><br>");
            }
            rep.println("</body></html>");
            rep.close();
        }
        return ris;
    }

    private static String assenze(DatiStudente.ScrutinioMateria infoMateria) {
        if (infoMateria.assenze == null) return "0";
        return "" + infoMateria.assenze;
    }

    private static String voto(DatiStudente.ScrutinioMateria infoMateria) {
        if (infoMateria.voto != null) {
            if (infoMateria.voto < 6)
                return "<span style='color:red;font-weight:bolder'>" + infoMateria.voto + "";
            else
                return infoMateria.voto + "";
        }
        if (infoMateria.votoGiudizio != null) {
            return infoMateria.votoGiudizio;
        }
        return "-";
    }


}