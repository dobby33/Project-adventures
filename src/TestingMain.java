/**
 * Created by Bas on 11-Nov-16.
 */
public class TestingMain
{
    public static void main(String[] agrs)
    {
        ShowDetailts(LoadInAutomaton("find_key_before_opening_door.aut").intersection(
                LoadInAutomaton("jump_river_or_sword.aut").intersection(
                        LoadInAutomaton("min_two_treasure_after_last_arc.aut"))));
    }

    static private void ShowAllDetails()
    {
        //        ShowDetailts(LoadInAutomaton("find_key_before_opening_door.aut"));
        //        ShowDetailts(LoadInAutomaton("jump_river_or_sword.aut"));
        //        ShowDetailts(LoadInAutomaton("min_two_treasure_after_last_arc.aut"));
        //        ShowDetailts(LoadInAutomaton("two_treasures.aut"));
    }

    static private void ShowDetailts(Automaton a)
    {
        System.out.println("Korst geaccepteerd: " + a.getShortestExample(true));
        System.out.println("Korst niet geaccepteerd: " + a.getShortestExample(false));

        AutomatonViewer view = new AutomatonViewer(a);
        view.PrintInAutFormat();
        view.SavePs("..\\Intersection.ps");
    }

    static private void TestLevels()
    {
        Level0.main(new String[]{});
        Level1.main(new String[]{});
        Level2.main(new String[]{});
    }

    static private Automaton LoadInTest1()
    {
        Automaton aut1 = new Automaton();
        Automaton aut2 = new Automaton();
        // Test is gelukt
        aut1.addEdge("1", "2", "a");
        aut1.addEdge("2", "3", "a");
        aut1.addEdge("3", "4", "a");
        aut1.addEdge("1", "1", "b");
        aut1.addEdge("2", "2", "b");
        aut1.addEdge("3", "3", "b");
        aut1.addEdge("4", "4", "b");
        aut1.addEdge("4", "4", "a");
        aut1.setStart("1");
        aut1.addFinish("3");


        aut2.addEdge("1", "2", "b");
        aut2.addEdge("2", "3", "b");
        aut2.addEdge("1", "1", "a");
        aut2.addEdge("2", "2", "a");
        aut2.addEdge("3", "3", "a");
        aut2.addEdge("3", "3", "b");
        aut2.setStart("1");
        aut2.addFinish("3");

        return aut1.intersection(aut2);
    }

    private static Automaton LoadInAutomaton(String filename)
    {
        AutomatonParser parse = new AutomatonParser(filename);
        try {
            parse.parse();
        } catch (Exception e) {
            return null;
        }
        return parse.automaton();
    }

    private static Automaton LoadInTest2()
    {
        Automaton aut1 = new Automaton();
        Automaton aut2 = new Automaton();
        // Test is gelukt

        aut1.addEdge("1", "2", "a");
        aut1.addEdge("2", "1", "a");
        aut1.addEdge("1", "1", "b");
        aut1.addEdge("2", "2", "b");
        aut1.setStart("1");
        aut1.addFinish("1");


        aut2.addEdge("1", "2", "a");
        aut2.addEdge("2", "1", "b");
        aut2.addEdge("1", "1", "b");
        aut2.addEdge("2", "3", "a");
        aut2.addEdge("3", "3", "a");
        aut2.addEdge("3", "3", "b");
        aut2.setStart("1");
        aut2.addFinish("1");

        return aut1.intersection(aut2);
    }


    /**
     * Deze automaat heeft geen eindtoestaden
     */
    private static Automaton LoadInTest3()
    {
        Automaton aut1 = new Automaton();
        Automaton aut2 = new Automaton();
        // Test is gelukt

        aut1.addEdge("1", "2", "a");
        aut1.addEdge("2", "1", "a");
        aut1.addEdge("1", "1", "b");
        aut1.addEdge("2", "2", "b");
        aut1.setStart("1");


        return aut1;
    }

    /**
     * Deze automaat heeft enkel eindtoestanden
     */
    private static Automaton LoadInTest4()
    {
        Automaton aut1 = new Automaton();
        Automaton aut2 = new Automaton();
        // Test is gelukt

        aut1.addEdge("1", "2", "a");
        aut1.addEdge("2", "1", "a");
        aut1.addEdge("1", "1", "b");
        aut1.addEdge("2", "2", "b");
        aut1.setStart("1");
        aut1.addFinish("1");
        aut1.addFinish("2");


        return aut1;
    }

    /**
     * Deze automaat accepteerd een aantal a's + een aantal b's
     * Niet deterministische automaat
     */
    private static Automaton LoadInTest5()
    {
        Automaton aut1 = new Automaton();
        // Test is gelukt

        aut1.addEdge("1", "1", "b");
        aut1.addEdge("1", "2", "b");
        aut1.addEdge("2", "2", "b");
        aut1.setStart("1");
        aut1.addFinish("2");


        return aut1;
    }
}


/*

 */