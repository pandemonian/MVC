package model;

import model.Warriors.Warrior;
import view.FightObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
public class Battle implements FightModel {

    private FightObserver observer;
    private Initializer init;
    private Random random;
    private StringBuilder msg;

    private String team1Name;
    private String team2Name;
    private List<Warrior> team1 = new ArrayList<>();
    private List<Warrior> team2 = new ArrayList<>();

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public void setTeam1(List<Warrior> team1) {
        this.team1 = team1;
    }

    public void setTeam2(List<Warrior> team2) {
        this.team2 = team2;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public List<Warrior> getTeam1() {
        return team1;
    }

    public List<Warrior> getTeam2() {
        return team2;
    }


    Battle (Initializer init) {
        this.init = init;
        this.msg = new StringBuilder(0);
    }


    @Override
    public void registerObserver(FightObserver o) {
        observer = o;
    }

    @Override
    public void notifyObserver() {
        observer.updateView(msg);
        msg.delete(0, msg.length());
    }

    //возвращает true если только в одном из отрядов не осталось живых бойцов.
    private boolean isAnyLoose(Squad crew1, Squad crew2) {
        return (!crew1.hasAliveWarriors()) ^ (!crew2.hasAliveWarriors());
    }

    public boolean isTeamsNotEmpty() {
        return ((team1.size() > 0) && (team2.size() > 0));
    }

    private int getRandomIndexWarriorTeam(Squad crew) {
        while (true) {
            int indexWarriorTeam = random.nextInt(crew.getTeamSize());
            if (crew.getTeamWarrior(indexWarriorTeam).isAlive()) {
                return indexWarriorTeam;
            }
        }
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

        msg.append("Отряд ").append(winner).append(" победил, уничтожив отряд ").append(looser);
        notifyObserver();
    }

    void startBattle() {
        Squad squad1 = new Squad(init.getTeam1Name(), init.getTeam1());
        Squad squad2 = new Squad(init.getTeam2Name(), init.getTeam2());

        msg.append("Битва началась!!! ").append(DataHelper.getFormattedStartDate());
        notifyObserver();

        startFight(squad1, squad2);

        msg.append("Бой продолжался: ").append(DataHelper.getFormattedDiff());
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

            indexWarriorTeam1 = getRandomIndexWarriorTeam(crew1);
            indexWarriorTeam2 = getRandomIndexWarriorTeam(crew2);

            crew1.getTeamWarrior(indexWarriorTeam1).attackingUnit(crew2.getTeamWarrior(indexWarriorTeam2));

            msg.append(crew1.getTeamWarrior(indexWarriorTeam1).getClass().getSimpleName()).append(" ")
                    .append(crew1.getTeamWarrior(indexWarriorTeam1).getName()).append(" из отряда \"")
                    .append(crew1.getName()).append("\" нанёс ")
                    .append(crew2.getTeamWarrior(indexWarriorTeam2).getClass().getSimpleName())
                    .append("`у ").append(crew2.getTeamWarrior(indexWarriorTeam2).getName()).append(" из отряда \"")
                    .append(crew2.getName()).append("\" ").append("\" ")
                    .append(String.valueOf(crew1.getTeamWarrior(indexWarriorTeam1).getDamage())).append(" единиц урона!");
            notifyObserver();
        }
    }


    public void initNameTeams(String team1Name, String team2Name) {
        if (!team1Name.equals("")) {
            this.team1Name = team1Name;
            msg.append("Название первого отряда: ").append(this.team1Name);
            notifyObserver();
        } else {
            this.team1Name = "England";
            msg.append("Ничего не введено, указано название первого отряда по-умолчанию - England");
            notifyObserver();
        }

        if ((!team2Name.equals("")) && (!team2Name.equals(this.team1Name))) {
            this.team2Name = team2Name;
            msg.append("Название второго отряда: ").append(this.team2Name);
            notifyObserver();
        } else {
            this.team2Name = "France";
            msg.append("Ничего не введено, либо указано имя первого отряда. Присвоено название второго отряда по-умолчанию - France");
            notifyObserver();
        }
    }

    
}
