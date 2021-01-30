package utility.corso_sicurezza;

import utility.CSVUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportFromCSVSidiStudenti {

    private static final String CODICE_ALUNNO = "Codice Alunno";
    private static final String CODICE_FISCALE = "Codice Fiscale";
    private static final String COGNOME = "Cognome";
    private static final String NOME = "Nome";
    private static final String ANNO_DI_CORSO = "Anno di Corso";
    private static final String SEZIONE = "Sezione";
    private static final String SCUOLA = "Scuola";
    private static final String SEDE = "Sede";
    private static final String STATO_ALUNNO = "Stato Alunno";



    public static StudenteSidiCollection parse(File f1_liceo, File f2_ipia) throws IOException {
        return parseStudents(f1_liceo, f2_ipia);
    }

    private static StudenteSidiCollection parseStudents(File... f) throws IOException {
        StudenteSidiCollection ris = new StudenteSidiCollection();
        for (File file : f) {
            ArrayList<StudenteSidi> studenti = new ArrayList<>();
            parseStudents(file, studenti);
            ris.add(studenti);
        }
        return ris;
    }

    ;

    public static void parseStudents(File f, ArrayList<StudenteSidi> studenti) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(f));

        String s = in.readLine();
        List<String> header = CSVUtils.parseLine(s, ';');
        while ((s = in.readLine()) != null) {
            List<String> row = CSVUtils.parseLine(s, ';');
            String codiceAlunno = row.get(header.indexOf(CODICE_ALUNNO));
            String codiceFiscale = row.get(header.indexOf(CODICE_FISCALE));
            String cognome = row.get(header.indexOf(COGNOME));
            String nome = row.get(header.indexOf(NOME));
            String annoCorso = row.get(header.indexOf(ANNO_DI_CORSO));
            String sezione = row.get(header.indexOf(SEZIONE));
            String scuola = row.get(header.indexOf(SCUOLA));
            String sede = row.get(header.indexOf(SEDE));
            String statoAlunno = row.get(header.indexOf(STATO_ALUNNO));

            StudenteSidi stud = new StudenteSidi(codiceAlunno, codiceFiscale, cognome, nome, annoCorso, sezione, scuola, sede, statoAlunno);
            studenti.add(stud);
        }
        System.out.println(header);
    }


}
