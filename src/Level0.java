public class Level0 {
    public static void main(String [] args){
        try {
            AutomatonParser parse1 = new AutomatonParser("src\\adventure1.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();
            System.out.println(aut1.getShortestExample(true));
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}
