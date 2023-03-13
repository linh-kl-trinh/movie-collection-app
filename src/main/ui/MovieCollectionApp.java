package ui;

import model.Collection;
import model.Event;
import model.EventLog;
import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

// Movie collection application
public class MovieCollectionApp extends JFrame {
    private MovieList currentList;
    private Movie currentMovie;
    private boolean error = false;
    private Collection collection;
    private static final String JSON_STORE = "./data/watchlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final JLabel prompt = new JLabel("Please select from:");
    private JPanel buttonArea;
    private GroupLayout layout;

    // EFFECTS: runs the movie collection application
    public MovieCollectionApp() {
        super("Movie Collection");
        initializeGraphics();
        init();
        runCollection();
    }

    public GroupLayout getLayout() {
        return layout;
    }

    public JLabel getPrompt() {
        return prompt;
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public MovieList getCurrentList() {
        return currentList;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Collection getCollection() {
        return collection;
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this MovieCollectionApp will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 250));
        setLocationRelativeTo(null);
        setVisible(true);
        buttonArea = new JPanel();
        layout = new GroupLayout(buttonArea);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        buttonArea.setLayout(layout);
        add(buttonArea);
    }

    // MODIFIES: this
    // EFFECTS: removes layout and runs collectionUI
    void runCollection() {
        LayoutManager curLayout = buttonArea.getLayout();
        if (curLayout != null) {
            buttonArea.removeAll();
        }

        new CollectionUI(this, null);

        pack();
    }

    // MODIFIES: this
    // EFFECTS: removes layout and creates new CollectionUI with given message
    private void runCollection(String message) {
        LayoutManager curLayout = buttonArea.getLayout();
        if (curLayout != null) {
            buttonArea.removeAll();
        }

        new CollectionUI(this, new JLabel(message));

        pack();
    }

    // MODIFIES: this
    // EFFECTS: removes layout and creates new MovieListUI
    void runList() {
        LayoutManager curLayout = buttonArea.getLayout();
        if (curLayout != null) {
            buttonArea.removeAll();
        }

        new MovieListUI(this, error);

        pack();
    }

    // EFFECTS: saves collection to file
    void saveCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(collection);
            jsonWriter.close();
            runCollection("File saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            runCollection("File not found!");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads collection from file
    void loadCollection() {
        try {
            collection = jsonReader.read();
            runCollection("File loaded from " + JSON_STORE);
        } catch (IOException e) {
            runCollection("File not found!");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes layout and creates new MovieUI
    void runMovie() {
        LayoutManager curLayout = buttonArea.getLayout();
        if (curLayout != null) {
            buttonArea.removeAll();
        }

        new MovieUI(this, error);

        pack();
    }

    // MODIFIES: this
    // EFFECTS: removes layout and creates new SortedListUI
    void runSortedList(JList sortedListToDisplay) {
        LayoutManager curLayout = buttonArea.getLayout();
        if (curLayout != null) {
            buttonArea.removeAll();
        }

        new SortedListUI(this, sortedListToDisplay);

        pack();
    }

    // MODIFIES: this
    // EFFECTS: initializes collection
    private void init() {
        collection = new Collection();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        MovieList waiting = new MovieList("Waiting List");
        MovieList watching = new MovieList("Watching List");
        MovieList finished = new MovieList("Finished List");
        MovieList dropped = new MovieList("Dropped List");
        collection.addList(waiting);
        collection.addList(watching);
        collection.addList(finished);
        collection.addList(dropped);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                EventLog el = EventLog.getInstance();
                for (Event next : el) {
                    System.out.println(next);
                }
            }
        };
        addWindowListener(exitListener);
    }

    // EFFECTS: selects a movie in currentList
    void selectMovie(int selection) {
        try {
            currentMovie = currentList.getWatchlist().get(selection - 1);
            runMovie();
        } catch (ArrayIndexOutOfBoundsException e) {
            runList();
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts user command to select a list
    void runSelectedList(String command) {
        ArrayList<MovieList> lists = collection.getLists();

        if (command.equals("waiting")) {
            currentList = lists.get(0);
        } else if (command.equals("watching")) {
            currentList = lists.get(1);
        } else if (command.equals("finished")) {
            currentList = lists.get(2);
        } else if (command.equals("dropped")) {
            currentList = lists.get(3);
        }

        error = false;
        runList();
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        JWindow window = new JWindow();
        final URL url = new URL("https://c.tenor.com/xTRIwCd1bKAAAAAC/cinema-minion.gif");
        ImageIcon splashImage = new ImageIcon(url);
        window.getContentPane().add(new JLabel("", splashImage, SwingConstants.CENTER));
        window.setBounds(0, 0, 500, 250);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        Thread.sleep(3250);
        window.setVisible(false);
        window.dispose();
        new MovieCollectionApp();
    }
}