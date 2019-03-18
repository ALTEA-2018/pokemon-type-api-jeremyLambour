package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonTypeServiceImpl implements  PokemonTypeService {

	@Autowired
    PokemonTypeRepository pokemonTypeRepository;
    
    @Autowired
    TranslationRepository translationRepository;
	
	
    public PokemonTypeServiceImpl() {
    	super();
	}
	@Override
    public PokemonType getPokemonType(int id) {
        PokemonType pokemon =  pokemonTypeRepository.findPokemonTypeById(id);
        pokemon.setName(translationRepository.getPokemonName(pokemon.getId(), LocaleContextHolder.getLocale()));
        return pokemon;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        List<PokemonType> types =  pokemonTypeRepository.findAllPokemonType();
        for(PokemonType type: types) {
        	type.setName(translationRepository.getPokemonName(type.getId(), LocaleContextHolder.getLocale()));
        }
        return types;
    }

    @Override
    public PokemonType getPokemonType(String name) {
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }
    
    
	public void setPokemonTypeRepository(PokemonTypeRepository repository) {
		this.pokemonTypeRepository = repository;
		
	}
	
	public void setTranslationRepository(TranslationRepository repository) {
		this.translationRepository = repository;	
	}
	
	
}
