package controller;


import model.Warriors.Warrior;

import java.util.List;

/**
 * Created by Gubanov Pavel on 13.02.17.
 */
public interface FightController {

    void relayTeam1Name(String team1Name);
    void relayTeam2Name(String team2Name);
    void relayTeam1(List<Warrior> team1);
    void relayTeam2(List<Warrior> team2);



}
