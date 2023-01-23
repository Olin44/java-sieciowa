package lab_1.client.zad5;

import java.io.IOException;

public class Lab_1_zad_5 {

    public static void main(String[] args) throws IOException {
        FileDownloader fileDownloader = new FileDownloader();
        fileDownloader.downloadFile("https://speed.hetzner.de/100MB.bin", "file");
    }
}
