package codetally.service;

import codetally.model.Project;
import codetally.model.User;
import codetally.repository.ProjectRepository;
import com.codetally.plugin.ExternalProject;
import com.codetally.plugin.ProjectOwner;
import com.codetally.plugin.ProjectSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 */
@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("projectSourceListFactoryBean")
    Object projectSources;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getByKey(String projectKey) {
        Project project = new Project();
        project.setProjectkey(projectKey);
        Example<Project> projectExample = Example.of(project, ExampleMatcher.matchingAny());
        Project returnProject = projectRepository.findOne(projectExample).get();

        return returnProject;
    }

    public List<Project> search(String keyword, Pageable pageable) {
        return projectRepository.findByProjectnameContainingIgnoreCase(keyword, pageable);
    }

    public Project getById(Long id) {
        return projectRepository.getOne(id);
    }

    public List<Project> importProjects(String className) {

        List<Project> projectList = new ArrayList<>();

        getProjectSources().forEach(projectSource -> {
            System.out.println("External project retrieval");
            if (projectSource.getClass().getName().equalsIgnoreCase(className)) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User loggedUser = userService.findByUsername(authentication.getName());
                ProjectOwner projectOwner = new ProjectOwner();
                projectOwner.setEmail(loggedUser.getUsername());
                projectOwner.setHandle(loggedUser.getName());
                projectOwner.setUrl(loggedUser.getWebsiteurl());
                List<ExternalProject> externalProjectList = projectSource.getProjects(projectOwner);
                externalProjectList.forEach(externalProject -> {
                    Project project = new Project();
                    project.setProjectname(externalProject.getProjectname());
                    project.setProjectkey(externalProject.getProjectkey());
                    project.setProjecturl(externalProject.getProjecturl());
                    project = save(project);
                    projectList.add(project);
                });
            }

        });
        return projectList;
    }

    public Project save(Project project) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findByUsername(authentication.getName());
        project.setUser(loggedUser);
        if (project.getCharges() == null || project.getCharges().size() < 1) {
            //TODO: Set default charge model (?)
        }
        return projectRepository.save(project);
    }

    public Project findByProjectname(String projectname) {
        return projectRepository.findByProjectname(projectname);
    }

    public List<ProjectSource> getProjectSources() {
        return ((List<ProjectSource>) projectSources);
    }
}
