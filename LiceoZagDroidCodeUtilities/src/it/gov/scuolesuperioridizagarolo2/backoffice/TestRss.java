package it.gov.scuolesuperioridizagarolo2.backoffice;

import it.gov.scuolesuperioridizagarolo.model.rss.RssFeed;
import it.gov.scuolesuperioridizagarolo.model.rss.RssItem;
import it.gov.scuolesuperioridizagarolo.rss.RssReader;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;

/**
 * Created by stefano on 22/12/15.
 */
public class TestRss {
    public static void main(String[] args) throws IOException, SAXException {
        String u = "http://www.fermitivoli.gov.it/joomla/index.php?format=feed&type=rss";
        final RssFeed feed;
        feed = RssReader.read(new URL(u));
        for (RssItem x : feed.getRssItems()) {
            System.out.println("===================================");
            System.out.println("===================================");
            final String description = C_TextUtil.normalizeTextFromHtml(x.getDescription());
            for (int i = 0; i < description.length(); i++) {
                final char c = description.charAt(i);
                if (c > 128 || c < 32) {
                    System.out.println("\n????????????? " + (int) c + " <" + c + ">");
                }
            }

            System.out.println(description);
        }
    }
}
