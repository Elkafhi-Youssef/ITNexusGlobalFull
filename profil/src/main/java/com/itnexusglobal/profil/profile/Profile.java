package com.itnexusglobal.profil.profile;

import com.itnexusglobal.profil.education.Education;
import com.itnexusglobal.profil.experience.Experience;
import com.itnexusglobal.profil.hobbie.Hobbie;
import com.itnexusglobal.profil.language.Language;
import com.itnexusglobal.profil.person.Person;
import com.itnexusglobal.profil.project.Project;
import com.itnexusglobal.profil.skill.Skill;
import jakarta.persistence.*;

import java.util.List;
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

    @OneToMany(mappedBy = "profileExperience",fetch = FetchType.EAGER)
    private Set<Experience> profileExperienceExperiences;

    @OneToMany(mappedBy = "prifileEducation",fetch = FetchType.EAGER)
    private List<Education> prifileEducationEducations;

    @OneToMany(mappedBy = "profileProject",fetch = FetchType.EAGER)
    private Set<Project> profileProjectProjects;

    @OneToMany(mappedBy = "profileSkill",fetch = FetchType.EAGER)
    private Set<Skill> profileSkillSkills;

    @OneToMany(mappedBy = "profileLanguage",fetch = FetchType.EAGER)
    private Set<Language> profileLanguageLanguages;

    @OneToMany(mappedBy = "profileHobbie",fetch = FetchType.EAGER)
    private Set<Hobbie> profileHobbieHobbies;

    @Column(nullable = false)
    private Long personID;
    @Transient
    private Person person;
}
