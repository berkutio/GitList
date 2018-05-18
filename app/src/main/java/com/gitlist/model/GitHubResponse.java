package com.gitlist.model;


import java.util.Arrays;

public class GitHubResponse {

    private RepoItem[] repoItems;

    public RepoItem[] getRepoItems() {
        return repoItems;
    }

    @Override
    public String toString() {
        return "GitHubResponse{" +
                "repoItems=" + Arrays.toString(repoItems) +
                '}';
    }
}
