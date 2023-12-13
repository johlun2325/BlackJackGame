package Model;

import javax.swing.*;

public class BlackJackLogic {

    private DeckOfCards deckOfCards;
    private  User user;
    private House house;
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


    public void setUserValues(){
        userName = JOptionPane.showInputDialog("Enter player name: ");
        String capital = JOptionPane.showInputDialog("Enter capital: "); //ev kontroll loop här ist.
        try {
            currentCapital = Integer.parseInt(capital);
        } catch (NumberFormatException e) {
            currentCapital = 1000;
        }
    }

    public String calculateWinner(){
        return "";
    }
    public void placeBet(){}
    public void startNewRound(){}
    private void payOutWinnings(){}

}
