package net.betvictor.core.service.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphParserTest {

    private ParagraphParser paragraphParser = new ParagraphParser();

    @Test
    void parse() {
        String[] result = paragraphParser.parse("<p>This is small text!</p><p>Do you agree?</p>");
        assertArrayEquals(
                List.of("This is small text!", "Do you agree?").toArray(), result);
    }
}