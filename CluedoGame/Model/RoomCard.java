package Model;

import Model.Card;

import javax.swing.*;
import java.awt.*;

public class RoomCard extends Card
{
    private String roomName;


    public RoomCard(String aRoomName, JLabel cardIcon, Image cardImage)
    {
        roomName = aRoomName;
        super.cardIcon = cardIcon;
        super.cardImage = cardImage;

    }

    String getRoomName()
    {
        return roomName;
    }



    public String toString()
    {
        return roomName;
    }
}
