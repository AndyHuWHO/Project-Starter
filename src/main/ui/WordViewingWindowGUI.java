package ui;

import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Window for viewing a word entry in Notebook
public class WordViewingWindowGUI implements ActionListener {
    private final Word wordBeingViewed;

    private JFrame wordViewingFrame;

    private JPanel centerPanel;
    private JTextArea definitionTextArea;
    private JTextArea learningContextTextArea;

    private JPanel optionsPanel;
    private JButton updateButton;
    private JButton backButton;


    //construct a word viewing window
    public WordViewingWindowGUI(Word word) {
        this.wordBeingViewed = word;
        setupWordBuildingFrame();
        setupNavigationPanel();
        setupCenterPanel();

    }



    //set up the frame for this class
    private void setupWordBuildingFrame() {
        wordViewingFrame = new JFrame();
        wordViewingFrame.setTitle("My Vocabulary Notebook");
        wordViewingFrame.setSize(600, 500);
        wordViewingFrame.setLayout(new BorderLayout());
        wordViewingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wordViewingFrame.getContentPane().setBackground(new Color(195, 243, 241));
        wordViewingFrame.setVisible(true);
    }

    //// set up the Navigation JPanel for main frame
    private void setupCenterPanel() {
        definitionTextArea = new JTextArea(wordBeingViewed.getDefinition() + " \n", 5, 20);
        definitionTextArea.setBounds(50, 100, 400, 100);
        definitionTextArea.setLineWrap(true);
        definitionTextArea.setWrapStyleWord(true);
        learningContextTextArea = new JTextArea(wordBeingViewed.getLearningContext() + " \n", 5, 20);
        learningContextTextArea.setBounds(50, 250, 400, 100);
        learningContextTextArea.setLineWrap(true);
        learningContextTextArea.setWrapStyleWord(true);


        JLabel wordLabel = new JLabel(wordBeingViewed.getName());
        wordLabel.setForeground(new Color(250, 24, 77));
        wordLabel.setFont(new Font("MP", Font.BOLD, 20));
        wordLabel.setBounds(50, 60, 350, 30);


        centerPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        centerPanel.setPreferredSize(new Dimension(600, 400));
        centerPanel.setLayout(null);
        centerPanel.setBackground(new Color(195, 243, 241));
        centerPanel.add(definitionTextArea);
        centerPanel.add(learningContextTextArea);
        centerPanel.add(wordLabel);

        wordViewingFrame.add(centerPanel, BorderLayout.CENTER);


    }

    //// set up the Navigation JPanel for main frame
    private void setupNavigationPanel() {
        optionsPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        optionsPanel.setPreferredSize(new Dimension(600, 100));
        optionsPanel.setLayout(new GridLayout());
        updateButton = new JButton("Update");
        updateButton.setBounds(0, 0, 200, 80);
        backButton = new JButton("Back");
        optionsPanel.add(updateButton);
        updateButton.addActionListener(this);
        optionsPanel.add(backButton);
        backButton.addActionListener(this);
        wordViewingFrame.add(optionsPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            wordBeingViewed.updateDefinition(definitionTextArea.getText());
            wordBeingViewed.updateLearningContext(learningContextTextArea.getText());
            wordViewingFrame.dispose();
        } else if (e.getSource() == backButton) {
            wordViewingFrame.dispose();
        }


    }
}
