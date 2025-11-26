package com.pokedex.pokedex_project.controller;
//package com.example.pokedex.controller;

//import com.example.pokedex.dto.PokemonDto;
//import com.example.pokedex.service.PokemonService;
import com.pokedex.pokedex_project.dto.PokemonDto;
import com.pokedex.pokedex_project.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PokemonController {
    private final PokemonService service;
    public PokemonController(PokemonService service) { this.service = service; }

    @GetMapping("/pokemon/{name}")
    public ResponseEntity<PokemonDto> getPokemon(@PathVariable String name) throws Exception {
        PokemonDto p = service.getPokemon(name);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/cache/stats")
    public ResponseEntity<Map<String, Object>> cacheStats() {
        return ResponseEntity.ok(service.cacheStats());
    }

    @PostMapping("/cache/clear")
    public ResponseEntity<Void> clearCache() {
        service.clearCache();
        return ResponseEntity.noContent().build();
    }
}

