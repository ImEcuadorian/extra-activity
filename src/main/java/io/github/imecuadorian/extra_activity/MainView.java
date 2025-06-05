package io.github.imecuadorian.extra_activity;

import io.github.imecuadorian.extra_activity.controller.*;
import io.github.imecuadorian.library.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainView extends JFrame {

    private final DefaultListModel<String> dniListModel = new DefaultListModel<>();
    private final DefaultListModel<String> nameListModel = new DefaultListModel<>();
    private final DefaultListModel<String> emailListModel = new DefaultListModel<>();
    private final DefaultListModel<String> fullListModel = new DefaultListModel<>();
    private final JTextArea displayArea = new JTextArea();
    private final JProgressBar progressBar = new JProgressBar();
    private final JButton btnShowData = new JButton("Show Data");

    public MainView() {
        setTitle("Extra Activity");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel listPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        listPanel.setBorder(BorderFactory.createTitledBorder("Extracted Fields"));

        listPanel.add(createScrollWithTitle(new JList<>(dniListModel), "DNI"));
        listPanel.add(createScrollWithTitle(new JList<>(nameListModel), "Name"));
        listPanel.add(createScrollWithTitle(new JList<>(emailListModel), "Email"));
        listPanel.add(createScrollWithTitle(new JList<>(fullListModel), "Full Data"));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        JButton btnLoadFile = new JButton("Extract Data");
        btnShowData.setEnabled(false);

        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(btnLoadFile);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        controlPanel.add(btnShowData);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        progressBar.setPreferredSize(new Dimension(200, 25));
        controlPanel.add(progressBar);
        controlPanel.add(Box.createHorizontalGlue());


        add(listPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.CENTER);

        btnLoadFile.addActionListener(e -> {
            try {
                Files fileLoader = new Files("");
                if (fileLoader.getFileFromFileChooser(this, "txt")) {
                    btnShowData.setEnabled(false);
                    Controller controller = new Controller(
                            fileLoader, progressBar,
                            dniListModel, nameListModel, emailListModel,
                            fullListModel,
                            btnShowData
                    );
                    controller.execute();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnShowData.addActionListener(e -> {
            try {
                Files fileLoader = new Files("registro.txt");
                Controller controller = new Controller(
                        fileLoader, progressBar,
                        dniListModel, nameListModel, emailListModel,
                        fullListModel,
                        btnShowData
                );
                controller.showRegisteredData();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setResizable(false);
        setVisible(true);
    }

    private JScrollPane createScrollWithTitle(JList<?> list, String title) {
        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(BorderFactory.createTitledBorder(title));
        return scroll;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
