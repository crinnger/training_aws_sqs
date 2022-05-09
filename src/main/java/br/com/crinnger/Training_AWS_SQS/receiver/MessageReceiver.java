package br.com.crinnger.Training_AWS_SQS.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@Slf4j
public class MessageReceiver {

    @SqsListener(value = "treinamento")
    public void  receive(String message){
        log.info("message recieve: {}",message);
    }

    @SqsListener(value = "treinamento.fifo",deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void  receiveFIFO(String message){

        log.info("message recieve: {}",message);

    }

}
