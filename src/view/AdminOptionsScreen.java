package view;

import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminOptionsScreen extends JFrame {
    private JButton addRecipeButton;
    private JButton manageRecipeButton;
    private JButton logOutButton;
    private JPanel mainPanel;
    private JButton importRecipeButton;

    public AdminOptionsScreen() {

        init();

        addRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRecipeScreen addRecipeScreen = new AddRecipeScreen();
                addRecipeScreen.setVisible(true);
            }
        });

        manageRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageRecipesScreen manageRecipesScreen = new ManageRecipesScreen();
                manageRecipesScreen.setVisible(true);
                dispose();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        importRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImportScreen importScreen = new ImportScreen();
                importScreen.setVisible(true);
                dispose();
            }
        });
    }

    public void init() {

        setContentPane(mainPanel);
        setTitle("Cook With Me - Admin Options Screen");
        pack();
        setSize(1300, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);
    }
}
