package lab_1.client.zad4;

import lab_1.client.zad3.JsonPlaceHolderConnector;

public class Lab_1_zad_4 {

    public static void main(String[] args) {
        JsonPlaceHolderConnector jsonPlaceHolderConnector = new JsonPlaceHolderConnector();
        System.out.println(jsonPlaceHolderConnector.createPost("foo", "bar"));
        System.out.println(jsonPlaceHolderConnector.createPost("Click bajtowy tytuł", "Zero treści"));
    }
}
