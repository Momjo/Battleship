import javafx.scene.layout.CornerRadii;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PlayerTest {

    private Player cc;

    {
        try {
            List<List<BattleChar>> battleFeld = new BattleFeldFileLoader().loadFromFile("/Users/mohammad/Documents/GitHub/BattleShip/src/feldBattle");
            BattleShip_Logic logic = new BattleShip_Logic(battleFeld);

            cc = new Player(logic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("Mark Adjacent Fields As Shot At")
    void mafasa(){

        Set<Coordinates> c = new HashSet<>();
        Coordinates cor0 = new Coordinates(0, 3);
        c.add(cor0);
        cc.markAdjacentFieldsAsShotAt(c);


    }

    @Test
    @DisplayName("Has Previously Shot At")
    void hpsa() {

        Coordinates cor0 = new Coordinates(0, 3);
        cc.markFieldAsShotAt(cor0);
        assertTrue(cc.hasPreviouslyShotAt(cor0));

    }

}
