package com.cbtw.interview.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pokemon-client", url = "https://pokeapi.co/api/v2/pokemon")
public interface PokemonClient {

    @GetMapping()
    String getPokemonByLimit(@RequestParam("limit") int limit);
}
