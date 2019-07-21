package com.prashanth.kafka.github.source.connector;

import com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig;
import java.time.Instant;
import kong.unirest.GetRequest;
import kong.unirest.Headers;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.apache.kafka.connect.errors.ConnectException;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GithubClient {

  private static Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);
  GitHubSourceConnectorConfig config;
  private Integer XRateLimit = 9999;
  private Integer XRateRemaining = 9999;
  private long XRateReset = Instant.MAX.getEpochSecond();

  public GithubClient(GitHubSourceConnectorConfig config) {
    this.config = config;
  }

  protected JSONArray getNextIssues(Integer page, Instant since)
      throws InterruptedException {
    HttpResponse<JsonNode> jsonResponse;

    try {
      jsonResponse = null;
      Headers headers = jsonResponse.getHeaders();
      XRateLimit = Integer.valueOf(headers.getFirst("X-RateLimit-Limit"));
      XRateRemaining = Integer
          .valueOf(headers.getFirst("X-RateLimit-Remaining"));
      XRateReset = Integer.valueOf(headers.getFirst("X-RateLimit-Reset"));
      switch (jsonResponse.getStatus()) {
        case 200:
          return jsonResponse.getBody().getArray();
        case 401:
          throw new ConnectException(
              "Bad Github credentials provided.Edit your config");
        case 403:
          long sleepTime = XRateReset - Instant.now().getEpochSecond();
          Thread.sleep(1000 * sleepTime);
          return getNextIssues(page, since);
        default:
          Thread.sleep(5000L);
          return getNextIssues(page, since);
      }
    } catch (UnirestException ex) {
      ex.printStackTrace();
      Thread.sleep(5000L);
      return new JSONArray();
    }
  }

  protected HttpResponse<JsonNode> getNextIssuesAPI(Integer page, Instant since)
      throws UnirestException {
    GetRequest unirest = Unirest.get(constructUrl(page, since));
    if (!config.getAuthUsername().isEmpty() && !config.getAuthPassword()
        .isEmpty()) {
      unirest = unirest
          .basicAuth(config.getAuthUsername(), config.getAuthPassword());
    }
    LOGGER.debug(String.format("GET %s", unirest.getUrl()));
    return unirest.asJson();
  }

  protected String constructUrl(Integer page, Instant since) {
    return String.format(
        "https://api.github.com/repos/%s/%s/issues?page=%s&per_page=%s&since=%s&state=all&direction=asc&sort=updated",
        config.getOwnerConfig(),
        config.getRepoConfig(),
        page,
        config.getBatchSize(),
        since.toString());
  }

  public void sleep() throws InterruptedException {
    long sleepTime = (long) Math.ceil(
        (double) (XRateReset - Instant.now().getEpochSecond()) / XRateRemaining);
    LOGGER.debug(String.format("Sleeping for %s seconds", sleepTime ));
    Thread.sleep(1000 * sleepTime);
  }

  public void sleepIfNeed() throws InterruptedException {
    if (XRateRemaining <= 10 && XRateRemaining > 0) {
      LOGGER.info(String.format("Approaching limit soon, you have %s requests left", XRateRemaining));
      sleep();
    }
  }
}
