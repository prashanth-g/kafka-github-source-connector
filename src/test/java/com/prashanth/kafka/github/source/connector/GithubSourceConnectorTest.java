package com.prashanth.kafka.github.source.connector;

import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.OWNER_CONFIG;
import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.REPO_CONFIG;
import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.SINCE_CONFIG;
import static com.prashanth.kafka.github.source.connector.config.GitHubSourceConnectorConfig.TOPIC_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BATCH_SIZE_CONFIG;

import com.prashanth.kafka.github.source.connector.GithubSourceConnector;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GithubSourceConnectorTest {

  private Map<String, String> initialConfig() {
    Map<String, String> initialProperties = new HashMap<>();
    initialProperties.put(OWNER_CONFIG, "foo");
    initialProperties.put(REPO_CONFIG, "bar");
    initialProperties.put(SINCE_CONFIG, "2017-04-26T01:23:45Z");
    initialProperties.put(BATCH_SIZE_CONFIG, "100");
    initialProperties.put(TOPIC_CONFIG, "github-issues");
    return initialProperties;
  }

  @Test
  public void taskConfigsShouldReturnOneTaskConfig() {
    GithubSourceConnector githubSourceConnector = new GithubSourceConnector();
    githubSourceConnector.start(initialConfig());
    assertEquals(githubSourceConnector.taskConfigs(1).size(), 1);
    assertEquals(githubSourceConnector.taskConfigs(10).size(), 1);
  }

}
