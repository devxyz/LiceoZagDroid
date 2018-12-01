package it.gov.scuolesuperioridizagarolo.services;

import android.content.Context;
import android.util.Log;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.dao.*;
import it.gov.scuolesuperioridizagarolo.dao.customType.*;
import it.gov.scuolesuperioridizagarolo.db.ManagerArticolo;
import it.gov.scuolesuperioridizagarolo.parser.parserArticolo.*;
import it.gov.scuolesuperioridizagarolo.util.C_Base64;
import it.gov.scuolesuperioridizagarolo.util.C_XmlUtil;
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
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by stefano on 02/02/2018.
 */
public class UpdateThreadArticoliUtil {


    public static void updateAndSave(MainMenuActivity activity) throws Throwable {

        final ScuolaAppDbHelperCallable<Date> maxRemoteIdRunner = new ScuolaAppDbHelperCallable<Date>() {
            @Override
            public Date call(DaoSession session, Context ctx) throws Throwable {
                ManagerArticolo m = new ManagerArticolo(session);
                return m.getModifiedRemoteDate();
            }
        };
        final Date lastModifiedDate = ScuolaAppDbHelper.runOneTransactionSync(activity, maxRemoteIdRunner);
        Log.e(UpdateThreadArticoliUtil.class.getName(), "lastModifiedDate=" + lastModifiedDate);
        //===============

        final String fullUrl;
        if (lastModifiedDate==null)
            fullUrl = activity.getResources().getString(R.string.url_article) + "1970-01-01 00:00:00";
        else {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fullUrl = activity.getResources().getString(R.string.url_article) + sf.format(lastModifiedDate);
        }

        final UpdateThreadContainer updateThreadContainer = downloadListCircolariComunicazioni(fullUrl);
        Log.e(UpdateThreadArticoliUtil.class.getName(), "Download effettuato");

        //=============================
        final ScuolaAppDbHelperCallable<Integer> saveDB = new ScuolaAppDbHelperCallable<Integer>() {
            @Override
            public Integer call(DaoSession session, Context ctx) throws Throwable {

                //rimuove articoli vecchi
                ManagerArticolo m = new ManagerArticolo(session);
                m.removeArticoliPrecedentiAdID(updateThreadContainer.minArticleId);
                Log.e(UpdateThreadArticoliUtil.class.getName(), "Cancellazione articoli vecchi");

                //crea articoli
                final ArrayList<ArticoloDB> values = new ArrayList<>(updateThreadContainer.articoliByRemoteId.values());

                for (ArticoloDB aa : values) {
                    final ArticoloDetails details = aa.getDetails();
                    if (details == null) {
                        throw new IllegalArgumentException("Dettagli null per " + aa);
                    }
                    try {
                        details.check();
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Errore per dettagli " + aa, e);
                    }
                }

                final ArticoloDBDao articoloDBDao = session.getArticoloDBDao();
                for (ArticoloDB aa : values) {
                    articoloDBDao.insert(aa);
                }
                Log.e(UpdateThreadArticoliUtil.class.getName(), "Inserimento articoli");

                return null;
            }
        };
        ScuolaAppDbHelper.runOneTransactionSync(activity, saveDB);

    }

