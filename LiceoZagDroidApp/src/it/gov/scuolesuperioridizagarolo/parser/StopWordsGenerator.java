package it.gov.scuolesuperioridizagarolo.parser;

import java.util.TreeSet;

/**
 * Created by stefano on 20/04/2018.
 */
public class StopWordsGenerator {
    public static String s = "\n" +
            " | An Italian stop word list. Comments begin with vertical bar. Each stop\n" +
            " | word is at the start of a line.\n" +
            "\n" +
            "ad             |  a (to) before vowel\n" +
            "al             |  a + il\n" +
            "allo           |  a + lo\n" +
            "ai             |  a + i\n" +
            "agli           |  a + gli\n" +
            "all            |  a + l'\n" +
            "agl            |  a + gl'\n" +
            "alla           |  a + la\n" +
            "alle           |  a + le\n" +
            "con            |  with\n" +
            "col            |  con + il\n" +
            "coi            |  con + i (forms collo, cogli etc are now very rare)\n" +
            "da             |  from\n" +
            "dal            |  da + il\n" +
            "dallo          |  da + lo\n" +
            "dai            |  da + i\n" +
            "dagli          |  da + gli\n" +
            "dall           |  da + l'\n" +
            "dagl           |  da + gll'\n" +
            "dalla          |  da + la\n" +
            "dalle          |  da + le\n" +
            "di             |  of\n" +
            "del            |  di + il\n" +
            "dello          |  di + lo\n" +
            "dei            |  di + i\n" +
            "degli          |  di + gli\n" +
            "dell           |  di + l'\n" +
            "degl           |  di + gl'\n" +
            "della          |  di + la\n" +
            "delle          |  di + le\n" +
            "in             |  in\n" +
            "nel            |  in + el\n" +
            "nello          |  in + lo\n" +
            "nei            |  in + i\n" +
            "negli          |  in + gli\n" +
            "nell           |  in + l'\n" +
            "negl           |  in + gl'\n" +
            "nella          |  in + la\n" +
            "nelle          |  in + le\n" +
            "su             |  on\n" +
            "sul            |  su + il\n" +
            "sullo          |  su + lo\n" +
            "sui            |  su + i\n" +
            "sugli          |  su + gli\n" +
            "sull           |  su + l'\n" +
            "sugl           |  su + gl'\n" +
            "sulla          |  su + la\n" +
            "sulle          |  su + le\n" +
            "per            |  through, by\n" +
            "tra            |  among\n" +
            "contro         |  against\n" +
            "io             |  I\n" +
            "tu             |  thou\n" +
            "lui            |  he\n" +
            "lei            |  she\n" +
            "noi            |  we\n" +
            "voi            |  you\n" +
            "loro           |  they\n" +
            "mio            |  my\n" +
            "mia            |\n" +
            "miei           |\n" +
            "mie            |\n" +
            "tuo            |\n" +
            "tua            |\n" +
            "tuoi           |  thy\n" +
            "tue            |\n" +
            "suo            |\n" +
            "sua            |\n" +
            "suoi           |  his, her\n" +
            "sue            |\n" +
            "nostro         |  our\n" +
            "nostra         |\n" +
            "nostri         |\n" +
            "nostre         |\n" +
            "vostro         |  your\n" +
            "vostra         |\n" +
            "vostri         |\n" +
            "vostre         |\n" +
            "mi             |  me\n" +
            "ti             |  thee\n" +
            "ci             |  us, there\n" +
            "vi             |  you, there\n" +
            "lo             |  him, the\n" +
            "la             |  her, the\n" +
            "li             |  them\n" +
            "le             |  them, the\n" +
            "gli            |  to him, the\n" +
            "ne             |  from there etc\n" +
            "il             |  the\n" +
            "un             |  a\n" +
            "uno            |  a\n" +
            "una            |  a\n" +
            "ma             |  but\n" +
            "ed             |  and\n" +
            "se             |  if\n" +
            "perché         |  why, because\n" +
            "anche          |  also\n" +
            "come           |  how\n" +
            "dov            |  where (as dov')\n" +
            "dove           |  where\n" +
            "che            |  who, that\n" +
            "chi            |  who\n" +
            "cui            |  whom\n" +
            "non            |  not\n" +
            "più            |  more\n" +
            "quale          |  who, that\n" +
            "quanto         |  how much\n" +
            "quanti         |\n" +
            "quanta         |\n" +
            "quante         |\n" +
            "quello         |  that\n" +
            "quelli         |\n" +
            "quella         |\n" +
            "quelle         |\n" +
            "questo         |  this\n" +
            "questi         |\n" +
            "questa         |\n" +
            "queste         |\n" +
            "si             |  yes\n" +
            "tutto          |  all\n" +
            "tutti          |  all\n" +
            "\n" +
            "               |  single letter forms:\n" +
            "\n" +
            "a              |  at\n" +
            "c              |  as c' for ce or ci\n" +
            "e              |  and\n" +
            "i              |  the\n" +
            "l              |  as l'\n" +
            "o              |  or\n" +
            "\n" +
            "               | forms of avere, to have (not including the infinitive):\n" +
            "\n" +
            "ho\n" +
            "hai\n" +
            "ha\n" +
            "abbiamo\n" +
            "avete\n" +
            "hanno\n" +
            "abbia\n" +
            "abbiate\n" +
            "abbiano\n" +
            "avrò\n" +
            "avrai\n" +
            "avrà\n" +
            "avremo\n" +
            "avrete\n" +
            "avranno\n" +
            "avrei\n" +
            "avresti\n" +
            "avrebbe\n" +
            "avremmo\n" +
            "avreste\n" +
            "avrebbero\n" +
            "avevo\n" +
            "avevi\n" +
            "aveva\n" +
            "avevamo\n" +
            "avevate\n" +
            "avevano\n" +
            "ebbi\n" +
            "avesti\n" +
            "ebbe\n" +
            "avemmo\n" +
            "aveste\n" +
            "ebbero\n" +
            "avessi\n" +
            "avesse\n" +
            "avessimo\n" +
            "avessero\n" +
            "avendo\n" +
            "avuto\n" +
            "avuta\n" +
            "avuti\n" +
            "avute\n" +
            "\n" +
            "               | forms of essere, to be (not including the infinitive):\n" +
            "sono\n" +
            "sei\n" +
            "è\n" +
            "siamo\n" +
            "siete\n" +
            "sia\n" +
            "siate\n" +
            "siano\n" +
            "sarò\n" +
            "sarai\n" +
            "sarà\n" +
            "saremo\n" +
            "sarete\n" +
            "saranno\n" +
            "sarei\n" +
            "saresti\n" +
            "sarebbe\n" +
            "saremmo\n" +
            "sareste\n" +
            "sarebbero\n" +
            "ero\n" +
            "eri\n" +
            "era\n" +
            "eravamo\n" +
            "eravate\n" +
            "erano\n" +
            "fui\n" +
            "fosti\n" +
            "fu\n" +
            "fummo\n" +
            "foste\n" +
            "furono\n" +
            "fossi\n" +
            "fosse\n" +
            "fossimo\n" +
            "fossero\n" +
            "essendo\n" +
            "\n" +
            "               | forms of fare, to do (not including the infinitive, fa, fat-):\n" +
            "faccio\n" +
            "fai\n" +
            "facciamo\n" +
            "fanno\n" +
            "faccia\n" +
            "facciate\n" +
            "facciano\n" +
            "farò\n" +
            "farai\n" +
            "farà\n" +
            "faremo\n" +
            "farete\n" +
            "faranno\n" +
            "farei\n" +
            "faresti\n" +
            "farebbe\n" +
            "faremmo\n" +
            "fareste\n" +
            "farebbero\n" +
            "facevo\n" +
            "facevi\n" +
            "faceva\n" +
            "facevamo\n" +
            "facevate\n" +
            "facevano\n" +
            "feci\n" +
            "facesti\n" +
            "fece\n" +
            "facemmo\n" +
            "faceste\n" +
            "fecero\n" +
            "facessi\n" +
            "facesse\n" +
            "facessimo\n" +
            "facessero\n" +
            "facendo\n" +
            "\n" +
            "               | forms of stare, to be (not including the infinitive):\n" +
            "sto\n" +
            "stai\n" +
            "sta\n" +
            "stiamo\n" +
            "stanno\n" +
            "stia\n" +
            "stiate\n" +
            "stiano\n" +
            "starò\n" +
            "starai\n" +
            "starà\n" +
            "staremo\n" +
            "starete\n" +
            "staranno\n" +
            "starei\n" +
            "staresti\n" +
            "starebbe\n" +
            "staremmo\n" +
            "stareste\n" +
            "starebbero\n" +
            "stavo\n" +
            "stavi\n" +
            "stava\n" +
            "stavamo\n" +
            "stavate\n" +
            "stavano\n" +
            "stetti\n" +
            "stesti\n" +
            "stette\n" +
            "stemmo\n" +
            "steste\n" +
            "stettero\n" +
            "stessi\n" +
            "stesse\n" +
            "stessimo\n" +
            "stessero\n" +
            "stando";


    public static void main(String[] args) {
        final String[] split = s.split("\n");
        System.out.println("TreeSet<String> stopWords = new TreeSet<>();");
        for (String s : split) {
            s = s.trim();
            if (s.length() == 0) continue;
            final int i = s.indexOf("|");

            String x;
            if (i >= 0) {
                x = s.substring(0, i).trim();

            } else {
                x = s.trim();
            }

            TreeSet<String> stopWords = new TreeSet<>();

            if (x.length() > 0) {
                System.out.println("stopWords.add(\"" + x + "\");");
            }
        }
    }
}
