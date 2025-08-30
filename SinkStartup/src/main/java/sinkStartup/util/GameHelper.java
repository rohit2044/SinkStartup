package sinkStartup.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {
    private static final String ALPHABET = "abcdefg";
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;
    private static final int MAX_ATTEMPTS = 200;
    static final int HORIZONTAL_INCREMENT = 1;
    static final int VERTICAL_INCREMENT = GRID_LENGTH; // A better way to represent these two
    // things is an enum (see Appendix B)
    private final int[] grid = new int[GRID_SIZE];
    private final Random random = new Random();
    private int startupCount = 0;

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    } //end getUserInput

    public ArrayList<String> placeStartup(int startupSize) {
        // holds index to grid (0 - 48)
        int[] startupCoords = new int[startupSize];
        int attempts = 0;
        boolean success = false; // current candidate co-ordinates
        // current attempts counter
        // flag = found a good location?
        startupCount++;
        int increment = getIncrement(); // nth Startup to place
        // alternate vert & horiz alignment
        while (!success & attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(GRID_SIZE); // main search loop
            // get random starting point
            for (int i = 0; i < startupCoords.length; i++) {
                startupCoords[i] = location;
                location += increment; // create array of proposed coords
                // put current location in array
                // calculate the next location
            }
            // System.out.println("Trying: " + Arrays.toString(startupCoords));
            if (startupFits(startupCoords, increment)) { // startup fits on the grid?
                success = coordsAvailable(startupCoords); // ...and locations aren't taken?
            } // end loop
        } // end while
        savePositionToGrid(startupCoords); // coords passed checks, save
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        // System.out.println("Placed at: "+ alphaCells);
        return alphaCells;
    } //end placeStartup

    private boolean startupFits(int[] startupCoords, int increment) {
        int finalLocation = startupCoords[startupCoords.length - 1];
        if (increment == HORIZONTAL_INCREMENT) {
            // check end is on same row as start
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
        } else {
            return finalLocation < GRID_SIZE; // check end isn't off the bottom
        }
    } //end startupFits

    private boolean coordsAvailable(int[] startupCoords) {
        for (int coord : startupCoords) { // check all potential positions
            if (grid[coord] != 0) { // this position already taken
                // System.out.println("position: " + coord + " already taken.");
                return false; // NO success
            }
        }
        return true;
    } //end coordsAvailable

    private void savePositionToGrid(int[] startupCoords) {
        for (int index : startupCoords) {
            grid[index] = 1; // there were no clashes, yay!
            // mark grid position as 'used'
        }
    } //end savePositionToGrid

    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        for (int index : startupCoords) { // for each grid coordinate
            String alphaCoords = getAlphaCoordsFromIndex(index); // turn it into an "a0" style
            alphaCells.add(alphaCoords); // add to a list
        }
        return alphaCells; // return the "a0"-style coords
    } // end convertCoordsToAlphaFormat

    private String getAlphaCoordsFromIndex(int index) {
        int row = calcRowFromIndex(index); // get row value
        int column = index % GRID_LENGTH; // get numeric column value
        String letter = ALPHABET.substring(column, column + 1); // convert to letter
        return letter + row;
    } // end getAlphaCoordsFromIndex

    private int calcRowFromIndex(int index) {
        return index / GRID_LENGTH;
    } // end calcRowFromIndex

    private int getIncrement() {
        if (startupCount % 2 == 0) { // if EVEN Startup
            return HORIZONTAL_INCREMENT; // place horizontally
        } else { // else ODD
            return VERTICAL_INCREMENT; // place vertically
        }
    } //end getIncrement
}