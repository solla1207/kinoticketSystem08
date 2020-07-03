import java.util.HashMap;
import java.util.Map;

public final class GeldBetrag
{
    private final int _euroAnteil;
    private final int _centAnteil;
    private static Map<String, GeldBetrag> _werteMenge = new HashMap<String, GeldBetrag>();

    private GeldBetrag(int euroAnteil, int centAnteil)
    {
        _euroAnteil = euroAnteil;
        _centAnteil = centAnteil;
    }

    private GeldBetrag(int centBetrag)
    {
        _centAnteil = centBetrag % 100;
        _euroAnteil = centBetrag / 100;
    }

    /**
     * @param euroAnteil
     * @param centAnteil
     * @return geldbetrag
     * @require istgueltig()
     */
    public static GeldBetrag select(int euroAnteil, int centAnteil)
    {
        String key = euroAnteil + "," + centAnteil;
        if (!_werteMenge.containsKey(key))
        {
            _werteMenge.put(key, new GeldBetrag(euroAnteil, centAnteil));
        }
        return _werteMenge.get(key);
    }

    public static GeldBetrag getGeldbetrag(int euroAnteil, int centAnteil)
    {
        assert istgueltig(euroAnteil,
                centAnteil) : "Vorbedingung verletzt: istgueltig()";
        return new GeldBetrag(euroAnteil, centAnteil);
    }

    public static boolean istgueltig(int euroAnetil, int centAnteil)
    {
        return (0 <= euroAnetil) && (euroAnetil <= Integer.MAX_VALUE)
                && (0 <= centAnteil) && (centAnteil < 100);
    }

    /**
     * @param betrag1
     * @param betrag2
     * @require istgueltigeEuroundCentanteil()==true
     */
    public static GeldBetrag addiere(GeldBetrag betrag1, GeldBetrag betrag2)
    {
        int euroAnteil = betrag1._euroAnteil + betrag2._euroAnteil;
        int centAnteil = betrag1._centAnteil + betrag2._centAnteil;
        if (centAnteil >= 100)
        {
            euroAnteil++;
            centAnteil = centAnteil - 100;
        }
        return new GeldBetrag(euroAnteil, centAnteil);
    }

    public GeldBetrag substrahiere(GeldBetrag betrag1, GeldBetrag betrag2)
    {
        if (istGroessereGleich(betrag1, betrag2))
        {
            int euroAnteil = betrag1.getEuroAnteil() - betrag2.getEuroAnteil();
            int centAnteil = betrag1.getCentAnteil() - betrag2.getCentAnteil();

            if (centAnteil < 0)
            {
                euroAnteil--;
                centAnteil += 100;
            }
            return new GeldBetrag(euroAnteil, centAnteil);
        }
        return substrahiere(betrag2, betrag1);

    }

    public GeldBetrag multipliziere(int faktor)
    {
        // int produkt = getCentBetrag() * faktor;

    }

    public boolean istGroessereGleich(GeldBetrag betrag1, GeldBetrag betrag2)
    {
        int euro1 = betrag1.getEuroAnteil();
        int euro2 = betrag2.getEuroAnteil();
        int cent1 = betrag1.getCentAnteil();
        int cent2 = betrag2.getCentAnteil();

        return euro1 >= euro2 || euro1 == euro2 && cent1 >= cent2;
    }

    public int getEuroAnteil()
    {
        return _euroAnteil;
    }

    public int getCentAnteil()
    {
        return _centAnteil;
    }

}
