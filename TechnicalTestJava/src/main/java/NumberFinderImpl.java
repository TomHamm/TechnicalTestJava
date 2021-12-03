import demo.CustomNumberEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberFinderImpl implements NumberFinder {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberFinderImpl.class);

    public boolean contains(int valueToFind, List<CustomNumberEntity> list) {

        List<FastestComparatorThread> fastestComparatorThreadList = new ArrayList<>();

        for (CustomNumberEntity customNumberEntity : list) {
            FastestComparatorThread fastestComparatorThread = new FastestComparatorThread(valueToFind, customNumberEntity);
            fastestComparatorThread.start();
            fastestComparatorThreadList.add(fastestComparatorThread);
        }

        long CurrentTime = System.currentTimeMillis();
        long endTime = CurrentTime + 10000;
        while(System.currentTimeMillis() < endTime){
            LOGGER.info("Allowing time for all threads to execute");
        }

        for (FastestComparatorThread fastestComparatorThread : fastestComparatorThreadList) {
            LOGGER.info("Result of Compare: ", fastestComparatorThread.getResult());
            if(fastestComparatorThread.getResult() == 0) {
                return true;
            }
        }
        return false;

//        If Implementing code below, FastestComparatorThread needs to be updated.
//        FastestComparatorThread fastestComparatorThread = new FastestComparatorThread(valueToFind, list);
//        fastestComparatorThread.start();
//
//        while(fastestComparatorThread.isAlive()) {
//            fastestComparatorThread.interrupt();
//            if (fastestComparatorThread.getResult() == 0) {
//                return true;
//            }
//        }
//        return false;
    }

    public List<CustomNumberEntity> readFromFile(String filePath) {
        List<CustomNumberEntity> customNumberEntityList = new ArrayList<>();

        // Initializing empty String to store JSON content
        String myJson = "";

        try {
            // Read from JSON file stored in Resource Folder as a String
            myJson = new Scanner(new File(getClass().getResource(filePath).getPath())).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Store Content in a JSON Array
        JSONArray jsonArray = new JSONArray(myJson);

        // Create an @CustomNumberEntity for Each Object in the JSON File and Store in a List
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            CustomNumberEntity customNumberEntity = new CustomNumberEntity(String.valueOf(jsonObject.get("number")));
            customNumberEntityList.add(customNumberEntity);
        }
        return customNumberEntityList;
    }

}