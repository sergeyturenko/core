package net.betvictor.core.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class WordParser {

    private final Pattern symbolsPattern = Pattern.compile("\\pP");
    private final Pattern splitPattern = Pattern.compile(" ");

    public String[] parse(String text) {
        return splitPattern.split(
                symbolsPattern.matcher(text)
                        .replaceAll("")
                        .toLowerCase()
        );
    }
}
