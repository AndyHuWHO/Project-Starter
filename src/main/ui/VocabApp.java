package ui;

import model.Vocab;
import model.VocabList;

import java.util.Scanner;

public class VocabApp {
    private Vocab newWord;
    private VocabList myVocabList;
    private Scanner input;
    boolean keepGoing = true;


    public VocabApp() {
        openNotebook();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void openNotebook() {

        String mainMenuCommand = null;

        init();

        while (keepGoing) {
            displayMainMenu();
            mainMenuCommand = input.next();
            mainMenuCommand = mainMenuCommand.toLowerCase();

            if (mainMenuCommand.equals("q")) {
                keepGoing = false;
            } else {
                processMainMenuCommand(mainMenuCommand);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes VocabApp
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        myVocabList = new VocabList();
    }

    // EFFECTS: displays main menu options to user
    private void displayMainMenu() {
        System.out.println("\nEnter your vocab to create a new entry, or:");
        System.out.println("\tv -> to view your vocab list");
        System.out.println("\tq -> quit");
    }

    //REQUIRES: user command was not v or q
    //EFFECTS: start building a new vocab entry with given name,
    // and ask user to put in definition or do other commands
    private void buildNewWord(String word) {
        newWord = new Vocab(word);
        String definitionPhaseCommand = null;

        displayEditDefinitionMenu();
        definitionPhaseCommand = input.next();
        definitionPhaseCommand = definitionPhaseCommand.toLowerCase();

        if (definitionPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processDefinitionPhaseCommand(definitionPhaseCommand);
        }

    }

    // EFFECTS: displays edit definition menu options to user
    private void displayEditDefinitionMenu() {
        System.out.println("\nPlease enter the definition for " + newWord.getName() + ", or:");
        System.out.println("\tm -> to main without saving the entry");
        System.out.println("\ts) -> to "
                + "save the entry without contents for definition or learning context and go back to main ");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: ask user to put in the learning context for the word or do other commands
    private void learningContextPhase() {
        String learningContextPhaseCommand = null;

        displayEditLearningContextMenu();
        learningContextPhaseCommand = input.next();
        learningContextPhaseCommand = learningContextPhaseCommand.toLowerCase();

        if (learningContextPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processLearningPhaseCommand(learningContextPhaseCommand);
        }
    }


    // EFFECTS: displays edit learning context menu options to user
    private void displayEditLearningContextMenu() {
        System.out.println("\nPlease write down your original learning context for " + newWord.getName() + ", or:");
        System.out.println("\tm -> to main without saving the entry");
        System.out.println("\ts) -> to "
                + "save the entry with the definition entered but without content for learning context");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: display vocab names in the vocab list with user options.
    private void viewNotebook() {
        if (myVocabList.isEmpty()) {
            System.out.println("\nThere's no words in your list yet");
        } else {
            myVocabList.printVocabList();
            String vocabListCommand = null;


            displayViewVocabListMenu();
            vocabListCommand = input.next();
            vocabListCommand = vocabListCommand.toLowerCase();

            if (vocabListCommand.equals("q")) {
                keepGoing = false;
            } else {
                processVocabListCommand(vocabListCommand);
            }

        }
    }


    // EFFECTS: displays view vocabularyList menu options to user
    private void displayViewVocabListMenu() {
        System.out.println("\nPlease type in the word you want to review:");
        System.out.println("\tm -> back to main");
        System.out.println("\td -> to delete a word entry");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display delete phase options
    private void deleteOneWord() {

        myVocabList.printVocabList();
        String deletePhaseCommand = null;


        displayDeletePhaseMenu();
        deletePhaseCommand = input.next();
        deletePhaseCommand = deletePhaseCommand.toLowerCase();

        if (deletePhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processDeletePhaseCommand(deletePhaseCommand);
        }

    }


    // EFFECTS: displays view vocabularyList menu options to user
    private void displayDeletePhaseMenu() {
        System.out.println("\nPlease type in the word entry you want to delete:");
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your vocab list");
        System.out.println("\tq -> quit");
    }




    // MODIFIES: this
    // EFFECTS: processes user command at main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("v")) {
            viewNotebook();
        } else {
            buildNewWord(command);
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command at definition phase
    private void processDefinitionPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu, word not saved");
        } else if (command.equals("s")) {
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + "is saved in your notebook "
                    + "with no definition or original learning context");
        } else {
            newWord.editDefinition(command);
            learningContextPhase();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at learning context phase
    private void processLearningPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu, word not saved");
        } else if (command.equals("s")) {
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + "is saved in your notebook "
                    + "without original learning context");
        } else {
            newWord.editLearningContext(command);
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + "is saved with definition and original learning context");

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at vocabList viewing phase
    private void processVocabListCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("d")) {
            deleteOneWord();
        } else {
            myVocabList.viewVocabEntry(command);
            viewNotebook();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processDeletePhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            myVocabList.deleteVocab(command);
            viewNotebook();

        }
    }

}



