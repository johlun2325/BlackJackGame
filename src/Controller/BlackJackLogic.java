package Controller;

import Model.*;

import javax.swing.*;
import java.util.List;

public class BlackJackLogic {

    private DeckOfCards deckOfCards;
    private final User user;
    private final House house;
    private int currentBet;

    private String userName;
    private int currentCapital;

    public BlackJackLogic() {
        setUserValues();
        //debug
        System.out.println(userName);
        System.out.println(currentCapital);

        deckOfCards = new DeckOfCards();
        user = new User(userName, currentCapital);
        house = new House();

    }

    public void discardAllHands(){
        user.discardHand();
        house.discardHand();
    }


    public void setUserValues(){
        userName = JOptionPane.showInputDialog("Enter player name: ");
        String capital = JOptionPane.showInputDialog("Enter capital: "); //ev kontroll loop här ist.
        try {
            currentCapital = Integer.parseInt(capital);
        } catch (NumberFormatException e) {
            currentCapital = 1000;
        }
    }

    public EndOfRound calculateWinner(){

        // TODO indicate to the controller to change the GUI, maybe a popup?
        if (user.getHandValue() > house.getHandValue()) {
            payOutWinnings();
            return EndOfRound.WIN;
        } else if (user.getHandValue() < house.getHandValue()) {
            return EndOfRound.LOSE;
        } else {
            return EndOfRound.DRAW;
        }
    }
    public void placeBet(int input){
        currentBet = input;
    }

    public void houseDrawCard() {
        house.drawCard(deckOfCards.dealCard());
    }
    public void userDrawCard() {
        user.drawCard(deckOfCards.dealCard());
    }
    public void dealCardsAtStartOfRound(){
        user.drawCard(deckOfCards.dealCard());
        house.drawCard(deckOfCards.dealCard());
        user.drawCard(deckOfCards.dealCard());

    }
    public int payOutWinnings(){
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
