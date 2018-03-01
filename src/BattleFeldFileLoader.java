import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BattleFeldFileLoader {

    public List<List<BattleChar>> loadFromFile(String filename) throws IOException {
        List<String> lines = this.loadFile(filename);
        List<List<BattleChar>> battleFeld = new ArrayList<>();

        for (int l = 0; l < lines.size(); l++) {

            String firstLine = lines.get(l);

            List<Character> characters = this.stringToCharacters(firstLine);
            List<BattleChar> battleChars = this.charactersToBattleChars(characters);

            battleFeld.add(battleChars);
        }

        return battleFeld;
    }


    private List<String> loadFile(String path) throws IOException {

        BufferedReader bufReader = new BufferedReader(new FileReader(path));
        ArrayList<String> listOfLines = new ArrayList<>();

        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }

        bufReader.close();

        return listOfLines;
    }


    private List<Character> stringToCharacters(String s) {

        ArrayList<Character> charslist = new ArrayList<>();
        for (Character c : s.toCharArray()) {
            charslist.add(c);
        }
        return charslist;
    }

    private List<BattleChar> charactersToBattleChars(List<Character> characters) {

        ArrayList<BattleChar> battleCharList = new ArrayList<>();
        for (Character charInCharList : characters) {
            BattleChar s = BattleChar.unserialize(charInCharList);
            battleCharList.add(s);
        }

        return battleCharList;
    }
}

