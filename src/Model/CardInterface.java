package Model;

import javax.swing.*;

public interface CardInterface {  //change interface name later

    String getSuit();
    int getValue();
    void setImage(String path);
    JLabel getImage();



}
