package com.prashanth.kafka.github.source.connector;

import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.BATCH_SIZE_CONFIG;
import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.OWNER_CONFIG;
import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.REPO_CONFIG;
import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.SINCE_CONFIG;
import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.TOPIC_CONFIG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kong.unirest.Header;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.UnirestException;
import org.junit.jupiter.api.Test;

public class GithubSourceTaskTest {

  private GithubSourceConnectorTask gitHubSourceTask = new GithubSourceConnectorTask();
  private Integer batchSize = 10;

  private Map<String, String> initialConfig() {
    Map<String, String> baseProps = new HashMap<>();
    baseProps.put(OWNER_CONFIG, "microsoft");
    baseProps.put(REPO_CONFIG, "vscode");
    baseProps.put(SINCE_CONFIG, "2019-07-01T00:00:00Z");
    baseProps.put(BATCH_SIZE_CONFIG, batchSize.toString());
    baseProps.put(TOPIC_CONFIG, "github-issues");
    return baseProps;
  }


  @Test
  public void test() throws UnirestException {
    gitHubSourceTask.config = new GitHubSourceConnectorConfig(initialConfig());
    gitHubSourceTask.nextPageToVisit = 1;
    gitHubSourceTask.nextQuerySince = Instant.parse("2017-01-01T00:00:00Z");
    gitHubSourceTask.githubClient = new GithubClient(gitHubSourceTask.config);
    String url = gitHubSourceTask.githubClient.constructUrl(gitHubSourceTask.nextPageToVisit, gitHubSourceTask.nextQuerySince);
    System.out.println(url);
    HttpResponse<JsonNode> httpResponse = gitHubSourceTask.githubClient.getNextIssuesAPI(gitHubSourceTask.nextPageToVisit, gitHubSourceTask.nextQuerySince);
    if (httpResponse.getStatus() != 403) {
      assertEquals(200, httpResponse.getStatus());
      List<Header> headers = httpResponse.getHeaders().all();
      assertTrue(headers.stream().anyMatch(header -> header.getName().equals("X-RateLimit-Remaining")));
    }
  }
}
