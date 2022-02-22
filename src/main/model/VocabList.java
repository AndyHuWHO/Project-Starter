package model;

import java.util.ArrayList;

//VocabList is a list of Vocabs
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


    //MODIFIES: this
    //EFFECTS: remove a vocab of the given name from the vocab list if the vocab is in the list and return true
    //         if there is no vocab of the given name in the list, return false
    public boolean deleteVocab(String word) {
        for (Vocab v : vocabList) {
            if (v.getName().equals(word)) {
                vocabList.remove(v);
                return true;
            }
        }
        return false;

    }


    //EFFECTS: get a vocab from the list
    public Vocab findVocab(String word) {
        for (Vocab v : vocabList) {
            if (v.getName().equals(word)) {
                return v;
            }
        }
        return null;
    }


    //EFFECTS: returns the vocabList which is ArrayList<Vocab>
    public ArrayList<Vocab> getVocabList() {
        return vocabList;
    }

    // EFFECTS: to check if the vocab list is empty, return true if yes and false otherwise
    public boolean isEmpty() {
        return vocabList.isEmpty();
    }

    // EFFECTS: to get the number of vocab entries in the vocab list
    public int getSize() {
        return vocabList.size();
    }

    // EFFECTS: to check if a specific vocab is in the vocab list, return true if yes and false otherwise
    public boolean containsVocab(Vocab v) {
        return vocabList.contains(v);
    }

    //EFFECTS: to make a list of the vocab names in the vocab list
    public String toString() {
        String result = "";
        for (Vocab v: vocabList) {
            result = result + (vocabList.indexOf(v) + 1) + ". " + v.getName() + "\n";
        }
        return result;
    }


}
