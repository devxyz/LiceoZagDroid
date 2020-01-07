package utility.orario;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImportaCattedreMaterie {


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(ImportaCattedreMaterie.class.getResourceAsStream("cattedre.txt")));
        String classeConcorso = "";
        ArrayList<String> materie = new ArrayList<>();
        ArrayList<String> ore_materie = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        sb.append("Materia\tDocente\tClasse\tOre\tCompito\tDocente in compresenza\tore\tLaboratorio");
        sb.append("\n");


        String r;
        while ((r = in.readLine()) != null) {
            if (r.trim().length() == 0) continue;
            //System.out.println("##RIGA:" + r);

            String riga = r.trim();
            String[] intestazione = riga.split("\t");
            if (riga.toUpperCase().startsWith("DOCENT")) {

                materie.clear();
                ore_materie.clear();
                materie.add("");
                ore_materie.add("");

                classeConcorso = intestazione[0].split("#")[1];
                for (int i = 1; i < intestazione.length; i++) {
                    String s = intestazione[i].trim();
                    if (s.length() > 0) {
                        String[] split = s.split("#");//materia+orario
                        materie.add(split[0].trim());
                        ore_materie.add(split[1].trim().replace("h", "").replace("H", "").replace(" ", ""));
                    }
                }
            } else {
                String nomeDocente = intestazione[0];
                for (int i = 1; i < intestazione.length; i++) {
                    String s = intestazione[i].trim();
                    String materia = materie.get(i);
                    int ore = Integer.parseInt(ore_materie.get(i));

                    if (s.length() > 0) {
                        String[] classi = s.split("-");//classi
                        String VUOTO = "";
                        for (String classe : classi) {
                            classe = classe.trim().replace(" ", "").replace("–", "");
                            if (classe.length() == 0) continue;
                            if (classe.length() != 2) {
                                throw new IllegalArgumentException("Classe errata: " + classe + " riga: " + r);
                            }

                            String oreCompito = "N";
                            if (
                                    (materia.equalsIgnoreCase("SCIENZE") && ore > 3) ||
                                            (materia.equalsIgnoreCase("MATEMATICA") && ore > 3) ||
                                            (materia.equalsIgnoreCase("ITALIANO") && ore > 3) ||
                                            (materia.equalsIgnoreCase("INGLESE") && ore >= 3) ||
                                            (materia.equalsIgnoreCase("LATINO") && ore >= 3)

                            ) {
                                oreCompito = "2";
                            }

                            String oreLaboratorio = "";
                            String nomeLaboratorio = "";
                            String nomeDocenteLaboratorio = "";

                            if (materia.equalsIgnoreCase("SCIENZE")) {
                                oreLaboratorio = "1";
                                nomeLaboratorio = "F32 CHIMICA";
                                nomeDocenteLaboratorio = nomeDocente;
                            }

                            if (materia.equalsIgnoreCase("FISICA")) {
                                oreLaboratorio = "1";
                                nomeLaboratorio = "A3 FISICA";
                                nomeDocenteLaboratorio = nomeDocente;
                            }

                            if (materia.equalsIgnoreCase("INFORMATICA")) {
                                oreLaboratorio = "2";
                                nomeLaboratorio = "A4 INFORMATICA";
                                nomeDocenteLaboratorio = nomeDocente;
                            }

                            if (materia.contains("Disegno e Storia")) {
                                materia = "Disegno e Arte";
                            }


                            if (materia.toUpperCase().contains("SCIENZE MOTORIE") && (

                                    classe.equalsIgnoreCase("2A")
                                            || classe.equalsIgnoreCase("2B")
                                            || classe.equalsIgnoreCase("1H")
                                            || classe.equalsIgnoreCase("1C")
                                            || classe.equalsIgnoreCase("1G")
                                            || classe.equalsIgnoreCase("4G")
                                            || classe.equalsIgnoreCase("2C")
                                            || classe.equalsIgnoreCase("3H")
                                            || classe.equalsIgnoreCase("2D")
                                            || classe.equalsIgnoreCase("1D")
                            )
                            ) {
                                sb.append(materia + "(T)\t" + nomeDocente + "\t" + classe + "\t" +
                                        ore / 2 + "\t" + oreCompito + "\t" + nomeDocenteLaboratorio + "\t" + oreLaboratorio + "\t" + nomeLaboratorio
                                );
                                sb.append("\n");
                                sb.append(materia + "\t" + nomeDocente + "\t" + classe + "\t" +
                                        ore / 2 + "\t" + oreCompito + "\t" + nomeDocenteLaboratorio + "\t" + oreLaboratorio + "\t" + nomeLaboratorio
                                );
                                sb.append("\n");
                            } else {
                                sb.append(materia + "\t" + nomeDocente + "\t" + classe + "\t" +
                                        ore + "\t" + oreCompito + "\t" + nomeDocenteLaboratorio + "\t" + oreLaboratorio + "\t" + nomeLaboratorio
                                );
                                sb.append("\n");
                            }

                        }
                    }
                }
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
