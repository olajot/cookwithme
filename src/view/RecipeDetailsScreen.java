package view;

import util.MySQLConnection;
import util.Utils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeDetailsScreen extends JFrame {
    private JPanel mainPanel;
    private JLabel titleField;
    private JButton printButton;
    private JButton closeButton;
    private JLabel preparationTimeLabel;
    private JLabel preparationTimeField;
    private JLabel stepsDescLabel;
    private JPanel lowerPanel;
    private JScrollPane scrollPane;
    private JLabel stepsDescField;
    private JTextArea ingredientsDescField;
    private JLabel ingredientsDescLabel;

    public RecipeDetailsScreen(String title) {

        init();
        int recipeId = getRecipeIdFromDB(title);
        getRecipeDetailsFromDB(recipeId);


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

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void getRecipeDetailsFromDB(int recipeId) {
        Connection con = MySQLConnection.getConnection();
        String selectSQL = "SELECT title, ingredientsDesc, stepsDesc, preparationTime FROM recipe WHERE id = " + recipeId;

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                titleField.setText(rs.getString("title"));
                preparationTimeField.setText(rs.getString("preparationTime"));
                ingredientsDescField.setText(rs.getString("ingredientsDesc"));

                stepsDescField.setText("<html><p>" + rs.getString("stepsDesc") + "</p></html>");
                //stepsDescField.setText("<html>"+ myString +"</html>");
                // "<html><p>A lot of text to be wrapped</p></html>"
                JLabel labelBeingUsed = stepsDescField;
                View view = (View) labelBeingUsed.getClientProperty(BasicHTML.propertyKey);
                view.setSize(scrollPane.getWidth(), 0.0f);
                float w = view.getPreferredSpan(View.X_AXIS);
                float h = view.getPreferredSpan(View.Y_AXIS);
                labelBeingUsed.setSize((int) w, (int) h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int getRecipeIdFromDB(String title) {

        Connection con = MySQLConnection.getConnection();
        String selectSQL = "SELECT id FROM recipe WHERE title = '" + title + "'";
        int recipeId = -1;

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);
            while (rs.next()) {
                recipeId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeId;
    }

    public void init() {
        printButton.setBackground(new Color(231, 207, 188));
        closeButton.setBackground(new Color(231, 207, 188));
        mainPanel.setBackground(new Color(244, 235, 217));
        setContentPane(mainPanel);
        setTitle("Cook With Me - Recipe Details Screen");
        pack();
        setSize(1300, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);
    }


}

