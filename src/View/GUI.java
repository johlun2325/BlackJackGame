package View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GUI extends JFrame {
    public JButton newGame;
    public JButton noMoreCards;
    public JButton newCard;
    public JButton rules;
    public JButton exit;

    private ImageIcon cardBack;

    private JLabel cardBackLabel;
    private JLabel totalCapital;
    private JLabel currentBet;
    private JLabel name;
    private JLabel instructions;

    private JPanel buttonPanel;
    protected JPanel userHandPanel;
    private JPanel houseHandPanel;
    private JPanel betAndCapitalPanel;
    private JPanel centerPanel;

    private JDialog dialog;
    private JScrollPane scrollPane;
    private JTextArea rulesText;

    private JPanel mainPanel;

    private Color brightYellow = new Color(255, 200, 0);
    private Color lightYellow = new Color(255, 235, 150);
    Font instructionsFont = new Font("Rockwell Condensed", Font.BOLD, 18);
    Font labelFont = new Font("Rockwell Condensed", Font.BOLD, 14);
    Font rulesFont = new Font("Arial", Font.BOLD, 14);


    public GUI() {
        initiateComponents(); // initierar alla komponenter
        addComponents(); //adderar komponenter till varandra
        this.setLayout(new BorderLayout());
        this.add(mainPanel,BorderLayout.CENTER); //mainPanel har resterande komponenter på sig
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Black Jack");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }


    private void addComponents() {
        centerPanel.add(buttonPanel);
        centerPanel.add(instructions);
        buttonPanel.add(newCard);
        buttonPanel.add(noMoreCards);
        buttonPanel.add(newGame);
        buttonPanel.add(rules);
        buttonPanel.add(exit);
        buttonPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrera instruktionerna

        centerPanel.add(buttonPanel);
        centerPanel.add(instructions);

        houseHandPanel.add(cardBackLabel);
        houseHandPanel.setOpaque(false);

        betAndCapitalPanel.add(name);
        betAndCapitalPanel.add(totalCapital);
        betAndCapitalPanel.add(currentBet);
        betAndCapitalPanel.setOpaque(false);

        userHandPanel.setOpaque(false);

        mainPanel.add(centerPanel,BorderLayout.CENTER);
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
        rules = new JButton("Rules");
        exit = new JButton("Exit");

        instructions = new JLabel(" ");
        instructions.setFont(instructionsFont);
        instructions.setForeground(brightYellow);
        instructions.setHorizontalAlignment(JLabel.CENTER);

        cardBack = new ImageIcon("src/Cards/Background/cardBack_blue2.png");
        cardBackLabel = new JLabel(cardBack);

        name = new JLabel("Player name: ");
        name.setForeground(brightYellow);
        name.setFont(labelFont);

        totalCapital = new JLabel("Total Capital: ");
        totalCapital.setForeground(brightYellow);
        totalCapital.setFont(labelFont);

        currentBet = new JLabel("Current Bet: ");
        currentBet.setForeground(brightYellow);
        currentBet.setFont(labelFont);

        buttonPanel = new JPanel(new FlowLayout());
        userHandPanel = new JPanel();
        houseHandPanel = new JPanel();
        betAndCapitalPanel = new JPanel(new GridLayout(3, 1));
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

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


    public void setPlayerName(String playerName) {
        name.setText("Player: " + playerName);
    }


    public String readRulesFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "Kunde inte läsa in reglerna.";
        }
    }


    public void showRules() {
        String rulesText = readRulesFromFile("src/rules.txt");

        this.rulesText = new JTextArea(rulesText);
        this.rulesText.setEditable(false);
        this.rulesText.setWrapStyleWord(true);
        this.rulesText.setLineWrap(true);
        this.rulesText.setCaretPosition(0);
        this.rulesText.setBackground(lightYellow);
        this.rulesText.setFont(rulesFont);

        scrollPane = new JScrollPane(this.rulesText);
        scrollPane.setPreferredSize(new Dimension(500, 350));

        dialog = new JDialog();
        dialog.setTitle("Black Jack-regler");
        dialog.add(scrollPane);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


    public void updateInstructions(String instruction){
       instructions.setText(instruction);
    }

}


