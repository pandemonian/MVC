package model;

import model.Warriors.Warrior;

import java.util.List;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Squad {

    private String name;
    private List<Warrior> team;

    Squad(String name, List<Warrior> team) {
        this.name = name;
        this.team = team;
    }

    String getName() {
        return name;
    }

    Warrior getTeamWarrior(int index) {
        return team.get(index);
    }

    int getTeamSize() {
        return team.size();
    }

    boolean hasAliveWarriors() {
        for (Warrior warrior: team) {
            if (warrior.isAlive()) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
