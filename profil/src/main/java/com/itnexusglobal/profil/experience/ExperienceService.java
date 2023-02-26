package com.itnexusglobal.profil.experience;

import com.itnexusglobal.profil.profile.Profile;
import com.itnexusglobal.profil.profile.ProfileRepository;
import com.itnexusglobal.profil.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ProfileRepository profileRepository;

    public ExperienceService(final ExperienceRepository experienceRepository,
            final ProfileRepository profileRepository) {
        this.experienceRepository = experienceRepository;
        this.profileRepository = profileRepository;
    }

    public List<ExperienceDTO> findAll() {
        final List<Experience> experiences = experienceRepository.findAll(Sort.by("expId"));
        return experiences.stream()
                .map((experience) -> mapToDTO(experience, new ExperienceDTO()))
                .collect(Collectors.toList());
    }

    public ExperienceDTO get(final Long expId) {
        return experienceRepository.findById(expId)
                .map(experience -> mapToDTO(experience, new ExperienceDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ExperienceDTO experienceDTO) {
        final Experience experience = new Experience();
        mapToEntity(experienceDTO, experience);
        return experienceRepository.save(experience).getExpId();
    }

    public void update(final Long expId, final ExperienceDTO experienceDTO) {
        final Experience experience = experienceRepository.findById(expId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(experienceDTO, experience);
        experienceRepository.save(experience);
    }

    public void delete(final Long expId) {
        experienceRepository.deleteById(expId);
    }

    private ExperienceDTO mapToDTO(final Experience experience, final ExperienceDTO experienceDTO) {
        experienceDTO.setExpId(experience.getExpId());
        experienceDTO.setExpName(experience.getExpName());
        experienceDTO.setPosition(experience.getPosition());
        experienceDTO.setStartDate(experience.getStartDate());
        experienceDTO.setEndDate(experience.getEndDate());
        experienceDTO.setDescription(experience.getDescription());
        experienceDTO.setProfileExperience(experience.getProfileExperience() == null ? null : experience.getProfileExperience().getProfileID());
        return experienceDTO;
    }

    private Experience mapToEntity(final ExperienceDTO experienceDTO, final Experience experience) {
        experience.setExpName(experienceDTO.getExpName());
        experience.setPosition(experienceDTO.getPosition());
        experience.setStartDate(experienceDTO.getStartDate());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setDescription(experienceDTO.getDescription());
        final Profile profileExperience = experienceDTO.getProfileExperience() == null ? null : profileRepository.findById(experienceDTO.getProfileExperience())
                .orElseThrow(() -> new NotFoundException("profileExperience not found"));
        experience.setProfileExperience(profileExperience);
        return experience;
    }

}
