package com.prashanth.kafka.github.source.connector.config.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prashanth.kafka.github.source.connector.model.Issue;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class IssueTest {
  String issueString = "{\n"
      + "    \"id\": 1,\n"
      + "    \"node_id\": \"MDU6SXNzdWUx\",\n"
      + "    \"url\": \"https://api.github.com/repos/octocat/Hello-World/issues/1347\",\n"
      + "    \"repository_url\": \"https://api.github.com/repos/octocat/Hello-World\",\n"
      + "    \"labels_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/1347/labels{/name}\",\n"
      + "    \"comments_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/1347/comments\",\n"
      + "    \"events_url\": \"https://api.github.com/repos/octocat/Hello-World/issues/1347/events\",\n"
      + "    \"html_url\": \"https://github.com/octocat/Hello-World/issues/1347\",\n"
      + "    \"number\": 1347,\n"
      + "    \"state\": \"open\",\n"
      + "    \"title\": \"Found a bug\",\n"
      + "    \"body\": \"I'm having a problem with this.\",\n"
      + "    \"user\": {\n"
      + "      \"login\": \"octocat\",\n"
      + "      \"id\": 1,\n"
      + "      \"node_id\": \"MDQ6VXNlcjE=\",\n"
      + "      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n"
      + "      \"gravatar_id\": \"\",\n"
      + "      \"url\": \"https://api.github.com/users/octocat\",\n"
      + "      \"html_url\": \"https://github.com/octocat\",\n"
      + "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n"
      + "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n"
      + "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n"
      + "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n"
      + "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n"
      + "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n"
      + "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n"
      + "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n"
      + "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n"
      + "      \"type\": \"User\",\n"
      + "      \"site_admin\": false\n"
      + "    },\n"
      + "    \"labels\": [\n"
      + "      {\n"
      + "        \"id\": 208045946,\n"
      + "        \"node_id\": \"MDU6TGFiZWwyMDgwNDU5NDY=\",\n"
      + "        \"url\": \"https://api.github.com/repos/octocat/Hello-World/labels/bug\",\n"
      + "        \"name\": \"bug\",\n"
      + "        \"description\": \"Something isn't working\",\n"
      + "        \"color\": \"f29513\",\n"
      + "        \"default\": true\n"
      + "      }\n"
      + "    ],\n"
      + "    \"assignee\": {\n"
      + "      \"login\": \"octocat\",\n"
      + "      \"id\": 1,\n"
      + "      \"node_id\": \"MDQ6VXNlcjE=\",\n"
      + "      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n"
      + "      \"gravatar_id\": \"\",\n"
      + "      \"url\": \"https://api.github.com/users/octocat\",\n"
      + "      \"html_url\": \"https://github.com/octocat\",\n"
      + "      \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n"
      + "      \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n"
      + "      \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n"
      + "      \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n"
      + "      \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n"
      + "      \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n"
      + "      \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n"
      + "      \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n"
      + "      \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n"
      + "      \"type\": \"User\",\n"
      + "      \"site_admin\": false\n"
      + "    },\n"
      + "    \"assignees\": [\n"
      + "      {\n"
      + "        \"login\": \"octocat\",\n"
      + "        \"id\": 1,\n"
      + "        \"node_id\": \"MDQ6VXNlcjE=\",\n"
      + "        \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n"
      + "        \"gravatar_id\": \"\",\n"
      + "        \"url\": \"https://api.github.com/users/octocat\",\n"
      + "        \"html_url\": \"https://github.com/octocat\",\n"
      + "        \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n"
      + "        \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n"
      + "        \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n"
      + "        \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n"
      + "        \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n"
      + "        \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n"
      + "        \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n"
      + "        \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n"
      + "        \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n"
      + "        \"type\": \"User\",\n"
      + "        \"site_admin\": false\n"
      + "      }\n"
      + "    ],\n"
      + "    \"milestone\": {\n"
      + "      \"url\": \"https://api.github.com/repos/octocat/Hello-World/milestones/1\",\n"
      + "      \"html_url\": \"https://github.com/octocat/Hello-World/milestones/v1.0\",\n"
      + "      \"labels_url\": \"https://api.github.com/repos/octocat/Hello-World/milestones/1/labels\",\n"
      + "      \"id\": 1002604,\n"
      + "      \"node_id\": \"MDk6TWlsZXN0b25lMTAwMjYwNA==\",\n"
      + "      \"number\": 1,\n"
      + "      \"state\": \"open\",\n"
      + "      \"title\": \"v1.0\",\n"
      + "      \"description\": \"Tracking milestone for version 1.0\",\n"
      + "      \"creator\": {\n"
      + "        \"login\": \"octocat\",\n"
      + "        \"id\": 1,\n"
      + "        \"node_id\": \"MDQ6VXNlcjE=\",\n"
      + "        \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n"
      + "        \"gravatar_id\": \"\",\n"
      + "        \"url\": \"https://api.github.com/users/octocat\",\n"
      + "        \"html_url\": \"https://github.com/octocat\",\n"
      + "        \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n"
      + "        \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n"
      + "        \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n"
      + "        \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n"
      + "        \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n"
      + "        \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n"
      + "        \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n"
      + "        \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n"
      + "        \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n"
      + "        \"type\": \"User\",\n"
      + "        \"site_admin\": false\n"
      + "      },\n"
      + "      \"open_issues\": 4,\n"
      + "      \"closed_issues\": 8,\n"
      + "      \"created_at\": \"2011-04-10T20:09:31Z\",\n"
      + "      \"updated_at\": \"2014-03-03T18:58:10Z\",\n"
      + "      \"closed_at\": \"2013-02-12T13:22:01Z\",\n"
      + "      \"due_on\": \"2012-10-09T23:39:01Z\"\n"
      + "    },\n"
      + "    \"locked\": true,\n"
      + "    \"active_lock_reason\": \"too heated\",\n"
      + "    \"comments\": 0,\n"
      + "    \"pull_request\": {\n"
      + "      \"url\": \"https://api.github.com/repos/octocat/Hello-World/pulls/1347\",\n"
      + "      \"html_url\": \"https://github.com/octocat/Hello-World/pull/1347\",\n"
      + "      \"diff_url\": \"https://github.com/octocat/Hello-World/pull/1347.diff\",\n"
      + "      \"patch_url\": \"https://github.com/octocat/Hello-World/pull/1347.patch\"\n"
      + "    },\n"
      + "    \"closed_at\": null,\n"
      + "    \"created_at\": \"2011-04-22T13:33:48Z\",\n"
      + "    \"updated_at\": \"2011-04-22T13:33:48Z\",\n"
      + "    \"repository\": {\n"
      + "      \"id\": 1296269,\n"
      + "      \"node_id\": \"MDEwOlJlcG9zaXRvcnkxMjk2MjY5\",\n"
      + "      \"name\": \"Hello-World\",\n"
      + "      \"full_name\": \"octocat/Hello-World\",\n"
      + "      \"owner\": {\n"
      + "        \"login\": \"octocat\",\n"
      + "        \"id\": 1,\n"
      + "        \"node_id\": \"MDQ6VXNlcjE=\",\n"
      + "        \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n"
      + "        \"gravatar_id\": \"\",\n"
      + "        \"url\": \"https://api.github.com/users/octocat\",\n"
      + "        \"html_url\": \"https://github.com/octocat\",\n"
      + "        \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n"
      + "        \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n"
      + "        \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n"
      + "        \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n"
      + "        \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n"
      + "        \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n"
      + "        \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n"
      + "        \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n"
      + "        \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n"
      + "        \"type\": \"User\",\n"
      + "        \"site_admin\": false\n"
      + "      },\n"
      + "      \"private\": false,\n"
      + "      \"html_url\": \"https://github.com/octocat/Hello-World\",\n"
      + "      \"description\": \"This your first repo!\",\n"
      + "      \"fork\": false,\n"
      + "      \"url\": \"https://api.github.com/repos/octocat/Hello-World\",\n"
      + "      \"archive_url\": \"http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}\",\n"
      + "      \"assignees_url\": \"http://api.github.com/repos/octocat/Hello-World/assignees{/user}\",\n"
      + "      \"blobs_url\": \"http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}\",\n"
      + "      \"branches_url\": \"http://api.github.com/repos/octocat/Hello-World/branches{/branch}\",\n"
      + "      \"collaborators_url\": \"http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}\",\n"
      + "      \"comments_url\": \"http://api.github.com/repos/octocat/Hello-World/comments{/number}\",\n"
      + "      \"commits_url\": \"http://api.github.com/repos/octocat/Hello-World/commits{/sha}\",\n"
      + "      \"compare_url\": \"http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}\",\n"
      + "      \"contents_url\": \"http://api.github.com/repos/octocat/Hello-World/contents/{+path}\",\n"
      + "      \"contributors_url\": \"http://api.github.com/repos/octocat/Hello-World/contributors\",\n"
      + "      \"deployments_url\": \"http://api.github.com/repos/octocat/Hello-World/deployments\",\n"
      + "      \"downloads_url\": \"http://api.github.com/repos/octocat/Hello-World/downloads\",\n"
      + "      \"events_url\": \"http://api.github.com/repos/octocat/Hello-World/events\",\n"
      + "      \"forks_url\": \"http://api.github.com/repos/octocat/Hello-World/forks\",\n"
      + "      \"git_commits_url\": \"http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}\",\n"
      + "      \"git_refs_url\": \"http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}\",\n"
      + "      \"git_tags_url\": \"http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}\",\n"
      + "      \"git_url\": \"git:github.com/octocat/Hello-World.git\",\n"
      + "      \"issue_comment_url\": \"http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}\",\n"
      + "      \"issue_events_url\": \"http://api.github.com/repos/octocat/Hello-World/issues/events{/number}\",\n"
      + "      \"issues_url\": \"http://api.github.com/repos/octocat/Hello-World/issues{/number}\",\n"
      + "      \"keys_url\": \"http://api.github.com/repos/octocat/Hello-World/keys{/key_id}\",\n"
      + "      \"labels_url\": \"http://api.github.com/repos/octocat/Hello-World/labels{/name}\",\n"
      + "      \"languages_url\": \"http://api.github.com/repos/octocat/Hello-World/languages\",\n"
      + "      \"merges_url\": \"http://api.github.com/repos/octocat/Hello-World/merges\",\n"
      + "      \"milestones_url\": \"http://api.github.com/repos/octocat/Hello-World/milestones{/number}\",\n"
      + "      \"notifications_url\": \"http://api.github.com/repos/octocat/Hello-World/notifications{?since,all,participating}\",\n"
      + "      \"pulls_url\": \"http://api.github.com/repos/octocat/Hello-World/pulls{/number}\",\n"
      + "      \"releases_url\": \"http://api.github.com/repos/octocat/Hello-World/releases{/id}\",\n"
      + "      \"ssh_url\": \"git@github.com:octocat/Hello-World.git\",\n"
      + "      \"stargazers_url\": \"http://api.github.com/repos/octocat/Hello-World/stargazers\",\n"
      + "      \"statuses_url\": \"http://api.github.com/repos/octocat/Hello-World/statuses/{sha}\",\n"
      + "      \"subscribers_url\": \"http://api.github.com/repos/octocat/Hello-World/subscribers\",\n"
      + "      \"subscription_url\": \"http://api.github.com/repos/octocat/Hello-World/subscription\",\n"
      + "      \"tags_url\": \"http://api.github.com/repos/octocat/Hello-World/tags\",\n"
      + "      \"teams_url\": \"http://api.github.com/repos/octocat/Hello-World/teams\",\n"
      + "      \"trees_url\": \"http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}\",\n"
      + "      \"clone_url\": \"https://github.com/octocat/Hello-World.git\",\n"
      + "      \"mirror_url\": \"git:git.example.com/octocat/Hello-World\",\n"
      + "      \"hooks_url\": \"http://api.github.com/repos/octocat/Hello-World/hooks\",\n"
      + "      \"svn_url\": \"https://svn.github.com/octocat/Hello-World\",\n"
      + "      \"homepage\": \"https://github.com\",\n"
      + "      \"language\": null,\n"
      + "      \"forks_count\": 9,\n"
      + "      \"stargazers_count\": 80,\n"
      + "      \"watchers_count\": 80,\n"
      + "      \"size\": 108,\n"
      + "      \"default_branch\": \"master\",\n"
      + "      \"open_issues_count\": 0,\n"
      + "      \"is_template\": true,\n"
      + "      \"topics\": [\n"
      + "        \"octocat\",\n"
      + "        \"atom\",\n"
      + "        \"electron\",\n"
      + "        \"api\"\n"
      + "      ],\n"
      + "      \"has_issues\": true,\n"
      + "      \"has_projects\": true,\n"
      + "      \"has_wiki\": true,\n"
      + "      \"has_pages\": false,\n"
      + "      \"has_downloads\": true,\n"
      + "      \"archived\": false,\n"
      + "      \"disabled\": false,\n"
      + "      \"pushed_at\": \"2011-01-26T19:06:43Z\",\n"
      + "      \"created_at\": \"2011-01-26T19:01:12Z\",\n"
      + "      \"updated_at\": \"2011-01-26T19:14:43Z\",\n"
      + "      \"permissions\": {\n"
      + "        \"admin\": false,\n"
      + "        \"push\": false,\n"
      + "        \"pull\": true\n"
      + "      },\n"
      + "      \"allow_rebase_merge\": true,\n"
      + "      \"template_repository\": null,\n"
      + "      \"allow_squash_merge\": true,\n"
      + "      \"allow_merge_commit\": true,\n"
      + "      \"subscribers_count\": 42,\n"
      + "      \"network_count\": 0\n"
      + "    }\n"
      + "  }";

  private JSONObject issueJsonObject = new JSONObject(issueString);

  @Test
  public void canParseIssueJson() {
    Issue issue = Issue.fromJson(issueJsonObject);
    assertEquals(issue.getUrl(), "https://api.github.com/repos/octocat/Hello-World/issues/1347");
    assertEquals(issue.getState(), "open");
    assertEquals(issue.getHtmlUrl(), "https://github.com/octocat/Hello-World/issues/1347");
    assertEquals(issue.getUser().getLogin(), "octocat");
  }
}
