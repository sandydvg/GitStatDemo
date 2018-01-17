package com.sandeep.gitstat.gitstat;


import javafx.application.Application;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping(value="/rest/gitstatus")

public class GitController {

    @RequestMapping(value = "/getAllRepo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)


    public String getGitRequest() throws IOException, ParseException {
       GitRestClient client = new GitRestClient();

       return client.getAllRepo();
    }

    @RequestMapping(value = "/getAllRepoLocal", method = RequestMethod.GET)
    public String getGitRequestLocal() throws IOException, GitAPIException {

        GitLocalRestClient localRestClient = new GitLocalRestClient();
        return localRestClient.getAllRepo();

    }
}
