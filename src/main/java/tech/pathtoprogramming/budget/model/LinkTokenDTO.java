package tech.pathtoprogramming.budget.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkTokenDTO {

    @JsonProperty("link_token")
    private String linkToken;

    public String linkToken() {
        return linkToken;
    }

    public void setLinkToken(String linkToken) {
        this.linkToken = linkToken;
    }

    @Override
    public String toString() {
        return "LinkTokenDTO{"
                + "linkToken='" + linkToken + '\''
                + '}';
    }
}
