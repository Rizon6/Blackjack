// Programmer: Rizon Takabe
// Class: CS 141 Face-to-face
// Date: 4/24/23
// Assignment: Lab 3 Deck of Cards
// Purpose: Make a program that plays black jack with the user by creating card objects
// I used a stack in the deck class, enum in the card suit class and a case switch in the card class

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Deck theDeck = new Deck(1, true);

        // init the player objects
        Player me = new Player("Rizon");
        Player dealer = new Player("Dealer");

        me.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());
        me.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());

        // print initial hands
        System.out.println("Cards are dealt\n");
        me.printHand(true);
        dealer.printHand(false);
        System.out.println("\n");

        // flags for when each player is finished hitting
        boolean meDone = false;
        boolean dealerDone = false;
        String ans;

        while (!meDone || !dealerDone) {
            //player's turn
            if (!meDone) {
                System.out.println("Hit or Stay? (Enter H or S): ");
                ans = scanner.next();
                System.out.println();

                // if player hits
                if (ans.compareToIgnoreCase("H") == 0) {
                    //add next card in teh deck and store whether player is busted
                    meDone = !me.addCard(theDeck.dealNextCard());
                    me.printHand(true);

                } else {
                    meDone = true;
                }
            }

            // dealer's turn
            if(!dealerDone) {
                if (dealer.getHandSum() < 17) {
                    System.out.println("The Dealer hits\n");
                    dealerDone = !dealer.addCard(theDeck.dealNextCard());
                    dealer.printHand(false);
                } else {
                    System.out.println("The Dealer stays\n");
                    dealerDone = true;
                }
            }

            System.out.println();
        }

        //close scanner
        scanner.close();

        // print final hands
        me.printHand(true);
        dealer.printHand(true);

        int mySum = me.getHandSum();
        int dealersum = dealer.getHandSum();

        if(mySum > dealersum && mySum <= 21 || dealersum > 21) {
            System.out.println("You win!");
        } else {
            System.out.println("Dealer wins!");
        }

    }
}
