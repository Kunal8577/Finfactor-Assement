package com.pokedex.pokedex_project.dto;
//package com.example.pokedex.dto;

import java.util.List;
import java.util.Map;

public class PokemonDto {
    public int id;
    public String name;
    public int height;
    public int weight;
    public int baseExperience;
    public Map<String, Object> images;
    public List<String> types;
    public List<Map<String, Object>> abilities;
    public List<Map<String, Object>> stats;
    public List<String> moves;
}

