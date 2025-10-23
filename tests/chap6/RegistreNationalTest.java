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
    @Order(3)
    public void estInvalide_1() {
        assertTrue(RegistreNational.isValid("36.10.21-171.99"));
    }

    @Test(expected = IllegalArgumentException.class)
    @Order(4)
    public void estInvalide_2() {
        assertTrue(RegistreNational.isValid("07.07.19-171.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    @Order(5)
    public void estInvalide_3() {
        assertTrue(RegistreNational.isValid("07.07.19-171.54.33"));
    }

    @Test(expected = IllegalArgumentException.class)
    @Order(6)
    public void estInvalide_4() {
        assertTrue(RegistreNational.isValid("07.07.19-171"));
    }

}
