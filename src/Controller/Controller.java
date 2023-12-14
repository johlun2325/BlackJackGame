package Controller;

import Model.Card;
import Model.CardInterface;
import Model.DeckOfCards;
import Model.Player;
import View.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controller {

            switch (logic.calculateWinner()) {
                case WIN -> JOptionPane.showMessageDialog(null, "You win: " + logic.payOutWinnings() + "€");
                case LOSE -> JOptionPane.showMessageDialog(null, "You lose ");
                case DRAW -> JOptionPane.showMessageDialog(null, "It's a draw!");

            }
        } else if (e.getSource() == gui.newGame){
            gui.newRoundLayout();
            logic.discardAllHands();
            logic.dealCardsAtStartOfRound();
            updateAllHandImages();
        }

    }

    public List<JLabel> getCardImages(Player player) {
        List<JLabel> cardImages = new ArrayList<>();
        for (CardInterface card : player.getCurrentHand()) {
            cardImages.add(card.getImage());
        }

        return cardImages;
    }

    public static void main(String[] args) {

        new BlackJackLogic();
    }
}
