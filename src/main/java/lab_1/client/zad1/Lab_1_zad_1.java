package lab_1.client.zad1;

import java.net.UnknownHostException;

public class
Lab_1_zad_1 {

    public static void main(String[] args) throws UnknownHostException {
        IpFinder ipFinder = new IpFinder();
        System.out.println(ipFinder.find("www.wykop.pl"));
        System.out.println(ipFinder.find("www.google.com"));
    }
}

