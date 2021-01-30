package utility.quiz_moodle;

public class IBaseConverter_to10 {
    final int base_partenza;

    public IBaseConverter_to10(int base_partenza) {
        this.base_partenza = base_partenza;
    }

    private int toInt(char n) {
        n = Character.toUpperCase(n);
        if (n >= '0' && n <= '9') {
            int i = n - '0';
            if (i >= base_partenza)
                throw new IllegalArgumentException("Errore valori non combatibili con base " + base_partenza + ": " + n);
            return i;
        } else {
            int i = (n - 'A') + 10;
            if (i >= base_partenza)
                throw new IllegalArgumentException("Errore valori non combatibili con base " + base_partenza + ": " + n);
            return i;
        }
    }

    public int convert(String n) {
        int ris = 0;
        int i = 0;
        for (int j = n.length() - 1; j >= 0; j--) {
            int v = toInt(n.charAt(j));
            ris += v * Math.pow(base_partenza, i);
            i++;
        }
        return ris;
    }

    public static void main(String[] args) {
        IBaseConverter_to10 c = new IBaseConverter_to10(2);
        System.out.println(c.convert("210"));
    }
}
