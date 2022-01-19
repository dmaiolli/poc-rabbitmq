package br.com.denys.poc.rabbit.consumer.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receiver(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
    }

}
