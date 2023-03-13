package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortedListUI implements ActionListener {
    private final MovieCollectionApp app;
    private final GroupLayout layout;
    private JButton backToListBtn;

    // EFFECTS: runs ui for sorted list
    public SortedListUI(MovieCollectionApp app, JList sortedListToDisplay) {
        this.app = app;
        this.layout = app.getLayout();
        setBackToListBtn();
        setSortedListLayout(sortedListToDisplay);
    }

    // MODIFIES: this
    // EFFECTS: initializes JButton
    private void setBackToListBtn() {
        backToListBtn = new JButton("Back");
        backToListBtn.setActionCommand("backToList");
        backToListBtn.addActionListener(this);
        backToListBtn.setBackground(new Color(0x2B2BFF));
    }

    // MODIFIES: this
    // EFFECTS: adds all components to layout and sets layout of given sorted list
    private void setSortedListLayout(JList sortedListToDisplay) {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(sortedListToDisplay)
                                .addComponent(backToListBtn))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(sortedListToDisplay)
                        .addComponent(backToListBtn)
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        app.setError(false);
        app.runSelectedList(command);
    }
}
