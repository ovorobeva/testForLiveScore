This project is a test task for LiveScore company. It performs three test-cases:
1. Extract all events, and for each event verify if there is a default provider ("d") and print its value ("P") and id ("ID") in the following format "P-ID" and the name of the teams ("Nm") in the format "Team1: {1st team} | Team2: {2nd team}.
2. Extract all events and check their overall status of the match ("Epr") if it is 0 then none of the "Tr1", "Tr2", "Trh1" and "Trh2" should be present
3. Extract all events and for each event verify that status of the match ("Epr") is an int from this array [0,1,2,3,4,5,6,7]

How to use:
1. Download testForLiveScore.jar file.
2. From the directory where testForLiveScore.jar is run a command:
java -jar testForLiveScore.jar <json_directory> <test_id>
Where
    <json_directory> - is a path to your json. Use -default to run test with default json.
    <test_id> - is an id of test-case that you want to run. There are four possible values:
        IsDefaultProviderExists - to run the first test-case from the task
        IsOverallStatusNull - to run the second test-case from the task
        IsErpCorrect - to run the third test-case from the task
        -all - to run all the tests.
    For example a command "java -jar testForLiveScore.jar D:\Docs\response.json IsDefaultProviderExists" runs the first test with the usage of json response.json from a directory D:\Docs\
3. After running report logfile appears in the same directory where your testForLiveScore.jar lies.
4. Name of the report file is created in format ddMMyyyy_hh:mm:ssreport.log where ddMMyyyy_hh:mm:ss is a datetime of running the test.
NOTICE: running the application without options equals to running with options -default -all.
        So, a command "java -jar testForLiveScore.jar" equals to "java -jar testForLiveScore.jar -default -all"

WARN: there is no way to use test_id option without json_directory option.