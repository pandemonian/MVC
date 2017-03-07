package model;

import model.Warriors.*;
import view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
public class ModelImpl implements Model {
    private StringBuilder infoMessage;
    private StringBuilder team1WarriorNameAndType;
    private StringBuilder team2WarriorNameAndType;

    private View observer;
    private Random random;
    private String team1Name;
    private String team2Name;
    private List<Warrior> team1;
    private List<Warrior> team2;
    private String[] randomWarriorName = {"Adar", "Abner", "Alford", "Bennett", "Ward", "Wild",
            "Irk", "Kellen", "Odin"};

    public ModelImpl() {
        random = new Random();
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        infoMessage = new StringBuilder(0);
        team1WarriorNameAndType = new StringBuilder(0);
        team2WarriorNameAndType = new StringBuilder(0);
    }

    @Override
    public void registerObserver(View o) {
        observer = o;
    }

    @Override
    public void notifyObserver() {
        observer.updateView(infoMessage, team1WarriorNameAndType, team2WarriorNameAndType);
        infoMessage.delete(0, infoMessage.length());
        team1WarriorNameAndType.delete(0, team1WarriorNameAndType.length());
        team2WarriorNameAndType.delete(0, team2WarriorNameAndType.length());
    }

    private String getRandomNameWarrior() {
        int index = random.nextInt(randomWarriorName.length);
        return randomWarriorName[index];
    }

    private int getRandomWarriorIndex(Squad crew) {
        while (true) {
            int indexWarriorTeam = random.nextInt(crew.getTeamSize());
            if (crew.getTeamWarrior(indexWarriorTeam).isAlive()) {
                return indexWarriorTeam;
            }
        }
    }


    public void startBattle() {
        Squad squad1 = new Squad(team1Name, team1);
        Squad squad2 = new Squad(team2Name, team2);

        infoMessage.append("Битва началась!!! ").append(DataHelper.getFormattedStartDate()).append("\n");
        notifyObserver();

        startFight(squad1, squad2);

        infoMessage.append("Бой продолжался: ").append(DataHelper.getFormattedDiff());
        notifyObserver();
    }

    private void startFight(Squad squad1, Squad squad2) {

        while (true) {
            if (strikeTeamToTeam(squad1, squad2)) {
                return;
            }
            if (strikeTeamToTeam(squad2, squad1)) {
                return;
            }
        }
    }

    private boolean strikeTeamToTeam(Squad squad1, Squad squad2) {
        striking(squad1, squad2);
        DataHelper.skipTime();
        if (isAnyLoose(squad1, squad2)) {
            winnerIs(squad1, squad2);
            return true;
        }
        return false;
    }

    private void striking(Squad crew1, Squad crew2) {

        int indexWarriorTeam1;
        int indexWarriorTeam2;

        //если у обеих отрядов есть живые, то выбираем их рандомно
        if ((crew1.hasAliveWarriors()) && (crew2.hasAliveWarriors())) {

            indexWarriorTeam1 = getRandomWarriorIndex(crew1);
            indexWarriorTeam2 = getRandomWarriorIndex(crew2);

            crew1.getTeamWarrior(indexWarriorTeam1).attackingUnit(crew2.getTeamWarrior(indexWarriorTeam2));

            infoMessage.append(crew1.getTeamWarrior(indexWarriorTeam1).getClass().getSimpleName()).append(" ")
                    .append(crew1.getTeamWarrior(indexWarriorTeam1).getName()).append(" из отряда \"")
                    .append(crew1.getName()).append("\" нанёс ")
                    .append(crew2.getTeamWarrior(indexWarriorTeam2).getClass().getSimpleName())
                    .append("`у ").append(crew2.getTeamWarrior(indexWarriorTeam2).getName()).append(" из отряда \"")
                    .append(crew2.getName()).append("\" ")/*.append("\" ")*/
                    .append(String.valueOf(crew1.getTeamWarrior(indexWarriorTeam1).getDamage())).append(" единиц урона!")
                    .append("\n");
            notifyObserver();
        }
    }

    //возвращает true если только в одном из отрядов не осталось живых бойцов.
    private boolean isAnyLoose(Squad crew1, Squad crew2) {
        return (!crew1.hasAliveWarriors()) ^ (!crew2.hasAliveWarriors());
    }

    private void winnerIs(Squad crew1, Squad crew2) {
        String winner;
        String looser;

        if (crew1.hasAliveWarriors()) {
            winner = crew1.getName();
            looser = crew2.getName();
        } else {
            winner = crew2.getName();
            looser = crew1.getName();
        }

        infoMessage.append("Отряд ").append(winner).append(" победил, уничтожив отряд ").append(looser).append("\n");
        notifyObserver();
    }


    @Override
    public boolean isTeamsNotEmpty() {
        return ((team1.size() > 0) && (team2.size() > 0));
    }

    @Override
    public void initNameTeams(String team1Name, String team2Name) {
        if (!team1Name.equals("")) {
            this.team1Name = team1Name;
            infoMessage.append("Название первого отряда: ").append(this.team1Name).append("\n");
            notifyObserver();
        } else {
            this.team1Name = "England";
            infoMessage.append("Ничего не введено, указано название первого отряда по-умолчанию - England").append("\n");
            notifyObserver();
        }

        if ((!team2Name.equals("")) && (!team2Name.equals(this.team1Name))) {
            this.team2Name = team2Name;
            infoMessage.append("Название второго отряда: ").append(this.team2Name).append("\n");
            notifyObserver();
        } else {
            this.team2Name = "France";
            infoMessage.append("Ничего не введено, либо указано имя первого отряда. Присвоено название второго отряда по-умолчанию - France")
                    .append("\n");
            notifyObserver();
        }
    }

    @Override
    public void initNameAndTypeWarriors(String nameWarriorArg, int indexTeamArg, int indexTypeWarriorArg) {
        List<Warrior> currentTeam = null;
        String currentTeamName = "";
        String currentTypeWarrior = "";
        String nameWarrior = nameWarriorArg;

        if (nameWarrior.equals(""))  nameWarrior = getRandomNameWarrior();

        switch (indexTeamArg) {
            case 0:
                currentTeam = team1;
                currentTeamName = team1Name;
                break;
            case 1:
                currentTeam = team2;
                currentTeamName = team2Name;
                break;
        }

        switch (indexTypeWarriorArg) {
            case 0:
                currentTeam.add(new Viking(nameWarrior, currentTeamName));
                currentTypeWarrior = "Viking";
                break;
            case 1:
                currentTeam.add(new Archer(nameWarrior, currentTeamName));
                currentTypeWarrior = "Archer";
                break;
            case 2:
                currentTeam.add(new Barbarian(nameWarrior, currentTeamName));
                currentTypeWarrior = "Barbarian";
                break;
        }

        switch (indexTeamArg) {
            case 0:
                team1WarriorNameAndType.append(nameWarrior).append(" ").append(currentTypeWarrior).append("\n");
                notifyObserver();
                break;
            case 1:
                team2WarriorNameAndType.append(nameWarrior).append(" ").append(currentTypeWarrior).append("\n");
                notifyObserver();
                break;
        }
    }
}