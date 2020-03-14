package com.github;

import com.codetally.plugin.Configuration;
import com.codetally.plugin.ExternalProject;
import com.codetally.plugin.ProjectOwner;
import com.codetally.plugin.ProjectSource;

import java.util.List;

public class GithubProjectConnector implements ProjectSource {
    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public List<ExternalProject> getProjects(ProjectOwner projectOwner) {
        return null;
    }
}
