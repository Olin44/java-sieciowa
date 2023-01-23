package lab_1.client.zad3;

public class Lab_1_zad_3 {
    //    Napisz klienta który połączy się z API (https://jsonplaceholder.typicode.com/) i wyświetl dostępne posty (GET https://jsonplaceholder.typicode.com/posts).
    public static void main(String[] args) {
        JsonPlaceHolderConnector jsonPlaceHolderConnector = new JsonPlaceHolderConnector();
        jsonPlaceHolderConnector.getPosts()
                .forEach(System.out::println);
    }
}
