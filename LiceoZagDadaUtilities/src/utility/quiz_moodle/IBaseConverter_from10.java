package utility.quiz_moodle;

public class IBaseConverter_from10 {
    final int base_arrivo;

    public IBaseConverter_from10(int base_arrivo) {
        this.base_arrivo = base_arrivo;
    }

    private String toChar(int n) {
        if (n >= 0 && n <= 9) {
            return "" + n;
        } else {
            String s = (char) ('A' + (n - 10)) + "";
            return s;
        }
    }

    public String convert(int n) {
        String s = "";
        do {
            s = toChar(n % base_arrivo) + s;
            n = n / base_arrivo;
        } while (n != 0);
        return s;

    }

    public static void main(String[] args) {
        IBaseConverter_from10 i = new IBaseConverter_from10(2);
        System.out.println(i.convert(14));
    }
}
