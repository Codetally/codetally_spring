package codetally.service;

import codetally.model.Project;
import codetally.repository.ProjectRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Currency;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 */
@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getByKey(String projectKey) {
        Project project = new Project();
        project.setProjectkey(projectKey);
        Example<Project> projectExample = Example.of(project, ExampleMatcher.matchingAny());
        Project returnProject = projectRepository.findOne(projectExample).get();
        if (returnProject==null) {
            projectRepository.save(project);
            return project;
        }

        return returnProject;
    }
    public Project getById(Long id) {
        return projectRepository.getOne(id);
    }

    public void save(Project project) {
        projectRepository.save(project);
    }
}
