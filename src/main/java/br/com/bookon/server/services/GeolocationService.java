package br.com.bookon.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bookon.server.payload.response.postgres.GeolocationResponse;

@Service
public class GeolocationService {

    private final String geocodingApiUrl = "https://maps.googleapis.com/maps/api/geocode/json";
    private final String apiKey = "AIzaSyD2-ti58XYBr75FuFn6UeGFcQQo5QfwM0g";

    @Autowired
    private RestTemplate restTemplate;

    public GeolocationResponse getGeolocation(String address) {
        String url = geocodingApiUrl + "?address=" + address + "&key=" + apiKey;
            return restTemplate.getForObject(url, GeolocationResponse.class);
        }
}
