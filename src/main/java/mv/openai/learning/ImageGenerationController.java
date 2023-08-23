package mv.openai.learning;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("image.html")
@Produces(MediaType.TEXT_HTML)
public class ImageGenerationController {

    @Location("image.html")
    Template template;

    @Inject
    OpenAIClient openAIClient;

    @GET
    public TemplateInstance image() {
        String url = openAIClient.generateImage("photo of a great white shark");
        return template.data("url", url);
    }
}