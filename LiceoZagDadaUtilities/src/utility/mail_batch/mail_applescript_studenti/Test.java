package utility.mail_batch.mail_applescript_studenti;

public class Test {

    public static void main(String[] args) {
        String s="3b.tango.lorenzo\n" +
                "3b.craciun.matteo\n" +
                "3b.pinci.rachele\n" +
                "3b.sanita.asia\n" +
                "3b.auricchio.lorenzo\n" +
                "3b.dipietro.niccolo\n" +
                "3b.pasquazi.elena\n" +
                "3b.monaco.mario\n" +
                "3b.stocco.matteo\n" +
                "3b.santoni.yuri\n" +
                "3b.fiorentini.giorgia\n" +
                "3b.gentile.gabriel\n" +
                "3b.velletrani.andrea\n" +
                "3b.parolari.kevin\n" +
                "3b.lullo.valerio\n" +
                "3b.evangelista.alessandro\n" +
                "3b.doraci.zaklina";
        String[] split = s.split("\n");
        for (String s1 : split) {
            s1=s1.trim();
            if(s1.length()==0)continue;
            String[] split1 = s1.split("[ ]+");
            String nome = split1[0];
            System.out.println("mkdir "+nome);
            System.out.println("mkdir "+nome+"/es1");
            System.out.println("mkdir "+nome+"/es2");
        }
    }
}
