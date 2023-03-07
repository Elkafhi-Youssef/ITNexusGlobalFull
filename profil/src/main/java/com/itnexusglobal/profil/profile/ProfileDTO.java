package com.itnexusglobal.profil.profile;

import com.itnexusglobal.profil.education.Education;
import com.itnexusglobal.profil.experience.Experience;
import com.itnexusglobal.profil.hobbie.Hobbie;
import com.itnexusglobal.profil.language.Language;
import com.itnexusglobal.profil.person.Person;
import com.itnexusglobal.profil.project.Project;
import com.itnexusglobal.profil.skill.Skill;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Getter
@Setter
public class ProfileDTO {

    private Long profileID;
    private Long personID;
    private List<Education> prifileEducationEducations;
    private Set<Experience> profileExperienceExperiences;
    private Set<Project> profileProjectProjects;
    private Set<Skill> profileSkillSkills;
    private Set<Language> profileLanguageLanguages;
    private Set<Hobbie> profileHobbieHobbies;
    private Person person;



}
