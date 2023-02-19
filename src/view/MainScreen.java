package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {
    private JButton userButton;
    private JButton adminButton;
    private JLabel mainLabel;
    private JPanel mainPanel;
    private JButton exitButton;


    public MainScreen() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        userButton.setBackground(new Color(231, 207, 188));
        adminButton.setBackground(new Color(231, 207, 188));
        mainPanel.setBackground(new Color(244, 235, 217));

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltersScreen filtersScreen = new FiltersScreen();
                filtersScreen.setVisible(true);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLoginScreen adminLoginScreen = new AdminLoginScreen();
                adminLoginScreen.setVisible(true);
            }
        });
    }

    public static void showWindow() {
        JFrame frame = new JFrame("Cook With Me - Main Screen");
        frame.setContentPane(new MainScreen().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1300, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

