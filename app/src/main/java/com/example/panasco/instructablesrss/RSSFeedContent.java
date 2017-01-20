package com.example.panasco.instructablesrss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panasco on 1/20/17.
 */

public class RSSFeedContent {

    public static final List<RSSContent> CONTENT = new ArrayList<RSSContent>();
    public static final Map<Integer, RSSContent> CONTENT_MAP = new HashMap<Integer, RSSContent>();

    public static class RSSContent {
        public final int id;
        public final String title;
        public final String link;
        public final String imageThumb;
        public final String imageFull;
        public final String author;
        public final String description;
        public final String pubDate;

        public RSSContent(int id, String title, String link, String imageThumb, String imageFull, String author,
                          String description, String pubDate){
            this.id = id;
            this.title = title;
            this.link = link;
            this.imageThumb = imageThumb;
            this.imageFull = imageFull;
            this.author = author;
            this.description = description;
            this.pubDate = pubDate;
        }
    }

    private static void mapItem(RSSFeedContent.RSSContent item) {
        CONTENT.add(item);
        CONTENT_MAP.put(item.id, item);
    }

    public static void addItem(int index, String title, String link, String imageThumb,
                        String imageFull, String author, String description, String pubDate){
        mapItem(new RSSContent(index, title, link, imageThumb, imageFull, author,
                description, pubDate));
    }
}
