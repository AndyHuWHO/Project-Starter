package ui;

import model.VocabList;
import model.Word;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Window for building new word to Notebook
public class NewWordBuildingWindowGUI implements ActionListener {
    Word newWord;
    VocabList myVocabList;

    JFrame wordBuildingFrame;

    JPanel centerPanel;
    JTextArea definitionArea;
    JTextArea learningContextArea;
    JPanel saveCancelPanel;
    JButton saveButton;
    JButton cancelButton;

    //construct a new word building window
    public NewWordBuildingWindowGUI(MainNotebookWindowGUI notebookWindow) {
        this.newWord = notebookWindow.newWord;
        this.myVocabList = notebookWindow.myVocabList;
        setupWordBuildingFrame();
        setupNavigationPanel();
        setupCenterPanel();

    }

    //set up the frame in this class
    private void setupWordBuildingFrame() {
        wordBuildingFrame = new JFrame();
        wordBuildingFrame.setTitle("My Vocabulary Notebook");
        wordBuildingFrame.setSize(600, 550);
        wordBuildingFrame.setLayout(new BorderLayout());
        wordBuildingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        wordBuildingFrame.getContentPane().setBackground(new Color(195, 243, 241));
        wordBuildingFrame.setVisible(true);
    }

    //// set up the center JPanel for this class
    private void setupCenterPanel() {
        definitionArea = new JTextArea(newWord.getDefinition() + " \n", 5, 20);
        definitionArea.setBounds(50, 150, 400, 100);
        definitionArea.setLineWrap(true);
        definitionArea.setWrapStyleWord(true);
        learningContextArea = new JTextArea(newWord.getLearningContext() + " \n", 5, 20);
        learningContextArea.setBounds(50, 300, 400, 100);
        learningContextArea.setLineWrap(true);
        learningContextArea.setWrapStyleWord(true);

        JLabel wordLabel = new JLabel("Enter information for \"" + newWord.getName() + "\" below:");
        wordLabel.setForeground(new Color(250, 24, 77));
        wordLabel.setFont(new Font("MP", Font.BOLD, 20));
        wordLabel.setBounds(50, 110, 550, 30);

        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(600, 400));
        centerPanel.setLayout(null);
        centerPanel.setBackground(new Color(195, 243, 241));
        centerPanel.add(definitionArea);
        centerPanel.add(learningContextArea);
        centerPanel.add(wordLabel);

        wordBuildingFrame.add(centerPanel, BorderLayout.CENTER);

    }



    //// set up the Navigation JPanel for main frame
    private void setupNavigationPanel() {
        saveCancelPanel = new JPanel();
        //navigationPanel.setBackground(new Color(255, 255, 255));
        saveCancelPanel.setPreferredSize(new Dimension(600, 100));
        saveCancelPanel.setLayout(new GridLayout());
        saveButton = new JButton("Save");
        saveButton.setBounds(0, 0, 200, 80);
        cancelButton = new JButton("Cancel");
        saveCancelPanel.add(saveButton);
        saveButton.addActionListener(this);
        saveCancelPanel.add(cancelButton);
        cancelButton.addActionListener(this);
        wordBuildingFrame.add(saveCancelPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            newWord.updateDefinition(definitionArea.getText());
            newWord.updateLearningContext(learningContextArea.getText());
            wordBuildingFrame.dispose();
        } else if (e.getSource() == cancelButton) {
            myVocabList.getVocabList().remove(newWord);
            wordBuildingFrame.dispose();
        }

    }
}
