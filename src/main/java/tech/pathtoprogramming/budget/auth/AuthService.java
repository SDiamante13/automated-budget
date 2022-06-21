package tech.pathtoprogramming.budget.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.pathtoprogramming.budget.config.AuthProperties;
import tech.pathtoprogramming.budget.model.AccessTokenDTO;
import tech.pathtoprogramming.budget.model.LinkTokenDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Service
public class AuthService {

    private final HttpClient client;
    private final AuthProperties authProperties;
    private final ObjectMapper objectMapper;

    public AuthService(HttpClient httpClient, AuthProperties authProperties, ObjectMapper objectMapper) {
        this.client = httpClient;
        this.authProperties = authProperties;
        this.objectMapper = objectMapper;
    }

    String getAccessToken(String publicToken) throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString("{"
                        + "\"client_id\": \"" + authProperties.id() + "\","
                        + "\"secret\": \"" + authProperties.secret() + "\","
                        + "\"public_token\": \"" + publicToken + "\"\n"
                        + "}"))
                .uri(new URI(authProperties.url() + "/item/public_token/exchange"))
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        log.info(String.format("Status: %s. Response from link token: %s", response.statusCode(), response.body()));
        AccessTokenDTO accessTokenDTO = objectMapper.readValue(response.body(), AccessTokenDTO.class);
        log.info("access token DTO: " + accessTokenDTO);
        return accessTokenDTO.accessToken();
    }

    public String getLinkToken() throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString("{"
                        + "\"client_id\": \"" + authProperties.id() + "\",\n"
                        + "\"secret\": \"" + authProperties.secret() + "\",\n"
                        + "\"client_name\": \"Automated Budget App\",\n"
                        + "\"country_codes\": [\"US\"],\n"
                        + "\"products\": [\"transactions\"],\n"
                        + "\"language\": \"en\",\n"
                        + "\"user\": {\n"
                        + "\"client_user_id\": \"" + authProperties.id() + "\"\n"
                        + "}\n"
                        + "}"))
                .uri(new URI(authProperties.url() + "/link/token/create"))
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        log.info(String.format("Status: %s Response from link token: %s", response.statusCode(), response.body()));
        LinkTokenDTO linkTokenDTO = objectMapper.readValue(response.body(), LinkTokenDTO.class);
        log.info("link token DTO " + linkTokenDTO);
        return linkTokenDTO.linkToken();
    }
}