package com.itnexusglobal.profil.language;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LanguageDTO {

    private Long langId;

    @NotNull
    @Size(max = 255)
    private String langName;

    @NotNull
    private LevelLanguage langLevel;

    @NotNull
    private Long profileLanguage;

}
