package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MovieListUI implements ActionListener {
    private final MovieCollectionApp app;
    private final boolean error;
    private final GroupLayout layout;
    private JComboBox<String> movies;
    private final JLabel prompt;
    private JButton addBtn;
    private JButton selectBtn;
    private JButton sortBtn;
    private JButton backToCollectionBtn;
    private JLabel titleLabel;
    private JLabel yearLabel;
    private JLabel selectLabel;
    private JLabel yearToSortLabel;
    private JTextField titleTextField;
    private JTextField yearTextField;
    private JTextField yearToSortTextField;

    // EFFECTS: runs ui for currentList
    public MovieListUI(MovieCollectionApp app, boolean error) {
        this.app = app;
        this.error = error;
        this.layout = app.getLayout();
        this.prompt = app.getPrompt();
        setListButtons();
        setListTextFields();
        runList();
    }

    // MODIFIES: this
    // EFFECTS: displays currentList with option menu
    private void runList() {
        movieDropdown();

        if (error) {
            setListLayout(displayList(), new JLabel("Invalid! Please try again!"));
        } else {
            setListLayout(displayList());
        }
    }

    // MODIFIES: this
    // EFFECTS: combines horizontal and vertical layouts of currentList
    private void setListLayout(JList listToDisplay) {
        setListHorizontalLayout(listToDisplay);
        setListVerticalLayout(listToDisplay);
    }

    // MODIFIES: this
    // EFFECTS: combines horizontal and vertical layouts with given message of currentList
    private void setListLayout(JList listToDisplay, JLabel message) {
        setListHorizontalLayout(listToDisplay, message);
        setListVerticalLayout(listToDisplay, message);
    }

    // MODIFIES: this
    // EFFECTS: adds all components to layout and sets vertical layout of currentList
    private void setListHorizontalLayout(JList listToDisplay) {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(listToDisplay)
                                .addComponent(prompt)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(titleLabel)
                                        .addComponent(titleTextField)
                                        .addComponent(yearLabel)
                                        .addComponent(yearTextField)
                                        .addComponent(addBtn))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(selectLabel)
                                        .addComponent(movies)
                                        .addComponent(selectBtn))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(yearToSortLabel)
                                        .addComponent(yearToSortTextField)
                                        .addComponent(sortBtn))
                                .addComponent(backToCollectionBtn))
        );
    }

    // MODIFIES: this
    // EFFECTS: adds all components and given message to layout and sets horizontal layout of currentList
    private void setListHorizontalLayout(JList listToDisplay, JLabel message) {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(listToDisplay)
                                .addComponent(prompt)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(titleLabel)
                                        .addComponent(titleTextField)
                                        .addComponent(yearLabel)
                                        .addComponent(yearTextField)
                                        .addComponent(addBtn))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(selectLabel)
                                        .addComponent(movies)
                                        .addComponent(selectBtn))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(yearToSortLabel)
                                        .addComponent(yearToSortTextField)
                                        .addComponent(sortBtn))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(backToCollectionBtn)
                                        .addComponent(message))
                        ));
    }

    // MODIFIES: this
    // EFFECTS: adds all components to layout and sets vertical layout of currentList
    private void setListVerticalLayout(JList listToDisplay) {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(listToDisplay)
                        .addComponent(prompt)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(titleLabel)
                                .addComponent(titleTextField)
                                .addComponent(yearLabel)
                                .addComponent(yearTextField)
                                .addComponent(addBtn))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(selectLabel)
                                .addComponent(movies)
                                .addComponent(selectBtn))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(yearToSortLabel)
                                .addComponent(yearToSortTextField)
                                .addComponent(sortBtn))
                        .addComponent(backToCollectionBtn)
        );
    }

    // MODIFIES: this
    // EFFECTS: adds all components and given message to layout and sets vertical layout of currentList
    private void setListVerticalLayout(JList listToDisplay, JLabel message) {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(listToDisplay)
                        .addComponent(prompt)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(titleLabel)
                                .addComponent(titleTextField)
                                .addComponent(yearLabel)
                                .addComponent(yearTextField)
                                .addComponent(addBtn))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(selectLabel)
                                .addComponent(movies)
                                .addComponent(selectBtn))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(yearToSortLabel)
                                .addComponent(yearToSortTextField)
                                .addComponent(sortBtn))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backToCollectionBtn)
                                .addComponent(message))
        );
    }

    // EFFECTS: makes a dropdown of movies in currentList
    private void movieDropdown() {
        movies = new JComboBox<>();
        movies.addItem("[select]");
        ArrayList<Movie> watchlist = app.getCurrentList().getWatchlist();
        for (Movie next : watchlist) {
            String rating;
            String title = next.getTitle();
            String year = String.valueOf(next.getYear());
            int index = watchlist.indexOf(next) + 1;
            if (next.getRating() == 0) {
                rating = "no rating";
            } else {
                rating = next.getRating() + "/10";
            }
            movies.addItem(index + ". " + title + " (" + year + ") " + " - " + rating);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes all JButton to be used in currentList
    private void setListButtons() {
        addBtn = new JButton("Add");
        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);

        selectBtn = new JButton("Select");
        selectBtn.setActionCommand("select");
        selectBtn.addActionListener(this);

        sortBtn = new JButton("Sort");
        sortBtn.setActionCommand("sort");
        sortBtn.addActionListener(this);

        backToCollectionBtn = new JButton("Back");
        backToCollectionBtn.setActionCommand("backToCollection");
        backToCollectionBtn.addActionListener(this);
        backToCollectionBtn.setBackground(new Color(0x2B2BFF));
    }

    // MODIFIES: this
    // EFFECTS: initializes all JLabel and JTextField to be used in currentList
    private void setListTextFields() {
        titleLabel = new JLabel("Title:");
        titleTextField = new JTextField(5);
        yearLabel = new JLabel("Year:");
        yearTextField = new JTextField(5);
        selectLabel = new JLabel("Select Movie:");
        yearToSortLabel = new JLabel("Sort By Year:");
        yearToSortTextField = new JTextField(5);
    }

    // EFFECTS: converts currentList into JList
    private JList displayList() {
        MovieList currentList = app.getCurrentList();
        DefaultListModel listModel = new DefaultListModel<>();
        listModel.addElement(currentList.getName());
        ArrayList<Movie> watchlist = currentList.getWatchlist();
        if (watchlist.isEmpty()) {
            listModel.addElement("This list is empty.");
        } else {
            for (Movie next : watchlist) {
                String rating;
                String title = next.getTitle();
                String year = String.valueOf(next.getYear());
                int index = watchlist.indexOf(next) + 1;
                if (next.getRating() == 0) {
                    rating = "no rating";
                } else {
                    rating = next.getRating() + "/10";
                }
                listModel.addElement(index + ". " + title + " (" + year + ") " + " - " + rating);
            }
        }
        return new JList(listModel);
    }

    // MODIFIES: this
    // EFFECTS: conducts movie addition with given title and year to currentList
    private void doAddMovie(String title, int year) {
        title = title.toLowerCase();
        Movie newMovie = new Movie(title, year);
        app.getCurrentList().addMovie(newMovie);
    }

    // EFFECTS: conducts sorting of currentList by given year and returns sorted list as JList
    private JList doSortByYear(int year) {
        MovieList currentList = app.getCurrentList();
        DefaultListModel listModel = new DefaultListModel<>();
        listModel.addElement("Movies in " + currentList.getName() + " released in " + year + ": ");
        ArrayList<String> sortedList = currentList.sortByYear(year);
        if (sortedList.isEmpty()) {
            listModel.addElement("None.");
        } else {
            for (String next : sortedList) {
                listModel.addElement(next);
            }
        }
        return new JList(listModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("add")) {
            tryAddMovieToList();
        } else if (command.equals("select")) {
            app.selectMovie(movies.getSelectedIndex());
        } else if (command.equals("sort")) {
            trySortByYear();
        } else {
            app.runCollection();
        }
    }

    // MODIFIES: this
    // EFFECTS: try adding movie to currentList
    private void tryAddMovieToList() {
        try {
            doAddMovie(titleTextField.getText(), Integer.parseInt(yearTextField.getText()));
            app.setError(false);
        } catch (NumberFormatException e) {
            app.setError(true);
        }

        titleTextField = new JTextField(5);
        yearTextField = new JTextField(5);
        app.runList();
    }

    // MODIFIES: this
    // EFFECTS: try sorting currentList by year
    private void trySortByYear() {
        try {
            int yearToSort = Integer.parseInt(yearToSortTextField.getText());
            yearToSortTextField = new JTextField(5);
            app.setError(false);
            app.runSortedList(doSortByYear(yearToSort));
        } catch (NumberFormatException e) {
            app.setError(true);
            yearToSortTextField = new JTextField(5);
            app.runList();
        }
    }

}
