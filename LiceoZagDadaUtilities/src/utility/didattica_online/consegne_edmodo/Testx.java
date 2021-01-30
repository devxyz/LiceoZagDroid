package utility.didattica_online.consegne_edmodo;

public class Testx {

    public static void main(String[] args) {
        String s = "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 b;6 c;6 d;6 e;6 f;6 g;7 a;7 b;7 c\n" +
                "non sono riuscita a fare nessun punto in quanto mi ha dato errore subito\n" +
                "nessuna mi ha dato errore subito\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 b;6 c;6 d;6 e;6 f;6 g;7 a;7 b;7 c;8 a;8 b;8 c;8 d;9;10;11;11 a\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 b;6 c;6 d;6 e;6 f;6 g;7 a;7 b;7 c;8 a\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 c;6 e;7 a;7 b;7 c;8 a;8 b;8 c;8 d;10;11 a\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 c;6 e;7 a;7 b;7 c;8 a;8 b;8 c;8 d;9;10\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 b;6 c;6 d;6 e;6 f;6 g;7 a;7 b;7 c\n" +
                "Sono riuscito a modificare l'articolo della Home e poi più nulla a causa dell'errore 500\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 b;6 d;6 f;7 a;7 b;7 c\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 b;6 c;6 d;6 e;6 f;6 g;7 a;7 b;7 c;8 a;8 b;8 c;8 d\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 d;6 g;7 a;7 b\n" +
                "1;2 a;2 b;3;4 a\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 c;6 e;7 a;7 b;7 c;8 a;8 b;8 c;8 d;9;10;11\n" +
                "1;2 a;2 b;3;4 a;4 b;4 c;4 d;4 e;5 a;5 b;5 c;6 a;6 c;6 e;7 a;7 b;7 c;8 a;8 b;8 c;8 d;9;10";
        String[] split = s.split("\n");
        for (String s1 : split) {
            System.out.println(s1.split(";").length);
        }
    }
}
