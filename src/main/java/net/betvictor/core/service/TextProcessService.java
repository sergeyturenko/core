package net.betvictor.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.betvictor.core.aspect.TrackExecutionStatisticTime;
import net.betvictor.core.dto.StatisticDto;
import net.betvictor.core.restclient.LoripsumService;
import net.betvictor.core.service.agregation.AggregateService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextProcessService {
    private final LoripsumService loripsumService;
    private final AggregateService aggregateService;


    @TrackExecutionStatisticTime
    public StatisticDto processText(Integer paragraphNumber, ParagraphLength paragraphLength) {
        StatisticDto statisticDto;
        String text = loripsumService.processParser(paragraphNumber, paragraphLength);
        statisticDto = aggregateService.process(text);
        return statisticDto;
    }
}
