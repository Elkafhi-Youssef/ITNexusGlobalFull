package com.itnexusglobal.profil.profile;

import com.itnexusglobal.profil.education.Education;
import com.itnexusglobal.profil.experience.Experience;
import com.itnexusglobal.profil.hobbie.Hobbie;
import com.itnexusglobal.profil.language.Language;
import com.itnexusglobal.profil.project.Project;
import com.itnexusglobal.profil.skill.Skill;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Profile {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long profileID;

    @OneToMany(mappedBy = "profileExperience")
    private Set<Experience> profileExperienceExperiences;

    @OneToMany(mappedBy = "prifileEducation")
    private Set<Education> prifileEducationEducations;

    @OneToMany(mappedBy = "profileProject")
    private Set<Project> profileProjectProjects;

    @OneToMany(mappedBy = "profileSkill")
    private Set<Skill> profileSkillSkills;

    @OneToMany(mappedBy = "profileLanguage")
    private Set<Language> profileLanguageLanguages;

    @OneToMany(mappedBy = "profileHobbie")
    private Set<Hobbie> profileHobbieHobbies;

    @Column(nullable = false)
    private Long personID;
}
