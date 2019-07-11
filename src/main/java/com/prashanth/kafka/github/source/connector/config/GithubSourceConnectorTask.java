package com.prashanth.kafka.github.source.connector.config;

import java.util.Map;
import org.apache.kafka.connect.connector.Task;

public class GithubSourceConnectorTask implements Task {

  @Override
  public String version() {
    return null;
  }

  @Override
  public void start(Map<String, String> map) {

  }

  @Override
  public void stop() {

  }
}
