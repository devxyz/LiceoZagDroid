package it.gov.scuolesuperioridizagarolo.dada.bitorario.parser;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Locale;

/**
 * Created by stefano on 27/09/2017.
 */
public class ParserDisposizioni_DaFileDocenti_EXCEL {

    public static void parsingExtraDocenti(File fileExcel, BitOrarioGrigliaOrario orario) throws IOException {

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileExcel));

        //creating workbook instance that refers to .xls file
        XSSFWorkbook wb = new XSSFWorkbook(in);

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            XSSFSheet sheet = wb.getSheetAt(i);
            if (sheet.getSheetName().toLowerCase().startsWith("#skip")) {
                System.out.println("Sheet skipped: " + sheet.getSheetName());
                continue;
            }

            String nomeDocente = getCell(sheet, 1, 0).getStringCellValue().trim().toUpperCase();
            String tipo = getCell(sheet, 0, 0).getStringCellValue().trim().toUpperCase();
            if (nomeDocente.trim().length() == 0) {
                throw new IllegalArgumentException("Nome docente mancante in " + sheet.getSheetName());
            }

            if (tipo.equalsIgnoreCase("educazione civica") || tipo.equalsIgnoreCase("sostegno")) {
                //compresenze - ed civica
                for (int riga_ora = 3; riga_ora <= 10; riga_ora++) {
                    EOra ora = EOra.getByNumber(riga_ora - 2);//1 --> PRIMA
                    for (int colonna_giorno = 1; colonna_giorno <= 5; colonna_giorno++) {
                        EGiorno giorno = EGiorno.getByNumber_LUN_DOM(colonna_giorno);//1 --> LUN
                        XSSFCell cell = getCell(sheet, riga_ora, colonna_giorno);
                        String classeValue = cell.getStringCellValue();
                        if (classeValue != null && classeValue.trim().length() > 0) {
                            ClassData classe = ClassData.search(classeValue);
                            BitOrarioOraLezione lezione = orario.getLezioneInClasse(ora, giorno, classe);
                            if (lezione == null) {
                                throw new IllegalArgumentException("Lezione non trovata: " + ora + " " + giorno + " " + classe + " (sheet " + sheet.getSheetName() + ")");
                            }

                            BitOrarioOraLezione nuovaLezione = null;
                            if (tipo.equalsIgnoreCase("educazione civica")) {
                                nuovaLezione = lezione.clonaLezioneConAltroDocenteCompresente(nomeDocente, tipo);
                                //controlla se il docente aveva ora di disposizione (se no da errore)
                                BitOrarioOraLezione lezioneConDocente = orario.getLezioneConDocente(nomeDocente, giorno, ora);
                                if (lezioneConDocente == null || lezioneConDocente.getTipoLezione() != BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                                    throw new IllegalArgumentException("Manca ora di disposizione per " + ora + " " + giorno + " " + nomeDocente + "(sheet " + sheet.getSheetName() + ")");
                                }
                                //rimuove una lezione di disposizione
                                orario.removeLezione(lezioneConDocente);
                            }
                            if (tipo.equalsIgnoreCase("sostegno")) {
                                nuovaLezione = lezione.clonaLezioneConAltroDocenteSostegno(nomeDocente);
                            }
                            orario.removeLezione(lezione);
                            orario.addLezione(nuovaLezione);
                        }
                    }

                }


            } else {
                throw new IllegalArgumentException("Valore sconosciuto: " + tipo);
            }

        }
    }

    public static XSSFCell getCell(XSSFSheet sheet, int riga, int colonna) {
        XSSFRow row = sheet.getRow(riga);
        XSSFCell cell = row.getCell(colonna);
        return cell;
    }

}
