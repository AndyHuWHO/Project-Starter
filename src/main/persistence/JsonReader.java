package persistence;

import model.VocabList;
import model.Word;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads VocabList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads VocabList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public VocabList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseVocabList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses VocabList from JSON object and returns it
    private VocabList parseVocabList(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");   //what's this line
        VocabList vocabList = new VocabList();
        addWords(vocabList, jsonObject);
        return vocabList;
    }

    // MODIFIES: wr
    // EFFECTS: parses words from JSON object and adds them to VocabList
    private void addWords(VocabList vocabList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("words");
        for (Object json : jsonArray) {
            JSONObject nextWord = (JSONObject) json;
            addWord(vocabList, nextWord);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addWord(VocabList vocabList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String definition = jsonObject.getString("definition");
        String learningContext = jsonObject.getString("learning context");
        Word word = new Word(name);
        word.overrideDefinition(definition);
        word.overrideLearningContext(learningContext);
        vocabList.addWord(word);
    }
}
