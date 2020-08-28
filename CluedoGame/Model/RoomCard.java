import javax.swing.*;
import java.awt.*;

public class RoomCard extends Card
{
    private String roomName;


    RoomCard(String aRoomName, JLabel cardIcon, Image cardImage)
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
