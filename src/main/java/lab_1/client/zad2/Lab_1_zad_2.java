package lab_1.client.zad2;

import java.io.IOException;

public class Lab_1_zad_2 {
    public static void main(String[] args) throws IOException {
        SiteContentDisplayer ipFinder = new SiteContentDisplayer();
        System.out.println(ipFinder.content("https://www.wykop.pl"));
        System.out.println(ipFinder.content("https://www.google.com"));
    }
}

