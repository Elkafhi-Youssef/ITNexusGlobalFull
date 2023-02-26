package com.itnexusglobal.profil.hobbie;

import com.itnexusglobal.profil.profile.Profile;
import com.itnexusglobal.profil.profile.ProfileRepository;
import com.itnexusglobal.profil.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class HobbieService {

    private final HobbieRepository hobbieRepository;
    private final ProfileRepository profileRepository;

    public HobbieService(final HobbieRepository hobbieRepository,
            final ProfileRepository profileRepository) {
        this.hobbieRepository = hobbieRepository;
        this.profileRepository = profileRepository;
    }

    public List<HobbieDTO> findAll() {
        final List<Hobbie> hobbies = hobbieRepository.findAll(Sort.by("hobId"));
        return hobbies.stream()
                .map((hobbie) -> mapToDTO(hobbie, new HobbieDTO()))
                .collect(Collectors.toList());
    }

    public HobbieDTO get(final Long hobId) {
        return hobbieRepository.findById(hobId)
                .map(hobbie -> mapToDTO(hobbie, new HobbieDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final HobbieDTO hobbieDTO) {
        final Hobbie hobbie = new Hobbie();
        mapToEntity(hobbieDTO, hobbie);
        return hobbieRepository.save(hobbie).getHobId();
    }

    public void update(final Long hobId, final HobbieDTO hobbieDTO) {
        final Hobbie hobbie = hobbieRepository.findById(hobId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(hobbieDTO, hobbie);
        hobbieRepository.save(hobbie);
    }

    public void delete(final Long hobId) {
        hobbieRepository.deleteById(hobId);
    }

    private HobbieDTO mapToDTO(final Hobbie hobbie, final HobbieDTO hobbieDTO) {
        hobbieDTO.setHobId(hobbie.getHobId());
        hobbieDTO.setHobName(hobbie.getHobName());
        hobbieDTO.setProfileHobbie(hobbie.getProfileHobbie() == null ? null : hobbie.getProfileHobbie().getProfileID());
        return hobbieDTO;
    }

    private Hobbie mapToEntity(final HobbieDTO hobbieDTO, final Hobbie hobbie) {
        hobbie.setHobName(hobbieDTO.getHobName());
        final Profile profileHobbie = hobbieDTO.getProfileHobbie() == null ? null : profileRepository.findById(hobbieDTO.getProfileHobbie())
                .orElseThrow(() -> new NotFoundException("profileHobbie not found"));
        hobbie.setProfileHobbie(profileHobbie);
        return hobbie;
    }

}
