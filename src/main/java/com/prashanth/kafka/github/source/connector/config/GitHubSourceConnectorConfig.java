package com.prashanth.kafka.github.source.connector.config;

import com.prashanth.kafka.github.source.connector.validator.BatchSizeValidator;
import com.prashanth.kafka.github.source.connector.validator.TimeStampValidator;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Map;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

public class GitHubSourceConnectorConfig extends AbstractConfig {

  static final String TOPIC_CONFIG = "topic";
  static final String TOPIC_DOC = "Topic to write to";

  static final String OWNER_CONFIG = "github.owner";
  static final String OWNER_DOC = "Owner of the github account";

  static final String REPO_CONFIG = "github.repo";
  static final String REPO_DOC = "Repository that ";

  static final String SINCE_CONFIG = "since.timestamp";
  static final String SINCE_DOC = "Only issues created after this timestamp will be captured";

  static final String BATCH_SIZE_CONFIG = "batch.size";
  static final String BATCH_SIZE_DOC = "Number of data points that the data can be captured";

  static final String AUTH_USERNAME_CONFIG = "auth.username";
  static final String AUTH_USERNAME_DOC = "Optional Username of the github account";

  static final String AUTH_PASSWORD_CONFIG = "auth.password";
  static final String AUTH_PASSWORD_DOC = "Optional Password of the github account";

  public GitHubSourceConnectorConfig(ConfigDef configDef,
      Map<String, String> parsedConfig) {
    super(configDef, parsedConfig);
  }

  public GitHubSourceConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        .define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, TOPIC_DOC)
        .define(OWNER_CONFIG, Type.STRING, Importance.HIGH, OWNER_DOC)
        .define(REPO_CONFIG, Type.STRING, Importance.HIGH, REPO_DOC)
        .define(BATCH_SIZE_CONFIG, Type.INT, 100, new BatchSizeValidator(),
            Importance.LOW, BATCH_SIZE_DOC)
        .define(SINCE_CONFIG, Type.STRING,
            ZonedDateTime.now().minusYears(1).toInstant().toString(),
            new TimeStampValidator(), Importance.HIGH, SINCE_DOC)
        .define(AUTH_USERNAME_CONFIG, Type.STRING, "", Importance.HIGH,
            AUTH_USERNAME_DOC)
        .define(AUTH_PASSWORD_CONFIG, Type.STRING, "", Importance.HIGH,
            AUTH_PASSWORD_DOC);
  }

  public String getTopic() {
    return this.getString(TOPIC_CONFIG);
  }

  public String getRepoConfig() {
    return this.getString(REPO_CONFIG);
  }

  public String getBatchSize() {
    return this.getString(BATCH_SIZE_CONFIG);
  }

  public Instant getSince() {
    return Instant.parse(this.getString(SINCE_CONFIG));
  }

  public String getOwnerConfig() {
    return this.getString(OWNER_CONFIG);
  }

  public String getAuthUsername() {
    return this.getString(AUTH_USERNAME_CONFIG);
  }

  public String getAuthPassword() {
    return this.getString(AUTH_PASSWORD_CONFIG);
  }
}