    //http://www.scuolesuperioridizagarolo.gov.it/prova.php?id=XXXX
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, it.gov.scuolesuperioridizagarolo.parser.impl.ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(new Date()));

        if (true)
            return;


        final UpdateThreadContainer x = downloadListCircolariComunicazioni("http://www.scuolesuperioridizagarolo.gov.it/circolari.php?id=1000");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        for (Map.Entry<Integer, ArticoloDB> s : x.articoliByRemoteId.entrySet()) {
            final ArticoloDB aa = s.getValue();

            final ArticoloDetails details = aa.getDetails();
            if (details == null) {
                throw new IllegalArgumentException("Dettagli null per " + aa);
            }
            try {
                details.check();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Errore per dettagli " + aa, e);
            }

            //System.out.println(a);
            System.out.println(aa.getType() + "\n" + aa.getTitle());
            System.out.println(aa.getDetails());


            System.out.println("====================");
            System.out.flush();
        }
    }


    public static UpdateThreadContainer downloadListCircolariComunicazioni(String url) throws IOException, ParserConfigurationException, SAXException, it.gov.scuolesuperioridizagarolo.parser.impl.ParseException {
        URL u = new URL(url);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        Document doc = dBuilder.parse(new BufferedInputStream(con.getInputStream()));
        con.disconnect();
        final UpdateThreadContainer ris = parseAndUpdateList(doc);
        return ris;
    }

    @SuppressWarnings("ConstantConditions")
    private static UpdateThreadContainer parseAndUpdateList(Document doc) throws UnsupportedEncodingException, it.gov.scuolesuperioridizagarolo.parser.impl.ParseException {

        final UpdateThreadContainer ris = new UpdateThreadContainer();

        final Element documentElement = doc.getDocumentElement();

        final NodeList childNodes = documentElement.getChildNodes();


        //elenco articoli
        final Element minId = C_XmlUtil.searchFirstByTagName(childNodes, "min-article-id");
        ris.minArticleId = Integer.parseInt(minId.getTextContent().trim());

        final Element articles = C_XmlUtil.searchFirstByTagName(childNodes, "articles");
        final List<Element> article = C_XmlUtil.searchByTagName(articles.getChildNodes(), "article");

        //2018-02-01 09:36:52
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Element a : article) {

            ArticoloDB aa = new ArticoloDB();

            final String article_id = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-id").getTextContent().trim();
            final String article_created = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-created").getTextContent().trim();
            final String category_id = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "category-id").getTextContent().trim();
            final String category_title = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "category-title").getTextContent().trim();
            final String article_url = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-url").getTextContent().trim();
            final String article_title = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-title").getTextContent().trim();
            final String article_content = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-content").getTextContent().trim();
            String article_content_decode = new String(C_Base64.decode(article_content), "UTF-8");
            aa.setCategoryTitle(category_title);
            aa.setContent(article_content_decode);


            try {
                aa.setPubDate(sf.parse(article_created));
            } catch (ParseException e) {
                aa.setPubDate(new Date());//data corrente se mancante
            }
            aa.setId(Long.parseLong(article_id));
            aa.setInsertTimestamp(new Date());
            aa.setRemoteCategoryId(Integer.parseInt(category_id));
            aa.setRemoteId(Integer.parseInt(article_id));
            aa.setTitle(article_title);
            aa.setUrl(article_url);


            switch (category_title.toUpperCase().trim()) {
                case "EVENTI": {


                    ArticoloDetailsEvento x = new ArticoloDetailsEvento();

                    //parsng titolo
                    final ContainerParsedEvento parse1 = (ContainerParsedEvento) ParserArticolo.parse(ArticoloType.EVENTO, aa.getTitle());
                    x.oggetto = parse1.oggetto.toString();
                    x.dataEvento = parse1.dataEvento;
                    x.addWordsLowerCase(parse1.termini);

                    aa.setDetails(x);
                    aa.setType(ArticoloType.EVENTO);

                    //assegna data
                    if (x.dataEvento == null) {
                        x.dataEvento = aa.getPubDate();
                    }
                    if (x.dataEvento == null) {
                        x.dataEvento = new Date();
                    }
                    aa.setDate(x.dataEvento);

                    //valorizza le date
                    if (aa.getDate() == null) {
                        aa.setDate(new Date());
                    }
                    if (aa.getPubDate() == null) {
                        aa.setPubDate(new Date());
                    }


                    break;
                }
                case "AVVISI/COMUNICAZIONI": {
                    ArticoloDetailsAvviso x = new ArticoloDetailsAvviso();

                    //parsng titolo
                    final ContainerParsedAvviso parse1 = (ContainerParsedAvviso) ParserArticolo.parse(ArticoloType.AVVISO, aa.getTitle());
                    x.oggetto = parse1.oggetto.toString();
                    x.dataAvviso = parse1.dataAvviso;
                    x.addWordsLowerCase(parse1.termini);

                    aa.setDetails(x);
                    aa.setType(ArticoloType.AVVISO);

                    //assegna data
                    if (x.dataAvviso == null) {
                        x.dataAvviso = aa.getPubDate();
                    }
                    if (x.dataAvviso == null) {
                        x.dataAvviso = new Date();
                    }
                    aa.setDate(x.dataAvviso);

                    //valorizza le date
                    if (aa.getDate() == null) {
                        aa.setDate(new Date());
                    }
                    if (aa.getPubDate() == null) {
                        aa.setPubDate(new Date());
                    }


                    break;
                }
                case "CIRCOLARI": {

                    ArticoloDetailsCircolare x = new ArticoloDetailsCircolare();

                    //parsng titolo
                    final ContainerParsedCircolare parse1 = (ContainerParsedCircolare) ParserArticolo.parse(ArticoloType.CIRCOLARE, aa.getTitle());
                    x.oggetto = parse1.oggetto.toString();
                    x.dataCircolare = parse1.dataCircolare;
                    x.numeroCircolare = parse1.numCircolare == null ? 0 : parse1.numCircolare;
                    x.addWordsLowerCase(parse1.termini);


                    aa.setDetails(x);
                    aa.setType(ArticoloType.CIRCOLARE);


                    //assegna data
                    if (x.dataCircolare == null) {
                        x.dataCircolare = aa.getPubDate();
                    }
                    if (x.dataCircolare == null) {
                        x.dataCircolare = new Date();
                    }
                    aa.setDate(x.dataCircolare);

                    //valorizza le date
                    if (aa.getDate() == null) {
                        aa.setDate(new Date());
                    }
                    if (aa.getPubDate() == null) {
                        aa.setPubDate(new Date());
                    }

                    break;
                }
                default: {
                    ArticoloDetailsGenerico x = new ArticoloDetailsGenerico();

                    //parsng titolo
                    final ContainerParsedArticolo parse1 = ParserArticolo.parse(ArticoloType.GENERICO, aa.getTitle());
                    x.oggetto = parse1.oggetto.toString();
                    x.addWordsLowerCase(parse1.termini);


                    aa.setDetails(x);
                    aa.setType(ArticoloType.GENERICO);

                    aa.setDate(aa.getPubDate());

                    //valorizza le date
                    if (aa.getDate() == null) {
                        aa.setDate(new Date());
                    }
                    if (aa.getPubDate() == null) {
                        aa.setPubDate(new Date());
                    }

                    break;
                }
            }


            //ArticoloTypeCircolare t=new ArticoloTypeCircolare()
            //aa.setJsonClass(t.parseClass().getName());
            //aa.setJsonContent(t.parseClass().getName());

            ris.articoliByRemoteId.put(aa.getRemoteId(), aa);
            System.out.println("======================================");
            System.out.println(article_id);
            System.out.println(article_title);
            System.out.println(article_url);
            System.out.println(article_content);
            System.out.println(article_content_decode);
        }

        //System.out.println();
        //System.out.println();
        // System.out.println();
        // System.out.println();

        //elenco tag
        final Element tags = C_XmlUtil.searchFirstByTagName(childNodes, "tags");
        final List<Element> tag = C_XmlUtil.searchByTagName(tags.getChildNodes(), "tag-map");
        for (Element a : tag) {
            //System.out.println("=========================");


            final String article_id = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "article-id").getTextContent().trim();
            final String tag_id = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "tag-id").getTextContent().trim();
            final String tag_title = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "tag-title").getTextContent().trim();

            final ArticoloDetails d = ris.articoliByRemoteId.get(Integer.parseInt(article_id)).getDetails();
            final ArticoloTagDetails a1 = new ArticoloTagDetails();
            d.addTag(a1);
            a1.setInsertTimestamp(new Date());
            a1.setTag(tag_title);
            a1.setRemoteTagId(Integer.parseInt(tag_id));


            //tt.setArticoloDB(ris.articoliByRemoteId.get(Integer.parseInt(article_id)));
            //ris.tagsByRemoteArticleId.add(new C_Pair<>(tt, Integer.parseInt(article_id)));

