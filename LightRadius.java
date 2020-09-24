public class LightRadius {
    
    
    
    public void drawRadius(Player player) {
        StdDraw.setPenColor(StdDraw.BLACK);
        //outer borders 80,30
        StdDraw.filledRectangle(player.getx()+12.5,player.gety()+150+12.5,500,70);
        StdDraw.filledRectangle(player.getx()+12.5+150,player.gety()+12.5,70,500);
        StdDraw.filledRectangle(player.getx()+12.5,player.gety()-150+12.5,500,70);
        StdDraw.filledRectangle(player.getx()+12.5-150,player.gety()+12.5,70,500);
        //inner corner 60,70
        StdDraw.filledRectangle(player.getx()+12.5+150,player.gety()+150+12.5,90,90);
        StdDraw.filledRectangle(player.getx()+12.5-150,player.gety()+150+12.5,90,90);
        StdDraw.filledRectangle(player.getx()+12.5+150,player.gety()-150+12.5,90,90);
        StdDraw.filledRectangle(player.getx()+12.5-150,player.gety()-150+12.5,90,90);
        //other corners
        StdDraw.filledRectangle(player.getx()+12.5+150,player.gety()+150+12.5,80,120);
        StdDraw.filledRectangle(player.getx()+12.5+150,player.gety()-150+12.5,80,120);
        StdDraw.filledRectangle(player.getx()+12.5-150,player.gety()+150+12.5,80,120);
        StdDraw.filledRectangle(player.getx()+12.5-150,player.gety()-150+12.5,80,120);
        
        StdDraw.filledRectangle(player.getx()+12.5+150,player.gety()+150+12.5,120,80);
        StdDraw.filledRectangle(player.getx()+12.5+150,player.gety()-150+12.5,120,80);
        StdDraw.filledRectangle(player.getx()+12.5-150,player.gety()+150+12.5,120,80);
        StdDraw.filledRectangle(player.getx()+12.5-150,player.gety()-150+12.5,120,80);
    }
    
}