package View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GUI extends JFrame {
    public JButton newCard;
    public JButton noMoreCards;
    public JButton newGame;
    public JButton rules;

    private ImageIcon cardBack;

    private JLabel cardBackLabel;
    private JLabel totalCapital;
    private JLabel currentBet;
    private JLabel instructions;

    private JPanel buttonPanel;
    protected JPanel userHandPanel;
    private JPanel houseHandPanel;
    private JPanel betAndCapitalPanel;
    private JPanel centerPanel;

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
        centerPanel.add(buttonPanel);
        centerPanel.add(instructions);
        buttonPanel.add(newCard);
        buttonPanel.add(noMoreCards);
        buttonPanel.add(newGame);
        buttonPanel.add(rules);
        buttonPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrera instruktionerna

        centerPanel.add(buttonPanel);
        centerPanel.add(instructions);




        houseHandPanel.add(cardBackLabel);
        houseHandPanel.setOpaque(false);

        betAndCapitalPanel.add(totalCapital);
        betAndCapitalPanel.add(currentBet);
        betAndCapitalPanel.setOpaque(false);


        userHandPanel.setOpaque(false);


//        mainPanel.add(BorderLayout.WEST, deckPanel);
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

        instructions = new JLabel("Här kommer instruktioner");
        instructions.setFont(new Font("Rockwell Condensed", Font.BOLD, 18));
        instructions.setForeground(new Color(255, 200, 0));
        instructions.setHorizontalAlignment(JLabel.CENTER);

        cardBack = new ImageIcon("src/Cards/Background/cardBack_blue2.png");
        cardBackLabel = new JLabel(cardBack);

        totalCapital = new JLabel("Total Capital: ");
        currentBet = new JLabel("Current Bet: ");

        buttonPanel = new JPanel(new FlowLayout());
        userHandPanel = new JPanel();
        houseHandPanel = new JPanel();
        betAndCapitalPanel = new JPanel(new GridLayout(2, 1));
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

    public String readRulesFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "Kunde inte läsa in reglerna.";
        }
    }
/*
    public void showRules() {
        String rulesText = readRulesFromFile("C:\\Users\\annab\\OOPJ\\BlackJackGame\\src\\rules.txt"); // Ersätt med rätt sökväg till din fil
        JTextArea textArea = new JTextArea(rulesText);
        textArea.setEditable(false); /
        textArea.setWrapStyleWord(true); // Bryt ord korrekt
        textArea.setLineWrap(true); // Aktivera radbrytning

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 150)); // Ange en lämplig storlek

        JOptionPane.showMessageDialog(null, scrollPane, "Black Jack-regler", JOptionPane.INFORMATION_MESSAGE);
    }*/
/*
    public void showRules() {
        String rulesText = readRulesFromFile("C:\\Users\\annab\\OOPJ\\BlackJackGame\\src\\rules.txt");
        JTextArea textArea = new JTextArea(rulesText);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Välj en lämplig font
        textArea.setBackground(new Color(240, 240, 240)); // Välj en bakgrundsfärg

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 150));

        // Skapa en JDialog för att visa reglerna
        JDialog dialog = new JDialog();
        dialog.setTitle("Black Jack-regler");
        dialog.setContentPane(scrollPane);
        dialog.setSize(400, 200); // Ange en lämplig storlek för dialogrutan
        dialog.setLocationRelativeTo(null); // Centrera dialogrutan på skärmen
        dialog.setModal(true); // Gör dialogrutan modal

        // Lägg till en knapp för att stänga dialogrutan
        JButton closeButton = new JButton("Stäng");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true); // Visa dialogrutan
    }*/

    public void showRules() {
        String rulesText = readRulesFromFile("C:\\Users\\annab\\OOPJ\\BlackJackGame\\src\\rules.txt");

        JTextArea textArea = new JTextArea(rulesText);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 150));

        JDialog dialog = new JDialog();
        dialog.setTitle("Black Jack-regler");
        dialog.add(scrollPane);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


}


