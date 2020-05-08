import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(JUnitParamsRunner.class)
public class RunTests {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream fileName;
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy_hhmmss");
        String currentDateTime = format.format(new Date());

        PrintStream report = new PrintStream(new FileOutputStream(currentDateTime + "report.log"));
        PrintStream oldStream = new PrintStream(System.out);
        System.setOut(report);
        System.setErr(report);
        String fileType;
        String runType;

        if (args.length == 0){
            fileType = "-default";
            runType = "-all";
        } else {
            fileType = args[0];
            runType = args[1];
        }

        if (fileType.equals("-default")) {
            fileName = RunTests.class.getResourceAsStream("response.json");
        } else{
            fileName = new FileInputStream(fileType);
        }

        JsonEvents jsonEvents = new JsonEvents(new BufferedReader(new InputStreamReader(fileName)));

        Object[] events = jsonEvents.getEvents();

        if (runType.equals("IsOverallStatusNull")) {
            runIsOverallStatusNull(events);
        } else if (runType.equals("IsErpCorrect")) {
            runIsErpCorrect(events);
        } else if (runType.equals("IsDefaultProviderExists")) {
            runIsDefaultProviderExists(events);
        } else if (runType.equals("-all")) {
            runIsOverallStatusNull(events);
            runIsErpCorrect(events);
            runIsDefaultProviderExists(events);
        }
        System.setOut(oldStream);
        System.out.println("You can see your report in the log files at the jar directory");

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
