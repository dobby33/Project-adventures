
public class Main {
    public static void main(String[] args) {
        AutomatonParser parser = new AutomatonParser("src\\adventure3.aut");
        try {
            parser.parse();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        Automaton a1 = parser.automaton();

        parser = new AutomatonParser("src\\adventure2.aut");
        try {
            parser.parse();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        Automaton a2 = parser.automaton();

        Automaton a3 = a1.intersection(a2);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3.toString());
    }
}
