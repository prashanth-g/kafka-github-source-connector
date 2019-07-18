package com.prashanth.kafka.github.source.connector;

import com.fasterxml.jackson.databind.JsonNode;
import com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig;
import java.time.Instant;
import kong.unirest.HttpResponse;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GithubClient {

  private static Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);

  private Integer XRateLimit = 9999;
  private Integer XRateRemaining = 9999;
  private long XRateReset = Instant.MAX.getEpochSecond();

  GitHubSourceConnectorConfig config;

  public GithubClient(GitHubSourceConnectorConfig config) { this.config = config; }

  protected JSONArray getNextIssues(Integer page, Instant since) throws InterruptedException {
    HttpResponse<JsonNode> jsonResponse;
  }

}
