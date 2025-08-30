package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SimpleStartup {
    private String name;
    private ArrayList<String> locationArr = new ArrayList<>();

    public void setLocation(ArrayList<String> locations) {
        locationArr = locations;
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
            result = "Ouch! You sunk " + name + " :(";
        }

        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        // hold user Num of Guesses
        int numOfGuess = 0;
        SimpleStartup game = new SimpleStartup();
        GameHelper input = new GameHelper();

        // set the location[], but computing random num from 0 to 4 (since max arr size is 7)
        int startIndex = (int) (Math.random() * 5);

        char randomRow = (char) ('A' + Math.random() * 7);
        char randomCol = (char) ('0' + Math.random() * 7);

        String l1 = "" + randomRow + 1 + randomCol;
        String l2 = "" + randomRow + randomCol;
        String l3 = "" + randomRow + randomCol;

        // then set the next 2 numbers to location[]
        ArrayList<String> locations = new ArrayList<>(Arrays.asList(String.valueOf(startIndex), String.valueOf(startIndex + 1), String.valueOf(startIndex + 2)));
        // call the setLocation() method
        game.setLocation(locations);

        // play game
        while (true) {
            // take user input
            String guess = input.getUserInput("Input your guess");
            // increment numOfGuess
            numOfGuess++;
            // check the user input using checkYourGuess()
            String result = game.checkYourGuess(guess);
            if (result.equals("kill")) {
                // give user their total guesses
                System.out.println("game over, your guesses " + numOfGuess);
                // end game
                break;
            }
        }
    }

//    // two instance vars
//    1. int[] locationCells;
//    2. int numOfGuess;
//
//    // two methods
//    1. check the guess
    // make a loop for each ele of locationCells array
//            1. check if ele matches, if yes,
//                    if last ele -> kill -> give the numOfGuess
//                    else -> hit -> delete from array
//            2. if not, print "miss"
//        // increment the numOfGuess, continue
//    2. set the locationCells arr
}
