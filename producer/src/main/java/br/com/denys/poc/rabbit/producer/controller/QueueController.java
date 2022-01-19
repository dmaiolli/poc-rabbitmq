package br.com.denys.poc.rabbit.producer.controller;

import br.com.denys.poc.rabbit.producer.queue.QueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueProducer queueProducer;


    @GetMapping
    public String send() {
        queueProducer.send("test message");
        return "ok. done";
    }

}
