package utility.esami_stato_2019_20;

import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;

import java.util.*;

public class CommissioniOrali {
    private static Sottocommissione sottoCommissione(String line) {
        String[] split = line.trim().toUpperCase().split("\t");
        Set<Integer> giorniDaScartare = new TreeSet<>();
        String[] giorni = split[0].split(",");
        for (String s : giorni) {
            if (s.trim().length() > 0) {
                giorniDaScartare.add(Integer.parseInt(s.trim()));
            }
        }

        String classe = (split[2]);
        String nomecommissione = (split[1]);
        int numStuddenti = Integer.parseInt(split[3]);
        int numGiorni = numStuddenti / 5 + (numStuddenti % 5 > 0 ? 1 : 0);
        Sottocommissione s = new Sottocommissione(classe, nomecommissione, numGiorni, giorniDaScartare);

        for (int i = 4; i < split.length; i++) {
            String commissario = split[i].trim();
            if (commissario.length() == 0) continue;
            s.commissari.add(commissario.trim().toUpperCase());

        }
        return s;
    }

    public static void main(String[] args) {
        String commissione1 = "5,12,19\tCOMMISSIONE A-C\tSEZ. A\t20\tMinotti\t\tMonardo\tPonza\t\tLatini\tMartelli\t\tDi Rosa\t\n" +
                "5,12,19\tCOMMISSIONE A-C\tSEZ. C\t17\tDe Simone\t\tMonardo\tBorrello\tFerrarese\tCentracchio\t\t\tMartini\t\n";

        String commissione2 = "5,12,19\tCOMMISSIONE E\tSEZ. E\t17\tChiedi\t\tPanepuccia\tCenturioni\t\tMonego\t\tCortoni\tDi Rosa\t\n";

        String commissione3 = "5,12,19\tCOMMISSIONE B-D\tSEZ. B\t15\tLiberati\tMillozzi\tGentile\tMarchese\t\tDel Signore\tSaccenti\t\t\t\n" +
                "5,12,19\tCOMMISSIONE B-D\tSEZ. D\t16\tMinotti\tMillozzi\tPucci\tVolpi\t\tDel Signore\tCarabella\t\t\t\n";
        String s = commissione1 + commissione2 + commissione3;
        String[] split = s.split("\n");
        ArrayList<Sottocommissione> commissioni = new ArrayList<>();
        for (String s1 : split) {
            if (s1.trim().length() == 0) continue;
            commissioni.add(sottoCommissione(s1));
        }

        System.out.println("***** Commissioni e componenti:");
        for (Sottocommissione x : commissioni) {
            System.out.println(x);
        }

        System.out.println("====================\n");
        System.out.println("***** Incompatibilità:");
        for (int i = 0; i < commissioni.size(); i++) {
            for (int j = i + 1; j < commissioni.size(); j++) {
                Sottocommissione c1 = commissioni.get(i);
                Sottocommissione c2 = commissioni.get(j);
                if (c1.conflitto(c2)) {
                    System.out.println(" > " + c1.classe + " " + c2.classe);
                }
            }
        }
        System.out.println("====================\n");
        System.out.println("***** Soluzioni:");


        Permutations pp = new Permutations();
        List<List<Sottocommissione>> permute = pp.permute(commissioni);

        TreeSet<String> riss = new TreeSet<>();


        int numGiorniTabella = 20;
        for (List<Sottocommissione> x : permute) {
            Calendar c = Calendar.getInstance();
            {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DAY_OF_MONTH, 17);
                c.set(Calendar.YEAR, 2020);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("-\t-\t");
            for (int i = 1; i <= numGiorniTabella; i++) {
                sb.append(C_DateUtil.toDDMM(c.getTime()) + "\t");
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
            sb.append("\n");


            ArrayList<SlotSottocommissione> slot = calculate(x);
            Collections.sort(slot);
            for (SlotSottocommissione xx : slot) {
                sb.append(xx.sottocommissione.nomecommissione + " (" + xx.sottocommissione.classe + ")\t");
                TreeSet<Integer> giorni = xx.giorniImpegnati();
                sb.append(giorni + "\t");


                Integer max = Collections.max(giorni);
                for (int i = 1; i <= numGiorniTabella; i++) {

                    if (giorni.contains(i)) {
                        sb.append(xx.sottocommissione.classe + "\t");
                    } else {
                        sb.append("-\t");
                    }
                }
                sb.append("\n");

            }
            riss.add(sb.toString());
        }


        for (String x : riss) {
            System.out.println(x);
            System.out.println("========================================");
        }
/*
        System.out.println("======================");
        for (SlotSottocommissione xx : slot) {
            System.out.println(xx);
        }

        System.out.println("======================");
*/


    }

    public static ArrayList<SlotSottocommissione> calculate(List<Sottocommissione> commissioni) {
        ArrayList<SlotSottocommissione> slot = new ArrayList<>();
        for (Sottocommissione c : commissioni) {
            SlotSottocommissione ss = new SlotSottocommissione(1, c);
            slot.add(ss);
            while (conflitto(slot)) {
                ss.giornoInizio++;
            }
        }
        return slot;
    }

    private static boolean conflitto(List<SlotSottocommissione> commissioni) {
        for (int i = 0; i < commissioni.size(); i++) {
            for (int j = i + 1; j < commissioni.size(); j++) {
                SlotSottocommissione s1 = commissioni.get(i);
                SlotSottocommissione s2 = commissioni.get(j);
                if (s1.conflitto(s2)) return true;
            }
        }
        return false;
    }
}
