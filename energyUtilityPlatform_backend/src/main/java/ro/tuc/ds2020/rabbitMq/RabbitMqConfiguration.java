package ro.tuc.ds2020.rabbitMq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {
    public static final String queueMessage = "queueMessage";
    public static final String exchangeMessage = "exchangeMessage";
    public static final String routingKeyMessage = "routingKeyMessage";

    @Bean
    public Queue createQueue() {
        return  new Queue(queueMessage);
    }

    @Bean
    public TopicExchange createExchange() {
        return new TopicExchange(exchangeMessage);
    }

    @Bean
    public Binding bindingTogether(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKeyMessage);
    }

    @Bean
    public MessageConverter createMessageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(createMessageConverter());
        return  template;
    }
}
