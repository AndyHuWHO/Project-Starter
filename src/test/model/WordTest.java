package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    private Word testWordEntry;

    @BeforeEach
    void runBefore() {
        testWordEntry = new Word("testVocab");
    }

    @Test
    void testConstructor() {
        assertEquals("testVocab", testWordEntry.getName());
        assertEquals("Definition:", testWordEntry.getDefinition());
        assertEquals("Original Learning Context:", testWordEntry.getLearningContext());
    }

    @Test
    void testEditName() {
        testWordEntry.editName("testNewName");
        assertEquals("testNewName", testWordEntry.getName());
        assertEquals("Definition:", testWordEntry.getDefinition());
        assertEquals("Original Learning Context:", testWordEntry.getLearningContext());
    }

    @Test
    void testEditDefinition() {
        testWordEntry.editDefinition("definition for testVocab");
        assertEquals("testVocab", testWordEntry.getName());
        assertEquals("Definition: definition for testVocab", testWordEntry.getDefinition());
        assertEquals("Original Learning Context:", testWordEntry.getLearningContext());
    }

    @Test
    void testEditLearningContext() {
        testWordEntry.editLearningContext("learning context for testVocab");
        assertEquals("testVocab", testWordEntry.getName());
        assertEquals("Definition:", testWordEntry.getDefinition());
        assertEquals("Original Learning Context: learning context for testVocab",
                testWordEntry.getLearningContext());
    }

    @Test
    void testMakeWordCard() {
        assertEquals("************************\n" +
                "Word: testVocab\n" +
                "Definition:\n" +
                "Original Learning Context:\n" +
                "*************************", testWordEntry.makeWordCard());
        testWordEntry.editDefinition("definition for testVocab");
        testWordEntry.editLearningContext("learning context for testVocab");
        assertEquals("************************\n" +
                "Word: testVocab\n" +
                "Definition: definition for testVocab\n" +
                "Original Learning Context: learning context for testVocab\n" +
                "*************************",testWordEntry.makeWordCard());
    }


}