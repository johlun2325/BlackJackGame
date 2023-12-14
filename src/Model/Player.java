package Model;

import Model.CardFactory.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Card> currentHand;

    public Player() {
        this.currentHand = new ArrayList<>();
    }

    public void discardHand(){
        currentHand.clear();
    }

    public void drawCard(Card card) {
        this.currentHand.add(card);
    }

    public int getHandValue() {
        int totalHandValue = 0;

        for (Card card : currentHand) {
            switch (card.getValue()) {
                case 11, 12, 13 -> totalHandValue += 10;
                case 14 -> totalHandValue += 11;
                default -> totalHandValue += card.getValue();
            }
        }
        //checks to see if aces can be used as value 1 instead of 11.
        if (totalHandValue > 21) {
            for (Card card : currentHand) {
                switch (card.getValue()) {
                    case 11, 12, 13 -> totalHandValue += 10;
                    case 14 -> totalHandValue += 1;
                    default -> totalHandValue += card.getValue();
                }
            }
        }
        if (totalHandValue <= 21) {
            return totalHandValue;
        } else return -1;

    }

        public List<Card> getCurrentHand() {
            return currentHand;
        }
    }

