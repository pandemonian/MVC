package view;

import controller.FightController;
import model.FightModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gubanov Pavel on 19.12.16.
 */
public class Gui extends JFrame implements FightObserver {

    private FightController controller;
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

    public Gui(FightController controller, FightModel model) {
        super("Приложение \"Битва\"");
        this.controller = controller;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        initComponents();
        addListeners();
        model.registerObserver(this);
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
        comboBoxTypeWarrior = new JComboBox<>(new String[] {"Viking", "Archer", "Barbarian"});
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
            controller.initNameTeams(fieldFirstNameTeam.getText(), fieldSecondNameTeam.getText());
            buttonSetTeamNames.setEnabled(false);
            buttonAddWarrior.setEnabled(true);
        });

        buttonAddWarrior.addActionListener(e -> {
            controller.initNameAndTypeWarriors(fieldNameWarrior.getText(), comboBoxTeam.getSelectedIndex(),
                    comboBoxTypeWarrior.getSelectedIndex());
            if (controller.isTeamsNotEmpty()) {
                buttonStartFight.setEnabled(true);
            }
        });

        buttonStartFight.addActionListener(e -> {
            controller.startBattle();
            buttonAddWarrior.setEnabled(false);
            buttonStartFight.setEnabled(false);
        });
    }

    private String getFieldFirstNameTeam() {
        return fieldFirstNameTeam.getText();
    }

    private String getFieldSecondNameTeam() {
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


    @Override
    public void updateView(StringBuilder msg, StringBuilder team1WarriorName, StringBuilder team2WarriorName) {
        log.append(msg.toString());
        fieldFirstTeamWarriorList.append(team1WarriorName.toString());
        fieldSecondTeamWarriorList.append(team2WarriorName.toString());
    }
}
