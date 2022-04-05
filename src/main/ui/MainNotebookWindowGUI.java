package ui;

import model.Event;
import model.EventLog;
import model.VocabList;
import model.Word;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


// Opening Window for the Notebook, new word can be created
public class MainNotebookWindowGUI extends WindowAdapter implements ActionListener {
    private static final String JSON_STORE = "./data/vocabList.json";
    private static final String JSON_BACKUP = "./data/vocabListBackupMar29.json";
    private static final String JSON_TEST = "./data/testGUIVocabList.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    protected Word newWord;
    protected VocabList myVocabList;

    protected JFrame mainFrame;

    private JPanel mainPanel;
    private JLabel mvpLabel;
    private JTextField wordTextField;
    private JButton addWordButton;
    private JLabel descriptionLabel;

    private JPanel navigationPanel;
    private JButton viewNoteBookButton;
    private JButton saveVocabListButton;
    protected JButton loadVocabListButton;


    // constructs the Notebook window
    public MainNotebookWindowGUI() {
        setupMainFrame();
        setupMainLabel();
        setupTextField();
        setupDescriptionLabel();
        setupMainPanel();
        setupNavigationPanel();
        mainFrame.add(navigationPanel, BorderLayout.SOUTH);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
        myVocabList = new VocabList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    // set up the JFrame for main frame
    private void setupMainFrame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("My Vocabulary Notebook");
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(new Color(195, 243, 241));
        mainFrame.addWindowListener(this);

    }

    //navigation buttons specification
    public void setNavigationButtonsColor(JButton button) {
        //button.setBackground(new Color(245, 222, 77));
        button.setForeground(new Color(36, 36, 37));
        button.setFont(new Font("j", Font.PLAIN, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
    }

    public JButton getLoadVocabListButton() {
        return loadVocabListButton;
    }


    //set up the main center panel
    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800, 500));
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(195, 243, 241));

        mainPanel.add(mvpLabel);
        mainPanel.add(wordTextField);
        mainPanel.add(addWordButton);
        //mainPanel.add(descriptionLabel);

    }


    // set up the JLabel for main frame
    private void setupMainLabel() {
        ImageIcon appIconImage = new ImageIcon("images/MVP-Logo.png");
        Image mvpImage = appIconImage.getImage();
        mvpImage = mvpImage.getScaledInstance(130, 70, 100);
        appIconImage = new ImageIcon(mvpImage);
        mvpLabel = new JLabel();
        mvpLabel.setBounds(250, 50, 300, 130);
        mvpLabel.setIcon(appIconImage);
        mvpLabel.setText("Your personal Vocabulary Notebook");
        mvpLabel.setHorizontalTextPosition(JLabel.CENTER);
        mvpLabel.setVerticalTextPosition(JLabel.BOTTOM);
        mvpLabel.setForeground(new Color(250, 24, 77));
        mvpLabel.setFont(new Font("MVP", Font.BOLD, 15));
    }


    // set up the JLabel for main frame
    private void setupDescriptionLabel() {
        descriptionLabel = new JLabel();
        descriptionLabel.setBounds(150, 150, 800, 330);
        descriptionLabel.setText("<html>A personalized Vocabulary Notebook that allows you to record "
                + "the original learning context <br>for new words learned in a second language</html>");
        //descriptionLabel.setHorizontalTextPosition(JLabel.CENTER);
        //descriptionLabel.setVerticalTextPosition(JLabel.BOTTOM);
        descriptionLabel.setForeground(new Color(250, 24, 77));
        descriptionLabel.setFont(new Font("MVP", Font.PLAIN, 15));
    }

    //// set up the Navigation JPanel for main frame
    private void setupNavigationPanel() {
        navigationPanel = new JPanel();
        navigationPanel.setBounds(0, 400, 600, 80);
        navigationPanel.setLayout(new GridLayout(1, 3));
        viewNoteBookButton = new JButton("View My Notebook");
        viewNoteBookButton.setPreferredSize(new Dimension(200, 80));
        setNavigationButtonsColor(viewNoteBookButton);
        saveVocabListButton = new JButton("Save My Notebook");
        saveVocabListButton.setPreferredSize(new Dimension(200, 80));
        loadVocabListButton = new JButton("Load My Notebook");
        loadVocabListButton.setPreferredSize(new Dimension(200, 80));
        setNavigationButtonsColor(saveVocabListButton);
        setNavigationButtonsColor(loadVocabListButton);
        viewNoteBookButton.addActionListener(this);
        saveVocabListButton.addActionListener(this);
        loadVocabListButton.addActionListener(this);
        navigationPanel.add(viewNoteBookButton);
        navigationPanel.add(saveVocabListButton);
        navigationPanel.add(loadVocabListButton);
    }

    // set up the JTextField for main frame
    private void setupTextField() {
        wordTextField = new JTextField(10);
        wordTextField.setPreferredSize(new Dimension(250, 40));
        wordTextField.setBounds(240, 180, 150, 30);
        addWordButton = new JButton("Create Word Entry");
        addWordButton.setBounds(400, 180, 150, 30);
        addWordButton.addActionListener(this);
    }


    // EFFECTS: saves the workroom to file
    private void saveVocabList() {
        try {
            jsonWriter.open();
            jsonWriter.write(myVocabList);
            jsonWriter.close();
            System.out.println("Your Vocabulary List is SAVED to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadVocabList() {
        try {
            VocabList oldList = jsonReader.read();
            myVocabList = oldList.addAll(myVocabList.getVocabList());
            System.out.println("Your Vocabulary List is LOADED from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    public String printLog(EventLog el) {
        String logText = "";
        for (Event event : el) {
            logText = logText + event.toString() + "\n\n";
        }
        return logText;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addWordButton) {
            if (wordTextField.getText().isEmpty()) {
                System.out.println("cant be empty, get a jOptionPane later");
            } else {
                newWord = new Word(wordTextField.getText());
                myVocabList.addWord(newWord);
                wordTextField.setText("");
                new NewWordBuildingWindowGUI(this);

            }
        } else if (e.getSource() == viewNoteBookButton) {
            new VocabListWindowGUI(this);
            mainFrame.setVisible(false);
        } else if (e.getSource() == saveVocabListButton) {
            saveVocabList();
            JOptionPane.showMessageDialog(mainFrame,"You have saved your Vocabulary list","Saved!",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == loadVocabListButton) {
            loadVocabList();
            loadVocabListButton.setEnabled(false);
        }

    }

    @Override
    public void windowClosing(WindowEvent e) {
        closeApplication();

    }

    // MODIFIES: this
    // EFFECTS: display the confirmation dialog of quitting the program
    private void closeApplication() {
        String[] responses = {"Save and Quit", "No"};

        int confirmed = JOptionPane.showOptionDialog(mainFrame,
                "SAVE your current Vocabulary list with "
                        + myVocabList.getSize() + " words in it?",
                "", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                responses,
                0);

        if (confirmed == 0) {
            saveVocabList();
            System.out.println(printLog(EventLog.getInstance()));
            System.exit(0);
        } else if (confirmed == 1) {
            System.out.println(printLog(EventLog.getInstance()));
            System.exit(0);
        }

    }


}
