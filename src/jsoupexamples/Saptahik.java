/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoupexamples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Subin
 */
public class Saptahik {

    public void category(String category) throws IOException {
        Document doc = Jsoup.connect(category).userAgent("Mozilla/5.0").timeout(60*1000).get();
        Elements columnLink = doc.select("div[class~=col-xs-12.col-md-4.col-sm-4.item.?]");
        for (Element link : columnLink) {
            System.out.println("\nlink: " + link.select("a").attr("abs:href"));
            System.out.println("text: " + link.select("a").text());
            System.out.println("Time: " + link.select("p.author").text());
            System.out.println("Description: " + link.select("div[class=teaser]").text());
            System.out.println("Image: " + link.select("img[src]").attr("src"));
        }
    }
    
    public HashMap<String, List> getCategory() throws IOException {
        Document doc = Jsoup.connect("http://saptahik.ekantipur.com/").userAgent("Mozilla/5.0").timeout(60 * 1000).get();
        Elements catagories = doc.select("a[href~=/category/?]");
        HashMap<String, List> catagoryAndLink = new HashMap<>();
        List<String> eachCategory = new ArrayList<>();
        List<String> eachLink = new ArrayList<>();
        for (Element catagory : catagories) {
            if(!catagory.attr("abs:href").equals("") && !catagory.text().equals("")) {
                eachLink.add(catagory.attr("abs:href"));
                eachCategory.add(catagory.text());
                catagoryAndLink.put("catagory", eachCategory);
                catagoryAndLink.put("link", eachLink);
            }
        }
        return catagoryAndLink;

    }
    
     public void detailNews(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(60 * 1000).get();
        Element news = doc.select("div.maincontent").first();
        System.out.println("\nTitle: " + news.select("div#content").first().select("h1").text());
        System.out.println("text: " + news.select("p:not(.author)").text());
        System.out.println("author: " + news.select("p.author").text());
        System.out.println("dates: " + news.select("span.dates").text());
        System.out.println("Image: " + news.select("img[src]").attr("abs:src"));
    }
}
