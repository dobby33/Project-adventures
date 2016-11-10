import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bas on 10-Nov-16.
 */
public class Automaton
{
    // TODO: changed: numberofstates en shortest

    private List<Edge> _edges;
    private String _start;
    private String _finish;
    private Boolean _changed;
    private int _numberOfStates;

    private String _minCombinatie;
    private int _minNEdges;

    public Automaton()
    {
        _edges = new ArrayList<>();
        _numberOfStates = 0;
        Changed();
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
        result._start = concatStates(_start, aut._start);
        result._finish = concatStates(_finish, aut._finish);

        for (Edge edge1: _edges)
            for (Edge edge2 : aut._edges)
                if (edge1.weigth.equals(edge2.weigth))
                    result.addEdge(
                            concatStates(edge1.from, edge2.from),
                            concatStates(edge1.to, edge2.to),
                            edge1.weigth
                    );
        return result;
    }

    /**
     * Deze functie schrijft de korste string uit die de automaat wel of niet accepteerd
     *
     * @param accept : true: korste string die wordt geaccepteerd
     *               false: de korste string die niet wordt geaccepteerd
     * @return De gevonden string
     * @custom.pre : De automaat is correct opgebouwd
     * @custom.post : string bevat geen $
     */
    public String getShortestExample(Boolean accept)
    {
        if (accept)
        {
            _minNEdges = getNumberOfStates();
            _minCombinatie = null;
            shortestAccept(_start, "", 0);
            return _minCombinatie;
        }
        else
        {
            return null;
        }
    }

    /**
     * Bij de eerste aanroep is
     *      private String _minCombinatie = null
     *      private int _minNEdges = het aantal edges
     *
     *      nEdges = 0
     *      state = startstate
     *      combinatie = ""
     *
     *  * de combinaties staan in minCombinatie
     */
    private void shortestAccept(String state, String combinatie, int nEdges)
    {
        if (nEdges >= _minNEdges)
            return;
        if (state.equals(_finish))  // en nEdges is kleiner den _minEdges
        {
            _minCombinatie = combinatie;
            _minNEdges = nEdges;
            return;
        }

        for (Edge edge : _edges)
        {
            if (edge.from.equals(state) && "$".equals(edge.weigth))
                shortestAccept(edge.to, combinatie + edge.weigth, nEdges);
            else if (edge.from.equals(state))
                shortestAccept(edge.to, combinatie + edge.weigth, nEdges+1);

        }
    }

    /**
     * Voegt een nieuwe state toe aan de automaat
     * @param from : De String state vanwaar de boog vertrekt
     * @param to : De string van de aankomst state
     * @param weight : De string die het karakter voorstelt
     */
    public void addEdge(String from, String to, String weight)
    {
        Changed();
        _edges.add(new Edge(from, to, weight));
    }

    public void setStart(String start)
    {
        _start = start;
        Changed();
    }

    public void setFinish(String finish)
    {
        _finish = finish;
        Changed();
    }


    private void Changed()
    {
        _changed = true;
    }

    private class Edge
    {
        Edge(String from, String to, String weigth)
        {
            this.from = from;
            this.to = to;
            this.weigth = weigth;
        }

        String from;
        String to;
        String weigth;

        @Override
        public String toString()
        {
            return "Edge{" + from + "  \t--- " + weigth + " ---> " + _finish + '}';
        }
    }

    /***
     * Voegt twee states aan elkaar
     * @param x : een eerste state
     * @param y : een tweede state
     * @return : de combinatei van de states
     */
    private String concatStates(String x, String y)
    {
        return "(" + x + "," + y + ")";
    }

    private int getNumberOfStates()
    {
        if (_changed)
        {
            _numberOfStates = 0;
            for (Edge edge : _edges)
                _numberOfStates++;
        }
        _changed = false;
        return _numberOfStates;
    }


    @Override
    public String toString()
    {
        String result = "{\n";
        result += "Start: " + _start;
        result += "\nFinish: " + _finish;
        result += "\nEdges: ";
        for (Edge edge : _edges)
            result += "\n\t" + edge;
        result += "\n}";
        return result;
    }
}


