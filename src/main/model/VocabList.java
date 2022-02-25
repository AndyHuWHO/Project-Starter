package model;

import java.util.ArrayList;

//VocabList is a list of Words
public class VocabList {
    ArrayList<Word> vocabList;

    //A new empty list of the word entries
    public VocabList() {
        this.vocabList = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: add a new word entry into VocabList
    public void addWord(Word name) {
        vocabList.add(name);
    }


    //MODIFIES: this
    //EFFECTS: remove a word of the given name from the vocab list if the word is in the list and return true
    //         if there is no word of the given name in the list, return false
    public boolean deleteWordByName(String word) {
        for (Word v : vocabList) {
            if (v.getName().equals(word)) {
                vocabList.remove(v);
                return true;
            }
        }
        return false;
    }


    //EFFECTS: get a word of the given name from the vocab list
    public Word findWord(String word) {
        for (Word v : vocabList) {
            if (v.getName().equals(word)) {
                return v;
            }
        }
        return null;
    }


    //EFFECTS: returns the vocabList which is ArrayList<Vocab>
    public ArrayList<Word> getVocabList() {
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
    public boolean containsWord(Word v) {
        return vocabList.contains(v);
    }

    //EFFECTS: to make a list of the words' names in the vocab list
    public String toListVocabulary() {
        String result = "Below is your vocab list:";
        for (Word v: vocabList) {
            result = result + "\n" + (vocabList.indexOf(v) + 1) + ". " + v.getName();
        }
        return "****************************\n" + result + "\n****************************";
    }


}
