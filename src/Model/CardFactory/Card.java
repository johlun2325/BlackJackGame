package Model.CardFactory;

import javax.swing.*;

public interface Card {  //change interface name later

    String getSuit();
    int getValue();
    void setImage(String path);
    JLabel getImage();



}
