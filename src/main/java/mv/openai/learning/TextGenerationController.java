package mv.openai.learning;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("text.html")
@Produces(MediaType.TEXT_HTML)
public class TextGenerationController {

    @Location("text.html")
    private Template template;
    @Inject
    private OpenAIClient openAIClient;

    public TemplateInstance text(){
        String text = openAIClient.generateText("Generate a snippet of some Djavan songs");
        return template.data("text", text);
    }
}
