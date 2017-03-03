package controller;

import model.FightModel;
import model.Warriors.Warrior;
import view.Gui;

import java.util.List;

/**
 * Created by Gubanov Pavel on 13.02.17.
 */
public class FightControllerImpl implements FightController {
    private FightModel model;
    //private Gui view;

    public FightControllerImpl(FightModel model) {
        this.model = model;
        //this.view = view;
    }

    @Override
    public void initNameTeams(String team1Name, String team2Name) {
        model.initNameTeams(team1Name, team2Name);
    }

    @Override
    public void initNameAndTypeWarriors(String nameWarrior, int indexTeam, int indexTypeWarrior) {
        model.initNameAndTypeWarriors(nameWarrior, indexTeam, indexTypeWarrior);
    }

    @Override
    public boolean isTeamsNotEmpty() {
        return model.isTeamsNotEmpty();
    }

    @Override
    public void startBattle() {
        model.startBattle();
    }
}
