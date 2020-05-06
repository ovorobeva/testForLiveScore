import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

@RunWith(JUnitParamsRunner.class)
public class JSONtest {
    private JsonEvents jsonEvents = new JsonEvents(new FileReader("src/test/testResources/response.json"));
    private String team1name;
    private String team2name;

    public JSONtest() throws FileNotFoundException {
    }

    private Object[] getEvent() {
        return jsonEvents.getEvents().toArray();
    }

    @Test
    @Parameters(method = "getEvent")
    public void IsDefaultProviderExists(JSONObject event) throws FileNotFoundException {
        final JSONArray providers;
        providers = (JSONArray) event.get("IDs");
        int providersCount = 0;

        Iterator<JSONObject> iterator = providers.iterator();
        while (iterator.hasNext()) {
            JSONObject provider = iterator.next();
//TODO: Move getting teamnames and providers into additional class Event
            if (provider.containsKey("d")) {
                providersCount++;

                JSONArray team1 = (JSONArray) event.get("T1");
                Iterator <JSONObject> team1iterator = team1.iterator();
                while (team1iterator.hasNext()){
                    team1name = (String) team1iterator.next().get("Nm");
                }

                JSONArray team2 = (JSONArray) event.get("T2");
                Iterator <JSONObject> team2iterator = team2.iterator();
                while (team2iterator.hasNext()){
                    team2name = (String) team2iterator.next().get("Nm");
                }

                System.out.println(provider.get("P") + "-" + provider.get("ID") + "\n" +
                            "Team1: " + team1name + " | Team2: " + team2name);
            }

            if (providersCount == 0){
                Assert.fail("There is not a default provider in this event");
            }
        }
    }
}
