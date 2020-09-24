import java.util.*;
import java.io.*;
public class StairsGenerator {
    
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private Room stairRoom;
    private Player player;
    
    public StairsGenerator(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }
    
    public Stairs createStairs() {
        Random rand = new Random();
        int num = (rand.nextInt(this.roomList.size()));
        Room stairsRoom = this.roomList.get(num);
        
        int stairsx = stairsRoom.getxFirst()+25*(rand.nextInt(stairsRoom.getWidthUnits() - 2) + 1);
        int stairsy = stairsRoom.getyFirst()+25*(rand.nextInt(stairsRoom.getHeightUnits() - 2) + 1);
        Stairs sta = new Stairs(stairsx,stairsy);
        this.roomList.get(num).setIsStairRoomTrue();
        return sta;
    }
    
    public void drawStairs(Stairs sta) {
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.rectangle(sta.getx()+12.5,sta.gety()+12.5,12.5,12.5);
    }
    
    public void drawMiniStairs(Stairs sta, Player player) {
        StdDraw.setPenRadius(.005);
        StdDraw.setPenColor(StdDraw.ORANGE);
        double smallc = .1;
        int relativePlayerx = 50;
        int relativePlayery = -150;
        StdDraw.rectangle(relativePlayerx+sta.getx()*smallc+12.5*smallc+player.getx(),player.gety()+relativePlayery+sta.gety()*smallc+12.5*smallc,12.5*smallc,12.5*smallc);
    }
}