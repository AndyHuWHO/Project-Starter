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
        vocab1 = new Vocab("one");
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
    void testDeleteVocabByName() {
        assertFalse(testVocabList.deleteVocabByName("one"));
        testVocabList.addVocab(vocab1);
        testVocabList.addVocab(vocab2);
        assertFalse(testVocabList.deleteVocabByName("three"));
        assertTrue(testVocabList.deleteVocabByName("one"));
        assertEquals(1,testVocabList.getSize());
        assertFalse(testVocabList.containsVocab(vocab1));
        assertFalse(testVocabList.deleteVocabByName("one"));
        assertTrue(testVocabList.deleteVocabByName("two"));
        assertTrue(testVocabList.isEmpty());
    }


    @Test
    void testFindVocab() {
        assertNull(testVocabList.findVocab("one"));
        testVocabList.addVocab(vocab2);
        testVocabList.addVocab(vocab1);
        assertEquals(vocab1,testVocabList.findVocab("one"));
        assertEquals(vocab2,testVocabList.findVocab("two"));
        assertNull(testVocabList.findVocab("three"));
    }


    @Test
    void testGetVocabList() {
        assertEquals(0,testVocabList.getVocabList().size());
        testVocabList.addVocab(vocab2);
        testVocabList.addVocab(vocab1);
        assertEquals(2,testVocabList.getVocabList().size());
        assertTrue(testVocabList.getVocabList().remove(vocab1));
        assertEquals(1,testVocabList.getVocabList().size());
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


    @Test
    void testToString() {
        assertEquals("",testVocabList.toListVocabs());
        testVocabList.addVocab(vocab1);
        testVocabList.addVocab(vocab2);
        assertEquals("1. one\n2. two\n",testVocabList.toListVocabs());
    }


}
