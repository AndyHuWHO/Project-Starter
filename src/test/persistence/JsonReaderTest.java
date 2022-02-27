package persistence;

import model.VocabList;
import model.Word;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            VocabList vocabList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyVocabList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyVocabList.json");
        try {
            VocabList vocabList = reader.read();
            assertEquals(0, vocabList.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralVocabList.json");
        try {
            VocabList vocabList = reader.read();
            List<Word> words = vocabList.getWords();
            assertEquals(2, words.size());
            checkWord("needle", "definition for needle",
                    "learning context for needle",words.get(0));
            checkWord("saw", "definition for saw",
                    "learning context for saw",words.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
