package chap6;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.*;


public class RegistreNationalTest {
    @Test
    @Order(1)
    public void estNumeroValide_Post2000() {
        assertTrue(RegistreNational.isValid("36.10.21-171.54"));
        assertTrue(RegistreNational.isValid("07.07.19-171.66"));
    }

    @Test
    @Order(2)
    public void estNumeroInvalide_Pre2000() {
        assertFalse(RegistreNational.isValid("05.05.05-123.63"));
    }

    @Test(expected = IllegalArgumentException.class)
    @Order(1)
    public void estInvalide() {
        assertTrue(RegistreNational.isValid("36.10.21-171.99"));
        assertTrue(RegistreNational.isValid("07.07.19-171.00"));
    }

}
