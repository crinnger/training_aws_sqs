package br.com.crinnger.Training_AWS_SQS.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageSender {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endpointBasic;
    @Value("${cloud.aws.end-point.urififo}")
    private String endpointFIFO;

    @GetMapping("/send/{message}")
    public void send(@PathVariable(value = "message") String message){
        Message<String> payload= MessageBuilder.withPayload(message).build();
        queueMessagingTemplate.send(endpointBasic,payload);
    }

    @GetMapping("/send/{message}/{groupid}/{duplicateid}")
    public void sendFifo(@PathVariable(value = "message") String message,
                         @PathVariable(value = "groupid") String groupid,
                         @PathVariable(value = "duplicateid") String duplicateid){
        Message<String> payload= MessageBuilder
                .withPayload(message)
                .setHeader("message-group-id",groupid)
                .setHeader("message-deduplication-id",duplicateid) //usado para definir o id da mensagem.
                .build();
        queueMessagingTemplate.send(endpointFIFO,payload);
    }
}
