public class Level0 {
    public static void main(String [] args){
        try {
            AutomatonParser parse1 = new AutomatonParser("adventure.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();
            String shortestPath = aut1.getShortestExample(true);
            if (shortestPath == null)
                System.out.println("null");
            else
                System.out.println(shortestPath);
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}
