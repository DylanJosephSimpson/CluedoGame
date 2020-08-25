import javax.swing.*;

public class RoomCard extends Card
{
    private String roomName;


    RoomCard(String aRoomName, JLabel cardIcon)
    {
        roomName = aRoomName;
        super.cardIcon = cardIcon;

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
