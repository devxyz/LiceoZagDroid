package utility.didattica_online.registro_elettronico_voti;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CreaFilesVuoti {
    static final String[] studenti = ("Bascandura Dennis Andrei\n" +
            "Basto Martina\n" +
            "Boccia Gabriele\n" +
            "Carbone Andrea\n" +
            "Carbone Matteo\n" +
            "Chiapparelli Paolo\n" +
            "Chiapparelli Simone\n" +
            "Cristofari Alessandro\n" +
            "Danaj Mateo\n" +
            "Esposito Alessio\n" +
            "Federici Gianluca\n" +
            "Foschi Francesco\n" +
            "Leone Francesca\n" +
            "Lucarelli Gabriele\n" +
            "Mammetti Filippo\n" +
            "Mancinotti Christian\n" +
            "Marcelli Alessandro\n" +
            "Michelessi Francesco\n" +
            "Mistura Diego\n" +
            "Nobile Luca\n" +
            "Orsi Angelica\n" +
            "Penna Isabel\n" +
            "Sabellico Giovanna\n" +
            "Scaramella Federico\n" +
            "Sgro' Lorenzo\n" +
            "Tamiro Federico\n" +
            "Zimpi Tommaso\n" +
            "Achitei Fabrizio\n" +
            "Aloe Luna\n" +
            "Andronic Marta\n" +
            "Careri Lorenzo\n" +
            "Filacchione Gabriele\n" +
            "Frattarola Rebecca\n" +
            "Gagliardi Mattia\n" +
            "Ienne Gianluigi\n" +
            "Maccaroni Daniele\n" +
            "Maier Tiziano\n" +
            "Mariani Azzurra\n" +
            "Marta Elena\n" +
            "Martini Nicolo'\n" +
            "Massimi Diego Alessio\n" +
            "Petrucci Sara\n" +
            "Piazzai Francesco\n" +
            "Piccirilli Daniele\n" +
            "Pizziconi Leonardo\n" +
            "Sbordone Andrey\n" +
            "Tololoi Claudio Markus\n" +
            "Trani Alessio\n" +
            "Zaratti Davide\n" +
            "Zarlenga Samuele\n" +
            "Aron Daniele\n" +
            "Attilia Desire'E\n" +
            "Bartalini Lorenzo\n" +
            "Celletti Cristina\n" +
            "Ciavardini Gianluca\n" +
            "Coccia Luca\n" +
            "Cristino Francesco Mattia\n" +
            "Dantimi Damiano\n" +
            "De Francesco Marco\n" +
            "Del Duca Gabriele\n" +
            "Fortunato Gabriele\n" +
            "Kozar Bartosz\n" +
            "Manca Rocco\n" +
            "Mancinelli Jacopo\n" +
            "Mastrantonio Valerio\n" +
            "Orlandi Tiziano\n" +
            "Popa Razvan\n" +
            "Pucci Alessandro\n" +
            "Rosicarelli Emanuele\n" +
            "Sarandrea Gabriele\n" +
            "Scacco Federico\n" +
            "Scarano Luca\n" +
            "Tolfa Benedetta\n" +
            "Trinchieri Luca\n" +
            "Vittucci Damiano\n" +
            "Auricchio Lorenzo\n" +
            "Craciun Matteo\n" +
            "DPietro Niccolo'\n" +
            "Doraci Zaklina\n" +
            "Evangelista Alessandro\n" +
            "Fiorentini Giorgia\n" +
            "Gentile Gabriel\n" +
            "Lullo Valerio\n" +
            "Mecani Ketlin\n" +
            "Mandolfo Michael\n" +
            "Monaco Mario\n" +
            "Parolari Kevin\n" +
            "Pasquazi Elena\n" +
            "Pinci Rachele\n" +
            "Popa Cristian Eduard\n" +
            "Sanita' Asia\n" +
            "Santoni Yuri\n" +
            "Stocco Matteo\n" +
            "Tango Lorenzo\n" +
            "Velletrani Andrea\n" +
            "Ascenzi Gianmarco\n" +
            "Beltrami Lorenzo\n" +
            "Bitsch Flaviano\n" +
            "Bonafede Giuseppe\n" +
            "Cicerchia Nicolas\n" +
            "Correnti Asia\n" +
            "Curzi Nicolo'\n" +
            "DAlessandro Antonio\n" +
            "Delle Fratte Flavio\n" +
            "Di Carlo Francesca\n" +
            "Donca Andrea Denisa\n" +
            "Ferracci Ludovica\n" +
            "Frusta Alessandro\n" +
            "Gaetano Davide\n" +
            "Gheorghiu Marco Davide\n" +
            "Giordano Matteo\n" +
            "Giuliani Federica\n" +
            "Glielmi Mattia\n" +
            "Ligori Paolo\n" +
            "Marignoli Mario\n" +
            "Mastrofini Valerio\n" +
            "Nicolaus Leonardo\n" +
            "Pop Maximilian Ioan\n" +
            "Romagnoli Nicolo'\n" +
            "Schioppa Matteo\n" +
            "Wehbe Latifa\n" +
            "Bernassola Gabriele\n" +
            "Cianfriglia Michele\n" +
            "Cipolletta Matteo\n" +
            "Colagrossi Iacopo\n" +
            "Fabiani Leonardo\n" +
            "Giovannini Francesca\n" +
            "Longo Beatrice\n" +
            "Marani Lorenzo\n" +
            "Mazzenga Nicolo'\n" +
            "Menasci' Giulia\n" +
            "Nardi Marco\n" +
            "Pucciarelli Matteo\n" +
            "Pulsoni Andrea\n" +
            "Scacco Giovanni\n" +
            "Schiavella Pierluca\n" +
            "Serra Lorenzo\n" +
            "Terenzi Paolo\n" +
            "Tomasi Daniele\n" +
            "Tulli Christian\n" +
            "Ugolini Alessio\n" +
            "Zippo Gabriele\n" +
            "Bravi Sofia\n" +
            "Callegari Luca\n" +
            "Coccia Luigi\n" +
            "Costea Andreea Alexandra\n" +
            "DiVito Andrea\n" +
            "Dodaro Manuel\n" +
            "Gallotti Fatima\n" +
            "Guidi Francesco\n" +
            "Hozoc Cristian Fabio\n" +
            "Igiri Lorenzo\n" +
            "Mecchia Eduardo\n" +
            "Paniccia Leonardo\n" +
            "Pasquazi Rucsandra Ioana\n" +
            "Petrassi Francesco\n" +
            "Romeo Salvatore\n" +
            "Sanna Ilaria\n" +
            "Tucci Matteo\n" +
            "Benegiano Denise\n" +
            "Campoli Francesco\n" +
            "Cesarotti Emma\n" +
            "Cipriani Marco\n" +
            "DelSignore Alessandro\n" +
            "DCesare Simone\n" +
            "Libianchi Chiara Maria\n" +
            "Lunati Edoardo\n" +
            "Mas Emanuelcarlo\n" +
            "Nestola Matteo\n" +
            "Nicolosi Gabriele\n" +
            "Pescetelli Alessandro\n" +
            "Ricci Alessia\n" +
            "Rossi Gemma\n" +
            "Scipioni Simone\n" +
            "Antonellini Federico\n" +
            "Boccia Valentina\n" +
            "Carrarini Jacopo\n" +
            "Cepoiu Rares Claudiu\n" +
            "Fadda Emanuele\n" +
            "Giacomobono Francesco\n" +
            "Giovannetti Simone\n" +
            "Granata Simone\n" +
            "Loreti Federica\n" +
            "Malito Alessia\n" +
            "Pancelli Valerio\n" +
            "Petriglia Andrea\n" +
            "Ruffa Marcus\n" +
            "Sorrenti Alessio\n" +
            "Sperandio Dario\n" +
            "Vania Nicolo'").split("[\n]+");

    static final String[] classi = ("1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "1b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2b\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "2d\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3b\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "3d\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4b\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "4d\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5b\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d\n" +
            "5d").split("[\n]+");

    public static void main(String[] args) throws IOException {
        TreeMap<String, Set<String>> studentiPerClasse = new TreeMap<>();

        for (int i = 0; i < studenti.length; i++) {
            String stud = studenti[i];
            String classe = classi[i];
            Set<String> studenti = studentiPerClasse.get(classe);
            if (studenti == null) {
                studenti = new TreeSet<>();
                studentiPerClasse.put(classe, studenti);
            }
            studenti.add(stud);
        }


        for (Map.Entry<String, Set<String>> e : studentiPerClasse.entrySet()) {

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("studenti");

            int rowCount = 0;

            {
                String[] intestazione = "N.\tnome\tvoto\tcommento privato\tcommento pubblico".split("\t");
                Row row = sheet.createRow(rowCount);
                int columnCount = 0;
                for (String s : intestazione) {
                    Cell cell = row.createCell(columnCount);
                    cell.setCellValue(s);
                    columnCount++;
                }
                rowCount++;
            }


            for (String stud : e.getValue()) {
                Row row = sheet.createRow(rowCount);
                Cell cell = row.createCell(0);
                cell.setCellValue(rowCount);

                cell = row.createCell(1);
                cell.setCellValue(stud);
                rowCount++;
            }


            FileOutputStream outputStream = new FileOutputStream(new File(MainInserisciVoti.root, e.getKey().toUpperCase() + ".xlsx"));
            workbook.write(outputStream);
            outputStream.close();


        }

    }
}
