package view;

import util.DBReader;
import util.MySQLConnection;
import util.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RecipeListScreen extends JFrame {
    private JLabel criteriaCaptionLabel;
    private JScrollPane recipeScrollPane;
    private JTable recipeTable;
    private JButton openButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JPanel upperestPanel;
    private JPanel lowerPanel;
    private JPanel upPanel;

    public RecipeListScreen(String sql) {

        init();
        DBReader.showTableContent(recipeTable,sql);
        recipeTable.setRowHeight(35);
        recipeTable.changeSelection(0, 0, false, false);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = recipeTable.getSelectedRow();
                String recipeTitle = (String) recipeTable.getValueAt(selRow, 0);

                RecipeDetailsScreen recipeDetailsScreen = new RecipeDetailsScreen(recipeTitle);
                recipeDetailsScreen.setVisible(true);

            }
        });
    }

    public void init() {
        setContentPane(mainPanel);
        setTitle("Cook With Me - Recipe List Screen");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1300, 1000);
        setLocationRelativeTo(null);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);
    }
}
