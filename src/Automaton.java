import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Bas on 10-Nov-16.
 */
public class Automaton
{
    /**
     * De lijst met alle overgangen tussen twee states
     */
    private List<Edge> _edges;
    /**
     * De start state
     */
    private String _start;
    /**
     * Een verzameling met eind states
     */
    private HashSet<String> _finish;
    /**
     * Een verzameling van alle states
     */
    private HashSet<String> _states;

    /* Dit zijn variabele voor het zoeken van het korste pad */
    private String _minCombinatie;
    private int _minNEdges;
    private int _result;

    public Automaton()
    {
        _edges = new ArrayList<>();
        _finish = new HashSet<>();
        _states = new HashSet<>();
    }

    /**
     * geeft een eindige automaat terug waarvan de taal gelijk is aan de intersectie
     * van de taal L1 van de automaat van de klasse en taal L2 van de automaat aut.
     *
     * @param aut de andere automaat
     * @return De nieuwe intersectie automaat
     */
    public Automaton intersection(Automaton aut)
    {
        Automaton result = new Automaton();
        // de start state zetten
        result._start = concatStates(_start, aut._start);
        // De finish states combineren
        for (String state1 : _finish)
            for (String state2 : aut._finish)
                result.addFinish(concatStates(state1, state2));

        // De edges combineren
        for (Edge edge1 : _edges)
            for (Edge edge2 : aut._edges)
                if (edge1.getWeigth().equals(edge2.getWeigth()))
                    result.addEdge(concatStates(edge1.getFrom(), edge2.getFrom()),
                                   concatStates(edge1.getTo(), edge2.getTo()),
                                   edge1.getWeigth());
        return result;
    }

    /**
     * Deze functie schrijft de korste string uit die de automaat wel of niet accepteerd
     *
     * @param accept : true: korste string die wordt geaccepteerd
     *               false: de korste string die niet wordt geaccepteerd
     * @return De korst string, of null indien deze niet bestaat.
     * @pre : De automaat is correct opgebouwd
     * @post : string bevat geen $
     */
    public String getShortestExample(Boolean accept)
    {
        if (accept) {
            shortestAccept();
            return _minCombinatie;
        } else {
            shortestNotAccept();
            return _minCombinatie;
        }
    }

    /**
     * Deze functie moet opgeroepen worden wanneer men het kortste pad wil vinden in de automaat
     * dat niet wordt geaccepteerd.
     *
     * Het resultaat wordt opgeslagen in {@link Automaton#_minCombinatie}
     * Het aantal adges van dit resulaat staat in {@link Automaton#_minNEdges}
     */
    private void shortestNotAccept()
    {
        _minNEdges = getNumberOfStates();   // We willen geen pad dat langer is dan het aantal states
        _minCombinatie = null;  // Als er geen korste pad wordt gevonden wordt er null terug gegeven
        shortestNotAccept(_start, "", 0);
    }

    /**
     * Deze functie zoekt door middle van recursie het kortste pad in de automaat dat niet wordt geaccepteerd. De eerste keer moet men deze functie aanroepen zonder paramters
     * @param state : de huidige state
     * @param combinatie : Het pad dat we tot nu toe hebben
     * @param nEdges : het aantal egdes dat we reeds zij gepasseerd
     *
     * Het resultaat wordt opgeslagen in {@link Automaton#_minCombinatie}
     * Het aantal adges van dit resulaat staat in {@link Automaton#_minNEdges}
     */
    private void shortestNotAccept(String state, String combinatie, int nEdges)
    {
        if (nEdges >= _minNEdges) // Als we al een korter pad hebben gevonden
            return;
        if (!_finish.contains(state))  // Als er een kleinere combinatie is gevonden
        {
            // Deze string wordt niet geaccepteerd
            _minNEdges = nEdges;
            _minCombinatie = combinatie;
            return;
        }

        // Als het wel een accept state is
        // We gaan alle nieuwe edges af
        for (Edge edge : _edges) {
            if (edge.getFrom().equals(state) && "$".equals(edge.getWeigth()))   // als het een lege string is
                shortestNotAccept(edge.getTo(), combinatie, nEdges + 1);
            else if (edge.getFrom().equals(state))
                shortestNotAccept(edge.getTo(), combinatie + edge.getWeigth(), nEdges + 1);
        }

    }

