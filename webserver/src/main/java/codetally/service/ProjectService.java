package codetally.service;

import codetally.model.Project;
import codetally.model.User;
import codetally.repository.ProjectRepository;
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

    public Project save(Project project) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findByUsername(authentication.getName());
        project.setUser(loggedUser);
        return projectRepository.save(project);
    }

    public Project findByProjectname(String projectname) {
        return projectRepository.findByProjectname(projectname);
    }
    public List<ProjectSource> getProjectSources() {
        return ((List<ProjectSource>) projectSources);
    }
}
