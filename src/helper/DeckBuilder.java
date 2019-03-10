package helper;

import cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckBuilder {

    private static final String[] SUITES = {"Hearts", "Spades", "Clubs", "Diamonds"};
    private static final String[] NAMES = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    /**
     * Constructs a deck given the SUITES and cards.
     * @return a constructed deck
     */
    public static List<Card> buildDeck(){
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < SUITES.length; i++){
            for (int j = 0; j < NAMES.length; j++){
                Card k = new Card(NAMES[j], SUITES[i]);
                deck.add(k);
            }
        }
        return deck;
    }

    /**
     * Shuffles the cards so that they are in random order.
     * @param deck of cards
     * @return a new shuffled deck
     */
    public static List<Card> shuffleDeck(List<Card> deck){
        List<Card> shuffledDeck = new ArrayList<>();
        int r = 0;
        while (deck.size() > 0){
            Random card = new Random();
            r = card.nextInt(deck.size());
            Card temp = deck.remove(r);
            shuffledDeck.add(temp);
        }
        return shuffledDeck;
    }
}
