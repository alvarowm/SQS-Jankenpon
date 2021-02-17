package br.alvaro;

import br.alvaro.handler.JanKenPanHandler;
import br.alvaro.models.Mao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;


@Path("/jankenpon/mao2")
public class JanKenPonConsumerResource {

    @Inject
    SqsClient sqs;

    @ConfigProperty(name = "queue.url")
    String queueUrl;

    @GET
    @Path("/play")
    public List<String> receive() {
        List<Message> messages = sqs.receiveMessage(m -> m.maxNumberOfMessages(10).queueUrl(queueUrl)).messages();
        JanKenPanHandler handler = new JanKenPanHandler();
        List<String> saidas = new ArrayList<>();
         messages.stream().forEach(m ->
                 {
                     String jogada = handler.gerarJogada();
                     Boolean seVenci = handler.sePrimeiroJogadorGanhou(jogada, m.body());
                     if (seVenci == null)
                         saidas.add("Empate com " + jogada + "!");
                     else if (seVenci)
                         saidas.add("Venci jogador 2 jogando " + jogada + " e ele " + m.body()  + " =P ");
                     else
                         saidas.add("Perdi do jogador 2 jogando " + jogada + " e ele " + m.body() + " =***( ");
                 });

         return  saidas;
    }
}
