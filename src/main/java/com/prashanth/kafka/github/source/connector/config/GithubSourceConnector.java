package com.prashanth.kafka.github.source.connector.config;

import java.util.List;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GithubSourceConnector extends SourceConnector {

  private static Logger LOGGER = LoggerFactory.getLogger(GithubSourceConnector.class);
  private GitHubSourceConnectorConfig config;
  private static final String VERSION = "0.0.1";

  @Override
  public void start(Map<String, String> map) {
    config = new GitHubSourceConnectorConfig(map);
  }

  @Override
  public Class<? extends Task> taskClass() {
    return null;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int i) {
    return null;
  }

  @Override
  public void stop() {

  }

  @Override
  public ConfigDef config() {
    return null;
  }

  @Override
  public String version() {
    return VERSION;
  }
}
