package lab_1.client.zad6;

import feign.Param;
import feign.RequestLine;
import feign.Response;

interface OpenStreetMapClient {

    @RequestLine("GET {street} {streetNumber} {city}?format=geojson&addressdetails=1&limit=1&polygon_geojson=1")
    Response getGeometry(@Param("street") String street, @Param("streetNumber") String streetNumber, @Param("city") String city);
}
