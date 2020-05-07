import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
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
    public static String IsDefaultProviderExists(JSONObject event) throws FileNotFoundException {
        String output = "";
        JsonEvent testedEvent = new JsonEvent(event);
        JSONArray providers = testedEvent.getProviders();


        int providersCount = 0;
        Iterator<JSONObject> iterator = providers.iterator();

        while (iterator.hasNext()) {
            JSONObject provider = iterator.next();
            if (provider.containsKey("d")) {
                providersCount++;

                output = provider.get("P") + "-" + provider.get("ID") + "\n" +
                        "Team1: " + testedEvent.getTeamName(1) + " | Team2: " + testedEvent.getTeamName(2);
            }

            if (providersCount == 0){
                output = "There is no default provider in this event";
                Assert.fail(output);

            }
        }
        return output;
    }

    @Test
    @Parameters(method = "getEvent")
    public static String IsOverallStatusNull(JSONObject event) throws FileNotFoundException {
        String output;

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
        if (badNotNull > 0){
            output = "There are bad elements in this event: \n" + badElements;
            Assert.fail(output);
        } else output = "Test is passed";
        return output;
    }

    @Test
    @Parameters(method = "getEvent")
    public static String IsErpCorrect(JSONObject event) {
        String output;
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
        if (isCorrect){
            output = "Test is passed";
        } else {
            output = "Erp is incorrent, value is " + event.get("Epr") + " Correct values are: 0,1,2,3,4,5,6,7";
            Assert.fail(output);
        }
        return output;
    }
}
