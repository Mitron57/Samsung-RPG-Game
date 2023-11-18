public class Player {
    private String nick;
    private Unit[] units = new Unit[3];
    private int lastUnit = 0;

    public Player(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void addUnit(Unit unit) {
        if (lastUnit < units.length) {
            units[lastUnit++] = unit;
        } else {
            System.out.println("Cannot add new unit to player (too many units)");
        }
    }

    public Unit[] getUnits() {
        return units;
    }

    public String[] getUnitsNames() {
        String[] names = new String[3];
        for (int i = 0; i < 3; ++i) {
            names[i] = units[i].getName();
        }
        return names;
    }
}
