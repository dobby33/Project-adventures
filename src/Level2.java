public class Level2 {
    public static void main(String [] args){
        try {
            /* Gegeven bestand */
            Automaton aut1 = getAutomaton("adventure.aut");

            // autommaat voor het vinden van een sleutel voor je door een deur gaat
            Automaton aut3 = getAutomaton("find_key_before_opening_door.aut");

            // automaat om na het tegenkomen van een draak in de rivier te springen als je geen zwaard hebt
            Automaton aut4 = getAutomaton("jump_river_or_sword.aut");

            // autommaat om minstens twee schatten te vinden na het passeren van de laatste boog
            Automaton aut5 = getAutomaton("min_two_treasure_after_last_arc.aut");

            Automaton result = aut1.intersection(aut3).intersection(aut4).intersection(aut5);

<<<<<<< HEAD
            aut4.addEdge("3", "3", "D"); // <--
            aut4.addEdge("3", "3", "S"); // <--
            aut4.addEdge("3", "1", "A"); // ARK --> 1
            aut4.addEdge("3", "3", "R"); // <--
            aut4.addEdge("3", "3", "K"); // <--
            aut4.addEdge("3", "3", "G"); // <--
            aut4.addEdge("3", "3", "T"); // <--
            Automaton result = aut1.intersection(aut2).intersection(aut3).intersection(aut4);
            String shortestPath = result.getShortestExample(true);
            if (shortestPath == null)
                System.out.println("null");
            else
                System.out.println(shortestPath);
=======
            System.out.println(result.getShortestExample(true));
>>>>>>> origin/master
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }

    private static Automaton getAutomaton(String filename) throws Exception
    {
        AutomatonParser parse = new AutomatonParser(filename);
        parse.parse();
        return parse.automaton();
    }
}
