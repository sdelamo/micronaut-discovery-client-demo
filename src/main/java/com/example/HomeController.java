package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;

@Controller
public class HomeController {

    private final GithubApiClient githubApiClient;

    public HomeController(GithubApiClient githubApiClient) {
        this.githubApiClient = githubApiClient;
    }

    @ExecuteOn(TaskExecutors.IO)
    @Get
    List<GithubRelease> index() {
        return githubApiClient.fetchReleases();
    }
}
