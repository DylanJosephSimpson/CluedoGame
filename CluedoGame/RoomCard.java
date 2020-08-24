import javax.swing.*;

public class RoomCard extends Card
{
    private String roomName;
    private JLabel cardIcon;

    RoomCard(String aRoomName, JLabel cardIcon)
    {
        roomName = aRoomName;
        this.cardIcon = cardIcon;

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
