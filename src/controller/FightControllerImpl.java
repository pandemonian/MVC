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
    private Gui view;

    public FightControllerImpl(FightModel model, Gui view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void relayTeam1Name(String team1Name) {
        model.setTeam1Name(team1Name);
    }

    @Override
    public void relayTeam2Name(String team2Name) {
        model.setTeam2Name(team2Name);
    }

    @Override
    public void relayTeam1(List<Warrior> team1) {
        model.setTeam1(team1);
    }

    @Override
    public void relayTeam2(List<Warrior> team2) {
        model.setTeam2(team2);
    }
}
