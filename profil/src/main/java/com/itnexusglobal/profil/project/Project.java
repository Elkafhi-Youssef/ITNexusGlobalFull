package com.itnexusglobal.profil.project;

import com.itnexusglobal.profil.profile.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Project {

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
    private Long proId;

    @Column(nullable = false)
    private String proTilte;

    @Column(nullable = false)
    private String tech;

    @Column
    private String proRepo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_project_id", nullable = false)
    private Profile profileProject;

}
