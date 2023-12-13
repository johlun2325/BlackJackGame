package Controller;

import Model.BlackJackLogic;
import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private BlackJackLogic logic;
    private GUI gui = new GUI();

    public Controller(BlackJackLogic logic, GUI gui){
        this.logic = logic;

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //alla händelser från gui med implementerad logik


    }
}
