package com.codetally.service;

import com.codetally.configuration.GithubConfiguration;
import com.codetally.configuration.JavaMail;
import com.codetally.model.github.Repository;
import com.codetally.repository.RepoRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Created by greg on 25/06/17.
 */
@Service
public class RepositoryServiceImpl implements RepositoryService{

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private RepoRepository repoRepository;

    public String getAll() {
        List<Repository> repositoryList = null;
        try {
            repositoryList = repoRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return new Gson().toJson(repositoryList);
    }

    public String getAllByOwnername(String ownername) {
        try {
            //synchAllByOwnername should probably be exposed to the end user as an explicit action
            synchAllByOwnername(ownername);
            //Now would be a good time to mail.
            JavaMail.SendMail(ownername);
            List<Repository> repositoryList = repoRepository.getAllByOwnername(ownername);
            return new Gson().toJson(repositoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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
                    if (repoRepository.getCountByOwnerAndRepo(ownername, repository.getName()) < 1) {
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
            repository = repoRepository.getSingle(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return new Gson().toJson(repository);
    }

    public Repository getSingleByOwnerAndRepo(String owner, String name) {
        Repository repository = null;
        try {
            repository = repoRepository.findByOwnerName(owner, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repository;
    }

    public long getSingleIdByOwnerAndRepo(String owner, String name) {
        long repositoryId = 0;
        try {
            repositoryId = repoRepository.getSingleIdByOwnerAndRepo(owner, name);
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
            repoRepository.addRepo(repository, codecost);
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
            repoRepository.setCurrency(repositoryId, currency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Currency getCurrency(long repositoryId) {
        Currency currency = null;
        try {
            currency = repoRepository.getCurrency(repositoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency;
    }

    public void setCodecost(long repositoryId, float codecost) {
        try {
            repoRepository.setCodecost(repositoryId, codecost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
