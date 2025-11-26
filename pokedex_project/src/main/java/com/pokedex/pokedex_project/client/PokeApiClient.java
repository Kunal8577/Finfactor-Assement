package com.pokedex.pokedex_project.client;

//package com.example.pokedex.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiClient {
    private final RestTemplate rest = new RestTemplate();

    @Value("${pokedex.pokeapi.base}")
    private String baseUrl;

    public String fetchPokemonRaw(String name) {
        String url = String.format("%s/pokemon/%s", baseUrl, name.toLowerCase());
        ResponseEntity<String> resp = rest.getForEntity(url, String.class);
        return resp.getBody();
    }
}

