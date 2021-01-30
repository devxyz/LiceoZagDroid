package utility.gsuite.moduli;

import utility.gsuite.moduli.impl.*;

import java.io.File;

public class MainDownloadModuloFromCSVGoogle {

    static boolean FERIE = false;
    static boolean ALTRI_INCARICHI = false;
    static boolean PCTO = false;
    static boolean RESP_PROGETTI = false;


    static boolean FUNZ_STRUM = false;
    static boolean RELAZIONI_DISCIPLINE = true;
    static boolean PROGR_SVOLTI = true;


    public static void main(String[] args) throws Exception {

        final File root = new File("/Users/stefano/Downloads/moduli/");

        if (FERIE) {
            File file_csv = new File(root, "domanda-ferie.csv");
            File out = new File(root, "domanda-ferie");

            new DownloadModuloFromCSVGoogle_Ferie().download(file_csv, out);
        }

        if (ALTRI_INCARICHI) {
            File file_csv = new File(root, "altri-Incarichi.csv");
            File out = new File(root, "altri-Incarichi");

            new DownloadModuloFromCSVGoogle_AltriIncarichi().download(file_csv, out);
        }

        if (PCTO) {
            File file_csv = new File(root, "responsabili.-pcto.csv");
            File out = new File(root, "responsabili.-pcto");

            new DownloadModuloFromCSVGoogle_PCTO().download(file_csv, out);
        }

        if (RESP_PROGETTI) {
            File file_csv = new File(root, "responsabili-progetto.csv");
            File out = new File(root, "responsabili-progetto");

            new DownloadModuloFromCSVGoogle_ResponsabiliProgetto().download(file_csv, out);
        }

        if (FUNZ_STRUM) {
            File file_csv = new File(root, "funzioni-strumentali.csv");
            File out = new File(root, "funzioni-strumentali");

            new DownloadModuloFromCSVGoogle_FunzioniStrumentali().download(file_csv, out);
        }

        if (RELAZIONI_DISCIPLINE) {
            File file_csv = new File(root, "relazioni-finali.csv");
            File out = new File(root, "relazioni-finali");
            new DownloadModuloFromCSVGoogle_RelazioniDisciplinari().download(file_csv, out);
        }

        if (PROGR_SVOLTI) {
            File file_csv = new File(root, "programmi-svolti.csv");
            File out = new File(root, "programmi-svolti");
            new DownloadModuloFromCSVGoogle_ProgrammiSvolti().download(file_csv, out);
        }
    }

}
