package net.betvictor.core.service.agregation;

import net.betvictor.core.dto.StatisticDto;
import net.betvictor.core.service.parser.ParagraphParser;
import net.betvictor.core.service.parser.WordParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AggregateServiceTest {
    @InjectMocks
    private AggregateService aggregateService;
    @Spy
    private FreqWordAggregate freqWordAggregate = new FreqWordAggregate();
    @Spy
    private ParagraphParser paragraphParser = new ParagraphParser();
    @Spy
    private WordParser wordParser = new WordParser();

    @Test
    void process() {
        StatisticDto result = aggregateService.process("<p>Hello world. Test.</p><p>Example text, test!</p>");
        assertEquals("test", result.getFreqWord());
        assertEquals(3L, result.getAvgParagraphSize());
        verify(freqWordAggregate).process(any(), eq(List.of("hello", "world", "test").toArray(String[]::new)));
        verify(freqWordAggregate).process(any(), eq(List.of("example", "text", "test").toArray(String[]::new)));
    }
}