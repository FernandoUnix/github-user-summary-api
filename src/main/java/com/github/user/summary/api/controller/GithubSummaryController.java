package com.github.user.summary.api.controller;

import com.github.user.summary.api.response.GithubSummaryResponse;
import com.github.user.summary.api.service.IGitHubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/github-summary")
public class GithubSummaryController {

    Logger log = LoggerFactory.getLogger(GithubSummaryController.class);

    @Autowired
    private IGitHubService iGitHubService;

    @GetMapping(value = "/{username}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity getSummary(@PathVariable("username") String username) {

        try {

            GithubSummaryResponse summary = iGitHubService.getUserSummary(username);

            if (summary == null) {

                String notFoundMessage = "Not Found, The resource you are looking for has been removed, had its name changed, or is temporarily unavailable for username: " + username;
                log.error(notFoundMessage);
                return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(summary, HttpStatus.OK);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
