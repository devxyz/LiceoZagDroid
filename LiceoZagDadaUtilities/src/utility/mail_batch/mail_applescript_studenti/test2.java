package utility.mail_batch.mail_applescript_studenti;

import java.util.ArrayList;

public class test2 {
    static String s=" Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che David Abunei\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 12 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Auricchio Lorenzo\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 20 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Callegari Luca\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 8 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Cerboni Federico\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 16 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Cicerchia Nicolas\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 20 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Popa Cristian Eduard\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 16 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Curzi Nicolò\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 12 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Gaetano Davide\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 16 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Lullo Valerio\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 16 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Passa Nikolas\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 16 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Petrucci Lorenzo\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 8 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Pop Maximilian Ioan\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 16 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Schioppa Matteo\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 20 ore\n" +
            "  \n" +
            " Roma, 26 Maggio 2020\n" +
            "Lazio Innova Spa Direttore operativo Spazio Attivo Open Innovation\n" +
            "Si attesta che Stocco Matteo\n" +
            "ha partecipato al progetto Startup@School Academy per un totale di 16 ore\n" +
            "  ";

    public static void main(String[] args) {
        String[] split = s.split("\n");
        ArrayList<String> nomi=new ArrayList<>();
        for (String s1 : nomi) {
            if(s1.trim().toLowerCase().startsWith("Si attesta che".toLowerCase()))
            System.out.println(s1);
        }
    }
}
