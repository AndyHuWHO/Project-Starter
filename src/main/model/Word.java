package model;

// Word is a second language word entry, with its name, definition, and its original learning context
public class Word {
    private String name;
    private String definition;
    private String learningContext;

    //EFFECTS: Word has the name of the word, a definition, and its original learning context
    public Word(String word) {
        this.name = word;
        this.definition = "Definition:";
        this.learningContext = "Original Learning Context:";
    }


    //MODIFIES: this
    //EFFECTS:set the name of the word entry
    public void editName(String newName) {
        this.name = newName;
    }


    //MODIFIES: this
    //EFFECTS:add newDefinition content to the definition of the word entry
    public void editDefinition(String newDefinition) {
        this.definition = this.definition + " " + newDefinition;
    }

    //MODIFIES: this
    //EFFECTS:add newLearningContext content to the learningContext of the word entry
    public void editLearningContext(String newLearningContext) {
        this.learningContext = this.learningContext + " " + newLearningContext;
    }

    // EFFECTS: returns word name
    public String getName() {
        return name;
    }

    // EFFECTS: returns word definition
    public String getDefinition() {
        return definition;
    }

    // EFFECTS: returns word learningContext
    public String getLearningContext() {
        return learningContext;
    }


    //EFFECTS: to string the word entry name, definition, and learning context together to make a word card
    public String makeWordCard() {
        return "************************"
                + "\nWord: " + name
                + "\n" + definition
                + "\n" + learningContext
                + "\n*************************";
    }

}
