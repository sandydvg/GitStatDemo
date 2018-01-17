package com.sandeep.gitstat.gitstat;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GsonUtils;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.List;

public class GitRestClient {

    public  static Gson gson = GsonUtils.getGson();
    public  static String json;

    public String getAllRepo() throws IOException {

        GitHubClient client = new GitHubClient();
        client = GitHubClient.createClient("https://github.com/sandeepvbk/eventuate-local.git").setCredentials("sandeepvbk@gmail.com", "SanQuest@123");
        RepositoryService service = new RepositoryService(client);

        CommitService cs = new CommitService(client);

        List<Repository> repositories = service.getRepositories();
        for (int i = 0; i < repositories.size() - 1; i++) {

            List<RepositoryCommit> commits = cs.getCommits(repositories.get(i));

            for (int j = 0; j < commits.size(); j++) {

                RepositoryCommit repositoryCommit = commits.get(j);
                json = toJson(repositoryCommit) + json;
//                if (json.equals(null)){

//                    json = toJson(repositoryCommit);
//                }
//                else {

//                    json = json.concat(toJson(repositoryCommit));
//                }
//               json = json.concat(toJson(repositoryCommit));

            }

        }
        return json;
    }

    protected static String toJson(Object object) throws IOException {
        try {

            return gson.toJson(object);
        } catch (JsonParseException jpe) {
            IOException ioe = new IOException(
                    "Parse exception converting object to JSON"); //$NON-NLS-1$
            ioe.initCause(jpe);
            throw ioe;
        }
    }
}
