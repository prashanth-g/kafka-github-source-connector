package com.prashanth.kafka.github.source.connector.config;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import org.apache.kafka.common.config.ConfigDef.Validator;
import org.apache.kafka.common.config.ConfigException;

public class TimeStampValidator implements Validator {

  @Override
  public void ensureValid(String name, Object value) {
    String timeStamp = (String) value;
    try {
      Instant.parse(timeStamp);
    } catch (DateTimeParseException ex) {
      throw new ConfigException(name, value, "Was not able to parse date time");
    }
  }
}
