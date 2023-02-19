package view;

import model.DBReader;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class FiltersScreen extends JFrame {

    private JComboBox mealTypeComboBox;
    private JButton searchButton;
    private JPanel mainPanel;
    private JButton cancelButton;
    private JList labelList;
    private JComboBox timeComboBox;
    private JPanel firstPanel;
    private JPanel lowerPanel;
    private DefaultListModel listModel;

    public FiltersScreen() {

        init();
        DBReader.getLabelsFromDB(listModel, labelList);
        DBReader.getAllMealTypesFromDB(mealTypeComboBox);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = createSQLWithCriteriaFromUI();
                RecipeListScreen recipeListScreen = new RecipeListScreen(sql);
                recipeListScreen.setVisible(true);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private String createSQLWithCriteriaFromUI() {

        String selectedMealType = getMealTypeFromUI();
        int[] selectedPreparationTime = getPreparationTimeFromUI();
        List<String> selectedLabels = getLabelsFromUI();

        String sql = "SELECT  DISTINCT  r.title , r.preparationTime , mt.name AS mealType FROM recipe r" +
                " JOIN recipeMealType rmt ON rmt.recipeId = r.id " +
                " JOIN mealType mt ON mt.id  = rmt.mealTypeId " +
                " JOIN labelledRecipe lr ON lr.recipeId = r.id " +
                " JOIN label l ON l.id = lr.labelId " +
                " WHERE r.hidden = 0 ";

        if (selectedPreparationTime[0] != -1 || selectedPreparationTime[1] != 1) {
            sql += " AND r.preparationTime between " + selectedPreparationTime[0] + " AND " + selectedPreparationTime[1];
        }

        if (!selectedMealType.equals("choose all")) {
            sql += " AND mt.name = '" + selectedMealType + "'";
        }

        if (selectedLabels != null) {
            sql += " AND l.name in ('" + Arrays.toString(selectedLabels.toArray())
                    .replace("[", "")
                    .replace("]", "")
                    .replace(", ", "', '") + "')";
        }
        return sql;
    }

    private List<String> getLabelsFromUI() {
        List<String> labelSelectedValues = labelList.getSelectedValuesList();
        return labelSelectedValues;
    }

    private int[] getPreparationTimeFromUI() {
        int preparationTimeSelectedIndex = timeComboBox.getSelectedIndex();
        int[] preparationTimeRange = new int[2];

        switch (preparationTimeSelectedIndex) {
            case 1:
                preparationTimeRange[0] = 1;
                preparationTimeRange[1] = 30;
                break;
            case 2:
                preparationTimeRange[0] = 31;
                preparationTimeRange[1] = 60;
                break;
            case 3:
                preparationTimeRange[0] = 61;
                preparationTimeRange[1] = 90;
                break;
            case 4:
                preparationTimeRange[0] = 91;
                preparationTimeRange[1] = 1000;
                break;
            default:
                preparationTimeRange[0] = -1;
                preparationTimeRange[1] = 1001;
                break;
        }
        return preparationTimeRange;
    }

    private String getMealTypeFromUI() {
        String mealTypeSelectedVal = (String) mealTypeComboBox.getSelectedItem();
        return mealTypeSelectedVal;
    }

    public void init() {
        setContentPane(mainPanel);
        setTitle("Cook With Me - Filters Screen");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setSize(1300, 1000);
        setLocationRelativeTo(null);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);

    }
}
