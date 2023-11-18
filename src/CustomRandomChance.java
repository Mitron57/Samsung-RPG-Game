import java.util.Random;

public class CustomRandomChance {
    private static final Random random = new Random(1000);
    public static double getRandomChance() {
        return random.nextDouble() / 10;
    }
}
