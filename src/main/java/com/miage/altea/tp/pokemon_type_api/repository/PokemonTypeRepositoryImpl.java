package com.miage.altea.tp.pokemon_type_api.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        Optional<PokemonType> type =  pokemons.stream().filter(x -> x.getId() == id).findFirst();
        return (type.isPresent() ? type.get() : null);
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        Optional<PokemonType> type =  pokemons.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
        return (type.isPresent() ? type.get() : null);
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        return pokemons.stream().sorted(Comparator.comparing(PokemonType::getId)).collect(Collectors.toList());
    }

}
