package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI extends JFrame {
    public JButton newCard;
    public JButton noMoreCards;
    public JButton newGame;

    private ImageIcon cardBack;

    private JLabel cardBackLabel;
    private JLabel numberOfCardsLeftInDeck;
    private JLabel playerScoreLabel;
    private JLabel NPCScoreLabel;

//    private JPanel deckPanel; //kvar?
    private JPanel buttonPanel;
    protected JPanel userHandPanel;
    private JPanel houseHandPanel;
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

        houseHandPanel.add(cardBackLabel);
        scorePanel.add(playerScoreLabel);
        scorePanel.add(NPCScoreLabel);

        mainPanel.add(numberOfCardsLeftInDeck, BorderLayout.WEST);  //flytta main west
//        mainPanel.add(BorderLayout.WEST, deckPanel);
        mainPanel.add(BorderLayout.SOUTH, buttonPanel);
        mainPanel.add(BorderLayout.CENTER, userHandPanel);
        mainPanel.add(BorderLayout.NORTH, houseHandPanel);
        mainPanel.add(BorderLayout.EAST, scorePanel);
    }

    private void initiateComponents(){
        newCard = new JButton("Hit me!");
        noMoreCards = new JButton("Stop!");
        newGame = new JButton("New game");

        cardBack = new ImageIcon("src/Cards/Background/cardBack_blue2.png");
        cardBackLabel = new JLabel(cardBack);
        playerScoreLabel = new JLabel();
        NPCScoreLabel = new JLabel();
        numberOfCardsLeftInDeck = new JLabel();

//        deckPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel = new JPanel();
        userHandPanel = new JPanel();
        houseHandPanel = new JPanel();
        scorePanel = new JPanel(new GridLayout(2, 1));
        mainPanel = new JPanel(new BorderLayout());
    }
    public void updateUserHandImages(List<JLabel> cardImages) {
        userHandPanel.removeAll();
        for (JLabel cardImage : cardImages) {
            userHandPanel.add(cardImage);
        }
        revalidate();
        repaint();
    }

    public void removeUpsideDownCard(){
        houseHandPanel.removeAll();
    }

    public void updateHouseHandImages(List<JLabel> cardImages) {
        for (JLabel cardImage : cardImages) {
            houseHandPanel.add(cardImage);
        }
        revalidate();
        repaint();
    }

    public void newRoundLayout (){
        houseHandPanel.removeAll();
        houseHandPanel.add(cardBackLabel);
    }


    public JPanel getUserHandPanel() {
        return userHandPanel;
    }

    public JPanel getHouseHandPanel() {
        return houseHandPanel;
    }

    public static void main(String[] args) {
        new GUI();
    }

}


