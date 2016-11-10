/* Dit project is gemaakt door Bas Van Assche en Tom Martens*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AutomatonParser {
    private ArrayList<String> fileLines;

    public AutomatonParser(String filename){
        /*De constructor leest een .aut bestand filename in.*/
        try {

            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                fileLines.add(line);
            }
            bufferedReader.close();
            String[] split = new String[0];
            for (String newLine : fileLines){
                split = newLine.split("\\s+");

            }

        } catch (FileNotFoundException e){
            System.out.println("Opgegeben bestand is niet gevonden");
        } catch (IOException e){
            System.out.println( e.getMessage() );
        }
    }

    public Automaton intersection(Automaton aut)
    {
        /*Deze methode geeft een eindige automaat terug waarvan de taal gelijk is aan de intersectie
        van de taal L1 van de automaat van de klasse en taal L2 van de automaat aut.
        Hint: gebruik de productconstructie. Merk echter op dat de productconstructie in het boek
        van Sipser zich beperkt tot DFA’s. Bekijk zelf hoe je het idee van de productconstructie kunt
        toepassen op NFA’s. (Een alternatieve methode van NFA’s eerst omzetten naar DFA’s en dan
        de productconstructie van Sipser uitvoeren is waarschijnlijk te traag voor de voorbeeldadventures.)*/
        return null;

    }

    public void parse() throws Exception{
        /*Deze methode construeert de eindige automaat behorende bij filename (die is doorgegeven
        in de constructor). Indien blijkt dat het bestand niet van het juiste formaat is (welke zal
                resulteren in een fout tijdens het parsen), dan wordt een Exception geworpen.*/

    }

    public Automaton automaton(){
        /*Deze methode geeft de eindige automaat berekend met parse() terug.*/
        return null;

    }

    public String getShortestExample(Boolean accept){
        /*Deze methode schrijft een kortste string uit die door de automaat wel (niet, respectievelijk)*/
        return null;
    }
}
