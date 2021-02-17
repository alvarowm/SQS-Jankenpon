package br.alvaro;

import br.alvaro.models.Mao;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jankenpon/mao1")
@Produces(MediaType.TEXT_PLAIN)
public class JanKenPonMaoProducerResource {

    @Inject
    SqsClient sqs;

    @ConfigProperty(name = "queue.url")
    String queueUrl;

    @POST
    @Path("/play")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessage(Mao mao){
        String message = mao.getMao();
        SendMessageResponse response = sqs.sendMessage(m -> m.queueUrl(queueUrl).messageBody(message));
        return Response.ok().entity(response.messageId()).build();
    }

}
