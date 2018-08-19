package it.gov.scuolesuperioridizagarolo.dada.bitorario.parser;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 27/09/2017.
 */
public class ParserDisposizioniTXT {
    public static List<BitOrarioOraLezione> parsingDisposizioniDocenti(File fileDisposizioniDocenti) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileDisposizioniDocenti));
        String line;


        ParserOrarioAllocazioneAuleTXT.MatriceDinamicaStringhe orario = new ParserOrarioAllocazioneAuleTXT.MatriceDinamicaStringhe();
        int riga = 0;
        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();


        while ((line = in.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("=====")) {
                riga++;
                continue;
            }
            if (line.length() == 0)
                continue;
            if (line.startsWith("----"))
                continue;
            if (line.startsWith("Ora|"))
                continue;

            final String[] a = line.split("[|]+");
            for (int i = 0; i < a.length; i++) {
                orario.append(riga, i, a[i].trim() + "#");
            }
        }


        for (int rigaOra = 1; rigaOra < orario.getRowCount(); rigaOra++) {
            for (int colonnaGiorno = 2; colonnaGiorno < orario.getColumnCount(); colonnaGiorno++) {
                final EOra eOra = EOra.valuesOreDiLezione()[rigaOra - 1];
                final EGiorno eGiorno = EGiorno.valuesGiorniDiLezione()[colonnaGiorno - 2];

                final String s = orario.get(rigaOra, colonnaGiorno);
                final String[] split = s.split("#");
                for (String nomeDocente : split) {
                    nomeDocente = nomeDocente.trim();
                    if (nomeDocente.length() > 0) {
                        //      System.out.println(eOra.getProgressivOra() + " " + eSettimana + " "+nomeDocente);
                        ris.add( BitOrarioOraLezione.creaOraDisposizione(nomeDocente,  eOra, eGiorno));
                    }
                }

            }
        }

        //System.out.println(orario);

        return ris;
    }

}
