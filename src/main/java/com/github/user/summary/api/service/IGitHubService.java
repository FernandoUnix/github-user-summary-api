package com.github.user.summary.api.service;

import com.github.user.summary.api.response.GithubSummaryResponse;

public interface IGitHubService {
    GithubSummaryResponse getUserSummary(String username);
}
