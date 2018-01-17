package com.sandeep.gitstat.gitstat;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GitstatApplication {

	public static void main(String[] args) throws IOException, GitAPIException {
		SpringApplication.run(GitstatApplication.class, args);
		GitRestClient client = new GitRestClient();
		GitLocalRestClient localRestClient = new GitLocalRestClient();
		localRestClient.getAllRepo();
//		System.out.println(client.getAllRepo());
	}
}
