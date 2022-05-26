package net.betvictor.core.restclient;

import feign.Response;
import lombok.RequiredArgsConstructor;
import net.betvictor.core.exception.ServiceUnavailableException;
import net.betvictor.core.service.ParagraphLength;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoripsumService {

    private final LoripsumClient loripsumClient;

    public String processParser(Integer paragraphNumber, ParagraphLength paragraphLength) {
        Response response = loripsumClient.getTextResponse(paragraphNumber, paragraphLength.getCode());
        try (Reader br = response.body().asReader(StandardCharsets.UTF_8)) {
            BufferedReader bufferedReader = new BufferedReader(br);
            return bufferedReader.lines()
                    .collect(Collectors.joining());
        } catch (IOException e) {
            throw new ServiceUnavailableException("Reader exception", e);
        }
    }
}
