package br.com.denys.poc.rabbit.consumer.queue;

import br.com.denys.poc.rabbit.consumer.logger.ConsoleLogger;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receiver(@Payload String fileBody) {
        new Gson().toString();
        ConsoleLogger.info(fileBody, this.getClass());
    }

}
