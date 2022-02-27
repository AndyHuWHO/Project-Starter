package persistence;

import model.Word;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkWord(String name, String definition, String learningContext, Word word) {
        assertEquals(name, word.getName());
        assertEquals(definition, word.getDefinition());
        assertEquals(learningContext, word.getLearningContext());
    }
}
