package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Wurmdobler,Schlueschen,Lee,Wiltberg
 * @version 05.07.2020
 *
 */

public class GeldbetragTest
{

    @Test
    public void testAddiere()
    {
        Geldbetrag betrag1 = Geldbetrag.select(50, 40);
        Geldbetrag betrag2 = Geldbetrag.select(10, 65);
        Geldbetrag summebetrag = Geldbetrag.select(61, 5);

        assertEquals(summebetrag, betrag1.subtrahiere(betrag1, betrag2));
    }

    @Test
    public void testSubstrahriere()
    {
        Geldbetrag betrag1 = Geldbetrag.select(50, 40);
        Geldbetrag betrag2 = Geldbetrag.select(10, 55);
        Geldbetrag differenzbetrag = Geldbetrag.select(39, 85);

        assertEquals(differenzbetrag, betrag1.subtrahiere(betrag1, betrag2));
    }

    @Test
    public void testMultipliziere()
    {
        int faktor = 2;
        Geldbetrag betrag = Geldbetrag.select(50, 60);
        Geldbetrag produktbetrag = Geldbetrag.select(101, 20);

        assertEquals(produktbetrag, betrag.multipliziere(betrag, faktor));

    }

    @Test
    public void testAddierenMoegich()
    {
        int grenzwert = Integer.MAX_VALUE;

        Geldbetrag betrag1 = Geldbetrag.select(grenzwert, 99);
        Geldbetrag betrag2 = Geldbetrag.select(grenzwert, 98);
        Geldbetrag betrag3 = Geldbetrag.select(00, 1);

        assertFalse(betrag1.istAddierenMoeglich(betrag1, betrag3));
        assertTrue(betrag2.istAddierenMoeglich(betrag2, betrag3));

    }

    @Test
    public void testIstMultiplizierenMoeglich()
    {
        int zahl1 = Integer.MAX_VALUE / 2;
        int zahl2 = zahl1 * 2;

        Geldbetrag betrag1 = Geldbetrag.select(zahl2, 00);

        assertFalse(betrag1.istMultiplizirenMoeglich(betrag1, 0));
        assertTrue(betrag1.istMultiplizirenMoeglich(betrag1, 1));
        assertFalse(betrag1.istMultiplizirenMoeglich(betrag1, 3));

    }

    @Test
    public void testeIstGroessereGleich()
    {
        Geldbetrag betrag1 = Geldbetrag.select(2, 0);
        Geldbetrag betrag2 = Geldbetrag.select(1, 0);

        //        betrag1 soll groesser als betrag2 sein
        assertTrue(betrag1.istGroesserGleich(betrag1, betrag2));
        //      betrag1 soll kleiner als betrag2 sein
        assertFalse(betrag1.istGroesserGleich(betrag2, betrag1));

        assertTrue(betrag1.istGroesserGleich(betrag1, betrag1));
    }

    @Test
    public void testIstGueltigerCentAnteil()
    {
        Geldbetrag betrag = null;
        assertFalse(betrag.istGueltigerCentAnteil(-1));
        assertTrue(betrag.istGueltigerCentAnteil(0));
        assertTrue(betrag.istGueltigerCentAnteil(99));
        assertFalse(betrag.istGueltigerCentAnteil(100));
    }

    @Test
    public void testIstGueltigerEuroAnteil()
    {
        Geldbetrag betrag = null;
        assertFalse(betrag.istGueltigerEuroAnteil(-1));
        assertTrue(betrag.istGueltigerEuroAnteil(0));
        assertTrue(betrag.istGueltigerEuroAnteil(Integer.MAX_VALUE));
        assertFalse(betrag.istGueltigerEuroAnteil(Integer.MAX_VALUE + 1));

    }

    @Test
    public void testGetFormatierenGeldbetrag()
    {
        Geldbetrag betrag = Geldbetrag.select(50, 50);
        String text = "50,50";

        assertEquals(text, betrag.getFormatiertenGeldbetrag());

    }

}
