package com.itnexusglobal.profil.education;

import com.itnexusglobal.profil.profile.Profile;
import com.itnexusglobal.profil.profile.ProfileRepository;
import com.itnexusglobal.profil.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final ProfileRepository profileRepository;

    public EducationService(final EducationRepository educationRepository,
            final ProfileRepository profileRepository) {
        this.educationRepository = educationRepository;
        this.profileRepository = profileRepository;
    }

    public List<EducationDTO> findAll() {
        final List<Education> educations = educationRepository.findAll(Sort.by("eduId"));
        return educations.stream()
                .map((education) -> mapToDTO(education, new EducationDTO()))
                .collect(Collectors.toList());
    }

    public EducationDTO get(final Long eduId) {
        return educationRepository.findById(eduId)
                .map(education -> mapToDTO(education, new EducationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final EducationDTO educationDTO) {
        final Education education = new Education();
        mapToEntity(educationDTO, education);
        return educationRepository.save(education).getEduId();
    }

    public void update(final Long eduId, final EducationDTO educationDTO) {
        final Education education = educationRepository.findById(eduId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(educationDTO, education);
        educationRepository.save(education);
    }

    public void delete(final Long eduId) {
        educationRepository.deleteById(eduId);
    }

    private EducationDTO mapToDTO(final Education education, final EducationDTO educationDTO) {
        educationDTO.setEduId(education.getEduId());
        educationDTO.setEduName(education.getEduName());
        educationDTO.setStartDate(education.getStartDate());
        educationDTO.setEndDate(education.getEndDate());
        educationDTO.setPrifileEducation(education.getPrifileEducation() == null ? null : education.getPrifileEducation().getProfileID());
        return educationDTO;
    }

    private Education mapToEntity(final EducationDTO educationDTO, final Education education) {
        education.setEduName(educationDTO.getEduName());
        education.setStartDate(educationDTO.getStartDate());
        education.setEndDate(educationDTO.getEndDate());
        final Profile prifileEducation = educationDTO.getPrifileEducation() == null ? null : profileRepository.findById(educationDTO.getPrifileEducation())
                .orElseThrow(() -> new NotFoundException("prifileEducation not found"));
        education.setPrifileEducation(prifileEducation);
        return education;
    }

}
