import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * Created by Bas on 11-Nov-16.
 */
public class AutomatonViewer
{
    private Automaton _automaton;

    public AutomatonViewer(Automaton automaton)
    {
        _automaton = automaton;
    }

    public void PrintInAutFormat()
    {
        System.out.println("(START) |- " + _automaton.getStart());
        List<Edge> edges = _automaton.getEdges();
        for (Edge edge : edges)
            System.out.println(edge.getFrom() + " " + edge.getWeigth() + " " + edge.getTo());
        for (String endState: _automaton.getFinish())
            System.out.println(endState + " -| (FINAL)");
    }

    public void SaveToDot(String dotFile)
    {
        try
        {
            PrintWriter outFile = new PrintWriter(dotFile);
            /* Header */
            outFile.println("digraph Automaton {");
            outFile.println("rankdir = LR;");
            outFile.println("dpi=200;");
            outFile.println("fontsize =16;");
            outFile.println("concentrate=true");
            outFile.println("Start [shape=plaintext,label=\"\"];\nStart -> " + _automaton.getStart());

            /* Toevoegen van states */
            for (String state : _automaton.getStates())
            {
                if (_automaton.getFinish().contains(state))
                    outFile.println(state + " [shape=doublecircle,label=\""+ state + "\"];");
                else
                    outFile.println(state + " [shape=circle,label=\""+ state + "\"];");

            }

            
            /* Toevoegen van bogen */
            for (Edge edge : _automaton.getEdges())
//                if (!edge.getFrom().equals(edge.getTo()))
                    outFile.println(edge.getFrom() + " -> " + edge.getTo() + " [label=\"" + edge.getWeigth() + "\"]");

            /* Close file */
            outFile.println("}");
            outFile.close();
        } catch (FileNotFoundException e)
        {
            System.err.println("Error: file not found.\nFileName: '" + dotFile + "'");
        }
    }

    public void SavePs(String psFile)
    {
        SaveToDot("..\\AtomatonView-temp-SavePs.dot");
        try
        {
            String[] makePsFile = {"D:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe", "-Tps", "..\\AtomatonView-temp-SavePs.dot",  "-o", psFile};
            ProcessBuilder process = new ProcessBuilder(makePsFile);
            process.redirectErrorStream(true);
            Process p = process.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }

            Desktop.getDesktop().open(new File(psFile));
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("RuntimeException");
        }
    }
}
