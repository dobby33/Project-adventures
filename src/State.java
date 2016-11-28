import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bas on 25-Nov-16.
 */
public class State
{
    private List<Edge> _edgesStartingFromHere;
    private String _label;

    public State(String label)
    {
        _label = label;
        _edgesStartingFromHere = new ArrayList<>();
    }

    public List<Edge> getEdgesStartingFromHere()
    {
        return _edgesStartingFromHere;
    }

    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String label)
    {
        _label = label;
    }
}
