package com.example.panasco.instructablesrss;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by panasco on 1/18/17.
 */

public class RSSFeed extends AsyncTask {

    /**
     * Recent: index/rss.xml
     * Search by keyword: /tag/type-id/keyword-<keyword>/rss.xml
     */

    public String base_url = "http://www.instructables.com/";
    public String recent_path = "index";
    public String keyword_path = "tag/type-id/keyword-";
    public String rss_path = "/rss.xml";
    public String full_path = "";


    public void searchByKeyword(String keyword) {
        full_path = base_url + keyword_path + keyword + rss_path;
    }

    public void sendRequest(String full_path) {

        try {
            URL url = new URL(full_path);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Object doInBackground(Object[] params) {

        try{
            URL url = new URL(full_path);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(getInputStream(url), "UTF_8");

        }
        catch (Exception e){

        }

        return null;
    }

}
