package utility.concorsoDirigenti.moodle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by stefano on 26/06/2018.
 */
public class TestImport {
    public static void main(String[] args) throws IOException {
        String cat1 = "Default per CD2018/Prova1";
        String cat2 = "Default per CD2018/Prova2";
        String rif="XXX";
        Questionario q = new Questionario();

        q.domande.add(new Domanda(rif, cat1, "Titolo 1", "Testo 1", new Risposta("risposta 1", 100), new Risposta("risposta 2", 0), new Risposta("risposta 3", 0), new Risposta("risposta 4", 0)));
        q.domande.add(new Domanda(rif, cat2, "Titolo 2", "Testo 2", new Risposta("risposta 1", 0), new Risposta("risposta 2", 100), new Risposta("risposta 3", 0), new Risposta("risposta 4", 0)));


        BufferedWriter w=new BufferedWriter(new FileWriter(new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/data/concorsodirigenti/moodle.xml")));
        w.append(q.toXml());
        w.close();
        System.out.println(q.toXml());
    }
}
