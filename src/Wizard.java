public class Wizard extends Unit {

    protected int mana = 100;

    public Wizard(String name) {
        super(name);
        super.setCriticalChance(CustomRandomChance.getRandomChance() / 10.0);
        super.setParryChance(CustomRandomChance.getRandomChance() / 10.0);
        power = 25;
        health = 80;
    }

    @Override
    public void attack(Unit unit) {
        if (this.power != 25) {
            this.power = 25;
        }
        if (criticalChance % 10 > 5 && mana >= 10) {
            criticalChance = CustomRandomChance.getRandomChance() / 10.0;
            this.power += 10;
        } else if (mana >= 10) {
            this.power += mana / 10;
            super.attack(unit);
            mana -= 10;
        }
    }

    @Override
    public void getDamage(int damage) {
        super.getDamage(damage);
        mana += damage * 10;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "health=" + health +
                ", mana=" + mana +
                ", defence=" + defence +
                ", power=" + power +
                '}';
    }

    public void print() {
        System.out.println(this);
    }

    public void print(String str) {
        System.out.println(str + " " + this);
    }
}
