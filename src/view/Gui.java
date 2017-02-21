package view;

import controller.FightController;
import model.Battle;
import model.FightModel;
import model.Singleton;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by Gubanov Pavel on 19.12.16.
 */
public class Gui extends JFrame implements FightObserver {

    private FightController controller;
    private FightModel model;
    private JButton buttonSetTeamNames;
    private JButton buttonAddWarrior;
    private JButton buttonStartFight;
    private static JTextArea fieldFirstNameTeam;
    private static JTextArea fieldSecondNameTeam;
    private static JTextArea fieldNameWarrior;
    private static TextArea fieldFirstTeamWarriorList;
    private static TextArea fieldSecondTeamWarriorList;
    private static JComboBox<String> comboBoxTeam;
    private static JComboBox<String> comboBoxTypeWarrior;
    private static TextArea log;
    private static StringBuilder strBldrFirstWarriorList = new StringBuilder();
    private static StringBuilder strBldrSecondWarriorList = new StringBuilder();
    private static StringBuilder strBldrLog = new StringBuilder();

    Gui(FightModel model) {
        super("Приложение \"Битва\"");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        initComponents();
        addListeners();
        this.model = model;
    }

    private void initComponents() {

        buttonSetTeamNames = new JButton("Задать имена командам");
        buttonStartFight = new JButton("Начать битву");
        JLabel labelWarriorName = new JLabel("Имя война");
        buttonAddWarrior = new JButton("Добавить война в отряд");
        fieldFirstNameTeam = new JTextArea("Название первой команды", 2, 20);
        fieldSecondNameTeam = new JTextArea("Название второй команды", 2, 20);
        fieldNameWarrior = new JTextArea(1, 10);
        fieldFirstTeamWarriorList = new TextArea(10, 15);
        fieldSecondTeamWarriorList = new TextArea(10, 15);
        comboBoxTeam = new JComboBox<>(new String[] {"Первая команда", "Вторая команда"});
        comboBoxTypeWarrior = new JComboBox<>(new String[] {"model.Viking", "model.Archer", "model.Barbarian"});
        log = new TextArea(20, 90);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.add(fieldFirstNameTeam);
        topPanel.add(buttonSetTeamNames);
        topPanel.add(fieldSecondNameTeam);

        JPanel panelStartFight = new JPanel(new GridLayout(2, 1, 5, 40));
        panelStartFight.add(comboBoxTeam);
        panelStartFight.add(buttonStartFight);
            buttonStartFight.setEnabled(false);

        JPanel panelWarriorInfo = new JPanel(new GridLayout(4, 1, 5, 20));
        topPanel.setBackground(Color.LIGHT_GRAY);
        panelWarriorInfo.add(labelWarriorName);
        panelWarriorInfo.add(fieldNameWarrior);
        panelWarriorInfo.add(comboBoxTypeWarrior);
        panelWarriorInfo.add(buttonAddWarrior);
            buttonAddWarrior.setEnabled(false);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 22, 10));
        topPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.add(panelStartFight);
        centerPanel.add(panelWarriorInfo);
        centerPanel.add(fieldFirstTeamWarriorList);
            fieldFirstTeamWarriorList.setEditable(false);
        centerPanel.add(fieldSecondTeamWarriorList);
            fieldSecondTeamWarriorList.setEditable(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(log);
            log.setEditable(false);

        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        pack();
    }

    private void addListeners() {
        buttonSetTeamNames.addActionListener(e -> {
            //Singleton.INSTANCE.getInit().initNameTeams();
            initNameTeams();
            buttonSetTeamNames.setEnabled(false);
            buttonAddWarrior.setEnabled(true);
        });

        buttonAddWarrior.addActionListener(e -> {
            Singleton.INSTANCE.getInit().initNameAndTypeWarriors();
            if (Singleton.INSTANCE.getInit().isTeamsNotEmpty()) buttonStartFight.setEnabled(true);
        });

        buttonStartFight.addActionListener(e -> {
            new Battle().startBattle();
            buttonAddWarrior.setEnabled(false);
            buttonStartFight.setEnabled(false);

        });
    }

    static String getFieldFirstNameTeam() {
        return fieldFirstNameTeam.getText();
    }

    static String getFieldSecondNameTeam() {
        return fieldSecondNameTeam.getText();
    }

    static int getComboBoxTeam() {
        return comboBoxTeam.getSelectedIndex();
    }

    static int getComboBoxTypeWarrior() {
        return comboBoxTypeWarrior.getSelectedIndex();
    }

    static String getFieldNameWarrior() {
        return fieldNameWarrior.getText();
    }

    static void setFieldFirstTeamWarriorList(String text1, String text2) {
        strBldrFirstWarriorList.append(text1).append(" ").append(text2).append("\n");
        fieldFirstTeamWarriorList.setText(strBldrFirstWarriorList.toString());
    }

    static void setFieldSecondTeamWarriorList(String text1, String text2) {
        strBldrSecondWarriorList.append(text1).append(" ").append(text2).append("\n");
        fieldSecondTeamWarriorList.setText(strBldrSecondWarriorList.toString());
    }

    /*static void setLog(String ... arg) {
        Arrays.stream(arg)
                .forEach(t-> strBldrLog.append(t));
        strBldrLog.append("\n");
        log.setText(strBldrLog.toString());
    }*/

    /////  verify methods of Initializer  /////////

    void initNameTeams() {
        String inputStr;

        inputStr = getFieldFirstNameTeam();
        if (!inputStr.equals("")) {

            controller.relayTeam1Name(inputStr);
            log.setText("Название первого отряда: " + inputStr);
        }
        else {
            log.setText("Ничего не введено, указано название первого отряда по-умолчанию - England");
            controller.relayTeam1Name("England");
        }

        inputStr = getFieldSecondNameTeam();
        if ((!inputStr.equals("")) && (!inputStr.equals(model.getTeam1Name()))) {
            log.setText("Название второго отряда: " + inputStr);
        }
        else {
            log.setText("Ничего не введено, либо указано имя первого отряда. Присвоено название второго " +
                    "отряда по-умолчанию - France");
            controller.relayTeam2Name("France");
        }
    }

    /////  verify methods of Initializer  /////////

    @Override
    public void updateView(StringBuilder msg) {
        log.setText(msg.toString());
    }
}
