// the black jack player

public class Player {

    //players name
    private String name;

    //The cards in the player's current hand
    private Card[] hand = new Card[10];

    private int numCards;

    //aName the name of the player
    public Player(String aName) {

        this.name = aName;

        //set a player's hand to empty
        this.emptyHand();
    }

    //reset players hand
    public void emptyHand() {

        for (int c = 0; c < 10; c++) {
            this.hand[c] = null;
        }
        this.numCards = 0;
    }

    //add a card to the player's hand
    //returns whether the sum of the new hand is below or equal to 21
    public boolean addCard(Card aCard) {

        //print error if we already have the max number of cards
        if (this.numCards == 10) {
            System.err.printf("%s's hand already has 10 cards; " + "cannot add another\n", this.name);
            System.exit(1);
        }

        // add new card in next slot and increment number of cards counter
        this.hand[this.numCards] = aCard;
        this.numCards++;

        return (this.getHandSum() <= 21);
    }

    //get the sum of cards in the players hand
    public int getHandSum() {

        int handSum = 0;
        int cardNum;
        int numAces = 0;

        //calculate each card's contribution to the hand sum
        for (int c = 0; c < this.numCards; c++) {
            //get the number for the current card
            cardNum = this.hand[c].getNumber();

            if (cardNum == 1) { // ace
                numAces++;
                handSum += 11;
            } else if (cardNum > 10) { // face card
                handSum += 10;
            } else {
                handSum += cardNum;
            }
        }

        // if we have aces and our sum is over 21 set some or all of the aces to 1
        while (handSum > 21 && numAces > 0) {
            handSum -= 10;
            numAces--;
        }

        return handSum;
    }

    // print the cards in the player's hand
    // showFirstCard whether the first card is hidden or not
    public void printHand(boolean showFirstCard) {
        System.out.printf("%s's cards:\n", this.name);
        for (int c = 0; c < this.numCards; c++) {
            if (c == 0 && !showFirstCard) {
                System.out.println(" [hidden]");
            } else {
                System.out.printf(" %s\n", this.hand[c].toString());
            }
        }
    }

}