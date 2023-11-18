import java.util.Arrays;
import java.util.Scanner;

enum Config {
    AUTO, //unused
    SEMIAUTO, //unused
    MANUAL
}

public class Game {
    private final String name = "BattleCLI";
    private final Scenary scenary = new Scenary(State.Greeting);
    private Config config = Config.MANUAL;
    private Player[] players = new Player[2];
    private static int lastPlayer = 0;
    private final Scanner scan = new Scanner(System.in);

    public void setPlayers(int playersCount) {
        this.players = new Player[playersCount];
    }

    public void addPlayer(String nick) {
        if (lastPlayer < players.length) {
            players[lastPlayer++] = new Player(nick);
        }
    }

    private boolean isAlive(Unit[] first, Unit[] second) {
        return Arrays.stream(first).anyMatch(Unit::isAlive) || Arrays.stream(second).anyMatch(Unit::isAlive);
    }

    public void run() {
        int round = 0;
        switch (scenary.getState()) {
            case Greeting:
                UtilScenaryPrint.greet(getName());
            case Config:
                System.out.println("Choose language (RU, EN) (by default EN, type Enter to skip this part):");
                String lang = scan.nextLine();
                switch (lang) {
                    case "RU" -> UtilScenaryPrint.setLanguage(Language.Russian);
                    case "EN", "" -> UtilScenaryPrint.setLanguage(Language.English);
                    default -> {
                        UtilScenaryPrint.errorMessage();
                        return;
                    }
                }
                String configString = UtilScenaryPrint.config(scan);
                if (configString.equals("SA")) {
                    config = Config.SEMIAUTO; //Unimplemented
                } else if (configString.equals("A")) {
                    config = Config.AUTO; //Unimplemented
                } else if (!configString.isEmpty()) {
                    UtilScenaryPrint.errorMessage();
                } else {
                    config = Config.MANUAL;
                }
            case Equipment:
                switch (config) {
                    case MANUAL -> {
                        for (int i = 0; i < 2; ++i) {
                            UtilScenaryPrint.getPlayerName();
                            addPlayer(scan.nextLine());
                            UtilScenaryPrint.equip();
                            var units = scan.nextLine().strip().split(" ");
                            for (var unit : units) {
                                switch (unit) {
                                    case "W" -> players[i].addUnit(new Wizard(unit));
                                    case "K" -> players[i].addUnit(new Knight(unit));
                                    case "T" -> players[i].addUnit(new Terminator(unit));
                                    default -> {
                                        UtilScenaryPrint.errorMessage();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    case SEMIAUTO, AUTO -> {
                        UtilScenaryPrint.errorMessage();
                        return;
                    }
                }
            case Battle:
                int[] units = new int[2];
                var firstPlayerUnits = players[0].getUnits();
                var secondPlayerUnits = players[1].getUnits();
                while (isAlive(firstPlayerUnits, secondPlayerUnits)) {
                    var player = players[round % 2];
                    var enemy = players[(round + 1) % 2];
                    Unit unit = new Unit();
                    for (var hero : player.getUnits()) {
                        if (hero.isAlive) {
                            unit = hero;
                            break;
                        }
                    }
                    UtilScenaryPrint.battle(
                            round + 1,
                            player.getNick(),
                            unit.getName(),
                            unit.toString(),
                            enemy
                    );
                    int number;
                    while ((number = scan.nextInt()) > enemy.getUnits().length) {
                        UtilScenaryPrint.errorMessage();
                        UtilScenaryPrint.choose();
                    }
                    var enemyUnit = enemy.getUnits()[number - 1];
                    unit.attack(enemyUnit);
                    if (enemyUnit.isParry()) {
                        UtilScenaryPrint.parry();
                    } else if (unit.isCritical()) {
                        UtilScenaryPrint.critical();
                    }
                    units[round % 2]++;
                    round++;
                }
            case End:
                UtilScenaryPrint.endGame(players[(round - 1)%2].getNick());
        }
    }

    public String getName() {
        return name;
    }
}
