package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.corso_sicurezza;

import java.util.ArrayList;
import java.util.Collection;

public class StudenteCorsoSicurezzaCollection {
    private final ArrayList<StudenteCorsoSicurezza> studenti = new ArrayList<>();

    public void add(StudenteCorsoSicurezza s) {
        studenti.add(s);
    }

    public void add(Collection<StudenteCorsoSicurezza> s) {
        studenti.addAll(s);
    }

    public ArrayList<StudenteCorsoSicurezza> getStudenti() {
        return studenti;
    }

    public static interface StudenteCorsoSicurezzaFilter {
        public boolean accept(StudenteCorsoSicurezza s);
    }

    public StudenteCorsoSicurezza searchByCF(String cf) {
        String cfU = cf.trim();
        return searchFirst(new StudenteCorsoSicurezzaFilter() {
            @Override
            public boolean accept(StudenteCorsoSicurezza s) {
                return s.codicefiscaleStudente.trim().equalsIgnoreCase(cfU);
            }
        });
    }

    public StudenteCorsoSicurezza searchFirst(StudenteCorsoSicurezzaFilter f) {
        for (StudenteCorsoSicurezza StudenteCorsoSicurezza : studenti) {
            if (f.accept(StudenteCorsoSicurezza)) return StudenteCorsoSicurezza;
        }
        return null;
    }
}
