package ui;

import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordViewingWindow implements ActionListener {
    private Word wordBeingViewed;

    private JFrame wordViewingFrame;
    JPanel centerPanel;
    JTextField definitionField;
    JTextField learningContextField;
    JPanel optionsPanel;
    JButton updateButton;
    JButton backButton;



    public WordViewingWindow(Word word) {
        this.wordBeingViewed = word;

        setupWordBuildingFrame();
        setupNavigationPanel();
        setupCenterPanel();

    }



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
        definitionField = new JTextField(wordBeingViewed.getDefinition(),20);
        //definitionField.setPreferredSize(new Dimension(350, 100));
        definitionField.setBounds(50, 100, 350, 100);
        learningContextField = new JTextField(wordBeingViewed.getLearningContext(),20);
        //learningContextField.setPreferredSize(new Dimension(350, 100));
        learningContextField.setBounds(50, 250, 350, 100);

        centerPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        centerPanel.setPreferredSize(new Dimension(600,400));
        centerPanel.setLayout(null);
        centerPanel.setBackground(new Color(195, 243, 241));
        centerPanel.add(definitionField);
        centerPanel.add(learningContextField);

        wordViewingFrame.add(centerPanel,BorderLayout.CENTER);


    }

    //// set up the Navigation JPanel for main frame
    private void setupNavigationPanel() {
        optionsPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        optionsPanel.setPreferredSize(new Dimension(600,100));
        optionsPanel.setLayout(new GridLayout());
        updateButton = new JButton("Update");
        updateButton.setBounds(0, 0, 200, 80);
        backButton = new JButton("Back");
        optionsPanel.add(updateButton);
        updateButton.addActionListener(this);
        optionsPanel.add(backButton);
        backButton.addActionListener(this);
        wordViewingFrame.add(optionsPanel,BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            wordBeingViewed.updateDefinition(definitionField.getText());
            wordBeingViewed.updateLearningContext(learningContextField.getText());
            wordViewingFrame.dispose();
        } else if (e.getSource() == backButton) {
            wordViewingFrame.dispose();
        }


    }
}
