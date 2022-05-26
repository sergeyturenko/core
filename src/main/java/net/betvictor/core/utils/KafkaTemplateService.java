package net.betvictor.core.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTemplateService<T> {

    private final KafkaTemplate<String, T> template;

    public void sendKafkaMessage(String topicName, String key, T messageObject) {
        ProducerRecord<String, T> record = new ProducerRecord<>(topicName, key, messageObject);
        ListenableFuture<SendResult<String, T>> sendResultFuture = template.send(record);
        sendResultFuture.addCallback(
                new ListenableFutureCallback<>() {
                    @Override
                    public void onSuccess(SendResult<String, T> result) {
                        // do nothing here
                    }
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Error when sending Kafka message", ex);
                    }
                });
    }
}
