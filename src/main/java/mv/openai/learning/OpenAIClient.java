package mv.openai.learning;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ApplicationScoped
public class OpenAIClient {

    Client clientBuilder;
    WebTarget textCompletionsTarget;

    @Inject
    @ConfigProperty(name = "openai.secret-key")
    String secretKey;

    @PostConstruct
    void initClient() {
        clientBuilder = ClientBuilder.newClient();
        textCompletionsTarget = clientBuilder.target("https://api.openai.com/v1/completions");
    }

    public String generateText(String prompt) {
        JsonObject json = Json.createObjectBuilder()
                .add("model", "text-davinci-003")
                .add("prompt", prompt)
                .add("max_tokens", 1000).build();

        System.out.println(json.toString());
        try (Response response = textCompletionsTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .post(Entity.json(json))) {

            return response.readEntity(String.class);
        }
    }

    @PreDestroy
    void close() {
        clientBuilder.close();
    }
}
