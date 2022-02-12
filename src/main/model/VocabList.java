package model;

import java.util.ArrayList;

//VocabList is a list of Vocabs, which are vocabulary entries.
public class VocabList {
    ArrayList<Vocab> vocabList;

    //A new empty list of the Vocab entries
    public VocabList() {
        this.vocabList = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: add new Vocab Entry into VocabList
    public void addVocab(Vocab name) {
        vocabList.add(name);
    }


    //returns the vocabList
    public ArrayList<Vocab> getVocabList() {
        return vocabList;
    }

    public boolean isEmpty() {
        return vocabList.isEmpty();
    }

    public int getSize() {
        return vocabList.size();
    }

    public boolean containsVocab(Vocab v) {
        return vocabList.contains(v);
    }


}
