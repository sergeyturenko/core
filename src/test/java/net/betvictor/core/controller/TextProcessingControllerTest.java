package net.betvictor.core.controller;

import net.betvictor.core.config.AppConfig;
import net.betvictor.core.dto.StatisticDto;
import net.betvictor.core.mapper.StatisticMapping;
import net.betvictor.core.service.ParagraphLength;
import net.betvictor.core.service.TextProcessService;
import net.betvictor.core.service.kafka.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TextProcessingController.class)
@Import({AppConfig.class})
class TextProcessingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TextProcessService textProcessService;
    @MockBean
    private StatisticMapping statisticMapping;
    @MockBean
    private KafkaProducer kafkaProducer;

    @Test
    void processSuccess() throws Exception {
        StatisticDto statisticDto =
                StatisticDto.builder()
                        .freqWord("test")
                        .avgParagraphSize(123)
                        .avgParagraphProcessingTime(123L)
                        .totalProcessingTime(555L)
                        .build();
        when(textProcessService.processText(1, ParagraphLength.SHORT))
                .thenReturn(statisticDto);
        mockMvc.perform(get("/betvictor/text?p=1&l=short"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.freq_word")
                        .value(statisticDto.getFreqWord()))
                .andExpect(jsonPath("$.avg_paragraph_size")
                        .value(statisticDto.getAvgParagraphSize()))
                .andExpect(jsonPath("$.avg_paragraph_processing_time")
                        .value(statisticDto.getAvgParagraphProcessingTime()))
                .andExpect(jsonPath("$.total_processing_time")
                        .value(statisticDto.getTotalProcessingTime()));
    }


    @Test
    void processWrongWithMissParam() throws Exception {
        mockMvc.perform(get("/betvictor/text?p=1"))
                .andExpect(status().isBadRequest());
    }
}