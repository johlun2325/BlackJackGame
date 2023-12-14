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
    private JLabel totalCapital;
    private JLabel currentBet;

    private JPanel buttonPanel;
    protected JPanel userHandPanel;
    private JPanel houseHandPanel;
    private JPanel betAndCapitalPanel;


    private JPanel mainPanel;


    public GUI() {
        initiateComponents(); // initierar alla komponenter
        addComponents(); //adderar komponenter till varandra
        this.setLayout(new BorderLayout());
        this.add(mainPanel,BorderLayout.CENTER); //mainPanel har resterande komponenter på sig
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Black Jack");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    private void addComponents() {
        buttonPanel.add(newCard);
        buttonPanel.add(noMoreCards);
        buttonPanel.add(newGame);
        buttonPanel.setOpaque(false);



        houseHandPanel.add(cardBackLabel);
        houseHandPanel.setOpaque(false);

        betAndCapitalPanel.add(totalCapital);
        betAndCapitalPanel.add(currentBet);
        betAndCapitalPanel.setOpaque(false);


        userHandPanel.setOpaque(false);


//        mainPanel.add(BorderLayout.WEST, deckPanel);
        mainPanel.add(buttonPanel,BorderLayout.CENTER);
        mainPanel.add(userHandPanel, BorderLayout.SOUTH);
        mainPanel.add(houseHandPanel,BorderLayout.NORTH);
        mainPanel.add(betAndCapitalPanel,BorderLayout.WEST);





    }

    private void createMainPanel() {
        ImageIcon backgroundImage = new ImageIcon("src/Background/background.jpg");
        mainPanel = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        mainPanel.setOpaque(false);


    }

    private void initiateComponents() {
        newCard = new JButton("Hit me!");
        noMoreCards = new JButton("Stop!");
        newGame = new JButton("New game");

        cardBack = new ImageIcon("src/Cards/Background/cardBack_blue2.png");
        cardBackLabel = new JLabel(cardBack);

        totalCapital = new JLabel("Total Capital: ");
        currentBet = new JLabel("Current Bet: ");

        buttonPanel = new JPanel(new FlowLayout());
        userHandPanel = new JPanel();
        houseHandPanel = new JPanel();
        betAndCapitalPanel = new JPanel(new GridLayout(2, 1));

        createMainPanel();

    }

    public void updateUserHandImages(List<JLabel> cardImages) {
        userHandPanel.removeAll();
        for (JLabel cardImage : cardImages) {
            userHandPanel.add(cardImage);
        }
        revalidate();
        repaint();
    }

    public void removeUpsideDownCard() {
        houseHandPanel.removeAll();
    }

    public void updateHouseHandImages(List<JLabel> cardImages) {
        for (JLabel cardImage : cardImages) {
            houseHandPanel.add(cardImage);
        }
        revalidate();
        repaint();
    }



    public void newRoundLayout() {
        houseHandPanel.removeAll();
        houseHandPanel.add(cardBackLabel);
    }


    public void setTotalCapital(int capital) {
        String s = "Total Capital: ";
        totalCapital.setText(s + capital);
    }

    public void setCurrentBet(int bet) {
        String s = "Current Bet: ";
        currentBet.setText(s+bet);
    }

}


