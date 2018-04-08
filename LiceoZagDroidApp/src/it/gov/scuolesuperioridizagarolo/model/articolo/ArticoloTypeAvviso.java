package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloTypeAvviso extends ArticoloType {

    public Date dataAvviso;
    public String oggetto;

    public ArticoloTypeAvviso(Date dataAvviso, String oggetto) {
        this.dataAvviso = dataAvviso;

        this.oggetto = oggetto;
    }

    public ArticoloTypeAvviso() {
        oggetto = "";
    }

    public static ArticoloTypeAvviso loadFrom(ArticoloDB db) {
        return ArticoloType.loadFrom(ArticoloTypeAvviso.class, db);
    }

    public void parseDataAvviso(String s) {
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
            dataAvviso = f.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString() {
        return "ArticoloTypeAvviso{" +
                "dataAvviso=" + dataAvviso +
                ", oggetto='" + oggetto + '\'' +
                '}';
    }

    public void appendOggetto(String s) {
        if (s == null) return;
        oggetto = (oggetto + " " + s).trim();
    }
}
