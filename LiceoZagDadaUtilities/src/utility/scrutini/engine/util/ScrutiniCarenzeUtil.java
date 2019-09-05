package utility.scrutini.engine.util;

import com.itextpdf.kernel.geom.LineSegment;
import com.itextpdf.kernel.geom.Vector;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import utility.scrutini.engine.data.ScrutiniCarenze_Termine;
import utility.scrutini.engine.data.ScrutiniCarenze_TerminiPerPagina;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrutiniCarenzeUtil {
    public static double getAvgX(TextRenderInfo l) {
        LineSegment a = l.getAscentLine();
        LineSegment d = l.getDescentLine();
        Vector p1 = a.getStartPoint();
        Vector p2 = a.getEndPoint();
        Vector p3 = d.getStartPoint();
        Vector p4 = d.getEndPoint();
        return (getX(p1) + getX(p2) + getX(p3) + getX(p4)) / 4.;
    }

    public static double getAvgY(TextRenderInfo l) {
        LineSegment a = l.getAscentLine();
        LineSegment d = l.getDescentLine();
        Vector p1 = a.getStartPoint();
        Vector p2 = a.getEndPoint();
        Vector p3 = d.getStartPoint();
        Vector p4 = d.getEndPoint();
        return (getY(p1) + getY(p2) + getY(p3) + getY(p4)) / 4.;
    }

    public static List<ScrutiniCarenze_TerminiPerPagina> extractParoleByRow(File f) throws IOException {
        return extractParoleByRow(f, -1, -1);
    }

    public static List<ScrutiniCarenze_TerminiPerPagina> extractParoleByRow(File f, double minDistanzaMerge, double maxAltezzaMinRigheY) throws IOException {
        PdfReader reader = new PdfReader(f);
        PdfDocument p = new PdfDocument(reader);
        final int numberOfPages = p.getNumberOfPages();

        List<ScrutiniCarenze_TerminiPerPagina> ris = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i = i + 1) {
            final PdfPage p1 = p.getPage(i);
            ScrutiniCarenze_TextExtractionByRow strategy = new ScrutiniCarenze_TextExtractionByRow();
            PdfTextExtractor.getTextFromPage(p1, strategy);
            ArrayList<ScrutiniCarenze_Termine> parole = strategy.getParole();

            ArrayList<ArrayList<ScrutiniCarenze_Termine>> termini;
            if (minDistanzaMerge > 0 && maxAltezzaMinRigheY > 0)
                termini = ScrutiniCarenze_MergeTermini.disponiInGriglia(parole, minDistanzaMerge, maxAltezzaMinRigheY);
            else
                termini = ScrutiniCarenze_MergeTermini.disponiInGriglia(parole);
            ris.add(new ScrutiniCarenze_TerminiPerPagina(i, termini));
        }
        return ris;
    }


    public static ScrutiniCarenze_Termine _cercaTermineColonnaPiuVicina(ArrayList<ScrutiniCarenze_Termine> riga, ScrutiniCarenze_Termine termine) {
        if (riga.size() == 0) return null;
        ScrutiniCarenze_Termine minimo = riga.get(0);
        for (ScrutiniCarenze_Termine x : riga) {
            if (Math.abs(x.avgX - termine.avgX) < Math.abs(minimo.avgX - termine.avgX)) {
                minimo = x;
            }
        }
        return minimo;
    }

    public static int cercaRigaCheIniziaPer(ArrayList<ArrayList<ScrutiniCarenze_Termine>> termini, String t) {
        for (int i = 0; i < termini.size(); i++) {
            ArrayList<ScrutiniCarenze_Termine> riga = termini.get(i);
            if (riga.size() > 0 && riga.get(0).text.equalsIgnoreCase(t))
                return i;
        }
        return -1;
    }

    public static void stampaTabellaTermini(ArrayList<ArrayList<ScrutiniCarenze_Termine>> xx) {
        int r = 0;
        for (ArrayList<ScrutiniCarenze_Termine> riga : xx) {
            r++;
            System.out.print(r + " ## ");
            for (ScrutiniCarenze_Termine termine : riga) {
                if (termine.text.length() == 0)
                    System.out.print("*|\t");
                else
                    System.out.print(termine.text + "|\t");
            }

            System.out.println();
        }
    }

    public static double getX(Vector v) {
        return v.get(Vector.I2);
    }

    public static double getY(Vector v) {
        return v.get(Vector.I1);
    }

    private static double getZ(Vector v) {
        return v.get(Vector.I3);
    }

    public static void printDebugLine(TextRenderInfo l) {
        LineSegment a = l.getAscentLine();
        LineSegment d = l.getDescentLine();
        Vector p1 = a.getStartPoint();
        Vector p2 = a.getEndPoint();
        Vector p3 = d.getStartPoint();
        Vector p4 = d.getEndPoint();
        System.out.println("TEXT:" + l.getText() +
                "\n  P1_xy= " + Math.round(getX(p1)) + "\t" + Math.round(getY(p1)) +
                "\n  P2_xy= " + Math.round(getX(p2)) + "\t" + Math.round(getY(p2)) +
                "\n  P3_xy= " + Math.round(getX(p3)) + "\t" + Math.round(getY(p3)) +
                "\n  P4_xy= " + Math.round(getX(p4)) + "\t" + Math.round(getY(p4))
        );

    }
}
