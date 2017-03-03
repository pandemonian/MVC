import controller.FightController;
import controller.FightControllerImpl;
import model.Battle;
import view.Gui;

/**
 * Created by Gubanov Pavel on 25.11.16.
 */
public class Run {

    public static void main(String[] args) {
        Battle battle = new Battle();
        FightController controller = new FightControllerImpl(battle);
        Gui gui = new Gui(controller);
    }
}
