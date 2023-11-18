public class Unit {
    protected int health = 100;
    protected int defence = 100;
    protected int power = 10;

    protected double criticalChance = 0.1;
    protected double parryChance = 0.1;

    protected boolean isCritical = false;
    protected boolean isParry = false;


    public String getName() {
        return name;
    }

    protected String name;

    protected boolean isAlive = true;

    public Unit() {}
    public Unit(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public int getDefence() {
        return defence;
    }

    public int getPower() {
        return power;
    }

    public double getCriticalChance() {
        return criticalChance;
    }

    public double getParryChance() {
        return parryChance;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setCriticalChance(double criticalChance) {
        this.criticalChance = criticalChance;
    }

    public void setParryChance(double parryChance) {
        this.parryChance = parryChance;
    }

    public void attack(Unit unit) {
        unit.getDamage(power);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "health=" + health +
                ", defence=" + defence +
                ", power=" + power +
                '}';
    }

    public boolean isCritical() {
        return isCritical;
    }

    public boolean isParry() {
        return isParry;
    }

    public void getDamage(int damage) {
        isParry = false;
        if (isAlive && this.parryChance % 100 > 10) {
            isParry = true;
        } else if (isAlive && this.defence >= 0) {
            this.defence -= damage;
        } else if (isAlive && this.health > 0) {
            this.health -= damage;
        } else {
            isAlive = false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }
}
