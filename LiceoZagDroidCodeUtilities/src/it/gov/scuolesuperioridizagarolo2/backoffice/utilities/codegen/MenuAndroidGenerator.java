package it.gov.scuolesuperioridizagarolo2.backoffice.utilities.codegen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by stefano on 18/09/15.
 */
public class MenuAndroidGenerator {

    public static void main(String[] args) throws Exception {
        File destFolder = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidApp/src/it/gov/scuolesuperioridizagarolo/model/menu/impl");

        File f1 = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidApp/res/values/strings_menu_principale.xml");
        File f2 = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidApp/res/values/strings_menu_home_docenti.xml");
        File f3 = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidApp/res/values/strings_menu_home_studenti.xml");
        File f4 = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidApp/res/values/strings_menu_home_genitori.xml");
        File f5 = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidApp/res/values/strings_menu_home_ata.xml");

        genera(f1, destFolder);
        genera(f2, destFolder);
        genera(f3, destFolder);
        genera(f4, destFolder);
        genera(f5, destFolder);
    }

    public static void genera(File f, File destFolder) throws Exception {
        final StringWriter out1 = new StringWriter(1000);
        PrintWriter out = new PrintWriter(out1);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        ArrayList<String> ss = new ArrayList<>();

        DocumentBuilder builder = dbf.newDocumentBuilder();
        File xmlFile = f;
        Document document = builder.parse(xmlFile);
        final Element elem = document.getDocumentElement();
        final NodeList cl = elem.getChildNodes();
        for (int i = 0; i < cl.getLength(); i++) {
            final Node item = cl.item(i);
            if (item instanceof Element) {
                Element e = (Element) item;
                System.out.println(e.getNodeName());
                final NodeList items = e.getChildNodes();
                for (int j = 0; j < items.getLength(); j++) {
                    final Node it = items.item(j);
                    if (it instanceof Element) {
                        Element eit = (Element) it;
                        final Node firstChild = eit.getFirstChild();
                        if (firstChild != null) {
                            final String nodeValue = firstChild.getNodeValue();
                            if (nodeValue != null)
                                ss.add(nodeValue);
                            else
                                ss.add("");

                        } else {
                            ss.add("");
                        }
                    }
                }
            }
        }
        for (String s : ss) {
            //  System.out.println(s);
        }


        final String className = capitalize(f.getName().replaceAll(".xml", ""));


        out.println("package it.gov.scuolesuperioridizagarolo.model.menu.impl;");
        out.println("import it.gov.scuolesuperioridizagarolo.R;\n");
        out.println("import java.util.*;\n");
        out.println("import it.gov.scuolesuperioridizagarolo.model.menu.*;");
        out.println("public class " + className + "{");

        int i = 0;
        while (i < ss.size()) {

            String menuLabel = ss.get(i++);
            String longLabel = ss.get(i++);
            String actionClass = ss.get(i++);
            String imageId = ss.get(i++);
            String flags = ss.get(i++);
            String closeItem = ss.get(i++);
            String nomeCampo = menuLabel.replaceAll("[ .-]+", "_").toUpperCase().replace("(", "").replace(")", "");


            //System.out.println("x1=" + flags);
            //System.out.println("x2=" + closeItem);
            //skip null
            if (actionClass.trim().length() == 0)
                continue;

            ArrayList<String> ffll = new ArrayList<>();
            final String[] split = flags.split(",");
            for (String s : split) {
                s = s.trim();
                if (s.length() > 0)
                    ffll.add("DataMenuInfoFlag." + s);
            }

            String fl = ("new TreeSet<>(Arrays.<DataMenuInfoFlag>asList(" + ffll.toString().replace("[", "").replace("]", "") + ") )");


            //final DataMenuInfoType type, Set<DataMenuInfoFlag > flags
            final String s = MessageFormat.format("     public static final DataMenuInfo {0}= new DataMenuInfo(\n\"{1}\",\n\"{2}\",\n{3}.class,\n{4},\nDataMenuInfoType.search({3}.class),\n{6});",
                    nomeCampo,
                    menuLabel, longLabel.length() == 0 ? menuLabel : longLabel, actionClass, imageId.replace("@drawable/", "R.drawable."), "", fl);
            //System.out.println(s);
            out.println(s);
        }
        out.println("}");

        // System.out.println(out1);

        File fout = new File(destFolder, className + ".java");
        FileWriter fout2 = new FileWriter(fout);
        fout2.write(out1.toString());
        fout2.close();

        System.out.println("=============================================== " + f.getName() + " >>>>> " + fout.getName());

    }


    private static String capitalize0(String s) {
        if (s == null || s.length() == 0) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    private static String capitalize(String s) {
        final String[] split = s.split("[_]+");
        StringBuilder sb = new StringBuilder();
        for (String x : split) {
            System.out.println(sb.append(capitalize0(x)));
        }
        return sb.toString();
    }

}
