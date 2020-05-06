import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonEvents {
    private JSONObject jsonObject;
    private JSONArray events = new JSONArray();


    JsonEvents(FileReader jsonFile){
        JSONParser parser = new JSONParser();
        try {
            jsonObject = (JSONObject) parser.parse(jsonFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public JSONArray getEvents(){
        JSONArray stages;
        JSONArray eventsContainer;

        stages = (JSONArray) jsonObject.get("Stages");
        Iterator <JSONObject> iterator = stages.iterator();

        while (iterator.hasNext()){
            eventsContainer = (JSONArray) iterator.next().get("Events");
            if (eventsContainer != null) {
                Iterator <JSONObject> eventIterator = eventsContainer.iterator();
                while (eventIterator.hasNext()) {
                    events.add(eventIterator.next());
                }
            }
        }
        return events;
    }


    public JSONArray getProviders(JSONObject event){
        JSONArray providers;
        providers = (JSONArray) event.get("IDs");
        return providers;
    }


}
