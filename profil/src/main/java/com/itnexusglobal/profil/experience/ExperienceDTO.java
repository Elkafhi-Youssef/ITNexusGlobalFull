package com.itnexusglobal.profil.experience;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExperienceDTO {

    private Long expId;

    @NotNull
    @Size(max = 255)
    private String expName;

    @NotNull
    @Size(max = 255)
    private String position;

    @NotNull
    @Size(max = 255)
    private String startDate;

    @NotNull
    @Size(max = 255)
    private String endDate;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    private Long profileExperience;

}
