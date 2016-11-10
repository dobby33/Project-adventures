/* Dit project is gemaakt door Bas Van Assche en Tom Martens*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AutomatonParser {
    private ArrayList<String> _fileLines;
    private Automaton _automoton;

    public AutomatonParser(String filename){
        /*De constructor leest een .aut bestand filename in.*/

        _automoton = new Automaton();
        _fileLines = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                _fileLines.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e){
            System.out.println("Opgegeven bestand is niet gevonden");
        } catch (IOException e){
            System.out.println(e.getMessage() );
        }
    }

    public void parse() throws Exception{
        /*Deze methode construeert de eindige automaat behorende bij filename (die is doorgegeven
        in de constructor). Indien blijkt dat het bestand niet van het juiste formaat is (welke zal
                resulteren in een fout tijdens het parsen), dan wordt een Exception geworpen.*/

        String[] split = new String[0];
        for (String newLine : _fileLines){
            split = newLine.split("\\s+");
            if (split.length != 3){
                throw new Exception("Invalid line: " + newLine);
            }
            if (split[0].equals("(START)")){
                _automoton.setStart(split[2]);
            }
            else if (split[2].equals("(FINAL)")){
                _automoton.setFinish(split[0]);
            }
            else {
                _automoton.addEdge(split[0], split[2], split[1]);
            }
        }
    }

    public Automaton automaton(){
        /*Deze methode geeft de eindige automaat berekend met parse() terug.*/

        return _automoton;
    }

    public void display(){
        System.out.println(_automoton.toString());
    }
}
