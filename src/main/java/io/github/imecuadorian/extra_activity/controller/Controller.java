package io.github.imecuadorian.extra_activity.controller;

import io.github.imecuadorian.extra_activity.model.Person;
import io.github.imecuadorian.extra_activity.model.PersonDAO;
import io.github.imecuadorian.library.Files;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Controller extends SwingWorker<Void, Void> {
    private final Files fileLoader;
    private final PersonDAO personDAO;
    private final JProgressBar progressBar;
    private final DefaultListModel<String> dniList;
    private final DefaultListModel<String> nameList;
    private final DefaultListModel<String> emailList;
    private final DefaultListModel<String> fullListModel;
    private final JButton btnShowData;


    public Controller(Files fileLoader, JProgressBar progressBar,
                      DefaultListModel<String> dniList, DefaultListModel<String> nameList,
                      DefaultListModel<String> emailList,
                      DefaultListModel<String> fullListModel, JButton btnShowData) throws IOException {
        this.fileLoader = fileLoader;
        this.progressBar = progressBar;
        this.dniList = dniList;
        this.nameList = nameList;
        this.emailList = emailList;
        this.fullListModel = fullListModel;
        this.btnShowData = btnShowData;
        this.personDAO = new PersonDAO();
    }


    @Override
    protected Void doInBackground() throws Exception {
        java.util.List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileLoader.getFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        int totalLines = lines.size();
        int progress = 0;

        for (int i = 0; i < totalLines; i++) {
            String line = lines.get(i);
            Thread.sleep(3000);

            String[] parts = line.split(";");
            if (parts.length == 3) {
                Person p = new Person(parts[0], parts[1], parts[2]);
                personDAO.save(p);
                dniList.addElement(p.getDNI());
                nameList.addElement(p.getName());
                emailList.addElement(p.getEmail());
            }

            progress = (int) (((i + 1) / (double) totalLines) * 100);
            progressBar.setValue(progress);
        }

        return null;
    }


    @Override
    protected void done() {
        btnShowData.setEnabled(true);
        JOptionPane.showMessageDialog(null, "Data extraction completed successfully!");
    }

    public void showRegisteredData() throws IOException {
        dniList.clear();
        nameList.clear();
        emailList.clear();
        fullListModel.clear();

        for (Person p : personDAO.loadAll()) {
            dniList.addElement(p.getDNI());
            nameList.addElement(p.getName());
            emailList.addElement(p.getEmail());
            fullListModel.addElement(p.toString());
        }
    }

}
