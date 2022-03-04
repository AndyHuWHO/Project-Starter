package persistence;


import org.json.JSONObject;

public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}  //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//code in this class is used from the link above
