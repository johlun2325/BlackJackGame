package View;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public JButton newCard;
    public JButton noMoreCards;
    public JButton newGame;

    private ImageIcon cardBack;

    private JLabel deckLabel;
    private JLabel numberOfCardsLeftInDeck;
    private JLabel playerScoreLabel;
    private JLabel NPCScoreLabel;

    private JPanel deckPanel;
    private JPanel buttonPanel;
    private JPanel playerHandPanel;
    private JPanel NPCHandPanel;
    private JPanel scorePanel;

    private JPanel mainPanel;


    public GUI() {
        initiateComponents(); // initierar alla komponenter
        addComponents(); //adderar komponenter till varandra

        this.add(mainPanel); //mainPanel har resterande komponenter på sig
        this.setLayout(new FlowLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Black Jack");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    /*private void setUserNameAndSum(){
        nameAndSumPanel.add(new JLabel("Namn: ")
    */

    private void addComponents(){
        buttonPanel.add(newCard);
        buttonPanel.add(noMoreCards);
        buttonPanel.add(newGame);

        deckPanel.add(deckLabel);
        deckPanel.add(numberOfCardsLeftInDeck);
        scorePanel.add(playerScoreLabel);
        scorePanel.add(NPCScoreLabel);

        mainPanel.add(BorderLayout.NORTH, buttonPanel);
        mainPanel.add(BorderLayout.CENTER, playerHandPanel);
        mainPanel.add(BorderLayout.SOUTH, NPCHandPanel);
        mainPanel.add(BorderLayout.WEST, deckPanel);
        mainPanel.add(BorderLayout.EAST, scorePanel);
    }

    private void initiateComponents(){
        newCard = new JButton("Hit me!");
        noMoreCards = new JButton("Stop!");
        newGame = new JButton("New game");

        cardBack = new ImageIcon("src/Cards/Background/cardBack_blue2.png");
        deckLabel = new JLabel(cardBack);
        playerScoreLabel = new JLabel();
        NPCScoreLabel = new JLabel();
        numberOfCardsLeftInDeck = new JLabel();

        deckPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel = new JPanel();
        playerHandPanel = new JPanel();
        NPCHandPanel = new JPanel();
        scorePanel = new JPanel(new GridLayout(2, 1));
        mainPanel = new JPanel(new BorderLayout());
    }

    public static void main(String[] args) {
        new GUI();
    }

}
