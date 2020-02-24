package com.codetally.service;

import com.codetally.model.github.Repository;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Currency;

public interface RepositoryService {
    public String getAll();

    public String getAllByOwnername(String ownername);

    public String getSingle(int id);

    public Repository getSingleByOwnerAndRepo(String owner, String name);

    public long getSingleIdByOwnerAndRepo(String owner, String name);

    public boolean addSingle(Repository repository);

    public boolean addSingle(Repository repository, float codecost);

    public boolean addSingle(InputStream repositoryInputStream) throws UnsupportedEncodingException ;

    public void setCurrency(long repositoryId, Currency currency);

    public Currency getCurrency(long repositoryId);

    public void setCodecost(long repositoryId, float codecost);
}
