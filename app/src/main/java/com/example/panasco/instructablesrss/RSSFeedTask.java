package com.example.panasco.instructablesrss;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by panasco on 1/18/17.
 */

public class RSSFeedTask extends AsyncTask<String, Integer, String> {

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
        new RSSFeedTask().execute(full_path);
    }

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        String response = "";

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                response = readStream(urlConnection.getInputStream());
                Log.d("Response:",response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }

        return response;
    }

    private String readStream(InputStream in) {

        HashMap<Integer, HashMap> items = new HashMap<Integer, HashMap>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("item");

            for (int i=0; i<nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element node_element = (Element) node;


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}



