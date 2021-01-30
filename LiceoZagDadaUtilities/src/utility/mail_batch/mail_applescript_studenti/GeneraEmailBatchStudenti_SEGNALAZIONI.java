package utility.mail_batch.mail_applescript_studenti;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import it.gov.scuolesuperioridizagarolo.model.dto.C_MyDate;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.*;

public class GeneraEmailBatchStudenti_SEGNALAZIONI {
    private static ArrayList<String[]> split(String s) {
        String[] split = s.split("\n");
        ArrayList<String[]> ris = new ArrayList<>();
        for (String s1 : split) {
            s1 = s1.trim();
            if (s1.length() > 0) {
                ris.add(s1.split("\t"));
            }
        }
        return ris;
    }

    public static void main(String[] args) throws Exception {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/mail_batch/mail_applescript_studenti");
        File template = new File(root, "template-segnalazioni.script");

        String templateString = new String(Files.readAllBytes(template.toPath()));


        Map<Integer, String> mappaCodiciAlunni_Email = new TreeMap<>();
        Map<Integer, String[]> mappaCodiciAlunni_Info = new TreeMap<>();
        {
            // COPIA COLONNA EMAIL
            final ArrayList<String[]> tabellaSegnalazioni = split(
                            "alessandroserra2003@gmail.com\tSerra\tAlessandro\t7462\t3A\tLICEO SCIENTIFICO\n" +
                                    "pfornari13@gmail.com\tZappia\tLorenzo\t00007004\tV C\tLICEO SCIENTIFICO");


            for (int i = 0; i < tabellaSegnalazioni.size(); i++) {
                String[] row = tabellaSegnalazioni.get(i);
                String email = tabellaSegnalazioni.get(i)[0];
                String codice_alunno = tabellaSegnalazioni.get(i)[3].replace("o", "0").replace("O", "0");
                mappaCodiciAlunni_Email.put(Integer.parseInt(codice_alunno), email.trim());
                mappaCodiciAlunni_Info.put(Integer.parseInt(codice_alunno), row);
            }
        }

        CSVReader reader = new CSVReaderBuilder(new FileReader(new File(root, "users_studenti.csv"))).withSkipLines(1).build();


        int n = 1;

        PrintStream out = new PrintStream(new File(root, "/invio-credenziali-segnalazioni/segnalazioni_" + (new Date().toString().replace(" ", "_")) + ".scpt"));
        int i = 0;
        for (String[] riga : reader.readAll()) {
            //Anna Rita,Paliotta,annarita.paliotta@scuolesuperioridizagarolo.edu.it,Pass08975311,annaritapaliotta@gmail.com
            int col = 0;
            String nome = riga[0];
            String cognome = riga[1];
            String classe = riga[20];
            String password = riga[3];
            String emailgsuite = riga[2];
            //String emailpersonale = riga[9];


            //nuova email
            final int codice_alunno = Integer.parseInt(riga[16]);
            final String nuovaEmail = mappaCodiciAlunni_Email.remove(codice_alunno);
            if (nuovaEmail == null) continue;//skip
            String[] info_segnalazione = mappaCodiciAlunni_Info.remove(codice_alunno);

            String messaggio = "L'IIS Paolo Borsellino e Giovanni Falcone di Zagarolo ha attivato per tutti gli studenti un account nella piattaforma GSuite Education per potenziare i servizi per la didattica a distanza.\n" +
                    "Ciascuno studente è invitato ad accedere alla pagina di GSuite https://gsuite.google.com/dashboard, inserire username e password indicate nella mail e verificarne la correttezza.\n\n" +
                    "DATI DELLO STUDENTE:\n" +
                    " ** Nominativo: " + nome + " " + cognome + "\n" +
                    " ** Classe: " + classe.replace("_", " ") + "\n" +
                    " ** Username: " + emailgsuite + "\n" +
                    "                Nota: dopo il nome e cognome è presente la  matricola che contiene solo cifre numeriche\n\n" +
                    " ** Password:" + password + "\n" +
                    "                Nota: ad eccezione del primo e dell'ultimo carattere, le altre parti della password sono solo cifre numeriche\n\n" +
                    "Dai prossimi giorni i docenti inizieranno ad utilizzare i servizi GSuite con gli studenti.\n" +
                    "Si ricorda che per utilizzare i servizi GSuite è sempre necessario effettuare il login con l'indirizzo GSuite (" + emailgsuite + ")\n\n" +
                    "In caso di problemi è possibile compilare il form all'indirizzo https://docs.google.com/forms/d/e/1FAIpQLSccAO1k5cgpVTvjjwL3r17Lr6mkmyCwg8njCrg8TSffe2ZLFw/viewform\n" + "\nGrazie della collaborazione";

            String t = templateString;
            t = t.replace("#to-name", "Genitori Studente " + nome + " " + cognome);
            t = t.replace("#to-address", nuovaEmail);
            t = t.replace("#subject", "IIS Paolo Borsellino e Giovanni Falcone di Zagarolo - Iscrizione servizi GSuite - STUDENTE " + nome + " " + cognome + " - NUOVO INOLTRO A SEGUITO SEGNALAZIONE");
            t = t.replace("#content", messaggio);
            out.println(t);

            System.out.println("====================================================");
            System.out.println("DATI:      " + nome + " " + cognome + "\t" + "CODICE STUDENTE " + codice_alunno + "\t CLASSE:" + classe + "\tUSERNAME: " + emailgsuite + "\tPASSWORD:" + password);
            System.out.println("RICHIESTA  " + info_segnalazione[2].trim().toUpperCase() + " " + info_segnalazione[1].trim().toUpperCase() + "\tCODICE STUDENTE: " + info_segnalazione[3] + " NUOVA EMAIL PERSONALE " + nuovaEmail + " INFO TOTALI:" + Arrays.toString(info_segnalazione))
            ;
            i++;
        }

        for (Map.Entry<Integer, String[]> e : mappaCodiciAlunni_Info.entrySet()) {
            String[] info = e.getValue();
            Integer codice_alunno = e.getKey();
            System.out.println("Email NON GESTITA per CODICE STUDENTE " + codice_alunno + " DATI " + Arrays.toString(info) + " --> RIGA NON TROVATA");
        }
        out.close();
        reader.close();


    }
}
