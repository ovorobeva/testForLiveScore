import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

@RunWith(JUnitParamsRunner.class)
public class JSONtest {
    private JsonEvents jsonEvents = new JsonEvents(new FileReader("src/test/testResources/response.json"));

    private ArrayList<JSONObject> getEvent(){
        ArrayList<JSONObject> eventsList = new ArrayList<JSONObject>();

        Iterator <JSONObject> iterator = jsonEvents.getEvents().iterator();
        while (iterator.hasNext()) {
            eventsList.add(iterator.next());
        }
        return eventsList;
    }

    public JSONtest() throws FileNotFoundException {
    }

    @Test
    @Parameters(method = "getEvent")
    public void IsDefaultProviderExists(JSONObject event) throws FileNotFoundException {
        final JSONArray providers;
        providers = (JSONArray) event.get("IDs");

        Iterator <JSONObject> iterator = providers.iterator();
        while (iterator.hasNext()){
            JSONObject provider = iterator.next();
            if (provider.containsKey("d")){
                System.out.println(provider.get("P") + "-" + provider.get("ID"));
            }
        }
    }
}
