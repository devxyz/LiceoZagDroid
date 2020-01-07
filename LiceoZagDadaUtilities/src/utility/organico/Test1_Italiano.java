package utility.organico;

import java.util.LinkedList;

public class Test1_Italiano {
    public static void main(String[] args) {
        //Materia2019_2020 materia = Materia2019_2020.A026_MATEMATICA;

        for (Materia2019_2020 materia : Materia2019_2020.values()) {
            materia = Materia2019_2020.A011_LETTERE_LATINO;

            System.out.println("=============================================================");
            System.out.println("TEST MATERIA " + materia);
            System.out.println("=============================================================");
            ClasseOrdinamento2019_2020 c = new ClasseOrdinamento2019_2020(MonteOre2019_2020.oreList(materia));
            Classi2019_2020[] values = Classi2019_2020.values();
            int numClasse = 1;
            for (Classi2019_2020 cc : values) {
                LinkedList<MateriaOraria2019_2020> mmtt = c.materiePerClasse(cc);
                int tot = 0;
                for (MateriaOraria2019_2020 x : mmtt) {
                    tot += x.ore;
                }

                if (numClasse != cc.classe() && cc.ordinamento != Ordinamento2019_2020.IPIA) {
                    numClasse = cc.classe();
//                    System.out.println();
                }
                System.out.println(cc + "(" + tot + ")\t"+mmtt);
            }
            System.out.println();
            System.out.println("=============================================================");
            //System.out.println(c);
            //System.out.println("========================");
            c.compute(materia);
            System.out.println("============================");
            System.out.println("Totale ore da assegnare: " + c.totaliOre());
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();

            if (true) break;
        }


    }
}
