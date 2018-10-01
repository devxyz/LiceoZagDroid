package it.gov.scuolesuperioridizagarolo.dada.bitorario.split;

import it.gov.scuolesuperioridizagarolo.model.bitorario.ClassroomFilter;
import it.gov.scuolesuperioridizagarolo.model.bitorario.LessonWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by stefano on 25/06/2017.
 */
public class SplitMaterieUtil {
    public static List<LessonWrapper> splitSubjects(List<LessonWrapper> lessonList) {
        List<LessonWrapper> ris = new ArrayList<>();
        final List<SplitMaterie> split = build();


        for (LessonWrapper x : lessonList) {


            SplitMaterie rename = null;
            for (SplitMaterie y : split) {
                if (y.compatibile(x.classroom, x.subject)) {
                    rename = y;
                }
            }

            if (rename != null) {
                final String nuovaMateria = rename.assegna(x.duration);
                if (nuovaMateria != null)
                    ris.add(new LessonWrapper(x.teacher, x.duration, nuovaMateria, x.classroom, x.room));
                else {
                    if (nuovaMateria == null) {
                        throw new IllegalArgumentException("Impossibile gestire lo slot " + x + "\n con " + rename);
                    }
                }

            } else {
                ris.add(x);
            }
        }
        return ris;
    }

    private static void addAll(List<SplitMaterie> ris, List<SplitMaterie> altro) {
        for (SplitMaterie n : altro) {

            for (SplitMaterie x : ris) {
                if (x.classe == n.classe && x.materiaComposta.equals(n.materiaComposta)) {
                    throw new IllegalArgumentException("Il nuovo " + n + "\n si riferisce alle stesse classi di " + x);
                }
            }
            ris.add(n);
        }
    }


    public static List<SplitMaterie> build() {
        List<SplitMaterie> ris = new ArrayList<>();

        //==========================
        //ED FISICA
        //==========================
        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.TUTTO(),
                        "ed.fis", "ED.FIS", 2, "ED.FIS", 0
                ));


        //==========================
        //ITAL-LAT
        //==========================
        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.LICEO_TRADIZIONALE(),
                        "ITAL-LAT", "ITAL", 4, "LAT", 3
                ));


        //==========================
        //ITAL STORIA
        //==========================
        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.BIENNIO(),
                        "ITAL-STO", "ITAL", 4, "STO B", 3
                ));

        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.TRIENNIO(),
                        "ITAL-STO", "ITAL", 4, "STO T", 2
                ));


        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.BIENNIO(),
                        "LAT-STO B", "LAT", 3, "STO B", 3
                ));


        //==========================
        //MAT FIS
        //==========================
        //mat-fis biennio scienze app
        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.LICEO_SCIENZE_APPLICATE().and(ClassroomFilter.BIENNIO()),
                        "MAT-FIS", "MAT", 5, "FIS", 2
                ));

        //mat-fis biennio tradizionale
        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.LICEO_TRADIZIONALE().and(ClassroomFilter.BIENNIO()),
                        "MAT-FIS", "MAT", 6, "FIS", 2
                ));

        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.TRIENNIO(),
                        "MAT-FIS", "MAT", 4, "FIS", 3
                ));


        //==========================
        //STO FILOSOFIA
        //==========================
        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.LICEO_TRADIZIONALE().and(ClassroomFilter.TRIENNIO()),
                        "ST-FIL", "STO T", 2, "FIL", 3
                ));

        addAll(ris,
                SplitMaterie.splitMaterie(
                        ClassroomFilter.LICEO_SCIENZE_APPLICATE().and(ClassroomFilter.TRIENNIO()),
                        "ST-FIL", "STO T", 2, "FIL", 2
                ));


        Collections.sort(ris, new Comparator<SplitMaterie>() {
            @Override
            public int compare(SplitMaterie o1, SplitMaterie o2) {
                return (o1.classe + o1.materiaComposta).compareTo((o2.classe + o2.materiaComposta));
            }
        });

        System.out.println("================================");
        for (SplitMaterie ri : ris) {
            System.out.println(ri);
        }
        System.out.println("================================");


        return ris;
    }
}
