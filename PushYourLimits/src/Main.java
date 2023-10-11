import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        // Get Number of Players
        System.out.println("Enter number of players (Max 6 Players): ");
        int numPlayers = 0;

        while(numPlayers < 1 || numPlayers > 6) {
            String numPlayersString = myObj.nextLine();
            numPlayers = Integer.parseInt(numPlayersString);

            if (numPlayers < 1 || numPlayers > 6) {
                System.out.println("Invalid Number of Players! Please enter another number between 1 and 6: ");
            }
        }

        // Get Player's Names
        String[] playerNames = new String[numPlayers];
        int countNames = 0;
        while(countNames < numPlayers){
            System.out.println("Enter Player " + (countNames + 1) + "'s Name: ");
            playerNames[countNames] = myObj.nextLine();
            countNames++;
        }

        // Go through Player's turns
        int currentScore = 0;
        int bestScore = 0;
        ArrayList<String> winners = new ArrayList<>();

        for(int i = 0; i < numPlayers; i++){
            currentScore = 0;
            System.out.println(playerNames[i] + "! It is now your turn!\nWould you like to roll the die (r) or end your turn (e)?");
            String userResponse = myObj.nextLine();
            boolean turnOver = false;

            while(!turnOver){
                if(userResponse.equalsIgnoreCase("e")){
                    System.out.println(playerNames[i] + " ended with a score of " + currentScore + "!");
                    turnOver = true;
                    if(currentScore > bestScore){
                        System.out.println("That is a new high score! " + playerNames[i] + " is our new leader!");
                        bestScore = currentScore;
                        winners.clear();
                        winners.add(playerNames[i]);
                    } else if (currentScore == bestScore) {
                        System.out.println("There is a tie for the best score of " + bestScore);
                        winners.add(playerNames[i]);
                    }else{
                        System.out.println("That is not better than the high score...better luck next time!");
                    }
                }else if(userResponse.equalsIgnoreCase("r")) {
                    int dieRollResult = rollDie();
                    if (dieRollResult == 1){
                        turnOver = true;
                        currentScore = 0;
                        System.out.println("Oh no! You rolled a 1 and your turn is over! ");
                        if(currentScore == bestScore && i == 0){
                            System.out.println("That is the best score so far. You are the current leader with a score of " + currentScore);
                            winners.add(playerNames[i]);
                        } else if (currentScore == bestScore) {
                            System.out.println("That puts you in a tie for the best score.");
                            winners.add(playerNames[i]);
                        }
                    }else {
                        System.out.println("You rolled a " + dieRollResult + "!");
                        currentScore += dieRollResult;
                        System.out.println("Your current score is " + currentScore + "!\nWould you like to roll again (r) or end your turn (e)?");
                        userResponse = myObj.nextLine();
                    }
                }else{
                    System.out.println("That is an invalid response. If you would like to roll please type the 'r' key. If you would like to end your turn, please type the 'e' key");
                    userResponse = myObj.nextLine();
                }
            }
        }

        int lenWinners = winners.size();

        if(lenWinners == 1){
            System.out.println("What a game! The winner was " + winners.get(0) + "!\nThanks for Playing everyone!");
        }else{
            System.out.print("What a game! We had a tie! The winners were ");
            for (int i = 0; i < lenWinners - 1; i++){
                System.out.print(winners.get(i) + ", ");
            }
            System.out.print(winners.get(lenWinners - 1) + "!\nThanks for Playing everyone!");
        }
    }
// Method returns a number 1-6, simulating a roll of a die
    public static int rollDie(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}