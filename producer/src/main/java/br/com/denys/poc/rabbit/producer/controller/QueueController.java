package br.com.denys.poc.rabbit.producer.controller;

import br.com.denys.poc.rabbit.producer.service.RabbitMqSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private RabbitMqSenderService rabbitMqSenderService;

    @GetMapping
    public String send() {
        rabbitMqSenderService.send(new Object(), "my_durable");
        return "ok. done";
    }

}
