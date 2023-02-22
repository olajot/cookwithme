package view;

import util.DBReader;
import util.DBWriter;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRecipeScreen extends JFrame {
    JTextField addTitleTextField;
    private JTextArea addStepsDescTextArea;
    private JPanel mainPanel;
    private JTextArea addIngredientsDescTextArea;
    private JTextField addLabelTextField;
    private JButton addLabelButton;
    private JButton addRecipeButton;
    private JLabel addStepsDescLabel;
    private JLabel addIngredientsDescLabel;
    private JTextField addPreparationTimeTextField;
    private JCheckBox hiddenCheckBox;
    private JComboBox addMealTypeComboBox;
    private JButton closeButton;
    private JButton saveButton;

    private ArrayList<String> enteredLabels = new ArrayList<String>();
    private int recipeId = -1;

    public AddRecipeScreen() {

        init();

        DBReader dbReader = new DBReader();
        dbReader.getMealTypesFromDB(addMealTypeComboBox);

        DBWriter dbWriter = new DBWriter();
        saveButton.setEnabled(false);

        addRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkMandatoryFields()) {
                    if (checkPreparationTime()) {
                        if (checkFieldsLength()) {
                            if (checkPreparationTimePositive()) {

                                String insertSQL = getFieldsFromUI();

                                recipeId = dbWriter.addRecipeToDB(insertSQL);
                                dbWriter.assignMealTypeToRecipe(addMealTypeComboBox, recipeId);

                                disableFieldsAfterRecipeInsert();
                                addLabelButton.setEnabled(true);
                                addLabelTextField.setEnabled(true);
                                saveButton.setEnabled(true);
                            } else {
                                JOptionPane.showMessageDialog(mainPanel,
                                        "Preparation time should be a positive integer.",
                                        "Error",
                                        JOptionPane.PLAIN_MESSAGE);
                            }
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


        addLabelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredLabels.add(addLabelTextField.getText());
                addLabelTextField.setText("");
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbWriter.assignLabelsToRecipe(recipeId, enteredLabels);
                dispose();
            }
        });
    }

    private boolean checkPreparationTimePositive() {
        int prepTime = Integer.parseInt(addPreparationTimeTextField.getText());
        return prepTime > 0;
    }

    //overloaded constructor
    public AddRecipeScreen(String[] parsedRecipe) {

        //Constructor Chaining
        //calling default constructor
        this();
        addTitleTextField.setText(parsedRecipe[0]);
        addIngredientsDescTextArea.setText(parsedRecipe[1]);
        addStepsDescTextArea.setText(parsedRecipe[2]);

    }

    private boolean checkMandatoryFields() {
        return !addTitleTextField.getText().equals("") && !addIngredientsDescTextArea.getText().equals("");
    }

    private boolean checkFieldsLength() {

        return addIngredientsDescTextArea.getText().length() <= 2000 && addStepsDescTextArea.getText().length() <= 2000;
    }

    private boolean checkPreparationTime() {
        if (!addPreparationTimeTextField.getText().isEmpty()) {
            try {
                Integer.parseInt(addPreparationTimeTextField.getText());

            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;

    }

    private void disableFieldsAfterRecipeInsert() {
        addTitleTextField.setEnabled(false);
        addIngredientsDescTextArea.setEnabled(false);
        addStepsDescTextArea.setEnabled(false);
        addPreparationTimeTextField.setEnabled(false);
        addMealTypeComboBox.setEnabled(false);
        hiddenCheckBox.setEnabled(false);
        addRecipeButton.setEnabled(false);
    }

    private String getFieldsFromUI() {

        String insertSQL = "INSERT INTO recipe (title, ingredientsDesc, stepsDesc, preparationTime, hidden) " +
                " VALUES ('" + addTitleTextField.getText() + "','" + addIngredientsDescTextArea.getText() + "','"
                + addStepsDescTextArea.getText() + "',";

        if (addPreparationTimeTextField.getText().equals("")) {
            insertSQL += "0, ";
        } else {
            insertSQL += addPreparationTimeTextField.getText() + ", ";
        }

        if (hiddenCheckBox.isSelected()) {
            insertSQL += "true)";
        } else {
            insertSQL += "false)";
        }

        return insertSQL;
    }

    public void init() {

        setContentPane(mainPanel);
        setTitle("Cook With Me - Add Recipe Screen");
        pack();
        setSize(1300, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addLabelButton.setEnabled(false);
        addLabelTextField.setEnabled(false);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);
    }

}
