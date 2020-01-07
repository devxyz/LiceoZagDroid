package it.gov.scuolesuperioridizagarolo.dada.bitorario.main;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.BitOrarioOraLezioneJSonConverter;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.output.*;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.parser.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by stefano on 25/09/2017.
 */
public class MainParserGeneraStampeOrario {
    @Deprecated
    public static final String DEBUG_FOLDER_INPUT = "/Users/stefano/Dropbox/Circolari Scolastiche Liceo/AS 2018.19/Orario Scolastico/orario";
    static final String file_allocazione_aule = "Orario Allocazione Aule.txt";
    static final String file_disposizione_docenti = "Orario Ore a Disposizione.txt";
    static final String file_disposizione_da_orario_verticale_opzional = "Orario Professori Verticale.txt";

    public static BitOrarioGrigliaOrario readJsonFileOrarioAuleClassi(File file) throws IOException {
        if (file.getName().toLowerCase().trim().endsWith(".zip")) {

            ZipInputStream in = new ZipInputStream(new FileInputStream(file));
            final ZipEntry nextEntry = in.getNextEntry();
            final BitOrarioGrigliaOrario s = BitOrarioOraLezioneJSonConverter.fromJSon(in);
            in.close();
            return s;


        } else {
            final FileInputStream s1 = new FileInputStream(file);
            final BitOrarioGrigliaOrario s = BitOrarioOraLezioneJSonConverter.fromJSon(s1);
            s1.close();
            return s;


        }

    }

    public static BitOrarioGrigliaOrario parsingDefaultFileOrarioAuleClassi(File folderInput) throws IOException {
        return parsingDefaultFileOrarioAuleClassi(folderInput, false);
    }

    @Deprecated
    public static BitOrarioGrigliaOrario parsingDefaultFileOrarioAuleClassi(File folderInput, boolean noAule) throws IOException {

        final String titolo = "Liceo Scientifico - Orario versione 0.1";
        final BitOrarioGrigliaOrario orarioTotale = parsingFileOrarioAuleClassi(
                titolo,
                new File(folderInput, file_allocazione_aule),
                new File(folderInput, file_disposizione_docenti),
                new File(folderInput, file_disposizione_da_orario_verticale_opzional),
                null,
                null, noAule
        );
        return orarioTotale;
    }

    public static BitOrarioGrigliaOrario parsingFileOrarioAuleClassi(String titolo,
                                                                     File fileAllocazioneAule, File file_disposizione_docenti,
                                                                     File file_disposizione_da_orario_orizzontale_opzional,
                                                                     File fileSostegnoOrizzontale,
                                                                     File file_progettiOrizzontale, boolean noAule) throws IOException {

        if (!fileAllocazioneAule.exists()) {
            throw new IllegalArgumentException("File orario " + fileAllocazioneAule.getName() + " non trovato");
        }
        /*
        if (file_disposizione_da_orario_orizzontale_opzional==null||!file_disposizione_da_orario_orizzontale_opzional.exists() && !file_disposizione_docenti.exists()) {
            throw new IllegalArgumentException("Nessun file disposizioni trovato");
        }*/

        if (file_disposizione_da_orario_orizzontale_opzional != null && file_disposizione_docenti != null) {
            throw new IllegalArgumentException("Troppi file disposizioni trovati sia in " + file_disposizione_da_orario_orizzontale_opzional.getName() + " che in " + file_disposizione_docenti.getName());
        }

        final BitOrarioGrigliaOrario orarioTotale = new BitOrarioGrigliaOrario(titolo);

        final List<BitOrarioOraLezione> l1 = ParserOrarioAllocazioneAuleTXT.parsingFileOrarioAuleClassi(fileAllocazioneAule, noAule);
        orarioTotale.addLezione(l1);


        if (file_disposizione_docenti != null) {
            final List<BitOrarioOraLezione> l = ParserDisposizioni_DaFileGrigliaTXT.parsingDisposizioniDocenti(file_disposizione_docenti);
            orarioTotale.addLezione(l);
        }

        if (file_disposizione_da_orario_orizzontale_opzional != null) {
            final List<BitOrarioOraLezione> l = ParserDisposizioni_DaFileOrizzontaleTXT.parsingDisposizioniDocenti(file_disposizione_da_orario_orizzontale_opzional);
            orarioTotale.addLezione(l);
        }

        if (fileSostegnoOrizzontale != null) {
            ParserSostegno_DaFileOrizzontaleTXT.parsingOreSostegnoDocenti(fileSostegnoOrizzontale, orarioTotale);
        }


        if (file_progettiOrizzontale != null) {
            final List<BitOrarioOraLezione> l = ParserProgetto_DaFileOrizzontaleTXT.parsingProgettoDocenti(file_progettiOrizzontale);
            orarioTotale.addLezione(l);
        }

        return orarioTotale;
    }


