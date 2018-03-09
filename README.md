# Battleship


This is a simple Battleship game in command line

## Development

Should install JUnit and set JUnit to CLASSPATH

### Run

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
![asdas](https://gonevis.s3.amazonaws.com/dolphin/278e9df7-e9bc-4714-88aa-3aa4f6c1bf2a/1520596777376_Screen_Shot_2018-03-09_at_12.34.45.png)

**Run the .jar file:**

```bash
> java -jar Battleship.jar
```
