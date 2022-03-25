package ui;

import model.VocabList;
import model.Word;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Opening Window for the Notebook, new word can be created
public class NotebookWindow implements ActionListener {
    private static final String JSON_STORE = "./data/testGUIVocabList.json";

    private JPanel navigationPanel;
    private JTextField wordTextField;
    private JButton addWordButton;
    private JButton viewNoteBookButton;
    private JButton saveVocabListButton;
    private JButton loadVocabListButton;
    private JLabel mvpLabel;
    protected JFrame mainFrame;

    protected Word newWord;
    protected VocabList myVocabList;

    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // constructs the Notebook window
    public NotebookWindow() {
        setupMainFrame();
        setupMainLabel();
        setupMainNavigationPanel();
        setupMainTextField();
        addComponentsToFrame();
        //this.addComponentsToPanel();
        //mainFrame.add(navigationPanel);
        mainFrame.setVisible(true);
        myVocabList = new VocabList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // set up the JFrame for main frame
    private void setupMainFrame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("My Vocabulary Notebook");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
        mainFrame.setLayout(null);
        //mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(new Color(195, 243, 241));
    }


    // set up the JLabel for main frame
    private void setupMainLabel() {
        ImageIcon appIconImage = new ImageIcon("images/MVP-Logo.png");
        Image mvpImage = appIconImage.getImage();
        mvpImage = mvpImage.getScaledInstance(130, 70, 100);
        appIconImage = new ImageIcon(mvpImage);
        mvpLabel = new JLabel();
        mvpLabel.setBounds(150, 50, 300, 130);
        mvpLabel.setIcon(appIconImage);
        mvpLabel.setText("Your personal Vocabulary Notebook");
        mvpLabel.setHorizontalTextPosition(JLabel.CENTER);
        mvpLabel.setVerticalTextPosition(JLabel.BOTTOM);
        mvpLabel.setForeground(new Color(250, 24, 77));
        mvpLabel.setFont(new Font("MVP", Font.BOLD, 15));
    }

    //// set up the Navigation JPanel for main frame
    private void setupMainNavigationPanel() {
        navigationPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        navigationPanel.setBounds(0, 400, 600, 80);
        navigationPanel.setLayout(new GridLayout(1,3));
        viewNoteBookButton = new JButton("View My Notebook");
        viewNoteBookButton.setPreferredSize(new Dimension(200,80));
        saveVocabListButton = new JButton("Save My Notebook");
        saveVocabListButton.setPreferredSize(new Dimension(200,80));
        loadVocabListButton = new JButton("Load My Notebook");
        loadVocabListButton.setPreferredSize(new Dimension(200,80));
        viewNoteBookButton.addActionListener(this);
        saveVocabListButton.addActionListener(this);
        loadVocabListButton.addActionListener(this);
        navigationPanel.add(viewNoteBookButton);
        navigationPanel.add(saveVocabListButton);
        navigationPanel.add(loadVocabListButton);
    }

    // set up the JTextField for main frame
    private void setupMainTextField() {
        wordTextField = new JTextField(10);
        wordTextField.setPreferredSize(new Dimension(250, 40));
        wordTextField.setBounds(140, 180, 150, 30);
        addWordButton = new JButton("Create Word Entry");
        addWordButton.setBounds(300, 180, 150, 30);
        addWordButton.addActionListener(this);
        //addWordButton.setBorder(BorderFactory.createEtchedBorder());
    }

    //// set up the Navigation JPanel for main frame
    private void addComponentsToFrame() {
        mainFrame.add(mvpLabel);
        mainFrame.add(wordTextField);
        mainFrame.add(addWordButton);
        mainFrame.add(navigationPanel);
    }

    //// set up the Navigation JPanel for main frame
    private void addComponentsToPanel() {
        navigationPanel.add(mvpLabel);
        navigationPanel.add(wordTextField);
        navigationPanel.add(addWordButton);

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
            myVocabList = jsonReader.read();
            System.out.println("Your Vocabulary List is LOADED from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addWordButton) {
            newWord = new Word(wordTextField.getText());
            myVocabList.addWord(newWord);
            wordTextField.setText("");
            new NewWordBuildingWindow(this);

            System.out.println(wordTextField.getText() + " was added to myVocabList");
        } else if (e.getSource() == viewNoteBookButton) {
            new VocabListWindow(this);
            mainFrame.setVisible(false);
        } else if (e.getSource() == saveVocabListButton) {
            saveVocabList();
        } else if (e.getSource() == loadVocabListButton) {
            loadVocabList();
        }

    }


}
