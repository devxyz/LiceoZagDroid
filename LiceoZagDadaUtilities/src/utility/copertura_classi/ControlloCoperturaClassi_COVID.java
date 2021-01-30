package utility.copertura_classi;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

import java.util.*;

public class ControlloCoperturaClassi_COVID {
    public static class AssenzaDocenti_COVID {
        public final String docente;
        public final Set<EGiorno> giorni = new TreeSet<>();

        public AssenzaDocenti_COVID(String docente, EGiorno... giorni) {
            this.docente = docente.toUpperCase();
            this.giorni.addAll(Arrays.asList(giorni));
        }
    }

    public static class CoperturaClasse_COVID {
        public final ClassData classe;
        public final Map<EGiorno, Integer> ore_coperte;
        public final Map<EGiorno, Integer> ore_non_coperte;

        public CoperturaClasse_COVID(ClassData classe) {
            this.classe = classe;
            ore_coperte = new TreeMap<>();
            ore_non_coperte = new TreeMap<>();
            for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
                ore_coperte.put(g, 0);
                ore_non_coperte.put(g, 0);
            }
        }

        public String reportTotale() {
            int tot_non_coperte = 0;
            int tot_coperte = 0;
            for (Map.Entry<EGiorno, Integer> e : ore_coperte.entrySet()) {
                tot_coperte += e.getValue();
            }
            for (Map.Entry<EGiorno, Integer> e : ore_non_coperte.entrySet()) {
                tot_non_coperte += e.getValue();
            }

            double non_copertura = Math.round((100.0 * tot_non_coperte) / (tot_non_coperte + tot_coperte));
            return classe + "\t" + (non_copertura + "").replace(".", ",") + "\t ORE NON COPERTE " + tot_non_coperte + "/" + (tot_non_coperte + tot_coperte);
        }

        public String headerReportPerGiorno() {
            StringBuilder sb = new StringBuilder();
            sb.append("CLASSE\t% NON COPERTO\tTOTALI\t");
            for (EGiorno g : ore_coperte.keySet()) {
                sb.append(g + " non coperto\t");
                sb.append(g + " coperto\t");
                sb.append(g + " esito\t");
            }
            return sb.toString();
        }

        public String headerReportCriticitàPerGiorno() {
            StringBuilder sb = new StringBuilder();
            sb.append("CLASSE\t% NON COPERTO\tTOTALI\t");
            for (EGiorno g : ore_coperte.keySet()) {
                sb.append(g + " (SCOPERTO/COPERTO) \t");
            }
            return sb.toString();
        }

        public String reportPerGiorno() {
            StringBuilder sb = new StringBuilder();
            for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
                int scoperte = ore_non_coperte.get(g);
                int coperte = ore_coperte.get(g);
                sb.append(scoperte).append("\t");
                sb.append(coperte).append("\t");

                if (scoperte >= coperte && (scoperte + coperte) > 0) {
                    //se anomala e non DDI
                    sb.append("x " + scoperte + "/" + (scoperte + coperte)).append("\t");
                } else {
                    if ((scoperte + coperte) == 0)
                        sb.append("DAD").append("\t");
                    else
                        sb.append("-").append("\t");
                }
            }
            return reportTotale() + "\t" + sb;
        }

        public String reportCriticitaPerGiorno() {
            StringBuilder sb = new StringBuilder();
            for (EGiorno g : EGiorno.valuesGiorniDiLezione()) {
                int scoperte = ore_non_coperte.get(g);
                int coperte = ore_coperte.get(g);
                if (scoperte >= coperte - 1) {
                    sb.append("x " + scoperte + "/" + (scoperte)).append("\t");
                } else {
                    sb.append("-").append("\t");
                }
            }
            return reportTotale() + "\t" + sb;
        }

    }


    public static Map<ClassData, CoperturaClasse_COVID> report(BitOrarioGrigliaOrario orario, Collection<AssenzaDocenti_COVID> assenze) {
        Map<ClassData, CoperturaClasse_COVID> ris = new TreeMap<>();
        for (ClassData c : ClassData.values()) {
            ris.put(c, new CoperturaClasse_COVID(c));
        }

        TreeSet<String> docenti = orario.getDocentiUppercase();
        Map<String, AssenzaDocenti_COVID> map_assenze = new TreeMap<>();
        for (AssenzaDocenti_COVID a : assenze) {
            map_assenze.put(a.docente, a);
            if (!docenti.contains(a.docente.toUpperCase())) {
                System.out.println(docenti);
                throw new IllegalArgumentException("Docente " + a.docente + " non presente in orario");
            }

        }

        for (BitOrarioOraLezione l : orario.getLezioni()) {
            switch (l.getTipoLezione()) {
                case LEZIONE_CON_COMPRESENZA:
                case LEZIONE_SINGOLA: {
                    String d1 = l.getDocentePrincipale().toUpperCase();
                    if (l.getAula().isDDI()) continue;

                    AssenzaDocenti_COVID assenzaDocenti_covid = map_assenze.get(d1);
                    CoperturaClasse_COVID coperturaClasse_covid = ris.get(l.getClasse());
                    if (assenzaDocenti_covid != null && assenzaDocenti_covid.giorni.contains(l.getGiorno())) {
                        Integer val = coperturaClasse_covid.ore_non_coperte.get(l.getGiorno());
                        coperturaClasse_covid.ore_non_coperte.put(l.getGiorno(), 1 + val);
                    } else {
                        Integer val = coperturaClasse_covid.ore_coperte.get(l.getGiorno());
                        coperturaClasse_covid.ore_coperte.put(l.getGiorno(), 1 + val);

                    }
                }
            }
        }

        return ris;
    }
}
