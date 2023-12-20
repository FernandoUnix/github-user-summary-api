package com.github.user.summary.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.user.summary.api.response.GithubSummaryResponse;
import com.github.user.summary.api.service.IGitHubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class GithubSummaryControllerTest {

    @Mock
    private IGitHubService gitHubService;

    @InjectMocks
    private GithubSummaryController controller;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private static final String URL = "/github-summary/{username}";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetSummarySuccessJson() throws Exception {

        String username = "testUser";
        GithubSummaryResponse response = new GithubSummaryResponse();
        when(gitHubService.getUserSummary(username)).thenReturn(response);

        mockMvc.perform(get(URL, username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void testGetSummarySuccessXml() throws Exception {

        String username = "testUser";
        GithubSummaryResponse response = new GithubSummaryResponse();
        when(gitHubService.getUserSummary(username)).thenReturn(response);

        mockMvc.perform(get(URL, username)
                        .accept(MediaType.APPLICATION_XML)
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML));
    }

    @Test
    public void testGetSummaryNotFound() throws Exception {
        String username = "nonExistentUser";
        when(gitHubService.getUserSummary(username)).thenReturn(null);

        mockMvc.perform(get(URL, username))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Not Found, The resource you are looking for has been removed, had its name changed, or is temporarily unavailable for username: nonExistentUser"));
    }

    @Test
    void testGetSummaryInternalServerError() throws Exception {
        String username = "errorUser";
        when(gitHubService.getUserSummary(username)).thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(get(URL, username))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Server Error"));
    }
}