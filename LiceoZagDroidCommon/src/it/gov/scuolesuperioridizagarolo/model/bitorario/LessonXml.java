package it.gov.scuolesuperioridizagarolo.model.bitorario;

import it.gov.scuolesuperioridizagarolo.util.C_XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by stefano on 02/04/2017.
 */
public class LessonXml {
    private static final XPathExpressionCache filterSubject = new XPathExpressionCache("SUBJECT");
    private static final XPathExpressionCache filterDuration = new XPathExpressionCache("DURATION");
    private static final XPathExpressionCache filterTeacher = new XPathExpressionCache("TEACHER/NAME");
    private static final XPathExpressionCache filterClassroom = new XPathExpressionCache("GROUP/NAME");
    private static final XPathExpressionCache filterRoom = new XPathExpressionCache("ROOM/NAME");

    private final Element root;
    private final Document doc;

    public LessonXml(Element root, Document doc) {
        this.root = root;
        this.doc = doc;
    }

    public Element getRoot() {
        return root;
    }

    public String getSubject() {
        return filterSubject.getNode(root).getAttributes().getNamedItem("value").getNodeValue();
    }

    public void setSubject(String s) {
        final Node value = filterSubject.getNode(root).getAttributes().getNamedItem("value");
        value.setNodeValue(s);
    }

    public String getTeacher() {
        final Node value = filterTeacher.getNode(root).getAttributes().getNamedItem("value");
        return value.getNodeValue();
    }

    public void setTeacher(String t) {
        final Node value = filterTeacher.getNode(root).getAttributes().getNamedItem("value");
        value.setNodeValue(t);
    }

    public String getClassroom() {
        final Node node = filterClassroom.getNode(root);
        if (node == null) return null;
        return node.getAttributes().getNamedItem("value").getNodeValue();
    }

    public void setClassroom(String c) {
        Element node = (Element) filterClassroom.getNode(root);
        if (node == null) {


            final Element group = doc.createElement("GROUP");
            root.appendChild(group);

            node = doc.createElement("NAME");
            group.appendChild(node);
            node.setAttributeNode(doc.createAttribute("value"));
        }

        node.getAttributes().getNamedItem("value").setNodeValue(c);
    }

    public String getRoom() {
        final Node node = filterRoom.getNode(root);
        if (node == null) return null;
        return node.getAttributes().getNamedItem("value").getNodeValue();
    }

    public void setRoom(String c) {
        Element node = (Element) filterRoom.getNode(root);
        if (c == null) {
            if (node == null)
                return;
            else {
                node.getParentNode().removeChild(node);
            }
        }


        if (node == null) {

            //cerca TIMEFRAMES
            Node before = null;
            {

                final NodeList ll = root.getChildNodes();
                for (int i = 0; i < ll.getLength(); i++) {
                    final Node item = ll.item(i);
                    if (item.getNodeName().equals("TIMEFRAMES")) {
                        before = item;
                        break;
                    }
                }
                if (before == null)
                    throw new IllegalArgumentException("Non trovato timeframes in " + C_XmlUtil.toXmlString(root));
            }


            final Element group = doc.createElement("ROOM");
            root.insertBefore(group, before);

            node = doc.createElement("NAME");
            group.appendChild(node);
            node.setAttributeNode(doc.createAttribute("value"));
        }

        node.getAttributes().getNamedItem("value").setNodeValue(c);
    }

    public int getDuration() {
        return Integer.parseInt(filterDuration.getNode(root).getAttributes().getNamedItem("value").getNodeValue());
    }

    public void setDuration(int d) {
        filterDuration.getNode(root).getAttributes().getNamedItem("value").setNodeValue("" + d);
    }

}
