package net.betvictor.core.service.kafka;

import lombok.RequiredArgsConstructor;
import net.betvictor.core.service.kafka.model.StatisticModel;
import net.betvictor.core.utils.KafkaTemplateService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplateService<StatisticModel> kafkaTemplateService;

    public void send(StatisticModel statisticDto) {
        kafkaTemplateService.sendKafkaMessage("words.processed",
                statisticDto.getFreqWord(),
                statisticDto
        );
    }
}
