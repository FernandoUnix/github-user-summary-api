package com.github.user.summary.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.github.user.summary.api.feign.GitHub;
import com.github.user.summary.api.feign.response.GitHubRepository;
import com.github.user.summary.api.feign.response.GithubOwner;
import com.github.user.summary.api.response.GithubSummaryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GitHubServiceImplTest {

    @Mock
    private GitHub gitHub;

    @InjectMocks
    private GitHubServiceImpl gitHubService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserSummary() {

        String username = "testUser";

        GithubOwner owner = new GithubOwner();
        owner.setLogin(username);
        owner.setHtmlUrl("github.com/owner");

        List<GitHubRepository> repositories = Arrays.asList(
                new GitHubRepository("repo1", "description1", "Java", 100, "url1", owner),
                new GitHubRepository("repo2", "description2", "Python", 150, "url2", owner)
        );

        when(gitHub.getRepositories(username)).thenReturn(repositories);

        GithubSummaryResponse response = gitHubService.getUserSummary(username);

        assertNotNull(response);
        assertEquals("testUser", response.getUsername());
        assertEquals("url1", response.getGithubLink());
        assertEquals(2, response.getRepositoryQuantity());
        assertEquals(2, response.getRepositories().size());
        assertNotNull(response.getLanguagePercentages());
    }


    @Test
    public void testSortByValuesDescending() {

        String username = "testUser";

        GithubOwner owner = new GithubOwner();
        owner.setLogin(username);
        owner.setHtmlUrl("github.com/owner");

        List<GitHubRepository> repositories = Arrays.asList(
                new GitHubRepository("repo1", "description1", "Java", 50, "url1", owner),
                new GitHubRepository("repo2", "description2", "Python", 30, "url2", owner),
                new GitHubRepository("repo3", "description3", "JavaScript", 20, "url3", owner)
        );

        when(gitHub.getRepositories(username)).thenReturn(repositories);

        GithubSummaryResponse response = gitHubService.getUserSummary(username);

        assertEquals(50.0, response.getLanguagePercentages().get("Java"));
        assertEquals(30.0, response.getLanguagePercentages().get("Python"));
        assertEquals(20.0, response.getLanguagePercentages().get("JavaScript"));

    }

    @Test
    public void testGetUserSummaryWithNullRepositories() {

        String username = "testUser";
        when(gitHub.getRepositories(username)).thenReturn(null);

        GithubSummaryResponse response = gitHubService.getUserSummary(username);

        assertNull(response);
    }

    @Test
    public void testGetUserSummaryWithNoRepositories() {

        String username = "testUser";
        when(gitHub.getRepositories(username)).thenReturn(Collections.emptyList());

        GithubSummaryResponse response = gitHubService.getUserSummary(username);

        assertNull(response);
    }

    @Test
    public void testCalculateLanguagePercentages() {

        String username = "testUser";

        GithubOwner owner = new GithubOwner();
        owner.setLogin(username);
        owner.setHtmlUrl("github.com/owner");

        List<GitHubRepository> repositories = Arrays.asList(
                new GitHubRepository("repo1", "description1", "Java", 100, "url1", owner),
                new GitHubRepository("repo2", "description2", "Python", 150, "url2", owner)
        );

        when(gitHub.getRepositories(username)).thenReturn(repositories);

        GithubSummaryResponse response = gitHubService.getUserSummary(username);

        assertEquals(40.0, response.getLanguagePercentages().get("Java"));
        assertEquals(60.0, response.getLanguagePercentages().get("Python"));
    }
}
