package io.github.p0lbang.gofish.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Deck {
    @SuppressWarnings("CanBeFinal")
    ArrayList<Card> deck;
    final Random randomizer;

    final int[] HIERARCHY = { 1,2,3,4,5,6,7,8,9,10,11,12,13 };
    public final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    final String[] SUITS = { "C", "S", "H", "D" };

    public Deck() {
        this.deck = new ArrayList<>();
        this.randomizer = new Random();
    }

    public void initializeDeck() {
        for (int index = 0; index < 51; index++) {
            this.deck.add(new Card(HIERARCHY[index % 13], RANKS[index % 13], SUITS[index % 4]));
        }
    }

    public void addCard(Card card) {
        this.deck.add(card);
        this.deck.sort(Comparator.comparing(Card::getHierarchy));
    }

    public void addCardMultiple(ArrayList<Card> cards) {
        this.deck.addAll(cards);
    }

    public Card removeCard(Card card) {
        this.deck.remove(card);
        return card;
    }

    public Card removeCard(int index) {
        return this.deck.remove(index);
    }

    public ArrayList<Card> stealCard(String rank) {
        ArrayList<Card> stolen = new ArrayList<>();

        if (!checkRank(rank)) {
            return stolen;
        }

        for (Card card : this.deck) {
            if (card.getRank().equals(rank)) {
                stolen.add(card);
            }
        }

        return stolen;
    }

    public Boolean checkRank(String rank) {
        for (Card card : this.deck) {
            // System.out.println(card.getRank()+" | "+rank);
            if (card.getRank().equals(rank)) {
                // System.out.println("True in check rank");
                return true;
            }
        }
        return false;
    }

    public Card getRandomCard() {
        int index = randomizer.nextInt(this.deck.size());
        return this.removeCard(index);
    }

    public void display() {
        for (Card card : this.deck) {
            System.out.printf("%4s",card.stringify());
            System.out.print(" | ");
        }
        System.out.println();
    }

}
