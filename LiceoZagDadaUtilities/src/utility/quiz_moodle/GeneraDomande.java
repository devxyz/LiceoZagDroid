package utility.quiz_moodle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class GeneraDomande {
    public static void main(String[] args) throws FileNotFoundException {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/quiz_moodle/xml");
        root.mkdirs();
        Random r = new Random(13);

        //DEC 2 BIN
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 100; i++) {
                int basePartenza = 10;
                int baseArrivo = 2;
                int numCifre = 4;
                BASE_BASE b = new BASE_BASE(basePartenza, baseArrivo, GeneraNumeroCasualeBase.generaNumero(r, basePartenza, numCifre));
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "DEC-BIN.xml"));
        }

        //BIN 2 DEC
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 100; i++) {
                int basePartenza = 2;
                int baseArrivo = 10;
                int numCifre = 9;
                BASE_BASE b = new BASE_BASE(basePartenza, baseArrivo, GeneraNumeroCasualeBase.generaNumero(r, basePartenza, numCifre));
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "BIN-DEC.xml"));
        }

        //HEX 2 DEC
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 100; i++) {
                int basePartenza = 16;
                int baseArrivo = 10;
                int numCifre = 3;
                BASE_BASE b = new BASE_BASE(basePartenza, baseArrivo, GeneraNumeroCasualeBase.generaNumero(r, basePartenza, numCifre));
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "HEX-DEC.xml"));
        }


        //DEC 2 HEX
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 100; i++) {
                int basePartenza = 10;
                int baseArrivo = 16;
                int numCifre = 3;
                BASE_BASE b = new BASE_BASE(basePartenza, baseArrivo, GeneraNumeroCasualeBase.generaNumero(r, basePartenza, numCifre));
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "DEC-HEX.xml"));
        }


        //HEX 2 BIN
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 100; i++) {
                int basePartenza = 16;
                int baseArrivo = 2;
                int numCifre = 4;
                BASE_BASE b = new BASE_BASE(basePartenza, baseArrivo, GeneraNumeroCasualeBase.generaNumero(r, basePartenza, numCifre));
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "HEX-BIN.xml"));
        }

        //BIN 2 HEX
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 100; i++) {
                int basePartenza = 2;
                int baseArrivo = 16;
                int numCifre = 14;
                BASE_BASE b = new BASE_BASE(basePartenza, baseArrivo, GeneraNumeroCasualeBase.generaNumero(r, basePartenza, numCifre));
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "BIN-HEX.xml"));
        }

        //BASE 8, base 10
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 100; i++) {
                int basePartenza = 8;
                int baseArrivo = 10;
                int numCifre = 5;
                BASE_BASE b = new BASE_BASE(basePartenza, baseArrivo, GeneraNumeroCasualeBase.generaNumero(r, basePartenza, numCifre));
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "OCT-DEC.xml"));
        }


        //BASE 10, base 5
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 100; i++) {
                int basePartenza = 10;
                int baseArrivo = 2;
                int numCifre = 2;
                BASE_BASE b = new BASE_BASE(basePartenza, baseArrivo, GeneraNumeroCasualeBase.generaNumero(r, basePartenza, numCifre));
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "DEC-5.xml"));
        }

        //confronti
        {
            RaccoltaQuizMoodle a = new RaccoltaQuizMoodle();
            for (int i = 1; i <= 10; i++) {
                int base1 = 10;
                int base2 = 2;
                int numCifre1 = 3;
                int numCifre2 = 8;
                COMPARAZIONE_BASE_BASE b = new COMPARAZIONE_BASE_BASE(base1, base2,
                        GeneraNumeroCasualeBase.generaNumero(r, base1, numCifre1),
                        GeneraNumeroCasualeBase.generaNumero(r, base2, numCifre2)
                );
                a.quizzes.add(b.quiz());
            }

            for (int i = 1; i <= 10; i++) {
                int base1 = 16;
                int base2 = 2;
                int numCifre1 = 2;
                int numCifre2 = 8;
                COMPARAZIONE_BASE_BASE b = new COMPARAZIONE_BASE_BASE(base1, base2,
                        GeneraNumeroCasualeBase.generaNumero(r, base1, numCifre1),
                        GeneraNumeroCasualeBase.generaNumero(r, base2, numCifre2)
                );
                a.quizzes.add(b.quiz());
            }
            a.toFile(new File(root, "COMPARAZIONE.xml"));
        }


    }
}
