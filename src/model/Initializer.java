package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gubanov Pavel on 05.01.17.
 */
class Initializer {

    private Random random = new Random();
    private String team1Name;
    private String team2Name;
    private List<Warrior> team1 = new ArrayList<>();
    private List<Warrior> team2 = new ArrayList<>();
    private String[] warriorName = {"Adar", "Abner", "Alford", "Bennett", "Ward", "Wild",
            "Irk", "Kellen", "Odin"};

    String getTeam1Name() {
        return team1Name;
    }

    String getTeam2Name() {
        return team2Name;
    }

    List<Warrior> getTeam1() {
        return team1;
    }

    List<Warrior> getTeam2() {
        return team2;
    }

    private String getRandomNameWarrior() {
        int index = random.nextInt(warriorName.length);
        return warriorName[index];
    }

    boolean isTeamsNotEmpty() {
        return ((team1.size() > 0) && (team2.size() > 0));
    }

    void initNameTeams() {
        String inputStr;

        inputStr = Gui.getFieldFirstNameTeam();
        if (!inputStr.equals("")) {
            team1Name = inputStr;
            Gui.setLog("Название первого отряда: ", team1Name);
        }
        else {
            Gui.setLog("Ничего не введено, указано название первого отряда по-умолчанию - ", "England");
            team1Name = "England";
        }

        inputStr = Gui.getFieldSecondNameTeam();
        if ((!inputStr.equals("")) && (!inputStr.equals(team1Name))) {
            team2Name = inputStr;
            Gui.setLog("Название второго отряда: ", team2Name);
        }
        else {
            Gui.setLog("Ничего не введено, либо указано имя первого отряда. Присвоено название второго " +
                    "отряда по-умолчанию - ", "France");
            team2Name = "France";
        }
    }

    void initNameAndTypeWarriors() {
        List<Warrior> currentTeam = null;
        String currentTeamName = "";
        String currentTypeWarrior = "";
        String nameWarrior = Gui.getFieldNameWarrior();
        int indexTeam = Gui.getComboBoxTeam();
        int indexTypeWarrior = Gui.getComboBoxTypeWarrior();

        if (nameWarrior.equals(""))  nameWarrior = getRandomNameWarrior();

        switch (indexTeam) {
            case 0:
                currentTeam = team1;
                currentTeamName = team1Name;
                break;
            case 1:
                currentTeam = team2;
                currentTeamName = team2Name;
                break;
        }

        switch (indexTypeWarrior) {
            case 0:
                currentTeam.add(new Viking(nameWarrior, currentTeamName));
                currentTypeWarrior = "model.Viking";
                break;
            case 1:
                currentTeam.add(new Archer(nameWarrior, currentTeamName));
                currentTypeWarrior = "model.Archer";
                break;
            case 2:
                currentTeam.add(new Barbarian(nameWarrior, currentTeamName));
                currentTypeWarrior = "model.Barbarian";
                break;
        }

        switch (indexTeam) {
            case 0:
                Gui.setFieldFirstTeamWarriorList(nameWarrior, currentTypeWarrior);
                break;
            case 1:
                Gui.setFieldSecondTeamWarriorList(nameWarrior, currentTypeWarrior);
                break;
        }
    }
}
