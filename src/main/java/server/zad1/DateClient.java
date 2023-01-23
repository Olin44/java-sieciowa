package server.zad1;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DateClient {
    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket("127.0.0.1", 4444)) {
            Scanner in = new Scanner(s.getInputStream(), StandardCharsets.UTF_8);
            while(in.hasNextLine()) {
                System.out.println( in.nextLine() );
            }
        }
    }
}