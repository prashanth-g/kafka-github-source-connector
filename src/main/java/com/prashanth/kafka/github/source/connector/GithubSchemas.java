package com.prashanth.kafka.github.source.connector;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Timestamp;

public class GithubSchemas {

  public static final String NEXT_PAGE_FIELD = "next_page";

  // Issue fields
  public static final String FULL_REPO_FIELD = "owner/repository";
  public static final String OWNER_FIELD = "owner";
  public static final String REPOSITORY_FIELD = "repository";
  public static final String CREATED_AT_FIELD = "created_at";
  public static final String UPDATED_AT_FIELD = "updated_at";
  public static final String NUMBER_FIELD = "number";
  public static final String URL_FIELD = "url";
  public static final String HTML_URL_FIELD = "html_url";
  public static final String TITLE_FIELD = "title";
  public static final String STATE_FIELD = "state";

  // User fields
  public static final String USER_FIELD = "user";
  public static final String USER_URL_FIELD = "url";
  public static final String USER_HTML_URL_FIELD = "html_url";
  public static final String USER_ID_FIELD = "id";
  public static final String USER_LOGIN_FIELD = "login";

  // PR fields
  public static final String PR_FIELD = "pull_request";
  public static final String PR_URL_FIELD = "url";
  public static final String PR_HTML_URL_FIELD = "html_url";

  // Schema names
  public static final String SCHEMA_KEY = "Github Issue Key";
  public static final String SCHEMA_VALUE_ISSUE = "Issue";
  public static final String SCHEMA_VALUE_USER = "User";
  public static final String SCHEMA_VALUE_PR = "PR";

  // Key Schema
  public static Schema KEY_SCHEMA = SchemaBuilder.struct().name(SCHEMA_KEY)
      .version(1)
      .field(OWNER_FIELD, Schema.STRING_SCHEMA)
      .field(REPOSITORY_FIELD, Schema.STRING_SCHEMA)
      .field(NUMBER_FIELD, Schema.INT32_SCHEMA)
      .build();

  // Value schema
  public static Schema USER_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_USER)
      .version(1)
      .field(USER_URL_FIELD, Schema.STRING_SCHEMA)
      .field(USER_ID_FIELD, Schema.INT32_SCHEMA)
      .field(USER_LOGIN_FIELD, Schema.STRING_SCHEMA)
      .build();

  // Value schema
  public static Schema PR_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_PR)
      .version(1)
      .field(PR_URL_FIELD, Schema.STRING_SCHEMA)
      .field(PR_HTML_URL_FIELD, Schema.STRING_SCHEMA)
      .optional()
      .build();

  // Value schema
  public static Schema VALUE_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_USER)
      .version(1)
      .field(URL_FIELD, Schema.STRING_SCHEMA)
      .field(TITLE_FIELD, Schema.STRING_SCHEMA)
      .field(CREATED_AT_FIELD, Timestamp.SCHEMA)
      .field(UPDATED_AT_FIELD, Timestamp.SCHEMA)
      .field(NUMBER_FIELD, Schema.INT32_SCHEMA)
      .field(STATE_FIELD, Schema.STRING_SCHEMA)
      .field(USER_FIELD, USER_SCHEMA)
      .field(PR_FIELD, PR_SCHEMA)
      .build();

}
