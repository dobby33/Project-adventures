import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Bas on 10-Nov-16.
 */
public class Automaton
{
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
    private HashMap<String, State> _states;

    /* Dit zijn variabele voor het zoeken van het korste pad */
    private String _minCombinatie;
    private int _minNEdges;
    public int _counter;

    public Automaton()
    {
        _finish = new HashSet<>();
        _states = new HashMap<>();
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
        for (Edge edge1 : getEdges())
            for (Edge edge2 : aut.getEdges())
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
        _minNEdges = getNumberOfStates();  // We zeggen dat het te zoeken pad kleiner moet zijn dan het aantal states
        _minCombinatie = null;  // Als er geen combinatie wordt gevonden zal er dus null in het resultaat staan
        shortestExample(_start, "", 0, new HashSet<>(), accept);
        return _minCombinatie;
    }


    /**
     * Hierop wordt de recursie gedaan, eerste keer functie zonder parameters aanroepen
     * @param state      : Het label van de huidige state
     * @param combinatie : De labels van de edges achterelkaar die we tot nu toe hebben
     * @param nEdges : De hoeveelheid edges die we reeds hebben gedaan.
    *
    * Het resultaat wordt opgeslagen in {@link Automaton#_minCombinatie}
    * Het aantal adges van dit resulaat staat in {@link Automaton#_minNEdges}
     * @param doneStates : De states die we al zijn gepasseerd
     * @param accept : als de de string moet geaccepteerd worden ja of nee
     */
    private void shortestExample(String state, String combinatie, int nEdges, HashSet<String> doneStates,
                                 boolean accept)
    {
        _counter++;
        if (nEdges >= _minNEdges)  // Als we al een combinatie hebben die korter is dan deze combinatie
            return;

        if ((accept && _finish.contains(state)) || (!accept && !_finish.contains(state))) // Als er een kleinere combinatie is gevonden
        { // State is een accept state
            _minCombinatie = String.join("", combinatie);
            _minNEdges = nEdges;
            return;
        }

        if (doneStates.contains(state))         // Als we de state al hebben gehad kunnen we stoppen
            return;

        doneStates.add(state);  // Voeg toe aan de states die we al zijn gepasseerd
        // 'state' is nog geen accept state
        // We moeten dus nog edges toevoegen
        for (Edge edge : _states.get(state).getEdgesStartingFromHere()) {
            if (edge.getFrom().equals(state) && "$".equals(edge.getWeigth()))   // als het een lege string is
                shortestExample(edge.getTo(), combinatie, nEdges + 1, doneStates, accept);
            else if (edge.getFrom().equals(state))
                shortestExample(edge.getTo(), combinatie + edge.getWeigth(), nEdges + 1, doneStates, accept);
        }

        doneStates.remove(state);   // We gaan een stap terug dus de state moet er terug uit
    }


    /**
     * Dit is een zeer dure operatie! Linear aan het aantal states in de automaat
     * @return al de edges uit de graaf
     */
    public List<Edge> getEdges()
    {
        List<Edge> result = new ArrayList<>();
        for (State state: _states.values())
            result.addAll(state.getEdgesStartingFromHere());
        return result;
    }

    public String getStart()
    {
        return _start;
    }

    public void setStart(String start)
    {
        _start = start;
        addState(start);
    }

    public HashSet<String> getFinish()
    {
        return _finish;
    }

    public HashMap<String, State> getStates()
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
        addEdgeToState(from, new Edge(from, to, weight));      // gaat 'from' enkel toevoegen als 'from' er nog niet in zit
        addState(to);      // hetzelfde voor 'to'
    }

    /**
     * Deze functie voegt een nieuwe eindstate toe.
     *
     * @param finish : de naam van de eindtoestand.
     */
    public void addFinish(String finish)
    {
        addState(finish);
        _finish.add(finish);
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


    /**
     * Deze functie gaat een state toevoegen aan _states, als deze nog niet bestaat
     * @param state : het label van de state
     */
    private void addState(String state)
    {
        if (!_states.containsKey(state))
            _states.put(state, new State(state));
    }

    /**
     * Functie voegt een state toe als deze nog niet bestaat, en gaat dan de edge aan deze state toevoegen
     * @param state : de state
     * @param edge : een edge dat begin in state
     */
    private void addEdgeToState(String state, Edge edge)
    {
        addState(state);
        _states.get(state).getEdgesStartingFromHere().add(edge);
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
        for (Edge edge: getEdges())
            result.append("\t|\t").append(edge).append("\n");
        /* states */
        result.append("\t").append("States:\n");
        for (State state: _states.values())
            result.append("\t|\t").append(state.getLabel()).append("\n");
        /* finish states */
        result.append("\t").append("Finish states:\n");
        for (String state: _finish)
            result.append("\t|\t").append(state).append("\n");

        return result.toString();
    }
}