/*
            System.out.println(article_id);
            System.out.println(tag_id);
            System.out.println(tag_title);
            */
        }

        //elenco attachment
        final Element attachments = C_XmlUtil.searchFirstByTagName(childNodes, "attachments");
        final List<Element> attachment = C_XmlUtil.searchByTagName(attachments.getChildNodes(), "attachment-map");
        for (Element a : attachment) {
            //System.out.println("=========================");
            ArticoloAttachmentDetails att = new ArticoloAttachmentDetails();

            final String xxxx_filename = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filename").getTextContent().trim();
            final String xxxx_url = (C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-url").getTextContent().trim()).replace(" ", "%20");
            final String xxxx_filesize = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filesize").getTextContent().trim();
            final String xxxx_filetype = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-filetype").getTextContent().trim();
            final String xxxx_state = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-access").getTextContent().trim();
            final String xxxx_article_id = C_XmlUtil.searchFirstByTagName(a.getChildNodes(), "attachment-article-id").getTextContent().trim();


            att.setInsertTimestamp(new Date());
            att.setUrl(xxxx_url);
            att.setFilename(xxxx_filename);
            att.setFilesize(Integer.parseInt(xxxx_filesize));
            att.setState(Integer.parseInt(xxxx_state));
            att.setFiletype(xxxx_filetype);

            final ArticoloDetails d = ris.articoliByRemoteId.get(Integer.parseInt(xxxx_article_id)).getDetails();
            //att.setArticoloDB(ris.articoliByRemoteId.get(Integer.parseInt(xxxx_article_id)));
            d.addArticoloAttachment(att);

            /*
            System.out.println(xxxx_article_id);
            System.out.println(xxxx_url);
            */
        }
        return ris;
    }


    private static class UpdateThreadContainer {
        final Map<Integer, ArticoloDB> articoliByRemoteId = new TreeMap<>();
        /**
         * id minimo remoto di articoli (al di sotto del quale gli articoli non vanno piu' considerati)
         */
        int minArticleId = -1;

    }
}
