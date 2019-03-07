package game;

import cards.Card;
import sun.util.resources.cldr.to.CalendarData_to_TO;

import java.util.List;
import java.util.Scanner;

public class Game {

    /**
     * Draws a card for the actual player (dealer or user).
     * @param newDeck the current deck
     * @param playersCards the cards of the players (dealer and user)
     * @return the total value of the players cards
     */
    public int drawCard(List<Card> newDeck, List<Card> playersCards){
        int total = 0;
        Card playerCard1 = newDeck.remove(0);
        playerCard1.printCard();
        total += playerCard1.giveValue();
        playersCards.add(playerCard1);
        return total;
    }

    /**
     * Checks to see if the player or the dealer won.
     * @param total running total of player's cards
     * @param dealer running total of dealer's cards
     * @param to_play
     * @return the money amount that the player has
     */
    public int winCheck(int total, int dealer, int to_play) {
        int gains_losses;
        if (total == 21) {
            System.out.println("You have: " + total);
            System.out.println("You got BlackJack!  You win!");
            gains_losses = 2 * to_play;
        } else if (total > 21) {
            System.out.println("You have: " + total);
            System.out.println("You bust");
            gains_losses = -1 * to_play;
        } else if (total == dealer) {
            System.out.println("You have: " + total);
            System.out.println("Dealer has: " + dealer);
            System.out.println("Push.");
            gains_losses = 0;
        } else if (dealer > 21) {
            System.out.println("Dealer has: " + dealer);
            System.out.println("Dealer busts.  You win!");
            gains_losses = 2 * to_play;
        } else if (total < dealer) {
            System.out.println("You have: " + total);
            System.out.println("Dealer has: " + dealer);
            System.out.println("Dealer wins.");
            gains_losses = -1 * to_play;
        } else {
            System.out.println("You have: " + total);
            System.out.println("Dealer has: " + dealer);
            System.out.println("You beat the dealer!");
            System.out.println("You win!");
            gains_losses = 2 * to_play;
        }
        return gains_losses;
    }

    /**
     * The bet amount for each round, subtracting from total.
     * @param money money for each round
     * @param console allow for user interaction
     * @return the bet on the game
     */
    public int betAmount(int money, Scanner console) {
        String answer = "";
        int bet = 0;
        loop:
        while(!answer.matches("\\d+") && bet > money || bet < 10) {
            System.out.println("How much would you like to bet?");
            answer = console.next();
            try {
                bet = Integer.parseInt(answer);
                if (bet < 10) {
                    System.out.println("There is a minimum bet of $10");
                } else  if(bet > money){
                    System.out.println("You don't have that much money.");
                } else {
                    System.out.println("You bet $" + bet);
                }
            } catch (NumberFormatException e) {
                continue loop;
            }
        }
        return bet;
    }

    /**
     * To decide if they want to hit or not.
     * @param total total of player's cards
     * @param console user interaction
     * @return true if the player wants to hit or false if not
     */
    public boolean hit(int total, Scanner console){
        boolean ans;
        System.out.println();
        System.out.println("You have: " + total);
        System.out.println("Do you want to hit?");
        String answer = console.next();
        if (answer.indexOf("y") == 0 || answer.indexOf("Y") == 0) {
            ans = true;
        } else if (answer.indexOf("n") == 0 || answer.indexOf("N") == 0) {
            ans = false;
        }	else {
            System.out.println();
            ans = false;
        }
        return ans;
    }

    /**
     * Allows the user to decide if they are going to play again.
     * @param console user interaction
     * @param money total money the player has
     * @return true if the user wants to play again or false if not
     */
    public boolean playAgain(Scanner console, int money){
        System.out.println("You have: $" + money);
        if (money == 0) {
            System.out.println("You're out of money. House wins.");
            return false;
        }
        String answer = "";
        while(answer.equals("") || answer.matches("\\d+")) {
            System.out.println("Do you want to play again?");
            answer = console.next();
        }
        if (answer.indexOf("y") == 0 || answer.indexOf("Y") == 0) {
            return true;
        } else {
            if (money > 100) {
                System.out.println("Congratulations! You won: $" + (money - 100));
            } else {
                System.out.println("You lost: $" + (100 - money));
            }
            return false;
        }
    }

    /**
     * Prints the introduction to the game.
     * @param money total money that the user is given
     */
    public void intro(int money) {
        System.out.println("Welcome to BlackJack!");
        System.out.println();
        System.out.println("You have: $" + money);
    }
}
