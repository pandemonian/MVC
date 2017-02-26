package model;

import view.FightObserver;

/**
 * Created by Gubanov Pavel on 13.02.17.
 */

// Observable
public interface FightModel {

    void registerObserver(FightObserver o);
    void notifyObserver();

    void initNameTeams(String team1Name, String team2Name);
    void initNameAndTypeWarriors(String nameWarrior, int indexTeam, int indexTypeWarrior);
    void startBattle();
    boolean isTeamsNotEmpty();
}
