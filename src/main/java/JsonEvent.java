import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class JsonEvent {

    private JSONObject event;

    public JsonEvent(JSONObject event){
        this.event = event;
    }

    public JSONArray getProviders(){
        return (JSONArray) event.get("IDs");
    }

    public String getTeamName(int teamNumber) {

        String teamName = null;
        JSONArray team;
        if (teamNumber == 1) {
            team = (JSONArray) event.get("T1");
        } else if (teamNumber == 2) {
            team = (JSONArray) event.get("T2");
        } else {
            team = null;
            System.out.println("There are only two teams");
        }
        Iterator<JSONObject> teamIterator = team.iterator();
        while (teamIterator.hasNext()) {
            teamName = (String) teamIterator.next().get("Nm");
        }
        return teamName;
    }
}