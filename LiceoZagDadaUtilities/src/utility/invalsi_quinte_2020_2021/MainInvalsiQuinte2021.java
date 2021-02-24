package utility.invalsi_quinte_2020_2021;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class MainInvalsiQuinte2021 {
    public static void main(String[] args) throws IOException {
        File root = new File("/Users/stefano/Google Drive (stefano.millozzi@scuolesuperioridizagarolo.edu.it)/invalsi");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (!pathname.getName().toLowerCase().endsWith(".pdf")) return false;
                if (!pathname.getName().toLowerCase().startsWith("elenco_credenziali_")) return false;
                return pathname.isFile();
            }
        });

        for (File file : files) {
            System.out.println(file);
            PdfReader pdfReader = new PdfReader(file.getAbsolutePath());
            int pages = pdfReader.getNumberOfPages();
            System.out.println("Pagine " + pages);
            for (int j = 0; j < pages; j++) {
                TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
                String textFromPage = PdfTextExtractor.getTextFromPage(pdfReader, j + 1, strategy);
                System.out.println(textFromPage);
            }
        }

    }
}
