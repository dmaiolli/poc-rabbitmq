package br.com.denys.poc.rabbit.producer.service;


import br.com.denys.poc.rabbit.producer.logger.ConsoleLogger;
import com.google.gson.Gson;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object to, Queue queue) {
        this.send(to, queue.getName());
    }

    public void send(Object to, String queueName) {
        ConsoleLogger.info("Sending message to queue \"" + queueName + "\": " + to, this.getClass());
        rabbitTemplate.convertAndSend(queueName, new Gson().toJson(to));
    }
}
