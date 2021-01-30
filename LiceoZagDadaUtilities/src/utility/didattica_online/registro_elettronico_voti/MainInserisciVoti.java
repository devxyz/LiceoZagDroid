package utility.didattica_online.registro_elettronico_voti;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainInserisciVoti {
    private static class MyWindow extends JFrame {
        public MyWindow() throws HeadlessException {
            setUndecorated(true);
            getContentPane().setBackground(Color.red);
            setAlwaysOnTop(true);
            setSize(50, 50);
            setLocation(0, 0);
            setVisible(true);
        }
    }

    static String getValueAsString(Cell c) {
        if (c == null) return null;
        CellType cellType = c.getCellType();
        switch (cellType) {
            case _NONE:
                return null;
            case BLANK:
                return null;
            case BOOLEAN:
                return c.getBooleanCellValue() + "";
            case ERROR:
                return null;
            case FORMULA:
                return c.getCellFormula();
            case NUMERIC:
                return "" + c.getNumericCellValue();
            case STRING:
                return c.getStringCellValue();
            default:
                return null;
        }
    }

    public static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/data/csv_registro");

    public void InserimentoVoti(File... nomiFileExcel) throws Exception {
        MyWindow w = new MyWindow();
        WebDriver driver;
        Map<String, Object> vars;
        JavascriptExecutor js;
        System.setProperty("webdriver.chrome.driver", "/Users/stefano/DATA-EXT/seleniumdriver/chromedriver");

        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

        driver.get("https://www.sissiweb.it/SwStart.aspx?Customer_ID=93015960581");

        JOptionPane.showMessageDialog(w, "Effettuo LOGIN?");
        driver.findElement(By.id("txtUser")).sendKeys("7245");
        driver.findElement(By.id("txtPassword")).sendKeys("jueePMn6wLZyGaht");
        driver.findElement(By.id("btnLogin")).click();
        driver.manage().window().setSize(new Dimension(1851, 1177));

        for (File nomeFileExcel : nomiFileExcel) {
            FileInputStream fis = new FileInputStream(nomeFileExcel); // Finds the workbook instance for XLSX file XSSFWorkbook myWorkBook = new XSSFWorkbook (fis); // Return first sheet from the XLSX workbook XSSFSheet mySheet = myWorkBook.getSheetAt(0); // Get iterator to all the rows in current sheet Iterator<Row> rowIterator = mySheet.iterator(); // Traversing over each row of XLSX file while (rowIterator.hasNext()) { Row row = rowIterator.next(); // For each row, iterate through each columns Iterator<Cell> cellIterator = row.cellIterator(); while (cellIterator.hasNext()) { Cell cell = cellIterator.next(); switch (cell.getCellType()) { case Cell.CELL_TYPE_STRING: System.out.print(cell.getStringCellValue() + "\t"); break; case Cell.CELL_TYPE_NUMERIC: System.out.print(cell.getNumericCellValue() + "\t"); break; case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t"); break; default : } } System.out.println(""); }
            // Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis); // Return first sheet from the XLSX workboo

            for (int sheetIndex = 0; sheetIndex < myWorkBook.getNumberOfSheets(); sheetIndex++) {

                XSSFSheet mySheet = myWorkBook.getSheetAt(sheetIndex); // Get iterator to all the rows in current sheet
                String message = "Accedere al registro docente,\n" +
                        "selezionare la classe e la data\nFILE/Sheet: " + nomeFileExcel.getName() + "@" + mySheet.getSheetName() + "\n" +
                        "quindi apri la scheda Inserimento Valutazioni per gli studenti da valutare \n" +
                        "premere SI per inserite / NO per saltare";
                int carica_i_dati = JOptionPane.showConfirmDialog(w, message, "Carica i dati", JOptionPane.YES_NO_OPTION);
                //0 == OK

                if (carica_i_dati != 0) {
                    JOptionPane.showMessageDialog(w, "SKIPPED");
                    System.out.println("SKIP");
                    continue;
                }

                driver.manage().window().setSize(new Dimension(1851, 1177));
                int i = -1;

                System.out.println("------------- AVVIO COMPILAZIONE ----------");
                Iterator<Row> rowIterator = mySheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (i == -1) {
                        i++;
                        continue;//salta prima riga
                    }


                    String num = getValueAsString(row.getCell(0));
                    String stud = getValueAsString(row.getCell(1));
                    String voto = getValueAsString(row.getCell(2));
                    ;
                    String commentoPrivato = getValueAsString(row.getCell(3));
                    ;
                    String commentoPubblico = getValueAsString(row.getCell(4));
                    ;

                    System.out.println(stud + "\t" + voto + "\t" + commentoPrivato + "\t" + commentoPubblico);

                    Formatter ff = new Formatter();
                    String id = ff.format("%03d", i).toString();
                    //driver.findElement(By.id("fsVoto-000")).click();
                    if (voto != null) {
                        driver.findElement(By.id("fsVoto-" + id)).click();
                        driver.findElement(By.id("fsVoto-" + id)).sendKeys(voto);
                        System.out.println("> VOTO: " + voto);
                    }
                    if (commentoPrivato != null) {
                        driver.findElement(By.id("fsCommPrv-" + id)).click();
                        driver.findElement(By.id("fsCommPrv-" + id)).sendKeys(commentoPrivato.replace("\\n", "\n"));
                        System.out.println("> PRIVATO: " + commentoPrivato);
                    }
                    if (commentoPubblico != null) {
                        driver.findElement(By.id("fsCommPub-" + id)).click();
                        driver.findElement(By.id("fsCommPub-" + id)).sendKeys(commentoPubblico.replace("\\n", "\n"));
                        System.out.println("> PUBBLICO: " + commentoPubblico);
                    }
                    i++;

                }
                JOptionPane.showMessageDialog(w, "Salva i dati e poi premi OK");
            }
            fis.close();

        }
        driver.quit();

    }

    public static void main(String[] args) {
        try {
            new MainInserisciVoti().InserimentoVoti(new File(root, "valutazioni-dicembre 2020.xlsx"));
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
