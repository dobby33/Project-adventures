/**
 * Created by Bas on 11-Nov-16.
 */
public class Edge
{
    private String _from;
    private String _to;
    private String _weigth;


    public Edge(String from, String to, String weigth)
    {
        this._from = from;
        this._to = to;
        this._weigth = weigth;
    }

    public String getFrom()
    {
        return _from;
    }

    public void setFrom(String from)
    {
        _from = from;
    }

    public String getTo()
    {
        return _to;
    }

    public void setTo(String to)
    {
        _to = to;
    }

    public String getWeigth()
    {
        return _weigth;
    }

    public void setWeigth(String weigth)
    {
        _weigth = weigth;
    }

    @Override
    public String toString()
    {
        return _from + " -> " + _to + " [label=\"" + _weigth + "\"];";
    }
}

