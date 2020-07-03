package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class GeldBetragTest
{
    @Test
    public void testeCompareTo()
    {
        GeldBetrag geldbetrag1 = GeldBetrag.getGeldbetrag(8, 20);
    }

    // teste get- Methode
    @Test
    public void testeKonstruktoren()
    {
        GeldBetrag geldbetrag = GeldBetrag.getGeldbetrag(8, 20);
        assertEquals(8, geldbetrag.getEuroAnteil());
    }

    @Test
    public void testeistGroessereGleich()
    {

    }

    @Test
    public void istGeueltig()
    {
        assertFalse(GeldBetrag.istgueltig(-1, 120));

    }

    @Test
    public void testAddiere()
    {

    }

    @Test
    public void testSubstrahriere()
    {

    }

    @Test
    public void testMultipliziere()
    {

    }

}
