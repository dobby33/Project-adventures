public class Level1 {
    public static void main(String [] args){
        try {
            /* Gegeven bestand */
            Automaton aut1 = getAutomaton("adventure.aut");

            // autommaat voor minstens twee schatten te vinden
            Automaton aut2 = getAutomaton("two_treasures.aut");

            // autommaat voor het vinden van een sleutel voor je door een deur gaat
            Automaton aut3 = getAutomaton("find_key_before_opening_door.aut");

            // automaat om na het tegenkomen van een draak in de rivier te springen als je geen zwaard hebt
            Automaton aut4 = getAutomaton("jump_river_or_sword.aut");


            Automaton result = aut1.intersection(aut2).intersection(aut3).intersection(aut4);

            System.out.println(result.getShortestExample(true));
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
