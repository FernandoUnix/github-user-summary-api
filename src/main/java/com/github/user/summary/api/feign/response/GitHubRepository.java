package com.github.user.summary.api.feign.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class GitHubRepository {

    @JsonProperty("name")
    private String name;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("language")
    private String language;

    @JsonProperty("owner")
    private GithubOwner owner;

    @JsonProperty("size")
    private Integer size;

    public GitHubRepository() {
    }

    public GitHubRepository(String name, String description, String language, int size, String url, GithubOwner owner) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.size = size;
        this.htmlUrl = url;
        this.owner = owner;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public GithubOwner getOwner() {
        return owner;
    }

    public void setOwner(GithubOwner owner) {
        this.owner = owner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitHubRepository that = (GitHubRepository) o;
        return Objects.equals(name, that.name) && Objects.equals(htmlUrl, that.htmlUrl) && Objects.equals(description, that.description) && Objects.equals(language, that.language) && Objects.equals(owner, that.owner) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, htmlUrl, description, language, owner, size);
    }
}
