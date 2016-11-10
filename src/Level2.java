public class Level2 {
    public static void main(String [] args){
        try {
            AutomatonParser parse1 = new AutomatonParser("src\\adventure1.aut");
            parse1.parse();
            Automaton aut1 = parse1.automaton();

            // autommaat voor het vinden van een sleutel voor je door een deur gaat
            Automaton aut2 = new Automaton();
            aut2.setStart("1");
            aut2.addFinish("1");
            aut2.addFinish("2");
            aut2.addEdge("1", "1", "D"); // <--
            aut2.addEdge("1", "1", "S"); // <--
            aut2.addEdge("1", "1", "A"); // <--
            aut2.addEdge("1", "1", "R"); // <--
            aut2.addEdge("1", "2", "K"); // KEY --> 2
            aut2.addEdge("1", "3", "G"); // GATE --> 3
            aut2.addEdge("1", "1", "T"); // <--

            aut2.addEdge("2", "2", "D"); // <--
            aut2.addEdge("2", "2", "S"); // <--
            aut2.addEdge("2", "2", "A"); // <--
            aut2.addEdge("2", "2", "R"); // <--
            aut2.addEdge("2", "2", "K"); // <--
            aut2.addEdge("2", "2", "G"); // <--
            aut2.addEdge("2", "2", "T"); // <--

            aut2.addEdge("3", "3", "D"); // <--
            aut2.addEdge("3", "3", "S"); // <--
            aut2.addEdge("3", "3", "A"); // <--
            aut2.addEdge("3", "3", "R"); // <--
            aut2.addEdge("3", "3", "K"); // <--
            aut2.addEdge("3", "3", "G"); // <--
            aut2.addEdge("3", "3", "T"); // <--

            // automaat om na het tegenkomen van een draak in de rivier te springen als je geen zwaard hebt
            Automaton aut3 = new Automaton();
            aut3.setStart("1");
            aut3.addFinish("1");
            aut3.addFinish("2");
            aut3.addFinish("4");
            aut3.addEdge("1", "3", "D"); // DRAGON --> 3
            aut3.addEdge("1", "2", "S"); // SWORD --> 2
            aut3.addEdge("1", "1", "A"); // <--
            aut3.addEdge("1", "1", "R"); // <--
            aut3.addEdge("1", "1", "K"); // <--
            aut3.addEdge("1", "1", "G"); // <--
            aut3.addEdge("1", "1", "T"); // <--

            aut3.addEdge("2", "2", "D"); // <--
            aut3.addEdge("2", "2", "S"); // <--
            aut3.addEdge("2", "2", "A"); // <--
            aut3.addEdge("2", "2", "R"); // <--
            aut3.addEdge("2", "2", "K"); // <--
            aut3.addEdge("2", "2", "G"); // <--
            aut3.addEdge("2", "2", "T"); // <--

            aut3.addEdge("3", "4", "R"); // RIVER --> 4

            aut3.addEdge("4", "3", "D"); // DRAGON --> 3
            aut3.addEdge("4", "2", "S"); // SWORD --> 2
            aut3.addEdge("4", "1", "A"); // --> 1
            aut3.addEdge("4", "1", "R"); // --> 1
            aut3.addEdge("4", "1", "K"); // --> 1
            aut3.addEdge("4", "1", "G"); // --> 1
            aut3.addEdge("4", "1", "T"); // --> 1

            // autommaat om minstens twee schatten te vinden na het passeren van de laatste boog
            Automaton aut4 = new Automaton();
            aut4.setStart("1");
            aut4.addFinish("3");
            aut4.addEdge("1", "1", "D"); // <--
            aut4.addEdge("1", "1", "S"); // <--
            aut4.addEdge("1", "1", "A"); // <--
            aut4.addEdge("1", "1", "R"); // <--
            aut4.addEdge("1", "1", "K"); // <--
            aut4.addEdge("1", "1", "G"); // <--
            aut4.addEdge("1", "2", "T"); // TREASURE --> 2

            aut4.addEdge("2", "2", "D"); // <--
            aut4.addEdge("2", "2", "S"); // <--
            aut4.addEdge("2", "1", "A"); // ARK --> 1
            aut4.addEdge("2", "2", "R"); // <--
            aut4.addEdge("2", "2", "K"); // <--
            aut4.addEdge("2", "2", "G"); // <--
            aut4.addEdge("2", "3", "T"); // TREASURE --> 3

            aut4.addEdge("3", "3", "D"); // <--
            aut4.addEdge("3", "3", "S"); // <--
            aut4.addEdge("3", "1", "A"); // ARK --> 1
            aut4.addEdge("3", "3", "R"); // <--
            aut4.addEdge("3", "3", "K"); // <--
            aut4.addEdge("3", "3", "G"); // <--
            aut4.addEdge("3", "3", "T"); // <--
            Automaton result = aut1.intersection(aut2).intersection(aut3).intersection(aut4);
            System.out.println(result.toString());
            String shortestPath = result.getShortestExample(true);
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
