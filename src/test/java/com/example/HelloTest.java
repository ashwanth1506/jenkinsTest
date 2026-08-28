package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloTest {
    @Test
    void testMessage() {
        assertEquals("Hello from Maven + Jenkins!", Hello.message());
    }
}
