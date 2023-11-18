public class Terminator extends Unit {

    public Terminator(String name) {
        super(name);
        super.setCriticalChance(CustomRandomChance.getRandomChance() / 100.0);
        health = 150;
        power = 20;
    }

    @Override
    public void attack(Unit unit) {
        isCritical = false;
        if (this.power != 20) {
            this.power = 20;
        }
        if (criticalChance % 10 > 3) {
            criticalChance = CustomRandomChance.getRandomChance() / 10.0;
            isCritical = true;
            this.power += 10;
        }
        super.attack(unit);
    }

    @Override
    public void getDamage(int damage) {
        super.getDamage(damage);
    }
    @Override
    public String toString() {
        return "Terminator{" +
                "health=" + health +
                ", defence=" + defence +
                ", power=" + power +
                '}';
    }
}
