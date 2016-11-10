public class Level2 {
    public static void main(String [] args){
        try {
            AutomatonParser parse1 = new AutomatonParser("src\\adventure1.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();

            Automaton aut2 = new Automaton();
            aut2.setStart("1");
            aut2.setFinish("3");
            aut2.addEdge("1", "2", "T");
            aut2.addEdge("2", "3", "T");
            Automaton result = aut1.intersection(aut2);
            System.out.println(result.getShortestExample(true));
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}
