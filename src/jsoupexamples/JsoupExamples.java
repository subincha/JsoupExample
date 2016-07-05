/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoupexamples;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsoupExamples {

    public static void main(String[] args) {
        Republica republica = new Republica();
        NagarikNews nagarikNews = new NagarikNews();
        Ekantipur ekantipur = new Ekantipur();
        TheKathmanduPost theKathmaduPost = new TheKathmanduPost();
        Saptahik saptahik = new Saptahik();
        TheHimalayanTimes theHimalayanTimes = new TheHimalayanTimes();
        NepalPatrika nepalPatrika = new NepalPatrika();
        Annapurnapost annapurnaPost = new Annapurnapost();
        try {
            HashMap<String, List> categoryAndList = theHimalayanTimes.getCategory();
            List<String> eachCategory = categoryAndList.get("catagory");
            List<String> eachLink = categoryAndList.get("link");
            for (int i = 0; i < eachCategory.size(); i++) {
                System.out.println("\nlink: " + eachLink.get(i));
                System.out.println("category: " + eachCategory.get(i));

            }
            //ekantipur.cartoon();
            //republica.category(eachLink.get(1));
            //republica.category("23");
            //nagarikNews.category("21");
            // nepalPatrika.category(eachLink.get(1));
            // annapurnaPost.category(eachLink.get(1));
            theHimalayanTimes.detailNews("http://thehimalayantimes.com/kathmandu/meeting-of-eminent-persons-group-epg-on-nepal-india-relations-concludes/");
        } catch (IOException ex) {
            Logger.getLogger(JsoupExamples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
