package tech.pathtoprogramming.budget.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "plaid.client")
public class AuthProperties {
    private String id;
    private String secret;
    private String url;

    public String id() {
        return id;
    }

    public String secret() {
        return secret;
    }

    public String url() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
