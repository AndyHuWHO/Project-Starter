package ui;

import model.Word;
import model.VocabList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


//Vocabulary Notebook Application for second language learner
public class VocabularyNotebook {
    private static final String JSON_STORE = "./data/vocabList.json";
    private Word newWord;
    private Word wordBeingEdited;
    private Word wordBeingViewed;
    private VocabList myVocabList;
    private final Scanner input;
    boolean keepGoing = true;    //why is this needed??
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    // initialize the VocabularyNotebook Application with an empty VocabList and run the mainMenu
    public VocabularyNotebook() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        myVocabList = new VocabList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        beforeMainMenu();
    }


    // MODIFIES: this  //does it modify this??
    // EFFECTS: processes user input at mainMenu
    private void beforeMainMenu() {
        String beforeMainMenuCommand;

        displayBeforeMainMenu();

        beforeMainMenuCommand = input.next();
        beforeMainMenuCommand = beforeMainMenuCommand.trim();

        if (beforeMainMenuCommand.equals("y")) {
            loadWorkRoom();
        } else {
            System.out.println("Previous Vocabulary List is NOT loaded. Starting with a new Vocabulary Notebook");
        }
        mainMenu();

    }

    // EFFECTS: displays main menu options to user
    private void displayBeforeMainMenu() {
        System.out.println("Welcome to your personalized Vocabulary Notebook Application!");
        System.out.println("\nWould you like to LOAD previously saved Vocabulary List? "
                + "Please enter [y]  for YES or [n] for NO");
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
        beforeQuitting();
    }

    // EFFECTS: displays main menu options to user
    private void displayMainMenu() {
        System.out.println("\nEnter a new WORD to create a new word entry, or:");
        System.out.println("\tv -> to view your Vocabulary List");
        System.out.println("\ts -> save Vocabulary List to file");
        System.out.println("\tl -> load Vocabulary List from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("v")) {
            viewVocabList();
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            buildNewWord(command);
        }
    }



    // EFFECTS: start building a new word entry with given name by
    // asking user to put in definition or do other commands
    private void buildNewWord(String word) {
        newWord = new Word(word);
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
        System.out.println("\nPlease enter the DEFINITION for [" + newWord.getName() + "], or:");
        System.out.println("\tm -> to main WITHOUT saving the word entry");
        System.out.println("\ts) -> to "
                + "SAVE the word entry without content for definition or learning context");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at definition phase
    private void processDefinitionPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu, word NOT saved");
        } else if (command.equals("s")) {
            myVocabList.addWord(newWord);
            System.out.println(newWord.getName() + " is SAVED in your notebook "
                    + "with no definition or original learning context");
        } else {
            newWord.editDefinition(command);
            learningContextPhase();
        }
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
        System.out.println("\nPlease write down your original LEARNING CONTEXT for [" + newWord.getName() + "], or:");
        System.out.println("\tm -> to main WITHOUT saving the word entry");
        System.out.println("\ts) -> to "
                + "Save the word entry without content for learning context");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at learning context phase
    private void processLearningContextPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu, word NOT saved");
        } else if (command.equals("s")) {
            myVocabList.addWord(newWord);
            System.out.println(newWord.getName() + " is SAVED in your notebook "
                    + "without original learning context");
        } else {
            newWord.editLearningContext(command);
            myVocabList.addWord(newWord);
            System.out.println(newWord.getName() + " is SAVED with definition and original learning context");
        }
    }


    //EFFECTS: display word names in the vocab list with user options.
    private void viewVocabList() {
        if (myVocabList.isEmpty()) {
            System.out.println("\nThere's no words in your Vocabulary List yet");
        } else {
            String vocabListCommand;

            System.out.println(myVocabList.toListVocabulary());
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
        System.out.println("\nPlease select word you want to REVIEW, or");
        System.out.println("\tm -> back to main");
        System.out.println("\ts -> save Vocabulary List to file");
        System.out.println("\tl -> load Vocabulary List from file");
        System.out.println("\te -> to EDIT a word entry");
        System.out.println("\td -> to DELETE a word entry");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at vocabList viewing phase
    private void processVocabListCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else if (command.equals("d")) {
            deleteOneWord();    //might not want this
        } else if (command.equals("e")) {
            editWordEntry();
        } else {
            wordBeingViewed = myVocabList.findWordByIndex(command);
            viewWordEntry(command);
        }
    }


    //EFFECTS: display word entry information with user options.
    private void viewWordEntry(String wordIndex) {
        if (wordBeingViewed == null) {
            System.out.println("There is no word with index number " + wordIndex + " in the list");
            viewVocabList();
        } else {
            String vocabListCommand;

            System.out.println(wordBeingViewed.makeWordCard());
            displayViewWordEntryMenu();

            vocabListCommand = input.next();
            vocabListCommand = vocabListCommand.toLowerCase();

            if (vocabListCommand.equals("q")) {
                keepGoing = false;
            } else {
                processViewWordEntryCommand(vocabListCommand);
            }
        }
    }

    // EFFECTS: displays view word entry menu options to user
    private void displayViewWordEntryMenu() {
        System.out.println("\tv -> to go back and view your Vocabulary List");
        System.out.println("\tm -> back to main");
        System.out.println("\te -> to EDIT the current word entry");
        System.out.println("\td -> to DELETE the current word entry");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at word entry viewing phase
    private void processViewWordEntryCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewVocabList();   //needed?
        } else if (command.equals("d")) {
            myVocabList.getVocabList().remove(wordBeingViewed);
            System.out.println(wordBeingViewed.getName() + " is deleted from your Vocabulary List");
            viewVocabList();  //needed?
        } else if (command.equals("e")) {
            editEntryName(wordBeingViewed);
        } else {
            System.out.println("invalid command");
        }
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display delete phase options
    private void deleteOneWord() {
        String deletePhaseCommand;

        System.out.println(myVocabList.toListVocabulary());
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
        System.out.println("\nPlease select the word you want to DELETE, or");
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your Vocabulary List");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at delete word phase
    private void processDeletePhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewVocabList();
        } else {
            if (!(myVocabList.deleteWordByIndex(command) == null)) {
                System.out.println("Word number " + command + " in the list was deleted");
            } else {
                System.out.println("There is no word with index number " + command + " in the list");
            }
            viewVocabList();
        }
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit phase options
    private void editWordEntry() {
        String editPhaseCommand;

        System.out.println(myVocabList.toListVocabulary());
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
        System.out.println("\nPlease select the word you want to EDIT, or");
        System.out.println("\tm -> back to main");
        System.out.println("\tv -> back to view your Vocabulary List");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at edit word phase
    private void processEditPhaseCommand(String command) {
        if (command.equals("m")) {
            System.out.println("going back to main menu");
        } else if (command.equals("v")) {
            viewVocabList();
        } else {
            editEntryName(myVocabList.findWordByIndex(command));
        }
    }


    //REQUIRES: vocabList is not empty
    //EFFECTS: display edit name phase options
    private void editEntryName(Word word) {
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
        System.out.println("\tv -> cancel and go back to view your Vocabulary List");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at edit word name phase
    private void processEditNamePhaseCommand(String command) {
        if (command.equals("v")) {
            viewVocabList();
        } else {
            wordBeingEdited.editName(command);
            System.out.println("Name had been updated");
            editEntryDefinition();
        }
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
        System.out.println("\tv -> back to view your Vocabulary List");  //last step edit is automatically saved
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at edit definition phase
    private void processEditDefinitionPhaseCommand(String command) {
        if (command.equals("v")) {
            viewVocabList();
        } else {
            wordBeingEdited.editDefinition(command);
            System.out.println("Definition had been updated");
            editEntryLearningContext();
        }
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
        System.out.println("\tv -> back to view your Vocabulary List");  //last step edit is automatically saved
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command at edit learning context phase
    private void processEditLearningContextPhaseCommand(String command) {
        if (!command.equals("v")) {
            wordBeingEdited.editLearningContext(command);
            System.out.println("Learning context had been updated");
        }
        viewVocabList();
    }


    // MODIFIES: this  //does it modify this??
    // EFFECTS: processes user input at mainMenu
    private void beforeQuitting() {
        String beforeQuittingCommand;

        displayBeforeQuittingMenu();

        beforeQuittingCommand = input.next();
        beforeQuittingCommand = beforeQuittingCommand.trim();

        if (beforeQuittingCommand.equals("y")) {
            saveWorkRoom();
            System.out.println("\nYour Vocabulary List is SAVED! See you next time!");
        } else if (beforeQuittingCommand.equals("n")) {
            System.out.println("You did NOT save your Vocabulary List. See you next time!");
        } else {
            System.out.println("Instruction not correctly read. Please try again.\n");
            beforeQuitting();
        }

    }

    // EFFECTS: displays main menu options to user
    private void displayBeforeQuittingMenu() {
        System.out.println("You are about to exit your Vocabulary Application!");
        System.out.println("\nWould you like to SAVE your Vocabulary List? "
                + "Please enter [y]  for YES or [n] for NO");
    }


    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
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
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            myVocabList = jsonReader.read();
            System.out.println("Your Vocabulary List is LOADED from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}


