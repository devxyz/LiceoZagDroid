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
