package util;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

public class Utils {

    public void centerWindow(JPanel frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public static void printComponent(Component component) throws PrinterException {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);
        PageFormat postformat = pjob.pageDialog(preformat);
        if (preformat != postformat) {
            pjob.setPrintable(new Printer(component), postformat);
            if (pjob.printDialog()) {
                pjob.print();
            }
        }
    }

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
