package ui;

import model.VocabList;
import model.Word;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Window for view Vocabulary List in Notebook
public class VocabListWindow implements ListSelectionListener {
    private static final String JSON_STORE = "./data/testGUIVocabList.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    private JScrollPane vocabListScrollPane;
    private JList list;
    private DefaultListModel listModel;

    private JFrame vocabListFrame;
    private JPanel navigationPanel;
    private JPanel wordOptionPanel;
    private JButton backButton;
    private JButton saveVocabListButton;
    private JButton loadVocabListButton;
    private JButton viewButton;
    private JButton deleteButton;

    private final JFrame myFrame;
    private VocabList myVocabList;


    //construct a new Vocabulary list viewing window
    public VocabListWindow(NotebookWindow notebookWindow) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        this.myFrame = notebookWindow.mainFrame;
        this.myVocabList = notebookWindow.myVocabList;

        setupFrame();
        setupScrollPane();
        vocabListFrame.add(vocabListScrollPane);
        setupWordOptionPanel();
        checkEmptyList();
        vocabListFrame.add(wordOptionPanel);
        setupNavigationPanel();
        vocabListFrame.add(navigationPanel);




    }

    //disable view and delete buttons if index is < 0
    private void checkEmptyList() {
        int size = listModel.getSize();
        if (size <= 0) {
            viewButton.setEnabled(false);
            deleteButton.setEnabled(false);
        } else {
            viewButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }

    }


    //set up the frame in this class
    private void setupFrame() {
        vocabListFrame = new JFrame();
        vocabListFrame.setTitle("My Vocabulary Notebook");
        vocabListFrame.setSize(600, 500);
        vocabListFrame.setLayout(null);
        vocabListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vocabListFrame.getContentPane().setBackground(new Color(195, 243, 241));
        vocabListFrame.setVisible(true);
    }


    //set up the Scroll pane for VocabList
    private void setupScrollPane() {
        listModel = new DefaultListModel();
        renderVocabListToListModel(listModel);
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        vocabListScrollPane = new JScrollPane(list);
        vocabListScrollPane.setBounds(50, 50, 300, 300);
    }

    //put the names of the words in current vocab list to listModel
    private void renderVocabListToListModel(DefaultListModel listModel) {
        for (Word w : myVocabList.getVocabList()) {
            listModel.addElement(w.getName());

        }
    }

    //// set up the Option JPanel for main frame
    private void setupWordOptionPanel() {
        wordOptionPanel = new JPanel();
        wordOptionPanel.setBounds(350, 50, 100, 200);
        wordOptionPanel.setLayout(new GridLayout(2, 1));
        viewButton = new JButton("view word");
        viewButton.addActionListener(new WordOptionListener());
        deleteButton = new JButton("delete word");
        deleteButton.addActionListener(new WordOptionListener());

        wordOptionPanel.add(viewButton);
        wordOptionPanel.add(deleteButton);
    }


    //// set up the Navigation JPanel for main frame
    private void setupNavigationPanel() {
        navigationPanel = new JPanel();
        navigationPanel.setBounds(0, 400, 600, 80);
        navigationPanel.setLayout(new GridLayout(1, 3));
        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 200, 80);
        saveVocabListButton = new JButton("Save My Notebook");
        saveVocabListButton.setPreferredSize(new Dimension(200, 80));
        loadVocabListButton = new JButton("Load My Notebook");
        loadVocabListButton.setPreferredSize(new Dimension(200, 80));
        backButton.addActionListener(new NavigationListener());
        saveVocabListButton.addActionListener(new NavigationListener());
        loadVocabListButton.addActionListener(new NavigationListener());
        navigationPanel.add(backButton);
        navigationPanel.add(saveVocabListButton);
        navigationPanel.add(loadVocabListButton);
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
    // EFFECTS: loads VocabList from file
    private void loadVocabList() {
        try {
            this.myVocabList = jsonReader.read();

            renderVocabListToListModel(listModel);
            list.setModel(listModel);
            vocabListScrollPane.repaint();

            checkEmptyList();
            System.out.println("Your Vocabulary List is LOADED from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            deleteButton.setEnabled(list.getSelectedIndex() != -1);
        }
    }
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ListDemoProject.zip
    //Code from this method is learned from the file above

    private class NavigationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                vocabListFrame.dispose();
                myFrame.setVisible(true);
            } else if (e.getSource() == saveVocabListButton) {
                saveVocabList();
            } else if (e.getSource() == loadVocabListButton) {
                loadVocabList();

            }

        }
    }

    private class WordOptionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            if (e.getSource() == viewButton) {

                new WordViewingWindow(myVocabList.findWordByIndex(index));



            } else if (e.getSource() == deleteButton) {
                listModel.remove(index);
                myVocabList.deleteWordByIndex(index);
                checkEmptyList();

                int size = listModel.getSize();

                if (size == 0) {
                    deleteButton.setEnabled(false);

                } else {
                    if (index == listModel.getSize()) {
                        index--;
                    }

                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            }
        }
    }
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ListDemoProject.zip
    //Code from this method is learned from the file above
}
