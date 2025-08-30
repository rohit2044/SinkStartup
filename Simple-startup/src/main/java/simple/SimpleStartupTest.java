package simple;


import java.util.ArrayList;
import java.util.List;

class SimpleStartupTest {

    // which tests
//    1. create SimpleStartup object
//    2. set the locationArr []
//    3. send user Guess
//    4. check the checkYourGuess() method
//    5. check the total guesses, check the responses
//    6. finish the game

    public static void main(String[] args) {
        // create object
        SimpleStartup game = new SimpleStartup();
        ArrayList<String> locations = new ArrayList<>(List.of("2", "3", "4"));
        game.setLocation(locations);

        String user = "4";
        String result = game.checkYourGuess(user);

        String testResult = "failed";
        if (result.equals("hit")) {
            testResult = "passed";
        }
        System.out.println(testResult);

    }

}