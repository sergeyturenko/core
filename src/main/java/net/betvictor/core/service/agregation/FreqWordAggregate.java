package net.betvictor.core.service.agregation;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FreqWordAggregate {
    public void process(@NonNull Map<String, Long> freqWordMap, String[] paragraph) {
        Arrays.stream(paragraph)
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                .forEach((key, value) -> freqWordMap.merge(key, value, Long::sum));
    }
}
