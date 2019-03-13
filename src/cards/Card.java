package cards;

/**
 * Card Object
 * @author Laurent based on acknapp's project: https://github.com/acknapp/BlackJack
 */
public class Card {
    private int value;
    public String name;
    public String suite;
    private boolean Ace;

    /**
     * Card Constructor.
     * @param name of card
     * @param suite of card
     */
    public Card(String name, String suite){
        this.name = name;
        this.suite = suite;
        this.value = determineCardValue(name);
        this.Ace = this.name.equals("Ace");
    }

    /**
     * Prints the name of the card.
     */
    public void printCard(){
        System.out.println(this.name + " of " + this.suite);
    }

    /**
     * Gives the value of the card.
     * @return value
     */
    public int giveValue(){
        return this.value;
    }

    /**
     * Checks if this card is an Ace.
     * @return Ace
     */
    public boolean isAce(){
        return Ace;
    }

    /**
     * Given the name of a card, determines the value of that card.
     * @param name the given card
     * @return value of the card
     */
    private int determineCardValue(String name) throws NumberFormatException{
        int value;
        try{
            value = Integer.parseInt(name.substring(0,1));
            return value > 2? value : 10;
        } catch (NumberFormatException e){
            if (name.charAt(0) == 'K' || name.charAt(0) == 'Q' || name.charAt(0) == 'J'){
                value = 10;
            } else {
                value = 11;
            }
            return value;
        }
    }
}
