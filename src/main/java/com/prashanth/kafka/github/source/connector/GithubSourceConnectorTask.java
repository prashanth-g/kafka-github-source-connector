package com.prashanth.kafka.github.source.connector;

import static com.prashanth.kafka.github.source.connector.GithubSchemas.NEXT_PAGE_FIELD;
import static com.prashanth.kafka.github.source.connector.GithubSchemas.OWNER_FIELD;
import static com.prashanth.kafka.github.source.connector.GithubSchemas.REPOSITORY_FIELD;
import static com.prashanth.kafka.github.source.connector.GithubSchemas.UPDATED_AT_FIELD;

import com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.connect.connector.Task;

public class GithubSourceConnectorTask implements Task {

  GitHubSourceConnectorConfig config;

  @Override
  public String version() {
    return null;
  }

  @Override
  public void start(Map<String, String> map) {
    config = new GitHubSourceConnectorConfig(map);
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
