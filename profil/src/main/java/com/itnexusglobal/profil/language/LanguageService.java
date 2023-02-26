package com.itnexusglobal.profil.language;

import com.itnexusglobal.profil.profile.Profile;
import com.itnexusglobal.profil.profile.ProfileRepository;
import com.itnexusglobal.profil.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final ProfileRepository profileRepository;

    public LanguageService(final LanguageRepository languageRepository,
            final ProfileRepository profileRepository) {
        this.languageRepository = languageRepository;
        this.profileRepository = profileRepository;
    }

    public List<LanguageDTO> findAll() {
        final List<Language> languages = languageRepository.findAll(Sort.by("langId"));
        return languages.stream()
                .map((language) -> mapToDTO(language, new LanguageDTO()))
                .collect(Collectors.toList());
    }

    public LanguageDTO get(final Long langId) {
        return languageRepository.findById(langId)
                .map(language -> mapToDTO(language, new LanguageDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LanguageDTO languageDTO) {
        final Language language = new Language();
        mapToEntity(languageDTO, language);
        return languageRepository.save(language).getLangId();
    }

    public void update(final Long langId, final LanguageDTO languageDTO) {
        final Language language = languageRepository.findById(langId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(languageDTO, language);
        languageRepository.save(language);
    }

    public void delete(final Long langId) {
        languageRepository.deleteById(langId);
    }

    private LanguageDTO mapToDTO(final Language language, final LanguageDTO languageDTO) {
        languageDTO.setLangId(language.getLangId());
        languageDTO.setLangName(language.getLangName());
        languageDTO.setLangLevel(language.getLangLevel());
        languageDTO.setProfileLanguage(language.getProfileLanguage() == null ? null : language.getProfileLanguage().getProfileID());
        return languageDTO;
    }

    private Language mapToEntity(final LanguageDTO languageDTO, final Language language) {
        language.setLangName(languageDTO.getLangName());
        language.setLangLevel(languageDTO.getLangLevel());
        final Profile profileLanguage = languageDTO.getProfileLanguage() == null ? null : profileRepository.findById(languageDTO.getProfileLanguage())
                .orElseThrow(() -> new NotFoundException("profileLanguage not found"));
        language.setProfileLanguage(profileLanguage);
        return language;
    }

}
