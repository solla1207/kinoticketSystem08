package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wurmdobler,Schlueschen,Lee,Wiltberg
 * @version 05.07.2020
 *
 */
public final class Geldbetrag
{

    private final int _euroAnteil;
    private final int _centAnteil;
    public static Map<String, Geldbetrag> _werteMenge = new HashMap<String, Geldbetrag>();

    /**
     * Wählt einen Geldbetrag aus.
     * 
     * @param euroAnteil undcentAnteil der Betrag
     * 
     * @require euroAnteil >= 0;
     * @require centAnteil >= 0;
     */
    private Geldbetrag(int euroAnteil, int centAnteil)
    {
        assert euroAnteil >= 0 : "Vorbedingung verletzt: euroAnteil >= 0";
        assert centAnteil >= 0 : "Vorbedingung verletzt: centAnteil >= 0";
        _euroAnteil = euroAnteil;
        _centAnteil = centAnteil;
    }

    /**
     * Wählt einen Geldbetrag aus.
     * 
     * @param eurocent Der Betrag in ganzen cent.
     * 
     * @require centAnteil >= 0;
     */
    private Geldbetrag(int centBetrag)
    {
        assert centBetrag >= 0 : "Vorbedingung verletzt: centBetrag >= 0";
        _centAnteil = centBetrag % 100;
        _euroAnteil = centBetrag / 100;
    }

    /**
     * Ruft den Geldbetrag ab, der mit den angegebenen Parametern übereinstimmt
     * @param euroAnteil
     * @param centAnteil
     * @return Geldbetrag, der mit den angegebenen Parametern übereinstimmt
     * 
     * @require euroAnteil >=0
     * @require centAnteil >=0
     */
    public static Geldbetrag select(int euroAnteil, int centAnteil)
    {
        assert euroAnteil >= 0 : "Vorbedingung verletzt: euroAnteil >=0";
        assert centAnteil >= 0 : "Vorbedingung verletzt: centAnteil >=0";

        String key = euroAnteil + "," + centAnteil;
        if (!_werteMenge.containsKey(key))
        {
            _werteMenge.put(key, new Geldbetrag(euroAnteil, centAnteil));
        }
        return _werteMenge.get(key);
    }

    /**
     * Ruft den Geldbetrag ab, der mit den angegebenen Parametern übereinstimmt
     * @param euroAnteil
     * @param centAnteil
     * @return Geldbetrag, der mit den angegebenen Parametern übereinstimmt
     * 
     * @require euroAnteil != null
     * @require centAnteil != null
     */
    public static Geldbetrag select(String euroAnteil, String centAnteil)
    {
        assert euroAnteil != null : "Vorbedingung verletzt: centAnteil ist null";
        assert centAnteil != null : "Vorbedingung verletzt: centAnteil ist null";

        String key = euroAnteil + "," + centAnteil;
        if (!_werteMenge.containsKey(key))
        {
            _werteMenge.put(key, new Geldbetrag(Integer.parseInt(euroAnteil),
                    Integer.parseInt(centAnteil)));
        }
        return _werteMenge.get(key);

    }

    /**
     * Addiert die uebergebene Parameters
     * @param summand1
     * @param summand2
     * @return das Ergebnis der Addieren als Geldbetrag
     */
    public Geldbetrag addiere(Geldbetrag summand1, Geldbetrag summand2)
    {
        assert istAddierenMoeglich(summand1,
                summand2) : "Vorbedingung verletzt: istAddierenMoeglich(summand1,summand2)";
        int summe = summand1.getCentBetrag() + summand2.getCentBetrag();
        return select(summe / 100, summe % 100);

    }

    /**
     * Prueft ob das Ergebnis der Addieren gueltig ist
     * @param summand1
     * @param summand2
     * @return true, falls euroBetrag des Ergebnis gueltig ist, false sonst
     * 
     * @require summand1 !=null
     * @require summand2 !=null
     */
    public boolean istAddierenMoeglich(Geldbetrag summand1, Geldbetrag summand2)
    {
        assert summand1 != null : "Vorbedingung verletzt: summand1 !=null";
        assert summand2 != null : "Vorbedingung verletzt: summand2 ist null";

        long euroBetrag = (long) summand1.getEuroAnteil()
                + (long) summand2.getEuroAnteil();
        int centBetrag = summand1.getCentAnteil() + summand2.getCentAnteil();
        if (centBetrag > 100)
        {
            euroBetrag++;
        }
        return euroBetrag <= Integer.MAX_VALUE;
    }

    /**
     * substrahiert betrag1 und betrag2
     * @param betrag1
     * @param betrag2
     * @return Das Ergebnis der Substrahierung
     */
    public Geldbetrag subtrahiere(Geldbetrag betrag1, Geldbetrag betrag2)
    {
        if (istGroesserGleich(betrag1, betrag2))
        {
            int euroAnteil = betrag1.getEuroAnteil() - betrag2.getEuroAnteil();
            int centAnteil = betrag1.getCentAnteil() - betrag2.getCentAnteil();

            if (centAnteil < 0)
            {
                euroAnteil--;
                centAnteil += 100;
            }
            return select(euroAnteil, centAnteil);
        }
        return subtrahiere(betrag2, betrag1);
    }

