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
    void testDeleteWordByStringIndex() {
        assertNull(testVocabList.deleteWordByIndex("one"));
        assertNull(testVocabList.deleteWordByIndex("1"));
        testVocabList.addWord(word1);
        testVocabList.addWord(word2);
        assertNull(testVocabList.deleteWordByIndex("3"));
        assertNull(testVocabList.deleteWordByIndex("one"));
        assertEquals(word1, testVocabList.deleteWordByIndex("1"));
        assertEquals(1,testVocabList.getSize());
        assertFalse(testVocabList.containsWord(word1));
        assertNull(testVocabList.deleteWordByIndex("2"));
        assertEquals(word2, testVocabList.deleteWordByIndex("1"));
        assertTrue(testVocabList.isEmpty());
    }

    @Test
    void testDeleteWordByIndex() {
        try {testVocabList.deleteWordByIndex(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        testVocabList.addWord(word1);
        testVocabList.addWord(word2);
        testVocabList.deleteWordByIndex(1);
        assertEquals(1, testVocabList.getSize());
        assertFalse(testVocabList.containsWord(word2));
        assertTrue(testVocabList.containsWord(word1));
        try {testVocabList.deleteWordByIndex(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            //expected
        }
        try {testVocabList.deleteWordByIndex(0);
            assertTrue(testVocabList.isEmpty());
        } catch (IndexOutOfBoundsException e) {
            fail();
        }
    }


    @Test
    void testFindWordByName() {
        assertNull(testVocabList.findWordByName("one"));
        testVocabList.addWord(word2);
        testVocabList.addWord(word1);
        assertEquals(word1,testVocabList.findWordByName("one"));
        assertEquals(word2,testVocabList.findWordByName("two"));
        assertNull(testVocabList.findWordByName("three"));
    }


    @Test
    void testFindWordByStringIndex() {
        assertNull(testVocabList.findWordByIndex("1"));
        assertNull(testVocabList.findWordByIndex("one"));
        testVocabList.addWord(word2);
        testVocabList.addWord(word1);
        assertEquals(word1,testVocabList.findWordByIndex("2"));
        assertEquals(word2,testVocabList.findWordByIndex("1"));
        assertNull(testVocabList.findWordByIndex("one"));
    }

    @Test
    void testFindWordByIndex() {
        assertNull(testVocabList.findWordByIndex(1));
        assertNull(testVocabList.findWordByIndex(0));
        testVocabList.addWord(word2);
        testVocabList.addWord(word1);
        assertEquals(word1,testVocabList.findWordByIndex(1));
        assertEquals(word2,testVocabList.findWordByIndex(0));
        assertNull(testVocabList.findWordByIndex(2));
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
