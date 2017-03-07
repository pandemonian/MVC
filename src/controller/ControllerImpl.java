package controller;

import model.Model;

/**
 * Created by Gubanov Pavel on 13.02.17.
 */
public class ControllerImpl implements Controller {
    private Model model;

    public ControllerImpl(Model model) {
        this.model = model;
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
