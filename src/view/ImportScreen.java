package view;

import util.FileManager;
import util.ReadFile;
import util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ImportScreen extends JFrame {
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton importButton;
    private JButton browseButton;
    private JTextArea fileNameTextArea;
    private String fileName;

    public ImportScreen() {

        init();
        importButton.setEnabled(false);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFile readFile = new ReadFile();
                if (readFile.checkIfFileValid(fileName)) {

                    String[] parsedRecipe = readFile.processFile(fileName);
                    AddRecipeScreen addRecipeScreen = new AddRecipeScreen(parsedRecipe);
                    addRecipeScreen.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel,
                            "The file doesn't contain <title> or <ingredients> tags.",
                            "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileName = Objects.requireNonNull(FileManager.showFile()).getAbsolutePath();
                fileNameTextArea.setText(fileName);
                importButton.setEnabled(true);
            }
        });
    }


    public void init() {
        setContentPane(mainPanel);
        setTitle("Cook With Me - Import Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setSize(1300, 1000);
        setLocationRelativeTo(null);

        Utils windowUtils = new Utils();
        windowUtils.centerWindow(mainPanel);
    }

}
