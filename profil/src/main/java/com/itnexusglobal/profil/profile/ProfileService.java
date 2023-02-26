package com.itnexusglobal.profil.profile;

import com.itnexusglobal.profil.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(final ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileDTO> findAll() {
        final List<Profile> profiles = profileRepository.findAll(Sort.by("profileID"));
        return profiles.stream()
                .map((profile) -> mapToDTO(profile, new ProfileDTO()))
                .collect(Collectors.toList());
    }

    public ProfileDTO get(final Long profileID) {
        return profileRepository.findById(profileID)
                .map(profile -> mapToDTO(profile, new ProfileDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProfileDTO profileDTO) {
        final Profile profile = new Profile();
        mapToEntity(profileDTO, profile);
        return profileRepository.save(profile).getProfileID();
    }

    public void update(final Long profileID, final ProfileDTO profileDTO) {
        final Profile profile = profileRepository.findById(profileID)
                .orElseThrow(NotFoundException::new);
        mapToEntity(profileDTO, profile);
        profileRepository.save(profile);
    }

    public void delete(final Long profileID) {
        profileRepository.deleteById(profileID);
    }

    private ProfileDTO mapToDTO(final Profile profile, final ProfileDTO profileDTO) {
        profileDTO.setProfileID(profile.getProfileID());
        return profileDTO;
    }

    private Profile mapToEntity(final ProfileDTO profileDTO, final Profile profile) {
        return profile;
    }

}
