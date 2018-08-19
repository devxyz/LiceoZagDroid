package it.gov.scuolesuperioridizagarolo.model.bitorario;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;

/**
 * Created by stefano on 02/04/2017.
 */
public class XPathExpressionCache {
    private final String expression;
    private XPathExpression exp;
    private XPathExpressionException error = null;

    public XPathExpressionCache(String expression) {
        this.expression = expression;

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        try {
            exp = xpath.compile(expression);
        } catch (XPathExpressionException e) {
            error = e;
            exp = null;
        }
    }

    /**
     * restituisce l'espressione (se si tratta di una stringa)
     *
     * @param doc
     * @return
     */
    public String getString(Element doc) {

        if (error != null) throw new IllegalArgumentException(error);

        try {
            return (String) exp.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * restituisce l'espressione (se si tratta di una stringa)
     *
     * @param doc
     * @return
     */
    public Node getNode(Element doc) {
        if (error != null) throw new IllegalArgumentException(error);

        try {
            return (Node) exp.evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * restituisce l'espressione (se si tratta di una stringa)
     *
     * @param doc
     * @return
     */
    public NodeList getNodeList(Element doc) {
        if (error != null) throw new IllegalArgumentException(error);

        try {
            return (NodeList) exp.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
