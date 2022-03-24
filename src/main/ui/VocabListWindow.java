package ui;

import model.VocabList;
import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VocabListWindow extends JFrame implements ActionListener {
    private JScrollPane vocabListScrollPane;
    private JList list;
    private DefaultListModel listModel;
    private JPanel navigationPanel;
    private JPanel optionPanel;
    private JButton backButton;
    private final JFrame myFrame;
    private VocabList myVocabList;


    public VocabListWindow(NotebookWindow notebookWindow) {
        this.myFrame = notebookWindow.mainFrame;
        this.myVocabList = notebookWindow.myVocabList;
        setupFrame();
        setVisible(true);
        setupNavigationPanel();
        add(navigationPanel);
        setupScrollPane();
        add(vocabListScrollPane);


    }


    private void setupFrame() {
        setTitle("My Vocabulary Notebook");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(195, 243, 241));
    }


    private void setupScrollPane() {
        listModel = new DefaultListModel();
        //listModel.addElement("try");
        renderScrollPane();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        //list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        vocabListScrollPane = new JScrollPane(list);
        vocabListScrollPane.setBounds(50, 50, 300, 300);
        //vocabListScrollPane.setOpaque(true);
    }

    private void renderScrollPane() {
        for (Word w: myVocabList.getVocabList()) {
            listModel.addElement(w.getName());

        }
    }


    //// set up the Navigation JPanel for main frame
    private void setupNavigationPanel() {
        navigationPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        navigationPanel.setBounds(0, 400, 600, 80);
        navigationPanel.setLayout(null);
        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 200, 80);
        //viewNoteBookButton.setBackground(new Color(220, 187, 102));
        backButton.addActionListener(this);
        navigationPanel.add(backButton);
    }

    private void setupBackButton() {
        backButton = new JButton("Back");
        backButton.setBounds(0, 400, 200, 100);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
            myFrame.setVisible(true);
            System.out.println("I did it");
        }

    }
}
