package com.imcmib;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncoderTest {

    private static final Encoder ENCODER = new Encoder();

    private static final Map<String, Integer[]> DATA = new HashMap<>() {{
        put("A", new Integer[]{16777217});
        put("FRED", new Integer[]{251792692});
        put(" :^)", new Integer[]{79094888});
        put("foo", new Integer[]{124807030});
        put(" foo", new Integer[]{250662636});
        put("foot", new Integer[]{267939702});
        put("BIRD", new Integer[]{251930706});
        put("....", new Integer[]{15794160});
        put("^^^^", new Integer[]{252706800});
        put("Woot", new Integer[]{266956663});
        put("no", new Integer[]{53490482});
        put("tacocat", new Integer[]{267487694, 125043731});
        put("never odd or even", new Integer[]{267657050, 233917524, 234374596, 250875466, 17830160});
        put("lager, sir, is regal", new Integer[]{267394382, 167322264, 66212897, 200937635, 267422503});
        put(
                "go hang a salami, I'm a lasagna hog",
                new Integer[]{
                        200319795, 133178981, 234094669, 267441422, 78666124, 99619077, 267653454, 133178165, 124794470
                }
        );
        put(
                "egad, a base tone denotes a bad age",
                new Integer[]{
                        267389735, 82841860, 267651166, 250793668, 233835785, 267665210, 99680277, 133170194, 124782119
                }
        );
    }};

    @Test
    public void test() {
        String string = "test string!";
        assertEquals(string, ENCODER.decode(ENCODER.encode(string)));
    }

    @Test
    public void test_encoder_encode() {
        for (Map.Entry<String, Integer[]> entry : DATA.entrySet()) {
            assertArrayEquals(toIntArray(entry.getValue()), ENCODER.encode(entry.getKey()));
        }
    }

    @Test
    public void test_encoder_decode() {
        for (Map.Entry<String, Integer[]> entry : DATA.entrySet()) {
            assertEquals(entry.getKey(), ENCODER.decode(toIntArray(entry.getValue())));
        }
    }

    private int[] toIntArray(Integer[] integers) {
        int[] ret = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            ret[i] = integers[i];
        }
        return ret;
    }
}
