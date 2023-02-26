package com.itnexusglobal.profil.project;

import com.itnexusglobal.profil.profile.Profile;
import com.itnexusglobal.profil.profile.ProfileRepository;
import com.itnexusglobal.profil.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;

    public ProjectService(final ProjectRepository projectRepository,
            final ProfileRepository profileRepository) {
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
    }

    public List<ProjectDTO> findAll() {
        final List<Project> projects = projectRepository.findAll(Sort.by("proId"));
        return projects.stream()
                .map((project) -> mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }

    public ProjectDTO get(final Long proId) {
        return projectRepository.findById(proId)
                .map(project -> mapToDTO(project, new ProjectDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProjectDTO projectDTO) {
        final Project project = new Project();
        mapToEntity(projectDTO, project);
        return projectRepository.save(project).getProId();
    }

    public void update(final Long proId, final ProjectDTO projectDTO) {
        final Project project = projectRepository.findById(proId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(projectDTO, project);
        projectRepository.save(project);
    }

    public void delete(final Long proId) {
        projectRepository.deleteById(proId);
    }

    private ProjectDTO mapToDTO(final Project project, final ProjectDTO projectDTO) {
        projectDTO.setProId(project.getProId());
        projectDTO.setProTilte(project.getProTilte());
        projectDTO.setTech(project.getTech());
        projectDTO.setProRepo(project.getProRepo());
        projectDTO.setProfileProject(project.getProfileProject() == null ? null : project.getProfileProject().getProfileID());
        return projectDTO;
    }

    private Project mapToEntity(final ProjectDTO projectDTO, final Project project) {
        project.setProTilte(projectDTO.getProTilte());
        project.setTech(projectDTO.getTech());
        project.setProRepo(projectDTO.getProRepo());
        final Profile profileProject = projectDTO.getProfileProject() == null ? null : profileRepository.findById(projectDTO.getProfileProject())
                .orElseThrow(() -> new NotFoundException("profileProject not found"));
        project.setProfileProject(profileProject);
        return project;
    }

}
