package net.betvictor.core.service.parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordParserTest {

    private WordParser wordParser = new WordParser();

    @Test
    void parse() {
        String[] result = wordParser.parse("This is small text! Do you agree?");
        assertArrayEquals(List.of("this", "is", "small", "text", "do", "you", "agree").toArray(), result);
    }
}