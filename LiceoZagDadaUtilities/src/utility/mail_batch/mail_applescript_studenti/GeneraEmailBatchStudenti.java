package utility.mail_batch.mail_applescript_studenti;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.Files;

public class GeneraEmailBatchStudenti {
    public static void main(String[] args) throws Exception {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/mail_batch/mail_applescript_studenti");
        File template = new File(root, "template.script");

        String templateString = new String(Files.readAllBytes(template.toPath()));


        CSVReader reader = new CSVReaderBuilder(new FileReader(new File(root, "GSUITE_PRIME_IPIA.csv"))).withSkipLines(1).build();


        int n = 1;
        PrintStream out = new PrintStream(new File(root, "final_ipia_" + n + ".scpt"));
        int i = 0;
        for (String[] riga : reader.readAll()) {
            //Anna Rita,Paliotta,annarita.paliotta@scuolesuperioridizagarolo.edu.it,Pass08975311,annaritapaliotta@gmail.com
            int col = 0;
            String nome = riga[0];
            String cognome = riga[1];
            //String classe = riga[4].replace("/studenti/","");
            String password = riga[3];
            String emailgsuite = riga[2];
            String emailpersonale = riga[8];
            String messaggio = "L'IIS Paolo Borsellino e Giovanni Falcone di Zagarolo ha attivato per tutti gli studenti un account nella piattaforma GSuite Education per potenziare i servizi per la didattica a distanza e mista.\n" +
                    "Ciascuno studente è invitato ad accedere alla pagina di GSuite https://gsuite.google.com/dashboard, inserire username e password indicate nella mail e verificarne la correttezza.\n\n" +
                    "DATI DELLO STUDENTE:\n" +
                    " ** Nominativo: " + nome + " " + cognome + "\n" +
              //      " ** Classe: " + classe.replace("_", " ") + "\n" +
                    " ** Username: " + emailgsuite + "\n" +
                    "                Nota: dopo il nome e cognome è presente la  matricola che contiene solo cifre numeriche\n\n" +
                    " ** Password:" + password + "\n" +
                    "                Nota: fare attenzione a maiuscole e minuscole\n\n" +
                    "Si ricorda che per utilizzare i servizi GSuite è sempre necessario effettuare il login con l'indirizzo GSuite (" + emailgsuite + ")\n\n" +
                    "In caso di problemi è possibile compilare il form all'indirizzo https://docs.google.com/forms/d/e/1FAIpQLSccAO1k5cgpVTvjjwL3r17Lr6mkmyCwg8njCrg8TSffe2ZLFw/viewform\n" + "\nGrazie della collaborazione";

            String t = templateString;
            t = t.replace("#to-name", "Genitori Studente " + nome + " " + cognome);
            t = t.replace("#to-address", emailpersonale);
            t = t.replace("#subject", "IIS Paolo Borsellino e Giovanni Falcone di Zagarolo - Iscrizione servizi GSuite - STUDENTE " + nome + " " + cognome);
            t = t.replace("#content", messaggio);
            if (emailpersonale.trim().length() > 0)
                out.println(t);
            else {
                System.out.println("Email mancante per " + nome + " " + cognome + "\t"  + "\tUSERNAME: " + emailgsuite + "\tPASSWORD:" + password);
            }
            i++;
            if (i % 100 == 0) {
                n++;
                out.close();
                out = new PrintStream(new File(root, "final_ipia_" + n + ".scpt"));
                //break;
            }
        }
        out.close();
        reader.close();
    }
}
