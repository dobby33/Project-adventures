
public class AutomatonParser {
    public AutomatonParser(String filename){
        /*De constructor leest een .aut bestand filename in.*/

    }

    public void parse() throws Exception{
        /*Deze methode construeert de eindige automaat behorende bij filename (die is doorgegeven
        in de constructor). Indien blijkt dat het bestand niet van het juiste formaat is (welke zal
                resulteren in een fout tijdens het parsen), dan wordt een Exception geworpen.*/

    }

    public Automaton automaton(){
        /*Deze methode geeft de eindige automaat berekend met parse() terug.*/

    }

    public Automaton intersection(Automaton aut){
        /*Deze methode geeft een eindige automaat terug waarvan de taal gelijk is aan de intersectie
        van de taal L1 van de automaat van de klasse en taal L2 van de automaat aut.
        Hint: gebruik de productconstructie. Merk echter op dat de productconstructie in het boek
        van Sipser zich beperkt tot DFA’s. Bekijk zelf hoe je het idee van de productconstructie kunt
        toepassen op NFA’s. (Een alternatieve methode van NFA’s eerst omzetten naar DFA’s en dan
        de productconstructie van Sipser uitvoeren is waarschijnlijk te traag voor de voorbeeldadventures.)*/

    }

    public String getShortestExample(Boolean accept){
        /*Deze methode schrijft een kortste string uit die door de automaat wel (niet, respectievelijk)*/

    }
}
