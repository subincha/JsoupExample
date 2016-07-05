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
public class Annapurnapost {

    public void category(String category) throws IOException {
        Document doc = Jsoup.connect(category).userAgent("Mozilla/5.0").timeout(60*1000).get();
        Elements columnLink = doc.select("div[class~=col-xs-12.col-sm-12.col-md-12.?]");
        System.out.println(columnLink.size());
        for (Element link : columnLink) {
            if(!link.select("span.maintitle").text().equals("")) {
                System.out.println("\nlink: " + link.select("a").attr("abs:href"));
                System.out.println("text: " + link.select("span.maintitle").text());
                System.out.println("Time: " + link.select("span.dates.first").text());
                System.out.println("Description: " + link.select("span.description").text());
                System.out.println("Image: " + link.select("img[src]").attr("src"));
               
            }
        }
    }
    
    public HashMap<String, List> getCategory() throws IOException {
        Document doc = Jsoup.connect("http://www.annapurnapost.com/").userAgent("Mozilla/5.0").timeout(60 * 1000).get();
        Elements catagories = doc.select("a[href~=/newslist/?]");
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
}
