package ui;

import model.VocabList;
import model.Word;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotebookWindow implements ActionListener {
    private static final String JSON_STORE = "./data/vocabList.json";

    private JPanel navigationPanel;
    private JTextField wordTextField;
    private JButton addWordButton;
    private JButton viewNoteBookButton;
    private JLabel mvpLabel;
    protected JFrame mainFrame;

    protected Word newWord;
    private Word wordBeingEdited;
    private Word wordBeingViewed;
    protected VocabList myVocabList;

    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // constructs the Notebook main frame
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
        navigationPanel.setLayout(null);
        viewNoteBookButton = new JButton("View My Notebook");
        viewNoteBookButton.setBounds(0, 0, 200, 80);
        //viewNoteBookButton.setBackground(new Color(220, 187, 102));
        viewNoteBookButton.addActionListener(this);
        navigationPanel.add(viewNoteBookButton);
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
        }

    }


}
