package net.betvictor.core.controller;

import lombok.RequiredArgsConstructor;
import net.betvictor.core.dto.StatisticDto;
import net.betvictor.core.mapper.StatisticMapping;
import net.betvictor.core.service.ParagraphLength;
import net.betvictor.core.service.TextProcessService;
import net.betvictor.core.service.kafka.KafkaProducer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/betvictor/text", produces = MediaType.APPLICATION_JSON_VALUE)
public class TextProcessingController {

    private final TextProcessService textProcessService;
    private final StatisticMapping statisticMapping;
    private final KafkaProducer kafkaProducer;

    @GetMapping
    public StatisticDto process(@RequestParam("p")
                                @Min(value = 1, message = "p must be grate 0") Integer paragraphNumber,
                                @RequestParam("l")
                                @NotNull(message = "l is mandatory request param") String paragraphLength) {
        StatisticDto dto = textProcessService.processText(paragraphNumber, ParagraphLength.getByCode(paragraphLength));
        kafkaProducer.send(statisticMapping.toModel(dto));
        return dto;
    }

}
