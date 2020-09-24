import java.util.*;
import java.io.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class aaaMain {
    
    public double runGame(int repetitions) {
        
        double smallc = .1;
        int relativePlayerx = 50;
        int relativePlayery = -150;
        
        double time = 0;
        int roomCount = 15;
        int width = 1000, height = 1000;
        StdDraw.setCanvasSize(width, height);
        
        StdDraw.setPenRadius(.01);
        
        RoomGenerator obj1 = new RoomGenerator();
        for (int i=0;i<roomCount;i++)
            obj1.createRoom();
        CorridorGenerator obj2 = new CorridorGenerator(obj1.getRoomList());
        StairsGenerator obj3 = new StairsGenerator(obj1.getRoomList());
        Stairs sta = obj3.createStairs();
        PlayerGenerator obj4 = new PlayerGenerator(obj1.getRoomList(),sta,"BLUE");
        Player player = obj4.createPlayer();
        LightRadius lr = new LightRadius();
        
        ArrayList<Room> roomList = obj1.getRoomList();
        ArrayList<Corridor> corridorList = new ArrayList<Corridor>();
        corridorList = obj2.createCorridors();
        ArrayList<Coordinates> legalCoordsList = new ArrayList<Coordinates>();
        for (int i=0;i<roomList.size();i++) {
            for (int j=roomList.get(i).getxFirst();j<roomList.get(i).getxSecond();j+=25) {
                for (int k=roomList.get(i).getyFirst();k<roomList.get(i).getySecond();k+=25) {
                    Coordinates loc = new Coordinates(j,k);
                    legalCoordsList.add(loc);
                }
            }
        }
        
        for (int i=0;i<corridorList.size();i++) {
            for (int j=-25;j<=corridorList.get(i).getLength();j+=25) {
                if (corridorList.get(i).getxc1() == corridorList.get(i).getxc2()) {
                    Coordinates loc = new Coordinates((int) (corridorList.get(i).getxc1()-12.5),(int) (j+corridorList.get(i).getyc1()-12.5));
                    legalCoordsList.add(loc);
                }
                else if (corridorList.get(i).getyc1() == corridorList.get(i).getyc2()) {
                    Coordinates loc = new Coordinates((int) (j+corridorList.get(i).getxc1()-12.5),(int) (corridorList.get(i).getyc1()-12.5));
                    legalCoordsList.add(loc);
                }
            }
        }
        
        
        //ensure duplicates in legalCoordsList don't cause character to move multiple times an iteration
        int marker = 0;
        //ensure a movement has been made before refreshing to save processing speed
        int marker2 = 0;
        //ensure another key hasn't been pressed so only one is processed per iteration
        int marker3 = 0;
        //first run marker
        int marker4 = 0;
        //whether or not to draw red dot, flashing light
        int redDotMarker = 0;
        boolean atStairs = false;
        StdDraw.enableDoubleBuffering();
        ArrayList<Corridor> tempcSeenList = new ArrayList<Corridor>();
        
        double xc1 = 0.0;
        double xc2 = 0.0;
        double yc1 = 0.0;
        double yc2 = 0.0;
        double playerx = 0.0;
        double playery = 0.0;
        
        while (!atStairs) {
            
            marker2 = 0;
            
            //d
            if (StdDraw.isKeyPressed(68)&& marker3 == 0) {
                for (int i=0;i<legalCoordsList.size();i++) {
                    if (legalCoordsList.get(i).getx() == player.getx()+25 && legalCoordsList.get(i).gety() == player.gety() && marker == 0){
                        player.setx(player.getx()+25);
                        marker = 1;
                        
                    }
                    
                }
                marker = 0;
                marker2 = 1;
                marker3 = 1;
            }
            //a
            if (StdDraw.isKeyPressed(65) && marker3 == 0) {
                for (int i=0;i<legalCoordsList.size();i++) {
                    if (legalCoordsList.get(i).getx() == player.getx()-25 && legalCoordsList.get(i).gety() == player.gety() && marker == 0) {
                        player.setx(player.getx()-25);
                        marker = 1;
                    }
                }
                marker = 0;
                marker2 = 1;
                marker3 = 1;
            }
            //w
            if (StdDraw.isKeyPressed(87)&& marker3 == 0) {
                for (int i=0;i<legalCoordsList.size();i++) {
                    if (legalCoordsList.get(i).gety() == player.gety()+25 && legalCoordsList.get(i).getx() == player.getx() && marker == 0){
                        player.sety(player.gety()+25);
                        marker = 1;
                    }
                    
                }
                marker = 0;
                marker2 = 1;
                marker3 = 1;
            }
            //s
            if (StdDraw.isKeyPressed(83)&& marker3 == 0) {
                for (int i=0;i<legalCoordsList.size();i++) {
                    if (legalCoordsList.get(i).gety() == player.gety()-25 && legalCoordsList.get(i).getx() == player.getx() && marker == 0){
                        player.sety(player.gety()-25);
                        marker = 1;
                    }
                    
                }
                marker = 0;
                marker2 = 1;
                marker3 = 1;
                
            }
            
            marker3 = 0;
            
            if (marker4 == 0) {
                StdDraw.clear(StdDraw.LIGHT_GRAY);
                
            }
            
            if (marker2 == 1 || marker4 == 0) {
                
                StdDraw.setXscale(player.getx()-150, player.getx()+150);
                StdDraw.setYscale(player.gety()-150, player.gety()+150);
                
                StdDraw.setPenRadius(.025);
                for (int i=0;i<roomList.size();i++)
                    obj1.drawRoom(roomList.get(i));
                
                for (int i=0;i<corridorList.size();i++) {
                    obj2.drawCorridorLines(corridorList.get(i));
                }
                for (int i=0;i<corridorList.size();i++)
                    obj2.drawCorridorRects(corridorList.get(i));
                
                StdDraw.setPenRadius(.0125);
                
                obj3.drawStairs(sta);
            
                
                obj4.drawPlayer(player);
                
                lr.drawRadius(player);
                
                StdDraw.setPenRadius(.0025);
                obj1.updaterSeenList(player);
                
                
                for (int i=0;i<obj1.getrSeenList().size();i++) {
                    obj1.drawMiniRoom(obj1.getrSeenList().get(i), player);
                    if (obj1.getrSeenList().get(i).isStairRoom()) {
                        obj3.drawMiniStairs(sta, player);
                    }
                    
                }
                
                
                
                
                StdDraw.setPenRadius(.0025);
                
                
                obj2.updatecSeenList(player);
                    
                for (int i=0;i<obj2.getcSeenList().size();i++)
                    obj2.drawMiniCorridorLines(obj2.getcSeenList().get(i),player);
                
                for (int i=0;i<obj2.getcSeenList().size();i++)
                    obj2.drawMiniCorridorRects(obj2.getcSeenList().get(i),player);
                
                

                marker4 = 1;
                
                obj4.drawMiniPlayer(player);
            
                StdDraw.show();
            }
            
            
            
            if (player.getx() == sta.getx() && player.gety() == sta.gety()) {
                atStairs = true;
                if (repetitions == 3) {
                    StdDraw.clear();
                    StdDraw.show();
                    
                }
            }
            
            if (!StdDraw.isKeyPressed(32)) {
                StdDraw.pause(100);
                time += 0.1;
            }
            
            
            if (marker2 != 0) {
                StdDraw.pause(50);
                time += 0.05;
            }
            
            
           
            StdDraw.pause(33);
            time += .045;
            
            ++redDotMarker;
            
            if (obj2.noCorridors() && roomCount > 1)
                return 0;
            
            StdDraw.clear(StdDraw.LIGHT_GRAY);
            
            
            
        }
        return time;
    }
    
    
    public static void main(String args[]){
        double time = 0.0;
        double tempTime = 0.0;
        aaaMain main = new aaaMain();
        for (int i=1;i<=3;i++) {
            tempTime = main.runGame(i);
            time += tempTime;
            if (tempTime == 0)
                --i;
        }
        System.out.printf("Congrats! Finish Time: %.3f %n", time);
        
    }
}

