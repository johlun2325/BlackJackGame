package Controller;

import Model.BlackJackLogic;
import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private BlackJackLogic logic;
    private GUI gui;

    public Controller(){
        logic = new BlackJackLogic();
        gui = new GUI();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //alla händelser från gui med implementerad logik
        //todo: lyssnare på field med anv.namn och kapital
        //todo: knapp med lyssnare för ta emot insats fr anv

        if (e.getSource() == gui.newCard){
            //draw new card
        } else if( e.getSource() == gui.noMoreCards){
            //huset drar och vinnare visas
        } else if (e.getSource() == gui.newGame){
            //starta ett nytt spel
        }

    }

    public static void main(String[] args) {
        new Controller();
    }
}
