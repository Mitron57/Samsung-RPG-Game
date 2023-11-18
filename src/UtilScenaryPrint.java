import java.util.Scanner;

enum Language {
    Russian,
    English
}

public class UtilScenaryPrint {
    private static Language language = Language.English;

    public static void greet(String gameName) {
        switch (language) {
            case English -> System.out.println("Hello! Let's play in " + gameName + "!");
            case Russian -> System.out.println("Привет! Давай поиграем в " + gameName + "!");
        }
    }

    public static String config(Scanner scan) {
        switch (language) {
            case English ->
                    System.out.println("Choose game configuration (by default Manual, type A or SA to change configuration (Auto, Semi-Auto)), type Enter to skip this part:");
            case Russian ->
                    System.out.println("Выберите конфигурацию игры (по умолчанию Manual, введите A or SA, чтобы изменить ее (Auto, Semi-Auto), чтобы пропустить, просто нажмите клавишу Enter):");
        }
        return scan.nextLine();
    }

    public static void errorMessage() {
        switch (language) {
            case English -> System.out.println("Unexpected error occurred!");
            case Russian -> System.out.println("Произошла непредвиденная ошибка!");
        }
    }

    public static void getPlayerName() {
        switch (language) {
            case English -> System.out.println("Let's meet! What's your name?");
            case Russian -> System.out.println("Давай познакомимся! Как тебя зовут?");
        }
    }

    public static void equip() {
        switch (language) {
            case English ->
                    System.out.println("Choose 3 units in desired sequence for the Battle! (type W, K, T with whitespace)");
            case Russian ->
                    System.out.println("Выберите 3 персонажей в желаемой последовательности, которыми вы будете сражаться! (введите W, K, T через пробел)");
        }
    }

    public static void battle(int round, String playerName, String characters, String perks, Player enemy) {
        switch (language) {
            case English ->
                    System.out.println("Round " + round + ". Player " + playerName + " is in the battle. " + characters + ". Main character perks: " + perks);

            case Russian ->
                    System.out.println("Раунд " + round + ". Игрок " + playerName + " совершает атаку. " + characters + ". Основныe характеристики: " + perks);

        }
        choose();
        for (int i = 0; i < 3; ++i) {
            System.out.println((i + 1) + " " + enemy.getUnitsNames()[i]);
        }

    }

    public static void parry() {
        switch (language) {
            case English -> System.out.println("Attack is parried! Zero damage!");
            case Russian -> System.out.println("Атака парирована! Никакого урона!");
        }
    }

    public static void critical() {
        switch (language) {
            case English -> System.out.println("Critical damage!");
            case Russian -> System.out.println("Критический урон!");
        }
    }

    public static void setLanguage(Language language) {
        UtilScenaryPrint.language = language;
    }

    public static void choose() {
        switch (language) {
            case English -> System.out.println("Type the number of enemy's character you want attack to:");
            case Russian -> System.out.println("Введите номер персонажа противника, которого вы хотите атаковать:");

        }
    }

    public static void endGame(String nick) {
        switch (language) {
            case English -> System.out.println("Game has ended! Winner is " + nick + "!");
            case Russian -> System.out.println("Игры окончена! Победил игрок " + nick + "!");
        }
    }
}
