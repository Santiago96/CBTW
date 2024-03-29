package com.cbtw.interview.controllers;


import com.cbtw.interview.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPokemons(@RequestParam int limit) {
        return ResponseEntity.ok().body(pokemonService.fetchPokemon(limit));
    }
}
