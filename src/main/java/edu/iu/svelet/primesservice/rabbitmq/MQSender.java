package edu.iu.svelet.primesservice.rabbitmq;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.text.MessageFormat;

@Component
public class MQSender {
    private final Queue queue;
    private final RabbitTemplate rabbitTemplate;

    public MQSender(Queue queue, RabbitTemplate rabbitTemplate) {
        this.queue = queue;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(int n, boolean isPrime) {
        String message =
                MessageFormat
                        .format("customer:{0}, n: {1}, isPrime: {2}",
                                "username", Integer.toString(n), isPrime);
        message = "{" + message + "}";
        rabbitTemplate.convertAndSend("primes", message);
    }


}
