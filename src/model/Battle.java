package model;

import model.Warriors.Warrior;
import view.FightObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Battle implements FightModel {

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
        //Gui.setLog("Отряд \"", winner, "\" победил, ", "уничтожив отряд \"", looser, "\"");
    }

    void startBattle() {
        Squad squad1 = new Squad(init.getTeam1Name(), init.getTeam1());
        Squad squad2 = new Squad(init.getTeam2Name(), init.getTeam2());

        msg.append("Битва началась!!! ").append(DataHelper.getFormattedStartDate());
        notifyObserver();
        //Gui.setLog("Битва началась!!! ", DataHelper.getFormattedStartDate());

        startFight(squad1, squad2);

        msg.append("Бой продолжался: ").append(DataHelper.getFormattedDiff());
        notifyObserver();
        //Gui.setLog("Бой продолжался: ", DataHelper.getFormattedDiff());
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

            /*Gui.setLog(crew1.getTeamWarrior(indexWarriorTeam1).getClass().getSimpleName(), " ", crew1.getTeamWarrior(indexWarriorTeam1).getName(),
                    " из отряда \"", crew1.getName(), "\" нанёс ", crew2.getTeamWarrior(indexWarriorTeam2).getClass().getSimpleName(),
                    "`у ", crew2.getTeamWarrior(indexWarriorTeam2).getName(), " из отряда \"", crew2.getName(), "\" ",
                    String.valueOf(crew1.getTeamWarrior(indexWarriorTeam1).getDamage()), " единиц урона!");*/

        }
    }
}
