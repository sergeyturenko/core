package net.betvictor.core.service.agregation;

import lombok.RequiredArgsConstructor;
import net.betvictor.core.dto.StatisticDto;
import net.betvictor.core.service.parser.ParagraphParser;
import net.betvictor.core.service.parser.WordParser;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AggregateService {

    private final ParagraphParser paragraphParser;
    private final WordParser wordParser;
    private final FreqWordAggregate freqWordAggregate;

    public StatisticDto process(String text) {
        StopWatch partTimeStopWatch = new StopWatch();
        StatisticDto statisticDto = new StatisticDto();
        partTimeStopWatch.start();
        String[] paragraphs = paragraphParser.parse(text);
        int countWords = 0;
        Map<String, Long> freqWordMap = new HashMap<>();
        for (String tmpText : paragraphs) {
            String[] words = wordParser.parse(tmpText);
            countWords += words.length;
            freqWordAggregate.process(freqWordMap, words);
        }
        statisticDto.setFreqWord(freqWordMap.entrySet().stream()
                .sorted((o1, o2) -> Long.compare(o2.getValue(), o1.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(""));
        partTimeStopWatch.stop();
        statisticDto.setAvgParagraphProcessingTime(partTimeStopWatch.getLastTaskTimeMillis() / paragraphs.length);
        statisticDto.setAvgParagraphSize(countWords / paragraphs.length);
        return statisticDto;
    }
}
