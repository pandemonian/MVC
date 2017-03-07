import controller.*;
import model.ModelImpl;
import view.ViewImpl;

/**
 * Created by Gubanov Pavel on 25.11.16.
 */
public class Run {

    public static void main(String[] args) {
        ModelImpl modelImpl = new ModelImpl();
        Controller controller = new ControllerImpl(modelImpl);
        new ViewImpl(controller, modelImpl);
    }
}