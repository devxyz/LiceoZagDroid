package utility.corso_sicurezza;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StudenteSidiCollection {
    private final ArrayList<StudenteSidi> studenti = new ArrayList<>();

    public void add(StudenteSidi s) {
        studenti.add(s);
    }

    public void add(Collection<StudenteSidi> s) {
        studenti.addAll(s);
    }

    public ArrayList<StudenteSidi> getStudenti() {
        return studenti;
    }

    public static interface StudenteSidiFilter {
        public boolean accept(StudenteSidi s);
    }

    public StudenteSidi searchByCF(String cf) {
        String cfU = cf.trim();
        return searchFirst(new StudenteSidiFilter() {
            @Override
            public boolean accept(StudenteSidi s) {
                return s.CodiceFiscale.trim().equalsIgnoreCase(cfU);
            }
        });
    }

    public List<StudenteSidi> searchAnnoCorso(int anno) {
        String _anno = "" + anno;
        return searchAll(new StudenteSidiFilter() {
            @Override
            public boolean accept(StudenteSidi s) {
                return (s.AnnoCorso+"").trim().equalsIgnoreCase(_anno);
            }
        });
    }

    public StudenteSidi searchFirst(StudenteSidiFilter f) {
        for (StudenteSidi studenteSidi : studenti) {
            if (f.accept(studenteSidi)) return studenteSidi;
        }
        return null;
    }

    public List<StudenteSidi> searchAll(StudenteSidiFilter f) {
        List<StudenteSidi> ris = new LinkedList<>();
        for (StudenteSidi studenteSidi : studenti) {
            if (f.accept(studenteSidi)) {
                ris.add(studenteSidi);
            }
            ;
        }
        return ris;
    }
}
