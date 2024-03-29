package com.prashanth.kafka.github.source.connector.validator;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Validator;
import org.apache.kafka.common.config.ConfigException;

public class BatchSizeValidator implements Validator {

  public void ensureValid(String name, Object value) {
    Integer batchSize = (Integer) value;
    if(!(1<= batchSize && batchSize <= 100)) {
      throw new ConfigException(name, value, "Batch size should be between the range 0 < 100");
    }
  }
}
