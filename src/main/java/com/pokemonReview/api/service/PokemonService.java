package com.pokemonReview.api.service;

import com.pokemonReview.api.dto.PokemonDto;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);

    List<PokemonDto> getAllPokemons();

    PokemonDto getPokemonById(int id);

    PokemonDto updatePokemon(PokemonDto pokemonDto,int id);

    void deletePokemon(int id);
}
