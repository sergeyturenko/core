package net.betvictor.core.service.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//TODO This class can be move to common libs
public class StatisticModel {
    @JsonProperty("freq_word")
    private String freqWord;

    @JsonProperty("avg_paragraph_size")
    private int avgParagraphSize;

    @NotNull(message = "avg_paragraph_processing_time is mandatory")
    @JsonProperty("avg_paragraph_processing_time")
    private long avgParagraphProcessingTime;

    @NotNull(message = "total_processing_time is mandatory")
    @JsonProperty("total_processing_time")
    private long totalProcessingTime;
}

