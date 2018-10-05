package it.gov.scuolesuperioridizagarolo.dao.customType;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloDetailsAvviso extends ArticoloDetails {

    public Date dataAvviso;
    public String oggetto;

    public ArticoloDetailsAvviso(Date dataAvviso, String oggetto) {
        this.dataAvviso = dataAvviso;

        this.oggetto = oggetto;
    }

    public void check() throws IllegalArgumentException{
        if (dataAvviso==null)throw new IllegalArgumentException("Data avviso null");
        if (oggetto==null)throw new IllegalArgumentException("Oggetto null");
    }

    public ArticoloDetailsAvviso() {
        oggetto = "";
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
