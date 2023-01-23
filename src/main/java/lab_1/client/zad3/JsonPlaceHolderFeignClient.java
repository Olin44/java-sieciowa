package lab_1.client.zad3;

import feign.Headers;
import feign.RequestLine;

import java.util.List;

public interface JsonPlaceHolderFeignClient {
    @RequestLine("GET")
    public List<Post> findAll();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    public CreatePostResponse createPost(CreatePostRequest createPostRequest);
}
