package Model;

public class BlackJackLogic {

    private DeckOfCards deckOfCards;
    private  User user;
    private House house;
    private int currentBet;

    public BlackJackLogic(DeckOfCards deckOfCards, User user, House house) {
        this.deckOfCards = deckOfCards;
        this.user = user;
        this.house = house;
    }

    public String calculateWinner(){
        return "";
    }
    public void placeBet(){}
    public void startNewRound(){}
    private void payOutWinnings(){}

}
