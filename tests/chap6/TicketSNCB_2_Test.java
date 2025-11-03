package chap6;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.Order;

public class TicketSNCB_2_Test {

    @Test
    @Order(1)
    public void calculerPrixTernaireTest() {
        assertEquals(0, TicketSNCB_2.calculerPrixTernaire(0), 0);
        assertEquals(0, TicketSNCB_2.calculerPrixTernaire(11), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixTernaire(12), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixTernaire(25), 0);
        assertEquals(25.8, TicketSNCB_2.calculerPrixTernaire(26), 0);
        assertEquals(25.8, TicketSNCB_2.calculerPrixTernaire(64), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixTernaire(65), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixTernaire(75), 0);
    }
    
    @Test
    @Order(2)
    public void calculerPrixCondtionsTest() {
        assertEquals(0, TicketSNCB_2.calculerPrixConditions(0), 0);
        assertEquals(0, TicketSNCB_2.calculerPrixConditions(11), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixConditions(12), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixConditions(25), 0);
        assertEquals(25.8, TicketSNCB_2.calculerPrixConditions(26), 0);
        assertEquals(25.8, TicketSNCB_2.calculerPrixConditions(64), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixConditions(65), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixConditions(75), 0);
    }

    @Test
    @Order(3)
    public void calculerPrixCondtionsReturn() {
        assertEquals(0, TicketSNCB_2.calculerPrixReturn(0), 0);
        assertEquals(0, TicketSNCB_2.calculerPrixReturn(11), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixReturn(12), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixReturn(25), 0);
        assertEquals(25.8, TicketSNCB_2.calculerPrixReturn(26), 0);
        assertEquals(25.8, TicketSNCB_2.calculerPrixReturn(64), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixReturn(65), 0);
        assertEquals(21.93, TicketSNCB_2.calculerPrixReturn(75), 0);
    }
}
