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
    private WebTarget imageGenerationsTarget;


    @Inject
    @ConfigProperty(name = "openai.secret-key")
    String secretKey;

    @PostConstruct
    void initClient() {
        clientBuilder = ClientBuilder.newClient();
        textCompletionsTarget = clientBuilder.target("https://api.openai.com/v1/completions");
        imageGenerationsTarget = clientBuilder.target("https://api.openai.com/v1/images/generations");

    }

    public String generateText(String prompt) {
        JsonObject json = Json.createObjectBuilder()
                .add("model", "text-davinci-003")
                .add("prompt", prompt)
                .add("max_tokens", 64).build();

        System.out.println(json.toString());
        JsonObject jsonObject;
        try (Response response = textCompletionsTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .post(Entity.json(json))) {

            jsonObject = response.readEntity(JsonObject.class);
        }

        return jsonObject.getJsonArray("choices").getJsonObject(0).getString("text");
    }

    public String generateImage(String prompt) {
        JsonObject json = Json.createObjectBuilder()
                .add("prompt", prompt)
                .add("n", 1)
                .add("size", "1024x1024")
                .build();

        JsonObject jsonObject;
        try (Response response = imageGenerationsTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .post(Entity.json(json))) {

            jsonObject = response.readEntity(JsonObject.class);
        }
        System.out.println("jsonObject = " + jsonObject.toString());

        return jsonObject.getJsonArray("data").getJsonObject(0).getString("url");
    }


    @PreDestroy
    void close() {
        clientBuilder.close();
    }
}
