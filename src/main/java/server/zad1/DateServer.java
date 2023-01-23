package server.zad1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class DateServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(4444)) {
            System.out.println("Listening on port 4444");
            try(Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                out.println("Aktualna data: " + LocalDate.now());
            }
            System.out.println("Zakończono działanie.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}