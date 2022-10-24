package com.imcmib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EncoderTest {

    private static final Encoder ENCODER = new Encoder();

    private static final Map<String, Integer> DATA = new HashMap<>() {{
        put("A", 16777217);
        put("FRED", 251792692);
        put(" :^)", 79094888);
        put("foo", 124807030);
        put(" foo", 250662636);
        put("foot", 267939702);
        put("BIRD", 251930706);
        put("....", 15794160);
        put("^^^^", 252706800);
        put("Woot", 266956663);
        put("no", 53490482);
    }};

    @Test
    public void test_encoder_encode() {
        for (Map.Entry<String, Integer> entry : DATA.entrySet()) {
            Assertions.assertEquals(entry.getValue(), ENCODER.encode(entry.getKey()));
        }
    }

    @Test
    public void test_encoder_decode() {
        for (Map.Entry<String, Integer> entry : DATA.entrySet()) {
            Assertions.assertEquals(entry.getKey(), ENCODER.decode(entry.getValue()));
        }
    }
}
