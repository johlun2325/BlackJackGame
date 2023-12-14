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

public class Controller implements ActionListener {

    private BlackJackLogic logic;
    private GUI gui;

    public Controller(){
        
        logic = new BlackJackLogic();
        gui = new GUI();
        gui.newGame.addActionListener(this);
        gui.noMoreCards.addActionListener(this);
        gui.newCard.addActionListener(this);
        logic.dealCardsAtStartOfRound();
        updateAllHandImages();
    }

    public void updateAllHandImages(){
        gui.updateUserHandImages(getCardImages(logic.getUser()));
        gui.updateHouseHandImages(getCardImages(logic.getHouse()));
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //alla händelser från gui med implementerad logik
        //todo: lyssnare på field med anv.namn och kapital
        //todo: knapp med lyssnare för ta emot insats fr anv

        if (e.getSource() == gui.newCard){
            logic.userDrawCard();
            gui.updateUserHandImages(getCardImages(logic.getUser()));

        } else if( e.getSource() == gui.noMoreCards){

            while(logic.getHouse().getHandValue() < 17){
                logic.houseDrawCard();
                gui.removeUpsideDownCard();
                gui.updateHouseHandImages(getCardImages(logic.getHouse()));
            }

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

        new Controller();
    }
}
