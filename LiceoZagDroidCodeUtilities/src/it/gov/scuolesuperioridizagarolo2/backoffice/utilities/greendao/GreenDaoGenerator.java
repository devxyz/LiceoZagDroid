package it.gov.scuolesuperioridizagarolo2.backoffice.utilities.greendao;

import org.greenrobot.greendao.generator.*;


/**
 * Created by stefano on 27/04/15.
 */
public class GreenDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(20180204, "it.gov.scuolesuperioridizagarolo.dao");

        //cache file
        final Entity cacheFile = schema.addEntity("CacheFileDB");
        {
            cacheFile.addIdProperty().autoincrement();
            cacheFile.addStringProperty("url").notNull().unique().index();
            cacheFile.addStringProperty("filename").notNull();
            cacheFile.addDateProperty("insertion_date").notNull();
            cacheFile.addBooleanProperty("load_complete").notNull();
        }

        //cache file
        final Entity timetableFile = schema.addEntity("TimetableDB");
        {
            timetableFile.addIdProperty().autoincrement();
            timetableFile.addStringProperty("url").notNull().unique().index();
            timetableFile.addLongProperty("remoteId").notNull().unique().index();
            timetableFile.addDateProperty("createDate").notNull().unique().index();
            timetableFile.addStringProperty("filename").notNull().unique().index();
            timetableFile.addDateProperty("startDate").notNull();
            timetableFile.addDateProperty("endDate").notNull();
            timetableFile.addByteArrayProperty("data").notNull();
        }


        //articolo
        final Entity articolo = schema.addEntity("ArticoloDB");
        {
            articolo.addIdProperty();
            articolo.addStringProperty("title").notNull();
            articolo.addDateProperty("pubDate").notNull();
            articolo.addDateProperty("insertTimestamp").notNull();
            articolo.addIntProperty("remoteId").unique().notNull();
            articolo.addIntProperty("remoteCategoryId").notNull();
            articolo.addStringProperty("categoryTitle").notNull();
            articolo.addStringProperty("content").notNull();
            articolo.addStringProperty("url").notNull();
        }

        final Entity tag = schema.addEntity("TagArticoloDB");
        {
            tag.addIdProperty();
            tag.addStringProperty("title").notNull();
            tag.addIntProperty("remoteId").unique().notNull();
            tag.addDateProperty("insertTimestamp").notNull();
            final Property.PropertyBuilder articleId = tag.addLongProperty("fk_articleId").index().notNull();
            tag.addToOne(articolo, articleId.getProperty());
        }

        final Entity attachment = schema.addEntity("AttachmentArticoloDB");
        {
            attachment.addIdProperty();
            attachment.addStringProperty("filename").notNull();
            attachment.addStringProperty("url").notNull();
            attachment.addIntProperty("filesize").notNull();
            attachment.addIntProperty("state").notNull();
            attachment.addDateProperty("insertTimestamp").notNull();
            attachment.addStringProperty("filetype").notNull();
            final Property.PropertyBuilder articleId = attachment.addLongProperty("fk_articleId").index().notNull();
            attachment.addToOne(articolo, articleId.getProperty());
        }


        //circolare
        @Deprecated
        final Entity circolare = schema.addEntity("CircolareDB");
        {
            circolare.addIdProperty().autoincrement();
            circolare.addDateProperty("dataInserimento").notNull();


            final Property data = circolare.addDateProperty("data").notNull().getProperty();
            final Property numero = circolare.addIntProperty("numero").notNull().getProperty();
            circolare.addStringProperty("titolo").notNull();
            circolare.addStringProperty("testo");
            circolare.addLongProperty("token").notNull();
            circolare.addStringProperty("url").notNull().unique();
            circolare.addBooleanProperty("flagContenutoLetto").notNull();

            circolare.addStringProperty("key").notNull().unique();

            //index
            Index indexCircolare = new Index();
            indexCircolare.makeUnique();
            indexCircolare.addProperty(data);
            indexCircolare.addProperty(numero);
            circolare.addIndex(indexCircolare);
        }


        //termine
        @Deprecated
        final Entity termine = schema.addEntity("TermineDB");
        {
            termine.addIdProperty().autoincrement();
            termine.addStringProperty("termine").notNull().unique().index();
            termine.addStringProperty("radice").notNull();
        }


        //termine
        @Deprecated
        final Entity news = schema.addEntity("NewsDB");
        {
            news.addIdProperty().autoincrement();

            news.addStringProperty("titolo").notNull();
            news.addStringProperty("link").notNull();
            news.addDateProperty("pubDate").notNull();
            news.addStringProperty("testo").notNull();
            news.addStringProperty("contenuto");
            news.addStringProperty("fullimageLink");
            news.addStringProperty("thumbimageLink");
            news.addStringProperty("key").notNull();
            news.addBooleanProperty("flagContenutoLetto").notNull();
            news.addDateProperty("dataInserimento").notNull();
            news.addLongProperty("token").notNull();
        }


        //tabella intermedia


        {
            @Deprecated
            final Entity circolareTermine = schema.addEntity("CircolareContieneTermineDB");
            Property id_circolare = circolareTermine.addLongProperty("_id_circolare").notNull().getProperty();
            Property id_termine = circolareTermine.addLongProperty("_id_termine").notNull().getProperty();
            circolareTermine.addIdProperty().autoincrement();
            circolareTermine.addLongProperty("occorrenze").notNull();
            circolareTermine.addToOne(circolare, id_circolare);
            circolareTermine.addToOne(termine, id_termine);

            //index
            Index indexCircolareTermine = new Index();
            indexCircolareTermine.makeUnique();
            indexCircolareTermine.addProperty(id_circolare);
            indexCircolareTermine.addProperty(id_termine);
            circolareTermine.addIndex(indexCircolareTermine);
        }

        //tabella intermedia
        {
            @Deprecated
            final Entity newsContieneTermine = schema.addEntity("NewsContieneTermineDB");
            Property id_news = newsContieneTermine.addLongProperty("_id_news").notNull().getProperty();
            Property id_termine = newsContieneTermine.addLongProperty("_id_termine").notNull().getProperty();
            newsContieneTermine.addIdProperty().autoincrement();
            newsContieneTermine.addLongProperty("occorrenze").notNull();
            newsContieneTermine.addToOne(news, id_news);
            newsContieneTermine.addToOne(termine, id_termine);

            //index
            Index indexNewsTermine = new Index();
            indexNewsTermine.makeUnique();
            indexNewsTermine.addProperty(id_news);
            indexNewsTermine.addProperty(id_termine);
            newsContieneTermine.addIndex(indexNewsTermine);
        }


        new DaoGenerator().generateAll(schema, "/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDroidApp/src");
    }
}
