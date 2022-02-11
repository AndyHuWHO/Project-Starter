package ui;

import model.Vocab;
import model.VocabList;

import java.util.Scanner;

public class VocabApp {
    private Vocab newWord;
    private Vocab wordBeingEdited;
    private VocabList myVocabList;
    private Scanner input;
    boolean keepGoing = true;


    public VocabApp() {
        openNotebook();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void openNotebook() {

        String mainMenuCommand;

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
        System.out.println("\nEnter your VOCABULARY to create a new entry, or:");
        System.out.println("\tv -> to view your vocab list");
        System.out.println("\tq -> quit");
    }

    //REQUIRES: user command was not v or q
    //EFFECTS: start building a new vocab entry with given name,
    // and ask user to put in definition or do other commands
    private void buildNewWord(String word) {
        newWord = new Vocab(word);
        String definitionPhaseCommand;

        displayAddDefinitionMenu();
        definitionPhaseCommand = input.next();
        definitionPhaseCommand = definitionPhaseCommand.toLowerCase();

        if (definitionPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processDefinitionPhaseCommand(definitionPhaseCommand);
        }

    }

    // EFFECTS: displays edit definition menu options to user
    private void displayAddDefinitionMenu() {
        System.out.println("\nPlease enter the DEFINITION for " + newWord.getName() + ", or:");
        System.out.println("\tm -> to main without saving the entry");
        System.out.println("\ts) -> to "
                + "save the entry without contents for definition or learning context and go back to main ");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: ask user to put in the learning context for the word or do other commands
    private void learningContextPhase() {
        String learningContextPhaseCommand;

        displayAddLearningContextMenu();
        learningContextPhaseCommand = input.next();
        learningContextPhaseCommand = learningContextPhaseCommand.toLowerCase();

        if (learningContextPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processLearningContextPhaseCommand(learningContextPhaseCommand);
        }
    }


    // EFFECTS: displays edit learning context menu options to user
    private void displayAddLearningContextMenu() {
        System.out.println("\nPlease write down your original LEARNING CONTEXT for " + newWord.getName() + ", or:");
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
            printVocabList();
            String vocabListCommand;


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
        System.out.println("\nPlease type in the word you want to REVIEW, or");
        System.out.println("\tm -> back to main");
        System.out.println("\te -> to edit a word entry");
        System.out.println("\td -> to delete a word entry");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display delete phase options
    private void deleteOneWord() {

        printVocabList();
        String deletePhaseCommand;


        displayDeletePhaseMenu();
        deletePhaseCommand = input.next();
        deletePhaseCommand = deletePhaseCommand.toLowerCase();

        if (deletePhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processDeletePhaseCommand(deletePhaseCommand);
        }

    }


    // EFFECTS: displays view delete phase menu options to user
    private void displayDeletePhaseMenu() {
        System.out.println("\nPlease type in the word you want to DELETE, or");
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your vocab list");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit phase options
    private void editWordEntry() {

        printVocabList();
        String editPhaseCommand;


        displayEditPhaseMenu();
        editPhaseCommand = input.next();
        editPhaseCommand = editPhaseCommand.toLowerCase();

        if (editPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processEditPhaseCommand(editPhaseCommand);
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditPhaseMenu() {
        System.out.println("\nPlease type in the word you want to EDIT, or");
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your vocab list");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryName(Vocab word) {
        wordBeingEdited = word;

        printVocabList();
        String editNamePhaseCommand;


        if (word == null) {
            System.out.println("It's not in the list");

        } else {
            displayEditNamePhaseMenu();
            editNamePhaseCommand = input.next();
            editNamePhaseCommand = editNamePhaseCommand.toLowerCase();

            if (editNamePhaseCommand.equals("q")) {
                keepGoing = false;
            } else {
                processEditNamePhaseCommand(editNamePhaseCommand);
            }
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditNamePhaseMenu() {
        System.out.println("\nPlease type in the new word name for:" + wordBeingEdited.getName());
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your vocab list");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryDefinition() {


        printVocabList();
        String editDefinitionPhaseCommand;


        displayEditDefinitionPhaseMenu();
        editDefinitionPhaseCommand = input.next();
        editDefinitionPhaseCommand = editDefinitionPhaseCommand.toLowerCase();

        if (editDefinitionPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processEditDefinitionPhaseCommand(editDefinitionPhaseCommand);
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditDefinitionPhaseMenu() {
        System.out.println("\nPlease type in definition for " + wordBeingEdited.getName() + " to add");
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your vocab list");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryLearningContext() {

        printVocabList();
        String editLearningContextPhaseCommand;


        displayEditLearningContextPhaseMenu();
        editLearningContextPhaseCommand = input.next();
        editLearningContextPhaseCommand = editLearningContextPhaseCommand.toLowerCase();

        if (editLearningContextPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processEditLearningContextPhaseCommand(editLearningContextPhaseCommand);
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditLearningContextPhaseMenu() {
        System.out.println("\nPlease type in learning context for " + wordBeingEdited.getName() + " to add");
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
    private void processLearningContextPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu, word not saved");
        } else if (command.equals("s")) {
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + "is saved in your notebook "
                    + "without original learning context");
        } else {
            newWord.editLearningContext(command);
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + " is saved with definition and original learning context");

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at vocabList viewing phase
    private void processVocabListCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("d")) {
            deleteOneWord();
        } else if (command.equals("e")) {
            editWordEntry();
        } else {
            viewVocabEntry(command);
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
            deleteVocab(command);
            viewNotebook();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            editEntryName(findVocab(command));
            viewNotebook();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditNamePhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            wordBeingEdited.editName(command);
            System.out.println("name had been edited");
            editEntryDefinition();
            viewNotebook();

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditDefinitionPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            wordBeingEdited.editDefinition(command);
            System.out.println("definition had been edited");
            editEntryLearningContext();


        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditLearningContextPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewNotebook();
        } else {
            wordBeingEdited.editLearningContext(command);
            System.out.println("learning context had been edited");
            viewNotebook();

        }
    }


    //EFFECTS: delete a vocab from the list
    private void deleteVocab(String word) {
        for (Vocab v: myVocabList.getVocabList()) {
            if (v.getName().equals(word)) {
                myVocabList.getVocabList().remove(v);
                System.out.println(word + " is deleted from the list");
            }

        }
        System.out.println(word + " is not in the list");
    }



    //EFFECTS: print the names of vocabs in the list
    private void printVocabList() {
        for (Vocab v: myVocabList.getVocabList()) {
            System.out.println(v.getName());
        }
    }


    //EFFECTS: print the names of vocabs in the list
    public void viewVocabEntry(String word) {
        for (Vocab v: myVocabList.getVocabList()) {
            if (v.getName().equals(word)) {
                System.out.println("Word: " + v.getName());
                System.out.println(v.getDefinition());
                System.out.println(v.getLearningContext());
                return;
            }

        }
        System.out.println(word + " is not in the list");
    }

    //EFFECTS: get a vocab from the list
    public Vocab findVocab(String word) {
        for (Vocab v: myVocabList.getVocabList()) {
            if (v.getName().equals(word)) {
                return v;
            }

        }
        System.out.println(word + " is not in the list");
        return null;
    }


}



