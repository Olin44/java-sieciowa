package lab_1.client.zad6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Lab_1_zad_6 {
    public static void main(String[] args) throws IOException {
        OpenStreetMapClient openStreetMapClient = configureFeignClient();
        Response feignResponse = openStreetMapClient.getGeometry("Bytomska", "21", "Krakow");
        String jsonString = new String(feignResponse.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        String coordinates = extractCoordinates(jsonString);
        System.out.println(coordinates);
    }

    private static OpenStreetMapClient configureFeignClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(OpenStreetMapClient.class))
                .logLevel(Logger.Level.FULL)
                .target(OpenStreetMapClient.class, "https://nominatim.openstreetmap.org/search/");
    }

    private static String extractCoordinates(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = prepareObjectMapper();
        JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
        JsonNode features = jsonNodeRoot.get("features").get(0);
        JsonNode geometry = features.get("geometry");
        JsonNode coordinates = geometry.get("coordinates");
        return coordinates.toPrettyString();
    }

    private static ObjectMapper prepareObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }


}
