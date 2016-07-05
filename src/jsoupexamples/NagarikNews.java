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
public class NagarikNews {
    public void category(String category) throws IOException {
        Document doc = Jsoup.connect(category).userAgent("Mozilla/5.0").timeout(60 * 1000).get();
        Elements initialRowLink = doc.select("div.col-sm-3.part-ent");
        for (Element link : initialRowLink) {
            System.out.println("\nlink: " + link.select("a").attr("abs:href"));
            System.out.println("text: " + link.select("a").text());
            System.out.println("Description: " + link.select("p").text());
        }

        Elements columnLink = doc.select("div.first-on.first-list");
        for (Element link : columnLink) {
            System.out.println("\nlink: " + link.select("a").attr("abs:href"));
            System.out.println("text: " + link.select("a").text());
            System.out.println("Time: " + link.select("p.headline-time").text());
            System.out.println("Description: " + link.select("p:not(.headline-time)").text());
            System.out.println("Image: " + link.select("img[src]").attr("src"));
        }
    }
    public HashMap<String, List> getCategory() throws IOException {
        Document doc = Jsoup.connect("http://www.nagariknews.com/").userAgent("Mozilla/5.0").timeout(60 * 1000).get();
        //Elements catagories = doc.select("div#navbar").first().select("a");
        Elements catagories = doc.select("a[href~=/category/?]");
        HashMap<String, List> catagoryAndLink = new HashMap<>();
        List<String> eachCategory = new ArrayList<>();
        List<String> eachLink = new ArrayList<>();
        for (Element catagory : catagories) {
            if(!catagory.attr("href").equals("") && !catagory.text().equals("")) {
                eachLink.add(catagory.attr("abs:href"));
                eachCategory.add(catagory.text());
                catagoryAndLink.put("catagory", eachCategory);
                catagoryAndLink.put("link", eachLink);
            }
        }
        return catagoryAndLink;

    }
    
}
