package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollectionUI implements ActionListener {
    private final MovieCollectionApp app;
    private final GroupLayout layout;
    private final JLabel prompt;
    private JButton waitingBtn;
    private JButton watchingBtn;
    private JButton finishedBtn;
    private JButton droppedBtn;
    private JButton loadBtn;
    private JButton saveBtn;

    // EFFECTS: runs ui for collection
    public CollectionUI(MovieCollectionApp app, JLabel message) {
        this.app = app;
        layout = app.getLayout();
        prompt = app.getPrompt();
        setCollectionButtons();

        if (message != null) {
            setCollectionLayout(message);
        } else {
            setCollectionLayout();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes all JButton to be used in the collection
    private void setCollectionButtons() {
        waitingBtn = new JButton("Waiting List");
        waitingBtn.setActionCommand("waiting");
        waitingBtn.addActionListener(this);

        watchingBtn = new JButton("Watching List");
        watchingBtn.setActionCommand("watching");
        watchingBtn.addActionListener(this);

        finishedBtn = new JButton("Finished List");
        finishedBtn.setActionCommand("finished");
        finishedBtn.addActionListener(this);

        droppedBtn = new JButton("Dropped List");
        droppedBtn.setActionCommand("dropped");
        droppedBtn.addActionListener(this);

        loadBtn = new JButton("Load");
        loadBtn.setActionCommand("load");
        loadBtn.addActionListener(this);
        loadBtn.setBackground(new Color(0xD0A324));

        saveBtn = new JButton("Save");
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);
        saveBtn.setBackground(new Color(0x0CA50C));
    }

    // MODIFIES: this
    // EFFECTS: combines horizontal and vertical layouts of this collection
    private void setCollectionLayout() {
        setCollectionHorizontalLayout();
        setCollectionVerticalLayout();
    }

    // MODIFIES: this
    // EFFECTS: combines horizontal and vertical layouts with given message of this collection
    private void setCollectionLayout(JLabel message) {
        setCollectionHorizontalLayout(message);
        setCollectionVerticalLayout(message);
    }

    // MODIFIES: this
    // EFFECTS: adds all components to layout and sets horizontal layout of this collection
    private void setCollectionHorizontalLayout() {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(prompt)
                                .addComponent(waitingBtn)
                                .addComponent(watchingBtn)
                                .addComponent(finishedBtn).addComponent(droppedBtn)
                                .addComponent(loadBtn)
                                .addComponent(saveBtn)));
    }

    // MODIFIES: this
    // EFFECTS: adds all components and given message to layout and sets horizontal layout of this collection
    private void setCollectionHorizontalLayout(JLabel message) {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(prompt)
                                .addComponent(waitingBtn)
                                .addComponent(watchingBtn)
                                .addComponent(finishedBtn)
                                .addComponent(droppedBtn)
                                .addComponent(loadBtn)
                                .addComponent(saveBtn)
                                .addComponent(message)));
    }

    // MODIFIES: this
    // EFFECTS: adds all components to layout and sets vertical layout of this collection
    private void setCollectionVerticalLayout() {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(prompt)
                        .addComponent(waitingBtn)
                        .addComponent(watchingBtn)
                        .addComponent(finishedBtn)
                        .addComponent(droppedBtn)
                        .addComponent(loadBtn)
                        .addComponent(saveBtn));
    }

    // MODIFIES: this
    // EFFECTS: adds all components and given message to layout and sets vertical layout of this collection
    private void setCollectionVerticalLayout(JLabel message) {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(prompt)
                        .addComponent(waitingBtn)
                        .addComponent(watchingBtn)
                        .addComponent(finishedBtn)
                        .addComponent(droppedBtn)
                        .addComponent(loadBtn)
                        .addComponent(saveBtn)
                        .addComponent(message));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("load")) {
            app.loadCollection();
        } else if (command.equals("save")) {
            app.saveCollection();
        } else {
            app.setError(false);
            app.runSelectedList(command);
        }
    }
}
