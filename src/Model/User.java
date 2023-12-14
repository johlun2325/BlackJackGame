package Model;

public class User extends Player{
    private  String name;
    private int currentCapital;

    public User(String name, int currentCapital){
        this.name = name;
        this.currentCapital = currentCapital;
    }

    public void addToTotalCapital(int winnings) {
        currentCapital += winnings;
    }

}
