import java.util.ArrayList;
import java.util.List;

public class ShipPlacer {

    private final List<List<BattleChar>> battleFeld = new ArrayList<>();

    public List<List<BattleChar>> askPlayerForShips() {
        for (int row = 0; row < 10; row++) {
            ArrayList<BattleChar> col = new ArrayList<>();
            for (int column = 0; column < 10; column++) {
                col.add(column, BattleChar.leer);
            }
            battleFeld.add(row, col);

        }
        return battleFeld;
    }

}

