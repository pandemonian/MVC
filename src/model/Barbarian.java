package model;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Barbarian extends AbstractWarrior implements Warrior{

    private static final int VIKING_HEALTH = 100;
    private static final int VIKING_DAMAGE = 30;

    Barbarian(String name, String squadName) {
        super(name, squadName, VIKING_HEALTH, VIKING_DAMAGE);
    }
}