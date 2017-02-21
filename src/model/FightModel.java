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

    void setTeam1Name(String team1Name);
    void setTeam2Name(String team2Name);
    void setTeam1(List<Warrior> team1);
    void setTeam2(List<Warrior> team2);

    String getTeam1Name();
    String getTeam2Name();
    List<Warrior> getTeam1();
    List<Warrior> getTeam2();
}
