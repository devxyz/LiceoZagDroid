package utility.didattica_online.presentazione_1B;

public class Presentazione1B {
    public static void main(String[] args) {
        String s = "Bascandura Dennis Andrei\n" +
                "Basto Martina\n" +
                "Boccia Gabriele\n" +
                "Carbone Andrea\n" +
                "Carbone Matteo\n" +
                "Chiapparelli Paolo\n" +
                "Chiapparelli Simone\n" +
                "Cristofari Alessandro\n" +
                "Danaj Mateo\n" +
                "Esposito Alessio\n" +
                "Federici Gianluca\n" +
                "Foschi Francesco\n" +
                "Leone Francesca\n" +
                "Lucarelli Gabriele\n" +
                "Mammetti Filippo\n" +
                "Mancinotti Christian\n" +
                "Marcelli Alessandro\n" +
                "Michelessi Francesco\n" +
                "Mistura Diego\n" +
                "Nobile Luca\n" +
                "Orsi Angelica\n" +
                "Penna Isabel\n" +
                "Sabellico Giovanna\n" +
                "Scaramella Federico\n" +
                "Sgro Lorenzo\n" +
                "Tamiro Federico\n" +
                "Zimpi Tommaso";
        String[] split = s.split("\n");
        String template = "var p=SlidesApp.create('NOME_FILE');\n" +
                "  var slide=p.getSlides()[0];\n" +
                "  var shape = slide.insertShape(SlidesApp.ShapeType.TEXT_BOX, 100, 300, 400, 60);\n" +
                "  var textRange = shape.getText();\n" +
                "  textRange.setText('NOME_STUDENTE');\n" +
                "  textRange.getTextStyle().setFontSize(40);\n" +
                "  textRange.getTextStyle().setBackgroundColor(255, 255, 0);\n" +
                "  Logger.log('Start: ' + textRange.getStartIndex() + '; End: ' +\n" +
                "     textRange.getEndIndex() + '; Content: ' + textRange.asString());\n" +
                "var subRange = textRange.getRange(0, 5);\n" +
                "Logger.log('Sub-range Start: ' + subRange.getStartIndex() + '; Sub-range End: ' +\n" +
                "    subRange.getEndIndex() + '; Sub-range Content: ' + subRange.asString());";

        String template3 = "var p=SlidesApp.create('NOME_FILE');\n" +
                "  var slide=p.getSlides()[0];\n" +
                "  var shape = slide.insertShape(SlidesApp.ShapeType.TEXT_BOX, 100, 300, 400, 60);\n" +
                "  var textRange = shape.getText();\n" +
                "  textRange.setText('NOME_STUDENTE');\n" +
                "  textRange.getTextStyle().setFontSize(40);\n" +
                "  textRange.getTextStyle().setBackgroundColor(0, 64, 255);\n" +
                "  Logger.log('Start: ' + textRange.getStartIndex() + '; End: ' +\n" +
                "     textRange.getEndIndex() + '; Content: ' + textRange.asString());\n" +
                "var subRange = textRange.getRange(0, 5);\n" +
                "Logger.log('Sub-range Start: ' + subRange.getStartIndex() + '; Sub-range End: ' +\n" +
                "    subRange.getEndIndex() + '; Sub-range Content: ' + subRange.asString());";

        String template2 = "var p=SlidesApp.create('NOME_FILE');\n" +
                "  var slide=p.getSlides()[0];\n" +
                "  var shape = slide.insertShape(SlidesApp.ShapeType.TEXT_BOX, 100, 300, 400, 60);\n" +
                "  var textRange = shape.getText();\n" +
                "  textRange.setText('NOME_STUDENTE');\n" +
                "  textRange.getTextStyle().setFontSize(40);\n" +
                "  textRange.getTextStyle().setBackgroundColor(255, 0, 0);\n" +
                "  Logger.log('Start: ' + textRange.getStartIndex() + '; End: ' +\n" +
                "     textRange.getEndIndex() + '; Content: ' + textRange.asString());\n" +
                "var subRange = textRange.getRange(0, 5);\n" +
                "Logger.log('Sub-range Start: ' + subRange.getStartIndex() + '; Sub-range End: ' +\n" +
                "    subRange.getEndIndex() + '; Sub-range Content: ' + subRange.asString());";

        for (String s1 : split) {
            String nome = s1;
            s1 = s1.replaceAll("[ ]+", "_");
            System.out.println("{\n" +
                    template.replace("NOME_FILE", "COMPITO-1_"+ s1.toLowerCase())
                            .replace("NOME_STUDENTE",nome+" Esercizio 1")
                    + "\n}\n"
            );
        }
        for (String s1 : split) {
            String nome = s1;
            s1 = s1.replaceAll("[ ]+", "_");
            System.out.println("{\n" +
                    template2.replace("NOME_FILE", "COMPITO-2_"+ s1.toLowerCase() )
                            .replace("NOME_STUDENTE", nome+" Esercizio 2")
                    + "\n}\n"
            );
        }
        for (String s1 : split) {
            String nome = s1;
            s1 = s1.replaceAll("[ ]+", "_");
            System.out.println("{\n" +
                    template3.replace("NOME_FILE", "COMPITO-3_"+ s1.toLowerCase() )
                            .replace("NOME_STUDENTE", nome+" Esercizio 3")
                    + "\n}\n"
            );
        }
    }
}
