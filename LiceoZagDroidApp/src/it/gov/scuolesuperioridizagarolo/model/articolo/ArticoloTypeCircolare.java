package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloTypeCircolare extends ArticoloType {
    public int numeroCircolare;
    public Date dataCircolare;
    public String oggetto;

    public ArticoloTypeCircolare(Date dataCircolare, int numeroCircolare, String oggetto) {
        this.dataCircolare = dataCircolare;
        this.numeroCircolare = numeroCircolare;
        this.oggetto = oggetto;
    }

    public ArticoloTypeCircolare() {
        oggetto = "";
    }

    public static ArticoloTypeCircolare loadFrom(ArticoloDB db) {
        return ArticoloType.loadFrom(ArticoloTypeCircolare.class, db);
    }

    public void parseNumeroCircolare(String s) {
        //rimuove caratteri indesiderati
        s = s.replaceAll("[.n \t\n]", "");
        numeroCircolare = Integer.parseInt(s);
    }

    public void parseDataCircolare(String s) {
        s = s.toUpperCase();
        String[] mesi = new String[]{"GENNAIO", "FEBBRAIO", "MARZO", "APRILE", "MAGGIO", "GIUGNO", "LUGLIO", "AGOSTO", "SETTEMBRE", "OTTOBRE", "NOVEMBRE", "DICEMBRE"};
        int mese = 1;
        for (String m : mesi) {
            s = s.replace(m, "/" + mese + "/");
        }
        s = s.replaceAll("[.]+", "/");
        s = s.replaceAll("[ ]+", "/");
        s = s.replaceAll("[/]+", "/");


        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataCircolare = f.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString() {
        return "ArticoloTypeCircolare{" +
                "dataCircolare=" + dataCircolare +
                ", numeroCircolare=" + numeroCircolare +
                ", oggetto='" + oggetto + '\'' +
                '}';
    }

    public void appendOggetto(String s) {
        if (s == null) return;
        oggetto = (oggetto + " " + s).trim();
    }
}
