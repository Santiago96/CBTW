package com.cbtw.interview.services;

import com.cbtw.interview.client.PokemonClient;
import com.cbtw.interview.models.Pokemon;
import com.cbtw.interview.models.PokemonResponse;
import com.cbtw.interview.repositories.PokemonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PokemonService {

    private static Logger logger = LoggerFactory.getLogger(PokemonService.class);
    private final PokemonClient pokemonClient;
    private final PokemonRepository pokemonRepository;
    private final ObjectMapper objectMapper;

    public PokemonService(PokemonClient pokemonClient, PokemonRepository pokemonRepository, ObjectMapper objectMapper) {
        this.pokemonClient = pokemonClient;
        this.pokemonRepository = pokemonRepository;
        this.objectMapper = objectMapper;
    }

    @Cacheable(value = "pokemonsCahe", key = "#limit")
    public List<Pokemon> fetchPokemon(int limit) {
        String responseString = pokemonClient.getPokemonByLimit(limit);
        try {
            PokemonResponse pokemonResponse = objectMapper.readValue(responseString, PokemonResponse.class);
            return pokemonResponse.getPokemonList();
        } catch (Exception e) {
            logger.error("Unable to find results pokemon list");
        }
        return Collections.emptyList();
    }
}
