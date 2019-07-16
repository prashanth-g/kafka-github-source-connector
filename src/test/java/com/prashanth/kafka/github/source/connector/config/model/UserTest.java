package com.prashanth.kafka.github.source.connector.config.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prashanth.kafka.github.source.connector.model.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class UserTest {
  String userJsonString = "{\n"
      + "  \"login\": \"prashanth-g\",\n"
      + "  \"id\": 7456647,\n"
      + "  \"node_id\": \"MDQ6VXNlcjc0NTY2NDc=\",\n"
      + "  \"avatar_url\": \"https://avatars2.githubusercontent.com/u/7456647?v=4\",\n"
      + "  \"gravatar_id\": \"\",\n"
      + "  \"url\": \"https://api.github.com/users/prashanth-g\",\n"
      + "  \"html_url\": \"https://github.com/prashanth-g\",\n"
      + "  \"followers_url\": \"https://api.github.com/users/prashanth-g/followers\",\n"
      + "  \"following_url\": \"https://api.github.com/users/prashanth-g/following{/other_user}\",\n"
      + "  \"gists_url\": \"https://api.github.com/users/prashanth-g/gists{/gist_id}\",\n"
      + "  \"starred_url\": \"https://api.github.com/users/prashanth-g/starred{/owner}{/repo}\",\n"
      + "  \"subscriptions_url\": \"https://api.github.com/users/prashanth-g/subscriptions\",\n"
      + "  \"organizations_url\": \"https://api.github.com/users/prashanth-g/orgs\",\n"
      + "  \"repos_url\": \"https://api.github.com/users/prashanth-g/repos\",\n"
      + "  \"events_url\": \"https://api.github.com/users/prashanth-g/events{/privacy}\",\n"
      + "  \"received_events_url\": \"https://api.github.com/users/prashanth-g/received_events\",\n"
      + "  \"type\": \"User\",\n"
      + "  \"site_admin\": false,\n"
      + "  \"name\": \"Prashanth\",\n"
      + "  \"company\": null,\n"
      + "  \"blog\": \"\",\n"
      + "  \"location\": null,\n"
      + "  \"email\": null,\n"
      + "  \"hireable\": true,\n"
      + "  \"bio\": \"listening, learning, exploring, experimenting at the moment\",\n"
      + "  \"public_repos\": 58,\n"
      + "  \"public_gists\": 10,\n"
      + "  \"followers\": 0,\n"
      + "  \"following\": 31,\n"
      + "  \"created_at\": \"2014-05-01T08:03:13Z\",\n"
      + "  \"updated_at\": \"2019-07-20T11:06:28Z\"\n"
      + "}";

  private JSONObject userJsonObject = new JSONObject(userJsonString);

  @Test
  public void canParseJson() {
    User user = User.fromJson(userJsonObject);
    assertEquals(user.getLogin(), "prashanth-g");
  }
}
