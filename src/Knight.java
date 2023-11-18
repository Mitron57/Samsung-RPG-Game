public class Knight extends Unit {

    private int durability = 100;
    public Knight(String name) {
        super(name);
        setCriticalChance(CustomRandomChance.getRandomChance() % 100);
        setParryChance(CustomRandomChance.getRandomChance() % 100);
        power = 40;
    }

    @Override
    public void attack(Unit unit) {
        if (durability >= 10) {
            durability -= 10;
            super.attack(unit);
        }
    }

    @Override
    public void getDamage(int damage) {
        super.getDamage(damage);
        durability += (damage % 11) * 4;
    }

    @Override
    public String toString() {
        return "Knight {" +
                "health=" + health +
                ", durability=" + durability +
                ", power=" + power +
                ", name='" + name + '\'' +
                '}';
    }
}
