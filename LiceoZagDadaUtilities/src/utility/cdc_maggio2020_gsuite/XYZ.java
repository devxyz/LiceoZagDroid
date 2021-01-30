package utility.cdc_maggio2020_gsuite;

public class XYZ {
    public static void main(String[] args) {
        String s="IA\n" +
                "IA\n" +
                "IB\n" +
                "IA \n" +
                "IA \n" +
                "IB\n" +
                "IB \n" +
                "IB \n" +
                "IC\n" +
                "IC\n" +
                "ID\n" +
                "ID\n" +
                "IE\n" +
                "IE\n" +
                "IF\n" +
                "IF\n" +
                "IG\n" +
                "IG\n" +
                "IH\n" +
                "IH\n" +
                "II A\n" +
                "II A\n" +
                "IIA \n" +
                "IIA \n" +
                "IIB\n" +
                "IIB\n" +
                "IIC\n" +
                "IIC\n" +
                "IID\n" +
                "IID\n" +
                "IIE\n" +
                "IIE\n" +
                "IIF\n" +
                "IIF\n" +
                "IIH\n" +
                "IIH\n" +
                "III A\n" +
                "III A\n" +
                "IIIA \n" +
                "IIIA \n" +
                "IIIB\n" +
                "IIIB\n" +
                "IIIC\n" +
                "IIIC\n" +
                "IIID\n" +
                "IIID\n" +
                "IIIE\n" +
                "IIIE\n" +
                "IIIF\n" +
                "IIIF\n" +
                "IIIG\n" +
                "IIIG\n" +
                "IIIH\n" +
                "IIIH\n" +
                "IV A\n" +
                "IV A\n" +
                "IVA \n" +
                "IVA \n" +
                "IVB\n" +
                "IVB\n" +
                "IVC\n" +
                "IVC\n" +
                "IVD\n" +
                "IVD\n" +
                "IVE\n" +
                "IVE\n" +
                "IVF\n" +
                "IVF\n" +
                "IVG\n" +
                "IVG\n" +
                "V A\n" +
                "V A \n" +
                "VA \n" +
                "VA \n" +
                "VB\n" +
                "VB\n" +
                "VB \n" +
                "VB \n" +
                "VC\n" +
                "VC\n" +
                "VD\n" +
                "VD\n" +
                "VE\n" +
                "VE";
        String[] s1 = s.replace(" ", "").split("\n");
        for (String row : s1) {
            System.out.println(row.replace("IV","4").replace("V","5").replace("III","3").replace("II","2").replace("I","1"));
        }
    }
}
