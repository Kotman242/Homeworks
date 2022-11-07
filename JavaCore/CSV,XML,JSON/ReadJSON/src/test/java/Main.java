import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        String pathToJson = "data.json";
        List<Employee> list = jsonToList(pathToJson);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public static List<Employee> jsonToList(String json) throws ParseException, FileNotFoundException {
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        List<Employee> list = gson.fromJson(new FileReader(json),listType);
        return list;
    }
}

