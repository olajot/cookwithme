package model;

import util.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBReader {

    public void refreshExistingLabels(JList component, int recipeId) {

        Connection con = MySQLConnection.getConnection();
        getAssignedLabelsFromDB(component, recipeId);
    }

    public static void getAssignedLabelsFromDB(JList component, int recipeId) {

        Connection con = MySQLConnection.getConnection();
        String selectSQL = "SELECT l.name FROM labelledRecipe lr " +
                "JOIN label l ON l.id = lr.labelId " +
                "WHERE lr.recipeId = " + recipeId;

        DefaultListModel listModel = new DefaultListModel();

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                listModel.addElement(rs.getString("name"));

            }
            component.setModel(listModel);
            component.setVisibleRowCount(5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getLabelsFromDB(DefaultListModel listModel, JList component) {

        Connection con = MySQLConnection.getConnection();
        String selectSQL = "SELECT name FROM label ORDER BY name ASC";

        listModel = new DefaultListModel();

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                listModel.addElement(rs.getString("name"));

            }
            component.setModel(listModel);
            component.setSelectedIndex(0);
            component.setVisibleRowCount(5);
            component.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAllMealTypesFromDB(JComboBox component) {

        Connection con = MySQLConnection.getConnection();
        //for the purpose of filter screen
        String selectSQL = "(SELECT 'choose all' as name FROM DUAL) UNION ALL (SELECT name FROM mealType ORDER BY 1 ASC)";

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                component.addItem(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMealTypesFromDB(JComboBox component) {

        Connection con = MySQLConnection.getConnection();
        String selectSQL = "SELECT name FROM mealType ORDER BY 1 ASC";

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                component.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getRecipeDetailsFromDB(int recipeId) {

        Connection con = MySQLConnection.getConnection();
        HashMap<String, String> resultMap = new HashMap<>();

        String selectSQL = "SELECT id, title, ingredientsDesc, stepsDesc, preparationTime , hidden  FROM recipe WHERE id = " + recipeId;

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                resultMap.put("id", rs.getString("id"));
                resultMap.put("title", rs.getString("title"));

                if (rs.getBoolean("hidden")) {
                    resultMap.put("hidden", "true");
                }
                else {
                    resultMap.put("hidden", "false");
                }
                resultMap.put("ingredientsDesc", rs.getString("ingredientsDesc"));
                resultMap.put("stepsDesc", rs.getString("stepsDesc"));
                resultMap.put("preparationTime",rs.getString("preparationTime"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String mtSQL = "SELECT mt.name FROM recipeMealType rmt " +
                "JOIN mealType mt ON mt.id  = rmt.mealTypeId " +
                "WHERE rmt.recipeId = " + recipeId;

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(mtSQL);

            while (rs.next()) {
                resultMap.put("mealType",rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultMap;
    }

    public int getMealTypeId(String selectedMealType) {
        Connection con = MySQLConnection.getConnection();

        String selectSQL = "SELECT id FROM mealType WHERE name = '" + selectedMealType + "'";
        int mealTypeId = -1;

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                mealTypeId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealTypeId;
    }

    public int getLabelId(String selectedLabel) {
        Connection con = MySQLConnection.getConnection();

        String selectSQL = "SELECT id FROM label WHERE name = '" + selectedLabel + "'";
        int labelId = -1;

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            while (rs.next()) {
                labelId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return labelId;
    }

    public void showTableContent(JTable component) {

        Connection con = MySQLConnection.getConnection();
        String selectSQL = "SELECT id, title, ingredientsDesc, stepsDesc, preparationTime , hidden  FROM recipe";

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);

            String[] header = {"id", "title", "ingredients", "steps", "preparation time", "hidden"};
            DefaultTableModel dtm = new DefaultTableModel(header, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };

            component.setModel(dtm);
            Object[] row = new Object[6];
            while (rs.next()) {
                row[0] = rs.getString("id");
                row[1] = rs.getString("title");
                row[2] = rs.getString("ingredientsDesc");
                row[3] = rs.getString("stepsDesc");
                row[4] = rs.getString("preparationTime");
                row[5] = (rs.getBoolean("hidden") ? "yes" : "no");
                dtm.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
