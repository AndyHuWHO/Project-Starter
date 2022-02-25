package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class VocabListTest {
    private VocabList testVocabList;
    private Word word1;
    private Word word2;

    @BeforeEach
    void runBefore() {
        testVocabList = new VocabList();
        word1 = new Word("one");
        word2 = new Word("two");
    }


    @Test
    void testVocabListConstructor() {
        assertEquals(0, testVocabList.getSize());
    }


    @Test
    void testAddWord() {
        testVocabList.addWord(word1);
        assertEquals(1, testVocabList.getSize());
        assertTrue(testVocabList.containsWord(word1));
        testVocabList.addWord(word2);
        assertEquals(2, testVocabList.getSize());
        assertTrue(testVocabList.containsWord(word1));
        assertTrue(testVocabList.containsWord(word2));
    }


    @Test
    void testDeleteWordByName() {
        assertFalse(testVocabList.deleteWordByName("one"));
        testVocabList.addWord(word1);
        testVocabList.addWord(word2);
        assertFalse(testVocabList.deleteWordByName("three"));
        assertTrue(testVocabList.deleteWordByName("one"));
        assertEquals(1,testVocabList.getSize());
        assertFalse(testVocabList.containsWord(word1));
        assertFalse(testVocabList.deleteWordByName("one"));
        assertTrue(testVocabList.deleteWordByName("two"));
        assertTrue(testVocabList.isEmpty());
    }


    @Test
    void testFindWord() {
        assertNull(testVocabList.findWord("one"));
        testVocabList.addWord(word2);
        testVocabList.addWord(word1);
        assertEquals(word1,testVocabList.findWord("one"));
        assertEquals(word2,testVocabList.findWord("two"));
        assertNull(testVocabList.findWord("three"));
    }


    @Test
    void testGetVocabList() {
        assertEquals(0,testVocabList.getVocabList().size());
        testVocabList.addWord(word2);
        testVocabList.addWord(word1);
        assertEquals(2,testVocabList.getVocabList().size());
        assertTrue(testVocabList.getVocabList().remove(word1));
        assertEquals(1,testVocabList.getVocabList().size());
    }


    @Test
    void testIsEmpty() {
        assertTrue(testVocabList.isEmpty());
        testVocabList.addWord(word2);
        assertFalse(testVocabList.isEmpty());
    }


    @Test
    void testContainsWord() {
        testVocabList.addWord(word1);
        assertTrue(testVocabList.containsWord(word1));
        assertFalse(testVocabList.containsWord(word2));
        testVocabList.addWord(word2);
        assertTrue(testVocabList.containsWord(word1));
        assertTrue(testVocabList.containsWord(word2));
    }


    @Test
    void testToListVocabulary() {
        assertEquals("****************************\n" +
                "Below is your vocab list:\n" +
                "****************************",testVocabList.toListVocabulary());
        testVocabList.addWord(word1);
        testVocabList.addWord(word2);
        assertEquals("****************************\nBelow is your vocab list:\n" +
                        "1. one\n2. two\n****************************",
                testVocabList.toListVocabulary());
    }


}
