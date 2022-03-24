package ui;

import model.VocabList;
import model.Word;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VocabListWindow implements ListSelectionListener {
    private JScrollPane vocabListScrollPane;
    private JFrame vocabListFrame;
    private JList list;
    private DefaultListModel listModel;
    private JPanel navigationPanel;
    private JPanel wordOptionPanel;
    private JButton backButton;
    private JButton viewButton;
    private JButton deleteButton;
    private final JFrame myFrame;
    private VocabList myVocabList;


    public VocabListWindow(NotebookWindow notebookWindow) {
        this.myFrame = notebookWindow.mainFrame;
        this.myVocabList = notebookWindow.myVocabList;
        setupFrame();
        //setVisible(true);
        setupWordOptionPanel();
        vocabListFrame.add(wordOptionPanel);
        setupNavigationPanel();
        vocabListFrame.add(navigationPanel);
        setupScrollPane();
        vocabListFrame.add(vocabListScrollPane);


    }


    private void setupFrame() {
        vocabListFrame = new JFrame();
        vocabListFrame.setTitle("My Vocabulary Notebook");
        vocabListFrame.setSize(600, 500);
        vocabListFrame.setLayout(null);
        vocabListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vocabListFrame.getContentPane().setBackground(new Color(195, 243, 241));
        vocabListFrame.setVisible(true);
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

    //// set up the Option JPanel for main frame
    private void setupWordOptionPanel() {
        wordOptionPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        wordOptionPanel.setBounds(350, 50, 100, 200);
        wordOptionPanel.setLayout(new GridLayout(2,1));
        viewButton = new JButton("view word");
        viewButton.addActionListener(new WordOptionListener());
        deleteButton = new JButton("delete word");
        deleteButton.addActionListener(new WordOptionListener());
        //viewNoteBookButton.setBackground(new Color(220, 187, 102));

        wordOptionPanel.add(viewButton);
        wordOptionPanel.add(deleteButton);
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
        backButton.addActionListener(new NavigationListener());
        navigationPanel.add(backButton);
    }

//    private void setupBackButton() {
//        backButton = new JButton("Back");
//        backButton.setBounds(0, 400, 200, 100);
//        backButton.addActionListener(new NavigationListener());
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == backButton) {
//            this.dispose();
//            myFrame.setVisible(true);
//            System.out.println("I did it");
//        }
//
//    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                deleteButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                deleteButton.setEnabled(true);
            }
        }
    }

    private class NavigationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                vocabListFrame.dispose();
                myFrame.setVisible(true);
                System.out.println("I did it");
            }

        }
    }

    private class WordOptionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewButton) {
                System.out.println("viewing");
                System.out.println(myVocabList.findWordByIndex(list.getSelectedIndex()).getName());
                System.out.println(myVocabList.findWordByIndex(list.getSelectedIndex()).getDefinition());
                System.out.println(myVocabList.findWordByIndex(list.getSelectedIndex()).getLearningContext());
            } else if (e.getSource() == deleteButton) {
                System.out.println("will delete");


                int index = list.getSelectedIndex();
                listModel.remove(index);
                myVocabList.deleteWordByIndex(index);

                int size = listModel.getSize();

                if (size == 0) { //Nobody's left, disable firing.
                    deleteButton.setEnabled(false);

                } else { //Select an index.
                    if (index == listModel.getSize()) {
                        //removed item in last position
                        index--;
                    }

                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            }
        }
    }
}
