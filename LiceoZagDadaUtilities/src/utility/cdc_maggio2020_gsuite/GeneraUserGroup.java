package utility.cdc_maggio2020_gsuite;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.TreeSet;


//inserisce gli utenti nei gruppi
public class GeneraUserGroup {


    public static void main(String[] args) throws Throwable {
        CredenzialiGSuiteDocenti doc = new CredenzialiGSuiteDocenti(GeneraGruppi.file_credenziali_docenti);
        CredenzialiGSuiteStudenti stu_liceo = new CredenzialiGSuiteStudenti(GeneraGruppi.file_credenziali_studenti_liceo);
        CredenzialiGSuiteStudenti stu_ipia = new CredenzialiGSuiteStudenti(GeneraGruppi.file_credenziali_studenti_ipia);

        //doc.cercaEmailNominativo("MILLOZZI S.");


        //if (true) return;

        TreeSet<String> emails = new TreeSet<>();

        PrintStream out;
        out = new PrintStream(new File(GeneraGruppi.root, "gruppi_output.csv"));
        out.println("Group Email [Required],Member Email,Member Name,Member Role,Member Type");


        CSVReader r = new CSVReaderBuilder(new FileReader(GeneraGruppi.file_componenti)).withSkipLines(1).build();
        TreeSet<String> gruppi = new TreeSet<>();
        for (String[] row : r.readAll()) {


            String nome = row[0].trim();
            if (nome.trim().length() < 2)
                continue;//skip no nome


            String scuola = row[1].trim();
            String classe = row[2].trim();
            String tipologia = row[3].replace(".", "_").trim();
            String note = row[4].trim();
            String gruppo = tipologia.toLowerCase() + "." + classe.toUpperCase() + "." + scuola.toLowerCase() + ".201920";

            //cerca email
            String email = null;
            String note2 = "";
            String _nome_gsuite = null;
            String _cognome_gsuite = null;
            if (tipologia.equalsIgnoreCase("docenti")) {
                CredenzialiGSuiteDocenti.CredenzialiGSuiteDocenti_Row ris = doc.cercaEmailNominativo(nome);
                if (ris != null) {
                    email = ris.email;
                    note2 = "Trovato:" + ris.cognome_nome + " - " + note + "-- SIMILARITA':" + doc.getLastSimilarity();
                    _nome_gsuite = ris.nome;
                    _cognome_gsuite = ris.cognome;
                }

            }

            //studenti o rappr genitori
            else if (scuola.equalsIgnoreCase("liceo")) {
                CredenzialiGSuiteStudenti.CredenzialiGSuiteDocenti_Row ris = stu_liceo.cercaEmailNominativo(nome, classe, scuola);
                if (ris != null) {
                    email = ris.email;
                    note2 = "Trovato:" + ris.cognome_nome + " " + ris.classe_sezione + " LICEO" + "-- SIMILARITA':" + stu_liceo.getLastSimilarity();
                    _nome_gsuite = ris.nome;
                    _cognome_gsuite = ris.cognome;
                }

            } else if (scuola.equalsIgnoreCase("ipia")) {
                CredenzialiGSuiteStudenti.CredenzialiGSuiteDocenti_Row ris = stu_ipia.cercaEmailNominativo(nome, classe, scuola);
                if (ris != null) {
                    email = ris.email;
                    note2 = "Trovato:" + ris.cognome_nome + " " + ris.classe_sezione + " IPIA" + "-- SIMILARITA':" + stu_ipia.getLastSimilarity();
                    _nome_gsuite = ris.nome;
                    _cognome_gsuite = ris.cognome;
                }

            } else {
                throw new IllegalArgumentException("Invalid row: " + Arrays.toString(row));
            }

            if (email == null) {
                throw new NullPointerException("EMAIL NON TROVATA " + Arrays.toString(row));
            }

            System.out.print(Arrays.toString(row) + "\t");
            System.out.println(email + "\t" + note + "\t" + note2);

            String nomegruppo = gruppo;
            String emailgsuite = email;
            if (tipologia.equalsIgnoreCase("docenti"))
                gruppi.add(nomegruppo);
            out.printf("%s@scuolesuperioridizagarolo.edu.it,%s,%s %s,MEMBER,USER\n", nomegruppo, emailgsuite, _nome_gsuite, _cognome_gsuite);

        }

        for (String gr : gruppi) {
            out.printf("%s@scuolesuperioridizagarolo.edu.it,%s,%s %s,MEMBER,USER\n", gr, "manuela.cenciarini@scuolesuperioridizagarolo.edu.it", "Manuela", "Cenciarini");
        }

        out.close();


    }
}
