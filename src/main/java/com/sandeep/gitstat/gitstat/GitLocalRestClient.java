package com.sandeep.gitstat.gitstat;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import com.google.gson.Gson;
public class GitLocalRestClient {

    static String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};

    public String getAllRepo() throws IOException, GitAPIException {

        Git git = Git.open(new File("/Users/sandeepkottur/data/eventuate-local/.git/"));

        Gson gsonObj = new Gson();
        String jsonStr = null;
        try {

//          Git git = Git.open(new File("./spring5webapp/.git/"));
//          Repository repository = git.getRepository();
            LogCommand logCommand = git.log().setMaxCount(500);
            System.out.println( git.log().getRepository().getGitwebDescription());
            Map<String, Integer> map = null;

//          for (RevCommit commit : logCommand.call())
            // Date commitTime = new Date(commit.getCommitTime() * 1000L);
//              System.out.println(commit.getCommitTime());
            map = getCountMap(0, logCommand);

            jsonStr = gsonObj.toJson(map);
            System.out.println(jsonStr);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return jsonStr;
    }
    private static Map<String, Integer> getCountMap(/*RevCommit commit,*/ int count, LogCommand logCommand) throws GitAPIException {

        Map<String, Integer> jsonMap = new TreeMap<String, Integer>();

        for (RevCommit commit : logCommand.call()) {


            Date createddate = new Date(commit.getCommitTime() * 1000L);
//        System.out.println(createddate);


            Date currentDate = new Date();
            long diff = currentDate.getTime() - createddate.getTime();

            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
//        System.out.println(days);

            String month = null;
            if (days <= 214) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(createddate);
                month = monthName[cal.get(Calendar.MONTH)];
                System.out.println(month);
                if (jsonMap.containsKey(month)) {

                    int i = jsonMap.get(month);
                    System.out.println(i);

                    jsonMap.put(month, ++i);

                } else {

                    jsonMap.put(month, 1);
                }

            } else {
                if (month != null) {
                    jsonMap.put(month, 0);
                }

            }
        }
        return jsonMap;
    }




}
