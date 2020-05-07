import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(JUnitParamsRunner.class)
public class RunTests {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName;
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy_hhmmss");
        String currentDateTime = format.format(new Date()).toString();

        PrintStream report = new PrintStream(new FileOutputStream(currentDateTime + "report.log"));
        System.setOut(report);
        System.setErr(report);

        if (args[0].equals("default")) {
            fileName = "src/main/resources/response.json";
        } else fileName = args[0];

        JsonEvents jsonEvents = new JsonEvents(new FileReader(fileName));
        Object[] events = jsonEvents.getEvents();

        if (args[1].equals("IsOverallStatusNull")) {
            runIsOverallStatusNull(events);
        } else if (args[1].equals("IsErpCorrect")) {
            runIsErpCorrect(events);
        } else if (args[1].equals("IsDefaultProviderExists")) {
            runIsDefaultProviderExists(events);
        } else if (args[1].equals("all")) {
            runIsOverallStatusNull(events);
            runIsErpCorrect(events);
            runIsDefaultProviderExists(events);
        }

    }

    public static void runIsErpCorrect(Object[] events) {
            for (Object event : events) {
                try {
                System.out.println("IsErpCorrect: " + JSONtest.IsErpCorrect((JSONObject) event) + "\n __________________________________________");
                }catch (AssertionError e){
                    e.printStackTrace();
                    continue;
                }
            }
    }

    public static void runIsDefaultProviderExists(Object[] events) throws FileNotFoundException {
            for (Object event : events) {
                try {
                System.out.println("IsDefaultProviderExists: " + JSONtest.IsDefaultProviderExists((JSONObject) event) + "\n __________________________________________");
                }
                catch (AssertionError e){
                    e.printStackTrace();
                }
            }
    }

    public static void runIsOverallStatusNull(Object[] events) throws FileNotFoundException {
            for (Object event : events) {
                try {
                System.out.println("IsOverallStatusNull: " + JSONtest.IsOverallStatusNull((JSONObject) event) + "\n __________________________________________");
                }catch (AssertionError e){
                    e.printStackTrace();
                }
            }
    }
}