    public static void main(String[] args) throws IOException {


        final BitOrarioGrigliaOrario orarioTotale = parsingDefaultFileOrarioAuleClassi(new File(DEBUG_FOLDER_INPUT), true);

        System.out.println(orarioTotale.getClassi());

        final BitOrarioOraLezione lezioneInClasse = orarioTotale.getLezioneInClasse(EOra.QUINTA, EGiorno.VENERDI, ClassData.CLASS_3D);
        System.out.println(lezioneInClasse);


        //System.out.println(orarioTotale.toStringInClasse("3A"));

        generaFile(orarioTotale, new File(DEBUG_FOLDER_INPUT));
    }

    public static void generaFile(BitOrarioGrigliaOrario orarioTotale, File FOLDER_INPUT) throws IOException {
        File folderOutput = new File(FOLDER_INPUT, orarioTotale.getTitolo().replaceAll("[ ]+", "_"));
        folderOutput.mkdirs();

        NoteVariazioniBitOrarioGrigliaOrario note = new NoteVariazioniBitOrarioGrigliaOrario();
        new HtmlOutputOrario_perClassi().print(orarioTotale, note, new File(folderOutput,
                "FORMAT_orario_CLASSI__A3_portrait.html"), EPaperFormat.A3);
        new HtmlOutputOrario_perClassiSingole().print(orarioTotale, note, new File(folderOutput,
                "FORMAT_orario_CLASSI__A4_1xfoglio_landscape.html"), EPaperFormat.A3);
        new HtmlOutputOrario_perDocenti().print(orarioTotale, note, new File(folderOutput,
                "FORMAT_orario_DOCENTI__A3_portrait.html"), EPaperFormat.A3);
        new HtmlOutputOrario_perAule().print(orarioTotale, note, new File(folderOutput,
                "FORMAT_orario_AULE__A4_landscape.html"), EPaperFormat.A4);
        new Report_perDocentiRidottoDettagliato().print(orarioTotale, new File(folderOutput,
                "FORMAT_orario_DOCENTI_RIDOTTO__A3_portrait.html"), EPaperFormat.A3);
        new Report_perDocentiRidotto().print(orarioTotale, note, new File(folderOutput,
                "FORMAT_orario_DOCENTI_AULE_RIDOTTO__A3_portrait.html"), EPaperFormat.A3);
        new Report_perAuleVuote().print(orarioTotale, new File(folderOutput,
                "FORMAT_aule_vuote.html"));
        new HtmlOutputOrario_perSpostamentiAule().print(orarioTotale, note, new File(folderOutput,
                "FORMAT_cambi_aula.html"), EPaperFormat.A3);
        new HtmlOutputOrario_perDisposizioni().print(orarioTotale, note, new File(folderOutput,
                "FORMAT_DISPOSIZIONI__A4_landscape.html"), EPaperFormat.A3);
        new HtmlOutputOrario_perAule_con_responsabili().print(orarioTotale, new File(folderOutput,
                "FORMAT_orario_AULE_con_responsabili__A4_landscape.html"));
        new Report_perOccupazioneAule().print(orarioTotale, new File(folderOutput,
                "FORMAT_disponibila_posti_aule.html"));
    }


}
