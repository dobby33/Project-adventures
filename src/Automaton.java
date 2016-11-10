import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bas on 10-Nov-16.
 */
public class Automaton
{
    private List<Edge> _edges;
    private int _start;
    private int _finish;

    public Automaton()
    {
        _edges = new ArrayList<>();
    }

    /**
     * geeft een eindige automaat terug waarvan de taal gelijk is aan de intersectie
     * van de taal L1 van de automaat van de klasse en taal L2 van de automaat aut.
     *
     * @param aut de andere automaat
     * @return De intersectie
     */
    public Automaton intersection(Automaton aut)
    {
        return null;
    }

    /**
     * Deze functie schrijft de korste string uit die de automaat wel of niet accepteerd
     *
     * @param accept : true: korste string die wordt geaccepteerd
     *               false: de korste string die niet wordt geaccepteerd
     * @return De gevonden string
     * @custom.post : string bevat geen $
     */
    public String getShortestExample(Boolean accept)
    {
        return null;
    }

    public void addEdge(int from, int to, String weight)
    {
        _edges.add(new Edge(from, to, weight));
    }

    public void setStart(int start)
    {
        _start = start;
    }

    public void setFinish(int finish)
    {
        _finish = finish;
    }

    private class Edge
    {
        Edge(int from, int to, String weigth)
        {
            this.from = from;
            this.to = to;
            this.weigth = weigth;
        }

        int from;
        int to;
        String weigth;
    }
}


