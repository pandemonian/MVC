package controller;

/**
 * Created by Gubanov Pavel on 13.02.17.
 */
public interface FightController {
    void initNameTeams(String team1Name, String team2Name);
    void initNameAndTypeWarriors(String nameWarrior, int indexTeam, int indexTypeWarrior);
    void startBattle();
    boolean isTeamsNotEmpty();
}
