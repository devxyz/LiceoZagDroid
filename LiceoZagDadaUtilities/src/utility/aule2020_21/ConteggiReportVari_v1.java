package utility.aule2020_21;

import java.util.ArrayList;

public class ConteggiReportVari_v1 {
    static String[][] matrice() {
        String s =// "CLASSE\tSCUOLA\tNUM STUDENTI\tDENOMINAZ\tLUN\tMAR\tMER\tGIO\tVEN\tSAB\tTOT\n" +
                "1A IPIA\tIPIA\t18\t1A IPIA\t7\t7\t6\t6\t6\t\t32\n" +
                        "1B IPIA\tIPIA\t17\t1B IPIA\t7\t7\t6\t6\t6\t\t32\n" +
                        "2A IPIA\tIPIA\t21\t2A IPIA\t7\t7\t6\t6\t6\t\t32\n" +
                        "2B IPIA\tIPIA\t22\t2B IPIA\t7\t7\t6\t6\t6\t\t32\n" +
                        "3A IPIA\tIPIA\t18\t3A IPIA\t7\t7\t6\t6\t6\t\t32\n" +
                        "4A IPIA\tIPIA\t23\t4A IPIA\t7\t7\t6\t6\t6\t\t32\n" +
                        "5A IPIA\tIPIA\t15\t5A IPIA\t7\t7\t6\t6\t6\t\t32\n" +
                        "1A LICEO\tLICEO\t21\t1A LICEO\t6\t6\t5\t5\t\t\t22\n" +
                        "1B LICEO\tLICEO\t24\t1B LICEO\t6\t6\t5\t5\t\t\t22\n" +
                        "1C LICEO\tLICEO\t20\t1C LICEO\t6\t6\t5\t5\t\t\t22\n" +
                        "1D LICEO\tLICEO\t24\t1D LICEO\t6\t6\t5\t5\t\t\t22\n" +
                        "1G LICEO\tLICEO\t20\t1G LICEO\t6\t6\t5\t5\t\t\t22\n" +
                        "1E LICEO\tLICEO\t27\t1E LICEO\t6\t6\t5\t5\t\t\t22\n" +
                        "1F LICEO\tLICEO\t20\t1F LICEO\t6\t6\t5\t5\t\t\t22\n" +
                        "2A LICEO\tLICEO\t19\t2A LICEO\t6\t6\t5\t\t5\t\t22\n" +
                        "2B LICEO\tLICEO\t27\t2B LICEO\t6\t6\t5\t\t5\t\t22\n" +
                        "2C LICEO\tLICEO\t22\t2C LICEO\t6\t6\t5\t\t5\t\t22\n" +
                        "2D LICEO\tLICEO\t27\t2D LICEO\t6\t6\t5\t\t5\t\t22\n" +
                        "2E LICEO\tLICEO\t22\t2E LICEO\t6\t6\t5\t\t5\t\t22\n" +
                        "2F LICEO\tLICEO\t23\t2F LICEO\t6\t6\t5\t\t5\t\t22\n" +
                        "2G LICEO\tLICEO\t19\t2G LICEO\t6\t6\t5\t\t5\t\t22\n" +
                        "2H LICEO\tLICEO\t23\t2H LICEO\t6\t6\t5\t\t5\t\t22\n" +
                        "3A LICEO\tLICEO\t21\t3A LICEO\t6\t6\t\t6\t6\t\t24\n" +
                        "3B LICEO\tLICEO\t23\t3B LICEO\t6\t6\t\t6\t6\t\t24\n" +
                        "3C LICEO\tLICEO\t21\t3C LICEO\t6\t6\t\t6\t6\t\t24\n" +
                        "3D LICEO\tLICEO\t25\t3D LICEO\t6\t6\t\t6\t6\t\t24\n" +
                        "3E LICEO\tLICEO\t19\t3E LICEO\t6\t6\t\t6\t6\t\t24\n" +
                        "3F LICEO\tLICEO\t21\t3F LICEO\t6\t6\t\t6\t6\t\t24\n" +
                        "3H LICEO\tLICEO\t18\t3H LICEO\t6\t6\t\t6\t6\t\t24\n" +
                        "4A LICEO\tLICEO\t20\t4A LICEO\t6\t6\t\t6\t6\t\t24\n" +
                        "4B LICEO\tLICEO\t20\t4B LICEO\t6\t\t6\t6\t6\t\t24\n" +
                        "4C LICEO\tLICEO\t20\t4C LICEO\t6\t\t6\t6\t6\t\t24\n" +
                        "4D LICEO\tLICEO\t27\t4D LICEO\t6\t\t6\t6\t6\t\t24\n" +
                        "4E LICEO\tLICEO\t23\t4E LICEO\t6\t\t6\t6\t6\t\t24\n" +
                        "4F LICEO\tLICEO\t21\t4F LICEO\t6\t\t6\t6\t6\t\t24\n" +
                        "4G LICEO\tLICEO\t25\t4G LICEO\t6\t\t6\t6\t6\t\t24\n" +
                        "4H LICEO\tLICEO\t20\t4H LICEO\t6\t\t6\t6\t6\t\t24\n" +
                        "5A LICEO\tLICEO\t22\t5A LICEO\t6\t\t6\t6\t6\t\t24\n" +
                        "5B LICEO\tLICEO\t21\t5B LICEO\t\t6\t6\t6\t6\t\t24\n" +
                        "5C LICEO\tLICEO\t22\t5C LICEO\t\t6\t6\t6\t6\t\t24\n" +
                        "5D LICEO\tLICEO\t16\t5D LICEO\t\t6\t6\t6\t6\t\t24\n" +
                        "5E LICEO\tLICEO\t20\t5E LICEO\t\t6\t6\t6\t6\t\t24\n" +
                        "5F LICEO\tLICEO\t16\t5F LICEO\t\t6\t6\t6\t6\t\t24\n" +
                        "5G LICEO\tLICEO\t16\t5G LICEO\t\t6\t6\t6\t6\t\t24";
        String[] righe = s.split("\n");
        int colonne = righe[0].split("\t").length;
        String[][] mat = new String[righe.length][colonne];
        int row = 0;
        for (String line : righe) {
            String[] split = line.split("\t");
            for (int j = 0; j < split.length; j++) {
                mat[row][j] = split[j];
            }
            row++;
        }
        return mat;
    }

    public static void main(String[] args) {
        String[][] matrice = matrice();
        int totaleStudenti = 0;
        for (int i = 0; i < matrice.length; i++) {
            int numStud = getNumero(matrice[i][2]);
            totaleStudenti += numStud;
        }

        for (String[] strings : matrice) {
            for (String l : strings) {
                System.out.print(l + "\t");
            }
            System.out.println();
        }

        System.out.println();

        for (int ora = 1; ora <= 7; ora++) {
           // System.out.print("ORA " + ora + ":");
            for (int giorno = 1; giorno <= 6; giorno++) {
                int contaStudenti = 0;
                ArrayList<String> classi = new ArrayList<>();
                for (int i = 0; i < matrice.length; i++) {

                    int numStud = getNumero(matrice[i][2]);
                    int numOre = getNumero(matrice[i][giorno + 3]);
                    if (numOre >= ora) {
                        contaStudenti += numStud;
                        classi.add(matrice[i][0] + numOre);
                    }
                }
                System.out.print(Math.round(100.0 * contaStudenti / totaleStudenti)/* + classi.toString()*/ + "\t");

            }
            System.out.println();
        }
    }

    static int getNumero(String s) {
        if (s.trim().length() == 0) return 0;
        return Integer.parseInt(s);
    }
}
