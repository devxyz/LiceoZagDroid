package utility.quiz_moodle;

public class COMPARAZIONE_BASE_BASE implements IQuizMoodle {

    private int base1;
    private int base2;
    private String numero1;
    private String numero2;

    public COMPARAZIONE_BASE_BASE(int base1, int base2, String numero1, String numero2) {

        this.base1 = base1;
        this.base2 = base2;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }


    @Override
    public QuizMoodle_risposta_breve quiz() {
        IBaseConverter_to10 c1 = new IBaseConverter_to10(base1);
        IBaseConverter_to10 c2 = new IBaseConverter_to10(base2);

        int v1 = c1.convert(numero1);
        int v2 = c2.convert(numero2);
        String risposta;
        if (v1 < v2)
            risposta = numero2;
        else if (v1 > v2)
            risposta = numero1;
        else
            risposta = "???";

        return new QuizMoodle_risposta_breve(
                "Confronto " + numero1 + " (" + base1 + ") " + numero2 + " (" + base2 + ")",
                "Considera il numero <b>" + numero1 + " in base " + base1 + "</b> e il numero <b>" + numero2 + " in base " + base2 +"</b>"+
                        ".<br><br>Scrivere quale dei due e' il piu' grande (scrivere il numero senza la base)",

                risposta
        );
    }

    public static void main(String[] args) {
        COMPARAZIONE_BASE_BASE d = new COMPARAZIONE_BASE_BASE(10, 2, "12", "111");
        System.out.println(d.quiz().risposta_corretta);
    }

}
