package com.itnexusglobal.profil.experience;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/experiences", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExperienceResource {

    private final ExperienceService experienceService;

    public ExperienceResource(final ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienceDTO>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.findAll());
    }

    @GetMapping("/{expId}")
    public ResponseEntity<ExperienceDTO> getExperience(
            @PathVariable(name = "expId") final Long expId) {
        return ResponseEntity.ok(experienceService.get(expId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createExperience(
            @RequestBody @Valid final ExperienceDTO experienceDTO) {
        return new ResponseEntity<>(experienceService.create(experienceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{expId}")
    public ResponseEntity<Void> updateExperience(@PathVariable(name = "expId") final Long expId,
            @RequestBody @Valid final ExperienceDTO experienceDTO) {
        experienceService.update(expId, experienceDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{expId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteExperience(@PathVariable(name = "expId") final Long expId) {
        experienceService.delete(expId);
        return ResponseEntity.noContent().build();
    }

}
