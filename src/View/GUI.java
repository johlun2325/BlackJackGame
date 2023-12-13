package View;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public JButton newCard;
    private JButton noMoreCards;
    private JButton shuffle;
    private JButton newGame;
    private ImageIcon cardBack;
    private JLabel deckLabel;
    private JLabel numberOfCardsLeftInDeck;
    private JPanel deckPanel;
    private JPanel buttonPanel;
    private JPanel playerHandPanel;
    private JPanel NPCHandPanel;
    private JPanel scorePanel;
    private JLabel playerScoreLabel;
    private JLabel NPCScoreLabel;

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

    private void addComponents(){
        buttonPanel.add(newCard);
        buttonPanel.add(noMoreCards);
        buttonPanel.add(shuffle);
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
        shuffle = new JButton("Shuffle Deck");
        newGame = new JButton("New game");
        cardBack = new ImageIcon("Cards/Background/cardBack_blue2.png");
        deckLabel = new JLabel(cardBack);
        numberOfCardsLeftInDeck = new JLabel();
        deckPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel = new JPanel();
        playerHandPanel = new JPanel();
        NPCHandPanel = new JPanel();
        scorePanel = new JPanel(new GridLayout(2, 1));
        playerScoreLabel = new JLabel();
        NPCScoreLabel = new JLabel();
        mainPanel = new JPanel(new BorderLayout());
    }

    //mainmetod för att testa hur guit initieras
    public static void main(String[] args) {
        new GUI();
    }
}
