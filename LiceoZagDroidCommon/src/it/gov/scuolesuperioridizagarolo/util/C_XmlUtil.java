package it.gov.scuolesuperioridizagarolo.util;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 02/04/2017.
 */
public class C_XmlUtil {
    public static List<Node> searchBy(NodeList list, NodeListFilter filter) {
        List<Node> n = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            if (filter.accept(list.item(i)))
                n.add(list.item(i));
        }
        return n;
    }

    public static String encodeUrl(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Node searchFirstBy(NodeList list, NodeListFilter filter) {

        for (int i = 0; i < list.getLength(); i++) {
            if (filter.accept(list.item(i)))
                return (list.item(i));
        }
        return null;
    }

    public static Element searchFirstByTagName(NodeList list, final String tagName) {
        final List<Element> elements = searchByTagName(list, tagName);
        if (elements.size() == 0) return null;
        return elements.get(0);
    }

    public static List<Element> searchByTagName(NodeList list, final String tagName) {
        //noinspection unchecked
        return (List) searchBy(list, new NodeListFilter() {
            @Override
            public boolean accept(Node n) {
                if (n instanceof Element) {
                    Element e = (Element) n;
                    if (e.getTagName().equals(tagName))
                        return true;
                }
                return false;
            }
        });
    }

    public static String toXmlString(Node node) {
        TransformerFactory transFactory = TransformerFactory.newInstance();


        Transformer transformer = null;
        try {
            transformer = transFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new IllegalArgumentException(e);
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        StringWriter buffer = new StringWriter();
        try {
            transformer.transform(new DOMSource(node),
                    new StreamResult(buffer));
        } catch (TransformerException e) {
            throw new IllegalArgumentException(e);
        }
        return buffer.toString();
    }

    public interface NodeListFilter {
        boolean accept(Node n);
    }
}
