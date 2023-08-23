package mv.openai.learning;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.inject.Inject;

@Path("texts")
@ApplicationScoped
public class TextGenerationResource {

    @Inject
    OpenAIClient openAIClient;

    @GET
    public String text(){
        return openAIClient.generateText("Top songs of 2021");
    }
}
