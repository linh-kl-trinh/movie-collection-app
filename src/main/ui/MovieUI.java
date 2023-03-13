package ui;

import model.Collection;
import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MovieUI implements ActionListener {
    private final MovieCollectionApp app;
    private final boolean error;
    private final GroupLayout layout;
    private final JLabel prompt;
    private JButton changeTitleBtn;
    private JButton changeYearBtn;
    private JButton changeRatingBtn;
    private JButton changeStatusBtn;
    private JButton removeBtn;
    private JButton backToListBtn;
    private JTextField newTitleTextField;
    private JTextField newYearTextField;
    private JTextField ratingTextField;
    private JLabel titleLabel;
    private JLabel yearLabel;
    private JLabel ratingLabel;
    private JLabel statusLabel;
    private JComboBox<String> statuses;

    // EFFECTS: runs ui for currentMovie
    public MovieUI(MovieCollectionApp app, boolean error) {
        this.app = app;
        this.error = error;
        this.layout = app.getLayout();
        this.prompt = app.getPrompt();
        setMovieButtons();
        setMovieTextFields();
        runMovie();
    }

    // MODIFIES: this
    // EFFECTS: initializes all JButton to be used in currentMovie
    private void setMovieButtons() {
        changeTitleBtn = new JButton("Change");
        changeTitleBtn.setActionCommand("changeTitle");
        changeTitleBtn.addActionListener(this);

        changeYearBtn = new JButton("Change");
        changeYearBtn.setActionCommand("changeYear");
        changeYearBtn.addActionListener(this);

        changeRatingBtn = new JButton("Change");
        changeRatingBtn.setActionCommand("changeRating");
        changeRatingBtn.addActionListener(this);

        changeStatusBtn = new JButton("Change");
        changeStatusBtn.setActionCommand("changeStatus");
        changeStatusBtn.addActionListener(this);

        removeBtn = new JButton("Remove");
        removeBtn.setActionCommand("remove");
        removeBtn.addActionListener(this);
        removeBtn.setBackground(new Color(0xE70A0A));

        backToListBtn = new JButton("Back");
        backToListBtn.setActionCommand("backToList");
        backToListBtn.addActionListener(this);
        backToListBtn.setBackground(new Color(0x2B2BFF));
    }

    // MODIFIES: this
    // EFFECTS: initializes all JLabel and JTextField to be used in currentMovie
    private void setMovieTextFields() {
        newTitleTextField = new JTextField(5);
        titleLabel = new JLabel("Title:");
        newYearTextField = new JTextField(5);
        yearLabel = new JLabel("Year:");
        ratingLabel = new JLabel("Rating:");
        ratingTextField = new JTextField(5);
        statusLabel = new JLabel("Status:");
        statuses = new JComboBox<>();
        statuses.addItem("[select]");
        statuses.addItem("waiting");
        statuses.addItem("watching");
        statuses.addItem("finished");
        statuses.addItem("dropped");
    }

    // MODIFIES: this
    // EFFECTS: displays currentMovie with menu option
    private void runMovie() {
        statuses.setSelectedIndex(0);

        if (error) {
            setMovieLayout(displayMovie(), new JLabel("Invalid! Please try again!"));
        } else {
            setMovieLayout(displayMovie());
        }
    }

    // MODIFIES: this
    // EFFECTS: creates group of JLabel, JTextField, and JButton used to set title
    private GroupLayout.Group createTitleGroup(String type) {
        if (type.equals("sequential")) {
            return layout.createSequentialGroup()
                    .addComponent(titleLabel)
                    .addComponent(newTitleTextField)
                    .addComponent(changeTitleBtn);
        } else {
            return layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(newTitleTextField)
                    .addComponent(changeTitleBtn);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates group of JLabel, JTextField, and JButton used to set year
    private GroupLayout.Group createYearGroup(String type) {
        if (type.equals("sequential")) {
            return layout.createSequentialGroup()
                    .addComponent(yearLabel)
                    .addComponent(newYearTextField)
                    .addComponent(changeYearBtn);
        } else {
            return layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(yearLabel)
                    .addComponent(newYearTextField)
                    .addComponent(changeYearBtn);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates group of JLabel, JTextField, and JButton used to set rating
    private GroupLayout.Group createRatingGroup(String type) {
        if (type.equals("sequential")) {
            return layout.createSequentialGroup()
                    .addComponent(ratingLabel)
                    .addComponent(ratingTextField)
                    .addComponent(changeRatingBtn);
        } else {
            return layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ratingLabel)
                    .addComponent(ratingTextField)
                    .addComponent(changeRatingBtn);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates group of JLabel, JTextField, and JButton used to set status
    private GroupLayout.Group createStatusGroup(String type) {
        if (type.equals("sequential")) {
            return layout.createSequentialGroup()
                    .addComponent(statusLabel)
                    .addComponent(statuses)
                    .addComponent(changeStatusBtn);
        } else {
            return layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(statuses)
                    .addComponent(changeStatusBtn);
        }
    }

    // MODIFIES: this
    // EFFECTS: combines horizontal and vertical layouts of currentMovie
    private void setMovieLayout(JLabel movieToDisplay) {
        setMovieHorizontalLayout(movieToDisplay);
        setMovieVerticalLayout(movieToDisplay);
    }

    // MODIFIES: this
    // EFFECTS: combines horizontal and vertical layouts with given message of currentMovie
    private void setMovieLayout(JLabel movieToDisplay, JLabel message) {
        setMovieHorizontalLayout(movieToDisplay, message);
        setMovieVerticalLayout(movieToDisplay, message);
    }

    // MODIFIES: this
    // EFFECTS: adds all components to layout and sets horizontal layout of currentMovie
    private void setMovieHorizontalLayout(JLabel movieToDisplay) {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(movieToDisplay)
                                .addComponent(prompt)
                                .addGroup(createTitleGroup("sequential"))
                                .addGroup(createYearGroup("sequential"))
                                .addGroup(createRatingGroup("sequential"))
                                .addGroup(createStatusGroup("sequential"))
                                .addComponent(removeBtn)
                                .addComponent(backToListBtn))
        );
    }

    // MODIFIES: this
    // EFFECTS: adds all components and given message to layout and sets horizontal layout of currentMovie
    private void setMovieHorizontalLayout(JLabel movieToDisplay, JLabel message) {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(movieToDisplay)
                                .addComponent(prompt)
                                .addGroup(createTitleGroup("sequential"))
                                .addGroup(createYearGroup("sequential"))
                                .addGroup(createRatingGroup("sequential"))
                                .addGroup(createStatusGroup("sequential"))
                                .addComponent(removeBtn)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(backToListBtn)
                                        .addComponent(message))
                        ));

    }

    // MODIFIES: this
    // EFFECTS: adds all components to layout and sets vertical layout of currentMovie
    private void setMovieVerticalLayout(JLabel movieToDisplay) {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(movieToDisplay)
                        .addComponent(prompt)
                        .addGroup(createTitleGroup("parallel"))
                        .addGroup(createYearGroup("parallel"))
                        .addGroup(createRatingGroup("parallel"))
                        .addGroup(createStatusGroup("parallel"))
                        .addComponent(removeBtn)
                        .addComponent(backToListBtn)
        );
    }

    // MODIFIES: this
    // EFFECTS: adds all components and given message to layout and sets vertical layout of currentMovie
    private void setMovieVerticalLayout(JLabel movieToDisplay, JLabel message) {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(movieToDisplay)
                        .addComponent(prompt)
                        .addGroup(createTitleGroup("parallel"))
                        .addGroup(createYearGroup("parallel"))
                        .addGroup(createRatingGroup("parallel"))
                        .addGroup(createStatusGroup("parallel"))
                        .addComponent(removeBtn)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backToListBtn)
                                .addComponent(message)
                        ));
    }

    // EFFECTS: converts currentMovie into JLabel
    private JLabel displayMovie() {
        Movie currentMovie = app.getCurrentMovie();
        String rating;
        String title = currentMovie.getTitle();
        String year = String.valueOf(currentMovie.getYear());
        if (currentMovie.getRating() == 0) {
            rating = "no rating";
        } else {
            rating = currentMovie.getRating() + "/10";
        }
        return new JLabel(title + " (" + year + ")" + " - " + rating);
    }

    // MODIFIES: this
    // EFFECTS: conducts change with given title of currentMovie
    private void doRename(String title) {
        app.getCurrentMovie().rename(title);
        newTitleTextField = new JTextField(5);
        app.runMovie();
    }

    // MODIFIES: this
    // EFFECTS: conducts change with given year of currentMovie
    private void doChangeYear(int year) {
        app.getCurrentMovie().changeYear(year);
        app.setError(false);
        app.runMovie();
    }

    // MODIFIES: this
    // EFFECTS: conducts removal of currentMovie from currentList
    private void doRemove() {
        app.getCurrentList().removeMovie(app.getCurrentMovie());
        app.runList();
    }

    // MODIFIES: this
    // EFFECTS: conducts change with given status of currentMovie
    private void doChangeStatus(String status) {
        MovieList currentList = app.getCurrentList();
        Movie currentMovie = app.getCurrentMovie();
        MovieList to;
        Collection collection = app.getCollection();
        ArrayList<MovieList> lists = collection.getLists();
        if (status.equals("waiting")) {
            to = lists.get(0);
            currentList.removeMovie(currentMovie);
            to.addMovie(currentMovie);
        } else if (status.equals("watching")) {
            to = lists.get(1);
            currentList.removeMovie(currentMovie);
            to.addMovie(currentMovie);
        } else if (status.equals("finished")) {
            to = lists.get(2);
            currentList.removeMovie(currentMovie);
            to.addMovie(currentMovie);
        } else if (status.equals("dropped")) {
            to = lists.get(3);
            currentList.removeMovie(currentMovie);
            to.addMovie(currentMovie);
        }
        app.runMovie();
    }

    // MODIFIES: this
    // EFFECTS: conducts change with given rating of currentMovie
    // if rating is invalid, throws NumberFormatException
    private void doGiveRating(int rating) throws NumberFormatException {
        if (rating <= 0 || rating > 10) {
            throw new NumberFormatException();
        } else {
            app.getCurrentMovie().giveRating(rating);
            app.setError(false);
            app.runMovie();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("remove")) {
            app.setError(false);
            doRemove();
        } else if (command.equals("backToList")) {
            app.setError(false);
            app.runSelectedList(command);
        } else {
            changeMovieInfo(command);
        }
    }

    // EFFECTS: conducts user command in currentMovie
    private void changeMovieInfo(String command) {
        if (command.equals("changeTitle")) {
            doRename(newTitleTextField.getText());
        } else if (command.equals("changeYear")) {
            tryChangeYear();
        } else if (command.equals("changeRating")) {
            tryGiveRating();
        } else if (command.equals("changeStatus")) {
            doChangeStatus((String) statuses.getSelectedItem());
        }
    }

    // MODIFIES: this
    // EFFECTS: try changing year of currentMovie
    private void tryChangeYear() {
        try {
            int year = Integer.parseInt(newYearTextField.getText());
            newYearTextField = new JTextField(5);
            doChangeYear(year);
        } catch (NumberFormatException e) {
            app.setError(true);
            newYearTextField = new JTextField(5);
            app.runMovie();
        }
    }

    // MODIFIES: this
    // EFFECTS: try giving rating to currentMovie
    private void tryGiveRating() {
        try {
            int rating = Integer.parseInt(ratingTextField.getText());
            ratingTextField = new JTextField(5);
            doGiveRating(rating);
        } catch (NumberFormatException e) {
            app.setError(true);
            ratingTextField = new JTextField(5);
            app.runMovie();
        }
    }
}
