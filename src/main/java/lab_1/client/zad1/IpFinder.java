package lab_1.client.zad1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpFinder {

    public String find(String host) throws UnknownHostException {
        InetAddress giriAddress = java.net.InetAddress.getByName(host);
        return giriAddress.getHostAddress();
    }
}
