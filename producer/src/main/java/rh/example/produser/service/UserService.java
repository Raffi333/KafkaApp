package rh.example.produser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import rh.example.kafkaapp.model.UserEvent;
import rh.example.produser.conf.KafkaConf;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private KafkaTemplate<String, UserEvent> kafkaTemplate;

    public UserService( KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String createUser(UserEvent userEvent) throws ExecutionException, InterruptedException {
        String userId = UUID.randomUUID().toString();
        SendResult<String, UserEvent> result =kafkaTemplate.send(KafkaConf.USER_CREATED_EVENT_TOPIC,userId,userEvent).get();
        LOGGER.info("Send UserEvent by id:{} with data:{}",userId,userEvent.toString());
        return userId;
    }
}
