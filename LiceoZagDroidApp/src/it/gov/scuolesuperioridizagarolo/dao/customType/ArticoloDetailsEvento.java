package it.gov.scuolesuperioridizagarolo.dao.customType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloDetailsEvento extends ArticoloDetails {

    public Date dataEvento;
    public String oggetto;

    public ArticoloDetailsEvento(Date dataAvviso, String oggetto) {
        this.dataEvento = dataAvviso;

        this.oggetto = oggetto;
    }

    public ArticoloDetailsEvento() {
        oggetto = "";
    }

    public void parseDataEvento(String s) {
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
            dataEvento = f.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString() {
        return "ArticoloTypeAvviso{" +
                "dataAvviso=" + dataEvento +
                ", oggetto='" + oggetto + '\'' +
                '}';
    }

    public void appendOggetto(String s) {
        if (s == null) return;
        oggetto = (oggetto + " " + s).trim();
    }
}
