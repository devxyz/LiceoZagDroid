package it.gov.scuolesuperioridizagarolo.dada.bitorario.parser;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stefano on 27/09/2017.
 */
public class ParserDisposizioni_DaFileOrarioGlobaleCSV {
    public static List<BitOrarioOraLezione> parsingDisposizioniDocenti(File fileDisposizioniDocenti) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileDisposizioniDocenti));
        String line;
        //usa; come separatore, no apici
        
        /*
Docente           ;Lu ;Lu ;Lu ;Lu ;Lu ;Ma ;Ma ;Ma ;Ma ;Ma ;Me ;Me ;Me ;Me ;Me ;Gi ;Gi ;Gi ;Gi ;Gi ;Ve ;Ve ;Ve ;Ve ;Ve ;
                    ; 1 ; 2 ; 3 ; 4 ; 5 ; 1 ; 2 ; 3 ; 4 ; 5 ; 1 ; 2 ; 3 ; 4 ; 5 ; 1 ; 2 ; 3 ; 4 ; 5 ; 1 ; 2 ; 3 ; 4 ; 5 ;
A027_POTENZ>SUPPL   ;3B ;3B ;1E ;   ;   ;   ;   ;   ;   ;   ;3B ;   ;3B ;3B ;1E ;   ;   ;   ;   ;   ;   ;   ;   ; D ;3B ;
ACCIAI              ;5G ;5G ;   ;2B ;2B ;   ;   ;2B ;2B ;2G ;   ;   ;2G ;5G ;5G ;2B ; D ;2G ;2G ;2G ;2B ;2G ;2B ;   ;   ;
ALESSANDRONI        ;   ;   ;2E ;2H ;1E ;2H ;1E ;2E ;   ;   ;   ;1E ; D ;2E ;2H ;1E ;1E ;   ;2H ;2E ;1E ;   ;1E ;2E ;2E ;
ALIBRANDI           ;1F ;1F ;   ;1C ;2F ;1F ;2F ;4F ; D ;   ;   ;   ;2F ;4F ;1F ;4F ;   ;1F ;1C ;1F ;1C ;2F ;1F ;   ;   ;
ANDREOZZI           ;   ; D ; D ;1D ;2H ;1D ; D ;2G ;   ;   ;2G ;1F ; D ;2H ;2F ; D ; D ;   ;   ;   ;2F ; D ;   ;1F ; D ;

        * */

        //prima riga giorni settimana
        final ArrayList<EGiorno> giorni = new ArrayList<>();
        {
            line = in.readLine();
            String[] giorniArray = line.split(";");
            for (int i = 1; i < giorniArray.length; i++) {
                String s = giorniArray[i];
                if (s.trim().length() == 0) continue;
                switch (s.trim().toUpperCase()) {
                    case "LU":
                    case "LUN":
                        giorni.add(EGiorno.LUNEDI);
                        break;
                    case "MA":
                    case "MAR":
                        giorni.add(EGiorno.MARTEDI);
                        break;
                    case "ME":
                    case "MER":
                        giorni.add(EGiorno.MERCOLEDI);
                        break;
                    case "GI":
                    case "GIO":
                        giorni.add(EGiorno.GIOVEDI);
                        break;
                    case "VE":
                    case "VEN":
                        giorni.add(EGiorno.VENERDI);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid data:" + s + " - " + Arrays.toString(giorniArray));
                }
            }
        }

        //ore
        final ArrayList<EOra> ore = new ArrayList<>();
        {
            line = in.readLine();
            String[] oreArray = line.split(";");
            for (int i = 1; i < oreArray.length; i++) {
                String s = oreArray[i];
                if (s.trim().length() == 0) continue;
                switch (s.trim().toUpperCase()) {
                    case "1":
                        ore.add(EOra.PRIMA);
                        break;
                    case "2":
                        ore.add(EOra.SECONDA);
                        break;
                    case "3":
                        ore.add(EOra.TERZA);
                        break;
                    case "4":
                        ore.add(EOra.QUARTA);
                        break;
                    case "5":
                        ore.add(EOra.QUINTA);
                        break;
                    case "6":
                        ore.add(EOra.SESTA);
                        break;
                    case "7":
                        ore.add(EOra.SETTIMA);
                        break;
                    case "8":
                        ore.add(EOra.OTTAVA);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid data:" + s + " - " + Arrays.toString(oreArray));
                }

            }
        }

        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        while ((line = in.readLine()) != null) {
            if (line.trim().length() == 0) continue;
            String[] row = line.split(";");
            String docente = row[0];
            for (int i = 1; i < row.length; i++) {
                String s = row[i];
                if (s.toUpperCase().trim().equals("D")) {
                    EOra ora = ore.get(i - 1);
                    EGiorno giorno = giorni.get(i - 1);
                    ris.add(BitOrarioOraLezione.creaOraDisposizione(docente, ora, giorno));
                }
            }

        }

        return ris;
    }

}
