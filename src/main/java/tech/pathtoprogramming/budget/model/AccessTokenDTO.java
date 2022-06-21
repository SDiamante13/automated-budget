package tech.pathtoprogramming.budget.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenDTO {

    @JsonProperty("access_token")
    private String accessToken;

    public String accessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{"
                + "accessToken='" + accessToken + '\''
                + '}';
    }
}
