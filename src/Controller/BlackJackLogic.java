package Controller;

import Model.*;
import Model.CardFactory.Card;
import View.GUI;
import View.Instructions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BlackJackLogic implements ActionListener {

    private DeckOfCards deckOfCards;
    private GUI gui;
    private final User user;
    private final House house;
    private String userName;
    private int currentBet;
    private int currentCapital;


    public BlackJackLogic() {
        setUserValues();

        deckOfCards = new DeckOfCards();
        user = new User(userName, currentCapital);
        house = new House();

        gui = new GUI();
        gui.newGame.addActionListener(this);
        gui.noMoreCards.addActionListener(this);
        gui.newCard.addActionListener(this);
        gui.rules.addActionListener(this);
        gui.exit.addActionListener(this);
        gui.setTotalCapital(currentCapital);
        gui.setPlayerName(userName);
        nextRound();

    }

    public void showUserHandValue(){
        int sum = user.getHandValue();
        gui.setCurrentHandValue(String.valueOf(sum));
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

        if (e.getSource() == gui.newCard) {
            userDrawCard();
            gui.updateUserHandImages(getCardImages(getUser()));
            if (user.getHandValue() == -1) {
                gui.updateInstructions(Instructions.BUSTED.getInstructionSV());
                JOptionPane.showMessageDialog(null, "You're bust!");
                gui.newCard.setEnabled(false);
                gui.noMoreCards.setEnabled(false);
                //nextRound();

            }

        } else if (e.getSource() == gui.noMoreCards) {
            gui.removeHouseCards();
            gui.newCard.setEnabled(false);
            gui.noMoreCards.setEnabled(false);
            while (getHouse().getHandValue() < 17 && getHouse().getHandValue() > 0) {
                System.out.println(getHouse().getHandValue());
                System.out.println(getHouse().getCurrentHand());
                houseDrawCard();
                gui.updateHouseHandImages(getCardImages(getHouse()));

            }

            switch (calculateWinner()) {
                case WIN ->
                        JOptionPane.showMessageDialog(null, EndOfRound.WIN.getEndOfRound() + payOutWinnings() + "€");
                case LOSE -> JOptionPane.showMessageDialog(null, EndOfRound.LOSE.getEndOfRound());
                case DRAW -> JOptionPane.showMessageDialog(null, EndOfRound.DRAW.getEndOfRound());
            }
            //nextRound();
        } else if (e.getSource() == gui.newGame) {
            nextRound();

        } else if (e.getSource() == gui.rules) {
            gui.showRules();

        } else if (e.getSource() == gui.exit) {
            System.exit(0);
        }


    }

    private void nextRound() {
        if (deckOfCards.getDeckOfCards().size() > 15) {
            deckOfCards.createCardsFromFactory();
        }
        gui.updateInstructions(Instructions.PLACE_BET.getInstructionSV());
        gui.newRoundLayout();
        gui.newCard.setEnabled(true);
        gui.noMoreCards.setEnabled(true);
        discardAllHands();
        updateAllHandImages();
        placeBet();
        dealCardsAtStartOfRound();
        updateAllHandImages();
        showUserHandValue();
    }

    private void placeBet() {
        currentBet = 0;
        String answer = JOptionPane.showInputDialog(null, "Place your bet");

        int bet = Integer.parseInt(answer); // exception vid "avbryt" option

        if (bet < currentCapital) {
            currentBet = bet;
            user.subtractBetFromCapital(bet);
            gui.setTotalCapital(user.getCurrentCapital());
        }
        gui.setCurrentBet(currentBet);
        gui.updateInstructions(Instructions.DECIDE_NEXT_MOVE.getInstructionSV());
    }

    public List<JLabel> getCardImages(Player player) {
        List<JLabel> cardImages = new ArrayList<>();
        for (Card card : player.getCurrentHand()) {
            cardImages.add(card.getImage());
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
            gui.updateInstructions(Instructions.WON_ROUND.getInstructionSV());
            return EndOfRound.WIN;

        } else if (user.getHandValue() < house.getHandValue()) {
            gui.updateInstructions(Instructions.LOST_ROUND.getInstructionSV());
            return EndOfRound.LOSE;

        } else {
            gui.updateInstructions(Instructions.DRAW_ROUND.getInstructionSV());
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


    public User getUser() {
        return user;
    }

    public House getHouse() {
        return house;
    }

    public static void main(String[] args) {
        new BlackJackLogic();
    }
}
