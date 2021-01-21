package badminton;

import java.util.ArrayList;
import java.util.List;

public class ScoreSheet {

    //Fields
    public List<ScoreEntry> scoreList;
    public int player1wins;
    public int player2wins;

    //Constructors
    public ScoreSheet() {
        System.out.println("ScoreSheet initialized");
        scoreList = new ArrayList<ScoreEntry>();
        player1wins = 0;
        player2wins = 0;
    }

    //prints the sheets in order using StringBuilder, so the output looks nice :)
    @Override
    public String toString() {
        StringBuilder myString = new StringBuilder("ScoreSheet{\n");
        myString.append("Rally #:    Player 1:     Player 2:     Winning Stroke:     \n");
        for (int i = 0; i < scoreList.size(); i++){
            myString.append(i + "           ");
            myString.append(scoreList.get(i).player1 + "             ");
            myString.append(scoreList.get(i).player2 + "             ");
            myString.append(scoreList.get(i).winningStroke + "\n");
        }
        myString.append("\n}\n");
        return myString.toString();
    }

    //Methods
    void addEntry(int player1, int player2, String winningStroke){
        scoreList.add(new ScoreEntry(player1, player2, winningStroke));
    }

    //subclass that stores each row in the table, contains 2 ints and a string
    class ScoreEntry{
        public ScoreEntry(){
            System.out.println("ScoreEntry initialized");
        };
        public ScoreEntry(int player1, int player2, String winningStroke){
//            System.out.println("ScoreEntry initialized with arguments");
            this.player1 = player1;
            this.player2 = player2;
            this.winningStroke = winningStroke;
        };

        //Fields
        public int player1;
        public int player2;
        public String winningStroke;

        @Override
        public String toString() {
            return "ScoreEntry{" +
                    "player1=" + player1 +
                    ", player2=" + player2 +
                    ", winningStroke='" + winningStroke + '\'' +
                    "} \n";
        }
    }
}