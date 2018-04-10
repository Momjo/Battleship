# Battleship


This is a simple Battleship game in command line

## Development

Should install JUnit and set JUnit to CLASSPATH

### Run

**Use [releases](https://github.com/Momjo/Battleship/releases) tab to download the jar directly.**

**OR...**

**Go to project folder:**
```bash
> cd .../Battleship/src
```
**To load the default Battle field recomment this lines in BattleShip.java on Main method:**
```bash
  172: List<List<BattleChar>> battleFeld_a = new BattleFeldFileLoader().loadFromFile("playerBattleField");
  173: List<List<BattleChar>> battleFeld_b = new BattleFeldFileLoader().loadFromFile("playerBattleField");
```
**and comment this lines:**
```bash
  180: List<List<BattleChar>> battleFeld_a = new ShipPlacer().askPlayerForShips();
  180: List<List<BattleChar>> battleFeld_b = new ShipPlacer().askPlayerForShips();
```

**then run the BattleShip_View.java**
