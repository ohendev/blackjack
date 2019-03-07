package main;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import cards.Card;
import game.Game;

import static helper.DeckBuilder.buildDeck;
import static helper.DeckBuilder.shuffleDeck;

/**
 * Simple BlackJack game for fun.
 * @author Popysid from Andrew Knapp's project: https://github.com/acknapp/BlackJack
 * @version 3.1 - Separating Card class from Blackjack class, creating DeckBuilder and Game classes to separate the logic from the main.
 */
public class Blackjack {

    private  static final Game game = new Game();
    private static final int INITIAL_MONEY = 100;
    private static final String[] suites = {"Hearts", "Spades", "Clubs", "Diamonds"};
    private static final String[] names = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public static void main(String[] arg)	{
        Scanner console = new Scanner(System.in);

        // creating a deck to play
        List<Card> newDeck;
        newDeck = buildDeck();

        //setup the money of the player
        int money =	INITIAL_MONEY;
        game.intro(money);

        // start the game
        boolean play = true;
        while (money > 0 && play) {

            //Represents the total of player and dealer's cards
            int playerTotal = 0;
            int dealerTotal = 0;

            //Represents the cards of player and dealer
            List<Card> playersCards = new ArrayList<>();
            List<Card> dealersCards = new ArrayList<>();

            //deck setup
            newDeck = shuffleDeck(newDeck);

            //Asks the player how much he wants to bet
            int roundBet = game.betAmount(money, console);

            //Initial Deal
            System.out.print("First card: ");
            playerTotal += game.drawCard(newDeck, playersCards);

            System.out.print("Second card: ");
            playerTotal += game.drawCard(newDeck, playersCards);
            System.out.println();

            System.out.println("Dealer showing: ");
            dealerTotal += game.drawCard(newDeck, dealersCards);
            System.out.println("Dealer has: " + dealerTotal);

            //Player plays portion
            boolean another_card = true;
            while (playerTotal < 21){
                another_card = game.hit(playerTotal, console);
                if (!another_card) {
                    break;
                } else {
                    playerTotal += game.drawCard(newDeck, dealersCards);
                }

                for(int i = 0; i < playersCards.size(); i++){
                    if (playersCards.get(i).isAce() && playerTotal > 21) {
                        playerTotal -= 10;
                    }
                }
            }

            //Dealer plays portion
            while (dealerTotal < 17 && playerTotal < 21) {
                System.out.println("Dealer showing: " + dealerTotal);
                Card dealerCard = newDeck.remove(0);

                System.out.println("Dealer gets: ");
                dealerCard.printCard();
                dealerTotal += dealerCard.giveValue();
                dealersCards.add(dealerCard);

                for(int i = 0; i < dealersCards.size(); i++){
                    if (dealersCards.get(i).isAce() && dealerTotal > 21) {
                        playerTotal -= 10;
                    }
                }
            }

            //Decides who wins and whether to play another round
            System.out.println();
            money += game.winCheck(playerTotal, dealerTotal, roundBet);
            play = game.playAgain(console, money);
        }
    }

    /**
     * Getter to use the array in another class.
     */
    public static String[] getSuites() {
        return suites;
    }

    /**
     * Getter to use the array in another class.
     */
    public static String[] getNames() {
        return names;
    }
}