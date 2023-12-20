package com.github.user.summary.api.feign;

import com.github.user.summary.api.feign.response.GitHubRepository;
import org.springframework.cloud.openfeign.FeignClient;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "getFeignClient", url = "${client.post.baseUrl}")
public interface GitHub {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/repos", produces = "application/json")
    List<GitHubRepository> getRepositories(@PathVariable("username") String username);
}
