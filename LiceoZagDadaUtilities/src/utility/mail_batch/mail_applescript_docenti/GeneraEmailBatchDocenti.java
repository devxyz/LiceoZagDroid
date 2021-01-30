package utility.mail_batch.mail_applescript_docenti;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.Files;

public class GeneraEmailBatchDocenti {
    public static void main(String[] args) throws Exception {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/mail_batch/mail_applescript");
        File template = new File(root, "template.script");

        String templateString = new String(Files.readAllBytes(template.toPath()));


        CSVReader reader = new CSVReaderBuilder(new FileReader(new File(root, "utenti_gsuite.csv"))).withSkipLines(0).build();
        PrintStream out = new PrintStream(new File(root, "final.script"));
        int i = 0;
        for (String[] riga : reader.readAll()) {
            //Anna Rita,Paliotta,annarita.paliotta@scuolesuperioridizagarolo.edu.it,Pass08975311,annaritapaliotta@gmail.com
            int col = 0;
            String nome = riga[col++];
            String cognome = riga[col++];
            String emailgsuite = riga[col++];
            String emailpersonale = riga[col++];
            String messaggio = "Gentile " + nome + " " + cognome + ", in vista degli appuntamenti della prossima settimana (Riunione per Dipartimenti e Collegio Docenti) sono state attivate delle classi virtuali in GSuite in cui condividere informazioni necessarie allo svolgimento.\n\n" +
                    "Nella vostra posta elettronica GSuite  (" + emailgsuite + ") avete ricevuto un invito ad iscrivervi sia  alla classroom del dipartimento che alla classroom del Collegio Docenti.\n" +
                    "Occorre semplicemente accettare l'invito ed acccedere alla relativa classe per verificare che tutto funziona correttamente (https://gsuite.google.com/dashboard - scegliendo il servizio Classroom)\n" +
                    "Si ricorda che per utilizzare le classi e gli altri servizi GSuite è sempre necessario effettuare il login con l'indirizzo GSuite (" + emailgsuite + ")" +
                    "\nGrazie della collaborazione";

            String t = templateString;
            t = t.replace("#to-name", nome + " " + cognome);
            t = t.replace("#to-address", emailpersonale);
            t = t.replace("#subject", "Iscrizione Classroom per Dipartimenti e Collegio Docenti");
            t = t.replace("#content", messaggio);
            out.println(t);
        }
        out.close();
        reader.close();
    }
}
