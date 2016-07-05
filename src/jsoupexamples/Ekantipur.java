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
public class Ekantipur {

    public void category(String category) throws IOException {
        Document doc = Jsoup.connect(category).userAgent("Mozilla/5.0").timeout(60*1000).get();
        Elements columnLink = doc.select("div[class~=col-xs-12.col-md-6.col-sm-12.item.?]");
        for (Element link : columnLink) {
            System.out.println("\nlink: " + link.select("a").attr("abs:href"));
            System.out.println("text: " + link.select("a:not(.morelink)").text());
            System.out.println("Time: " + link.select("div[class=post]").text());
            System.out.println("Description: " + link.select("div[class=teaser]").text());
            System.out.println("Image: " + link.select("img[src]").attr("src"));
        }
    }
    
    
    public void cartoon() throws IOException {
        Document doc = Jsoup.connect("http://kantipur.ekantipur.com/cartoon/").userAgent("Mozilla/5.0").timeout(60 * 1000).get();
        Elements columnLink = doc.select("div.categories.layout1");
        for (Element link : columnLink) {
            Elements cartoons = link.select("div.col-xs-12.col-sm-4.col-md-4.cartoon-list.news-box-layout");
            for (Element cartoon : cartoons) {
                if (!cartoon.select("a").attr("title").equals("")) {
                    System.out.println("\ntitle: " + cartoon.select("a").attr("title"));
                    System.out.println("date: " + cartoon.select("span").text());
                    System.out.println("cartoon link: " + cartoon.select("img[src]").attr("src"));
                }
            }
        }
    }

    public HashMap<String, List> getCategory() throws IOException {
        Document doc = Jsoup.connect("http://kantipur.ekantipur.com/").userAgent("Mozilla/5.0").timeout(60 * 1000).get();
        //Elements catagories = doc.select("div.collapse.navbar-collapse").first().select("a"); //Selects only category from nav bar
        Elements catagories = doc.select("a[href~=/category/?]"); //Selects all category
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
