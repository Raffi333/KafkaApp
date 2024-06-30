package rh.example.consumer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rh.example.consumer.model.User;
import rh.example.consumer.repository.UserRepository;
import rh.example.kafkaapp.model.UserEvent;

@Component
public class UserEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    public UserEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "user-created-event-topic")
    public void handlerUserCreated(UserEvent userEvent) {
        User user = User.builder()
                .name(userEvent.getName())
                .surname(userEvent.getSurname())
                .email(userEvent.getEmail())
                .phone(userEvent.getPhone())
                .build();
        userRepository.save(user);
        LOGGER.info("received in user-created-event-topic and saved in DB. Created user id:{}", user.getId());
    }
}
