package lab_1.client.zad3;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.List;

public class JsonPlaceHolderConnector {

    private final JsonPlaceHolderFeignClient jsonPlaceHolderFeignClient;

    public JsonPlaceHolderConnector() {
        jsonPlaceHolderFeignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(JsonPlaceHolderFeignClient.class))
                .logLevel(Logger.Level.FULL)
                .target(JsonPlaceHolderFeignClient.class, "https://jsonplaceholder.typicode.com/posts");
    }

    public List<Post> getPosts() {
        return jsonPlaceHolderFeignClient.findAll();
    }

    public CreatePostResponse createPost(String title, String body) {
        return jsonPlaceHolderFeignClient.createPost(new CreatePostRequest(title, body));
    }
}
