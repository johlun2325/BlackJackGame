package View;

import Controller.LanguageManager;

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
    public JButton language;

    private ImageIcon cardBack;

    private JLabel cardBackLabel;
    private JLabel totalCapital;
    private JLabel currentBet;
    private JLabel name;
    private JLabel instructions;
    private JLabel userHandValue; ///

    private JPanel buttonPanel;
    protected JPanel userHandPanel;
    private JPanel houseHandPanel;
    private JPanel betAndCapitalPanel;
    private JPanel centerPanel;

    private JDialog dialog;
    private JScrollPane scrollPane;
    private JTextArea rulesText;

    private JPanel mainPanel;

    private final Color brightYellow = new Color(255, 200, 0);
    private final Color lightYellow = new Color(255, 235, 150);
    private final Font instructionsFont = new Font("Rockwell Condensed", Font.BOLD, 18);
    private final Font labelFont = new Font("Rockwell Condensed", Font.BOLD, 14);
    private final Font rulesFont = new Font("Arial", Font.BOLD, 14);

    private final String gameTitle = "Black Jack";
    private final String cardBackFilePath = "src/Cards/Background/cardBack_blue2.png";
    private final String backgroundFilePath = "src/Background/background.jpg";
    private final String rulesFilePath = "src/rules.txt";


    //Text with properties
    private String showPlayerNameText =  "Player:";
    private String showCapitalText = "Total capital:";
    private String showCurrentBetText = "Current bet:";
    private String showRulesText = "Rules";
    private String rulesTitleText =  "Black Jack rules";
    private String drawCardText =  "Hit me!";
    private String newGameText = "New Game";
    private String stopText = "Stop";
    private String exitText =  "";
    private String instructionsText = "";
    private String langugeButtonText = "Svenska";
    private String handValue = "Hand value";


    public GUI() {
//        LanguageManager.setLanguage("en"); // setting initial language
//        exitText = LanguageManager.getMessage(exitText);

        initiateComponents();
        addComponents();
        this.setLayout(new BorderLayout());
        this.add(mainPanel,BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle(gameTitle);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    public void setCurrentHandValue(String value){

        userHandValue.setText(handValue + value);
    }

    private void addComponents() {
        centerPanel.add(buttonPanel);
        centerPanel.add(instructions);
        buttonPanel.add(newCard);
        buttonPanel.add(noMoreCards);
        buttonPanel.add(newGame);
        buttonPanel.add(rules);
        buttonPanel.add(exit);
        buttonPanel.add(language);
        buttonPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(buttonPanel);
        centerPanel.add(instructions);

        houseHandPanel.add(cardBackLabel);
        houseHandPanel.setOpaque(false);

        betAndCapitalPanel.add(name);
        betAndCapitalPanel.add(totalCapital);
        betAndCapitalPanel.add(currentBet);
        betAndCapitalPanel.setOpaque(false);

        betAndCapitalPanel.add(userHandValue);

        userHandPanel.setOpaque(false);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(userHandPanel, BorderLayout.SOUTH);
        mainPanel.add(houseHandPanel,BorderLayout.NORTH);
        mainPanel.add(betAndCapitalPanel,BorderLayout.WEST);
    }

    private void createMainPanel() {
        ImageIcon backgroundImage = new ImageIcon(backgroundFilePath);
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
        userHandValue = new JLabel("0");  ////
        userHandValue.setFont(labelFont);
        userHandValue.setForeground(brightYellow);


        language = new JButton(langugeButtonText); //SET LANGUAGE, SEN ACTIONP
        buttonPanel = new JPanel(new FlowLayout());
        userHandPanel = new JPanel();
        houseHandPanel = new JPanel();
        betAndCapitalPanel = new JPanel(new GridLayout(4, 1));
        centerPanel = new JPanel(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        newCard = new JButton(drawCardText);
        noMoreCards = new JButton(stopText);
        newGame = new JButton(newGameText);
        rules = new JButton(showRulesText);
        exit = new JButton(exitText);

        name = new JLabel(showPlayerNameText);
        name.setForeground(brightYellow);
        name.setFont(labelFont);


        totalCapital = new JLabel(showCapitalText);
        totalCapital.setForeground(brightYellow);
        totalCapital.setFont(labelFont);

        currentBet = new JLabel(showCurrentBetText);
        currentBet.setForeground(brightYellow);
        currentBet.setFont(labelFont);

        instructions = new JLabel(instructionsText);
        instructions.setFont(instructionsFont);
        instructions.setForeground(brightYellow);
        instructions.setHorizontalAlignment(JLabel.CENTER);

        cardBack = new ImageIcon(cardBackFilePath);
        cardBackLabel = new JLabel(cardBack);

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


    public void removeHouseCards() {
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
        String s = showCapitalText;
        totalCapital.setText(s + " " + capital);
    }

    public void setCurrentBet(int bet) {
        currentBet.setText(showCurrentBetText+ " " + bet);
    }


    public void setPlayerName(String playerName) {
        name.setText(showPlayerNameText + " " +  playerName);
    }


    public String readRulesFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot read file path";
        }
    }


    public void showRules() {
        String rulesText = readRulesFromFile(rulesFilePath);

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
        dialog.setTitle(rulesTitleText); //P
        dialog.add(scrollPane);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


    public void updateInstructions(String instruction){
       instructions.setText(instruction);
    }

}


