package util;

import javax.swing.*;
import java.io.*;


public class FileManager {

    public static File showFile() {

        final JFileChooser fc = new JFileChooser();
        int response = fc.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            return (fc.getSelectedFile());
        } else {
            return null;
        }
    }

}