    /**
     * Bij de eerste aanroep is
     * private String _minCombinatie = null
     * private int _minNEdges = het aantal edges
     * <p>
     * nEdges = 0
     * state = startstate
     * combinatie = ""
     * <p>
     * * de combinaties staan in minCombinatie
     */
    private void shortestAccept()
    {
        _minNEdges = getNumberOfStates();  // We zeggen dat het te zoeken pad kleiner moet zijn dan het aantal states
        _minCombinatie = null;  // Als er geen combinatie wordt gevonden zal er dus null in het resultaat staan
        shortestAccept(_start, "", 0);
    }

    /**
     * Hierop wordt de recursie gedaan, eerste keer functie zonder parameters aanroepen
     *
     * @param state      : Het label van de huidige state
     * @param combinatie : De labels van de edges achterelkaar die we tot nu toe hebben
     * @param nEdges : De hoeveelheid edges die we reeds hebben gedaan.
     *
     * Het resultaat wordt opgeslagen in {@link Automaton#_minCombinatie}
     * Het aantal adges van dit resulaat staat in {@link Automaton#_minNEdges}
     */
    private void shortestAccept(String state, String combinatie, int nEdges)
    {
        if (nEdges >= _minNEdges)  // Als we al een combinatie hebben die korter is dan deze combinatie
            return;
        if (_finish.contains(state)) // Als er een kleinere combinatie is gevonden
        { // State is een accept state
            _minCombinatie = combinatie;
            _minNEdges = nEdges;
            return;
        }

        // 'state' is nog geen accept state
        // We moeten dus nog edges toevoegen
        for (Edge edge : _edges) {
            if (edge.getFrom().equals(state) && "$".equals(edge.getWeigth()))   // als het een lege string is
                shortestAccept(edge.getTo(), combinatie, nEdges + 1);
            else if (edge.getFrom().equals(state))
                shortestAccept(edge.getTo(), combinatie + edge.getWeigth(), nEdges + 1);
        }
    }

    public List<Edge> getEdges()
    {
        return _edges;
    }

    public String getStart()
    {
        return _start;
    }

    public void setStart(String start)
    {
        _start = start;
        _states.add(start);
    }

    public HashSet<String> getFinish()
    {
        return _finish;
    }

    public HashSet<String> getStates()
    {
        return _states;
    }

    /**
     * Voegt een nieuwe overgang toe aan de automaat.
     *
     * @param from   : De String state vanwaar de boog vertrekt
     * @param to     : De string van de aankomst state
     * @param weight : De string die het karakter voorstelt
     */
    public void addEdge(String from, String to, String weight)
    {
        _edges.add(new Edge(from, to, weight));
        _states.add(from);      // gaat 'from' enkel toevoegen als 'from' er nog niet in zit
        _states.add(to);        // hetzelfde voor 'to'
    }

    /**
     * Deze functie voegt een nieuwe eindstate toe.
     *
     * @param finish : de naam van de eindtoestand.
     */
    public void addFinish(String finish)
    {
        _finish.add(finish);
        _states.add(finish);
    }


    /***
     * Voegt twee states aan elkaar
     * @param x : een eerste state
     * @param y : een tweede state
     * @return : de combinatei van de states
     */
    private String concatStates(String x, String y)
    {
        return "<" + x + "," + y + ">";
    }

    private int getNumberOfStates()
    {
        return _states.size();
    }


    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("Automaat:\n");
        /* start */
        result.append("\t").append("Start:\n");
        result.append("\t|\t").append(_start).append("\n");
        /* edges */
        result.append("\t").append("Edges:\n");
        for (Edge edge: _edges)
            result.append("\t|\t").append(edge).append("\n");
        /* states */
        result.append("\t").append("States:\n");
        for (String state: _states)
            result.append("\t|\t").append(state).append("\n");
        /* finish states */
        result.append("\t").append("Finish states:\n");
        for (String state: _finish)
            result.append("\t|\t").append(state).append("\n");

        return result.toString();
    }
}


