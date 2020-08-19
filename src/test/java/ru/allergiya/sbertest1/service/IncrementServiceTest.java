package ru.allergiya.sbertest1.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncrementServiceTest {
    private static final String IN_STRING_1 = "1, 5, 76, -3, 9";
    private static final String OUT_STRING_1 = "2, 6, 77, -2, 10";
    private static final String IN_STRING_2 = "45, 87, -34, -2, 765";
    private static final String OUT_STRING_2 = "48, 90, -31, 1, 768";
    private static final int DIFF = 3;

    @Test
    public void incrementTest() throws Exception{
        IncrementService service = new IncrementService();
        service.init();
        assertEquals(service.increment(IN_STRING_1), OUT_STRING_1);
    }

    @Test
    public void setDiffTest() throws Exception{
        IncrementService service = new IncrementService();
        service.init();
        service.setDiff(DIFF);
        assertEquals(service.increment(IN_STRING_2), OUT_STRING_2);
    }
}
