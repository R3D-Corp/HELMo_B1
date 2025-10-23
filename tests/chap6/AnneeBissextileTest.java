package chap6;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Order;

public class AnneeBissextileTest {
    @Test
    @Order(1)

    public void estBissextile() {
        assertTrue(AnneeBissextile.estBissextile(2024));
        assertTrue(AnneeBissextile.estBissextile(2000));
        assertTrue(AnneeBissextile.estBissextile(2004));
        assertFalse(AnneeBissextile.estBissextile(2100));
        assertFalse(AnneeBissextile.estBissextile(2200));
    }
}
