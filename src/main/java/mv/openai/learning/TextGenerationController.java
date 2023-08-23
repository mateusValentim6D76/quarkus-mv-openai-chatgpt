package mv.openai.learning;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("text.html")
@Produces(MediaType.TEXT_HTML)
public class TextGenerationController {
}
