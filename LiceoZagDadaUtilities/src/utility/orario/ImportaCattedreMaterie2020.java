package utility.orario;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImportaCattedreMaterie2020 {


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(ImportaCattedreMaterie2020.class.getResourceAsStream("cattedre2020.txt")));
        StringBuilder sb = new StringBuilder();

        sb.append("Materia\tDocente\tClasse\tOre\tCompito\tDocente in compresenza\tore\tLaboratorio");
        sb.append("\n");
        String docenteCompresenza = "";
        String oreCompresenza = "";
        String laboratorioCompresenza = "";

        String r;
        String materia = null;
        String docente = "";
        int num_riga = 0;
        while ((r = in.readLine()) != null) {
            try {
                num_riga++;
                System.out.println("RIGA: " + num_riga);
                System.out.println(r);
                if (r.trim().length() == 0) continue;
                //System.out.println("##RIGA:" + r);

                String riga = r.trim();
                if (riga.length() == 0) continue;

                String[] celle_riga = riga.split("\t[ ]*");

                if (celle_riga[0].toUpperCase().startsWith("DOCENT")) {
                    continue;
                }
                if (celle_riga[0].toUpperCase().startsWith("#")) {
                    materia = riga.replace("#", "").trim();
                    materia = materia.trim().split("-")[1].trim().toUpperCase();
                    System.out.println("MATERIA " + materia);
                    continue;
                }

                if (celle_riga[0].trim().length() > 0)
                    docente = celle_riga[0];

                for (int i = 1; i < celle_riga.length; i = i + 2) {
                    String classe = celle_riga[i].toUpperCase().trim();
                    if (classe.trim().length() == 0)
                        continue;
                    //skip ipia
                    if (classe.toUpperCase().contains("IPIA"))
                        continue;

                    String ore = celle_riga[i + 1].toUpperCase().trim();


                    String[] elementi_classe = classe.split("[ ]+");


                    String[] classi;
                    if (elementi_classe.length == 1) {
                        //controlla se unica classe
                        classi = elementi_classe[0].split("/");
                    } else {
                        classi = elementi_classe[0].split("/");
                        materia = elementi_classe[1].trim();
                    }

                    for (String cc : classi) {
                        int oreSingolaDisciplina = Integer.parseInt(ore) / classi.length;

                        String materia1 = materia;
                        int oreMateria1 = oreSingolaDisciplina;
                        String oreCompito1 = "N";
                        String materia2 = null;
                        int oreMateria2 = 0;
                        String oreCompito2 = "N";


                        if (materia.equalsIgnoreCase("MAT")) {
                            materia1 = "MATEMATICA";
                            oreCompito1 = "2";
                        }
                        if (materia.equalsIgnoreCase("FIS")) {
                            materia1 = "FISICA";
                        }
                        if (materia.equalsIgnoreCase("FIL")) {
                            materia1 = "FILOSOFIA";
                        }
                        if (materia.equalsIgnoreCase("STO")) {
                            materia1 = "STORIA";
                        }
                        if (materia.equalsIgnoreCase("LAT")) {
                            materia1 = "LATINO";
                        }
                        if (materia.equalsIgnoreCase("ITA")) {
                            materia1 = "ITALIANO";
                            oreCompito1 = "2";
                        }
                        if (materia.equalsIgnoreCase("DIS STORIA DELL'ARTE")) {
                            materia1 = "DISEGNO ARTE";
                            oreCompito1 = "N";
                        }
                        if (materia.equalsIgnoreCase("ITA/LAT")) {
                            materia1 = "ITALIANO";
                            oreCompito1 = "2";
                            oreMateria1 = 4;

                            materia2 = "LATINO";
                            oreCompito2 = "N";
                            oreMateria2 = 3;
                        }

                        if (materia.equalsIgnoreCase("ITA/STO")) {
                            materia1 = "ITALIANO";
                            oreCompito1 = "2";
                            oreMateria1 = 4;

                            materia2 = "STORIA";
                            oreCompito2 = "N";
                            oreMateria2 = 3;
                        }


                        if (materia.equalsIgnoreCase("INGLESE")) {
                            oreCompito1 = "2";
                        }


                        //una sola classe

                        if (oreMateria1 > 3) {
                            oreCompito1 = "2";
                        }
                        if (oreMateria2 > 3) {
                            oreCompito2 = "2";
                        }

                        if (materia1 != null)
                            sb.append(materia1 + "\t" + docente + "\t" + cc + "\t" + oreMateria1 + "\t" + oreCompito1 + "\t" + docenteCompresenza + "\t" + oreCompresenza + "\t" + laboratorioCompresenza).append("\n");
                        if (materia2 != null)
                            sb.append(materia2 + "\t" + docente + "\t" + cc + "\t" + oreMateria2 + "\t" + oreCompito2 + "\t" + docenteCompresenza + "\t" + oreCompresenza + "\t" + laboratorioCompresenza).append("\n");
                    }
                }

            } catch (Throwable ex) {
                throw new IllegalArgumentException("Errore linea " + num_riga + " -> " + r, ex);
            }
        }
        System.out.println(sb);
        writeTextToClipboard(sb.toString());

    }

    public static void writeTextToClipboard(String s) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(s);
        clipboard.setContents(transferable, null);
    }
}
