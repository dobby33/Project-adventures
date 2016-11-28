public class TestOpUitzonderingen {
    public static void main(String [] args){
        System.out.println("dubbele_epsylon.aut");
        try {
            AutomatonParser parse1 = new AutomatonParser("dubbele_epsylon.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();
            String shortestPath = aut1.getShortestExample(false);
            System.out.println(shortestPath);
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        System.out.println("\nmengelijng.aut");
        try {
            AutomatonParser parse1 = new AutomatonParser("mengelijng.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();
            String shortestPath = aut1.getShortestExample(false);
            System.out.println(shortestPath);
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        System.out.println("\nontbrekende_letter.aut");
        try {
            AutomatonParser parse1 = new AutomatonParser("ontbrekende_letter.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();
            String shortestPath = aut1.getShortestExample(false);
            System.out.println(shortestPath);
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        System.out.println("\nontbrekende_letter_met_epsylon.aut");
        try {
            AutomatonParser parse1 = new AutomatonParser("ontbrekende_letter_met_epsylon.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();
            String shortestPath = aut1.getShortestExample(false);
            System.out.println(shortestPath);
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        System.out.println("\nontbrekende_letter_met_cirkel_epsylon.aut");
        try {
            AutomatonParser parse1 = new AutomatonParser("ontbrekende_letter_met_cirkel_epsylon.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();
            String shortestPath = aut1.getShortestExample(false);
            System.out.println(shortestPath);
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}