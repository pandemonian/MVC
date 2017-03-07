package view;

import controller.Controller;
import model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gubanov Pavel on 19.12.16.
 */
public class ViewImpl extends JFrame implements View {

    private Controller controller;
    private JButton buttonSetTeamNames;
    private JButton buttonAddWarrior;
    private JButton buttonStartFight;
    private JTextArea fieldFirstNameTeam;
    private JTextArea fieldSecondNameTeam;
    private JTextArea fieldNameWarrior;
    private TextArea fieldFirstTeamWarriorList;
    private TextArea fieldSecondTeamWarriorList;
    private JComboBox<String> comboBoxTeam;
    private JComboBox<String> comboBoxTypeWarrior;
    private TextArea log;

    public ViewImpl(Controller controller, Model model) {
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

        JLabel labelWarriorName = new JLabel("Имя война");
        buttonSetTeamNames = new JButton("Задать имена командам");
        buttonStartFight = new JButton("Начать битву");
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
            controller.initNameTeams(getNameFirstTeam(), getNameSecondTeam());
            buttonSetTeamNames.setEnabled(false);
            buttonAddWarrior.setEnabled(true);
        });

        buttonAddWarrior.addActionListener(e -> {
            controller.initNameAndTypeWarriors(getNameWarrior(), getTeamIndex(), getTypeWarriorIndex());
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

    private String getNameFirstTeam() {
        return fieldFirstNameTeam.getText();
    }

    private String getNameSecondTeam() {
        return fieldSecondNameTeam.getText();
    }

    private int getTeamIndex() {
        return comboBoxTeam.getSelectedIndex();
    }

    private int getTypeWarriorIndex() {
        return comboBoxTypeWarrior.getSelectedIndex();
    }

    private String getNameWarrior() {
        return fieldNameWarrior.getText();
    }

    @Override
    public void updateView(StringBuilder msg, StringBuilder team1WarriorName, StringBuilder team2WarriorName) {
        log.append(msg.toString());
        fieldFirstTeamWarriorList.append(team1WarriorName.toString());
        fieldSecondTeamWarriorList.append(team2WarriorName.toString());
    }
}
