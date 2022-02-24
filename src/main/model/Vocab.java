package model;

// Vocab is a vocabulary entry, with its name, its index number in the vocabList + 1,
// its definition, and its original learning context
public class Vocab {
    private String name;
    private String definition;
    private String learningContext;

    //EFFECTS: Vocab has the name of the word, a definition, and it's learning context
    public Vocab(String word) {
        this.name = word;
        this.definition = "Definition:";
        this.learningContext = "Original Learning Context:";
    }


    //MODIFIES: this
    //EFFECTS:set the name of the vocab entry as newName
    public void editName(String newName) {
        this.name = newName;
    }


    //MODIFIES: this
    //EFFECTS:add newDefinition content to the definition of the vocab entry
    public void editDefinition(String newDefinition) {
        this.definition = this.definition + " " + newDefinition;
    }

    //MODIFIES: this
    //EFFECTS:add newLearningContext content to the learningContext of the vocab entry
    public void editLearningContext(String newLearningContext) {
        this.learningContext = this.learningContext + " " + newLearningContext;
    }

    // EFFECTS: returns vocab name
    public String getName() {
        return name;
    }

    // EFFECTS: returns vocab definition
    public String getDefinition() {
        return definition;
    }

    // EFFECTS: returns vocab learningContext
    public String getLearningContext() {
        return learningContext;
    }


    //EFFECTS: print the names of vocabs in the list
    public String makeVocabCard() {
        return "************************"
                + "\nWord: " + name
                + "\n" + definition
                + "\n" + learningContext
                + "\n*************************";
    }

}
