package utility.prove_parallele_mat;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2019_20.VincoliSostituzioni_1920_nYY_TEST;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import utility.corso_sicurezza.ImportFromCSVSidiStudenti;
import utility.corso_sicurezza.StudenteSidi;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * sequenza studenti per prova parallela
 */
public class MainProveMatematica {
    static LinkedList<StudenteSidi> gruppoMaggiore(Map<Integer, LinkedList<StudenteSidi>> map, List<Integer> ultimoAnnoCorso) {
        int max = -1;
        int indexMax = -1;

        for (Map.Entry<Integer, LinkedList<StudenteSidi>> e : map.entrySet()) {
            if (ultimoAnnoCorso.contains(e.getKey())) continue;
            if (e.getValue().size() > max) {
                max = e.getValue().size();
                indexMax = e.getKey();
            }
        }
        if (indexMax < 0) return null;
        if (map.get(indexMax).size() == 0) return null;
        return map.get(indexMax);
    }

    public static void main(String[] args) throws IOException {
        {
            String root = "/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/prove_parallele_mat/";
            PdfDocument read = new PdfDocument(new PdfReader(new File(root, "1.pdf")));
            PdfDocument write = new PdfDocument(new PdfWriter(new File(root, "ris.pdf")).setSmartMode(true));
            final int numberOfPages = read.getNumberOfPages();

            read.copyPagesTo(1, read.getNumberOfPages(), write);

            PdfPage firstPage = write.getFirstPage();
            String prima_riga = "Prima riga";
            String seconda_riga = "Seconda riga";

            PdfCanvas canvas = new PdfCanvas(firstPage);
            canvas.setLineWidth(3);
            canvas.setFillColor(Color.LIGHT_GRAY);
            canvas.setStrokeColor(Color.RED);
            canvas.rectangle(20, 750, 550, 70).fillStroke();
            canvas.setColor(Color.BLACK, true);

            canvas.beginText().setFontAndSize(
                    PdfFontFactory.createFont(FontConstants.HELVETICA), 16)
                    .moveText(50, 790)
                    .showText(prima_riga)
                    .endText();


            canvas.beginText().setFontAndSize(
                    PdfFontFactory.createFont(FontConstants.HELVETICA), 16)
                    .moveText(50, 770)
                    .showText(seconda_riga)
                    .endText();


            read.close();
            write.close();


        }

        try {
            VincoliSostituzioni_1920_nYY_TEST.main(args);
        } catch (Throwable e) {
        }
        ;


        File f = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/prove_parallele_mat/EsportazioneDati_FAT_RMPS07701G_2019.csv");

        String[] DOCENTI_ora1 = new String[]{"CARABELLA S.", "CARLI A.", "CERRI M.", "COCCI O.", "CORTONI I.", "FABRONI A.", "GUGLIELMOTTI A.", "LATINI A.", "LIO L.", "MANCINI S.", "MANNA G.", "MARTELLI V.", "MATTOZZI M.", "PAGLIARI M.", "ROSSI C.", "TARABORRELLI F.", "TASSAN M.", "ZAPPIA M.", "ACCIAI I.", "BENEDETTI A.", "CENTURIONI S.", "CERULLO A.", "CORSI E.", "DEL SIGNORE L.", "DI ROSA E.", "MONEGO M.", "PANEPUCCIA A.", "PAONE M.", "PUCCI D.", "SUPINO S."};
        String[] DOCENTI_ora2 = new String[]{"CARABELLA S.", "CARLI A.", "CERRI M.", "COCCI O.", "CORTONI I.", "FABRONI A.", "GUGLIELMOTTI A.", "LATINI A.", "LIO L.", "MANCINI S.", "MANNA G.", "MARTELLI V.", "MATTOZZI M.", "PAGLIARI M.", "ROSSI C.", "TARABORRELLI F.", "TASSAN M.", "ZAPPIA M.", "ALESSANDRONI F.", "ANDREOZZI M.", "BIAZZO G.", "BIONDI M.", "CARONI C.", "CHIEDI C.", "DE ANGELIS G.", "DE SIMONE A.", "FUSANI I.", "GENTILE V.", "MARTINI P.", "MASELLA R.", "MATTETTI N.", "SGRO P.", "STECCA C."};


        ArrayList<StudenteSidi> studenti = new ArrayList<>();
        ImportFromCSVSidiStudenti.parseStudents(f, studenti);

        System.out.println("Studenti SIDI: " + studenti.size());
        Map<Integer, LinkedList<StudenteSidi>> map = new TreeMap<>();
        for (int i = 1; i <= 5; i++) {
            map.put(i, new LinkedList<>());
        }

        for (StudenteSidi s : studenti) {

            //ignora i trasferiti
            if (!s.StatoAlunno.equalsIgnoreCase("F"))
                continue;

            map.get(s.AnnoCorso).add(s);
        }
        //rimuove il quinto anno
        map.remove(5);

        //confonde gli alunni disordinando le classi
        Random r = new Random(10);
        for (LinkedList<StudenteSidi> gruppo : map.values()) {
            for (int i = 0; i < gruppo.size(); i++) {
                Collections.swap(gruppo, r.nextInt(gruppo.size()), r.nextInt(gruppo.size()));
            }
        }


        LinkedList<Integer> ultimoAnnoCorso = new LinkedList<>();
        int totStudenti = 0;
        for (LinkedList<StudenteSidi> st : map.values()) {
            totStudenti += st.size();
        }

        System.out.println("Studenti da assegnare:" + totStudenti);
        List<RoomData> room = new LinkedList<>();
        List<RoomData> aule = RoomData.filter(new RoomData.RoomDataFilter() {
            @Override
            public boolean accept(RoomData c) {
                return !c.isAulaFittizia() && !c.isAulaLaboratorioPalestra() && c.maxStudents > 20;
            }
        });

        int postiDisponibili = 0;
        for (RoomData roomData : aule) {
            postiDisponibili += roomData.maxStudents - 1;//lascia un posto libero per aula
        }

        //ordina in senso decrescente di capienza
        aule.sort(Comparator
                .comparing(RoomData::getMaxStudents, Comparator
                        .nullsFirst(Integer::compareTo)).reversed()
                .thenComparing(RoomData::getRoomName));

        System.out.println("AULE INDIVIDUATE: " + aule);
        {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            for (int i = 0; i < Math.max(DOCENTI_ora1.length, DOCENTI_ora2.length); i++) {
                String a = "DISPOSIZIONE";
                if (aule.size() > i) {
                    a = aule.get(i).roomName;
                }
                if (DOCENTI_ora1.length > i) {
                    sb1.append(a + "\t" + DOCENTI_ora1[i]).append("\n");
                }
                if (DOCENTI_ora2.length > i) {
                    sb2.append(a + "\t" + DOCENTI_ora2[i]).append("\n");
                }
            }
            System.out.println("SORVEGLIANZA SLOT 1");
            System.out.println(sb1);
            System.out.println("SORVEGLIANZA SLOT 2");
            System.out.println(sb2);
        }

        System.out.println("POSTI DISPONIBILI: " + postiDisponibili);
        if (postiDisponibili < totStudenti) {
            throw new IllegalArgumentException("Posti insufficienti");
        }
        LinkedList<RoomData> postiAulaDaUtilizzare = new LinkedList<>();
        for (RoomData a : aule) {
            for (int i = 0; i < a.maxStudents - 1; i++) {
                postiAulaDaUtilizzare.add(a);
            }
        }

        RoomData precRoom = null;
        int num = 1;
        while (totStudenti > 0) {

            //cerca il gruppo piu' numeroso e prende il primo student
            LinkedList<StudenteSidi> gruppoStudenti = gruppoMaggiore(map, ultimoAnnoCorso);
            if (gruppoStudenti == null) {
                throw new IllegalArgumentException("Gruppi terminati. Studenti da sistemare " + totStudenti);
            }

            StudenteSidi stud = gruppoStudenti.removeFirst();
            RoomData roomData = postiAulaDaUtilizzare.removeFirst();
            if (precRoom != null && roomData != precRoom) {
                System.out.println("===\t===\t===\t===\t===");
                num = 1;
            }


            precRoom = roomData;
            System.out.println(num + "\t" + stud.AnnoCorso + "" + stud.Sezione + "\t" + stud.Cognome + "\t" + stud.Nome + "\t" + roomData.roomName);

            ultimoAnnoCorso.add(stud.AnnoCorso);
            while (ultimoAnnoCorso.size() > 2) {
                ultimoAnnoCorso.removeFirst();
            }

            num++;
            totStudenti--;
        }

    }

}
