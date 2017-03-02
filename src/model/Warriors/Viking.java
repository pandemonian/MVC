package model.Warriors;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
public class Viking extends AbstractWarrior implements Warrior {

    private static final int VIKING_HEALTH = 120;
    private static final int VIKING_DAMAGE = 50;

    public Viking(String name, String squadName) {
        super(name, squadName, VIKING_HEALTH, VIKING_DAMAGE);
    }
}
