package model.Warriors;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
public class Archer extends AbstractWarrior implements Warrior {

    private static final int VIKING_HEALTH = 80;
    private static final int VIKING_DAMAGE = 80;

    Archer(String name, String squadName) {
        super(name, squadName, VIKING_HEALTH, VIKING_DAMAGE);
    }
}