package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

    private List<Card> deckOfCards = new ArrayList<>();

    public DeckOfCards() {
        createNewDeck();
    }

    public void createNewDeck(){
        deckOfCards.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                Card card = new Card(i,j);
                deckOfCards.add(card);
            }
        }
        ImagePaths imagePaths = new ImagePaths();
        for (int i = 0; i < 52; i++) {
            deckOfCards.get(i).setImage(imagePaths.getImagePath(i));
        }
        Collections.shuffle(deckOfCards);
    }

    public Card dealCard(){
        Card tempCard = deckOfCards.get(0);
        deckOfCards.remove(0);
        return tempCard;
    }

    public void shuffle(){}
}
