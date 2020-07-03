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

    public void testeistGroessereGleich()
    {

    }

    public void istGeueltig()
    {
        assertFalse(GeldBetrag.istgueltig(-1, 120));

    }

    public void testAddiere()
    {

    }

    public void testSubstrahriere()
    {

    }

    public void testMultipliziere()
    {

    }

}
