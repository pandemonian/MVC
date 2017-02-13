package model.Warriors;

/**
 * Created by Gubanov Pavel on 02.01.17.
 */
abstract class AbstractWarrior {

    private int health;
    private int damage;
    private String squadName;
    private String name;

    AbstractWarrior(String name, String squadName, int health, int damage) {
        this.name = name;
        this.squadName = squadName;
        this.health = health;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public int attack() {
        return damage;
    }

    public void takeDamage(int damage) {
        if ((damage < health) || (damage == health))  health -= damage;
        else health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String toString() {
        return "имя: " + name + ", класс: " + getClass().getSimpleName() + ", отряд: " + squadName;
    }
}
