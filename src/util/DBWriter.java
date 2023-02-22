package util;

import util.DBReader;
import util.MySQLConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DBWriter {

    DBReader dbReader = new DBReader();

    public void assignLabelsToRecipe(int recipeId, ArrayList<String> enteredLabels) {
        enteredLabels.forEach(enteredLabel -> {
            if (checkIfLabelExists(enteredLabel)) {
                assignLabel(enteredLabel, recipeId);
            } else {
                addLabelToDB(enteredLabel);
                assignLabel(enteredLabel, recipeId);
            }
        });
    }

    public void addLabelToDB(String enteredLabel) {
        Connection con = MySQLConnection.getConnection();
        String insertSQL = "INSERT INTO label(name) VALUES ('" + enteredLabel + "')";

        try {
            Statement s = con.createStatement();
            s.executeUpdate(insertSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfLabelExists(String enteredLabel) {
        Connection con = MySQLConnection.getConnection();
        String selectSQL = "SELECT count(id) FROM label WHERE name = '" + enteredLabel + "'";

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                return (rs.getInt("count(id)") > 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    private void assignLabel(String enteredLabel, int recipeId) {

        Connection con = MySQLConnection.getConnection();

        String labSQL = "SELECT id FROM label WHERE name = '" + enteredLabel + "'";
        int labelId = -1;

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(labSQL);
            while (rs.next()) {
                labelId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO labelledRecipe (recipeId, labelId) VALUES (" + recipeId + ", " + labelId + ")";

        try {
            Statement s = con.createStatement();
            s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void unassignLabel(int recipeId, String selectedLabel) {

        Connection con = MySQLConnection.getConnection();

        int labelId = dbReader.getLabelId(selectedLabel);
        String sql = "DELETE FROM labelledRecipe WHERE recipeId = " + recipeId + " AND labelId = " + labelId;

        try {
            Statement s = con.createStatement();
            s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignMealTypeToRecipe(JComboBox component, int recipeId) {

        String mealTypeSelectedVal = (String) component.getSelectedItem();
        Connection con = MySQLConnection.getConnection();

        int mealTypeId = dbReader.getMealTypeId(mealTypeSelectedVal);

        String sql = "INSERT INTO recipeMealType (recipeId, mealTypeId) VALUES (" + recipeId + ", " + mealTypeId + ")";

        try {
            Statement s = con.createStatement();
            s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addRecipeToDB(String insertSQL) {
        Connection con = MySQLConnection.getConnection();

        int recipePK = -1;
        try {
            String[] columnNames = new String[]{"id"};

            PreparedStatement s = con.prepareStatement(insertSQL, columnNames);

            if (s.executeUpdate() > 0) {
                // Returns the auto-generated key from executing a Statement
                ResultSet generatedKeys = s.getGeneratedKeys();
                if (generatedKeys.next()) {
                    recipePK = generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePK;
    }

    public void performUpdate(String sql) {
        Connection con = MySQLConnection.getConnection();

        try {
            Statement s = con.createStatement();
            s.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
