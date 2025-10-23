package chap5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.Order;


public class ipTest {
    @Test(expected = IllegalArgumentException.class)
    @Order(1)
    public void convertirOctetLength() {
        AdresseIPEnBase10.convertirOctet("010010110");
    }
    @Test(expected = IllegalArgumentException.class)
    @Order(2)
    public void convertirOctetBinary() {
        AdresseIPEnBase10.convertirOctet("0120101100");
    }

    @Test(expected = IllegalArgumentException.class)
    @Order(3)
    public void convertirIpLength() {
        AdresseIPEnBase10.convertirIpToOctet("11000000.10101000.0000000");
    }
    
    @Test
    @Order(4)
    public void convertirIpTest() {
        assertEquals(4, AdresseIPEnBase10.convertirIpToOctet("11000000.10101000.00000001.00000101").length);
    }
}
