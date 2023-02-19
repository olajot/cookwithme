package view;

import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class AdminLoginScreen extends JFrame {
    private JPanel mainPanel;
    private JPasswordField passwordField;
    private JPanel upperPanel;
    private JPanel lowerPanel;
    private JButton loginButton;
    private JButton closeButton;

    public AdminLoginScreen() {

        init();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkIfPasswordValid()) {
                    openAdminOptionsScreen();
                }
                dispose();
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 10) {

                    if (checkIfPasswordValid()) {
                        openAdminOptionsScreen();
                    }
                    dispose();
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void init() {
        setContentPane(mainPanel);
        setTitle("Cook With Me - Admin Login Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setSize(1300, 1000);
        setLocationRelativeTo(null);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);


    }

    public boolean checkIfPasswordValid() {
        String passwordAdmin = passwordField.getText();
        if (Objects.equals(passwordAdmin, "admin123")) {
            return true;

        } else {
            JOptionPane.showMessageDialog(mainPanel,
                    "Your password is incorrect!",
                    "Error",
                    JOptionPane.PLAIN_MESSAGE);
            return false;
        }
    }

    private void openAdminOptionsScreen() {
        AdminOptionsScreen adminOptionsScreen = new AdminOptionsScreen();
        adminOptionsScreen.setVisible(true);
    }
}
