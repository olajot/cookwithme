package view;

import util.DBReader;
import util.DBWriter;
import util.MySQLConnection;
import util.Utils;

import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.Connection;

public class ManageRecipesScreen extends JFrame {

    private JPanel mainPanel;
    private JButton closeButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable recipesTable;
    private JPanel upPanel;
    private JPanel downPanel;
    private JPanel middlePanel;
    private JButton printButton;
    DBReader dbReader = new DBReader();
    DBWriter dbWriter = new DBWriter();

    public ManageRecipesScreen() {

        init();
        dbReader.showTableContent(recipesTable);
        recipesTable.setRowHeight(35);
        recipesTable.changeSelection(0, 0, false, false);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = recipesTable.getSelectedRow();
                int recipeId = Integer.parseInt((String) recipesTable.getValueAt(selRow, 0));

                if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    deleteRecipeFromDB(recipeId);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = recipesTable.getSelectedRow();
                int recipeId = Integer.parseInt((String) recipesTable.getValueAt(selRow, 0));

                EditRecipeScreen editRecipeScreen = new EditRecipeScreen(recipeId);
                editRecipeScreen.setVisible(true);
            }
        });

        upPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dbReader.showTableContent(recipesTable);
            }
        });
        downPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dbReader.showTableContent(recipesTable);
            }
        });

        middlePanel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                dbReader.showTableContent(recipesTable);
            }
        });
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                        try {
                            Utils.printComponent(mainPanel);
                        } catch (PrinterException ex) {
                            ex.printStackTrace();
                        }
                    }
        });
    }

    private void deleteRecipeFromDB(int recipeId) {
        Connection con = MySQLConnection.getConnection();

        String rmtSQL = "DELETE FROM recipeMealType WHERE recipeId = " + recipeId;
        dbWriter.performUpdate(rmtSQL);

        String lrSQL = "DELETE FROM labelledRecipe WHERE recipeId = " + recipeId;
        dbWriter.performUpdate(lrSQL);

        String deleteSQL = "DELETE FROM recipe WHERE id = " + recipeId;
        dbWriter.performUpdate(deleteSQL);

        dbReader.showTableContent(recipesTable);
    }

    public void init() {

        setContentPane(mainPanel);
        setTitle("Cook With Me - Manage Recipes Screen");
        pack();
        setSize(1300, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);
    }



}
