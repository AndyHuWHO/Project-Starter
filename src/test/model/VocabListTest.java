package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class VocabListTest {
    private VocabList testVocabList;
    private Vocab vocab1;
    private Vocab vocab2;

    @BeforeEach
    void runBefore() {
        testVocabList = new VocabList();
        vocab1 = new Vocab("One");
        vocab2 = new Vocab("two");
    }

    @Test
    void testVocabListConstructor() {
        assertEquals(0, testVocabList.getSize());
    }

    @Test
    void testAddVocab() {
        testVocabList.addVocab(vocab1);
        assertEquals(1, testVocabList.getSize());
        assertTrue(testVocabList.containsVocab(vocab1));
        testVocabList.addVocab(vocab2);
        assertEquals(2, testVocabList.getSize());
        assertTrue(testVocabList.containsVocab(vocab1));
        assertTrue(testVocabList.containsVocab(vocab2));
    }

    @Test
    void testIsEmpty() {
        assertTrue(testVocabList.isEmpty());
        testVocabList.addVocab(vocab2);
        assertFalse(testVocabList.isEmpty());
    }

    @Test
    void testContainsVocab() {
        testVocabList.addVocab(vocab1);
        assertTrue(testVocabList.containsVocab(vocab1));
        assertFalse(testVocabList.containsVocab(vocab2));
        testVocabList.addVocab(vocab2);
        assertTrue(testVocabList.containsVocab(vocab1));
        assertTrue(testVocabList.containsVocab(vocab2));
    }



}
