package com.github.user.summary.api.response;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GithubSummaryResponse {

    private String username;
    private String githubLink;
    private int repositoryQuantity;

    private Map<String, Double> languagePercentages;

    private List<Repository> repositories;

    public Map<String, Double> getLanguagePercentages() {
        return languagePercentages;
    }

    public void setLanguagePercentages(Map<String, Double> languagePercentages) {
        this.languagePercentages = languagePercentages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public int getRepositoryQuantity() {
        return repositoryQuantity;
    }

    public void setRepositoryQuantity(int repositoryQuantity) {
        this.repositoryQuantity = repositoryQuantity;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubSummaryResponse that = (GithubSummaryResponse) o;
        return repositoryQuantity == that.repositoryQuantity && Objects.equals(username, that.username) && Objects.equals(githubLink, that.githubLink) && Objects.equals(languagePercentages, that.languagePercentages) && Objects.equals(repositories, that.repositories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, githubLink, repositoryQuantity, languagePercentages, repositories);
    }
}