    /**
     * multipliziert übergebene Betrag und Faktor
     * @param betrag
     * @param faktor
     * @return Das Ergebnist der Berechnung als Typ Geldbetrag
     * 
     * @require istMultiplizirenMoeglich(betrag,
                faktor) ==true
     */
    public Geldbetrag multipliziere(Geldbetrag betrag, int faktor)
    {
        assert istMultiplizirenMoeglich(betrag,
                faktor) : "Vorbedingung verletzt: istMultiplizirenMoeglich(betrag,faktor)";
        int produkt = betrag.getCentBetrag() * faktor;

        return select(produkt / 100, produkt % 100);
    }

    /**
     * prüft, ob das Ergebnis der Berechnungs weniger als Maximal Wert der Integer ist.
     * @param betrag
     * @param faktor
     * @return true, falls das Ergebnis guieltig ist, false sonst.
     * 
     * @require betrag !=null
     * @require faktor >=0
     */
    public boolean istMultiplizirenMoeglich(Geldbetrag betrag, int faktor)
    {
        assert betrag != null : "Vorbedingung verletzt: betrag ist null";
        assert faktor > 0 : "Vorbedingung verletzt: faktor >0";

        long euroBetrag = (long) betrag.getEuroAnteil() * faktor;
        long centBetrag = (long) betrag.getCentAnteil() * faktor;

        return euroBetrag + centBetrag <= Integer.MAX_VALUE;
    }

    /**
     * prueft ob betrag1 groesser als betrag2 ist
     * @param betrag1,betrag2
     * @return true, falls betrag1 groesser als betrag2 ist, false sonst
     * 
     * @require betrag1 !=null
     * @require betreag2 != null
     */
    public boolean istGroesserGleich(Geldbetrag betrag1, Geldbetrag betrag2)
    {
        assert betrag1 != null : "Vorbedingung verletzt: betrag1 ist null";
        assert betrag2 != null : "Vorbedingung verletzt: betrag2 ist null";

        int euro1 = betrag1.getEuroAnteil();
        int euro2 = betrag2.getEuroAnteil();
        int cent1 = betrag1.getCentAnteil();
        int cent2 = betrag2.getCentAnteil();

        return euro1 >= euro2 || euro1 == euro2 && cent1 >= cent2;
    }

    /**
     * Prueft ob CentAnteil gueltig ist
     * @param centAnteil
     * @return true, falls CentAnteil gueltig ist, false sonst.
     * 
     * @require centAnteil >= 0
     */
    public boolean istGueltigerCentAnteil(int centAnteil)
    {
        assert centAnteil >= 0 : "Vorbedingung verletzt: centAnteil >=0";
        return 0 <= centAnteil && centAnteil < 100;

    }

    /**
     * Prueft ob EuroAnteil gueltig ist
     * @param euroAnteil
     * @return true, falls EuroAnteil gueltig ist, false sonst.
     * 
     * @require euroAnteil >= 0
     */
    public boolean istGueltigerEuroAnteil(int euroAnteil)
    {
        assert euroAnteil >= 0 : "Vorbedingung verletzt: euroAnteil >=0";
        return euroAnteil >= 0 && euroAnteil < Integer.MAX_VALUE;
    }

    /**
     * Prueft ob beide EuroAnteil und CentAnteil gueltig sind
     * @param euroAnteil
     * @param centAnteil
     * @return true, falls beide EuroAnteil und CentAnteil gueiltig sind, false sonst.
     * 
     * @require euroAnteil >= 0
     * @require centAnteil >= 0
     */
    public boolean istGueltigerbetrag(int euroAnteil, int centAnteil)
    {
        return (0 <= euroAnteil) && (euroAnteil <= Integer.MAX_VALUE)
                && (0 <= centAnteil) && (centAnteil < 100);
    }

    /**
     * Gibt den Euroanteil ohne Cent zurück.
     * 
     * @return Den Euroanteil ohne Cent.
     */
    public int getEuroAnteil()
    {
        return _euroAnteil;

    }

    /**
     * Gibt den centAnteil der Eurocent.
     */
    public int getCentAnteil()
    {
        return _centAnteil;
    }

    /**
     * Gibt den Centbetrag ohne Eurobetrag zurück.
     */
    public int getCentBetrag()
    {
        return _euroAnteil * 100 + _centAnteil;
    }

    /**
     * Liefert einen formatierten String des Geldbetrags in der Form "10,23"
     * zurück.
     * 
     * @return eine String-Repräsentation.
     */
    public String getFormatiertenGeldbetrag()
    {
        return _euroAnteil + "," + _centAnteil;

    }

}
