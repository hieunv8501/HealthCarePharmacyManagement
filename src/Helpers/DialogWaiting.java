package Helpers;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class DialogWaiting {

    private JDialog dialog;

    public void makeWaiting(String msg, ActionEvent evt) {
        Window window = SwingUtilities.getWindowAncestor((AbstractButton) evt.getSource());
        dialog = new JDialog(window, msg, Dialog.ModalityType.APPLICATION_MODAL);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 35));
        panel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        panel.add(progressBar, BorderLayout.CENTER);
        //panel.add(new JLabel("Vui lòng chờ trong giây lát..."), BorderLayout.PAGE_START);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(window);
        dialog.setVisible(true);
    }

    public void close() {
        dialog.dispose();
    }
}
