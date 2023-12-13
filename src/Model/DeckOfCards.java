package Model;

import java.util.ArrayList;
import java.util.List;

public class DeckOfCards {

    private List<Card> listOfCards = new ArrayList<>();

    public DeckOfCards() {

    }

    public void createDeck(){}

    public Card drawCard(){

        return listOfCards.get(0); //ändra vid implementation
    }

    public void shuffle(){}
}
