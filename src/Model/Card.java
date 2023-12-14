package Model;

import javax.swing.*;

public class Card {
    private CardSuits suit;
    private int value;
    private JLabel cardImage = new JLabel();

    public Card(int suit, int value) {

        this.value = value;
        switch (suit) {
            case 0 -> this.suit = CardSuits.CLUB;
            case 1 -> this.suit = CardSuits.DIAMOND;
            case 2 -> this.suit = CardSuits.HEART;
            case 3 -> this.suit = CardSuits.SPADE;
        }
    }

    public void setImage(String path){
        ImageIcon icon = new ImageIcon(path);
        this.cardImage.setIcon(icon);
        //todo implement method
    }

    public void setSuit(int suit) {
        if (suit < 0 || suit > 3) {
            throw new IllegalArgumentException("Måste vara mellan 0 och 3");
        } else {
            switch (suit) {
                case 0 -> this.suit = CardSuits.CLUB;
                case 1 -> this.suit = CardSuits.DIAMOND;
                case 2 -> this.suit = CardSuits.HEART;
                case 3 -> this.suit = CardSuits.SPADE;
            }
        }
    }

    public void setValue(int value) {
        if (value < 2 || value > 14) {
            throw new IllegalArgumentException("Måste vara högre än 2 och lägre än 14");
        } else {
            this.value = value;
        }

    }

    // endast för debug, remove later:
    public String type(int type) {
        String typeString = "";
        if (type == 0) {
            typeString = "\u2663";
            return typeString;
        } else if (type == 1) {
            typeString = "\u2666";
            return typeString;
        } else if (type == 2) {
            typeString = "\u2665";
            return typeString;
        } else if (type == 3) {
            typeString = "\u2660";
            return typeString;
        }
        return typeString;
    }

    public String number(int type) {
        String typeString = "";
        if (type == 11) {
            typeString = "Knekt";
            return typeString;
        } else if (type == 12) {
            typeString = "Dam";
            return typeString;
        } else if (type == 13) {
            typeString = "Kung";
            return typeString;
        } else if (type == 14) {
            typeString = "Ess";
            return typeString;
        } else {
            typeString = "" + type;
        }
        return typeString;
    }


    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                ", cardImage=" + cardImage +
                '}';
    }
}
