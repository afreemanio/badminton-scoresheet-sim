/* Copyright (C) 2021 Andrew Freeman
 This file is part of badminton-scoresheet-sim.

 badminton-scoresheet-sim is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 badminton-scoresheet-sim is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

You should have received a copy of the GNU General Public License

Date of Last Update: May 1, 2020
Badminton

Requires:
    Badminton.java
    ScoreSheet.java

This is a program simulating a the score sheet for a badminton game, automatically counting rally #,
each player's wins, each player's most wins in a row, and each player's best stroke (the one they won with the most).
There are two testing methods for this program, one utilizing the statistics for the player wins and one not.

The method Badminton.testCase2() is used to test the program.
The number of rallies in each game is set by the upTo variable within Badminton

*/



package badminton;

import java.util.List;
import java.util.Scanner;

public class Badminton {

    int upTo = 4; //how many points required to win the game
//        int upTo = 4; //how many points required to win the game


    //returns -1 if string not allowed, 0 if string empty,
    //1 if smash, 2 if slice, 3 if net, 4 if drop, 5 if drive
    static int allowedString(String input) {

        int returnVal = -1;
        switch (input) {
            case "\n":
            case "": {
                returnVal = 0;
                break;
            }
            case "Smash":
            case "smash": {
                returnVal = 1;
                break;
            }
            case "Slice":
            case "slice": {
                returnVal = 2;
                break;
            }
            case "net":
            case "Net": {
                returnVal = 3;
                break;
            }
            case "drop":
            case "Drop": {
                returnVal = 4;
                break;
            }
            case "drive":
            case "Drive": {
                returnVal = 5;
                break;
            }
            default: {
                returnVal = -1;
            }

        }
        return returnVal;
    }

    //test case 1, does not use the statistics and scores the sheet differently, but still simulates the badminton game
    void testCase1(){
        ScoreSheet sheetTest = new ScoreSheet();
        System.out.println(sheetTest);
        Scanner myScanner = new Scanner(System.in);
        int player1;
        int player2;
        String myString;
        int myAllowedString = -1;
        for (int i = 0; i < 26; i++){
            System.out.println("Rally #: " + (1 + i));
            System.out.println("Please enter a number for player 1: ");
            player1 = myScanner.nextInt();
            System.out.println("Please enter a number for player 2: ");
            player2 = myScanner.nextInt();
            System.out.println("Please enter the winning stroke: ");
            myString = myScanner.nextLine();
            myString = myScanner.nextLine();
            myAllowedString = allowedString(myString);
//            System.out.println("ALLOWED STRING IS:" + myAllowedString);
            while (myAllowedString < 0){
                System.out.println("Error: Incorrect winning stroke...");
                System.out.println("Valid inputs are: smash, slice, net, drop, drive, and \"\"" +
                        " (enter key, no value");
                System.out.println("Please enter the winning stroke: ");
                myString = myScanner.nextLine();
                myAllowedString = allowedString(myString);
//                System.out.println("ALLOWED STRING IS:" + myAllowedString);
            }

            sheetTest.addEntry(player1, player2, myString);
            System.out.println(sheetTest);
        }


    }

    //Gets the most points scored in a row given a list of ScoreEntry (specifically, from a ScoreSheet object).
    //Does this by looping through the whole sheet for the given player (1 or 2), and adding to a temporary max value
    //and then getting the max of all the max values, and returning that value.
    int pointsScoredInARow(List<ScoreSheet.ScoreEntry> scoreList, int player){
        int maximumScoredInARow = 0;
        int currentScoredInARow = 0;
        int currentVal = 0;
        if (player == 1){
            for (int i = 1; i < scoreList.size(); i++){
                //get score
                currentVal = scoreList.get(i).player1;
//                System.out.println("Current value is " + currentVal);
                if (currentVal > 0 ){
                    currentScoredInARow++;
//                    System.out.println("Scored " + currentScoredInARow + "times in a row max");
                } else {
                    currentScoredInARow = 0;
                }
                if (currentScoredInARow > maximumScoredInARow){
                    maximumScoredInARow = currentScoredInARow;
//                    System.out.println("Scored " + maximumScoredInARow + "times in a row max");
                }
            }
        } else if (player == 2){
            for (int i = 1; i < scoreList.size(); i++){
                //get score
                currentVal = scoreList.get(i).player2;
                if (currentVal > 0 ){
                    currentScoredInARow++;
                } else {
                    currentScoredInARow = 0;
                }
                if (currentScoredInARow > maximumScoredInARow){
                    maximumScoredInARow = currentScoredInARow;
                }
            }
        } else {
            return -1; //if incorrect input, return -1
        }
        return maximumScoredInARow;
    }


