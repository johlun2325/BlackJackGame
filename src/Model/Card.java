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
    }


    public int getValue() {
        return value;
    }

    public JLabel getCardImage() {
        return cardImage;
    }
}
