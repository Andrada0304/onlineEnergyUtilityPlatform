package ro.tuc.ds2020.rabbitMq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.websocket.TextMessageDTO;
import ro.tuc.ds2020.websocket.WebSocketController;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate rabbitTemplate;
    List<QueueMessage> list = new ArrayList<QueueMessage>();
    private double valPrec = -1;
    private QueueMessage precQueue =null;
    @RabbitListener(queues = RabbitMqConfiguration.queueMessage)
    public void listener(QueueMessage message) {
        System.out.println(message);
        TextMessageDTO text = new TextMessageDTO();
        if(message.getMeasurement_value()>30.0){
            text.setMessage("Senzorul cu id-ul "+ message.getSensor_id()+" a depasit limita maxima");
        }else{
            text.setMessage("Senzorul cu id-ul "+message.getSensor_id() +" nu a depasit limita");
        }
        rabbitTemplate.convertAndSend("/topic/message", text);

    }

}