    //returns the best stroke for the given player (1 or 2), returns "" if an error occurs,
    //returns "absolutely nothing!" if the given player does not have a best (scored 0 points).
    String bestStroke(List<ScoreSheet.ScoreEntry> scoreList, int player){
        int smash = 0;
        int slice = 0;
        int net = 0;
        int drop = 0;
        int drive = 0;
        String best = "Nothing";
        String currentString = "";
        int currentVal = 0; //if the player won the round
        if (player == 1 || player == 2){
            for (int i = 1; i < scoreList.size(); i++){
                //get current value
                if (player == 1){
                    currentVal = scoreList.get(i).player1;
                } else {
                    currentVal = scoreList.get(i).player2;
                }
//                System.out.println("Current value is " + currentVal);
                if (currentVal > 0 ){ //if the player scored
                    currentString = scoreList.get(i).winningStroke; //current stroke = that winning stroke
                    switch (currentString){
                        case "smash":
                            smash++;
                            break;
                        case "slice":
                            slice++;
                            break;
                        case "net":
                            net++;
                            break;
                        case "drop":
                            drop++;
                            break;
                        case "drive":
                            drive++;
                            break;
                        default:
                            break;
                    }
                }
            }
            //find the largest
            int array[] = {smash, slice, net, drop, drive};
            int currentMax = array[0];
            for (int i = 0; i < 5; i++){
                if (array[i] > currentMax){
                    currentMax = array[i];
                }
            }
            for (int i = 0; i < 5; i++){
                if (currentMax == 0){
                    best = "absolutely nothing!";
                } else if (array[i] == currentMax){
                    switch (i){
                        case 0:
                            best = "smash";
                            break;
                        case 1:
                            best = "slice";
                            break;
                        case 2:
                            best = "net";
                            break;
                        case 3:
                            best = "drop";
                            break;
                        case 4:
                            best = "drive";
                            break;
                        default:
                            System.out.println("nothing!");
                            break;
                    }

                }
            }


        } else {
            return "Error: no best!"; //if incorrect input, return -1
        }

        return best;
    }

    //tests the ScoreSheet and Badminton methods, simulating a game up to the value of upTo.
    void testCase2(){
        ScoreSheet sheetTest = new ScoreSheet();
        Scanner myScanner = new Scanner(System.in);
        int player1;
        int player2;
        int winner = 0;
        String myString;
        int myAllowedString = -1;
        sheetTest.addEntry(0, 0, "");
        for (int i = 0; i < 1000; i++){
            if (sheetTest.player1wins >= upTo || sheetTest.player2wins >= upTo){
                break;
            }
            System.out.println("Rally #: " + (1 + i));
            System.out.println("Which player won this rally?: ");
            winner = myScanner.nextInt();
            System.out.println("You entered (" + winner + ")");
            while (winner != 1 && winner != 2){
                System.out.println("Error: Incorrect winner...");
                System.out.println("Valid inputs are: 1 and 2.");
                System.out.println("Which player won this rally?: ");
                winner = myScanner.nextInt();
                System.out.println("You entered (" + winner + ")");
            }
            System.out.println("Please enter the winning stroke: ");
            myString = myScanner.nextLine();
            myString = myScanner.nextLine();
            myAllowedString = allowedString(myString);
//            System.out.println("ALLOWED STRING IS:" + myAllowedString);
            System.out.println("You entered (" + myString + ")");
            while (myAllowedString < 0){
                System.out.println("Error: Incorrect winning stroke...");
                System.out.println("Valid inputs are: smash, slice, net, drop, drive, and \"\"" +
                        " (enter key, no value");
                System.out.println("Please enter the winning stroke: ");
                myString = myScanner.nextLine();
                myAllowedString = allowedString(myString);
//                System.out.println("ALLOWED STRING IS:" + myAllowedString);
                System.out.println("You entered (" + myString + ")");
            }
            if (winner == 1){
                sheetTest.player1wins++;
                sheetTest.addEntry(sheetTest.player1wins, 0, myString);
            } else if (winner == 2){
                sheetTest.player2wins++;
                sheetTest.addEntry(0, sheetTest.player2wins, myString);
            } else {
                System.out.println("ERROR: neither player 1 nor player 2 won?");
            }
            System.out.println(sheetTest);
        }
        System.out.println("Game over!");
        System.out.println("Results: ");
        System.out.println("Player 1 won " + sheetTest.player1wins + " times.");
        System.out.println("Player 2 won " + sheetTest.player2wins + " times.");
        if (sheetTest.player1wins >= sheetTest.player2wins ){
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 2 wins!");
        }
        System.out.println("Player 1 scored " + pointsScoredInARow(sheetTest.scoreList, 1) + " points in a row!");
        System.out.println("Player 2 scored " + pointsScoredInARow(sheetTest.scoreList, 2) + " points in a row!");
        System.out.println("Player 1's best stroke was " + bestStroke(sheetTest.scoreList, 1));
        System.out.println("Player 2's best stroke was " + bestStroke(sheetTest.scoreList, 2));
    }


    public static void main(String[] args){
        System.out.println("Start of badminton :)");
        Badminton badminton = new Badminton();
//            badminton.testCase1();
        badminton.testCase2();
    }
}
