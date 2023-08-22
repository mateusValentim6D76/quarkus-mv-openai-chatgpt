package mv.openai.learning;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;


@ApplicationScoped
public class OpenAIClient {

    private Client clientBuilder;
    private WebTarget target;

    @Inject
    @ConfigProperty(name = "openai.secret-key")
    String secretKey;

    @PostConstruct
    void initClient() {
        clientBuilder = ClientBuilder.newClient();
        target = clientBuilder.target("https://api.openai.com/v1/completions");
    }

    public String generateText(String prompt) {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("model", "text-davinci-003")
                .add("prompt", prompt)
                .add("max_tokens", 64)
                .build();

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .post(Entity.json(jsonObject));

        return response.readEntity(String.class);
    }

    @PreDestroy
    void close() {
        clientBuilder.close();
    }
}
