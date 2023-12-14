package Controller;

import Model.*;
import View.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BlackJackLogic implements ActionListener {

    private DeckOfCards deckOfCards;
    private final User user;
    private final House house;
    private int currentBet;

    private String userName;
    private int currentCapital;
    private GUI gui;

    public BlackJackLogic() {
        setUserValues();
        //debug
        System.out.println(userName);
        System.out.println(currentCapital);

        deckOfCards = new DeckOfCards();
        user = new User(userName, currentCapital);
        house = new House();

        gui = new GUI();
        gui.newGame.addActionListener(this);
        gui.noMoreCards.addActionListener(this);
        gui.newCard.addActionListener(this);
        gui.setTotalCapital(currentCapital);
        nextRound();

    }

    public void discardAllHands() {
        user.discardHand();
        house.discardHand();
    }

    public void updateAllHandImages() {
        gui.updateUserHandImages(getCardImages(getUser()));
        gui.updateHouseHandImages(getCardImages(getHouse()));
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        //alla händelser från gui med implementerad logik
        //todo: lyssnare på field med anv.namn och kapital
        //todo: knapp med lyssnare för ta emot insats fr anv

        if (e.getSource() == gui.newCard) {
            userDrawCard();
            gui.updateUserHandImages(getCardImages(getUser()));
            if (user.getHandValue()== -1) {
                JOptionPane.showMessageDialog(null, "You're bust!");
                nextRound();

            }

        } else if (e.getSource() == gui.noMoreCards) {

            while (getHouse().getHandValue() < 17 && getHouse().getHandValue() > 0) {
                houseDrawCard();
                gui.removeUpsideDownCard();
                gui.updateHouseHandImages(getCardImages(getHouse()));

            }

            switch (calculateWinner()) {
                case WIN -> JOptionPane.showMessageDialog(null, "You win: " + payOutWinnings() + "€");
                case LOSE -> JOptionPane.showMessageDialog(null, "You lose ");
                case DRAW -> JOptionPane.showMessageDialog(null, "It's a draw!");
            }
            nextRound();
        } else if (e.getSource() == gui.newGame) {
            nextRound();
        }

    }

    private void nextRound(){
        if (deckOfCards.getDeckOfCards().size() > 15) {
            deckOfCards.createNewDeck();
        }
        gui.newRoundLayout();
        discardAllHands();
        dealCardsAtStartOfRound();
        updateAllHandImages();
        placeBet();
    }

    private void placeBet() {
        currentBet = 0;
        String answer = JOptionPane.showInputDialog(null, "Place your bet");

        int bet = Integer.parseInt(answer);

        if (bet < currentCapital) {
            currentBet = bet;
            user.subractBetFromCapital(bet);
            gui.setTotalCapital(user.getCurrentCapital());
            System.out.println(currentBet); //debug
        }
        gui.setCurrentBet(currentBet);
    }

    public List<JLabel> getCardImages(Player player) {
        List<JLabel> cardImages = new ArrayList<>();
        for (Card card : player.getCurrentHand()) {
            cardImages.add(card.getCardImage());
        }

        return cardImages;
    }


    public void setUserValues() {
        userName = JOptionPane.showInputDialog("Enter player name: ");
        String capital = JOptionPane.showInputDialog("Enter capital: "); //ev kontroll loop här ist.
        currentBet = 0;
        try {
            currentCapital = Integer.parseInt(capital);
        } catch (NumberFormatException e) {
            currentCapital = 1000;
        }
    }

    public EndOfRound calculateWinner() {

        if (user.getHandValue() > house.getHandValue()) {
            return EndOfRound.WIN;
        } else if (user.getHandValue() < house.getHandValue()) {
            return EndOfRound.LOSE;
        } else {
            return EndOfRound.DRAW;
        }
    }

    public void houseDrawCard() {
        house.drawCard(deckOfCards.dealCard());
    }

    public void userDrawCard() {
        user.drawCard(deckOfCards.dealCard());
    }

    public void dealCardsAtStartOfRound() {
        user.drawCard(deckOfCards.dealCard());
        house.drawCard(deckOfCards.dealCard());
        user.drawCard(deckOfCards.dealCard());

    }

    public int payOutWinnings() {
        int winnings = currentBet * 2;
        user.addToTotalCapital(winnings);
        return winnings;
    }

    public List<Card> getUserCards() {
        return user.getCurrentHand();
    }

    public User getUser() {
        return user;
    }

    public House getHouse() {
        return house;
    }
}
