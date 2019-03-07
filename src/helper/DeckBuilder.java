package helper;

import cards.Card;
import main.Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckBuilder {

    private final static String[] suites = Blackjack.getSuites();
    private final static String[] names = Blackjack.getNames();

    /**
     * Constructs a deck given the suites and cards.
     * @return a constructed deck
     */
    public static List<Card> buildDeck(){
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < suites.length; i++){
            for (int j = 0; j < names.length; j++){
                Card k = new Card(names[j], suites[i]);
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

    public static List<Card> addNewShuffledDeck(List<Card> deck) {
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
