package controller;


import model.Warriors.Warrior;

import java.util.List;

/**
 * Created by Gubanov Pavel on 13.02.17.
 */
public interface FightController {

    void initNameTeams(String team1Name, String team2Name);
    void initNameAndTypeWarriors();
    void startBattle();
    boolean isTeamsNotEmpty();
}
