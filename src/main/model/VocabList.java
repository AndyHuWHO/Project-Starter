package model;

import java.util.ArrayList;

public class VocabList {
    ArrayList<Vocab> vocabList;

    //A list of the Vocab entries
    public VocabList() {
        this.vocabList = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: add new Vocab Entry
    public void addVocab(Vocab name) {
        vocabList.add(name);
    }


    //EFFECTS: print the names of vocabs in the list
    public void printVocabList() {
        for (Vocab v: vocabList) {
            System.out.println(v.getName());
        }
    }

    //EFFECTS: print the names of vocabs in the list
    public void viewVocabEntry(String word) {
        for (Vocab v: vocabList) {
            if (v.getName().equals(word)) {
                System.out.println("Word: " + v.getName());
                System.out.println(v.getDefinition());
                System.out.println(v.getLearningContext());
                return;
            }

        }
        System.out.println(word + " is not in the list");
    }


    //EFFECTS: delete a vocab from the list
    public void deleteVocab(String word) {
        for (Vocab v: vocabList) {
            if (v.getName().equals(word)) {
                vocabList.remove(v);
                System.out.println(word + " is deleted from the list");
                return;
            }

        }
        System.out.println(word + " is not in the list");
    }

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
