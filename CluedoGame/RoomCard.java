public class RoomCard extends Card
{
    private String roomName;

    RoomCard(String aRoomName)
    {
        roomName = aRoomName;

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
