package com.github.user.summary.api.feign.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Objects;

@JsonRootName("owner")
public class GithubOwner {

    @JsonProperty("login")
    private String login;

    @JsonProperty("html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubOwner that = (GithubOwner) o;
        return Objects.equals(login, that.login) && Objects.equals(htmlUrl, that.htmlUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, htmlUrl);
    }
}
