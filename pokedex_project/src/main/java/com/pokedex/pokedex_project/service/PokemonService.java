package com.pokedex.pokedex_project.service;
//package com.example.pokedex.service;
//
//import com.example.pokedex.cache.LRUCache;
//import com.example.pokedex.client.PokeApiClient;
//import com.example.pokedex.dto.PokemonDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokedex.pokedex_project.cache.LRUCache;
import com.pokedex.pokedex_project.client.PokeApiClient;
import com.pokedex.pokedex_project.dto.PokemonDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokemonService {
    private final PokeApiClient client;
    private final LRUCache<String, String> cache;
    private final ObjectMapper mapper = new ObjectMapper();

    public PokemonService(PokeApiClient client,
                          @Value("${pokedex.cache.maxEntries}") int maxEntries,
                          @Value("${pokedex.cache.defaultTtlMs}") long defaultTtlMs) {
        this.client = client;
        this.cache = new LRUCache<>(maxEntries, defaultTtlMs);
    }

    public PokemonDto getPokemon(String name) throws Exception {
        String key = name.toLowerCase();
        String raw = cache.get(key);
        if (raw == null) {
            raw = client.fetchPokemonRaw(name);
            cache.put(key, raw);
        }
        JsonNode root = mapper.readTree(raw);
        return normalize(root);
    }

    private PokemonDto normalize(JsonNode root) {
        PokemonDto dto = new PokemonDto();
        dto.id = root.path("id").asInt();
        dto.name = root.path("name").asText();
        dto.height = root.path("height").asInt();
        dto.weight = root.path("weight").asInt();
        dto.baseExperience = root.path("base_experience").asInt();

        Map<String,Object> images = new HashMap<>();
        JsonNode sprites = root.path("sprites");
        images.put("front_default", sprites.path("front_default").asText(null));
        images.put("front_shiny", sprites.path("front_shiny").asText(null));
        if (sprites.path("other").path("official-artwork").has("front_default")) {
            images.put("official_artwork", sprites.path("other").path("official-artwork").path("front_default").asText(null));
        }
        dto.images = images;

        List<String> types = new ArrayList<>();
        root.path("types").forEach(t -> types.add(t.path("type").path("name").asText()));
        dto.types = types;

        List<Map<String,Object>> abilities = new ArrayList<>();
        root.path("abilities").forEach(a -> {
            Map<String,Object> m = new HashMap<>();
            m.put("name", a.path("ability").path("name").asText());
            m.put("is_hidden", a.path("is_hidden").asBoolean(false));
            abilities.add(m);
        });
        dto.abilities = abilities;

        List<Map<String,Object>> stats = new ArrayList<>();
        root.path("stats").forEach(s -> {
            Map<String,Object> m = new HashMap<>();
            m.put("name", s.path("stat").path("name").asText());
            m.put("base", s.path("base_stat").asInt());
            stats.add(m);
        });
        dto.stats = stats;

        List<String> moves = new ArrayList<>();
        root.path("moves").forEach(m -> moves.add(m.path("move").path("name").asText()));
        dto.moves = moves;

        return dto;
    }

    public Map<String, Object> cacheStats() { return cache.stats(); }
    public void clearCache() { cache.clear(); }
}

