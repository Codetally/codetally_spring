package com.codetally.service;

import com.codetally.model.github.Commit;

public interface CommitService {

    public boolean isTimelog(Commit commit);

    public void addSingle(Commit commit, long repositoryId, float codecost, String elapsedTime);

    public float getRepoCodecost(long repositoryId);

    public String getCurrent(String owner, String repo);

    public String getHistory(String owner, String repo);

}
