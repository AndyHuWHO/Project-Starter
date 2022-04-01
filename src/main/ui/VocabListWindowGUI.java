package ui;

import model.EventLog;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Window for view Vocabulary List in Notebook
public class VocabListWindowGUI extends WindowAdapter implements ListSelectionListener {
    private static final String JSON_STORE = "./data/vocabList.json";
    private static final  String JSON_BACKUP = "./data/vocabListBackupMar29.json";
    private static final  String JSON_TEST = "./data/testGUIVocabList.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private final JFrame myFrame;
    private MainNotebookWindowGUI mainNotebookWindow;
    private VocabList myVocabList;

    private JFrame vocabListFrame;

    private JPanel navigationPanel;
    private JButton saveVocabListButton;
    private JButton loadVocabListButton;
    private JButton backButton;

    private JPanel centerPanel;
    private JPanel wordOptionPanel;
    private JButton viewButton;
    private JButton deleteButton;
    private JScrollPane vocabListScrollPane;
    private JList list;
    private DefaultListModel listModel;


    //construct a new Vocabulary list viewing window
    public VocabListWindowGUI(MainNotebookWindowGUI notebookWindow) {
        jsonWriter = new JsonWriter(JSON_TEST);
        jsonReader = new JsonReader(JSON_TEST);

        this.myFrame = notebookWindow.mainFrame;
        this.mainNotebookWindow = notebookWindow;
        this.myVocabList = notebookWindow.myVocabList;

        setupFrame();
        setupScrollPane();
        setupWordOptionPanel();
        setupCenterPanel();
        vocabListFrame.add(centerPanel,BorderLayout.CENTER);
        checkEmptyList();
        setupNavigationPanel();
        if (!mainNotebookWindow.loadVocabListButton.isEnabled()) {
            loadVocabListButton.setEnabled(false);
        }
        vocabListFrame.add(navigationPanel,BorderLayout.SOUTH);
    }

    //disable view and delete buttons if index is < 0
    private void checkEmptyList() {
        int size = listModel.getSize();
        //int index = list.getSelectedIndex();
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
        vocabListFrame.setSize(800, 600);
        vocabListFrame.setLayout(new BorderLayout());
        vocabListFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vocabListFrame.getContentPane().setBackground(new Color(195, 243, 241));
        vocabListFrame.setVisible(true);
        vocabListFrame.addWindowListener(this);
    }


    //set up the Scroll pane for VocabList
    private void setupScrollPane() {
        listModel = new DefaultListModel();
        renderVocabListToListModel(listModel);
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        //list.setVisibleRowCount(5);
        vocabListScrollPane = new JScrollPane(list);
        vocabListScrollPane.setBounds(100, 50, 300, 400);
        vocabListScrollPane.setName("My Vocabulary Notebook");
        vocabListScrollPane.setFont(new java.awt.Font("SansSerif", 0, 50));
    }

    //put the names of the words in current vocab list to listModel
    private void renderVocabListToListModel(DefaultListModel listModel) {
        listModel.removeAllElements();
        for (Word w : myVocabList.getVocabList()) {
            listModel.addElement((myVocabList.getVocabList().indexOf(w) + 1) + ". " + w.getName());

        }
    }

    //// set up the Option JPanel for main frame
    private void setupWordOptionPanel() {
        wordOptionPanel = new JPanel();
        wordOptionPanel.setBounds(400, 50, 100, 200);
        wordOptionPanel.setLayout(new GridLayout(2, 1));
        viewButton = new JButton("view word");
        viewButton.addActionListener(new WordOptionListener());
        deleteButton = new JButton("delete word");
        deleteButton.addActionListener(new WordOptionListener());
        mainNotebookWindow.setNavigationButtonsColor(viewButton);
        mainNotebookWindow.setNavigationButtonsColor(deleteButton);

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
        mainNotebookWindow.setNavigationButtonsColor(backButton);
        mainNotebookWindow.setNavigationButtonsColor(saveVocabListButton);
        mainNotebookWindow.setNavigationButtonsColor(loadVocabListButton);
        navigationPanel.add(backButton);
        navigationPanel.add(saveVocabListButton);
        navigationPanel.add(loadVocabListButton);
    }


    //// set up the center JPanel for this class
    private void setupCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(600, 400));
        centerPanel.setLayout(null);
        centerPanel.setBackground(new Color(195, 243, 241));
        centerPanel.add(vocabListScrollPane);
        centerPanel.add(wordOptionPanel);

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
            VocabList oldList = jsonReader.read();
            this.myVocabList = oldList.addAll(myVocabList.getVocabList());
            //this.myVocabList = jsonReader.read();
            this.mainNotebookWindow.myVocabList = this.myVocabList;


            System.out.println("Your Vocabulary List is LOADED from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
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

        int confirmed = JOptionPane.showOptionDialog(vocabListFrame,
                "SAVE your current Vocabulary list with "
                        + myVocabList.getSize() + " words in it?",
                "", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                responses,
                1);

        if (confirmed == 0) {
            saveVocabList();
            System.out.println(mainNotebookWindow.printLog(EventLog.getInstance()));
            System.exit(0);
        } else if (confirmed == 1) {
            System.out.println(mainNotebookWindow.printLog(EventLog.getInstance()));
            System.exit(0);
        }
    }




    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            deleteButton.setEnabled(list.getSelectedIndex() != -1);
//          viewButton.setEnabled(list.getSelectedIndex() != -1);
        }
        //else {
        //    viewButton.setEnabled(list.getSelectedIndex() != -1);
        //}
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
                renderVocabListToListModel(listModel);
                list.setModel(listModel);
                vocabListScrollPane.repaint();
                checkEmptyList();
                loadVocabListButton.setEnabled(false);
                mainNotebookWindow.getLoadVocabListButton().setEnabled(false);


            }

        }
    }

    private class WordOptionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            if (e.getSource() == viewButton) {

                new WordViewingWindowGUI(myVocabList.findWordByIndex(index));



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
