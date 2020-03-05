package com.codetally.service;

import com.codetally.configuration.GithubConfiguration;
import com.codetally.model.Project;
import com.codetally.model.github.Repository;
import com.codetally.repository.ProjectRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

/**
 * Created by greg on 25/06/17.
 */
@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    public String getAllByOwnername(String ownername) {
        //in the past, this was where a person signed in. An email was sent???
        return projectRepository.getAllByOwnername(ownername);
    }

    private void synchAllByOwnername(String ownername) {
        try {
            String urlParameters = GithubConfiguration.client_id
                    + "=" + GithubConfiguration.clientid
                    + "&" + GithubConfiguration.client_secret
                    + "=" + GithubConfiguration.clientsecret;

            String paging = "page=1&per_page=100";

            String request = GithubConfiguration.users_url + ownername + "/" + GithubConfiguration.repos + "?" + urlParameters + "&" + paging;
            URL url = new URL(request);

            if (url != null) {
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setRequestMethod("GET");
                urlconnection.connect();
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Repository>>() {
                }.getType();
                List<Repository> repositoryList = gson.fromJson(new InputStreamReader(urlconnection.getInputStream(), StandardCharsets.UTF_8), listType);
                for (Repository repository : repositoryList) {
                    if (projectRepository.getCountByOwnerAndRepo(ownername, repository.getName()) < 1) {
                        addSingle(repository);
                    }
                    urlconnection.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSingle(int id) {
        Repository repository = null;
        try {
            repository = projectRepository.getSingle(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return new Gson().toJson(repository);
    }

    public Project getSingleByOwnerAndRepo(String owner, String name) {
        Repository repository = null;
        try {
            repository = projectRepository.findByOwnerName(owner, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repository;
    }

    public long getSingleIdByOwnerAndRepo(String owner, String name) {
        long repositoryId = 0;
        try {
            repositoryId = projectRepository.getSingleIdByOwnerAndRepo(owner, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repositoryId;
    }

    public boolean addSingle(Repository repository) {
        return addSingle(repository, 0);
    }

    public boolean addSingle(Repository repository, float codecost) {
        try {
            projectRepository.addRepo(repository, codecost);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addSingle(InputStream repositoryInputStream) throws UnsupportedEncodingException {
        Gson gson = new Gson();
        Repository repository = gson.fromJson(new InputStreamReader(repositoryInputStream, StandardCharsets.UTF_8), Repository.class);
        return addSingle(repository);
    }

    public void setCurrency(long repositoryId, Currency currency) {
        try {
            projectRepository.setCurrency(repositoryId, currency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Currency getCurrency(long repositoryId) {
        Currency currency = null;
        try {
            currency = projectRepository.getCurrency(repositoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency;
    }

    public void setCodecost(long repositoryId, float codecost) {
        try {
            projectRepository.setCodecost(repositoryId, codecost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Project getByKey(String projectKey) {
        Project project = new Project();
        project.setProjectkey(projectKey);
        Example<Project> projectExample = Example.of(project, ExampleMatcher.matchingAny());
        return projectRepository.findOne(projectExample).get();
    }

    public void save(Project project) {
        projectRepository.save(project);
    }
}
