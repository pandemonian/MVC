package model;

import model.Warriors.Warrior;
import view.FightObserver;

import java.util.List;

/**
 * Created by Gubanov Pavel on 13.02.17.
 */

// Observable
public interface FightModel {

    void registerObserver(FightObserver o);
    void notifyObserver();

    void initNameTeams(String team1Name, String team2Name);
    void initNameAndTypeWarriors();
    void startBattle();
    boolean isTeamsNotEmpty();
}
