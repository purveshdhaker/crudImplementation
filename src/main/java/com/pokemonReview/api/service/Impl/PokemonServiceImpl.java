package com.pokemonReview.api.service.Impl;

import com.pokemonReview.api.dto.PokemonDto;
import com.pokemonReview.api.exceptions.PokemonNotFoundException;
import com.pokemonReview.api.models.Pokemon;
import com.pokemonReview.api.repository.PokemonRepository;
import com.pokemonReview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon pokemonSaved = pokemonRepository.save(pokemon);

        PokemonDto pokemonDtoSaved = new PokemonDto();
        pokemonDtoSaved.setId(pokemonSaved.getId());
        pokemonDtoSaved.setName(pokemonSaved.getName());
        pokemonDtoSaved.setType(pokemonSaved.getType());
        return pokemonDtoSaved;
    }

    @Override
    public List<PokemonDto> getAllPokemons() {
//        Pokemon pokemon2 = pokemonRepository.findById(33333).orElseThrow(()-> new PokemonNotFoundException("Pokemons not found"));
        List<Pokemon> pokemon = pokemonRepository.findAll();
        return pokemon.stream().map(this::mapToPokemonDto).collect(Collectors.toList());
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon=pokemonRepository.findById(id).orElseThrow(()->new PokemonNotFoundException("Pokemon not found by id: "+id));
        return mapToPokemonDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon=pokemonRepository.findById(id).orElseThrow(()->new PokemonNotFoundException("Pokemon could not updated by id: "+id));
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon updatedPokemonSaved = pokemonRepository.save(pokemon);
        return mapToPokemonDto(updatedPokemonSaved);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon=pokemonRepository.findById(id).orElseThrow(()->new PokemonNotFoundException("Pokemon could not Deleted by id: "+id));
        pokemonRepository.delete(pokemon);
    }

    private PokemonDto mapToPokemonDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    private Pokemon mapToEntityPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}
