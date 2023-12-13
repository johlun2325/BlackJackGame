package Model;

public class BlackJackLogic {

    private DeckOfCards deckOfCards;
    private  User user;
    private House house;
    private int currentBet;

    private String userName;
    private int currentCapital;

    public BlackJackLogic() {
        deckOfCards = new DeckOfCards();
        user = new User(userName, currentCapital);
        house = new House();

    }

    public void setUserValues(String name, int startCapital){
        this.userName = name;
        this.currentCapital = startCapital;
    }

    public String calculateWinner(){
        return "";
    }
    public void placeBet(){}
    public void startNewRound(){}
    private void payOutWinnings(){}

}
