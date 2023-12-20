package com.github.user.summary.api.response;

import java.util.Objects;

public class Repository {

    private String name;
    private String repositoryLink;
    private String description;
    private String language;

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepositoryLink() {
        return repositoryLink;
    }

    public void setRepositoryLink(String repositoryLink) {
        this.repositoryLink = repositoryLink;
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
        Repository that = (Repository) o;
        return Objects.equals(name, that.name) && Objects.equals(repositoryLink, that.repositoryLink) && Objects.equals(description, that.description) && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, repositoryLink, description, language);
    }
}
