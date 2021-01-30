package utility.esami_stato_2019_20;

import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;

public class SplitPDFFile {

    /**
     * @param args
     */
    public static void main(String[] args) {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2019-20/esame-stato 201920/materiali/materiali Informatica/materiali 5D/");
        try {
            File[] files = root.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".pdf");
                }
            });


            for (File file : files) {
                PdfReader pdfReader = new PdfReader(file.getAbsolutePath());
                int pages = pdfReader.getNumberOfPages();
                System.out.println("Pagine " + pages);
                for (int j = 0; j < pages; j++) {
                    TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
                    String textFromPage = PdfTextExtractor.getTextFromPage(pdfReader, j + 1, strategy);
                    String x = textFromPage.replaceAll("“", "").replaceAll("'“'", "").replaceAll("‘", "").replaceAll("[ \t\n]+", "-").replaceAll("Informatica-", "").replaceAll("/", "-");
                    if (x.length() > 180) {
                        x = x.substring(0, 180);
                    }
                    //System.out.println(x);

                    File file1 = new File(new File(file.getParentFile(), "ris"), file.getName().replaceAll(".pdf", "_" + (j + 1)) + "_" + x + ".pdf");
                    System.out.println(file1.getAbsolutePath());

                    file1.getParentFile().mkdirs();
                    FileOutputStream os = new FileOutputStream(file1);
                    Document document = new Document(pdfReader.getPageSizeWithRotation(j + 1));


                    PdfCopy writer = new PdfSmartCopy(document, os);
                    PdfImportedPage importedPage = writer.getImportedPage(pdfReader, j + 1);
                    System.out.println(importedPage);


                    document.open();
                    writer.addPage(importedPage);
                    document.close();

                    writer.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* example :
            java SplitPDFFile d:\temp\x\tx.pdf

            Reading d:\temp\x\tx.pdf
            Number of pages : 3
            Writing d:\temp\x\tx-001.pdf
            Writing d:\temp\x\tx-002.pdf
            Writing d:\temp\x\tx-003.pdf
         */

    }
}