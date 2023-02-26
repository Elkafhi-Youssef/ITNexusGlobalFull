package com.itnexusglobal.profil.profile;



import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileResource {

    private final ProfileService profileService;

    public ProfileResource(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(profileService.findAll());
    }

    @GetMapping("/{profileID}")
    public ResponseEntity<ProfileDTO> getProfile(
            @PathVariable(name = "profileID") final Long profileID) {
        return ResponseEntity.ok(profileService.get(profileID));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProfile( @Valid @RequestBody  final ProfileDTO profileDTO) {
        return new ResponseEntity<>(profileService.create(profileDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{profileID}")
    public ResponseEntity<Void> updateProfile(
            @PathVariable(name = "profileID") final Long profileID,
            @RequestBody @Valid final ProfileDTO profileDTO) {
        profileService.update(profileID, profileDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{profileID}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProfile(
            @PathVariable(name = "profileID") final Long profileID) {
        profileService.delete(profileID);
        return ResponseEntity.noContent().build();
    }

}
