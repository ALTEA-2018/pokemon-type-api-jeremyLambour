package com.miage.altea.tp.pokemon_type_api.repository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.Translation;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

	private Map<Locale, List<Translation>> translations;

	private List<Translation> defaultTranslations;

	public TranslationRepositoryImpl() {
		try {
			var objectMapper = new ObjectMapper();

			var frenchTranslationStream = new ClassPathResource("translations-fr.json").getInputStream();
			var frenchTranslationsArray = objectMapper.readValue(frenchTranslationStream, Translation[].class);

			var englishTranslationStream = new ClassPathResource("translations-en.json").getInputStream();
			var englishTranslationsArray = objectMapper.readValue(englishTranslationStream, Translation[].class);

			this.translations = Map.of(Locale.FRANCE, List.of(frenchTranslationsArray), Locale.UK,
					List.of(englishTranslationsArray));

			this.defaultTranslations = List.of(englishTranslationsArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getPokemonName(int id, Locale locale) {
		Translation finded = new Translation();
		for(Translation translation : translations.get(locale)) {
			if(translation.getId() == id) {
				finded = translation;
			}
		}
//		Optional<Translation> findPokemon = translations.get(locale).stream().filter(pokemon -> pokemon.getId() == id)
//				.findFirst();
//		return (findPokemon.isPresent() ? findPokemon.get().getName() : null);
		return finded.getName();
	}

}
