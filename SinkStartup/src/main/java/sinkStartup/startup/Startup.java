package sinkStartup.startup;

import java.util.ArrayList;
import java.util.Objects;

public class Startup {
    private String name;

    public String getName() {
        return name;
    }

    public ArrayList<String> getLocationArr() {
        return locationArr;
    }

    private ArrayList<String> locationArr; // change to 2D array

    public void setName(String name) {
        this.name = name;
    }

    public void setLocationArr(ArrayList<String> locationArr) {
        this.locationArr = locationArr;
    }

    public String checkYourGuess(String user) {
        String result = "miss";

        for (var j : locationArr) {
            if (Objects.equals(j, user)) {
                result = "hit";
                locationArr.remove(j);
                break;
            }
        }

        if (locationArr.isEmpty()) {
            result = "kill";
            System.out.println("Ouch! You sunk " + name + " :(");
        }

//        System.out.println(result);
        return result;
    }
}
