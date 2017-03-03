package view;

/**
 * Created by Gubanov Pavel on 13.02.17.
 */

//Observer
//методы для обновления UI
public interface FightObserver {
    void updateView(StringBuilder msg, StringBuilder team1WarriorName, StringBuilder team2WarriorName);
}
