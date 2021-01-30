package utility.didattica_online.assenze_online.data;

import java.util.Comparator;

public class DidatticaOnline_StudenteClasse implements Comparable<DidatticaOnline_StudenteClasse> {
    public final String nome;
    public final String cognome;
    public final String classe;

    private static String normalizzaNomeCognome(String s) {
        return
                s.replace(" ", "").replace("'", "").replace("è", "e").
                        replace("à", "a").replace("ò", "o").replace("Ò", "o").
                        replace("ì", "i").replace("Ì", "i").replace("Á", "a").
                        replace("È", "e").replace("é", "e").
                toUpperCase();
    }

    public DidatticaOnline_StudenteClasse(String nome, String cognome, String classe) {
        this.nome = normalizzaNomeCognome(nome);
        this.cognome = normalizzaNomeCognome(cognome);
        this.classe = classe.toUpperCase().trim();
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getClasse() {
        return classe;
    }

    private static final Comparator<DidatticaOnline_StudenteClasse> studenteClasseComparator = Comparator.comparing(DidatticaOnline_StudenteClasse::getClasse).thenComparing(DidatticaOnline_StudenteClasse::getCognome).thenComparing(DidatticaOnline_StudenteClasse::getNome);

    @Override
    public int compareTo(DidatticaOnline_StudenteClasse o) {
        return studenteClasseComparator.compare(this, o);
    }

    @Override
    public String toString() {
        return cognome + " " + nome + " classe='" + classe;
    }
}
