package it.gov.scuolesuperioridizagarolo.model.bar;

import java.io.Serializable;
import java.util.*;

/**
 * Created by stefano on 04/01/2018.
 */
public class ProdottoBarContainer implements Serializable {
    private final ArrayList<ProdottoBar> lista;

    public ProdottoBarContainer() {
        lista = new ArrayList<>();
    }

    public static ArrayList<ProdottoBar> demoProdotti(String nomeUtente) {
        final ArrayList<ProdottoBar> objects = new ArrayList<>();
        int i = 0;

        objects.add(new ProdottoBar(i++, nomeUtente, "panino mortadella", 1));
        objects.add(new ProdottoBar(i++, nomeUtente, "cappuccino", 1.5));
        objects.add(new ProdottoBar(i++, nomeUtente, "mocaccino", 0.5));
        objects.add(new ProdottoBar(i++, nomeUtente, "panino salame", 0.8));
        objects.add(new ProdottoBar(i++, nomeUtente, "cappuccino chiaro", 1.3));
        objects.add(new ProdottoBar(i++, nomeUtente, "latte macchiato", 0.7));
        objects.add(new ProdottoBar(i++, nomeUtente, "tamezzino salame", 1));
        objects.add(new ProdottoBar(i++, nomeUtente, "tramezzino mortadella", 1));
        return objects;

    }

    public static void main(String[] args) {
        ProdottoBarContainer p = new ProdottoBarContainer();
        p.add(ProdottoBarContainer.demoProdotti("pippo"));
        p.searchBy("pippo", 0).quantita = 5;
        p.searchBy("pippo", 3).quantita = 3;
        final String str = p.toQrCodeString();
        System.out.println(str);


        ProdottoBarContainer p2=new ProdottoBarContainer();
        p2.addFromQrCodeString(str);
        System.out.println(p2);

    }

    public ProdottoBar searchBy(String nomeUtente, int id) {
        for (ProdottoBar p : lista) {
            if (p.nomeUtente.equals(nomeUtente) && p.id == id)
                return p;
        }
        return null;
    }

    public void addFromQrCodeString(String s) {
        ArrayList<ProdottoBar> ris = new ArrayList<>();

        //normalizza input
        String[] righe = s.split("\n");
        ArrayList<String> ss = new ArrayList<>();
        for (String r : righe) {
            if (r != null && r.trim().length() > 0)
                ss.add(r.trim());
        }
        righe = ss.toArray(new String[ss.size()]);

        //controlla input
        if (righe.length % 2 != 0) throw new IllegalArgumentException("Numero righe non valide: " + righe.length);

        //parsing singole righe
        for (int i = 0; i < righe.length; i += 2) {
            String format_utente = righe[i];
            String format_prodotti = righe[i + 1];

            final String[] split1 = format_utente.split(":");
            if (!split1[0].equals("U")) throw new IllegalArgumentException("Formato non valido " + format_utente);

            final String nomeutente = split1[1];
            final String[] split2 = format_prodotti.split(":");
            if (!split2[0].equals("P")) throw new IllegalArgumentException("Formato non valido " + format_prodotti);

            final String[] listaProdotti = split2[1].split(";");
            Map<Integer, Integer> mapIdProdottoQuantita = new HashMap<>();
            for (String pp : listaProdotti) {
                final String[] split = pp.split("=");
                mapIdProdottoQuantita.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }

            //aggiungo i prodotti, aggiungendo eventualmente le quantita'
            final ArrayList<ProdottoBar> pb = demoProdotti(nomeutente);
            for (ProdottoBar p : pb) {
                final Integer qta = mapIdProdottoQuantita.get(p.id);
                if (qta != null) {
                    p.quantita = qta;
                }
                ris.add(p);
            }
        }

        //aggiunge tutto
        add(ris);
    }

    public String toQrCodeString() {
        StringBuilder sb = new StringBuilder();
        final ArrayList<String> nomiUtentiArray = new ArrayList<>(Arrays.asList(getNomiUtentiArray()));

        for (int i = 0; i < nomiUtentiArray.size(); i++) {
            String s = nomiUtentiArray.get(i);

            final List<ProdottoBar> lista = getLista(s);
            if (lista.size() > 0) {
                sb.append("U:" + s + "\n");
                sb.append("P:");
                for (ProdottoBar p : lista) {
                    if (p.quantita > 0)
                        sb.append(p.id + "=" + p.quantita + ";");
                }
                sb.append("\n");
            }

        }
        return sb.toString();


    }

    public List<ProdottoBar> getLista() {
        return new ArrayList<>(lista);
    }

    public List<ProdottoBar> getLista(String utente) {
        final ArrayList<ProdottoBar> objects = new ArrayList<>();
        for (ProdottoBar x : lista) {
            if (x.nomeUtente.equals(utente)) {
                objects.add(x);
            }
        }
        return objects;
    }

    public void add(ProdottoBar b) {
        lista.add(b);
    }

    public void add(List<ProdottoBar> b) {
        lista.addAll(b);
    }

    public String[] getNomiUtentiArray() {
        final LinkedHashSet<String> a = getNomiUtenti();
        return a.toArray(new String[a.size()]);
    }

    public LinkedHashSet<String> getNomiUtenti() {
        LinkedHashSet<String> ris = new LinkedHashSet<>();
        for (ProdottoBar x : lista) {
            ris.add(x.nomeUtente);
        }
        return ris;
    }

    public List<ProdottoBar> filter(ProdottoBarContainerFilter f) {
        List<ProdottoBar> ris = new ArrayList<>();
        for (ProdottoBar p : lista) {
            if (f.accept(p))
                ris.add(p);
        }
        return ris;
    }

    public void remove(ProdottoBarContainerFilter f) {
        for (Iterator<ProdottoBar> iterator = lista.iterator(); iterator.hasNext(); ) {
            ProdottoBar p = iterator.next();
            if (f.accept(p))
                iterator.remove();
        }
    }

    public int getNumeroProdotti() {
        int count = 0;
        for (ProdottoBar x : lista) {
            count += x.quantita;
        }
        return count;
    }

    public int getNumeroProdotti(String nomeUtente) {
        int count = 0;
        for (ProdottoBar x : lista) {
            if (x.nomeUtente.equals(nomeUtente))
                count += x.quantita;
        }
        return count;
    }

    public double getPrezzoTotale() {
        double count = 0;
        for (ProdottoBar x : lista) {
            count += x.prezzounitario * x.quantita;
        }
        return count;
    }

    public double getPrezzoTotale(String nomeUtente) {
        double count = 0;
        for (ProdottoBar x : lista) {
            if (x.nomeUtente.equals(nomeUtente))
                count += x.prezzounitario * x.quantita;
        }
        return count;
    }

    @Override
    public String toString() {
        return "ProdottoBarContainer{" +
                "lista=" + lista +
                '}';
    }

    public static interface ProdottoBarContainerFilter {
        public boolean accept(ProdottoBar b);
    }
}
