package it.gov.scuolesuperioridizagarolo.services;

import dada.bitorario.util.XmlUtil;
import it.gov.scuolesuperioridizagarolo.util.C_Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by stefano on 21/01/2018.
 */
public class UpdateThreadCircolariUtil {
    //http://www.scuolesuperioridizagarolo.gov.it/prova.php?id=XXXX
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        downloadListCircolariComunicazioni("http://www.scuolesuperioridizagarolo.gov.it/prova.php?id=1800");
    }

    @SuppressWarnings("ConstantConditions")
    public static void downloadListCircolariComunicazioni(String url) throws IOException, ParserConfigurationException, SAXException {
        URL u = new URL(url);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        Document doc = dBuilder.parse(new BufferedInputStream(con.getInputStream()));
        con.disconnect();
        final Element documentElement = doc.getDocumentElement();

        final NodeList childNodes = documentElement.getChildNodes();


        //elenco articoli
        final Element articles = XmlUtil.searchFirstByTagName(childNodes, "articles");
        final List<Element> article = XmlUtil.searchByTagName(articles.getChildNodes(), "article");
        for (Element a : article) {
            System.out.println("=========================");
            final String article_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-id").getTextContent();
            final String article_created = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-created").getTextContent();
            final String category_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "category-id").getTextContent();
            final String category_title = XmlUtil.searchFirstByTagName(a.getChildNodes(), "category-title").getTextContent();
            final String article_url = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-url").getTextContent();
            final String article_title = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-title").getTextContent();
            final String article_content = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-content").getTextContent();

            String article_content_decode = new String(C_Base64.decode(article_content), "UTF-8");
            System.out.println(article_id);
            System.out.println(article_title);
            System.out.println(article_url);
            //System.out.println(article_content);
            //System.out.println(article_content_decode);
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        //elenco tag
        final Element tags = XmlUtil.searchFirstByTagName(childNodes, "tags");
        final List<Element> tag = XmlUtil.searchByTagName(tags.getChildNodes(), "tag-map");
        for (Element a : tag) {
            System.out.println("=========================");
            final String article_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-id").getTextContent();
            final String tag_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "tag-id").getTextContent();
            final String tag_title = XmlUtil.searchFirstByTagName(a.getChildNodes(), "tag-title").getTextContent();

            System.out.println(article_id);
            System.out.println(tag_id);
            System.out.println(tag_title);
        }

        //elenco attachment
        final Element attachments = XmlUtil.searchFirstByTagName(childNodes, "attachments");
        final List<Element> attachment = XmlUtil.searchByTagName(attachments.getChildNodes(), "attachment-map");
        for (Element a : attachment) {
            System.out.println("=========================");

            final String xxxx_filename = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filename").getTextContent();
            final String xxxx_url = (XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-url").getTextContent()).replace(" ","%20");
            final String xxxx_filesize = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filesize").getTextContent();
            final String xxxx_filetype = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filetype").getTextContent();
            final String xxxx_state = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-state").getTextContent();
            final String xxxx_article_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-article-id").getTextContent();
            System.out.println(xxxx_article_id);
            System.out.println(xxxx_url);
        }



        /*
        <attachment-map>
  <attachment-filename><![CDATA[Mercoledi 17 gennaio a Palazzo  Rospigliosi.jpg]]></attachment-filename>
  <attachment-url><![CDATA[http://www.scuolesuperioridizagarolo.gov.it/pasw4/attachments/article/1841/Mercoledi 17 gennaio a Palazzo  Rospigliosi.jpg]]></attachment-url>
  <attachment-filesize>212664</attachment-filesize>
  <attachment-filetype>image/jpeg</attachment-filetype>
  <attachment-state>1</attachment-state>
  <attachment-article-id>1841</attachment-article-id>
</attachment-map>
         */

    }
}
