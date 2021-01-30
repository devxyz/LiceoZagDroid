package utility.didattica_online.assenze_online;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import it.gov.scuolesuperioridizagarolo.model.dto.C_MyDate;
import utility.didattica_online.assenze_online.data.DidatticaOnline_Lezione;
import utility.didattica_online.assenze_online.data.DidatticaOnline_Lezione_StudenteClasse;
import utility.didattica_online.assenze_online.data.DidatticaOnline_Presenze;
import utility.didattica_online.assenze_online.data.DidatticaOnline_StudenteClasse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AssenzeOnlineMain {
    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/didattica_online/assenze_online");

    public static void main(String[] args) throws Exception {
        File fileStudentiMoodle = new File(root, "users_studenti_moodle.csv");
        File fileStudentiGsuite = new File(root, "users_studenti_gsuite.csv");
        File filePresenze_GENERICO = new File(root, "generico.csv");
        File filePresenze_GSUITE = new File(root, "gsuite.csv");

        //test1(fileStudentiMoodle);
        //test2(filePresenze_no_GSUITE);
        TreeMap<DidatticaOnline_StudenteClasse, TreeSet<C_MyDate>> alunno_presenze = new TreeMap<>();
        TreeMap<DidatticaOnline_StudenteClasse, TreeSet<C_MyDate>> alunno_assenze = new TreeMap<>();
        TreeSet<DidatticaOnline_StudenteClasse> alunniTotali = new TreeSet<>();

        {
            TreeMap<DidatticaOnline_Lezione, DidatticaOnline_Presenze> analisi_report = costruisciPresenzeTOTALE(filePresenze_GENERICO, filePresenze_GSUITE, fileStudentiMoodle, fileStudentiGsuite);
            for (Map.Entry<DidatticaOnline_Lezione, DidatticaOnline_Presenze> report : analisi_report.entrySet()) {
                DidatticaOnline_Lezione lezione = report.getKey();
                DidatticaOnline_Presenze presenze_assenze = report.getValue();
                System.out.println("====================================");
                System.out.println("Lezione " + lezione);
                //System.out.println("Assenti: " + presenze_assenze.getAssenti());
                TreeSet<DidatticaOnline_StudenteClasse> assentiUsandoFuorilista = presenze_assenze.getAssentiUsandoFuorilista();
                System.out.println("Assenti Usando Fuorilista: " + assentiUsandoFuorilista);

                for (DidatticaOnline_StudenteClasse a : assentiUsandoFuorilista) {
                    TreeSet<C_MyDate> x = alunno_assenze.computeIfAbsent(a, k -> new TreeSet<>());
                    x.add(lezione.data);
                    alunniTotali.add(a);
                }

                for (DidatticaOnline_StudenteClasse a : presenze_assenze.getPresentiUsandoFuorilista()) {
                    TreeSet<C_MyDate> x = alunno_presenze.computeIfAbsent(a, k -> new TreeSet<>());
                    x.add(lezione.data);
                    alunniTotali.add(a);
                }

                //System.out.println("Fuori lista: " + presenze_assenze.getFuorilista());
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("========================================================");

        for (DidatticaOnline_StudenteClasse alunno : alunniTotali) {
            TreeSet<C_MyDate> assenze = alunno_assenze.get(alunno);
            TreeSet<C_MyDate> presenze = alunno_presenze.get(alunno);
            System.out.print(alunno.classe + " " + alunno.cognome + " " + alunno.nome + "\t");
            System.out.print("## REPORT PARTECIPAZIONE SESSIONI SINCRONE - 5 marzo 8 aprile 2020. ##\\n");
            if (assenze == null) {
                System.out.println("Sempre presente (100% presenza):\\nPresenze:" + presenze);
            } else if (presenze == null) {
                System.out.println("Sempre ASSENTE (0% presenza)\\nAssenze: " + assenze);
            } else {
                double percent = Math.round(100 * (double) presenze.size() / (presenze.size() + assenze.size()));
                System.out.println(percent + "% presenza.\\nPresenze: " + presenze + "\\nAssenze: " + assenze);
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("========================================================");
        TreeSet<String> report = new TreeSet<>();
        for (DidatticaOnline_StudenteClasse alunno : alunniTotali) {
            TreeSet<C_MyDate> assenze = alunno_assenze.get(alunno);
            TreeSet<C_MyDate> presenze = alunno_presenze.get(alunno);
            StringBuilder out = new StringBuilder();
            out.append("(" + alunno.classe + ") " + alunno.cognome + " " + alunno.nome + "\t");
            out.append("## REPORT partecipazione sessioni sincrone " + alunno.cognome + " " + alunno.nome + "##\\n");
            if (assenze == null) {
                out.append("Sempre presente (100% presenza):\\nPresenze:" + presenze.size());
            } else if (presenze == null) {
                out.append("Sempre ASSENTE (0% presenza)\\nAssenze: " + assenze.size());
            } else {
                double percent = Math.round(100 * (double) presenze.size() / (presenze.size() + assenze.size()));
                out.append(percent + "% presenza.\\nPresenze: " + presenze.size() + "\\nAssenze: " + assenze.size());
            }


            if (assenze == null) {
                out.append("\t100").append("\n");
            } else if (presenze == null) {
                out.append("\t0").append("\n");
            } else {
                double percent = Math.round(100 * (double) presenze.size() / (presenze.size() + assenze.size()));
                out.append("\t" + (percent + "").replace(".", ",")).append("\n");
            }
            report.add(out.toString());
        }

        for (String s : report) {
            System.out.print(s);
        }

    }


    public static TreeMap<DidatticaOnline_Lezione, DidatticaOnline_Presenze> costruisciPresenzeTOTALE(File presenzeGENERICHE, File presenzeGSUITE, File studentiMoodle, File studentiGSUITE) throws Exception {
        TreeMap<DidatticaOnline_Lezione, DidatticaOnline_Presenze> ris = new TreeMap<>();
        final List<DidatticaOnline_Lezione_StudenteClasse> studenti_lezioni = new ArrayList<>();
        studenti_lezioni.addAll(leggiLezioniSvolteGENERICO(presenzeGENERICHE));
        studenti_lezioni.addAll(leggiLezioniSvolteGSuite(presenzeGSUITE, studentiGSUITE));

        final Map<String, Set<DidatticaOnline_StudenteClasse>> studentiPerClasse = leggiStudentiTotali_Classe_Studenti(studentiMoodle);

        for (DidatticaOnline_Lezione_StudenteClasse l1 : studenti_lezioni) {
            DidatticaOnline_Lezione l = l1.lezione;

            DidatticaOnline_Presenze value = ris.get(l);
            if (value == null) {
                value = new DidatticaOnline_Presenze();
                Set<DidatticaOnline_StudenteClasse> studentiClasseCorrente = studentiPerClasse.get(l.classe);
                if (studentiClasseCorrente == null)
                    throw new NullPointerException("Classe " + l.classe + " non presente nel file elenco studenti");
                value.tutti.addAll(studentiClasseCorrente);
                ris.put(l, value);
            }
            value.presenti.add(l1.studente);
        }
        return ris;
    }


    public static List<DidatticaOnline_Lezione_StudenteClasse> leggiLezioniSvolteGENERICO(File presenze) throws Exception {
        List<DidatticaOnline_Lezione_StudenteClasse> ris = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        CSVReader r = new CSVReaderBuilder(new FileReader(presenze))
                .withSkipLines(1)
                .build();
        for (String[] riga : r.readAll()) {
            //System.out.println(Arrays.toString(riga));
            if (riga[0].trim().length() == 0) continue;
            String data = riga[0].split("[ ]+")[0];
            String nome = riga[1];
            String cognome = riga[2];
            String classe = riga[3];
            if (nome.trim().length() == 0) continue;
            DidatticaOnline_Lezione lezione = new DidatticaOnline_Lezione(new C_MyDate(format.parse(data)), classe);
            DidatticaOnline_StudenteClasse studente = new DidatticaOnline_StudenteClasse(nome, cognome, classe);
            ris.add(new DidatticaOnline_Lezione_StudenteClasse(lezione, studente));
        }


        r.close();
        return ris;
    }

    public static List<DidatticaOnline_Lezione_StudenteClasse> leggiLezioniSvolteGSuite(File presenzeGSUITE, File studentiGSuite) throws Exception {
        Map<String, DidatticaOnline_StudenteClasse> stud = leggiStudentiTotali_Email_Studente(studentiGSuite);
        List<DidatticaOnline_Lezione_StudenteClasse> ris = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        CSVReader r = new CSVReaderBuilder(new FileReader(presenzeGSUITE))
                .withSkipLines(1)
                .build();
        for (String[] riga : r.readAll()) {
            if (riga[0].trim().length() == 0) continue;
            String data = riga[0].split("[ ]+")[0];
            String email = riga[1].trim().toUpperCase();
            if (email.trim().length() == 0) continue;

            DidatticaOnline_StudenteClasse studente = stud.get(email);
            if (studente == null) {
                for (String s : stud.keySet()) {
                    System.out.println(s);
                }
                throw new NullPointerException("Errore email " + email + " non trovata");
            }
            DidatticaOnline_Lezione lezione = new DidatticaOnline_Lezione(new C_MyDate(format.parse(data)), studente.classe);
            ris.add(new DidatticaOnline_Lezione_StudenteClasse(lezione, studente));
        }


        r.close();
        return ris;
    }

    public static Map<String, Set<DidatticaOnline_StudenteClasse>> leggiStudentiTotali_Classe_Studenti(File fileStudentiMoodle) throws IOException, CsvException {
        CSVReader r = new CSVReaderBuilder(new FileReader(fileStudentiMoodle))
                .withSkipLines(1)
                .build();

        Map<String, Set<DidatticaOnline_StudenteClasse>> studenti = new TreeMap<>();

        List<String[]> strings = r.readAll();
        for (String[] row : strings) {
            //System.out.println(Arrays.toString(row));
            String classe = row[6].toUpperCase().trim();
            String stud = row[4].replace("[ ]+", " ").trim().toUpperCase().replace("'", "");
            Set<DidatticaOnline_StudenteClasse> classeStud = studenti.get(classe);
            if (classeStud == null) {
                classeStud = new TreeSet<>();
                studenti.put(classe, classeStud);
            }
            String[] nomecognome = stud.split("[ ]+");
            if (nomecognome.length != 2) {
                throw new IllegalArgumentException("Errore parsing " + stud + ": " + Arrays.toString(nomecognome));
            }
            String nome = (nomecognome[1]);
            String cognome = (nomecognome[0]);
            classeStud.add(new DidatticaOnline_StudenteClasse(nome, cognome, classe));
        }
        r.close();
        return studenti;
    }

    public static Map<String, DidatticaOnline_StudenteClasse> leggiStudentiTotali_Email_Studente(File fileStudentiGSUITE) throws IOException, CsvException {
        CSVReader r = new CSVReaderBuilder(new FileReader(fileStudentiGSUITE))
                .withSkipLines(1)
                .build();

        Map<String, DidatticaOnline_StudenteClasse> studenti = new TreeMap<>();

        List<String[]> strings = r.readAll();
        for (String[] row : strings) {
            //System.out.println(Arrays.toString(row));
            String email = row[2].toUpperCase().trim();
            String classe = row[4].split("_")[0].trim();
            String nome = row[0].trim();
            String cognome = row[1].trim();
            studenti.put(email, new DidatticaOnline_StudenteClasse(nome, cognome, classe));
        }
        r.close();
        return studenti;
    }


}
