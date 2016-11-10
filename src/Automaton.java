import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bas on 10-Nov-16.
 */
public class Automaton
{
    private List<Edge> _edges;
    private String _start;
    private String _finish;

    public Automaton()
    {
        _edges = new ArrayList<>();
    }

    public Automaton(Automaton other)
    {
        _edges = new ArrayList<>(other._edges);
        _start = other._start;
        _finish = other._finish;
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
        Automaton result = new Automaton();
        result._start = concatNodes(_start, aut._start);
        result._finish = concatNodes(_finish, aut._finish);

        for (Edge edge1: _edges)
            for (Edge edge2 : aut._edges)
                if (edge1.weigth.equals(edge2.weigth))
                    result.addEdge(
                            concatNodes(edge1.from, edge2.from),
                            concatNodes(edge1.to, edge2.to),
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
     * @custom.post : string bevat geen $
     */
    public String getShortestExample(Boolean accept)
    {
        return null;
    }

    /**
     * Voegt een nieuwe node toe aan de automaat
     * @param from : De String node vanwaar de boog vertrekt
     * @param to : De string van de aankomst node
     * @param weight : De string die het karakter voorstelt
     */
    public void addEdge(String from, String to, String weight)
    {
        _edges.add(new Edge(from, to, weight));
    }

    public void setStart(String start)
    {
        _start = start;
    }

    public void setFinish(String finish)
    {
        _finish = finish;
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
            return "Edge{" + from + " --- " + weigth + " ---> " + _finish + '}';
        }
    }

    private String concatNodes(String x, String y)
    {
        return "(" + x + "," + y + ")";
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


