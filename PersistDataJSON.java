package persistentdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

// Eric McCreath - 2015
// Example of saving and loading info using JSON
// note this program requires the json-simple.jar library
// So from the command line on a unbuntu linux system you can get this library by:
// $ sudo apt-get install libjson-simple-java
// compile:
// $ javac -cp .:/usr/share/java/json-simple.jar *.java
// and run:
// $ java -cp .:/usr/share/java/json-simple.jar PersistDataJSON


public class PersistDataJSON {
    private static final String PERSON = "person";
    private static final String AGE = "age";
    private static final String NAME = "name";
    String name;
    int age;
    public PersistDataJSON(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public PersistDataJSON() {
    }
    static public PersistDataJSON load(String filename) {
        File f = new File(filename);
        PersistDataJSON res = new PersistDataJSON();
        try {
            JSONObject obj = (JSONObject) JSONValue.parse(new FileReader(f));
            res.name = (String) obj.get(NAME);
            res.age = ((Long) obj.get(AGE)).intValue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
    public void save(String filename) {
        File f = new File(filename);
        JSONObject obj = new JSONObject();
        obj.put(NAME, name);
        obj.put(AGE, age);
        FileWriter out;
        try {
            out = new FileWriter(f);
            obj.writeJSONString(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void show() {
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
    }
    public static void main(String[] args) {
        PersistDataJSON data = new PersistDataJSON("Hugh", 10);
        data.save("data.json");
        PersistDataJSON dataload = load("data.json");
        dataload.show();
    }
}
