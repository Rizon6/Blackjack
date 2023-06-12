import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Deck {

    //the array of cards in the deck where the top card is in the first index
    private Stack<Card> myCards;
    //the number of cards currently in the deck
    private int numCards;

    // Constructor with a default of one deck and no shuffling
    public Deck() {
        //call the other constructor, defining one deck without shuffling
        this(1, false);
    }
    // Constructor that defines the number of decks (how many sets of 52) and if it should be shuffled
    public Deck(int numDecks, boolean shuffle) {
        this.numCards = numDecks * 52;
        this.myCards = new Stack<Card>();

        // for each deck
        for (int d = 0; d < numDecks; d++) {
            for (int s = 0; s < 4; s++) {
                for (int n = 1; n <= 13; n++) {
                    this.myCards.push(new Card(Suit.values()[s], n));
                }
            }
        }

        // shuffle, if true
        if (shuffle) {
            this.shuffle();
        }
    }

    //shuffle deck by randomly swapping pairs of cards
    public void shuffle() {
        List<Card> tempList = new ArrayList<Card>(this.myCards);
        Collections.shuffle(tempList);
        this.myCards = new Stack<Card>();
        for (Card card : tempList) {
            this.myCards.push(card);
        }
    }
    

    // Deal the next card from the top of the deck
    public Card dealNextCard() {
        if (this.numCards == 0) {
            return null;
        }
        Card top = this.myCards.pop();
        this.numCards--;
        return top;
    }
    

    // Print the top cards in the deck
    public void printDeck(int numToPrint) {
        Iterator<Card> iterator = this.myCards.iterator();
        int i = 1;
        while (iterator.hasNext() && i <= numToPrint) {
            System.out.printf("%3d/%d %s\n", i, this.numCards, iterator.next().toString());
            i++;
        }
        if (this.numCards > numToPrint) {
            System.out.printf("\t\t[%d other]\n", this.numCards - numToPrint);
        }
    }
    

}
