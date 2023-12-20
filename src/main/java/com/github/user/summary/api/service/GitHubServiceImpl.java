package com.github.user.summary.api.service;

import com.github.user.summary.api.feign.GitHub;
import com.github.user.summary.api.feign.response.GitHubRepository;
import com.github.user.summary.api.response.GithubSummaryResponse;
import com.github.user.summary.api.response.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GitHubServiceImpl implements IGitHubService{

    @Autowired
    private GitHub gitHub;

    public GithubSummaryResponse getUserSummary(String username) {

        List<GitHubRepository> repositories = gitHub.getRepositories(username);

        if (repositories != null && !repositories.isEmpty()) {

            GithubSummaryResponse githubSummaryResponse = new GithubSummaryResponse();

            GitHubRepository repo = repositories.stream().findFirst().get();

            githubSummaryResponse.setUsername(repo.getOwner().getLogin());
            githubSummaryResponse.setGithubLink(repositories.stream().findFirst().get().getHtmlUrl());
            githubSummaryResponse.setRepositoryQuantity(repositories.size());
            githubSummaryResponse.setRepositories(getRepositories(repositories));
            githubSummaryResponse.setLanguagePercentages(sortByValuesDescending(calculateLanguagePercentages(repositories)));

            return githubSummaryResponse;

        } else {

            return null;
        }
    }

    private Map<String, Double> calculateLanguagePercentages(List<GitHubRepository> repositories) {

        Map<String, Long> languageSizes = repositories.stream()
                .filter(repo -> repo.getLanguage() != null)
                .collect(Collectors.groupingBy(GitHubRepository::getLanguage, Collectors.summingLong(GitHubRepository::getSize)));

        long totalSize = repositories.stream().mapToLong(GitHubRepository::getSize).sum();

        return languageSizes.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry ->truncateToTwoDecimalPlaces(((double) entry.getValue() / totalSize) * 100)
                ));
    }

    private double truncateToTwoDecimalPlaces(double value) {
        return Math.floor(value * 100) / 100;
    }

    private List<Repository> getRepositories(List<GitHubRepository> repositories) {

        return  repositories
                .stream().map(repo -> {

                    Repository repository = new Repository();
                    repository.setDescription(repo.getDescription());
                    repository.setName(repo.getName());
                    repository.setRepositoryLink(repo.getHtmlUrl());
                    repository.setLanguage(repo.getLanguage());

                    return repository;
                }).collect(Collectors.toList());
    }

    private Map<String, Double> sortByValuesDescending(Map<String, Double> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
}
