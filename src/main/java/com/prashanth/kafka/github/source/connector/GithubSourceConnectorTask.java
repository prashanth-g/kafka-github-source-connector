package com.prashanth.kafka.github.source.connector;

import static com.prashanth.kafka.github.source.connector.GithubSchemas.NEXT_PAGE_FIELD;
import static com.prashanth.kafka.github.source.connector.GithubSchemas.NUMBER_FIELD;
import static com.prashanth.kafka.github.source.connector.GithubSchemas.OWNER_FIELD;
import static com.prashanth.kafka.github.source.connector.GithubSchemas.REPOSITORY_FIELD;
import static com.prashanth.kafka.github.source.connector.GithubSchemas.UPDATED_AT_FIELD;

import com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GithubSourceConnectorTask extends SourceTask {

  private static final Logger LOGGER = LoggerFactory.getLogger(GithubSourceConnectorTask.class);
  GitHubSourceConnectorConfig config;

  protected Instant nextQuerySince;
  protected Integer lastIssueNumber;
  protected Integer nextPageToVisit = 1;
  protected Instant lastUpdatedAt;

  GithubClient githubClient;

  private static final String VERSION = "0.0.1";

  @Override
  public String version() {
    return VERSION;
  }

  @Override
  public void start(Map<String, String> map) {
    config = new GitHubSourceConnectorConfig(map);
    initializeLastVariables();
    githubClient = new GithubClient(config);
  }

  @Override
  public List<SourceRecord> poll() throws InterruptedException {
    return null;
  }

  private void initializeLastVariables(){
    Map<String, Object> lastSourceOffset = null;
    lastSourceOffset = context.offsetStorageReader().offset(sourcePartition());
    if( lastSourceOffset == null){
      // we haven't fetched anything yet, so we initialize to 7 days ago
      nextQuerySince = config.getSince();
      lastIssueNumber = -1;
    } else {
      Object updatedAt = lastSourceOffset.get(UPDATED_AT_FIELD);
      Object issueNumber = lastSourceOffset.get(NUMBER_FIELD);
      Object nextPage = lastSourceOffset.get(NEXT_PAGE_FIELD);
      if(updatedAt != null && (updatedAt instanceof String)){
        nextQuerySince = Instant.parse((String) updatedAt);
      }
      if(issueNumber != null && (issueNumber instanceof String)){
        lastIssueNumber = Integer.valueOf((String) issueNumber);
      }
      if (nextPage != null && (nextPage instanceof String)){
        nextPageToVisit = Integer.valueOf((String) nextPage);
      }
    }
  }

  private Map<String, String> sourcePartition() {
    Map<String, String> map = new HashMap<>();
    map.put(OWNER_FIELD, config.getOwnerConfig());
    map.put(REPOSITORY_FIELD, config.getRepoConfig());
    return map;
  }

  @Override
  public void stop() {

  }

  private Map<String, String> sourcePartion() {
    Map<String, String> map = new HashMap<>();
    map.put(OWNER_FIELD, config.getOwnerConfig());
    map.put(REPOSITORY_FIELD, config.getRepoConfig());
    return map;
  }
}
