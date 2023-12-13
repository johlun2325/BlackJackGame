package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

    private List<Card> listOfCards = new ArrayList<>();

    public DeckOfCards() {
        createDeck();
    }

    public void createDeck(){
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                Card card = new Card();
                card.setSuit(i);
                card.setValue(j);
                listOfCards.add(card);
            }
        }
        ImagePaths imagePaths = new ImagePaths();
        for (int i = 0; i < 52; i++) {
            listOfCards.get(i).setImage(imagePaths.getImagePath(i));
        }
        Collections.shuffle(listOfCards);
    }

    public Card drawCard(){
        return listOfCards.get(0); //ändra vid implementation
    }

    public void shuffle(){}
}
