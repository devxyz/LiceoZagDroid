package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.*;

public class OrarioDDIEngine_SettimaneAlterne {
    public static interface OrarioDDIEngine_SettimaneAlterne_DDIAssign {
        IClassDataDDI[] generate(BitOrarioGrigliaOrario orario, Random r);
    }

    public static class OrarioDDIEngine_SettimaneAlterne_DDIAssign_2settimane implements OrarioDDIEngine_SettimaneAlterne_DDIAssign {
        public IClassDataDDI[] generate(BitOrarioGrigliaOrario orario, Random r) {
            ClassDataDDI_SettimanaFissa c = new ClassDataDDI_SettimanaFissa();
            ArrayList<ClassData> classiNonPrime = new ArrayList<>(ClassData.filter(new ClassData.ClassDataFilter() {
                @Override
                public boolean accept(ClassData c) {
                    return c._class > 1;
                }
            }));
            ArrayList<ClassData> classiPrime = new ArrayList<>(ClassData.filter(new ClassData.ClassDataFilter() {
                @Override
                public boolean accept(ClassData c) {
                    return c._class == 1;
                }
            }));
            //mescola la sequenza e prende metà delle classi in presenza e metà in ddi
            Collections.shuffle(classiNonPrime, r);

            c.fisse.addAll(classiPrime);

            int meta = classiNonPrime.size() / 2;
            for (int i = 0, classiSize = classiNonPrime.size(); i < classiSize; i++) {
                ClassData c2 = classiNonPrime.get(i);
                if (c.ddi.size() < meta) {
                    c.ddi.add(c2);
                } else {
                    c.presenza.add(c2);
                }
            }
            return new IClassDataDDI[]{c, c.inverso()};
        }
    }

    public static class OrarioDDIEngine_SettimaneAlterne_DDIAssign_4settimane implements OrarioDDIEngine_SettimaneAlterne_DDIAssign {
        public IClassDataDDI[] generate(BitOrarioGrigliaOrario orario, Random r) {
            ClassDataDDI_SettimanaFissa c = new ClassDataDDI_SettimanaFissa();

            ArrayList<ClassData> classi = new ArrayList<>(ClassData.filter(new ClassData.ClassDataFilter() {
                @Override
                public boolean accept(ClassData c) {
                    return c._class >= 0;
                }
            }));

            //prepara le 4 settimane
            ClassDataDDI_SettimanaFissa[] ripartizioneClassiPerSettimana = new ClassDataDDI_SettimanaFissa[4];
            for (int i = 0; i <= 3; i++) {
                ripartizioneClassiPerSettimana[i] = (new ClassDataDDI_SettimanaFissa());
            }


            //mescola la sequenza e prende metà delle classi in presenza e metà in ddi
            Collections.shuffle(classi, r);

            //aggiunge una classe in ddi a settimana
            int i = 0;
            for (ClassData cl : classi) {
                ripartizioneClassiPerSettimana[i].presenza.add(cl);
                i++;
                if (i >= ripartizioneClassiPerSettimana.length) i = 0;
            }


            //riempie le classi a distanza con quelle non in presenza
            for (ClassDataDDI_SettimanaFissa rip : ripartizioneClassiPerSettimana) {
                rip.ddi.addAll(Arrays.asList(ClassData.values()));
                rip.ddi.removeAll(rip.presenza);
            }

            return ripartizioneClassiPerSettimana;
        }
    }

    public static IClassDataDDI[] dosomething(BitOrarioGrigliaOrario orario, int numCicli, OrarioDDIEngine_SettimaneAlterne_DDIAssign generatore) {
        Random r = new Random(13);
        OrarioDocentiComplessivo o = new OrarioDocentiComplessivo();
        o.addAll(orario.getLezioni());

        int best_docentiInFullDDI = 0;
        IClassDataDDI[] best_ddi = null;

        for (int i = 0; i < numCicli; i++) {
            System.out.println(i + ") ===============================");
            IClassDataDDI[] ddiSettimane = generatore.generate(orario, r);
            //generaSettimanaFissa_SettimanaAlterna(r);

            //conta i giorni interi in ddi ed inversa (1 settimana + 1 settimana)
            int docentiInFullDDI = 0;
            for (IClassDataDDI ss : ddiSettimane) {
                o.assignDDI(ss);
                docentiInFullDDI += o.getGiorniFullDDITotali();
            }


            System.out.println(" > " + docentiInFullDDI);
            if (docentiInFullDDI > best_docentiInFullDDI) {
                best_docentiInFullDDI = docentiInFullDDI;
                best_ddi = ddiSettimane;
            }
        }

        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("BEST DDI: " + best_docentiInFullDDI);


        for (int i = 0; i < best_ddi.length; i++) {

            o.assignDDI(best_ddi[i]);
            ClassDataDDI_SettimanaFissa bdd = (ClassDataDDI_SettimanaFissa) best_ddi[i];
            System.out.println("=========== SETTIMANA " + (i + 1) + " =============");
            System.out.println("Classi a scuola: " + bdd.presenza);

            MatriceGiorniOre matriceGiorniOreDaCasa_sett1 = o.computeDDI_daCasa();
            MatriceGiorniOre matriceGiorniOreDaScuola_sett1 = o.computeDDI_daScuola();

            System.out.println("LEZIONI DDI da casa - SETTIMANA " + (i + 1));
            System.out.println(matriceGiorniOreDaCasa_sett1);
            System.out.println();

            System.out.println("LEZIONI DDI da scuola - SETTIMANA " + (i + 1));
            System.out.println(matriceGiorniOreDaScuola_sett1);
            System.out.println();

            System.out.println("DISPOSIZIONI DA SPOSTARE SETTIMANA " + (i + 1));
            System.out.println(o.computeReportDisposizioniDaSpostare(new OrarioDocentiComplessivo_SkipAlertReportDisposizioni(), false));
        }

        return best_ddi;
    }
}
