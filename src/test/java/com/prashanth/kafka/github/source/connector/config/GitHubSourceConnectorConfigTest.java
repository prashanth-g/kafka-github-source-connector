package com.prashanth.kafka.github.source.connector.config;

import static com.prashanth.kafka.GitHubSourceConnectorConfig.AUTH_PASSWORD_CONFIG;
import static com.prashanth.kafka.GitHubSourceConnectorConfig.AUTH_USERNAME_CONFIG;
import static com.prashanth.kafka.GitHubSourceConnectorConfig.OWNER_CONFIG;
import static com.prashanth.kafka.GitHubSourceConnectorConfig.REPO_CONFIG;
import static com.prashanth.kafka.GitHubSourceConnectorConfig.SINCE_CONFIG;
import static com.prashanth.kafka.GitHubSourceConnectorConfig.TOPIC_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BATCH_SIZE_CONFIG;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigValue;
import org.junit.jupiter.api.Test;

public class GitHubSourceConnectorConfigTest {

  private ConfigDef configDef = GitHubSourceConnectorConfig.conf();

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
  public void doc() {
    System.out.println(GitHubSourceConnectorConfig.conf().toRst());
  }

  @Test
  public void initialConfigIsValid() {
    assert (configDef.validate(initialConfig()).stream()
        .allMatch(configValue -> configValue.errorMessages().size() == 0));
  }

  @Test
  public void canReadConfigCorrectly() {
    GitHubSourceConnectorConfig config = new GitHubSourceConnectorConfig(initialConfig());
    config.getOwnerConfig();
  }

  @Test
  public void validateSince() {
    Map<String, String> initialConfig = new HashMap<>();
    initialConfig.put(SINCE_CONFIG, "foo");
    ConfigValue configValue = configDef.validateAll(initialConfig).get(SINCE_CONFIG);
    assert (configValue.errorMessages().size() > 0);
  }

  @Test
  public void validateBatchSize() {
    Map<String, String> initialConfigMin = new HashMap<>();
    initialConfigMin.put(BATCH_SIZE_CONFIG, "-1");
    ConfigValue configValueMin = configDef.validateAll(initialConfigMin).get(BATCH_SIZE_CONFIG);
    assert (configValueMin.errorMessages().size() > 0);

    Map<String, String> initialConfigMax = new HashMap<>();
    initialConfigMax.put(BATCH_SIZE_CONFIG, "101");
    ConfigValue configValueMax = configDef.validateAll(initialConfigMax).get(BATCH_SIZE_CONFIG);
    assert (configValueMax.errorMessages().size() > 0);
  }

  @Test
  public void validateUsername() {
    Map<String, String> initialConfig = new HashMap<>();
    initialConfig.put(AUTH_USERNAME_CONFIG, "foo");
    ConfigValue configValue = configDef.validateAll(initialConfig).get(AUTH_USERNAME_CONFIG);
    assert (configValue.errorMessages().size() == 0);
  }

  @Test
  public void validatePassword() {
    Map<String, String> initialConfig = new HashMap<>();
    initialConfig.put(AUTH_PASSWORD_CONFIG, "foo");
    ConfigValue configValue = configDef.validateAll(initialConfig).get(AUTH_PASSWORD_CONFIG);
    assert (configValue.errorMessages().size() == 0);
  }

}