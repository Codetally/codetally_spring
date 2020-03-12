package com.codetally.plugin;

import java.util.List;

public interface ProjectSource {
    public Configuration getConfiguration();
    public List<ExternalProject> getProjects(ProjectOwner projectOwner);
}
