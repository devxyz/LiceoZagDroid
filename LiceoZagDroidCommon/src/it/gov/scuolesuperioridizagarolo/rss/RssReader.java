/*
 * Copyright (C) 2011 Mats Hofman <http://matshofman.nl/contact/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.gov.scuolesuperioridizagarolo.rss;

import it.gov.scuolesuperioridizagarolo.model.rss.RssFeed;
import it.gov.scuolesuperioridizagarolo.model.rss.RssItem;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RssReader {

    public static RssFeed read(URL url) throws SAXException, IOException {

        return read(new BufferedInputStream(url.openStream()));

    }

    public static RssFeed read(InputStream stream) throws SAXException, IOException {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            RssHandler handler = new RssHandler();
            InputSource input = new InputSource(stream);

            reader.setContentHandler(handler);
            reader.parse(input);

            return handler.getResult();

        } catch (ParserConfigurationException e) {
            throw new SAXException();
        }

    }

    public static void normalizeHtml(RssFeed rss) {
        for (RssItem x : rss.getRssItems()) {
            x.setDescription(C_TextUtil.normalizeTextFromHtml(x.getDescription()));
            x.setTitle(C_TextUtil.normalizeTextFromHtml(x.getTitle()));
        }
    }

    public static RssFeed read(String source) throws SAXException, IOException {
        return read(new ByteArrayInputStream(source.getBytes()));
    }

}
