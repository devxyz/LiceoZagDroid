package utility.quiz_moodle;

public class IBaseConverter_BASE1_BASE2 {
    final int base_partenza;
    final int base_arrivo;

    public IBaseConverter_BASE1_BASE2(int base_partenza, int base_arrivo) {
        this.base_partenza = base_partenza;
        this.base_arrivo = base_arrivo;
    }

    public String convert(String n) {
        IBaseConverter_to10 c1 = new IBaseConverter_to10(base_partenza);
        IBaseConverter_from10 c2 = new IBaseConverter_from10(base_arrivo);
        return c2.convert(c1.convert(n));
    }

    public static void main(String[] args) {
        IBaseConverter_BASE1_BASE2 i = new IBaseConverter_BASE1_BASE2(10, 2);
        System.out.println(i.convert("14"));
    }
}
