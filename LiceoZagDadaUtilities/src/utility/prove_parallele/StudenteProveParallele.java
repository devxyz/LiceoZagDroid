package utility.prove_parallele;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

public class StudenteProveParallele implements Comparable<StudenteProveParallele> {
    public final String cognome_nome;
    public final ClassData classe;

    public StudenteProveParallele(String cognome_nome, ClassData classe) {
        this.cognome_nome = cognome_nome;
        this.classe = classe;
    }

    @Override
    public int compareTo(StudenteProveParallele o) {
        int i = this.classe.compareTo(o.classe);
        if (i != 0) return i;
        return cognome_nome.compareTo(o.cognome_nome);
    }
}
