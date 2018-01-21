package it.gov.scuolesuperioridizagarolo.services;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stefano on 21/01/2018.
 */
public class UpdateThreadCircolariUtil {
    //http://www.scuolesuperioridizagarolo.gov.it/prova.php?id=XXXX
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        downloadListCircolariComunicazioni("http://www.scuolesuperioridizagarolo.gov.it/prova.php?id=1850");
    }

    public static void downloadListCircolariComunicazioni(String url) throws IOException, ParserConfigurationException, SAXException {
        URL u = new URL(url);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        Document doc = dBuilder.parse(new BufferedInputStream(con.getInputStream()));
        con.disconnect();
        final Element documentElement = doc.getDocumentElement();

        final NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            final Node item = childNodes.item(i);
            if (item instanceof Element) {
                final Element e = (Element) item;
                System.out.println(e.getTagName());

                switch (e.getTagName()) {
                    case "articles": {
                        final NodeList childNodes1 = item.getChildNodes();
                        for (int j = 0; j < childNodes1.getLength(); j++) {

                        }
                        break;
                    }

                }

            }

        }


        System.out.println(documentElement);

    }
}
