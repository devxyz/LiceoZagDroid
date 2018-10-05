package it.gov.scuolesuperioridizagarolo.dao.customType;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stefano on 22/03/2018.
 */
public class ArticoloDetailsCircolare extends ArticoloDetails {
    public int numeroCircolare;
    public Date dataCircolare;
    public String oggetto;

    public ArticoloDetailsCircolare(Date dataCircolare, int numeroCircolare, String oggetto) {
        this.dataCircolare = dataCircolare;
        this.numeroCircolare = numeroCircolare;
        this.oggetto = oggetto;
    }

    public ArticoloDetailsCircolare() {
        oggetto = "";
    }


    public void check() throws IllegalArgumentException{
        if (dataCircolare==null)throw new IllegalArgumentException("Data circolare null");
        if (oggetto==null)throw new IllegalArgumentException("Oggetto null");
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
