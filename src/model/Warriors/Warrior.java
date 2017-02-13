package model.Warriors;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
interface Warrior extends Cloneable {

    int attack();
    default void attackingUnit(Warrior unit) {
        unit.takeDamage(this.attack());
    }
    int getDamage();
    void takeDamage(int damage);
    boolean isAlive();
    String getName();
}
