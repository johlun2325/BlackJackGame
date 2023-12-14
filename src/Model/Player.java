package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Card> currentHand;

    public Player(){
        this.currentHand = new ArrayList<>();
    }

    public void drawCard(Card card) {
        this.currentHand.add(card);
    }

}
