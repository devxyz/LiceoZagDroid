package it.gov.scuolesuperioridizagarolo.services;

import android.content.Context;
import android.util.Log;
import dada.bitorario.util.XmlUtil;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.dao.*;
import it.gov.scuolesuperioridizagarolo.db.ManagerArticolo;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by stefano on 02/02/2018.
 */
public class UpdateThreadArticoliUtil {
    public static void updateAndSave(MainMenuActivity activity) throws Throwable {

        final ScuolaAppDbHelperCallable<Integer> maxRemoteIdRunner = new ScuolaAppDbHelperCallable<Integer>() {
            @Override
            public Integer call(DaoSession session, Context ctx) throws Throwable {
                ManagerArticolo m = new ManagerArticolo(session);
                return m.getMaxRemoteId();
            }
        };
        final Integer lastCurrentId = ScuolaAppDbHelper.runOneTransactionSync(activity, maxRemoteIdRunner);
        Log.e(UpdateThreadArticoliUtil.class.getName(), "lastCurrentId=" + lastCurrentId);
        //===============

        final String fullUrl = activity.getResources().getString(R.string.url_article) + lastCurrentId;
        final UpdateThreadContainer updateThreadContainer = downloadListCircolariComunicazioni(fullUrl);
        Log.e(UpdateThreadArticoliUtil.class.getName(), "Download effettuato");

        //=============================
        final ScuolaAppDbHelperCallable<Integer> saveDB = new ScuolaAppDbHelperCallable<Integer>() {
            @Override
            public Integer call(DaoSession session, Context ctx) throws Throwable {
                ManagerArticolo m = new ManagerArticolo(session);
                m.removeAllLess(updateThreadContainer.minArticleId);


                return null;
            }
        };
        ScuolaAppDbHelper.runOneTransactionSync(activity, saveDB);

    }

    //http://www.scuolesuperioridizagarolo.gov.it/prova.php?id=XXXX
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        downloadListCircolariComunicazioni("http://www.scuolesuperioridizagarolo.gov.it/prova.php?id=1800");
    }


    public static UpdateThreadContainer downloadListCircolariComunicazioni(String url) throws IOException, ParserConfigurationException, SAXException {
        URL u = new URL(url);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        Document doc = dBuilder.parse(new BufferedInputStream(con.getInputStream()));
        con.disconnect();
        final UpdateThreadContainer ris = parseList(doc);
        return ris;
    }

    @SuppressWarnings("ConstantConditions")
    private static UpdateThreadContainer parseList(Document doc) throws UnsupportedEncodingException {

        final UpdateThreadContainer ris = new UpdateThreadContainer();

        final Element documentElement = doc.getDocumentElement();

        final NodeList childNodes = documentElement.getChildNodes();


        //elenco articoli
        final Element minId = XmlUtil.searchFirstByTagName(childNodes, "min-article-id");
        ris.minArticleId = Integer.parseInt(minId.getTextContent().trim());

        final Element articles = XmlUtil.searchFirstByTagName(childNodes, "articles");
        final List<Element> article = XmlUtil.searchByTagName(articles.getChildNodes(), "article");

        //2018-02-01 09:36:52
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Element a : article) {

            ArticoloDB aa = new ArticoloDB();

            final String article_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-id").getTextContent().trim();
            final String article_created = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-created").getTextContent().trim();
            final String category_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "category-id").getTextContent().trim();
            final String category_title = XmlUtil.searchFirstByTagName(a.getChildNodes(), "category-title").getTextContent().trim();
            final String article_url = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-url").getTextContent().trim();
            final String article_title = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-title").getTextContent().trim();
            final String article_content = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-content").getTextContent().trim();
            String article_content_decode = new String(C_Base64.decode(article_content), "UTF-8");
            aa.setCategoryTitle(category_title);
            aa.setContent(article_content_decode);


            try {
                aa.setPubDate(sf.parse(article_created));
            } catch (ParseException e) {
                aa.setPubDate(null);
            }
            aa.setInsertTimestamp(new Date());
            aa.setRemoteCategoryId(Integer.parseInt(category_id));
            aa.setRemoteId(Integer.parseInt(article_id));
            aa.setTitle(article_title);
            aa.setUrl(article_url);

            ris.articoliByRemoteId.put(aa.getRemoteId(), aa);


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
            TagArticoloDB tt = new TagArticoloDB();


            final String article_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-id").getTextContent().trim();
            final String tag_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "tag-id").getTextContent().trim();
            final String tag_title = XmlUtil.searchFirstByTagName(a.getChildNodes(), "tag-title").getTextContent().trim();

            tt.setInsertTimestamp(new Date());
            tt.setTitle(tag_title);
            tt.setRemoteId(Integer.parseInt(tag_id));
            tt.setArticoloDB(ris.articoliByRemoteId.get(Integer.parseInt(article_id)));
            ris.tagsByRemoteId.put(tt.getRemoteId(), tt);


            System.out.println(article_id);
            System.out.println(tag_id);
            System.out.println(tag_title);
        }

        //elenco attachment
        final Element attachments = XmlUtil.searchFirstByTagName(childNodes, "attachments");
        final List<Element> attachment = XmlUtil.searchByTagName(attachments.getChildNodes(), "attachment-map");
        for (Element a : attachment) {
            System.out.println("=========================");
            AttachmentArticoloDB att = new AttachmentArticoloDB();

            final String xxxx_filename = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filename").getTextContent().trim();
            final String xxxx_url = (XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-url").getTextContent().trim()).replace(" ", "%20");
            final String xxxx_filesize = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filesize").getTextContent().trim();
            final String xxxx_filetype = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filetype").getTextContent().trim();
            final String xxxx_state = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-state").getTextContent().trim();
            final String xxxx_article_id = XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-article-id").getTextContent().trim();

            att.setInsertTimestamp(new Date());
            att.setUrl(xxxx_url);
            att.setFilename(xxxx_filename);
            att.setFilesize(Integer.parseInt(xxxx_filesize));
            att.setState(Integer.parseInt(xxxx_state));
            att.setFiletype(xxxx_filetype);
            att.setArticoloDB(ris.articoliByRemoteId.get(Integer.parseInt(xxxx_article_id)));
            ris.attachmentsList.add(att);

            System.out.println(xxxx_article_id);
            System.out.println(xxxx_url);
        }
        return ris;
    }

    private static class UpdateThreadContainer {
        final Map<Integer, ArticoloDB> articoliByRemoteId = new TreeMap<>();
        final Map<Integer, TagArticoloDB> tagsByRemoteId = new TreeMap<>();
        final List<AttachmentArticoloDB> attachmentsList = new ArrayList<>();
        /**
         * id minimo remoto di articoli (al di sotto del quale gli articoli non vanno piu' considerati)
         */
        int minArticleId = -1;

    }
}
