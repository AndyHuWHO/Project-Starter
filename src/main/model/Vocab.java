package model;

// Vocab is a vocabulary entry, with its name, definition, and original learning context
public class Vocab {
    private String name;
    private String definition;
    private String learningContext;

    //EFFECTS: Vocab has the name of the word, a definition, and it's learning context
    public Vocab(String word) {
        this.name = word;
        this.definition = "Definition: ";
        this.learningContext = "Original Learning Context for " + word + ":";
    }


    //MODIFIES: this
    //EFFECTS:edit the name of the vocab entry
    public void editName(String newName) {
        this.name = newName;
        this.learningContext = "Original Learning Context for " + newName + ":";
    }

    //MODIFIES: this
    //EFFECTS:edit the definition of the vocab entry
    public void editDefinition(String newDefinition) {
        this.definition = this.definition +  newDefinition;
    }

    //MODIFIES: this
    //EFFECTS:edit the original learning context of the vocab entry
    public void editLearningContext(String newLearningContext) {
        this.learningContext = this.learningContext + "\n" + newLearningContext;
    }




    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns definition
    public String getDefinition() {
        return definition;
    }

    // EFFECTS: returns learningContext
    public String getLearningContext() {
        return learningContext;
    }

}
