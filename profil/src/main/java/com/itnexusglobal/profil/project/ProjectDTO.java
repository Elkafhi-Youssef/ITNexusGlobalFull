package com.itnexusglobal.profil.project;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectDTO {

    private Long proId;

    @NotNull
    @Size(max = 255)
    private String proTilte;

    @NotNull
    @Size(max = 255)
    private String tech;

    @Size(max = 255)
    private String proRepo;

    @NotNull
    private Long profileProject;

}
