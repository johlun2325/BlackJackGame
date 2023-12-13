package Model;

import javax.swing.*;

public class Card {
    private int suit; //todo enum
    private int value;
    private JLabel cardImage;

    public void setImage(String path){
        //todo implement method
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
