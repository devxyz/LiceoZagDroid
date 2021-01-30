package utility.quiz_moodle;

public class BASE_BASE implements IQuizMoodle {

    final String numero;
    private int base_partenza;
    private int base_destinazione;

    public BASE_BASE(int base_partenza, int base_destinazione, String numero) {
        this.numero = numero;
        this.base_partenza = base_partenza;
        this.base_destinazione = base_destinazione;
    }


    @Override
    public QuizMoodle_risposta_breve quiz() {
        IBaseConverter_BASE1_BASE2 c = new IBaseConverter_BASE1_BASE2(base_partenza, base_destinazione);
        return new QuizMoodle_risposta_breve(
                "Conversione da base " + base_partenza + " a base " + base_destinazione + ": " + numero,

                "Convertire il numero <b>" + numero + " dalla base " + base_partenza + " alla base " + base_destinazione+"</b>." +
                        "<br><br><i>Nella risposta riportare esclusivamente le cifre del risultato, eliminando gli zeri non significativi (quelli di sinistra) ed eventuali indicazioni della base.</i>",

                c.convert(numero)
        );
    }

    public static void main(String[] args) {
        BASE_BASE d = new BASE_BASE(10, 2, "76");
        System.out.println(d.quiz().risposta_corretta);
    }

}
