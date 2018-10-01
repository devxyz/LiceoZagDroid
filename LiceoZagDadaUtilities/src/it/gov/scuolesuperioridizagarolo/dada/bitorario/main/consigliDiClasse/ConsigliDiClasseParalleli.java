package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.consigliDiClasse;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by stefano on 24/09/2018.
 */
public class ConsigliDiClasseParalleli {
    public static void main(String[] args) throws IOException {
        final File folderInput = new File("/Users/stefano/Dropbox/Circolari Scolastiche Liceo/AS 2018.19/Orario Scolastico/orario/04-2018.10.01_2018.10.06");
        final BitOrarioGrigliaOrario o = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(folderInput);
        DocentiComuniCdC d = new DocentiComuniCdC(o);
        System.out.println(d);
        System.out.println("=================================");
        System.out.println();
        System.out.println("5F");
        System.out.println(o.getDocentiPerClasse(ClassData.CLASS_5F));
        System.out.println();
        System.out.println("2B");
        System.out.println(o.getDocentiPerClasse(ClassData.CLASS_2B));
        System.out.println(d.docentiInComune(ClassData.CLASS_2B, ClassData.CLASS_5F));
        System.out.println("=================================");


        int maxSize = 5;
        //final TreeSet<ClassData> classi = o.getClassi();
        final TreeSet<ClassData> classi = new TreeSet<>();
        classi.add(ClassData.CLASS_1F);
        classi.add(ClassData.CLASS_1H);
        classi.add(ClassData.CLASS_5F);
        classi.add(ClassData.CLASS_3B);
        classi.add(ClassData.CLASS_2H);
        classi.add(ClassData.CLASS_3E);
        classi.add(ClassData.CLASS_4D);
        classi.add(ClassData.CLASS_1E);
        classi.add(ClassData.CLASS_2D);
        classi.add(ClassData.CLASS_3C);
        classi.add(ClassData.CLASS_2F);
        classi.add(ClassData.CLASS_4E);
        classi.add(ClassData.CLASS_2B);
        classi.add(ClassData.CLASS_1A);
        classi.add(ClassData.CLASS_1B);
        classi.add(ClassData.CLASS_1C);
        classi.add(ClassData.CLASS_5C);
        classi.add(ClassData.CLASS_3D);
        classi.add(ClassData.CLASS_3F);
        classi.add(ClassData.CLASS_2A);

        Random r = new Random(0);

        ArrayList<Set<ClassData>> ris = null;
        for (int i = 0; i < 100000; i++) {
            final ArrayList<Set<ClassData>> ris2 = stampaCdCParalleliRandom(r, d, maxSize, classi);
            if (ris == null || ris.size() > ris2.size()) {
                ris = ris2;
            }
        }

        System.out.println("ASSEGNAZIONE MAX " + maxSize);
        for (Set<ClassData> ri : ris) {
            System.out.println(ri);
        }

        System.out.println();

    }

    private static ArrayList<Set<ClassData>> stampaCdCParalleliRandom(Random r, DocentiComuniCdC d, int maxSize, TreeSet<ClassData> classi) {
        //scambio casuale
        ArrayList<ClassData> cc = new ArrayList<>(classi);
        if (r != null) {
            for (int i = 0; i < classi.size() * 2; i++) {
                int i1 = r.nextInt(classi.size());
                int i2 = r.nextInt(classi.size());
                final ClassData v = cc.get(i1);
                cc.set(i1, cc.get(i2));
                cc.set(i2, v);
            }
        }


        ArrayList<Set<ClassData>> ris = new ArrayList<>();
        LinkedHashSet<ClassData> classiDaSistemare = new LinkedHashSet<>(cc);
        System.out.println("ANALISI DA " + classiDaSistemare);
        Set<ClassData> currentGroup = new TreeSet<>();
        while (classiDaSistemare.size() > 0) {

            //cerca una nuova classe compatibile se presente
            boolean trovato = false;
            for (Iterator<ClassData> iterator = classiDaSistemare.iterator(); iterator.hasNext(); ) {
                ClassData x = iterator.next();
                if (isCompatible(d, currentGroup, x)) {
                    currentGroup.add(x);
                    iterator.remove();
                    trovato = true;
                    break;
                }
            }

            if (!trovato || currentGroup.size() >= maxSize) {
                //System.out.println(currentGroup);
                ris.add(currentGroup);
                currentGroup = new TreeSet<>();
            }

        }
        if (currentGroup.size() > 0) {
            ris.add(currentGroup);
        }
        System.out.println(">>> slot " + ris.size());
        return ris;
    }


    private static boolean isCompatible(DocentiComuniCdC d, Set<ClassData> currentGroup, ClassData c) {
        for (ClassData x : currentGroup) {
            if (d.docentiInComune(x, c))
                return false;
        }
        return true;
    }
}
