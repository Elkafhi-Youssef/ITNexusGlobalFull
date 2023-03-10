package com.itnexusglobal.profil.skill;

import com.itnexusglobal.profil.profile.Profile;
import com.itnexusglobal.profil.profile.ProfileRepository;
import com.itnexusglobal.profil.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final ProfileRepository profileRepository;

    public SkillService(final SkillRepository skillRepository,
            final ProfileRepository profileRepository) {
        this.skillRepository = skillRepository;
        this.profileRepository = profileRepository;
    }

    public List<SkillDTO> findAll() {
        final List<Skill> skills = skillRepository.findAll(Sort.by("skillId"));
        return skills.stream()
                .map((skill) -> mapToDTO(skill, new SkillDTO()))
                .collect(Collectors.toList());
    }

    public SkillDTO get(final Long skillId) {
        return skillRepository.findById(skillId)
                .map(skill -> mapToDTO(skill, new SkillDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SkillDTO skillDTO) {
        final Skill skill = new Skill();
        mapToEntity(skillDTO, skill);
        return skillRepository.save(skill).getSkillId();
    }

    public void update(final Long skillId, final SkillDTO skillDTO) {
        final Skill skill = skillRepository.findById(skillId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(skillDTO, skill);
        skillRepository.save(skill);
    }

    public void delete(final Long skillId) {
        skillRepository.deleteById(skillId);
    }

    private SkillDTO mapToDTO(final Skill skill, final SkillDTO skillDTO) {
        skillDTO.setSkillId(skill.getSkillId());
        skillDTO.setSkillType(skill.getSkillType());
        skillDTO.setSkillName(skill.getSkillName());
        skillDTO.setProfileSkill(skill.getProfileSkill() == null ? null : skill.getProfileSkill().getProfileID());
        return skillDTO;
    }

    private Skill mapToEntity(final SkillDTO skillDTO, final Skill skill) {
        skill.setSkillType(skillDTO.getSkillType());
        skill.setSkillName(skillDTO.getSkillName());
        final Profile profileSkill = skillDTO.getProfileSkill() == null ? null : profileRepository.findById(skillDTO.getProfileSkill())
                .orElseThrow(() -> new NotFoundException("profileSkill not found"));
        skill.setProfileSkill(profileSkill);
        return skill;
    }

}
