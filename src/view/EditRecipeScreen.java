package view;

import model.DBReader;
import model.DBWriter;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class EditRecipeScreen extends JFrame {

    private JTextField editTitleTextField;
    private JCheckBox hiddenCheckBox;
    private JTextArea editIngredientsDescTextArea;
    private JLabel editIngredientsDescLabel;
    private JTextField editLabelTextField;
    private JButton addLabelButton;
    private JTextArea editStepsDescTextArea;
    private JLabel editStepsDescLabel;
    private JComboBox editMealTypeComboBox;
    private JTextField editPreparationTimeTextField;
    private JButton updateButton;
    private JButton closeButton;
    private JPanel mainPanel;
    private JList existingLabelsList;
    private JButton removeButton;

    private ArrayList<String> enteredLabels = new ArrayList<String>();
    DBReader dbReader = new DBReader();
    DBWriter dbWriter = new DBWriter();

    public EditRecipeScreen(int recipeId) {

        init();

        dbReader.getMealTypesFromDB(editMealTypeComboBox);
        dbReader.refreshExistingLabels(existingLabelsList, recipeId);

        HashMap<String, String> recipeDetails = dbReader.getRecipeDetailsFromDB(recipeId);
        editTitleTextField.setText(recipeDetails.get("title"));
        hiddenCheckBox.setSelected(Boolean.parseBoolean(recipeDetails.get("hidden")));
        editIngredientsDescTextArea.setText(recipeDetails.get("ingredientsDesc"));
        editStepsDescTextArea.setText(recipeDetails.get("stepsDesc"));
        editPreparationTimeTextField.setText(recipeDetails.get("preparationTime"));
        editMealTypeComboBox.setSelectedItem(recipeDetails.get("mealType"));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addLabelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredLabels.add(editLabelTextField.getText());
                dbWriter.assignLabelsToRecipe(recipeId, enteredLabels);
                dbReader.refreshExistingLabels(existingLabelsList, recipeId);
                editLabelTextField.setText("");
                enteredLabels.clear();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLabel = String.valueOf(existingLabelsList.getSelectedValue());
                dbWriter.unassignLabel(recipeId, selectedLabel);
                dbReader.refreshExistingLabels(existingLabelsList, recipeId);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkMandatoryFields()) {
                    if (checkPreparationTime()) {
                        if (checkFieldsLength()) {
                            String sql = updateRecipeSQLFromFieldsOnUI(recipeId);
                            dbWriter.performUpdate(sql);

                            String mtSQL = updateMealTypeRecipeFromFieldOnUI(recipeId);
                            dbWriter.performUpdate(mtSQL);

                            JOptionPane.showMessageDialog(mainPanel,
                                    "Recipe has been updated.",
                                    "Update performed",
                                    JOptionPane.PLAIN_MESSAGE);

                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(mainPanel,
                                    "Max length of ingredients and instructions is 2000.",
                                    "Error",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainPanel,
                                "Please enter a valid number of minutes.",
                                "Error",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel,
                            "Please enter at least Recipe Title and Recipe Ingredients.",
                            "Mandatory fields are empty",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }


    private void init() {
        setContentPane(mainPanel);
        setTitle("Cook With Me - Edit Recipe Screen");
        pack();
        setSize(1300, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);
    }

    private String updateRecipeSQLFromFieldsOnUI(int recipeId) {

        String updateSQL = "UPDATE recipe " +
                "SET title = '" + editTitleTextField.getText() + "', ingredientsDesc = '" + editIngredientsDescTextArea.getText() + "', stepsDesc = '"
                + editStepsDescTextArea.getText() + "', preparationTime = " + editPreparationTimeTextField.getText() + ", hidden = " + hiddenCheckBox.isSelected()
                + " WHERE id = " + recipeId;

        return updateSQL;
    }

    private String updateMealTypeRecipeFromFieldOnUI(int recipeId) {

        String selectedMealType = (String) editMealTypeComboBox.getSelectedItem();
        int mealTypeId = dbReader.getMealTypeId(selectedMealType);

        String updateSQL = "UPDATE recipeMealType SET mealTypeId = " + mealTypeId + " WHERE recipeId = " + recipeId;

        return updateSQL;
    }

    private boolean checkMandatoryFields() {
        return !editTitleTextField.getText().equals("") && !editIngredientsDescTextArea.getText().equals("");
    }

    private boolean checkPreparationTime() {
        if (!editPreparationTimeTextField.getText().isEmpty()) {
            try {
                Integer.parseInt(editPreparationTimeTextField.getText());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;

    }

    private boolean checkFieldsLength() {
        return editIngredientsDescTextArea.getText().length() <= 2000 && editStepsDescTextArea.getText().length() <= 2000;
    }
}
