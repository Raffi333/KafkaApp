package rh.example.produser.conf;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import rh.example.kafkaapp.model.UserEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConf {

    public static final String USER_CREATED_EVENT_TOPIC = "user-created-event-topic";
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapService;
    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;
    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;
    @Value("${spring.kafka.producer.acks}")
    private String acks;
    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;
    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String propertiesLinger;
    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;
    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private String idempotence;
    @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private String flightRequestPerConnection;

    private Map<String, Object> getKafkaConfigProperties() {
        Map<String, Object> conf = new HashMap<>();
        conf.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapService);
        conf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        conf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        conf.put(ProducerConfig.ACKS_CONFIG, acks);
        conf.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeout);
        conf.put(ProducerConfig.LINGER_MS_CONFIG, propertiesLinger);
        conf.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
        conf.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, idempotence);
        conf.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, flightRequestPerConnection);
        return conf;
    }

    @Bean
    public ProducerFactory<String, UserEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(getKafkaConfigProperties());
    }

    @Bean
    public KafkaTemplate<String, UserEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name(USER_CREATED_EVENT_TOPIC)
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }

}
