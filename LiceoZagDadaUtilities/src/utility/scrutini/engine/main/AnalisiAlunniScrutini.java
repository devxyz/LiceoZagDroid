package utility.scrutini.engine.main;

import utility.scrutini.engine.AnalizzaFileScrutiniAndCarenze;
import utility.scrutini.engine.data.DatiStudente;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class AnalisiAlunniScrutini {

    public static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/scrutini/dati");

    public static void main(String[] args) throws IOException {


        File[] tt = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });


        ArrayList<DatiStudente> stud = new ArrayList<>();

        for (File f : tt) {
            System.out.println("Analisi cartella " + f.getName());
            File[] tabellone = f.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".pdf") && pathname.getName().toLowerCase().contains("tabello");
                }
            });

            File[] recupero = f.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".pdf") && pathname.getName().toUpperCase().contains("CARENZE");
                }
            });

            if (tabellone == null || tabellone.length == 0) continue;
            if (recupero == null || recupero.length == 0) continue;

            File tab = tabellone[0];
            File rec = recupero[0];

            System.out.println("==============================================================");
            System.out.println("Analisi file TABELLONE:" + tab.getParentFile().getName() + "/" + tab.getName());
            System.out.println("             RECUPERO CARENZE:" + rec.getParentFile().getName() + "/" + rec.getName());
            ArrayList<DatiStudente> c = AnalizzaFileScrutiniAndCarenze.generaReport(tab, rec);
            stud.addAll(c);
        }


        BufferedWriter dettaglio = new BufferedWriter(new FileWriter(new File(root, "scrutini_alunni_dettaglio.csv")));
        BufferedWriter riepilogo = new BufferedWriter(new FileWriter(new File(root, "scrutini_alunni_riepilogo.csv")));
        dettaglio.write("nome;nomeclasse;classe;sez;indirizzo;materia;voto;assenze;modalita_recupero;anomalia\n");
        riepilogo.write("nome;nomeclasse;classe;sez;indirizzo;suff;insuff;tot_insuff_5;tot_insuff_4;esito\n");
        for (DatiStudente x : stud) {
            int suff = 0;
            int insuff5 = 0;
            int insuff4 = 0;
            for (Map.Entry<String, DatiStudente.ScrutinioMateria> e : x.getMaterie().entrySet()) {
                if (e.getValue().voto == null) continue;
                if (e.getValue().voto < 5) {
                    insuff4++;
                } else if (e.getValue().voto == 5) {
                    insuff5++;
                } else {
                    suff++;
                }
            }

            String nome = x.getNome();
            String classe = x.getClasse();
            int numClasse = Integer.parseInt(classe.substring(0, 1));
            String sezClasse = classe.substring(2, 3);
            String indirizzo = classe.substring(4).trim();


            for (Map.Entry<String, DatiStudente.ScrutinioMateria> e : x.getMaterie().entrySet()) {
                Integer voto = e.getValue().voto;
                Integer assenze = e.getValue().assenze;
                if (assenze == null) {
                    assenze = 0;
                }


                String materia = e.getKey();
                String recupero = e.getValue().modalitaRecupero;

                String anomalia = "";
                if (voto == null) {
                    anomalia = "VOTO MANCANTE";
                } else {
                    if ((voto < 6) && (recupero == null || recupero.trim().length() == 0))
                        anomalia = "ANOMALIA: modalita' di recupero mancante (INSUFF)";
                    if ((voto != null && voto >= 6) && (recupero != null && recupero.trim().length() > 0)) {
                        anomalia = "ANOMALIA: modalita' di recupero non consentita (SUFF.)";
                    }
                }


                dettaglio.write(nome + ";" + classe + ";" + numClasse + ";" + sezClasse + ";" + indirizzo + ";" + materia + ";" + voto + ";" + assenze + ";" + recupero + ";" + anomalia + "\n");

            }
            riepilogo.write(nome + ";" + classe + ";" + numClasse + ";" + sezClasse + ";" + indirizzo + ";" + suff + ";" + (insuff4 + insuff5) + ";" + insuff5 + ";" + insuff4 + ";" + x.getEsito() + "\n");

        }
        dettaglio.close();
        riepilogo.close();


    }
}
