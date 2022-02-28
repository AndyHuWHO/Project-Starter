package persistence;

import model.VocabList;
import model.Word;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            VocabList vocabList = new VocabList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyVocabList() {
        try {
            VocabList vocabList = new VocabList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyVocabList.json");
            writer.open();
            writer.write(vocabList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyVocabList.json");
            vocabList = reader.read();
            assertEquals(0, vocabList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            VocabList vocabList = new VocabList();
            Word newWord1 = new Word("needle");
            newWord1.editDefinition("definition for needle");
            newWord1.editLearningContext("learning context for needle");
            vocabList.addWord(newWord1);
            Word newWord2 = new Word("saw");
            newWord2.editDefinition("definition for saw");
            newWord2.editLearningContext("learning context for saw");
            vocabList.addWord(newWord2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralVocabList.json");
            writer.open();
            writer.write(vocabList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralVocabList.json");
            vocabList = reader.read();

            List<Word> words = vocabList.getWords();
            assertEquals(2, words.size());
            checkWord("needle", "Definition: definition for needle",
                    "Original Learning Context: learning context for needle",words.get(0));
            checkWord("saw", "Definition: definition for saw",
                    "Original Learning Context: learning context for saw",words.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
