package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

    private List<CardInterface> deckOfCards = new ArrayList<>();

    public DeckOfCards() {
        createCardsFromFactory();
    }

//    public void createNewDeck() {
//        deckOfCards.clear();
//        for (int i = 0; i < 4; i++) {
//            for (int j = 2; j < 15; j++) {
//                Card card = new Card(i,j);
//                deckOfCards.add(card);
//            }
//        }
//        ImagePaths imagePaths = new ImagePaths();
//        for (int i = 0; i < 52; i++) {
//            deckOfCards.get(i).setImage(imagePaths.getImagePath(i));
//}
//        Collections.shuffle(deckOfCards);
//}

    public void createCardsFromFactory() {
        CardFactory factory = new CardFactory();
        List<Integer> listOfValues = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        List<CardSuits> listOfSuits = Arrays.asList(
                CardSuits.SPADE,
                CardSuits.CLUB,
                CardSuits.HEART,
                CardSuits.DIAMOND);

        deckOfCards.clear();

        for (int i = 0; i < listOfSuits.size(); i++) {
            for (int j = 0; j < listOfValues.size(); j++) {
                CardInterface card = factory.createCard(listOfSuits.get(i), j);
                deckOfCards.add(card);
            }
        }
        ImagePaths imagePaths = new ImagePaths();
        for (int i = 0; i < 52; i++) {
            deckOfCards.get(i).setImage(imagePaths.getImagePath(i));
        }


        Collections.shuffle(deckOfCards);

    }

    public CardInterface dealCard() {
        CardInterface tempCard = deckOfCards.get(0);
        deckOfCards.remove(0);
        return tempCard;
    }

    public void shuffle() {
    }

    public List<CardInterface> getDeckOfCards(){
        return deckOfCards;
    }
}

