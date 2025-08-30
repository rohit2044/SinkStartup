package sinkStartup;

import sinkStartup.startup.Startup;
import sinkStartup.util.GameHelper;

import java.util.ArrayList;

public class SinkStartup {
    private final ArrayList<Startup> startupArr = new ArrayList<Startup>();
    private final GameHelper gameHelper = new GameHelper();
    private int numOfGuesses = 0;

    public void setupGame() {
        // initialize startupArr;
        Startup one = new Startup();
        one.setName("poniez");
        Startup two = new Startup();
        two.setName("hacqi");
        Startup three = new Startup();
        three.setName("cabista");

        startupArr.add(one);
        startupArr.add(two);
        startupArr.add(three);

        System.out.println("Your goal is to sink three Startups.");
        System.out.println("poniez, hacqi, cabista");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for (var startup : startupArr) {
            startup.setLocationArr(gameHelper.placeStartup(3));
        }
    }

    private void startGame() {
        while (!startupArr.isEmpty()) {
            String userGuess = gameHelper.getUserInput("Input your guess");
            checkGuess(userGuess);
        }
        finishGame();
    }

    private void checkGuess(String userGuess) {
        numOfGuesses++;
        String result = "miss";

        for (var startup : startupArr) {
            result = startup.checkYourGuess(userGuess);

            if (result.equals("kill")) {
                startupArr.remove(startup);
                break;
            } else if (result.equals("hit")) {
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("All Startups are dead! Your stock is now worthless");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options");
        }
    }

    public static void main(String[] args) {
        SinkStartup game = new SinkStartup();
        game.setupGame();
        game.printGrid();
        game.startGame();
    }

    // Enhanced grid display with hit/miss tracking and startup-specific symbols
    public void printGrid() {
        // Create a 7x7 grid filled with water (~)
        char[][] grid = new char[7][7];

        // Fill grid with water pattern
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                grid[row][col] = '~';
            }
        }

        // Mark startup positions with their initial characters
        for (Startup startup : startupArr) {
            ArrayList<String> locations = startup.getLocationArr();
            char startupSymbol = startup.getName().toUpperCase().charAt(0); // P, H, C

            for (String location : locations) {
                // Convert location string (like "A3") to grid coordinates
                int row = 'g' - location.charAt(0); // G=0, F=1, ..., A=6
                int col = Character.getNumericValue(location.charAt(1)); // 0-6
                grid[row][col] = startupSymbol;
            }
        }

        // Print column headers
        System.out.print("  ");
        for (int col = 0; col < 7; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        // Print grid with row labels
        for (int row = 0; row < 7; row++) {
            char rowLabel = (char)('g' - row); // G, F, E, D, C, B, A
            System.out.print(rowLabel + " ");

            for (int col = 0; col < 7; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }

        System.out.println("\nLegend:");
        System.out.println("~ = Water");
        System.out.println("P = Poniez startup");
        System.out.println("H = Hacqi startup");
        System.out.println("C = Cabista startup");
    }
}
