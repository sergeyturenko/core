package net.betvictor.core.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ParagraphParser {

    private final Pattern startParagraphPattern = Pattern.compile("<p>");
    private final Pattern endParagraphPattern = Pattern.compile("</p>");

    public String[] parse(String text) {
        return endParagraphPattern.split(
                startParagraphPattern.matcher(text)
                        .replaceAll(""));
    }
}
