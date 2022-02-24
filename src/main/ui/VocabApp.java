package ui;

import model.Vocab;
import model.VocabList;
import java.util.Scanner;


//Vocabulary Notebook Application
public class VocabApp {
    private Vocab newWord;
    private Vocab wordBeingEdited;
    private Vocab wordBeingViewed;
    private VocabList myVocabList;
    private Scanner input;
    boolean keepGoing = true;    //why is this needed??


    // initialize the Vocab App with an empty VocabList and run the mainMenu
    public VocabApp() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        myVocabList = new VocabList();
        mainMenu();
    }

    // MODIFIES: this  //does it modify this??
    // EFFECTS: processes user input at mainMenu
    private void mainMenu() {
        String mainMenuCommand;

        while (keepGoing) {
            displayMainMenu();

            mainMenuCommand = input.next();
            mainMenuCommand = mainMenuCommand.trim();

            if (mainMenuCommand.equals("q")) {
                keepGoing = false;
            } else {
                processMainMenuCommand(mainMenuCommand);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // EFFECTS: displays main menu options to user
    private void displayMainMenu() {
        System.out.println("\nEnter your VOCABULARY to create a new entry, or:");
        System.out.println("\tv -> to view your vocab list");
        System.out.println("\tq -> quit");
    }


    // EFFECTS: start building a new vocab entry with given name by
    // asking user to put in definition or do other commands
    private void buildNewWord(String word) {
        newWord = new Vocab(word);
        String definitionPhaseCommand;

        displayAddDefinitionMenu();

        definitionPhaseCommand = input.next();

        if (definitionPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processDefinitionPhaseCommand(definitionPhaseCommand);
        }

    }

    // EFFECTS: displays add definition menu options to user
    private void displayAddDefinitionMenu() {
        System.out.println("\nPlease enter the DEFINITION for " + newWord.getName() + ", or:");
        System.out.println("\tm -> to main WITHOUT saving the entry");  //why was this line commented out earlier??
        System.out.println("\ts) -> to "
                + "SAVE the entry without content for definition or learning context");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: ask user to put in the learning context for the word or do other commands
    private void learningContextPhase() {
        String learningContextPhaseCommand;

        displayAddLearningContextMenu();

        learningContextPhaseCommand = input.next();

        if (learningContextPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processLearningContextPhaseCommand(learningContextPhaseCommand);
        }
    }


    // EFFECTS: displays edit learning context menu options to user
    private void displayAddLearningContextMenu() {
        System.out.println("\nPlease write down your original LEARNING CONTEXT for " + newWord.getName() + ", or:");
        System.out.println("\tm -> to main WITHOUT saving the entry");  //why was this line commented out?
        System.out.println("\ts) -> to "
                + "Save the entry without content for learning context");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: display vocab names in the vocab list with user options.
    private void viewVocabList() {
        if (myVocabList.isEmpty()) {
            System.out.println("\nThere's no words in your list yet");
        } else {
            String vocabListCommand;

            //printVocabList();
            System.out.println(myVocabList.toListVocabs());
            displayVocabListMenu();

            vocabListCommand = input.next();
            vocabListCommand = vocabListCommand.trim();

            if (vocabListCommand.equals("q")) {
                keepGoing = false;
            } else {
                processVocabListCommand(vocabListCommand);
            }

        }
    }


    // EFFECTS: displays view vocabularyList menu options to user
    private void displayVocabListMenu() {
        System.out.println("\nPlease type in the word you want to REVIEW, or");
        System.out.println("\tm -> back to main");
        System.out.println("\te -> to edit a word entry");
        System.out.println("\td -> to delete a word entry");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: display vocab entry information with user options.
    private void viewVocabEntry(String vocabName) {
        if (wordBeingViewed == null) {
            System.out.println(vocabName + " is not in the list");
            viewVocabList();
        } else {

            String vocabListCommand;

            System.out.println(wordBeingViewed.makeVocabCard());
            displayViewVocabEntryMenu();

            vocabListCommand = input.next();
            vocabListCommand = vocabListCommand.toLowerCase();


            if (vocabListCommand.equals("q")) {
                keepGoing = false;
            } else {
                processViewVocabEntryCommand(vocabListCommand);
            }
        }

    }


    // EFFECTS: displays view vocab entry menu options to user
    private void displayViewVocabEntryMenu() {
        System.out.println("\tv -> to go back and view your vocab list");
        System.out.println("\tm -> back to main");
        System.out.println("\te -> to edit the current word entry");
        System.out.println("\td -> to delete the current word entry");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display delete phase options
    private void deleteOneWord() {
        String deletePhaseCommand;

        //printVocabList();
        System.out.println(myVocabList.toListVocabs());
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
    private void editVocabEntry() {
        String editPhaseCommand;

        System.out.println(myVocabList.toListVocabs());
        displayEditPhaseMenu();

        editPhaseCommand = input.next();
        editPhaseCommand = editPhaseCommand.trim();


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


        if (wordBeingEdited == null) {
            System.out.println("It's not in the list");
            viewVocabList();

        } else {
            String editNamePhaseCommand;

            displayEditNamePhaseMenu();

            editNamePhaseCommand = input.next();
            editNamePhaseCommand = editNamePhaseCommand.trim();


            if (editNamePhaseCommand.equals("q")) {
                keepGoing = false;
            } else {
                processEditNamePhaseCommand(editNamePhaseCommand);
            }
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditNamePhaseMenu() {
        System.out.println("\nPlease type in the new word NAME for [" + wordBeingEdited.getName() + "], or");
        System.out.println("\tv -> cancel and go back to view your vocab list");
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryDefinition() {
        String editDefinitionPhaseCommand;

        displayEditDefinitionPhaseMenu();

        editDefinitionPhaseCommand = input.next();

        if (editDefinitionPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processEditDefinitionPhaseCommand(editDefinitionPhaseCommand);
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditDefinitionPhaseMenu() {
        System.out.println("\nPlease add definition for [" + wordBeingEdited.getName() + "], or");
        System.out.println("\tv -> back to view your vocab list");  //last step edit is automatically saved
        System.out.println("\tq -> quit");
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryLearningContext() {
        String editLearningContextPhaseCommand;

        displayEditLearningContextPhaseMenu();

        editLearningContextPhaseCommand = input.next();

        if (editLearningContextPhaseCommand.equals("q")) {
            keepGoing = false;
        } else {
            processEditLearningContextPhaseCommand(editLearningContextPhaseCommand);
        }

    }


    // EFFECTS: displays view edit entry phase menu options to user
    private void displayEditLearningContextPhaseMenu() {
        System.out.println("\nPlease add learning context for [" + wordBeingEdited.getName() + "], or");
        System.out.println("\tv -> back to view your vocab list");  //last step edit is automatically saved
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: processes user command at main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("v")) {
            viewVocabList();
        } else {
            buildNewWord(command);
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command at definition phase
    private void processDefinitionPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu, word NOT saved");
        } else if (command.equals("s")) {
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + " is SAVED in your notebook "
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
            System.out.println("going back to main menu, word NOT saved");
        } else if (command.equals("s")) {
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + " is SAVED in your notebook "
                    + "without original learning context");
        } else {
            newWord.editLearningContext(command);
            myVocabList.addVocab(newWord);
            System.out.println(newWord.getName() + " is SAVED with definition and original learning context");

        }
    }



    // MODIFIES: this
    // EFFECTS: processes user command at vocabList viewing phase
    private void processVocabListCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("d")) {
            deleteOneWord();    //might not want this
        } else if (command.equals("e")) {
            editVocabEntry();
        } else {
            wordBeingViewed = myVocabList.findVocab(command);
            viewVocabEntry(command);

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at vocab entry viewing phase
    private void processViewVocabEntryCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewVocabList();   //needed?
        } else if (command.equals("d")) {
            myVocabList.getVocabList().remove(wordBeingViewed);
            System.out.println(wordBeingViewed.getName() + " is deleted from your vocab list");
            viewVocabList();  //needed?
        } else if (command.equals("e")) {
            editEntryName(wordBeingViewed);
        } else {
            System.out.println("invalid command");

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processDeletePhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewVocabList();
        } else {
            if (myVocabList.deleteVocabByName(command)) {
                System.out.println(command + " is deleted from the list");
            } else {
                System.out.println(command + " is not in the list");
            }
            //myVocabList.deleteVocab(command);
            viewVocabList();

        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewVocabList();
        } else {
            editEntryName(myVocabList.findVocab(command));

        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditNamePhaseCommand(String command) {
        if (command.equals("v")) {
            viewVocabList();
        }  else {
            wordBeingEdited.editName(command);
            System.out.println("Name had been updated");
            editEntryDefinition();
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditDefinitionPhaseCommand(String command) {
        if (command.equals("v")) {
            viewVocabList();
        } else {
            wordBeingEdited.editDefinition(command);
            System.out.println("Definition had been updated");
            editEntryLearningContext();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete vocab phase
    private void processEditLearningContextPhaseCommand(String command) {
        if (!command.equals("v")) {
            wordBeingEdited.editLearningContext(command);
            System.out.println("Learning context had been updated");
        }
        viewVocabList();
    }



}


