
import java.util.Set;

public class Player {
    public String name;


    private final BattleShip_Logic logic;

    public Player(BattleShip_Logic logic) {
        this.logic = logic;
    }

    public boolean hasShipAt(Coordinates coordinates) {
        return this.logic.get(coordinates) == BattleChar.X;
    }

    public boolean isFieldEmpty(Coordinates coordinates) {
        return this.logic.get(coordinates) == BattleChar.leer;
    }

    public boolean hasPreviouslyShotAt(Coordinates coordinates) {
        return logic.hasPreviouslyShotAt(coordinates);
    }

    public boolean hasPreviouslyShotAt(Set<Coordinates> coordinates) {
        return logic.hasPreviouslyShotAt(coordinates);
    }

    public Set<Coordinates> hitsEntireShipSunk(Coordinates c, Player target) {
        return logic.hitsEntireShipSunk(c, target);
    }

    public void markFieldAsShotAt(Coordinates coordinates) {
        logic.markFieldAsShotAt(coordinates);
    }
    public void markAdjacentFieldsAsShotAt(Set<Coordinates> coordinates) {
        logic.markAdjacentFieldsAsShotAt(coordinates);
    }
}
