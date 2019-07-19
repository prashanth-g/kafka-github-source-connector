package com.prashanth.kafka.github.source.connector;

import com.fasterxml.jackson.databind.JsonNode;
import com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig;
import java.net.ConnectException;
import java.time.Instant;
import kong.unirest.Headers;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.UnirestException;
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

    try{
      jsonResponse = null;
      Headers headers = jsonResponse.getHeaders();
      XRateLimit = Integer.valueOf(headers.getFirst("X-RateLimit-Limit"));
      XRateRemaining = Integer.valueOf(headers.getFirst("X-RateLimit-Remaining"));
      XRateReset = Integer.valueOf(headers.getFirst("X-RateLimit-Reset"));
      switch (jsonResponse.getStatus()) {
        case 200:
          return jsonResponse.getBody().getArray();
        case 401:
          throw new ConnectException("Bad Github credentials provided.Edit your config");
        case 403:
          long sleepTime = XRateReset - Instant.now().getEpochSecond();
          Thread.sleep(1000 * sleepTime);
          return getNextIssues(page, since);
        default:
          Thread.sleep(5000L);
          return getNextIssues(page, since);
      }
    } catch(UnirestException | ConnectException ex) {
      ex.printStackTrace();
      Thread.sleep(5000L);
      return new JSONArray();
    }
  }

}
