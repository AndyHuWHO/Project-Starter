package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class VocabTest {
    private Vocab testVocabEntry;

    @BeforeEach
    void runBefore() {
        testVocabEntry = new Vocab("testVocab");
    }

    @Test
    void testConstructor() {
        assertEquals("testVocab", testVocabEntry.getName());
        assertEquals("Definition: ", testVocabEntry.getDefinition());
        assertEquals("Original Learning Context for testVocab:", testVocabEntry.getLearningContext());
    }

    @Test
    void testEditName() {
        testVocabEntry.editName("testNewName");
        assertEquals("testNewName",testVocabEntry.getName());
        assertEquals("Definition: ", testVocabEntry.getDefinition());
        assertEquals("Original Learning Context for testNewName:", testVocabEntry.getLearningContext());
    }

    @Test
    void testEditDefinition() {
        testVocabEntry.editDefinition("definition for testVocab");
        assertEquals("testVocab",testVocabEntry.getName());
        assertEquals("Definition: definition for testVocab", testVocabEntry.getDefinition());
        assertEquals("Original Learning Context for testVocab:", testVocabEntry.getLearningContext());
    }

    @Test
    void testEditLearningContext() {
        testVocabEntry.editLearningContext("learning context for testVocab");
        assertEquals("testVocab",testVocabEntry.getName());
        assertEquals("Definition: ", testVocabEntry.getDefinition());
        assertEquals("Original Learning Context for testVocab:"
                + "\nlearning context for testVocab", testVocabEntry.getLearningContext());
    }


}