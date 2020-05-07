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

    public JSONtest() throws FileNotFoundException {
    }

    private Object[] getEvent() {
        return jsonEvents.getEvents();
    }

    @Test
    @Parameters(method = "getEvent")
    public void IsDefaultProviderExists(JSONObject event) {
        JsonEvent testedEvent = new JsonEvent(event);
        JSONArray providers = testedEvent.getProviders();

        int providersCount = 0;
        Iterator<JSONObject> iterator = providers.iterator();

        while (iterator.hasNext()) {
            JSONObject provider = iterator.next();
            if (provider.containsKey("d")) {
                providersCount++;

                System.out.println(provider.get("P") + "-" + provider.get("ID") + "\n" +
                            "Team1: " + testedEvent.getTeamName(1) + " | Team2: " + testedEvent.getTeamName(2));
            }

            if (providersCount == 0){
                Assert.fail("There is no default provider in this event");
            }
        }
    }

    @Test
    @Parameters(method = "getEvent")
    public void IsOverallStatusNull(JSONObject event) {
        String[] elements = new String[]{"Tr1", "Tr2", "Trh1", "Trh2"};
        int badNotNull = 0;
        StringBuilder badElements = new StringBuilder();
        if (((Long) event.get("Epr")).intValue() == 0){
            for (String element: elements){
                if (event.get(element) != null) {
                    badNotNull++;
                    badElements.append(element).append(" = ").append(event.get(element)).append("\n");
                }
            }
        }
        Assert.assertFalse("There are bad elements in this event: \n" + badElements, badNotNull > 0);

    }

    @Test
    @Parameters(method = "getEvent")
    public void IsErpCorrect(JSONObject event) {
        int[] correctValues = new int[]{0,1,2,3,4,5,6,7};
        boolean isCorrect = false;
        for (int value: correctValues){
            try {
                if (((Long) event.get("Epr")).intValue() == value){
                    isCorrect = true;
                    break;
                }
            }catch (ClassCastException e){
                Assert.fail("Erp is not an int");
            }
        }
        Assert.assertTrue("Erp is incorrent, value is " + event.get("Epr") + " Correct values are: 0,1,2,3,4,5,6,7", isCorrect);
    }
}
