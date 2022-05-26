package net.betvictor.core.service;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public enum ParagraphLength {
    SHORT("short"),
    MEDIUM("medium"),
    LONG("long"),
    VERYLONG("verylong");

    private String code;

    ParagraphLength(String code) {
        this.code = code;
    }

    public static ParagraphLength getByCode(String code) {
        return Arrays.stream(ParagraphLength.values())
                .filter(v -> v.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal param: " + code + ". "
                        + Arrays.stream(ParagraphLength.values())
                        .map(ParagraphLength::getCode)
                        .collect(Collectors.joining(", "))));
    }

}
