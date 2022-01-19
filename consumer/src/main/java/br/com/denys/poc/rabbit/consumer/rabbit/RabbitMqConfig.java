package br.com.denys.poc.rabbit.consumer.rabbit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableRabbit
@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")
})
public class RabbitMqConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port:5672}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        logger.info("Creating connection factory with: " + username + "@" + host + ":" + port);

        return connectionFactory;
    }

    /**
     * Required for executing adminstration functions against an AMQP Broker
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * This queue will be declared. This means it will be created if it does not exist. Once declared, you can do something
     * like the following:
     *
     * @RabbitListener(queues = "#{@myDurableQueue}")
     * @Transactional
     * public void handleMyDurableQueueMessage(CustomDurableDto myMessage) {
     *    // Anything you want! This can also return a non-void which will queue it back in to the queue attached to @RabbitListener
     * }
     */
    @Bean
    public Queue durableQueue() {
        logger.info("Creating my_durable queue");
        return new Queue("my_durable", true, false, false);
    }

    /**
     * The following is a complete declaration of an exchange, a queue and a exchange-queue binding
     */
    @Bean
    public TopicExchange emailExchange() {
        logger.info("Creating email exchange");
        return new TopicExchange("email", true, false);
    }

    @Bean
    public Queue inboudEmailQueue() {
        logger.info("Creating email_inbound queue");
        return new Queue("email_inbound", true, false, false);
    }

    @Bean
    public Binding inboundEmailExchangeBinding() {
        return BindingBuilder.bind(inboudEmailQueue()).to(emailExchange()).with("from.*");
    }



}
