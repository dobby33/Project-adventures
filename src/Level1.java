public class Level1 {
    public static void main(String [] args){
        try {
            AutomatonParser parse1 = new AutomatonParser("src\\adventure1.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();

            // autommaat voor minstens twee schatten te vinden
            Automaton aut2 = new Automaton();
            aut2.setStart("1");
            aut2.setFinish("3");
            aut2.addEdge("1", "1", "D"); // <--
            aut2.addEdge("1", "1", "S"); // <--
            aut2.addEdge("1", "1", "A"); // <--
            aut2.addEdge("1", "1", "R"); // <--
            aut2.addEdge("1", "1", "K"); // <--
            aut2.addEdge("1", "1", "G"); // <--
            aut2.addEdge("1", "2", "T"); // TREASURE --> 2

            aut2.addEdge("2", "2", "D"); // <--
            aut2.addEdge("2", "2", "S"); // <--
            aut2.addEdge("2", "2", "A"); // <--
            aut2.addEdge("2", "2", "R"); // <--
            aut2.addEdge("2", "2", "K"); // <--
            aut2.addEdge("2", "2", "G"); // <--
            aut2.addEdge("2", "3", "T"); // TREASURE --> 3

            aut2.addEdge("3", "3", "D"); // <--
            aut2.addEdge("3", "3", "S"); // <--
            aut2.addEdge("3", "3", "A"); // <--
            aut2.addEdge("3", "3", "R"); // <--
            aut2.addEdge("3", "3", "K"); // <--
            aut2.addEdge("3", "3", "G"); // <--
            aut2.addEdge("3", "3", "T"); // <--

            // autommaat voor het vinden van een sleutel voor je door een deur gaat
            Automaton aut3 = new Automaton();
            aut3.setStart("1");
            aut3.setFinish("3");
            aut3.addEdge("1", "1", "D"); // <--
            aut3.addEdge("1", "1", "S"); // <--
            aut3.addEdge("1", "1", "A"); // <--
            aut3.addEdge("1", "1", "R"); // <--
            aut3.addEdge("1", "2", "K"); // KEY --> 2
            aut3.addEdge("1", "1", "G"); // <--
            aut3.addEdge("1", "1", "T"); // <--

            aut3.addEdge("2", "2", "D"); // <--
            aut3.addEdge("2", "2", "S"); // <--
            aut3.addEdge("2", "2", "A"); // <--
            aut3.addEdge("2", "2", "R"); // <--
            aut3.addEdge("2", "2", "K"); // <--
            aut3.addEdge("2", "3", "G"); // GATE --> 3
            aut3.addEdge("2", "2", "T"); // <--

            aut3.addEdge("3", "3", "D"); // <--
            aut3.addEdge("3", "3", "S"); // <--
            aut3.addEdge("3", "3", "A"); // <--
            aut3.addEdge("3", "3", "R"); // <--
            aut3.addEdge("3", "3", "K"); // <--
            aut3.addEdge("3", "3", "G"); // <--
            aut3.addEdge("3", "3", "T"); // <--

            // automaat om na het tegenkomen van een draak in de rivier te springen als je geen zwaard hebt
            Automaton aut4 = new Automaton();
            aut4.setStart("1");
            aut4.setFinish("3");
            aut4.addEdge("1", "1", "D"); // <--
            aut4.addEdge("1", "1", "S"); // <--
            aut4.addEdge("1", "1", "A"); // <--
            aut4.addEdge("1", "1", "R"); // <--
            aut4.addEdge("1", "1", "K"); // <--
            aut4.addEdge("1", "1", "G"); // <--
            aut4.addEdge("1", "1", "T"); // <--
            Automaton result = aut1.intersection(aut2).intersection(aut3).intersection(aut4);
            System.out.println(result.getShortestExample(true));
        }
        catch (Exception e){
            System.out.print("Error : ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}
